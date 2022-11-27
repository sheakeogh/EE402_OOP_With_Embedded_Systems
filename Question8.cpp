#include <iostream>

using namespace std;

bool scoresIncreasing(int arr[], int size) {
    int tmp = 0; 

    for(int i = 1; i < size; i++) {
        if(arr[i] >= arr[i - 1]) {
            continue;
        }
        else {
            return false;
        }
    }
    return true;
}

int main() {
    int a[] = {1, 3, 4, 5, 6};
    int b[] = {1, 3, 2};
    int c[] = {1, 1, 4};

    cout << scoresIncreasing(a, 5) << endl;
    cout << scoresIncreasing(b, 3) << endl;
    cout << scoresIncreasing(c, 3) << endl;

    return 0;
}