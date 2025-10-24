#include <iostream>
#include <string>
using namespace std;

class Account {
public:
    Account(string n, int b) : name(n), balance(b), expenditure(0), income(0) {
        inCassa += balance;
    }
    void _expenditure(int sum) {
        inCassa -= sum;
        expenditure += sum;
    }
    void _income(int sum) {
        inCassa += sum;
        income += sum;
    }
    int getInCassa() {
        return inCassa;
    }
private:
    string name;
    int balance;
    int expenditure;
    int income;
    static int inCassa;
};

int Account::inCassa = 0;

int main() {
    Account a("a", 20);
    Account b("b", 30);
    cout << b.getInCassa() << endl;
}
