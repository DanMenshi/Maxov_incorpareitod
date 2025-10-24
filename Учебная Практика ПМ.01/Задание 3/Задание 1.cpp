#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <ctime>

using namespace std;

class AccountingOfProduct {
private:

	int productsInOnePackages;
	int packages;
	int fullProducts;
	string name;
	char timeDelivery[11];
	int sellProducts;
	int trashProducts;

	void GetTimeDelivery() {
		cout << "delivery date: " << timeDelivery << endl;
	}

public:

	AccountingOfProduct(int productsInOnePackages, int packages, string name) : productsInOnePackages(productsInOnePackages), packages(packages), name(name) {
		sellProducts = 0;
		trashProducts = 0;
		fullProducts = productsInOnePackages * packages;
		time_t now = time(nullptr);
		tm* local = localtime(&now);
		strftime(timeDelivery, sizeof(timeDelivery), "%d.%m.%Y", local);

		cout << "created object " << name << endl;
	}
	~AccountingOfProduct() { cout << "deleted object " << name << endl; }

	void SellProductsPlece() {
		if (1 > fullProducts) { cout << "there are too few product!" << endl; }
		else {
			--fullProducts;
			++sellProducts;
			cout << "successfully sold " << 1 << " product." << endl;
		}
	}
	void SellProductsPlece(int amo) {
		if (amo > fullProducts) { cout << "there are too few products!" << endl; }
		else {
			fullProducts -= amo;
			sellProducts += amo;
			cout << "successfully sold " << amo << " products." << endl;
		}
	}
	void SellProductsPackages(int packagesUser) {
		int SellProductsFull = packagesUser * productsInOnePackages;

		if (packagesUser > packages) { cout << "there are too few products!" << endl; }
		else if (fullProducts - SellProductsFull < productsInOnePackages && fullProducts != SellProductsFull) { cout << "the remaining product is less than one package." << endl; }
		else {
			fullProducts -= SellProductsFull;
			packages = fullProducts / productsInOnePackages;
			sellProducts += SellProductsFull;
			cout << "successfully sold " << packagesUser << " packages product." << endl;
		}
	}

	void TrashProducts() {
		if (1 > fullProducts) { cout << "there are too few product!" << endl; }
		else {
			--fullProducts;
			++trashProducts;
			cout << "successfully debited " << 1 << " product." << endl;
		}
	}
	void TrashProducts(int amo) {
		if (amo > fullProducts) { cout << "there are too few products!" << endl; }
		else {
			fullProducts -= amo;
			trashProducts += amo;
			cout << "successfully debited " << amo << " products." << endl;
		}
	}

	void RemainsPrint() {
		cout << endl;
		cout << name << ": " << fullProducts << " in " << fullProducts/productsInOnePackages << " whole packages.\n" << endl;
	}
	void Print() {
		cout << "\n" << "product '" << name << "'." << endl;
		GetTimeDelivery();
		cout << "total product: " << fullProducts << ".\n" << "total whole packages: " << fullProducts / productsInOnePackages << ".\n" << "total product sold: " << sellProducts << ".\n" << "total product recycled: " << trashProducts << ".\n" << endl;
	}


};

struct App {

	void start(AccountingOfProduct product) {
		while (true) {
			int choice = -1;
			int valueUser = -1;

			cout << "\n1. sell\n2. sell packages\n3. dispose\n4. remains print\n5. print\n6. exit\n" << endl;
			cin >> choice;
			switch (choice)
			{
				case 1: {
					cout << "how much to sell products: ";
					cin >> valueUser;
					if (valueUser < 1) { cout << "error." << endl; }
					else if (valueUser == 1) { product.SellProductsPlece(); }
					else { product.SellProductsPlece(valueUser); }
					break;
				}
				case 2: {
					cout << "how much to sell packegs with products: ";
					cin >> valueUser;
					if (valueUser < 1) { cout << "error." << endl; }
					else { product.SellProductsPackages(valueUser); }
					break;
				}
				case 3: {
					cout << "how much to dispose products: ";
					cin >> valueUser;
					if (valueUser < 1) { cout << "error." << endl; }
					else if (valueUser == 1) { product.TrashProducts(); }
					else { product.TrashProducts(valueUser); }
					break;
				}
				case 4: { product.RemainsPrint(); break; }
				case 5: { product.Print(); break; }
				case 6: { break; }
			}
			if (choice == 6) { break; }
		}
	}
};



int main() {
	AccountingOfProduct pencils(200, 40, "pencil");
	App x;


	x.start(pencils);
}
