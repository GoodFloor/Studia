#include <stdio.h>
#include "fifo.h"

void push(struct fifoList* list, struct fifoListElement* newElement){
    if (list->head == NULL)
    {
        list->head = newElement;
        list->butt = newElement;
    }
    newElement->next = NULL;
    list->butt->next = newElement;
    list->butt = newElement;
}
struct fifoListElement* pop(struct fifoList* list){
    struct fifoListElement* popped;
    if (list->head == NULL)
    {
        popped->id = -1;
        printf("Próba pobrania elementu z pustej listy\n");
        return popped;
    }
    popped = list->head;
    list->head = popped->next;
    return popped;
}