// Procedural_program_of_a_rectangle.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

//Procedural Programming can be defined as a programming model which is derived from structured programming,
//based upon the concept of calling procedure.Procedures, also known as routines, subroutines or functions,
//simply consist of a series of computational steps to be carried out.During a program’s execution,
//any given procedure might be called at any point, including by other procedures or itself.
//In Procedural Programming data and behavior are separated,
//and any procedure can have access of any entity so code beacome unpredictible.

#include <iostream>
using namespace std;

//members of a struct are public, so no need to implement getters/accessors
struct Rectangle {
    int length;
    int width;
};

//analogous to a constructor
void initialize_rectangle(struct Rectangle* r, int length, int width);

//facilitators-> functions that do processing
int area(struct Rectangle r);
int perimetre(struct Rectangle r);

//analogous to a setters/mutators
void change_Length(struct Rectangle *r, int new_length);
void change_width(struct Rectangle* r, int new_width);

int main()
{
    struct Rectangle rectangle;
    initialize_rectangle(&rectangle, 10, 10);
    cout << "Area is: " << area(rectangle) << endl;
    cout << "Perimetre is: " << perimetre(rectangle) << endl;
    cout << "----------------------------------" << endl;
    change_Length(&rectangle, 20);
    change_width(&rectangle, 20);
    cout << "Area is: " << area(rectangle) << endl;
    cout << "Perimetre is: " << perimetre(rectangle) << endl;

}

void initialize_rectangle(struct Rectangle* r, int length, int width) {
    r->length = length;
    r->width = width;
}

int area(struct Rectangle r) {
    return r.length * r.width;
}

int perimetre(struct Rectangle r) {
    return 2 * (r.length + r.width);
}

void change_Length(struct Rectangle* r, int new_length) {
    r->length = new_length;
}

void change_width(struct Rectangle* r, int new_width) {
    r->width = new_width;
}




