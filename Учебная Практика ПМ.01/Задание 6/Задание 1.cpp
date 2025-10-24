#include <iostream>
#include <string>
using namespace std;

class Xiaomi {
public:
	Xiaomi(int d, int c) : diagonal(d), chastota(c) {}
	Xiaomi() = default;

	string ShowXiaomi() {
		string output = "model: Xiaomi, d: " + to_string(diagonal) + ", c: " + to_string(chastota);
		return output;
	}
private:
	int diagonal;
	int chastota;
};

class Iphone {
public:
	Iphone(int d, int c) : diagonal(d), chastota(c) {}
	Iphone() = default;

	string ShowIphone() {
		string output = "model: Iphone, d: " + to_string(diagonal) + ", c: " + to_string(chastota);
		return output;
	}
private:
	int diagonal;
	int chastota;
};

int main() {
	Iphone iphone13(14, 120);
	Xiaomi pocoM3Pro(15, 90);

	string (Iphone::*showI)() = &Iphone::ShowIphone;
	string (Xiaomi::*showX)() = &Xiaomi::ShowXiaomi;
	
	cout << (iphone13.*showI)() << endl;
	cout << (pocoM3Pro.*showX)() << endl;
}
