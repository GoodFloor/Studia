#include <stdio.h>
#include <stdlib.h>
#include "fifo.h"

int main(int argc, char const *argv[])
{
    struct fifoList list;
    list.head = NULL;
    list.butt = NULL;
    for (int i = 0; i < 100; i++)
    {
        struct fifoListElement* newElement = malloc(sizeof(struct fifoListElement));
        newElement->id = i;
        printf("Dodaję element o id %i\n", i);
        push(&list, newElement);
    }
    
    for (int i = 0; i < 100; i++)
    {
        struct fifoListElement* poppedElement = pop(&list);
        if (poppedElement->id != -1)
        {
            printf("Pobrano element o id %i\n", poppedElement->id);
        }
        free(poppedElement);
    }
    return 0;
}
