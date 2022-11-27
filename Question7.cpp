#include <iostream>
#include <bits/stdc++.h>

using namespace std;

class Statistics {
    private:
        vector<double> arr;

    public:
        Statistics(vector<double> a) {
            arr = a;
        }
        
        void display() {
            cout << "Vector ["; 
            for(int i = 0; i < arr.size(); i++) {
                cout << arr[i] << " ";
            }
            cout << "]" << endl;
        }

        /*double average() {
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
        */
};

int main() {
    vector<double> aVector = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    Statistics s(aVector);
    s.display();

    return 0;
}