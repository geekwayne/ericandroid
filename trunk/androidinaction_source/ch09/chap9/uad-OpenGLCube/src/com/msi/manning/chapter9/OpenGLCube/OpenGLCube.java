package com.msi.manning.chapter9.OpenGLCube;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGL11;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLU;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class OpenGLCube extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(new DrawingSurfaceView(this));
    }

    class DrawingSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

        public SurfaceHolder mHolder;
        float xrot = 0.0f; 
        float yrot = 0.0f; 

        public DrawingThread mThread;

        public DrawingSurfaceView(Context c) {
            super(c);
            init();
        }

      
         

        
        public void init() {

            this.mHolder = getHolder();

            this.mHolder.addCallback(this);
            this.mHolder.setType(SurfaceHolder.SURFACE_TYPE_GPU);
        }

        public void surfaceCreated(SurfaceHolder holder) {

            this.mThread = new DrawingThread();
            this.mThread.start();
        }

        public void surfaceDestroyed(SurfaceHolder holder) {

            this.mThread.waitForExit();
            this.mThread = null;
        }

        public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {

            this.mThread.onWindowResize(w, h);
        }

        class DrawingThread extends Thread {

            boolean stop;
            int w;
            int h;

            boolean changed = true;

            DrawingThread() {
                super();
                this.stop = false;
                this.w = 0;
                this.h = 0;
            }

            @Override
            public void run() {

                EGL10 egl = (EGL10) EGLContext.getEGL();

                EGLDisplay dpy = egl.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);

                int[] version = new int[2];
                egl.eglInitialize(dpy, version);

                int[] configSpec = { EGL10.EGL_RED_SIZE, 5, EGL10.EGL_GREEN_SIZE, 6, EGL10.EGL_BLUE_SIZE, 5,
                    EGL10.EGL_DEPTH_SIZE, 16, EGL10.EGL_NONE };
                EGLConfig[] configs = new EGLConfig[1];
                int[] num_config = new int[1];
                egl.eglChooseConfig(dpy, configSpec, configs, 1, num_config);
                EGLConfig config = configs[0];

                EGLContext context = egl.eglCreateContext(dpy, config, EGL10.EGL_NO_CONTEXT, null);

                EGLSurface surface = null;
                GL10 gl = null;

                // now draw forever until asked to stop
                while (!this.stop) {
                    int W, H; // copies of  width and height
                    boolean updated;
                    synchronized (this) {
                        updated = this.changed;
                        W = this.w;
                        H = this.h;
                        this.changed = false;
                    }
                    if (updated) {
                        /*
                         * The window size has changed, so we need to create a new surface.
                         */
                        if (surface != null) {

                            /*
                             * unbind and destroy the old EGL surface, if there is one.
                             */
                            egl.eglMakeCurrent(dpy, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
                            egl.eglDestroySurface(dpy, surface);
                        }

                        surface = egl.eglCreateWindowSurface(dpy, config, DrawingSurfaceView.this.mHolder, null);

                        egl.eglMakeCurrent(dpy, surface, surface, context);

                        gl = (GL10) context.getGL();

                        gl.glDisable(GL10.GL_DITHER);

                        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);

                        gl.glClearColor(1, 1, 1, 1);
                        gl.glEnable(GL10.GL_CULL_FACE);
                        gl.glShadeModel(GL10.GL_SMOOTH);
                        gl.glEnable(GL10.GL_DEPTH_TEST);

                        gl.glViewport(0, 0, W, H);

                        float ratio = (float) W / H;
                        gl.glMatrixMode(GL10.GL_PROJECTION);
                        gl.glLoadIdentity();
                        gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);
                    }

                  

                    
                    drawFrame(gl, w, h);

                    egl.eglSwapBuffers(dpy, surface);

                    if (egl.eglGetError() == EGL11.EGL_CONTEXT_LOST) {

                        Context c = getContext();
                        if (c instanceof Activity) {
                            ((Activity) c).finish();
                        }
                    }
                }

                egl.eglMakeCurrent(dpy, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
                egl.eglDestroySurface(dpy, surface);
                egl.eglDestroyContext(dpy, context);
                egl.eglTerminate(dpy);

            }

            public void onWindowResize(int w, int h) {
                synchronized (this) {
                    this.w = w;
                    this.h = h;
                    this.changed = true;
                }
            }

            public void waitForExit() {
                this.stop = true;
                try {
                    join();
                } catch (InterruptedException ex) {
                }
            }


                
                protected FloatBuffer makeFloatBuffer (float[] arr) {
                    ByteBuffer bb = ByteBuffer.allocateDirect(arr.length*4);
                    bb.order(ByteOrder.nativeOrder());
                    FloatBuffer fb = bb.asFloatBuffer();
                    fb.put(arr);
                    fb.position(0);
                    return fb;
                }

                private void drawFrame(GL10 gl, int w1, int h1) {
                    float mycube[] = {                             
                        // FRONT
                        -0.5f, -0.5f,  0.5f,
                        0.5f, -0.5f,  0.5f,
                        -0.5f,  0.5f,  0.5f,
                        0.5f,  0.5f,  0.5f,
                        // BACK
                        -0.5f, -0.5f, -0.5f,
                        -0.5f,  0.5f, -0.5f,
                        0.5f, -0.5f, -0.5f,
                         0.5f,  0.5f, -0.5f,
                        // LEFT
                        -0.5f, -0.5f,  0.5f,
                        -0.5f,  0.5f,  0.5f,
                        -0.5f, -0.5f, -0.5f,
                        -0.5f,  0.5f, -0.5f,
                        // RIGHT
                        0.5f, -0.5f, -0.5f,
                        0.5f,  0.5f, -0.5f,
                        0.5f, -0.5f,  0.5f,
                        0.5f,  0.5f,  0.5f,
                        // TOP
                        -0.5f,  0.5f,  0.5f,
                        0.5f,  0.5f,  0.5f,
                        -0.5f,  0.5f, -0.5f,
                        0.5f,  0.5f, -0.5f,
                        // BOTTOM
                        -0.5f, -0.5f,  0.5f,
                        -0.5f, -0.5f, -0.5f,
                        0.5f, -0.5f,  0.5f,
                        0.5f, -0.5f, -0.5f,
                    };
                    FloatBuffer cubeBuff;                                      

                    cubeBuff = makeFloatBuffer(mycube);                        

                    gl.glEnable(GL10.GL_DEPTH_TEST);                           
                    gl.glEnable(GL10.GL_CULL_FACE);
                    gl.glDepthFunc(GL10.GL_LEQUAL);                            
                    gl.glClearDepthf(1.0f); 
                    gl.glClear(GL10.GL_COLOR_BUFFER_BIT | 
        GL10.GL_DEPTH_BUFFER_BIT);
                    gl.glMatrixMode(GL10.GL_PROJECTION);
                    gl.glLoadIdentity();
                    gl.glViewport(0,0,w,h)  ;
                    GLU.gluPerspective(gl, 45.0f, 
        ((float)w1)/h1  , 1f, 100f);                                           
                    gl.glMatrixMode(GL10.GL_MODELVIEW);
                    gl.glLoadIdentity();
                    GLU.gluLookAt(gl, 0, 0, 3, 0, 0, 0, 0, 1, 0); 
                    gl.glShadeModel(GL10.GL_SMOOTH);                      
                    gl.glVertexPointer(3, GL10.GL_FLOAT, 0, cubeBuff);
                    gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
                    gl.glRotatef(xrot, 1, 0, 0);                       
                    gl.glRotatef(yrot, 0, 1, 0);                       
                    gl.glColor4f(1.0f, 0, 0, 1.0f);
                    gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);          

                    gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 4, 4);
                    gl.glColor4f(0, 1.0f, 0, 1.0f);
                    gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 8, 4);
                    gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 12, 4);
                    gl.glColor4f(0, 0, 1.0f, 1.0f);
                    gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 16, 4);
                    gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 20, 4);
                    xrot += 1.0f;                                              
                    yrot += 0.5f;                                              
                

            }
        }
    }
}