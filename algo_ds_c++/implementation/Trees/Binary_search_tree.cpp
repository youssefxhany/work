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
    struct Node<T>* parent;
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
    void remove(T value);
    void inorderTraversal();
    void print();
    ~BinarySearchTree() { free(root); };
private:
    struct Node<T>* lookupImplementation(T value,bool deletionMode);
    struct Node<T>* getRoot();
    void inorder(struct Node<T>* node);
    struct Node<T>* inorderSuccessor(struct Node<T>* node);
    struct Node<T>* getMin(struct Node<T>* node);
    struct Node<T>* getMax(struct Node<T>* node);
    struct Node<T>* compareToParent(struct Node<T>* node);
    void printTree(struct Node<T>* root, int space);
    struct Node<T>* allocateHeapNode(T value);
    void deallocateNode(struct Node<T>* node);
};

int main()
{
    BinarySearchTree<int> binarySearchTree;
    binarySearchTree.insert(15);
    binarySearchTree.insert(6);
    binarySearchTree.insert(7);
    binarySearchTree.insert(4);
    binarySearchTree.insert(1);
    binarySearchTree.insert(5);
    binarySearchTree.insert(23);
    binarySearchTree.insert(71);
    binarySearchTree.insert(80);
    binarySearchTree.insert(50);
    binarySearchTree.insert(49);
    binarySearchTree.insert(51);
    binarySearchTree.insert(20);
    binarySearchTree.insert(17);
    binarySearchTree.insert(18);
    binarySearchTree.insert(16);

    binarySearchTree.print();

    cout << "------------------------------------------" ;

    binarySearchTree.inorderTraversal();

    cout << "------------------------------------------" << endl;

    binarySearchTree.remove(5);
    binarySearchTree.remove(7);
    binarySearchTree.remove(20);
    binarySearchTree.remove(1);
    binarySearchTree.remove(23);

    binarySearchTree.print();

    cout << "------------------------------------------" << endl;

    binarySearchTree.lookup(51);
    binarySearchTree.lookup(49);
    binarySearchTree.lookup(1);
    binarySearchTree.lookup(100);

}

template<class T>
BinarySearchTree<T>::BinarySearchTree() {
    this->root = NULL;
}

