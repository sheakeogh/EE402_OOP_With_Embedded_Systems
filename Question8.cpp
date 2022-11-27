#include <iostream>
#include <bits/stdc++.h>

using namespace std;

int main(int argc, char *argv[]) {
    int arr[10] = {0};

    cout << "There are " << argc - 1 << " strings" << endl;

    for(int i = 0; i < argc - 1; i++) {
        string s = argv[i];
        arr[s.length()]++;
    }

    cout << "The number of strings with:" << endl;
    for(int i = 1; i < 10; i++) {
        cout << "Length " << i << " characters: " << arr[i] << endl;
    }

    return 0;
}