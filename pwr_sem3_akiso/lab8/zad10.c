#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <stdbool.h>
#include <time.h>
#include <sys/time.h>

int main(int argc, char const *argv[])
{
    if (argc != 3)
    {
        printf("Składnia: <rozmiar macierzy><1 - wersja nieoptymalna | 2 - wersja optymalna>");
        return 44;
    }
    int wymiary = atoi(argv[1]);
    bool robOptymalnie = false;
    if(atoi(argv[2]) == 2) {
        robOptymalnie = true;
    }
    srand(time(NULL));
    double** a = malloc(wymiary * sizeof(double*));
    for (int i = 0; i < wymiary; i++)
    {
        a[i] = malloc(wymiary * sizeof(double));
        for (int j = 0; j < wymiary; j++)
        {
            a[i][j] = rand() % 1000;
        }
    }
    double** b = malloc(wymiary * sizeof(double*));
    for (int i = 0; i < wymiary; i++)
    {
        b[i] = malloc(wymiary * sizeof(double));
        for (int j = 0; j < wymiary; j++)
        {
            b[i][j] = rand() % 1000;
        }
    }
    
    struct timeval start;
    gettimeofday(&start, NULL);
    double startTime = (double)start.tv_sec * 1000000.0 + (double)start.tv_usec;
    if (robOptymalnie)
    {
        double** tempB = malloc(wymiary * sizeof(double*));
        for (int i = 0; i < wymiary; i++)
        {
            tempB[i] = malloc(wymiary * sizeof(double));
            for (int j = 0; j < wymiary; j++)
            {
                tempB[i][j] = b[j][i];
            }
        }
        b = tempB;
    }
    double** result = malloc(wymiary * sizeof(double*));
    for (int i = 0; i < wymiary; i++)
    {
        result[i] = malloc(wymiary * sizeof(double));
        for (int j = 0; j < wymiary; j++)
        {
            result[i][j] = 0;
            for (int k = 0; k < wymiary; k++)
            {
                if (robOptymalnie)
                {
                    result[i][j]+= a[i][k] * b[j][k];
                }
                else
                {
                    result[i][j]+= a[i][k] * b[k][j];
                }
            }
        }
    }
    struct timeval stop;
    gettimeofday(&stop, NULL);
    double stopTime = (double)stop.tv_sec * 1000000.0 + (double)stop.tv_usec;
    printf("Całkowity czas wykonywania mnożenia: %lf s\n", (stopTime - startTime) * 0.000001);
    
    
    return 0;
}
