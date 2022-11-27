#include <iostream>
#include <vector>
#include <algorithm>
#include <numeric>

using namespace std;

// Class for Q2A
class Sensor {
private:
    string name, type, units;
    float value;

public:
    Sensor(string nm, string typ, string unt, float val) : name(nm), type(typ),  units(unt), value(val){
        cout << "A sensor [" << name << "] has just been created." << endl;
    }

    ~Sensor() {
        cout << "A sensor name [" << name << "] value [" << value << "] was destroyed." << endl;

    }

    void display() {
        cout << "The sensor [" << name << "] has type [" << type << "] and value [" << value << " " << units << "]." << endl;
    }

    float getValue() const {
        return value;
    }

    string getName() {
        return name;
    }
};

// Class for Q2B
class Processor {
private:
    vector<Sensor*> myVector;

public:
    Processor() = default;

    void addValue(Sensor *s) {
        myVector.push_back(s);
    }

    void display() {
        cout << "Sorted sensor values are:" << endl;
        sort(myVector.begin(), myVector.end(),  [](Sensor *a, Sensor *b){ return a->getValue() < b->getValue(); });
        for(auto & i : myVector) {
            i->display();
        }
    }

    Sensor *findMin() {
        cout << "Minimum sensor Value is:" << endl;
        sort(myVector.begin(), myVector.end(),  [](Sensor *a, Sensor *b){ return a->getValue() < b->getValue(); });
        return(myVector.at(0));
    }

    Sensor *findMax() {
        cout << "Maximum sensor Value is:" << endl;
        sort(myVector.begin(), myVector.end(),  [](Sensor *a, Sensor *b){ return a->getValue() < b->getValue(); });
        return(myVector.at(myVector.size() - 1));
    }

    float getSum(const string& name) {
        float sum = 0;
        for(auto & i : myVector) {
            if(i->getName() == name) {
                sum += i->getValue();
            }
        }
        return sum;
    }

    friend ostream & operator<<( ostream &stream, Processor *p);
};

// Function for Q2C
ostream & operator<<( ostream &stream, Processor *p) {
    p->display();
    return stream;
}

// Function for Q3A
vector<int> find_primes(int a, int b) {
    vector<int> v((b - a) + 1);
    iota(v.begin(), v.end(), a);

    vector<int> primes;
    bool flag = true;

    for(int i : v) {
        if(i > 1) {
            for(int j = 2; j < (i/(2)); j++) {
                if(i % j == 0) {
                    flag = false;
                }
            }
        }
        else {
            flag = false;
        }
        if(flag) {
            primes.push_back(i);
        }
        flag = true;
    }

    return primes;
}

int main() {
    // Q2A
    Sensor *s = new Sensor("Sensor 1", "temperature", "degrees", 25.4);
    s->display();
    delete(s);

    // Q2B
    Sensor *s1 = new Sensor("Sensor 2", "temperature", "degrees", 30.3);
    Sensor *s2 = new Sensor("Sensor 1", "temperature", "degrees", 20.2);
    Sensor *s3 = new Sensor("Sensor 1", "temperature", "degrees", 40.4);
    Sensor *s4 = new Sensor("Sensor 1", "temperature", "degrees", 10.1);
    Processor *p1 = new Processor();
    p1->addValue(s1);
    p1->addValue(s2);
    p1->addValue(s3);
    p1->addValue(s4);
    p1->display();
    p1->findMin()->display();
    p1->findMax()->display();
    string sensName = "Sensor 1";
    cout << "The sum of [" << sensName << "] values is: " << p1->getSum(sensName) << endl;

    // Q2C
    cout << "Testing:" << endl;
    cout << p1 << endl;

    // Q3D
    vector<int> primes = find_primes(40,150);
    for(int& value: primes){
        cout << " " << value;
    }

    return 0;
}
