#include <stdio.h>
#include <stdlib.h>
#include <dlfcn.h>
int main(int argc, char **argv) 
{
    void *handle;
    double (*cosine)(double);
    char *error;
    handle = dlopen ("libm.so", RTLD_LAZY);
    if (!handle) {
        fprintf (stderr, "%s\n", dlerror());
        exit(1);
    }
    dlerror();    /* Clear any existing error */
    cosine = dlsym(handle, "cos");
    if ((error = dlerror()) != NULL)  {
        fprintf (stderr, "%s\n", error);
        exit(1);
    }
    printf ("%f\n", (*cosine)(2.0));
    dlclose(handle);
    return 0;
}