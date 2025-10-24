#include <iostream>
using namespace std;

class Car {
public:
	Car(string name, int speed) : name(name), speed(speed) {}

	auto operator<=>(const Car& rhs) const {
		if (auto a = speed <=> rhs.speed; a != 0) return a;
		return name <=> rhs.name;
	}
	bool operator==(const Car& rhs) const {
		return speed == rhs.speed;
	}
private:
	string name;
	int speed;
};

class Account {
public:
	Account(string name, int balance, int expenditure, int income) : 
		name(name), 
		balance(balance), 
		expenditure(expenditure), 
		income(income) 
	{}

	Account& operator++() {
		income += 1; return *this;
	}
	Account& operator--() {
		expenditure += 1; return *this;
	}
	Account operator+(int num) {
		if (num >= 0) {
			income += num;
		}
		else {
			expenditure += num * -1;
		}
		return *this;
	}
	friend ostream& operator << (ostream& os, const Account& rhs);

private:
	string name;
	int balance;
	int expenditure;
	int income;
};

ostream& operator << (ostream& os, const Account& rhs) {
	os << "Name: " << rhs.name << ", balance: " << rhs.balance << ", Expenditure: " << rhs.expenditure << ", Income: " << rhs.income << ".";
	return os;
}


int main() {
	
/*
	Car bmw("bmw", 180);
	Car audi("audi", 220);
	Car lada("lada", 120);
	Car ford("ford", 120);
	
	if (bmw < audi) {
		cout << "bmw < audi" << endl;
	}
	if (bmw > lada) {
		cout << "bmw > lada" << endl;
	}
	if (lada == ford) {
		cout << "lada == ford" << endl;
	}
*/

	Account a("Ivan", 50000, 1, 1);
	cout << a << endl;
	++a;
	cout << a << endl;
	--a;
	cout << a << endl;
	a = a + 20;
	cout << a << endl;
	a = a + -10;
	cout << a << endl;

}