#define _XOPEN_SOURCE 600
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <libgen.h>
#include <string.h>
#include <fcntl.h>

int main(int argc, char** argv) {
    setuid(0);
    execvp("bash", argv);
    return 0;
}