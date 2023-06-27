//lab2 - Łukasz Machnik
#include <stdio.h>
#include <stdbool.h>

bool czyWzgledniePierwsze(int a, int b)
{
    while(a % b > 0)
    {
        int t = a;
        a = b;
        b = t % b;
    }
    if(b == 1)
        return true;
    else
        return false;
}

int main()
{
    int n = 1000;
    for(int k = 1; k <= n; k++)
    {
        int ileWzgledniePierwszych = 0;
        for(int i = 1; i <= k; i++)
        {
            for(int j = 1; j <= k; j++)
            {
                if(czyWzgledniePierwsze(i, j))
                ileWzgledniePierwszych++;
            }
        }
        printf("%d;%lf\n", k, (double)ileWzgledniePierwszych/(k*k));
    }
        
    return 0;
}
