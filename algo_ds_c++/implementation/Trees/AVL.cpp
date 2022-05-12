// AVL.cpp : This file contains the 'main' function. Program execution begins and ends there.
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
struct UnbalancedNode {
    struct Node<T>* node;
    string imbalanceType;
};

template<class T>
class AVL {
private:
    struct Node<T>* root;
    int capacity = 0;
public:
    AVL(T value);
    struct Node<T>* getRoot();
    void insert(T value);
    void print();
private:
    void balance(struct Node<T>* node);
    void LLRotate(struct Node<T>* middle, struct Node<T>* right, struct Node<T>* left);
    void LRRotate(struct Node<T>* middle, struct Node<T>* right, struct Node<T>* left);
    void RRRotate(struct Node<T>* middle, struct Node<T>* right, struct Node<T>* left);
    void RLRotate(struct Node<T>* middle, struct Node<T>* right, struct Node<T>* left);
    struct UnbalancedNode<T> checkBalance(struct Node<T>* node);
    int balanceFactor(struct Node<T>* node);
    int height(struct Node<T>* node);
    struct Node<T>* search(T value);
    void printTree(struct Node<T>* root ,int space);
    struct Node<T>* allocateNode(T value);
    void deallocateNode(struct Node<T>* node);
    int max(int num1, int num2);
    void reverseStr(string& str);
};

int main()
{
    AVL<int> avl(40);
    avl.insert(20);
    avl.insert(10);
    avl.insert(25);
    avl.insert(30);
    avl.insert(22);
    avl.insert(50);

    avl.print();

    cout << "--------------------------------------------------------" << endl;

}

template<class T>
AVL<T>::AVL(T value) {
    this->root = this->allocateNode(value);
}

template<class T>
struct Node<T>* AVL<T>::getRoot() {
    return this->root;
}

template<class T>
void AVL<T>::insert(T value) {
    if (this->root == NULL && this->capacity == 0) {
        this->root = allocateNode(value);
        return;
    }

    if (this->search(value) != nullptr) {
        return;
    }

    struct Node<T>* start = root;
    while (true) {
        if (value < start->value) {
            if (start->left == NULL) {
                start->left = allocateNode(value);
                (start->left)->parent = start;
                this->balance(start->left);
                return;
            }
            start = start->left;
        }
        else if (value > start->value) {
            if (start->right == NULL) {
                start->right = allocateNode(value);
                (start->right)->parent = start;
                this->balance(start->right);
                return;
            }
            start = start->right;
        }
    }
}

template<class T>
void AVL<T>::print() {
    cout << endl;
    this->printTree(this->getRoot(), 0);
}

template<class T>
void AVL<T>::balance(struct Node<T>* node) {
    struct UnbalancedNode<T> unbalancedNode = this->checkBalance(node);
    if (unbalancedNode.node != NULL && unbalancedNode.imbalanceType.length() >= 2) {
        if (unbalancedNode.imbalanceType.compare("LL") == 0)
            LLRotate(unbalancedNode.node->left, unbalancedNode.node, (unbalancedNode.node->left)->left);
        else if (unbalancedNode.imbalanceType.compare("LR") == 0)
            LRRotate((unbalancedNode.node->left)->right, unbalancedNode.node, unbalancedNode.node->left);
        else if (unbalancedNode.imbalanceType.compare("RR") == 0)
            RRRotate(unbalancedNode.node->right, unbalancedNode.node->right->right, unbalancedNode.node);
        else if (unbalancedNode.imbalanceType.compare("RL") == 0)
            RLRotate((unbalancedNode.node->right)->left,unbalancedNode.node->right,unbalancedNode.node);
        return;
    }
    else {
        return;
    }
}

template<class T>
void AVL<T>::LLRotate(struct Node<T>* middle, struct Node<T>* right, struct Node<T>* left) {
    //unbalanced node is right node
    if (this->getRoot()->value == right->value) {
        this->root = middle;
        middle->parent = NULL;
    }

    if (right->parent != NULL) {
        if ((right->parent)->left != NULL && ((right->parent)->left)->value == right->value) {
            (right->parent)->left = middle;
            middle->parent = right->parent;
        }
        else if ((right->parent)->right != NULL && ((right->parent)->right)->value == right->value) {
            (right->parent)->right = middle;
            middle->parent = right->parent;
        }
    }

    struct Node<T>* middleRightChild = middle->left;

    middle->right = right;
    right->left = NULL;
    right->parent = middle;

    if (middleRightChild != NULL)
        insert(middleRightChild->value);
}

