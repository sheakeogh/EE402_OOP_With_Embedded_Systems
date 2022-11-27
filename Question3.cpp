#include <iostream>

using namespace std;

string monkeyTrouble(bool a, bool b) {
    if(a == b) {
        return "true";
    }
    else {
        return "false";
    }
}

int main() {
    cout << monkeyTrouble(true, true) << endl;
    cout << monkeyTrouble(false, false) << endl;
    cout << monkeyTrouble(true, false) << endl;
    
    return 0;
}