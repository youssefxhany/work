// hash_table.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <stdio.h>
#define TOTAL_CAPACITY 200
#define SIZE 50
using namespace std;

template<class K, class V>
struct HashNode {
    K key;
    V value;
};

template<class K, class V>
class HashTable {
private:
    struct HashNode<K, V>** nodes;
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
HashTable<K, V>::HashTable(K key, V value) {
    nodes = (HashNode<K, V> **) malloc(SIZE * sizeof(HashNode<K, V>));
    for (int i = 0;i < SIZE;i++) {
        nodes[i] = NULL;
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
        cout << "COLLISION HAPPENED" << endl;
    }
}

//still needs to be changed to handle collisions
template<class K, class V>
void HashTable<K, V>::traverse() {
    for (int i = 0;i < SIZE;i++) {
        if (nodes[i] != NULL) {
            cout << "[" << nodes[i]->key << "," << nodes[i]->value << "] " << endl;
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




