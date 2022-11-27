#include <iostream>

using namespace std;

int count7(int n) {
    if(n == 0) {
        return 0;
    }
    if(n % 10 == 7) {
        return 1 + count7(n / 10);
    }

    return count7(n / 10);
}

int main() {
    cout << count7(717) << endl;
    cout << count7(7) << endl;
    cout << count7(123) << endl;

    return 0;
}