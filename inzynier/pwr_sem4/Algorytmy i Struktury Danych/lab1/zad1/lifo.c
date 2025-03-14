#include <stdio.h>
#include "lifo.h"

void push(struct lifoList* list, struct lifoListElement* newElement){
    newElement->next = list->head;
    list->head = newElement;
}
struct lifoListElement* pop(struct lifoList* list){
    struct lifoListElement* popped;
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