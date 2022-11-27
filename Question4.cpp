#include <iostream>

using namespace std;

class Vehicle {
    protected:
        string make, model, color;
    
    Vehicle(string x, string y, string z) {
        make = x;
        model = y;
        color = z;
    }

    virtual void display() = 0;
};

class Car : public Vehicle {
    private :
        int numSeats = 0;

    public : 
        Car(string x, string y, string z, int n);

        virtual void display();
};

void Car::display() {
    cout << "Your cars specs ..." << endl;
    cout << "Make: " << make << endl;
    cout << "Model: " << model << endl;
    cout << "Colour: " << color << endl;
    cout << "Seats: " << numSeats << endl; 
}

Car::Car(string x, string y, string z, int n) :
    Vehicle(x, y, z) {
        numSeats = n;
    }

int main() {
    Car myCar1("Audi", "A3", "Black", 5);

    myCar1.display();

    return 0;
}