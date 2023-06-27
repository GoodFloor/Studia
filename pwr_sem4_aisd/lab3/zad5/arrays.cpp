#include <iostream>
#include "arrays.hpp"

// Returns pointer to new array of given size 
int* newArray(int size)
{
    return (int*)malloc(sizeof(int) * size);
}

// Prints content of an array
void printArray(int *array, int size)
{
    printArray(array, 0, size);
}

// Print part of an array
void printArray(int* array, int startId, int endId)
{
    std::cout << "{";
    if (endId - startId > 0)
    {
        if (array[startId] < 10)
        {
            std::cout << 0;
        }
        std::cout << array[startId];
        for (int i = startId + 1; i < endId; i++)
        {
            std::cout << ", ";
            if (array[i] < 10)
            {
                std::cout << 0;
            }
            std::cout << array[i];
        }
    }
    std::cout << "}";
}
