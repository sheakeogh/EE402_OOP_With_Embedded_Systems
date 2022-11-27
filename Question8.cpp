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

        double average() {
            int sum = accumulate(arr.begin(), arr.end(), 0);    
            double ave = sum/arr.size();
            return ave;
        }

        int max() {
            vector<double> tmpArr = arr;
            sort(tmpArr.begin(), tmpArr.end(), greater<double>());
            return tmpArr[0];
        }

        int min() {
            vector<double> tmpArr = arr;
            sort(tmpArr.begin(), tmpArr.end());
            return tmpArr[0];
        }
};

int main() {
    vector<double> aVector = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    Statistics s(aVector);
    s.display();
    cout << "Average = " << s.average() << endl;
    cout << "Max = " << s.max() << endl;
    cout << "Min = " << s.min() << endl;

    return 0;
}