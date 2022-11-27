#include <iostream>
#include <bits/stdc++.h>

using namespace std;

string mirrorEnds(string str) {
    string tmp = "";
    for(int i = 0; i < str.length(); i++) {
        if(str[i] == str[str.length() - 1 - i]) {
            tmp.push_back(str[i]);
        }
        else {
            break;
        }
    }

    return tmp;
}

int main() {
    cout << mirrorEnds("abXYZba") << endl;
    cout << mirrorEnds("abca") << endl;
    cout << mirrorEnds("navan") << endl;

    return 0;
}