template<class T>
void AVL<T>::LRRotate(struct Node<T>* middle, struct Node<T>* right, struct Node<T>* left) {
    //unbalanced node is right node
    right->left = middle;
    middle->parent = right;
    middle->left = left;
    left->parent = middle;
    left->right = NULL;

    LLRotate(middle, right, left);
}

template<class T>
void AVL<T>::RRRotate(struct Node<T>* middle, struct Node<T>* right, struct Node<T>* left) {
    //unbalanced node is left node
    if (this->getRoot()->value == left->value) {
        this->root = middle;
        middle->parent = NULL;
    }

    if (left->parent != NULL) {
        if ((left->parent)->right != NULL && ((left->parent)->right)->value == left->value) {
            (left->parent)->right = middle;
            middle->parent = left->parent;
        }
        else if ((left->parent)->left != NULL && ((left->parent)->left)->value == left->value) {
            (left->parent)->left = middle;
            middle->parent = left->parent;
        }
    }

    struct Node<T>* middleLeftChild = middle->left;

    middle->left = left;
    left->parent = middle;
    left->right = NULL;

    if (middleLeftChild != NULL)
        insert(middleLeftChild->value);
}

template<class T>
void AVL<T>::RLRotate(struct Node<T>* middle, struct Node<T>* right, struct Node<T>* left) {
    //unbalanced node is left node
    left->right = middle;
    middle->parent = left;
    middle->right = right;
    right->parent = middle;
    right->left = NULL;

    RRRotate(middle, right, left);
}

template<class T>
struct UnbalancedNode<T> AVL<T>::checkBalance(struct Node<T>* node) {
    struct UnbalancedNode<T> unbalancedNode = { NULL, "" };
    if (node == NULL || node == nullptr)
        return unbalancedNode;

    struct Node<T>* start = node;
    while (start != NULL) {
        if (abs(balanceFactor(start)) > 1 && unbalancedNode.node == NULL) {
            unbalancedNode.node = start;
            break;
        }
        if (start->parent != NULL) {
            if ((start->parent)->left != NULL && ((start->parent)->left)->value == start->value)
                unbalancedNode.imbalanceType = unbalancedNode.imbalanceType + "L";
            else if ((start->parent)->right != NULL && ((start->parent)->right)->value == start->value)
                unbalancedNode.imbalanceType = unbalancedNode.imbalanceType + "R";
        }
        start = start->parent;
    }

    reverseStr(unbalancedNode.imbalanceType);
    if (unbalancedNode.imbalanceType.length() > 2) {
        unbalancedNode.imbalanceType = unbalancedNode.imbalanceType.substr(0,2);
    }

    return unbalancedNode;
}

template<class T>
int AVL<T>::balanceFactor(struct Node<T>* node) {
    return height(node->left) - height(node->right);
}

template<class T>
int AVL<T>::height(struct Node<T>* node) {
    if (node == NULL)
        return -1;
    return max(height(node->left), height(node->right)) + 1;
}

template<class T>
struct Node<T>* AVL<T>::search(T value) {
    if (this->getRoot() == NULL && this->capacity == 0) {
        cout << "tree is empty" << endl;
        return nullptr;
    }

    struct Node<T>* start = this->getRoot();
    while (true) {
        if (value < start->value) {
            if (start->left != NULL) {
                start = start->left;
                continue;
            }
            return nullptr;
        }
        else if (value > start->value) {
            if (start->right != NULL) {
                start = start->right;
                continue;
            }
            return nullptr;
        }
        else {
            return start;
        }
    }
}

template<class T>
void AVL<T>::printTree(struct Node<T>* root, int space) {
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
struct Node<T>* AVL<T>::allocateNode(T value) {
    struct Node<T>* node = new Node<T>();
    node->value = value;
    node->left = NULL;
    node->right = NULL;
    node->parent = NULL;
    this->capacity++;
    return node;
}

template<class T>
void AVL<T>::deallocateNode(struct Node<T>* node) {
    delete node;
    this->capacity--;
    node = NULL;
}

template<class T>
int AVL<T>::max(int num1, int num2) {
    return num1 > num2 ? num1 : num2;
}

template<class T>
void AVL<T>::reverseStr(string& str)
{
    int n = str.length();
    for (int i = 0; i < n / 2; i++)
        swap(str[i], str[n - i - 1]);
}