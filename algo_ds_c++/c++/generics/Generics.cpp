// Generics.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

//A template is a simple and yet very powerful tool in C++. The simple idea is to pass data type as a parameter
//so that we don’t need to write the same code for different data types. 
//For example, a software company may need sort() for different data types. Rather than writing and maintaining the multiple codes,
//we can write one sort() and pass data type as a parameter. 
//C++ adds two new keywords to support templates: ‘template’ and ‘typename’.
//The second keyword can always be replaced by keyword ‘class’.

#include <iostream>
using namespace std;

template<class T>
class Arithmetic {
private:
    T var1;
    T var2;

public:
    Arithmetic(T var1, T var2);
    T sum();
    T sub();
};

int main()
{
    Arithmetic<int> integers(5, 3);
    cout << "Sum: " << integers.sum() << endl;
    cout << "Sub: " << integers.sub() << endl;
    cout << "-----------------------------" << endl;
    Arithmetic<float> floats(5.3, 3.8);
    cout << "Sum: " << floats.sum() << endl;
    cout << "Sub: " << floats.sub() << endl;
    cout << "-----------------------------" << endl;
    Arithmetic<string> strings("Youssef", " Hany");
    cout << "Sum: " << strings.sum() << endl;
}

template<class T>
Arithmetic<T>::Arithmetic(T var1, T var2) {
    this->var1 = var1;
    this->var2 = var2;
}

template<class T>
T Arithmetic<T>::sum() {
    return this->var1 + this->var2;
}

template<class T>
T Arithmetic<T>::sub() {
    return this->var1 - this->var2;
}

