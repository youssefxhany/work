// treeTraversals.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <stdio.h>
#define COUNT 10
using namespace std;


template<class T>
struct LinkedListNode {
    T value;
    struct LinkedListNode<T>* next;
    struct LinkedListNode<T>* prev;
    LinkedListNode(T value);
    LinkedListNode();
};

template<class T>
class LinkedList {
private:
    struct LinkedListNode<T>* head, *tail;
    int capacity = 0;
public:
    LinkedList();
    LinkedList(T value);
    void append(T value);
    struct LinkedListNode<T>* removeLast();
    struct LinkedListNode<T>* getTail();
    int getSize();
    void printForward();
    void printBackward();
private:
    struct LinkedListNode<T>* allocateLinkedListNode(T value);
    void deallocateLinkedListNode(struct LinkedListNode<T>* node);
};

template<class T>
class Stack {
private:
    LinkedList<T> stack;
public:
    Stack();
    Stack(T value);
    void push(T value);
    T pop();
    T top();
    bool isEmpty();
    void print();
};

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
    void inorderRecursive();
    void preorderRecursive();
    void postorderRecusrsive();
    void print();
    ~BinarySearchTree();
private:
    T* inorderRecursiveTraversal(struct Node<T>* node, T* traversal);
    T* preorderRecursiveTraversal(struct Node<T>* node, T* traversal);
    T* postorderRecursiveTraversal(struct Node<T>* node, T* traversal);
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
    cout << "RUCURSIVE TRAVERSALS:" << endl;
    cout << "----------------------" << endl;
    binarySearchTree.inorderRecursive();
    binarySearchTree.preorderRecursive();
    binarySearchTree.postorderRecusrsive();

    cout << "--------------------------------------------------------" << endl;

}

//stack methods

template<class T>
Stack<T>::Stack() : stack() {

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
    if (this->isEmpty()) {
        cout << "CANT'T POP FROM EMPTY STACK" << endl;
        return NULL;
    }
    return this->stack.removeLast()->value;
}

template<class T>
T Stack<T>::top() {
    if (this->isEmpty()) {
        cout << "NO TOP FOR EMPTY STACK" << endl;
        return NULL;
    }
    return this->stack.getTail()->value;
}

template<class T>
bool Stack<T>::isEmpty() {
    if (this->stack.getSize() == 0)
        return true;
    return false;
}

template<class T>
void Stack<T>::print() {
    this->stack.printForward();
}

//linked list node constructors

template<class T>
LinkedListNode<T>::LinkedListNode() {
}

template<class T>
LinkedListNode<T>::LinkedListNode(T value) {
    this->value = value;
    this->next = nullptr;
    this->prev = nullptr;
}

//linked list methods

template<class T>
LinkedList<T>::LinkedList() {
    this->head = this->tail = nullptr;
}

template<class T>
LinkedList<T>::LinkedList(T value) {
    struct LinkedListNode<T>* node = head = tail = allocateLinkedListNode(value);
}

template<class T>
void LinkedList<T>::append(T value) {
    struct LinkedListNode<T>* node = allocateLinkedListNode(value);
    if (head == nullptr && tail == nullptr) {
        head = tail = node;
    }
    tail->next = node;
    node->prev = tail;
    tail = node;
}

template<class T>
LinkedListNode<T>* LinkedList<T>::removeLast() {
    struct LinkedListNode<T>* removedNode = tail;
    struct LinkedListNode<T>* temp = new LinkedListNode<T>(removedNode->value);
    (removedNode->prev)->next = NULL;
    tail = removedNode->prev;
    deallocateLinkedListNode(removedNode);
    return temp;
}

template<class T>
LinkedListNode<T>* LinkedList<T>::getTail() {
    return this->tail;
}

template<class T>
int LinkedList<T>::getSize() {
    return this->capacity;
}

template<class T>
void LinkedList<T>::printForward() {
    struct LinkedListNode<T>* start = head;
    for (int i = 0;i < capacity;i++) {
        cout << "[" << start->value << "]<-->";
        start = start->next;
    }
    cout << endl;
}

template<class T>
void LinkedList<T>::printBackward() {
    struct LinkedListNode<T>* node = tail;
    for (int i = 0;i < capacity;i++) {
        cout  << "[" << node->value << "]<-->";
        node = node->prev;
    }
    cout << endl;
}

template<class T>
struct LinkedListNode<T>* LinkedList<T>::allocateLinkedListNode(T value) {
    struct LinkedListNode<T>* node = new LinkedListNode<T>(value);
    this->capacity++;
    return node;
}

template<class T>
void LinkedList<T>::deallocateLinkedListNode(struct LinkedListNode<T>* node) {
    delete node;
    this->capacity--;
    node = NULL;
}

//binary search tree methods

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
void BinarySearchTree<T>::inorderRecursive() {
    T* traversal = new T[this->capacity];
    this->inorderRecursiveTraversal(this->getRoot(), &traversal[0]);
    cout << "INORDER TRAVERSAL: ";
    for (int i = 0;i < this->capacity;i++) {
        cout << traversal[i] << "--->";
    }
    cout << endl;
}

template<class T>
void BinarySearchTree<T>::preorderRecursive() {
    T* traversal = new T[this->capacity];
    this->preorderRecursiveTraversal(this->getRoot(), traversal);
    cout << "PREORDER TRAVERSAL: ";
    for (int i = 0;i < this->capacity;i++) {
        cout << traversal[i] << "--->";
    }
    cout << endl;
}

template<class T>
void BinarySearchTree<T>::postorderRecusrsive() {
    T* traversal = new T[this->capacity];
    this->postorderRecursiveTraversal(this->getRoot(), traversal);
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
T* BinarySearchTree<T>::inorderRecursiveTraversal(struct Node<T>* node,T* traversal) {
    int static i = 0;

    if (node == NULL)
        return NULL;

    inorderRecursiveTraversal(node->left, traversal);
    traversal[i++] = node->value;
    inorderRecursiveTraversal(node->right, traversal);
}

template<class T>
T* BinarySearchTree<T>::preorderRecursiveTraversal(struct Node<T>* node, T* traversal) {
    int static i = 0;

    if (node == NULL)
        return NULL;

    traversal[i++] = node->value;
    preorderRecursiveTraversal(node->left, traversal);
    preorderRecursiveTraversal(node->right, traversal);
}

template<class T>
T* BinarySearchTree<T>::postorderRecursiveTraversal(struct Node<T>* node, T* traversal) {
    int static i = 0;

    if (node == NULL)
        return NULL;

    postorderRecursiveTraversal(node->left, traversal);
    postorderRecursiveTraversal(node->right, traversal);
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


