// Binary_search_tree.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <stdio.h>
#define COUNT 10
using namespace std;

template<class T>
struct Node {
    T value;
    struct Node<T>* left;
    struct Node<T>* right;
};

template<class T>
class BinarySearchTree {
private:
    struct Node<T>* root;
    int numberOfNodes = 0;
public:
    BinarySearchTree();
    void insert(T value);
    struct Node<T>* lookup(T value);
    void print(struct Node<T>* root, int space);
    struct Node<T>* getRoot();
    ~BinarySearchTree() { free(root); };
private:
    struct Node<T>* allocateHeapNode(T value);
    void deallocateNode(struct Node<T>* node);
};

int main()
{
    BinarySearchTree<int> binarySearchTree;
    binarySearchTree.insert(9);
    binarySearchTree.insert(4);
    binarySearchTree.insert(6);
    binarySearchTree.insert(20);
    binarySearchTree.insert(170);
    binarySearchTree.insert(15);
    binarySearchTree.insert(1);
    binarySearchTree.print(binarySearchTree.getRoot(),0);

    binarySearchTree.lookup(170);
    binarySearchTree.lookup(1);
    binarySearchTree.lookup(1000);
}

template<class T>
BinarySearchTree<T>::BinarySearchTree() {
    this->root = NULL;
}

template<class T>
void BinarySearchTree<T>::insert(T value) {
    struct Node<T>* node = allocateHeapNode(value);
    if (this->root == NULL) {
        this->root = node;
        return;
    }
    struct Node<T>* current = this->root;
    while (true) {
        if (value < current->value) {
            if (current->left == NULL) {
                current->left = node;
                return;
            }
            current = current->left;
        }
        else if(value >= current->value){
            if (current->right == NULL) {
                current->right = node;
                return;
            }
            current = current->right;
        }
    }
    return;
}

template<class T>
struct Node<T>* BinarySearchTree<T>::lookup(T value) {
    struct Node<T>* current = root;
    if (root == NULL) {
        cout << "EMPTY TREE" << endl;
        return root;
    }
    cout << endl;
    while (true)
    {
        if (value > current->value) {
            if (current->right == NULL) {
                cout << "VALUE [" << value << "] DOESN'T EXIST IN TREE" << endl;
                return NULL;
            }
            current = current->right;
        }
        else if (value < current->value) {
            if (current->left == NULL) {
                cout << "VALUE [" << value << "] DOESN'T EXIST IN TREE" << endl;
                return NULL;
            }
            current = current->left;
        }
        else {
            cout << "VALUE [" << value << "] EXISTS IN TREE" << endl;
            return current;
        }
    }
}

template<class T>
void BinarySearchTree<T>::print(struct Node<T>* root,int space) {
    if (root == NULL)
        return;
    space += COUNT;
    print(root->right, space);

    cout << endl;
    for (int i = COUNT; i < space; i++)
        cout << " ";
    cout << root->value << "\n";

    print(root->left, space);
}

template<class T>
struct Node<T>* BinarySearchTree<T>::getRoot() {
    return this->root;
}

template<class T>
struct Node<T>* BinarySearchTree<T>::allocateHeapNode(T value) {
    struct Node<T>* node = (struct Node<T>*) malloc(sizeof(struct Node<T>));
    node->value = value;
    node->right = NULL;
    node->left = NULL;
    this->numberOfNodes++;
    return node;
}

template<class T>
void BinarySearchTree<T>::deallocateNode(struct Node<T>* node) {
    free(node);
    node = NULL;
    this->numberOfNodes--;
}

