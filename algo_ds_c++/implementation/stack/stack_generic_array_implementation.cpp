// stack_generic_array_implementation.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
using namespace std;

template<class T>
class Stack {
private:
    T *stack;
    int capacity;
    int top;

public:
    Stack(int size);
    bool isEmpty();
    bool isFull();
    void push(T new_element);
    T pop();
    T peek();
    void displayStack();
    ~Stack();
};

int main()
{
    Stack<string> strings(5);

    //should display error-> cant pop
    strings.pop();
    strings.peek();

    strings.push("LOL");
    strings.push("BOM");
    strings.push("DOM");
    strings.push("YOU");
    strings.push("FOO");

    //should display error-> cant push
    strings.push("FOO");

    strings.displayStack();

    Stack<int> integers(5);
    integers.push(100);
    integers.push(200);
    integers.push(300);
    integers.displayStack();

    Stack<float> floats(5);
    floats.push(1.8);
    floats.push(2.6);
    floats.push(5.9);
    floats.displayStack();
}

template<class T>
Stack<T>::Stack(int size) {
    this->stack = new T[size];
    this->top = -1;
    this->capacity = size;
}

template<class T>
bool Stack<T>::isEmpty() {
    return this->top == -1;
}

template<class T>
bool Stack<T>::isFull() {
    return this->top == (this->capacity - 1);
}

template<class T>
void Stack<T>::push(T new_element) {
    if (this->isFull()) {
        cout << "STACK IS FULL, CANT PUSH" << endl;
        return;
    }
    this->stack[++top] = new_element;
    return;
}

template<class T>
T Stack<T>::pop() {
    if (this->isEmpty()) {
        cout << "STACK IS EMPTY, CANT POP" << endl;
        return (T) "";
    }
    return this->stack[top--];
}

template<class T>
T Stack<T>::peek() {
    if (this->isEmpty()) {
        cout << "STACK IS EMPTY, NO PEEK" << endl;
        return (T) "";
    }
    T temp = this->pop();
    this->push(temp);
    return temp;
}


template<class T>
void Stack<T>::displayStack() {
    cout << endl;
    for (int i = top;i >= 0;i--) {
        cout << "| " << this->stack[i] << " |" << endl;
        cout << "*******" << endl;
    }
    cout << endl;
}

template<class T>
Stack<T>::~Stack() {
    delete[] this->stack;
    cout << "DESTRUCTOR EXECUTED" << endl;
}
