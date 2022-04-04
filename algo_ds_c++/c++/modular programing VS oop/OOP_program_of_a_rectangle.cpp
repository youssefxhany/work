// OOP_program_of_a_rectangle.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

//Object oriented programming can be defined as a programming model which is based upon the concept of objects.
//Objects contain data in the form of attributes and code in the form of methods.
//In object oriented programming, computer programs are designed using the concept of objects that interact with real world.
//Object oriented programming languages are various but the most popular ones are class-based, meaning that objects are instances of classes,
//which also determine their types.
//In OOP attributes and behaviors are grouped in a single entity, thus we can control access to those entities.

#include <iostream>
using namespace std;

//By default all members of a class are private, and all members of a struct are public
//In c++ we can also define methods inside a struct
class Rectangle {
private:
    int length;
    int width;

public:
    //As we can see, constructor overloading is used-> same method but with different paramaters
    Rectangle();
    Rectangle(int length, int width);

    //Facilitator-> methods used to do processing
    int area();
    int perimetre();

    //Getters or accessors
    int getLength();
    int getWidth();

    //Setters or mutators
    void setLength(int new_length);
    void setWidth(int new_width);

    //A distructor-> used to release heap memory + executed when we get out of the scope where the object was declared
    ~Rectangle();
};

int main()
{
    Rectangle rectangle;
    cout << "Area is: " << rectangle.area() << endl;
    cout << "Perimetre is: " << rectangle.perimetre() << endl;
    cout << "---------------------------------------" << endl;
    Rectangle bigger_rectangle(20, 30);
    cout << "Area is: " << bigger_rectangle.area() << endl;
    cout << "Perimetre is: " << bigger_rectangle.perimetre() << endl;
    cout << "---------------------------------------" << endl;
    cout << "---REDUCE WIDTH AND LENGTH OF BIGGER RECTANGLE BY 10---" << endl;
    bigger_rectangle.setLength(bigger_rectangle.getLength() - 10);
    bigger_rectangle.setWidth(bigger_rectangle.getWidth() - 10);
    cout << "Area is: " << bigger_rectangle.area() << endl;
    cout << "Perimetre is: " << bigger_rectangle.perimetre() << endl;
    cout << "---------------------------------------" << endl;
    cout << "WHEN WE GET OUT MAIN DISTRUCTOR IS EXECUTED FOR EACH OBJECT" << endl;
}

//We use scope resolution, which means method <Rectangle> belongs to class <Rectangle>
Rectangle::Rectangle() {
    this->length = 1;
    this->width = 1;
}

Rectangle::Rectangle(int length, int width) {
    this->length = length;
    this->width = width;
}

int Rectangle::area() {
    return this->length * this->width;
}

int Rectangle::perimetre() {
    return 2 * (this->length + this->width);
}

int Rectangle::getLength() {
    return this->length;
}

int Rectangle::getWidth() {
    return this->width;
}

void Rectangle::setLength(int new_length) {
    this->length = new_length;
}

void Rectangle::setWidth(int new_width) {
    this->width = new_width;
}

//As no heap is used in this example, we will only examine when the distructor would be executed
Rectangle::~Rectangle() {
    cout << "Distructor executed" << endl;
}


