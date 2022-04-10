// doubly_VS_singly_linkedList.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <stdio.h>
using namespace std;

template<class T>
struct doublyNode {
    T value;
    struct doublyNode<T>* next;
    struct doublyNode<T>* prev;
};

template<class T>
class DoublyLinkedList {
private:
    struct doublyNode<T>* head, * tail;
    int capacity = 0;
public:
    DoublyLinkedList(T value);
    void append(T value);
    void prepend(T value);
    void insert(struct doublyNode<T>* node, T value);
    void removeFirst();
    void removeLast();
    void remove(struct doublyNode<T>* node);
    void printForward();
    void printBackward();
    struct doublyNode<T>* traverse(int index);
private:
    struct doublyNode<T>* allocateHeapNode(T value);
    void deallocateHeapNode(struct doublyNode<T>* node);
};

int main()
{
    DoublyLinkedList<int> doublyLinkedListInteger(1);
    doublyLinkedListInteger.append(2);
    doublyLinkedListInteger.append(3);
    doublyLinkedListInteger.append(4);
    doublyLinkedListInteger.append(5);
    doublyLinkedListInteger.prepend(0);
    doublyLinkedListInteger.insert(doublyLinkedListInteger.traverse(2), 100);
    doublyLinkedListInteger.removeFirst();
    doublyLinkedListInteger.removeLast();
    doublyLinkedListInteger.remove(doublyLinkedListInteger.traverse(1));
    doublyLinkedListInteger.printForward();
    doublyLinkedListInteger.printBackward();
    cout << "--------------------------------------------" << endl;

    DoublyLinkedList<char> doublyLinkedListChar('A');
    doublyLinkedListChar.append('B');
    doublyLinkedListChar.append('C');
    doublyLinkedListChar.append('D');
    doublyLinkedListChar.append('E');
    doublyLinkedListChar.prepend('Y');
    doublyLinkedListChar.insert(doublyLinkedListChar.traverse(2), '@');
    doublyLinkedListChar.removeFirst();
    doublyLinkedListChar.removeLast();
    doublyLinkedListChar.remove(doublyLinkedListChar.traverse(1));
    doublyLinkedListChar.printForward();
    doublyLinkedListChar.printBackward();
    cout << "--------------------------------------------" << endl;
}

template<class T>
DoublyLinkedList<T>::DoublyLinkedList(T value) {
    struct doublyNode<T>* node = head = tail = allocateHeapNode(value);
}

template<class T>
void DoublyLinkedList<T>::append(T value) {
    struct doublyNode<T>* node = allocateHeapNode(value);
    tail->next = node;
    node->prev = tail;
    tail = node;
}

template<class T>
void DoublyLinkedList<T>::prepend(T value) {
    struct doublyNode<T>* node = allocateHeapNode(value);
    node->next = head;
    head->prev = node;
    head = node;
}

template<class T>
void DoublyLinkedList<T>::insert(struct doublyNode<T>* node, T value) {
    if (node == head) prepend(value);
    else if (node == tail) append(value);
    else {
        struct doublyNode<T>* newNode = allocateHeapNode(value);
        (node->prev)->next = newNode;
        newNode->prev = node->prev;
        newNode->next = node;
        node->prev = newNode;
    }
}

template<class T>
void DoublyLinkedList<T>::removeFirst() {
    struct doublyNode<T>* oldHead = head;
    (oldHead->next)->prev = NULL;
    head = oldHead->next;
    deallocateHeapNode(oldHead);
}

template<class T>
void DoublyLinkedList<T>::removeLast() {
    struct doublyNode<T>* oldTail = tail;
    (oldTail->prev)->next = NULL;
    tail = oldTail->prev;
    deallocateHeapNode(oldTail);
}

template<class T>
void DoublyLinkedList<T>::remove(struct doublyNode<T>* node) {
    (node->prev)->next = node->next;
    (node->next)->prev = node->prev;
    deallocateHeapNode(node);
}

template<class T>
void DoublyLinkedList<T>::printForward() {
    struct doublyNode<T>* start = head;
    cout << "NULL<-->";
    for (int i = 0;i < capacity;i++) {
        cout << start->value << "<-->";
        start = start->next;
    }
    cout << "NULL" << endl;
}

template<class T>
void DoublyLinkedList<T>::printBackward() {
    struct doublyNode<T>* start = tail;
    cout << "NULL<-->";
    for (int i = 0;i < capacity;i++) {
        cout << start->value << "<-->";
        start = start->prev;
    }
    cout << "NULL" << endl;
}

template<class T>
struct doublyNode<T>* DoublyLinkedList<T>::traverse(int index) {
    if (index >= capacity) index = capacity - 1;
    //forward traverse else, backward traverse
    if (index < (capacity - 1) / 2) {
        int i = 0;
        struct doublyNode<T>* start = head;
        while (i != index)
        {
            start = start->next;
            i++;
        }
        return start;
    }
    else {
        int i = capacity - 1;
        struct doublyNode<T>* start = tail;
        while (i != index)
        {
            start = start->prev;
            i--;
        }
        return start;
    }
}

template<class T>
struct doublyNode<T>* DoublyLinkedList<T>::allocateHeapNode(T value) {
    struct doublyNode<T>* node = (struct doublyNode<T>*) malloc(sizeof(struct doublyNode<T>));
    node->value = value;
    node->next = NULL;
    node->prev = NULL;
    capacity++;
    return node;
}

template<class T>
void DoublyLinkedList<T>::deallocateHeapNode(struct doublyNode<T>* node) {
    free(node);
    capacity--;
    node = NULL;
}


