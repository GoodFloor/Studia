#include <iostream>
#include <stdlib.h>
#include "hashHandler.hpp"

void printBoard(long long hash)
{
    unsigned long long mask = 0xf;
    int position = 15;
    mask = mask << (position * 4);
    for (int i = 0; i < 17; i++)
    {
        for (int j = 0; j < 17; j++)
        {
            if (i % 4 == 0 || j % 4 == 0)
            {
                std::cout << "#";
            }
            else if ((i % 2) == 1 || (j % 2) == 1)
            {
                std::cout << " ";
            }
            else
            {
                long long curr = (hash & mask) >> (position * 4);
                position--;
                std::cout << std::hex << curr << std::dec;
                mask = mask >> 4;
            }   
        }
        std::cout << std::endl;
    }
}

int findBlank(long long hash)
{
    unsigned long long mask = 0xf;
    int currentValue = hash & mask;
    int position = 0;
    while (currentValue != 0)
    {
        position++;
        mask = mask << 4;
        currentValue = (hash & mask) >> (position * 4);
    }
    return position;
}

bool isSolvable(long long hash)
{
    int numberOfInversions = 0;
    int blankPos;
    for (int i = 15; i >= 0; i--)
    {
        int value1 = (hash >> (4 * i)) & 0xf;
        if (value1 == 0)
        {
            blankPos = i;
            continue;
        }
        for (int j = i - 1; j >= 0; j--)
        {
            int value2 = (hash >> (4 * j)) & 0xf;
            if (value2 == 0)
            {
                continue;
            }
            else if (value2 < value1)
            {
                numberOfInversions++;
            }
        }
    }
    if ((blankPos / 4) % 2 == numberOfInversions % 2)
    {
        return true;
    }
    return false;
}

long long swapValues(long long hash, int pos1, int pos2)
{
    int shift1 = pos1 * 4;
    int shift2 = pos2 * 4;
    unsigned long long mask1 = (unsigned long long)0xf << shift1;    
    unsigned long long mask2 = (unsigned long long)0xf << shift2;
    unsigned long long value1 = ((hash & mask1) >> shift1) << shift2;
    unsigned long long value2 = ((hash & mask2) >> shift2) << shift1;
    hash = hash & ~mask1;
    hash = hash & ~mask2;
    hash += value1;
    hash += value2;    
    return hash;
}

long long moveNorth(long long hash) 
{
    int pos1 = findBlank(hash);
    if (pos1 < 12)
    {
        return swapValues(hash, pos1, pos1 + 4);
    }
    return hash;
}

long long moveEast(long long hash)
{
    int pos1 = findBlank(hash);
    if (pos1 % 4 != 0)
    {
        return swapValues(hash, pos1, pos1 - 1);
    }
    return hash;
}

long long moveSouth(long long hash)
{
    int pos1 = findBlank(hash);
    if (pos1 > 3)
    {
        return swapValues(hash, pos1, pos1 - 4);
    }
    return hash;
}

long long moveWest(long long hash)
{
    int pos1 = findBlank(hash);
    if (pos1 % 4 != 3)
    {
        return swapValues(hash, pos1, pos1 + 1);
    }
    return hash;
}

unsigned char heuristic(long long hash)
{
    unsigned char m = manhattanHeuristic(hash);
    unsigned char i = inversionHeuristic(hash);
    if (m > i)
    {
        return m;
    }
    return i;
}

unsigned char manhattanHeuristic(long long hash)
{
    unsigned char h = 0;
    long long mask = 0xf;
    for (int i = 0; i < 16; i++)
    {
        int value = (hash & mask) >> (i * 4) & 0xf;
        if (value != 0)
        {
            int expectedPos = 16 - value;
            h += abs((expectedPos / 4) - (i / 4));
            h += abs((expectedPos % 4) - (i % 4));
        }
        mask = mask << 4;
    }
    return h;
}

unsigned char inversionHeuristic(long long hash)
{
    unsigned char vert = 0, hor = 0;
    for (int i = 15; i >= 0; i--)
    {
        int value1 = (hash >> (4 * i)) & 0xf;
        if (value1 == 0)
        {
            continue;
        }
        for (int j = i - 1; j >= 0; j--)
        {
            int value2 = (hash >> (4 * j)) & 0xf;
            if (value2 == 0)
            {
                continue;
            }
            else if (value2 < value1)
            {
                hor++;
            }
        }
    }
    hor /= 3;
    unsigned long long transposedHash = hash;
    transposedHash = swapValues(transposedHash, 1, 4);
    transposedHash = swapValues(transposedHash, 2, 8);
    transposedHash = swapValues(transposedHash, 3, 12);
    transposedHash = swapValues(transposedHash, 6, 9);
    transposedHash = swapValues(transposedHash, 7, 13);
    transposedHash = swapValues(transposedHash, 11, 14);
    for (int i = 0; i < 15; i++)
    {
        int shift = 4 * i;
        unsigned long long mask = (unsigned long long)0xf << shift;
        unsigned long long value1 = (transposedHash & mask);
        transposedHash -= value1;
        value1 = value1 >> shift;
        unsigned long long value2;
        switch (value1)
        {
        case 1:
            value2 = 1;
            break;
        case 2:
            value2 = 5;
            break;
        case 3:
            value2 = 9;
            break;
        case 4:
            value2 = 13;
            break;
        case 5:
            value2 = 2;
            break;
        case 6:
            value2 = 6;
            break;
        case 7:
            value2 = 10;
            break;
        case 8:
            value2 = 14;
            break;
        case 9:
            value2 = 3;
            break;
        case 10:
            value2 = 7;
            break;
        case 11:
            value2 = 11;
            break;
        case 12:
            value2 = 15;
            break;
        case 13:
            value2 = 4;
            break;
        case 14:
            value2 = 8;
            break;
        case 15:
            value2 = 12;
            break;
        default:
            value2 = 0;
            break;
        }
        transposedHash += (value2 << shift); 
    }
    
    hash = transposedHash;
    for (int i = 15; i >= 0; i--)
    {
        int value1 = (hash >> (4 * i)) & 0xf;
        if (value1 == 0)
        {
            continue;
        }
        for (int j = i - 1; j >= 0; j--)
        {
            int value2 = (hash >> (4 * j)) & 0xf;
            if (value2 == 0)
            {
                continue;
            }
            else if (value2 < value1)
            {
                vert++;
            }
        }
    }
    vert /= 3;
    return hor + vert;
}
