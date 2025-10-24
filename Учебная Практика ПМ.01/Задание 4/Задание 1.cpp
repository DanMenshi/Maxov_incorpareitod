#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

void searchSymbol(string& str) {
	int sum = 0;
	char s;

	cout << "Enter symbol: ";
	cin.get(s);

	for (int i = 0; i < str.size(); ++i) {
		if (str[i] == s) {
			++sum;
		}
	}
	if (sum != 0) {
		cout << "The number of occurrences of the '" << s << "' character is : " << sum << "." << endl;
	}
	else {
		cout << "String not have symbol: '" << s << "'" << endl;
	}
}

void searchAndSetSymbol(string& str) {
	char s;
	cout << "Enter search symbol: ";
	cin.get(s);

	char c;
	cout << "Enter set symbol: ";
	cin >> c;

	bool flag = false;

	for (int i = 0; i < str.size(); ++i) {
		if (str[i] == s) { 
			str[i] = c;
			flag = true;
		}
	}
	if (flag) {
		cout << "Symbol set." << endl;
	}
	else { cout << "Symbol not founded." << endl; }
}

void sortSearchInVector(vector<int>& arr) {
	sort(arr.begin(), arr.end());

	int num;
	cout << "Enter number: ";
	cin >> num;

	bool flag = false;
	for (int i = 0; i < arr.size(); ++i) {
		if (arr[i] == num) {
			cout << num <<"[" << i << "], ";
			flag = true;
		}
	}
	if (flag) {
		cout << " - here :)" << endl;
	}
	else {
		cout << "Number is not have in array." << endl;
	}
}

int main() {
	string str = "ajidjwadna doizwoidi owa";
	vector<int> arr{ 3,1,3,4,5,3,6,4,1,1 };
	
	//ВsearchSymbol(str);
	//searchAndSetSymbol(str);
	sortSearchInVector(arr);
}
