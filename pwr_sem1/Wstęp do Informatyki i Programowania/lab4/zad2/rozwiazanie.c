//lab3 - Łukasz Machnik
#include <stdio.h>
#include <math.h>
#include "f.h"

double rozwiazanie(double a, double b, double eps)
{
    if((a == b && f(a) != 0) || (f(a) * f(b) > 0))
        return 0;
    if(a > b)
    {
        double temp = a;
        a = b;
        b = temp;
    }
    if(f(a) == 0)
        return a;
    else if(f(b) == 0)
        return b;
    while(b-a > eps && (f(a) * f(b) < 0))
    {
        if(f((a+b)/2.0) == 0)
            return (a+b)/2.0;
        else if(f(a) * f((a+b)/2.0) < 0)
            b = (a+b)/2.0;
        else
            a = (a+b)/2.0;
    }
    return (a+b)/2.0;
}
