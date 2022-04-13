// hash_table.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <stdio.h>
#define TOTAL_CAPACITY 200
#define SIZE 2
using namespace std;

template<class K, class V>
struct LinkedListNode {
    K key;
    V value;
    struct LinkedListNode<K, V>* next;
};

template<class K, class V>
class LinkedList {
private:
    struct LinkedListNode<K, V>* head, * tail;
    int capacity = 0;
public:
    LinkedList();
    LinkedList(K key, V value);
    void append(K key, V value);
    void print();
    int size();
private:
    struct LinkedListNode<K, V>* allocateHeapNode(K key, V value);
    void deallocateHeapNode(struct LinkedListNode<K, V>* node);
};

template<class K, class V>
struct HashNode {
    K key;
    V value;
};

template<class K, class V>
class HashTable {
private:
    struct HashNode<K, V>** nodes;
    struct LinkedList<K, V>* extraNodes;
    int capacity = 0;
public:
    HashTable(K key, V value);
    void insert(K key, V value);
    void traverse();
private:
    struct HashNode<K, V>* allocateHashNode(K key, V value);
    void deallocateHashNode(struct HashNode<K, V>* node);
    int hashFunction(K key);
    bool isFull();
};

int main()
{
    HashTable<string, int> hashTable("Apple", 10);
    hashTable.insert("BANANA", 20);
    hashTable.insert("ORANGE", 7);
    hashTable.insert("MILK", 2);
    hashTable.insert("Apple", 1000);
    hashTable.insert("Lays", 3);
    hashTable.traverse();
}

template<class K, class V>
LinkedList<K, V>::LinkedList() {
    struct LinkedListNode<K, V>* node = NULL;
}

template<class K, class V>
LinkedList<K, V>::LinkedList(K key, V value) {
    struct LinkedListNode<K, V>* node = head = tail = allocateHeapNode(key, value);
}

template<class K, class V>
void LinkedList<K, V>::append(K key, V value) {
    struct LinkedListNode<K, V>* node = allocateHeapNode(key, value);
    tail->next = node;
    tail = node;
}

template<class K, class V>
void LinkedList<K, V>::print() {
    struct LinkedListNode<K, V>* start = head;
    cout << "-->";
    for (int i = 0;i < capacity;i++) {
        cout << "[" << start->key << "," << start->value << "]" << "--->";
        start = start->next;
    }
}

template<class K, class V>
struct LinkedListNode<K, V>* LinkedList<K, V>::allocateHeapNode(K key, V value) {
    struct LinkedListNode<K, V>* node = new LinkedListNode<K, V>();
    node->key = key;
    node->value = value;
    node->next = NULL;
    capacity++;
    return node;
}

template<class K, class V>
void LinkedList<K, V>::deallocateHeapNode(struct LinkedListNode<K, V>* node) {
    delete node;
    node = NULL;
    capacity--;
}

template<class K, class V>
int LinkedList<K, V>::size() {
    return capacity;
}

template<class K, class V>
HashTable<K, V>::HashTable(K key, V value) {
    nodes = (HashNode<K, V> **) malloc(SIZE * sizeof(HashNode<K, V>));
    extraNodes = new LinkedList<K,V>[SIZE]();
    for (int i = 0;i < SIZE;i++) {
        nodes[i] = NULL;
        extraNodes[i] = LinkedList<K,V>();
    }
    nodes[hashFunction(key)] = allocateHashNode(key, value);
}

template<class K,class V>
void HashTable<K, V>::insert(K key, V value) {
    if (isFull()) return;
    int index = hashFunction(key);
    if (nodes[index] == NULL) {
        nodes[index] = allocateHashNode(key, value);
    }
    else if (nodes[index] != NULL && (nodes[index]->key).compare(key) == 0) {
        nodes[index]->value = value;
    }
    else {
        if (extraNodes[index].size() == 0) {
            extraNodes[index] = LinkedList<K, V>(key, value);
        }
        else {
            extraNodes[index].append(key, value);
        }
    }
}

//still needs to be changed to handle collisions
template<class K, class V>
void HashTable<K, V>::traverse() {
    for (int i = 0;i < SIZE;i++) {
        if (nodes[i] != NULL) {
            cout << "[" << nodes[i]->key << "," << nodes[i]->value << "] ";
            if (extraNodes[i].size() > 0)
                extraNodes[i].print();
            cout << endl;
        }
    }
    cout << endl;
}

template<class K, class V>
struct  HashNode<K, V>* HashTable<K, V>::allocateHashNode(K key, V value){
    struct HashNode<K, V>* node = new HashNode<K, V>();
    node->key = key;
    node->value = value;
    capacity++;
    return node;
}

template<class K, class V>
void HashTable<K, V>::deallocateHashNode(struct HashNode<K, V>* node) {
    delete node;
    capacity--;
    node = NULL;
}

template<class K, class V>
int HashTable<K, V>::hashFunction(K key) {
    int hashValue = 0;
    for (int i = 0;i < key.size();i++) {
        hashValue = hashValue + key.at(i) * i;
    }
    return hashValue % SIZE;
}

template<class K, class V>
bool HashTable<K, V>::isFull(){
    if (capacity == TOTAL_CAPACITY) {
        cout << "TOTAL CAPACITY REACHED, NO INSERTION POSSIBLE" << endl;
        return true;
    }
    return false;
}




