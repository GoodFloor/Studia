//lab3 - Łukasz Machnik
#include <stdio.h>
#include <math.h>
#include "agents.h"

double distance(struct agent a1, struct agent a2)
{
    double c;
    c = sqrt(pow(a1.posX - a2.posX, 2) + pow(a1.posY - a2.posY, 2));
    return c;
}
