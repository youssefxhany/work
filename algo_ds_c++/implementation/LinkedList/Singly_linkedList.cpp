// Singly_linkedList.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

//Advantages of Linked Lists:
//1-Size of linked lists is not fixed, they can expandand shrink during run time.
//2-Insertionand Deletion Operations are fastand easier in Linked Lists.
//3-Memory allocation is done during run - time(no need to allocate any fixed memory).
//4-Data Structures like Stacks, Queues, and trees can be easily implemented using Linked list.

//Disadvantages of Linked Lists
//1-Memory consumption is more in Linked Lists when compared to arrays.Because each node contains a pointer in linked listand it requires extra memory.
//2-Elements cannot be accessed at random in linked lists.
//3-Traversing from reverse is not possible in singly linked lists.

#include <iostream>
#include <stdio.h>
using namespace std;

struct Node{
    int value;
    struct Node *next;
};

class LinkedList {
private:
    struct Node *head,*tail;
    int capacity = 0;

public:
    LinkedList(int value);
    void append(int new_value);
    void prepend(int new_value);
    void insert(int new_value, int index);
    void remove(int index);
    void displayLinkedList();
    ~LinkedList();

private:
    struct Node* allocateHeapNode();
    void deallocateHeapNode(struct Node *node);
    struct Node* traverse(struct Node* current_node, int index);
};

int main()
{
    LinkedList linkedList(2);
    linkedList.append(3);
    linkedList.append(4);
    linkedList.append(5);
    linkedList.prepend(1);
    linkedList.prepend(0);
    linkedList.insert(100, 2);
    linkedList.insert(100, 0);
    linkedList.insert(100, 1000);
    linkedList.remove(0);
    linkedList.remove(7);
    linkedList.remove(2);
    linkedList.remove(1000);
    linkedList.remove(1);
    linkedList.displayLinkedList();
    linkedList.remove(1000);
    linkedList.remove(1000);
    linkedList.remove(1000);
    linkedList.remove(1000);
    linkedList.remove(1000);
}

LinkedList::LinkedList(int value) {
    head = tail = allocateHeapNode();
    head->value = value;
    head->next = NULL;
    capacity++;
}

void LinkedList::append(int new_value) {
    struct Node* new_node = tail->next = allocateHeapNode();
    new_node->value = new_value;
    new_node->next = NULL;
    tail = new_node;
    capacity++;
    return;
}

void LinkedList::prepend(int new_value) {
    struct Node *new_node = allocateHeapNode();
    struct Node *old_head = head;
    head = new_node;
    new_node->value = new_value;
    new_node->next = old_head;
    capacity++;
    return;
}

void LinkedList::insert(int new_value, int index) {
    if (index >= capacity) index = capacity - 1;
    if (index == 0) prepend(new_value);
    else if (index == capacity - 1) append(new_value);
    else {
        struct Node* current_node = traverse(head, index);
        struct Node* new_node = allocateHeapNode();
        new_node->next = current_node->next;
        new_node->value = new_value;
        current_node->next = new_node;
        capacity++;
    }
}

void LinkedList::remove(int index) {
    if (capacity == 0) {
        cout << "EMPTY LINKED LIST, NOTHING TO BE REMOVED" << endl;
        return;
    }
    if (index == 0) {
        struct Node* first_node = head;
        head = head->next;
        deallocateHeapNode(first_node);
    }
    else if (index >= capacity - 1) {
        struct Node* current_node = tail = traverse(head, capacity - 1);
        deallocateHeapNode(current_node->next);
        current_node->next = NULL;
    }
    else {
        struct Node *current_node = traverse(head, index);
        struct Node *removed_node = current_node->next;
        current_node->next = removed_node->next;
        deallocateHeapNode(removed_node);
    }
    capacity--;
    return;
}

void LinkedList::displayLinkedList() {
    struct Node *start = head;
    for (int i = 0; i < capacity;i++) {
        cout << start->value << "-->";
        start = start->next;
    }
    cout << "NULL" << endl;
}

LinkedList::~LinkedList() {
    cout << "Distructor executed" << endl;
}

struct Node * LinkedList::allocateHeapNode() {
    return (struct Node *)malloc(sizeof(struct Node));
}

void LinkedList::deallocateHeapNode(struct Node* node) {
    free(node);
    node = NULL;
}

struct Node* LinkedList::traverse(struct Node *current_node, int index) {
    for (int i = 0; i < (index - 1); i++) {
        current_node = current_node->next;
    }
    return current_node;
}

