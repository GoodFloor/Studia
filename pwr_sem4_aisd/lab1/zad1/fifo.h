struct fifoList
{
    struct fifoListElement* head;
    struct fifoListElement* butt;
};
struct fifoListElement
{
    int id;
    struct fifoListElement* next;
};
void push(struct fifoList*, struct fifoListElement*);
struct fifoListElement* pop(struct fifoList*);