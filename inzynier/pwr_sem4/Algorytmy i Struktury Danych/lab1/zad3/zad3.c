#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <sys/time.h>

struct list
{
    int size;
    struct listElement* head;
};
struct listElement
{
    int id;
    int value;
    struct listElement* prev;
    struct listElement* next;
};
// Tworzy nową listę i zwraca wskaźnik do niej
struct list* newList()
{
    struct list* new = malloc(sizeof(struct list*));
    new->size = 0;
    new->head = NULL;
    return new;
}
// Tworzy nowy element listy i zwraca wskaźnik do niego
struct listElement* newListElement(int value)
{
    struct listElement* new = malloc(sizeof(struct listElement*));
    new->value = value;
    new->id = 0;
    new->prev = NULL;
    new->next = NULL;
    return new; 
}
// Dodaje element na koniec listy
void push(struct list* list, struct listElement* newElement)
{
    newElement->id = list->size;
    list->size++;
    if (list->head == NULL)
    {
        list->head = newElement;
        newElement->prev = newElement;
        newElement->next = newElement;
        return;
    }
    struct listElement* curr = list->head->prev;
    curr->next = newElement;
    newElement->prev = curr;
    newElement->next = list->head;
    list->head->prev = newElement;
}

// Zwraca element z listy o podanym atrybucie id
struct listElement* get(int id, struct list* from)
{
    if (from->head == NULL)
    {
        return NULL;
    }
    struct listElement* curr = from->head;
    if (id < from->size / 2)
    {
        while (curr->id != id && curr->next != from->head)
        {
            curr = curr->next;
        }
    }
    else
    {
        while (curr->id != id && curr->prev != from->head)
        {
            curr = curr->prev;
        }
    }
    
    if (curr->id == id)
    {
        return curr;
    }
    else 
    {
        return NULL;
    }
}
// Usuwa element o podanym id z listy i zwraca go
struct listElement* pop(int id, struct list* from)
{
    if (from->head == NULL)
    {
        return NULL;
    }
    else if(from->head == from->head->next && from->head->id == id)
    {
        struct listElement* curr = from->head;
        from->head = NULL;
        curr->next = NULL;
        curr->prev = NULL;
        from->size = 0;
        return curr;
    }
    
    struct listElement* curr = get(id, from);
    if (curr == NULL)
    {
        return curr;
    }
    
    struct listElement* prev = curr->prev;
    struct listElement* next = curr->next;
    prev->next = next;
    next->prev = prev;
    curr->next = NULL;
    curr->prev = NULL;
    if(from->head->id == id) 
    {
        from->head = next;
    }
}
// Wypisuje wszystkie elementy listy
void printList(struct list* list)
{
    if (list->head == NULL)
    {
        printf("\tLista jest pusta\n");
        return;
    }
    struct listElement* curr = list->head;
    do
    {
        printf("\t[%i] = %i\n", curr->id, curr->value);
        curr = curr->next;
    } while (curr != list->head);
}
// Wypisuje wszystkie elementy listy w odwrotnej kolejności
void reversePrintList(struct list* list)
{
    if (list->head == NULL)
    {
        printf("\tLista jest pusta\n");
        return;
    }
    struct listElement* curr = list->head;
    do
    {
        printf("\t[%i] = %i\n", curr->id, curr->value);
        curr = curr->prev;
    } while (curr != list->head);
}
// Do listy 1 dołącza listę 2
void merge(struct list* list1, struct list* list2)
{
    if (list1->head == NULL)
    {
        list1->head = list2->head;
        return;
    }
    else if (list2->head == NULL)
    {
        return;
    }
    else
    {
        struct listElement* curr = list2->head;
        do
        {
            curr->id += list1->size;
            curr = curr->next;
        } while (curr != list2->head);
        
        struct listElement* last1 = list1->head->prev;
        struct listElement* last2 = list2->head->prev;
        last1->next = list2->head;
        list2->head->prev = last1;
        last2->next = list1->head;
        list1->head->prev = last2;
    }
}

