#include <iostream>

using namespace std;

class Statistics {
    private:
        double arr[50];
        int arrLength;

    public:
        Statistics(double a[], int b) {
            arrLength = b;
            for(int i = 0; i < arrLength; i++) {
                arr[i] = a[i];
            }
        }
        void display() {
            cout << "Array ["; 
            for(int i = 0; i < arrLength; i++) {
                cout << arr[i] << " ";
            }
            cout << "]" << endl;
        }
};

int main() {
    double anArray[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    Statistics s(anArray, 10);
    s.display();

    return 0;
}