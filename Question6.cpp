#include <iostream>

using namespace std;

bool sum28(int arr[], int size) {
    int count = 0;

    for(int i = 0; i < size; i++) {
        if(arr[i] == 2) {
            count += arr[i];
        }
    }

    bool ans = (count == 8) ? true : false;
    return ans;
}

int main() {
    int a[] = {2, 3, 2, 2, 4, 2};
    int b[] = {2, 3, 2, 2, 4, 2, 2};
    int c[] = {1, 2, 3, 4};

    cout << sum28(a, 6) << endl;
    cout << sum28(b, 7) << endl;
    cout << sum28(c, 4) << endl;

    return 0;
}