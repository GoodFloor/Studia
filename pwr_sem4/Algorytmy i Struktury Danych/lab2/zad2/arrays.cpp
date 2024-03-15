#include <iostream>
#include "arrays.hpp"

// Returns pointer to new array of given size 
int *newArray(int size)
{
    return (int*)malloc(sizeof(int) * size);
}

// Prints content of an array
void printArray(int *array, int size)
{
    std::cout << "{";
    if (size != 0)
    {
        std::cout << array[0];
        for (int i = 1; i < size; i++)
        {
            std::cout << ", " << array[i];
        }
    }
    std::cout << "}";
}
