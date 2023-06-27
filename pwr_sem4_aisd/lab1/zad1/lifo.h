struct lifoList
{
    struct lifoListElement* head;
};
struct lifoListElement
{
    int id;
    struct lifoListElement* next;
};
void push(struct lifoList*, struct lifoListElement*);
struct lifoListElement* pop(struct lifoList*);