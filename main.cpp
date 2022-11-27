#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

namespace DCU {
    class Student {
    private:
        string name, programme;
        int id;

    public:
        Student(string nm, int idNum, string prgrm) {
            name = nm;
            id = idNum;
            programme = prgrm;
        }

        virtual void display();

        virtual string getType() = 0;
    };

    void Student::display() {
        cout << "A " << getType() << " with name: " << name << endl;
        cout << "-> id number: " << id << " and programme: " << programme << endl;
    }

    class taughtStudent : public Student {
    private:
        vector<string> modules;
    public:
        taughtStudent(string nm, int idNum, string prgrm, vector<string> vec) : Student(nm, idNum, prgrm) {
            modules = vec;
        }

        virtual void display();

        virtual string getType();
    };

    string taughtStudent::getType() {
        return "Taught Student";
    }

    void taughtStudent::display() {
        Student::display();
        cout << "-> and has the following " << modules.size() << " modules: [";
        for(int i = 0; i < modules.size(); i++) {
            cout << modules.at(i) << " ";
        }
        cout << "]" << endl;
    }

    class researchStudent : public Student {
    private:
        string office;
    public:
        researchStudent(string nm, int idNum, string prgrm, string off) : Student(nm, idNum, prgrm) {
            office = off;
        }

        virtual void display();

        virtual string getType();
    };

    string researchStudent::getType() {
        return "Research Student";
    }

    void researchStudent::display() {
        Student::display();
        cout << "-> and has office: " << office << endl;
    }
}

int count3s(int from, int to) {
    int count = 0;
    int low, high;
    string tmp;
    if(from > to) {
        high = from;
        low = to;
    }
    else {
        low = from;
        high = to;
    }

    for(int i = low; i < high + 1; i++) {
        tmp += to_string(i);
    }

    for(int i = 0; i < tmp.size(); i++) {
        if(tmp[i] == '3') {
            count++;
        }
    }

    return count;
}


int main() {
    vector<string> modules = {"EE456", "EE567", "EE402", "EE513"};
    DCU::taughtStudent *t1 = new DCU::taughtStudent("James", 22012345, "MECE", modules);
    DCU::researchStudent *r1 = new DCU::researchStudent("Jennifer", 22012346, "EEPD1", "S123");
    vector<DCU::Student*> students;
    students.push_back(t1);
    students.push_back(r1);
    for_each(students.begin(), students.end(), [](DCU::Student *s) {s->display();});

    // Q2
    cout << count3s(0, 10) << endl;
    cout << count3s(3, 100) << endl;
    cout << count3s(0, -1) << endl;
    cout << count3s(100, 1000) << endl;
    cout << count3s(-100, 100) << endl;

    return 0;
}
