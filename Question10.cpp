#include <iostream>

using namespace std;

int* reverse(int arr[],int size) {
    int *revArr = new int[size];

    for(int i = 0; i < size; i++) {
        revArr[i] = arr[size - i - 1];
    }

    return revArr;
}

int main() {
    int a[] = {5, 11, 9};
    int b[] = {7, 0, 0, 10};

    int *arrA = reverse(a, 3);
    int * arrB = reverse(b, 4);

    cout << "The first array ..." << endl;  
    for(int i = 0; i < 3; i++) {
        cout << "The " << i + 1 << " element is: " << *(arrA + i) << endl;
    }

    cout << "The second array ..." << endl;  
    for(int i = 0; i < 4; i++) {
        cout << "The " << i + 1 << " element is: " << *(arrB + i) << endl;
    }

    return 0;
}