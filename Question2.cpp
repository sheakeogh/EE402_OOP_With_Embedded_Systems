#include <iostream>

using namespace std;

string helloName(string a) {
    return ("Hello " + a + "!");
}

int main() {
    cout << helloName("Bob") << endl;
    cout << helloName("Alice") << endl;
    cout << helloName("X") << endl;

    return 0;
}