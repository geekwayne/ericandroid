package com.msi.manning.chapter10.SimpleCamera;

import java.io.OutputStream;

import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;

public class ImageCaptureCallback implements PictureCallback {

    private OutputStream filoutputStream;

    public ImageCaptureCallback(OutputStream filoutputStream) {
        this.filoutputStream = filoutputStream;
    }

    public void onPictureTaken(byte[] data, Camera camera) {
        try {
            this.filoutputStream.write(data);
            this.filoutputStream.flush();
            this.filoutputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
