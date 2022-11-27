#include <iostream>
#include <math.h>

using namespace std;

void square(int &a) {
    cout << pow(a, 2) << endl;
}


int main() {
    int a = 2;
    int b = 5;
    int c = 25;
    
    square(a);
    square(b);
    square(c);

    return 0;
}