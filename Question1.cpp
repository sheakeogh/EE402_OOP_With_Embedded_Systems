#include <iostream>

using namespace std;

int wordsCount(string arr[], int size, int len) {
    int count = 0;
    for(int i = 0; i < size; i++) {
        if(len == arr[i].length()) {
            count++;
        }
    }

    return count;
}

int main() {
    string arr[] = {"a", "bb", "b", "ccc"};
    cout << wordsCount(arr, 4, 1) << endl;
    cout << wordsCount(arr, 4, 3) << endl;
    cout << wordsCount(arr, 4, 4) << endl;

    return 0;
}