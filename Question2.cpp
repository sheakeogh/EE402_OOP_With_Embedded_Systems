#include <iostream>

using namespace std;

int blackjack(int a, int b) {
    if(a > 21 || b > 21) {
        if(b > 21) {
            return a;
        }
        else {
            return b;
        }
    }
    else if(a == 21 || b == 21) {
        if(a == 21) {
            return a;
        }
        else {
            return b;
        }
    }
    else {
        int aDiff = 21 - a;
        int bDiff = 21 - b;
        
        if(aDiff < bDiff) {
            return a;
        }
        else {
            return b;
        }
    }
}

int main() {
    cout << blackjack(19, 21) << endl;
    cout << blackjack(21, 19) << endl;
    cout << blackjack(19, 22) << endl;

    return 0;
}