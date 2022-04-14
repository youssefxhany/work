// hash_table.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <stdio.h>
#define TOTAL_CAPACITY 200
#define SIZE 3
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
    void remove(K key);
    struct LinkedListNode<K, V>* get(K key);
    struct LinkedListNode<K, V>* get(int index);
    struct LinkedListNode<K, V>* getHead();
    int getIndex(K key);
    void print();
    int size();
private:
    void removeFirst();
    void removeLast();
    struct LinkedListNode<K, V>* allocateLinkedListNode(K key, V value);
    void deallocateLinkedListNode(struct LinkedListNode<K, V>* node);
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
    void remove(K key);
    V get(K key);
    K* keySet();
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
    hashTable.insert("MILK",88);
    hashTable.insert("Nutella", 2);
    hashTable.insert("Pringles", 22);
    hashTable.insert("cheetos", 120);
    hashTable.insert("doritos", 1);
    hashTable.insert("Pizza", 55);
    hashTable.insert("burger", 20);
    hashTable.insert("Nutelli", 20);
    hashTable.traverse();
    cout << "Nutelli: " << hashTable.get("Nutelli") << endl;
    cout << "ORANGE: " << hashTable.get("ORANGE") << endl;
    cout << "cheetos: " << hashTable.get("cheetos") << endl;
    hashTable.get("oreo");

    cout << "--------------------------------" << endl;
    hashTable.remove("ORANGE");
    hashTable.remove("Lays");
    hashTable.remove("cheetos");
    hashTable.remove("doritos");
    hashTable.remove("Nutella");
    hashTable.traverse();

    cout << "--------------------------------" << endl;
    hashTable.insert("Nutelli", 44);
    hashTable.insert("Nescafe", 30);
    hashTable.remove("frapuccino");
    hashTable.traverse();

    cout << "HASHTABLE KEYS ARE:" << endl;
    hashTable.keySet();

}


//LinkedList methods
template<class K, class V>
LinkedList<K, V>::LinkedList() {
    struct LinkedListNode<K, V>* node = NULL;
}

template<class K, class V>
LinkedList<K, V>::LinkedList(K key, V value) {
    struct LinkedListNode<K, V>* node = head = tail = allocateLinkedListNode(key, value);
}

template<class K, class V>
void LinkedList<K, V>::append(K key, V value) {
    struct LinkedListNode<K, V>* node = allocateLinkedListNode(key, value);
    tail->next = node;
    tail = node;
}

template<class K, class V>
void LinkedList<K, V>::remove(K key) {
    struct LinkedListNode<K, V>* node = get(key);
    if (node == head) {
        removeFirst();
    }
    else if (node == tail) {
        removeLast();
    }
    else {
        int index = getIndex(key);
        if (index == -1) return;
        struct LinkedListNode<K, V>* currentNode = get(index-1);
        currentNode->next = node->next;
        deallocateLinkedListNode(node);
    }
}

template<class K,class V>
struct LinkedListNode<K, V>* LinkedList<K, V>::get(K key) {
    struct LinkedListNode<K, V>* start = head;
    for (int i = 0;i < capacity;i++) {
        if (start->key == key)
            return start;
        start = start->next;
    }
    return NULL;
}

template<class K, class V>
struct LinkedListNode<K, V>* LinkedList<K, V>::get(int index) {
    struct LinkedListNode<K, V>* start = head;
    if (index >= capacity) index = capacity - 1;
    int counter = 0;
    while (counter != index) {
        start = start->next;
        counter++;
    }
    return start;
}

template<class K,class V>
struct LinkedListNode<K, V>* LinkedList<K, V>::getHead() {
    return head;
}

template<class K, class V>
void LinkedList<K, V>::removeFirst() {
    struct LinkedListNode<K, V>* node = head->next;
    deallocateLinkedListNode(head);
    head = node;
}

template<class K, class V>
void LinkedList<K, V>::removeLast() {
    struct LinkedListNode<K, V>* newTail = get(capacity - 2);
    newTail->next = NULL;
    deallocateLinkedListNode(tail);
    tail = newTail;
}

template<class K, class V>
int LinkedList<K, V>::getIndex(K key) {
    struct LinkedListNode<K, V>* start = head;
    for (int i = 0;i < capacity;i++) {
        if (start->key == key)
            return i;
        start = start->next;
    }
    return -1;
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
int LinkedList<K, V>::size() {
    return capacity;
}

template<class K, class V>
struct LinkedListNode<K, V>* LinkedList<K, V>::allocateLinkedListNode(K key, V value) {
    struct LinkedListNode<K, V>* node = new LinkedListNode<K, V>();
    node->key = key;
    node->value = value;
    node->next = NULL;
    capacity++;
    return node;
}

template<class K, class V>
void LinkedList<K, V>::deallocateLinkedListNode(struct LinkedListNode<K, V>* node) {
    delete node;
    node = NULL;
    capacity--;
}

//HashTable methods
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
        else if(extraNodes[index].get(key) != NULL){
            struct LinkedListNode<K, V>* node = extraNodes[index].get(key);
            node->value = value;
        }else {
            extraNodes[index].append(key, value);
        }
    }
}

template<class K,class V>
void HashTable<K, V>::remove(K key) {
    int index = hashFunction(key);
    if ((nodes[index] != NULL) && (nodes[index]->key == key) && (extraNodes[index].size() == 0) ) {
        deallocateHashNode(nodes[index]);
        nodes[index] = NULL;
    }
    else if ((nodes[index] != NULL) && (nodes[index]->key == key) && (extraNodes[index].size() > 0)) {
        struct LinkedListNode<K, V>* linkedListNode = extraNodes[index].getHead();
        nodes[index]->key = linkedListNode->key;
        nodes[index]->value = linkedListNode->value;
        extraNodes[index].remove(linkedListNode->key);
    }
    else if ((nodes[index] != NULL) && (nodes[index]->key != key) && (extraNodes[index].size() > 0) && (extraNodes[index].getIndex(key) != -1)) {
        extraNodes[index].remove(key);
    }
    else {
        cout << "ELEMENT <" << key << "> NOT PRESENT IN HASHTABLE" << endl;
    }
}

template<class K,class V>
V HashTable<K, V>::get(K key) {
    int index = hashFunction(key);
    if (nodes[index] != NULL && nodes[index]->key == key) {
        return nodes[index]->value;
    }
    else if (nodes[index] != NULL && nodes[index]->key != key && extraNodes[index].size() > 0 && extraNodes[index].getIndex(key) != -1) {
        return extraNodes[index].get(key)->value;
    }
    else {
        cout << "ELEMENT <" << key << "> NOT PRESENT IN HASHTABLE" << endl;
        return NULL;
    }
}

template<class K,class V>
K* HashTable<K,V>::keySet() {
    K* keys = new K[TOTAL_CAPACITY];
    int counter = 0;
    for (int i = 0; i < SIZE ;i++) {
        if (nodes[i] != NULL) {
            cout << nodes[i]->key << endl;
            keys[counter++] = nodes[i]->key;
            if (extraNodes[i].size() > 0) {
                for (int j = 0; j < extraNodes[i].size() ;j++) {
                    cout << extraNodes[i].get(j)->key << endl;
                    keys[counter++] = extraNodes[i].get(j)->key;
                }
            }
        }
    }
    return keys;
}

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




