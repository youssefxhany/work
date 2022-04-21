// treeTraversals.cpp : This file contains the 'main' function. Program execution begins and ends there.
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
    struct Node<T>* parent;
};

template<class T>
class BinarySearchTree{
private:
    struct Node<T>* root;
    int capacity = 0;;
public:
    BinarySearchTree();
    void insert(T value);
    struct Node<T>* lookup(T value);
    void inorder();
    void preorder();
    void postorder();
    void print();
    ~BinarySearchTree();
private:
    T* inorderTraversal(struct Node<T>* node, T* traversal);
    T* preorderTraversal(struct Node<T>* node, T* traversal);
    T* postorderTraversal(struct Node<T>* node, T* traversal);
    void printTree(struct Node<T>* root, int space);
    struct Node<T>* getRoot();
    struct Node<T>* allocateNode(T value);
    void deallocateNode(struct Node<T>* node);
};

int main()
{
    BinarySearchTree<int> binarySearchTree;
    binarySearchTree.insert(15);
    binarySearchTree.insert(15);
    binarySearchTree.insert(6);
    binarySearchTree.insert(4);
    binarySearchTree.insert(1);
    binarySearchTree.insert(5);
    binarySearchTree.insert(7);
    binarySearchTree.insert(23);
    binarySearchTree.insert(20);
    binarySearchTree.insert(17);
    binarySearchTree.insert(16);
    binarySearchTree.insert(18);
    binarySearchTree.insert(71);
    binarySearchTree.insert(50);
    binarySearchTree.insert(49);
    binarySearchTree.insert(51);
    binarySearchTree.insert(80);

    cout << "--------------------------------------------------------" << endl;
    binarySearchTree.print();
    cout << "--------------------------------------------------------" << endl;

    binarySearchTree.lookup(80);
    binarySearchTree.lookup(23);
    binarySearchTree.lookup(15);
    binarySearchTree.lookup(1000);

    cout << "--------------------------------------------------------" << endl;

    binarySearchTree.inorder();
    binarySearchTree.preorder();
    binarySearchTree.postorder();

    cout << "--------------------------------------------------------" << endl;
}

template<class T>
BinarySearchTree<T>::BinarySearchTree() {
    this->root = NULL;
    this->capacity = 0;
}

template<class T>
void BinarySearchTree<T>::insert(T value) {
    struct Node<T>* node = allocateNode(value);
    if (this->root == NULL) {
        this->root = node;
        return;
    }

    if (this->lookup(value) != NULL) {
        cout << "***DUPLICATION NOT ALLOWED***" << endl;
        deallocateNode(node);
        return;
    }

    struct Node<T>* current = root;
    while (true) {
        //go to the left child
        if (current->value > value) {
            if (current->left == NULL) {
                current->left = node;
                node->parent = current;
                return;
            }
            current = current->left;
        }
        //go to the right child
        else if (current->value <= value) {
            if (current->right == NULL) {
                current->right = node;
                node->parent = current;
                return;
            }
            current = current->right;
        }
    }
}

template<class T>
struct Node<T>* BinarySearchTree<T>::lookup(T value) {
    struct Node<T>* current = this->getRoot();
    if (current == NULL && this->capacity == 0) {
        cout << "ROOT IS EMPTY" << endl;
    }

    if (current->value == value) {
        cout << "ELEMENT [" << value << "] EXISTS IN TREE" << endl;
        return this->getRoot();
    }

    while (true) {
        //go to left child 
        if (current->value > value ) {
            if (current->left == NULL) {
                cout << "ELEMENT [" << value << "] DOESN'T EXISTS IN TREE" << endl;
                return NULL;
            }
            current = current->left;
        }
        //go to the right
        else if (current->value < value) {
            if (current->right == NULL) {
                cout << "ELEMENT [" << value << "] DOESN'T EXISTS IN TREE" << endl;
                return NULL;
            }
            current = current->right;
        }
        //value found
        else {
            cout << "ELEMENT [" << value << "] EXISTS IN TREE" << endl;
            return current;
        }
    }
}

template<class T>
void BinarySearchTree<T>::inorder() {
    T* traversal = new T[this->capacity];
    this->inorderTraversal(this->getRoot(), &traversal[0]);
    cout << "INORDER TRAVERSAL: ";
    for (int i = 0;i < this->capacity;i++) {
        cout << traversal[i] << "--->";
    }
    cout << endl;
}

template<class T>
void BinarySearchTree<T>::preorder() {
    T* traversal = new T[this->capacity];
    this->preorderTraversal(this->getRoot(), traversal);
    cout << "PREORDER TRAVERSAL: ";
    for (int i = 0;i < this->capacity;i++) {
        cout << traversal[i] << "--->";
    }
    cout << endl;
}

template<class T>
void BinarySearchTree<T>::postorder() {
    T* traversal = new T[this->capacity];
    this->postorderTraversal(this->getRoot(), traversal);
    cout << "POSTORDER TRAVERSAL: ";
    for (int i = 0;i < this->capacity;i++) {
        cout << traversal[i] << "--->";
    }
    cout << endl;
}

template<class T>
void BinarySearchTree<T>::print() {
    this->printTree(this->getRoot(), 0);
}

template<class T>
BinarySearchTree<T>::~BinarySearchTree() {
    delete this->root;
}

template<class T>
T* BinarySearchTree<T>::inorderTraversal(struct Node<T>* node,T* traversal) {
    int static i = 0;

    if (node == NULL)
        return NULL;

    inorderTraversal(node->left, traversal);
    traversal[i++] = node->value;
    inorderTraversal(node->right, traversal);
}

template<class T>
T* BinarySearchTree<T>::preorderTraversal(struct Node<T>* node, T* traversal) {
    int static i = 0;

    if (node == NULL)
        return NULL;

    traversal[i++] = node->value;
    preorderTraversal(node->left, traversal);
    preorderTraversal(node->right, traversal);
}

template<class T>
T* BinarySearchTree<T>::postorderTraversal(struct Node<T>* node, T* traversal) {
    int static i = 0;

    if (node == NULL)
        return NULL;

    postorderTraversal(node->left, traversal);
    postorderTraversal(node->right, traversal);
    traversal[i++] = node->value;
}

template<class T>
void BinarySearchTree<T>::printTree(struct Node<T>* root, int space) {
    if (root == NULL)
        return;

    space += COUNT;

    printTree(root->right, space);

    for (int i = COUNT;i < space;i++)
        cout << " ";
    cout << root->value << endl;

    printTree(root->left, space);
}

template<class T>
struct Node<T>* BinarySearchTree<T>::getRoot() {
    return this->root;
}

template<class T>
struct Node<T>* BinarySearchTree<T>::allocateNode(T value) {
    struct Node<T>* node = new Node<T>();
    node->value = value;
    node->left = NULL;
    node->right = NULL;
    node->parent = NULL;
    this->capacity++;
    return node;
}

template<class T>
void BinarySearchTree<T>::deallocateNode(struct Node<T>* node) {
    delete node;
    node = NULL;
    this->capacity--;
}


