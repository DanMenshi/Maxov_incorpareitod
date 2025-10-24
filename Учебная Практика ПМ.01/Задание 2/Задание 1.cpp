#include <iostream>
#include <cmath>

using namespace std;

void foo() {
    int x, valueUser;
    bool firstTime = true;

    while (true) {

        cout << "Enter  number: ";
        cin >> x;


        cout << endl;


        cout << "F(" << x << ") = " << -sin(x - 5) << endl;
        cout << "F(" << x << ") = " << log(pow(x, 2)) << endl;
        cout << "F(" << x << ") = " << sin(x - 5) << endl;


        if (firstTime) {
            cout << "F(" << x << ") = " << pow(2, x) << endl;
            cout << "F(" << x << ") = " << pow(cos(x + 5), 2) << endl;
            firstTime = false;
        }

        cout << "Do you want to continue the calculations ? \n(0 -yes, 1 -no) " << endl;
        cin >> valueUser;
        if (valueUser == 1) { break; }
    }
}

int main()
{
    foo();
}

