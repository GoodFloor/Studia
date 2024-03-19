#include <float.h>
#include <stdio.h>

int main(int argc, char const *argv[])
{
    printf("%.32f\n", FLT_EPSILON);
    printf("%.64f\n", DBL_EPSILON);
    printf("%.128Lf\n", LDBL_EPSILON);
    printf("%.0f\n", FLT_MAX);
    printf("%.0f\n", DBL_MAX);
    return 0;
}
