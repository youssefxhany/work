// Stack_array_implementation.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
using namespace std;

//Stack is a linear data structure that follows a particular order in which the operations are performed.
//The order may be LIFO(Last In First Out) or FILO(First In Last Out).
//Mainly the following three basic operations are performed in the stack:
//Push: Adds an item in the stack. If the stack is full, then it is said to be an Overflow condition.
//Pop: Removes an item from the stack.The items are popped in the reversed order in which they are pushed.If the stack is empty, then it is said to be an Underflow condition.
//Peek or Top : Returns the top element of the stack.
//isEmpty : Returns true if the stack is empty, else false.
//isFull : Returns true if the stack is full, else false.

class Stack {
private:
    int capacity;
    int top;
    int *stack;

public:
    Stack(int size);
    bool isEmpty();
    bool isFull();
    void push(int new_element);
    int pop();
    int peek();
    void displayStack();
    ~Stack();
};

int main()
{
    Stack stack(5);
    //should not pop stack is empty
    stack.pop();
    stack.push(1);
    stack.push(2);
    stack.push(3);
    stack.push(4);
    stack.push(5);
    //should not be pushed stack is full
    stack.push(6);
    stack.displayStack();
    
    stack.pop();
    stack.pop();
    stack.displayStack();

    stack.pop();
    cout << "PEEK IS: " << stack.peek() << endl;
    stack.displayStack();
}

Stack::Stack(int size) {
    stack = new int[size];
    top = -1;
    capacity = size;
}

bool Stack::isEmpty() {
    return top == -1;
}

bool Stack::isFull() {
    return top == (capacity - 1);
}

void Stack::push(int new_element) {
    if (isFull()) {
        cout << "CANT PUSH, STACK IS FULL" << endl;
        return;
    }
    stack[++top] = new_element;
    return;
}

int Stack::pop() {
    if (isEmpty()) {
        cout << "CANT POP, STACK IS EMPTY" << endl;
        return -1;
    }
    return stack[top--];
}

int Stack::peek() {
    if (isEmpty()) {
        cout << "STACK IS EMPTY" << endl;
        return -1;
    }
    int temp = pop();
    push(temp);
    return temp;
}

void Stack::displayStack() {
    cout << endl;
    for (int i = top;i >= 0;i--) {
        cout << "| " << stack[i] << " |" << endl;
        cout << "*****" << endl;
    }
    cout << endl;
}

Stack::~Stack() {
    delete [] stack;
    cout << "DESTRUCTOR EXECUTED" << endl;
}


