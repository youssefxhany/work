// stack_using_linkedList.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <stdio.h>
using namespace std;

template<class T>
struct Node {
    T value;
    struct Node<T>* next;
};

template<class T>
class LinkedList {
private:
    struct Node<T>* head, * tail;
    int capacity = 0;
public:
    LinkedList(T value);
    void append(T value);
    T removeLast();
    void reverseLinkedList();
    int size();
   void  print();
private:
    struct Node<T>* allocateLinkedListNode(T value);
    void deallocateLinkedListNode(struct Node<T>* node);
    struct Node<T>* traverse(int index);
};

template<class T>
class Stack {
private:
    LinkedList<T> stack;
public:
    Stack(T value);
    void push(T value);
    T pop();
    int size();
    void print();
};

int main()
{
    LinkedList<string> lol("joe");
    Stack<string> stack("JOE");
    stack.push("HOE");
    stack.push("LOL");
    stack.push("DDT");
    stack.print();

    cout << "-------------------------------" << endl;
    cout << "ELEMENT [" << stack.pop() << "] was poped" << endl;
    stack.print();

    cout << "-------------------------------" << endl;
    cout << "ELEMENT [" << stack.pop() << "] was poped" << endl;
    stack.print();

    cout << "-------------------------------" << endl;
    cout << "ELEMENT [" << stack.pop() << "] was poped" << endl;
    stack.print();

}

template<class T>
LinkedList<T>::LinkedList(T value) {
    struct Node<T>* node = head = tail = allocateLinkedListNode(value);
}

template<class T>
void LinkedList<T>::append(T value) {
    struct Node<T>* node = allocateLinkedListNode(value);
    tail->next = node;
    tail = node;
}

template<class T>
T LinkedList<T>::removeLast() {
    if (size() <= 0) {
        cout << "STACK EMPTY, CAN'T POP" << endl;
        return "";
    }
    struct Node<T>* current = traverse(capacity == 1 ? capacity : capacity - 2);
    T oldValue = tail->value;
    current->next = NULL;
    deallocateLinkedListNode(tail);
    tail = current;
    return oldValue;
}

template<class T>
void LinkedList<T>::reverseLinkedList() {
    struct Node<T>* first = head;
    struct Node<T>* second = head->next;
    tail = head;
    tail->next = NULL;
    while (second != NULL) {
        struct Node<T>* temp = second->next;
        second->next = first;
        first = second;
        second = temp;
    }
    head = first;
}

template<class T>
int LinkedList<T>::size() {
    return capacity;
}

template<class T>
void LinkedList<T>::print() {
    if (capacity <= 0) {
        cout << "STACK EMPTY" << endl;
        return;
    }
    reverseLinkedList();
    struct Node<T>* start = head;
    for (int i = 0; i < capacity; i++) {
        if (i == 0) {
            cout << "|" << start->value << "| <---TOP" << endl;;
            cout << "*****" << endl;
        }
        else {
            cout << "|" << start->value << "|" << endl;
            cout << "*****" << endl;
        }
        start = start->next;
    }
    reverseLinkedList();
}

template<class T>
struct Node<T>* LinkedList<T>::allocateLinkedListNode(T value) {
    struct Node<T>* node = new Node<T>();
    node->value = value;
    node->next = NULL;
    capacity++;
    return node;
}

template<class T>
void LinkedList<T>::deallocateLinkedListNode(struct Node<T>* node) {
    delete node;
    node = NULL;
    capacity--;
}

template<class T>
struct Node<T>* LinkedList<T>::traverse(int index) {
    struct Node<T>* start = head;
    if (index >= capacity) index = capacity - 1;
    int count = 0;
    while (count != index)
    {
        start = start->next;
        count++;
    }
    return start;
}


template<class T>
Stack<T>::Stack(T value) : stack(value) {
}

template<class T>
void Stack<T>::push(T value) {
    this->stack.append(value);
}

template<class T>
T Stack<T>::pop() {
    return this->stack.removeLast();
}

template<class T>
int Stack<T>::size() {
    return this->stack.size();
}

template<class T>
void Stack<T>::print() {
    this->stack.print();
}
