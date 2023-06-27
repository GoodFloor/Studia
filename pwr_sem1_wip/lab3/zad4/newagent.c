//lab3 - Łukasz Machnik
#include <stdio.h>
#include "agents.h"

struct agent newagent(int X, int Y)
{
    struct agent newA;
    newA.posX = X;
    newA.posY = Y;
    return newA;
}
