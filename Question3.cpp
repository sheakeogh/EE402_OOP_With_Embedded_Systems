#include <iostream>

using namespace std;

class Vehicle {
    public:
        string make, model, color;
    
    Vehicle(string x, string y, string z) {
        make = x;
        model = y;
        color = z;
    }

    virtual void display();
};

void Vehicle::display() {
    cout << "Your cars specs ..." << endl;
    cout << "Make: " << make << endl;
    cout << "Model: " << model << endl;
    cout << "Colour: " << color << endl;
}

int main() {
    Vehicle myCar1("Audi", "A3", "Black");

    myCar1.display();

    return 0;
}