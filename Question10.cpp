#include <iostream>
#include <bits/stdc++.h>
#include <vector>
#include <algorithm>

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
            vector<double> tmp = sortVec(1);
            return tmp[0];
        }

        int min() {
            vector<double> tmp = sortVec(0);
            return tmp[0];
        }

        vector<double> sortVec(int a) {
            vector<double> v = arr;
            if(a == 1) {
                sort(v.begin(), v.end(), greater<int>());
            }
            else {
                sort(v.begin(), v.end());
            }

            return v;
        }       

        double median() {
            vector<double> tmp = sortVec(0);
            if(tmp.size() % 2 == 0)  {
                return (((tmp[(tmp.size()/2) - 1])+(tmp[(tmp.size()/2)]))/(2));
            }           
            else {
                return (tmp[((tmp.size())/(2))]);
            }
        }

        void randomize() {
            random_shuffle(arr.begin(), arr.end());
        }
};

int main() {
    vector<double> aVector = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    Statistics s(aVector);
    s.display();
    cout << "Average = " << s.average() << endl;
    cout << "Max = " << s.max() << endl;
    cout << "Min = " << s.min() << endl;
    cout << "Median = " << s.median() << endl;
    s.randomize();
    s.display();
    
    return 0;
}