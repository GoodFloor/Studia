#include <stdio.h>
#include <stdlib.h>
#include "lifo.h"

int main(int argc, char const *argv[])
{
    struct lifoList list;
    list.head = NULL;
    for (int i = 0; i < 100; i++)
    {
        struct lifoListElement* newElement = malloc(sizeof(struct lifoListElement));
        newElement->id = i;
        printf("Dodaję element o id %i\n", i);
        push(&list, newElement);
    }
    
    for (int i = 0; i < 100; i++)
    {
        struct lifoListElement* poppedElement = pop(&list);
        if (poppedElement->id != -1)
        {
            printf("Pobrano element o id %i\n", poppedElement->id);
        }
        free(poppedElement);
    }
    return 0;
}
