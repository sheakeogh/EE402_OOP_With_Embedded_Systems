#include <iostream>

using namespace std;

int countEvens(int arr[], int arrSize) {
    int evens = 0;
    
    for(int i = 0; i < arrSize; i++) {
        if(arr[i] % 2 == 0) {
            evens++;
        }
    }
    
    return evens;
}

int main() {
    int a[] = {2, 1, 2, 3, 4};
    int b[] = {2, 2, 0};
    int c[] = {1, 3, 5};

    cout << countEvens(a, 5) << endl;
    cout << countEvens(b, 3) << endl;
    cout << countEvens(c, 3) << endl; 
    
    return 0;
}