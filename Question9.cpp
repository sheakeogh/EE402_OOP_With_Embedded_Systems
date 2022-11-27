#include <iostream>

using namespace std;

int factorial(int a) {
    int total = 1;
    
    if(a > 1) {
        return(a * factorial(a - 1));
    }
    else {
        return total;
    }
}

int main() {
    cout << factorial(1) << endl;
    cout << factorial(2) << endl;
    cout << factorial(5) << endl;

    return 0;
}