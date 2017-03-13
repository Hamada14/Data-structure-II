#include <iostream>
#include "Sorting.h"
#include "src/Heap.cpp"

using namespace std;

bool comp(string a, string b){
    return a > b;
}

int main()
{
    int arr[] = {1,2,10,-5,-2,0,-3432,343,121};
    Sorting s;
    s.HeapSort(arr, arr + 9);
    for(int i = 0; i < 9; i++) cout << arr[i] << " ";
    return 0;
}
