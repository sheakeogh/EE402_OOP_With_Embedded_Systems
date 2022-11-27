#include <iostream>

using namespace std;

double sumDouble(double a, double b) {
    if(a == b) {
        return ((a+b)*2);
    }
    else {
        return (a+b);
    }
}

int main() {
    cout << sumDouble(1,2) << endl;
    cout << sumDouble(3,2) << endl;
    cout << sumDouble(2,2) << endl;

    return 0;
}