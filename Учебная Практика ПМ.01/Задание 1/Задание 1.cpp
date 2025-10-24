#include <iostream>
#include <cmath>

using namespace std;

int main()
{
    int x, y;
    cout << "Enter 1 number: ";
    cin >> x;
    cout << "Enter 2 number: ";
    cin >> y;

    if (x < y) {
        x = y;
    }

    cout << endl;

    cout << "1. " << -sin(x - 5) << endl;
    cout << "2. " << log(pow(x, 2)) << endl;
    cout << "3. " << sin(x - 5) << endl;
    cout << "4. " << pow(2, x) << endl;
    cout << "5. " << pow(cos(x + 5), 2) << endl;
}
