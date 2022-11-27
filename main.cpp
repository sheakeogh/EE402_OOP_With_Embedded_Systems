#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

//Functions for Q1A
class Building {
private:
    float floorSize, value;
    string address;

public:
    Building(float flrSz, float val, string add) {
        floorSize = flrSz;
        value = val;
        address = add;
    }

    virtual void display();

    virtual string getType() = 0;
};

void Building::display() {
    cout << "A building of type: " << getType();
    cout << " has a size: " << floorSize;
    cout << " and value " << value << " euro." << endl;
    cout << " and the address " << address << endl;
}

class House : public Building {
private:
    int numRooms;

public:
    House(float flrSz, float val, string add, int numRms) : Building(flrSz, val, add) {
        numRooms = numRms;
    }

    virtual void display();

    virtual string getType();
};

void House::display() {
    Building::display();
    cout << " and " << numRooms << " bedrooms." << endl;
}

string House::getType() {
    return "House";
}

class Office : public Building {
private:
    int numWorkers;

public:
    Office(float flrSz, float val, string add, int numWrks) : Building(flrSz, val, add) {
        numWorkers = numWrks;
    }

    virtual void display();

    virtual string getType();
};

void Office::display() {
    Building::display();
    cout << " and there are " << numWorkers << " workers." << endl;
}

string Office::getType() {
    return "Office";
}

// Function for Q2A
vector<int> find_primes(const vector<int>& v) {
    vector<int> primes;
    bool flag = true;

    for(int i : v) {
        if(i > 1) {
            for(int j = 2; j < (i/(2)); j++) {
                if(i % j == 0) {
                    flag = false;
                }
            }
        }
        else {
            flag = false;
        }
        if(flag) {
            primes.push_back(i);
        }
        flag = true;
    }

    return primes;
}

// Function for Q2B
class Matrix {
private:
    int **matrix;

public:
    Matrix(int **mat) {
        matrix = mat;
    }

    void display() {
        for(int i = 0; i < 2; i++) {
            cout << "|" << matrix[i][0] << " " << matrix[i][1] << "|" << endl;
        }
    }

    int determinant() {
        return (((matrix[0][0]) * (matrix[1][1])) - ((matrix[1][0]) * (matrix[0][1])));
    }
};

void multiply(int **mat1, int **mat2) {
    int row1[] = {(((mat1[0][0])*(mat2[0][0]))+((mat1[0][1])*(mat2[1][0]))), (((mat1[0][0])*(mat2[0][1]))+((mat1[0][1])*(mat2[1][1])))};
    int row2[] = {(((mat1[1][0])*(mat2[0][0]))+((mat1[1][1])*(mat2[1][0]))), (((mat1[1][0])*(mat2[0][1]))+((mat1[1][1])*(mat2[1][1])))};
    int * result[] = {row1, row2};

    for(int i = 0; i < 2; i++) {
        cout << "|" << result[i][0] << " " << result[i][1] << "|" << endl;
    }
}

int main() {
    // Q1A
    House *h1 = new House(1000, 100000, "123 Fake Street", 3);
    Office *o1 = new Office(20000, 500000, "124 Fake Street", 50);

    // Q1B
    House *h2 = new House(750, 7500, "125 Fake Street", 3);
    Office *o2 = new Office(10000, 375000, "126 Fake Street", 50);
    House *h3 = new House(1500, 250000, "127 Fake Street", 3);
    Office *o3 = new Office(5000, 400000, "128 Fake Street", 50);
    vector<Building*> buildings = {h1, o1, h2, o2, h3, o3};
    for_each(buildings.begin(), buildings.end(), [](Building *b){ b->display(); });

    // Q2A
    vector<int> my_ID = {1, 8, 3, 7, 5, 7, 6};
    vector<int> primes = find_primes(my_ID);

    for(int& value: primes){
        cout << " " << value;
    }

    // Q2B
    int row1[] = {1,2};
    int row2[] = {3, 4};
    int * matrix1[] = {row1, row2};
    int row3[] = {5,6};
    int row4[] = {7, 8};
    int * matrix2[] = {row3, row4};
    Matrix *myMatrix1 = new Matrix(matrix1);
    Matrix *myMatrix2 = new Matrix(matrix2);
    myMatrix1->display();
    cout << "Det: " << myMatrix1->determinant() << endl;
    myMatrix2->display();
    cout << "Det: " << myMatrix2->determinant() << endl;
    multiply(matrix1, matrix2);

    return 0;
}
