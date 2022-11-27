#include <iostream>
#include <math.h>

using namespace std;

void square(int& a) {
    cout << pow(a, 2) << endl;
}

int main() {
    int a = 8;
    square(a);

    return 0;
}