int main(int argc, char const *argv[])
{
    struct list* list1 = newList();
    for (int i = 0; i < 10000; i++)
    {
        struct listElement* new = newListElement(i);
        push(list1, new);
    }
    int k = 1000;
    long int avg;
    // Czas dostępu do elementu o id 1000
    avg = 0;
    for (int i = 0; i < k; i++)
    {
        struct timeval begin;
        struct timeval end;
        struct timeval difference;
        gettimeofday(&begin, NULL);
        get(1000, list1);
        gettimeofday(&end, NULL);

        timersub(&end, &begin, &difference);
        avg += (long int)difference.tv_sec * 1000000 + (long int)difference.tv_usec;
    }
    avg /= k;
    printf("Średni czas dostępu do elementu o id 1000: %li\n", avg);

    // Czas dostępu do elementu o id 2500
    avg = 0;
    for (int i = 0; i < k; i++)
    {
        struct timeval begin;
        struct timeval end;
        struct timeval difference;
        gettimeofday(&begin, NULL);
        get(2500, list1);
        gettimeofday(&end, NULL);

        timersub(&end, &begin, &difference);
        avg += (long int)difference.tv_sec * 1000000 + (long int)difference.tv_usec;
    }
    avg /= k;
    printf("Średni czas dostępu do elementu o id 2500: %li\n", avg);

    // Czas dostępu do elementu o id 5000
    avg = 0;
    for (int i = 0; i < k; i++)
    {
        struct timeval begin;
        struct timeval end;
        struct timeval difference;
        gettimeofday(&begin, NULL);
        get(5000, list1);
        gettimeofday(&end, NULL);

        timersub(&end, &begin, &difference);
        avg += (long int)difference.tv_sec * 1000000 + (long int)difference.tv_usec;
    }
    avg /= k;
    printf("Średni czas dostępu do elementu o id 5000: %li\n", avg);
    // Czas dostępu do elementu o id 9000
    avg = 0;
    for (int i = 0; i < k; i++)
    {
        struct timeval begin;
        struct timeval end;
        struct timeval difference;
        gettimeofday(&begin, NULL);
        get(9000, list1);
        gettimeofday(&end, NULL);

        timersub(&end, &begin, &difference);
        avg += (long int)difference.tv_sec * 1000000 + (long int)difference.tv_usec;
    }
    avg /= k;
    printf("Średni czas dostępu do elementu o id 9000: %li\n", avg);

    // Czas dostępu do losowego elementu
    srand(time(NULL));
    avg = 0;
    for (int i = 0; i < k; i++)
    {
        struct timeval begin;
        struct timeval end;
        struct timeval difference;
        gettimeofday(&begin, NULL);
        int id = rand() % 10000;
        get(id, list1);
        gettimeofday(&end, NULL);

        timersub(&end, &begin, &difference);
        avg += (long int)difference.tv_sec * 1000000 + (long int)difference.tv_usec;
    }
    avg /= k;
    printf("Średni czas dostępu do losowego elementu: %li\n", avg);


    struct list* list2 = newList();
    struct list* list3 = newList();
    printf("Lista2:\n");
    printList(list2);
    for (int i = 0; i < 10; i++)
    {
        struct listElement* new = newListElement(i);
        push(list2, new);
    }
    for (int i = 10; i < 20; i++)
    {
        struct listElement* new = newListElement(i);
        push(list3, new);
    }
    printf("Lista2:\n");
    printList(list2);
    printf("Lista3:\n");
    printList(list3);
    merge(list2, list3);
    printf("Lista2 po złączeniu:\n");
    printList(list2);
    printf("Lista2 w odwrotnej kolejności:\n");
    reversePrintList(list2);
    printf("Usuwanie elementu o id 10 z listy 2\n");
    pop(10, list2);
    printf("Lista 2:\n");
    printList(list2);
    printf("Lista 2 od tyłu:\n");
    reversePrintList(list2);
    return 0;
}

