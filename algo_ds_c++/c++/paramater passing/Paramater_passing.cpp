// Paramater_passing.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
using namespace std;

//Formal Parameter : A variableand its type as they appear in the prototype of the function or method.
//Actual Parameter : The variable or expression corresponding to a formal parameter that appears in the function or method call in the calling environment.

//Actual paramaters are not affected by changes made on formal paramaters
void swap_by_value(int num1, int num2);

//Actual paramaters are affected by changes made on formal paramaters
void swap_by_address(int *num1, int *num2);

//Actual paramaters are affected by changes made on formal paramaters
//A reference is just an allias or a nickname for a variable
void swap_by_reference(int& num1, int& num2);

int main()
{
    cout << "------------BY VALUE------------" << endl;
    int a = 10, b = 5;
    cout << "before swapping a = " << a << "  and b = " << b << endl;
    swap_by_value(a, b);
    cout << "after swapping a = " << a << "  and b = " << b << endl;

    cout << "------------BY ADDRESS------------" << endl;
    a = 10; 
    b = 5;
    cout << "before swapping a = " << a << "  and b = " << b << endl;
    swap_by_address(&a, &b);
    cout << "after swapping a = " << a << "  and b = " << b << endl;

    cout << "------------BY REFERENCE------------" << endl;
    a = 10;
    b = 5;
    cout << "before swapping a = " << a << "  and b = " << b << endl;
    swap_by_reference(a, b);
    cout << "after swapping a = " << a << "  and b = " << b << endl;
}

void swap_by_value(int num1, int num2) {
    int temp = num1;
    num1 = num2;
    num2 = temp;
}

void swap_by_address(int* num1, int* num2) {
    int temp = *num1;
    *num1 = *num2;
    *num2 = temp;
}

void swap_by_reference(int& num1, int& num2) {
    int temp = num1;
    num1 = num2;
    num2 = temp;
}