template<class T>
void BinarySearchTree<T>::insert(T value) {
    struct Node<T>* node = allocateHeapNode(value);
    if (this->root == NULL) {
        node->parent = NULL;
        this->root = node;
        return;
    }
    struct Node<T>* current = this->root;
    while (true) {
        if (value < current->value) {
            if (current->left == NULL) {
                node->parent = current;
                current->left = node;
                return;
            }
            current = current->left;
        }
        else if(value >= current->value){
            if (current->right == NULL) {
                node->parent = current;
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
    return this->lookupImplementation(value,false);
}

template<class T>
void BinarySearchTree<T>::remove(T value) {
    struct Node<T>* node = lookupImplementation(value,true);
    if (node == NULL) {
        cout << "NODE DON'T EXISTS IN TREE" << endl;
        return;
    }

    //node is a leaf node case
    if (!node->left && !node->right ) {
        //node is left child of its parent
        if ((node->parent)->left && ((node->parent)->left)->value == value) {
            (node->parent)->left = NULL;
        }
        //node is right child of its parent
        else if((node->parent)->right && ((node->parent)->right)->value == value ){
            (node->parent)->right = NULL;
        }
        deallocateNode(node);
    }
    //node has exactly one child
    else if (!node->left || !node->right) {
        //if node's child is a right child
        if (node->right) {
            //node is left child of its parent
            if ((node->parent)->left && ((node->parent)->left)->value == value) {
                (node->parent)->left = node->right;
            }
            //node is right child of its parent
            else if ((node->parent)->right && ((node->parent)->right)->value == value) {
                (node->parent)->right = node->right;
            }
        }
        //if node's child is a left child
        else {
            //node is left child of its parent
            if ((node->parent)->left && ((node->parent)->left)->value == value) {
                (node->parent)->left = node->left;
            }
            //node is right child of its parent
            else if ((node->parent)->right && ((node->parent)->right)->value == value) {
                (node->parent)->right = node->left;
            }
        }
        deallocateNode(node);
    }
    //node has exactly two children
    else if(node->right && node->left) {
        struct Node<T>* successor = this->inorderSuccessor(node);
        node->value = successor->value;
        //successor is left child of its parent
        if ((successor->parent)->left && ((successor->parent)->left)->value == successor->value) {
            (successor->parent)->left = NULL;
        }
        //successor is right child of its parent
        else if ((successor->parent)->right && ((successor->parent)->right)->value == successor->value) {
            (successor->parent)->right = NULL;
        }
        deallocateNode(successor);
    }
}

template<class T>
void BinarySearchTree<T>::inorderTraversal() {
    cout << endl;
    cout << "INORDER TRAVERSAL: ";
    this->inorder(this->getRoot());
    cout << endl;
}

template<class T>
void BinarySearchTree<T>::print() {
    this->printTree(this->getRoot(), 0);
}

template<class T>
struct Node<T>* BinarySearchTree<T>::lookupImplementation(T value, bool deletionMode) {
    struct Node<T>* current = root;
    if (root == NULL) {
        cout << "EMPTY TREE" << endl;
        return root;
    }
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
            if (deletionMode == false)
                cout << "VALUE [" << value << "] EXISTS IN TREE" << endl;
            else
                cout << "VALUE [" << value << "] REMOVED FROM TREE" << endl;
            return current;
        }
    }
}

template<class T>
struct Node<T>* BinarySearchTree<T>::getRoot() {
    return this->root;
}

template<class T>
void BinarySearchTree<T>::inorder(struct Node<T>* node) {
    if (node == NULL)
        return;
    inorder(node->left);
    cout << node->value << "-->";
    inorder(node->right);
}

template<class T>
struct Node<T>* BinarySearchTree<T>::inorderSuccessor(struct Node<T>* node) {
    //if node has a right subtree we get leftmost/minimum element of the subtree
    if (node->right) {
        return this->getMin(node->right);
    }
    //if node is right most node in tree it has node successors
    else if(node == this->getMax(this->getRoot())){
        cout << "NODE IS RIGHTMOST NODE AND IT HAS NO SUCCESSORS" << endl;
        return NULL;
    }
    //if node has no right subtree
    else if (!node->right) {
        return compareToParent(node);
    }
}

template<class T>
struct Node<T>* BinarySearchTree<T>::getMin(struct Node<T>* node) {
    while (true)
    {
        if (!node->left)
            return node;
            node = node->left;
    }
}

template<class T>
struct Node<T>* BinarySearchTree<T>::getMax(struct Node<T>* node) {
    while (true) {
        if (!node->right)
            return node;
        node = node->right;
    }
}

template<class T>
struct Node<T>* BinarySearchTree<T>::compareToParent(struct Node<T>* node) {
    //we reached the root
    if (node == NULL)
        return NULL;
    if ((node->parent)->left && ((node->parent)->left)->value == node->value)
        return node->parent;
    return compareToParent(node->parent);
}

template<class T>
void BinarySearchTree<T>::printTree(struct Node<T>* root, int space) {
    if (root == NULL)
        return;
    space += COUNT;
    printTree(root->right, space);

    cout << endl;
    for (int i = COUNT; i < space; i++)
        cout << " ";
    cout << root->value << "\n";

    printTree(root->left, space);
}

template<class T>
struct Node<T>* BinarySearchTree<T>::allocateHeapNode(T value) {
    struct Node<T>* node = (struct Node<T>*) malloc(sizeof(struct Node<T>));
    node->value = value;
    node->right = NULL;
    node->left = NULL;
    node->parent = NULL;
    this->numberOfNodes++;
    return node;
}

template<class T>
void BinarySearchTree<T>::deallocateNode(struct Node<T>* node) {
    free(node);
    node = NULL;
    this->numberOfNodes--;
}

