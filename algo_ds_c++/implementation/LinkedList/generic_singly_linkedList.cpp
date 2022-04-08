// generic_singly_linkedList.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <stdio.h>
using namespace std;

template<class T>
struct Node {
    T value;
    struct Node<T> *next;
};

template<class T>
class LinkedList {
private:
    struct Node<T> *head, *tail;
    int capacity=0;

public:
    LinkedList(T value);
    void append(T value);
    void prepend(T value);
    void removeLast();
    void removeFirst();
    void insert(T value,int index);
    void remove(int index);
    void search(T value);
    int size();
    void displayLinkedList();
private:
    struct Node<T>* allocateHeapNode(T value);
    void deallocateHeapNode(struct Node<T>* node);
    struct Node<T>* traverse(int index);
};

int main()
{
    LinkedList<int> linkedListInt(1);
    linkedListInt.append(2);
    linkedListInt.append(3);
    linkedListInt.prepend(0);
    linkedListInt.prepend(-1);
    linkedListInt.removeLast();
    linkedListInt.removeFirst();
    linkedListInt.insert(100,1);
    linkedListInt.insert(100, 0);
    linkedListInt.insert(100, 1000);
    linkedListInt.remove(2);
    linkedListInt.remove(0);
    linkedListInt.remove(1000);
    linkedListInt.displayLinkedList();
    linkedListInt.search(1);
    linkedListInt.search(10000);
    cout << "size : " << linkedListInt.size() << endl;
    cout << "---------------------------------------------" << endl;

    LinkedList<float> linkedListFloat(1.88);
    linkedListFloat.append(2.88);
    linkedListFloat.append(3.88);
    linkedListFloat.prepend(0.00);
    linkedListFloat.prepend(-1.88);
    linkedListFloat.removeLast();
    linkedListFloat.removeFirst();
    linkedListFloat.insert(100.88, 1);
    linkedListFloat.insert(100.88, 0);
    linkedListFloat.insert(100.88, 1000);
    linkedListFloat.remove(2);
    linkedListFloat.remove(0);
    linkedListFloat.remove(1000);
    linkedListFloat.displayLinkedList();
    linkedListFloat.search(1.88);
    linkedListFloat.search(10000.88);
    cout << "size : " << linkedListFloat.size() << endl;
    cout << "---------------------------------------------" << endl;

    LinkedList<char> linkedListChar('A');
    linkedListChar.append('B');
    linkedListChar.append('C');
    linkedListChar.prepend('Z');
    linkedListChar.prepend('Y');
    linkedListChar.removeLast();
    linkedListChar.removeFirst();
    linkedListChar.insert('@', 1);
    linkedListChar.insert('@', 0);
    linkedListChar.insert('@', 1000);
    linkedListChar.remove(2);
    linkedListChar.remove(0);
    linkedListChar.remove(1000);
    linkedListChar.displayLinkedList();
    linkedListChar.search('A');
    linkedListChar.search('$');
    cout << "size : " << linkedListChar.size() << endl;
    cout << "---------------------------------------------" << endl;

}

template<class T>
LinkedList<T>::LinkedList(T value) {
    struct Node<T> *node = head = tail = allocateHeapNode(value);
    node->next = NULL;
}

template<class T>
void LinkedList<T>::append(T value) {
    struct Node<T>* node = allocateHeapNode(value);
    node->next = NULL;
    tail->next = node;
    tail = node;
}

template<class T>
void LinkedList<T>::prepend(T value) {
    struct Node<T>* node = allocateHeapNode(value);
    node->next = head;
    head = node;
}

template<class T>
void LinkedList<T>::removeLast() {
    struct Node<T> *node = traverse(capacity - 2);
    deallocateHeapNode(node->next);
    node->next = NULL;
    tail = node;
}

template<class T>
void LinkedList<T>::removeFirst() {
    struct Node<T>* temp = head;
    head = temp->next;
    deallocateHeapNode(temp);
}

template<class T>
void LinkedList<T>::insert(T value,int index) {
    if (index == 0) prepend(value);
    else if (index >= (capacity - 1)) append(value);
    else {
        struct Node<T>* node = allocateHeapNode(value);
        struct Node<T>* currentNode = traverse(index - 1);
        node->next = currentNode->next;
        currentNode->next = node;
    }
}

template<class T>
void LinkedList<T>::remove(int index) {
    if (index == 0) removeFirst();
    else if (index >= (capacity - 1)) removeLast();
    else {
        struct Node<T>* node = traverse(index - 1);
        struct Node<T>* deletedNode = node->next;
        node->next = deletedNode->next;
        deallocateHeapNode(deletedNode);
    }
}

template<class T>
void LinkedList<T>::search(T value) {
    struct Node<T>* start = head;
    for (int i = 0;i <= capacity - 1;i++) {
        if (start->value == value) {
            cout << "VALUE " << value << " FOUND AT INDEX: " << i << endl;
            return;
        }
        start = start->next;
    }
    cout << "VALUE " << value << " NOT FOUND" << endl;
}

template<class T>
int LinkedList<T>::size() {
    return this->capacity;
}

template<class T>
void LinkedList<T>::displayLinkedList() {
    struct Node<T>* start = head;
    for (int i = 0; i <= capacity-1 ;i++) {
        cout << start->value << "~~>";
        start = start->next;
    }
    cout << "NULL" << endl;
}

template<class T>
struct Node<T>* LinkedList<T>::allocateHeapNode(T value) {
    struct Node<T>* node = (struct Node<T> *)malloc(sizeof(struct Node<T>));
    node->value = value;
    capacity++;
    return node;
}

template<class T>
void LinkedList<T>::deallocateHeapNode(struct Node<T>* node) {
    free(node);
    node = NULL;
    capacity--;
}

template<class T> 
struct Node<T>* LinkedList<T>::traverse(int index) {
    struct Node<T>* start = head;
    if (index >= capacity) index = capacity - 1;
    for (int i = 0; i < index; i++) 
        start = start->next;
    return start;
}

