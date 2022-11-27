#include <iostream>

using namespace std;

class Vehicle {
    protected:
        string make, model, color;
        int reg = 0;
    
    Vehicle(string x, string y, string z) {
        cout << "Vehicle being created ..." << endl; 
        make = x;
        model = y;
        color = z;
        reg = rand() % 99999 + 10000;
    }

    virtual ~Vehicle();
    virtual void display() = 0;
};

class Car : public Vehicle {
    private :
        int numSeats = 0;

    public : 
        Car(string x, string y, string z, int n);

        virtual ~Car();
        virtual void display();
};

void Car::display() {
    cout << "Your cars specs ..." << endl;
    cout << "Make: " << make << endl;
    cout << "Model: " << model << endl;
    cout << "Colour: " << color << endl;
    cout << "Seats: " << numSeats << endl;
    cout << "Registration: " << reg << endl; 
}

Car::Car(string x, string y, string z, int n) :
    Vehicle(x, y, z) {
        cout << "Car being created ..." << endl; 
        numSeats = n;
    }

Vehicle::~Vehicle() {
    cout << "Vehicle being destroyed ..." << endl;
}

Car::~Car() {
    cout << "Car being destroyed ..." << endl;
}

int main() {
    Car myCar1("Audi", "A3", "Black", 5);
    Car myCar2("Volkswagen", "Golf", "Blue", 5);

    myCar1.display();
    myCar2.display();

    return 0;
}