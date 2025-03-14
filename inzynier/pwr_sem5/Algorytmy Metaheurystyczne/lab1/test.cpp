#include <iostream>
#include <stdlib.h>
#include "Node.hpp"
#include "Optimize.hpp"

using namespace std;

int main(int argc, char const *argv[])
{
    Node* testarray = (Node*)malloc(sizeof(Node) * 8);
    testarray[0].x = 1;
    testarray[0].y = 1;
    testarray[1].x = 1;
    testarray[1].y = 2;
    testarray[2].x = 1;
    testarray[2].y = 3;
    testarray[3].x = 2;
    testarray[3].y = 1;
    testarray[4].x = 2;
    testarray[4].y = 3;
    testarray[5].x = 3;
    testarray[5].y = 1;
    testarray[6].x = 3;
    testarray[6].y = 2;
    testarray[7].x = 3;
    testarray[7].y = 3;
    for (int i = 0; i < 8; i++)
    {
        cout << "(" << testarray[i].x << "; " << testarray[i].y << "), ";
    }
    cout << endl;
    LocalSearch(testarray, 8);
    for (int i = 0; i < 8; i++)
    {
        cout << "(" << testarray[i].x << "; " << testarray[i].y << "), ";
    }
    cout << endl;
    return 0;
}
