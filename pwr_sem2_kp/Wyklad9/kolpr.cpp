#include <iostream>
#include <queue>

using namespace std;

int main(int argc, char ** argv)
{
	// Kolejka priorytetowa (elementy od najwiekszego do najmniejszego)
	priority_queue<int> queue;

	queue.push(100);
	queue.push(300);
	queue.push(50);
	queue.push(150);

	cout << "Prechodzenie po elementach" << endl;
	while (!queue.empty())
	{
		// Wypisywanie najwiekszego elementu
		cout << queue.top() << endl;
		// Usuwanie najwiekszego elementu
		queue.pop();
	}

	system("pause");

	return 0;
}