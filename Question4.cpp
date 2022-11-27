#include <iostream>

using namespace std;

class Statistics {
    private:
        double * arr;
        int arrLength;

    public:
        Statistics(double *a, int b) {
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

        double average() {
            int sum = 0;
            double ave;
            for(int i = 0; i < arrLength; i++) {
                sum += arr[i];
            }            
            ave = sum/arrLength;
            return ave;
        }

        int max() {
            int tmp = -1000000000;
            for(int i = 0; i < arrLength; i++) {
                if(arr[i] > tmp) {
                    tmp = arr[i];
                }
            }
            return tmp;
        }

        int min() {
            int tmp = 1000000000;
            for(int i = 0; i < arrLength; i++) {
                if(arr[i] < tmp) {
                    tmp = arr[i];
                }
            }
            return tmp;
        }
};

int main() {
    double anArray[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    Statistics s(anArray, 10);
    s.display();

    return 0;
}