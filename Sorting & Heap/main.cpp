#include <iostream>
#include "include/Sorting.h"
#include <random>
#include <bits/stdc++.h>
#include <ctime>

using namespace std;


vector<int> vec;
Sorting a;

void generateRandom(int countNum){
    vec.clear();
    random_device rd;
    mt19937 gen(rd());
    uniform_int_distribution<> dis(1, 200000000);
    for (int i = 0; i < countNum; i++) {
            vec.push_back(dis(gen));
    }
}

void testBubble(){
    int *arr = new int[vec.size()];
    for(int i = 0; i < (int)vec.size(); i++) arr[i] = vec[i];
    clock_t begin = clock();
    a.bubbleSort(arr, arr + (int)vec.size());
    clock_t end = clock();
    cout << double(end - begin)/ CLOCKS_PER_SEC;
}

void testSelection(){
    int *arr = new int[vec.size()];
    for(int i = 0; i < (int)vec.size(); i++) arr[i] = vec[i];
    clock_t begin = clock();
    a.selectionSort(arr, arr + (int)vec.size());
    clock_t end = clock();
    cout << double(end - begin)/ CLOCKS_PER_SEC;
}

void testInsertion(){
    int *arr = new int[vec.size()];
    for(int i = 0; i < (int)vec.size(); i++) arr[i] = vec[i];
    clock_t begin = clock();
    a.insertionSort(arr, arr + (int)vec.size());
    clock_t end = clock();
    cout << double(end - begin)/ CLOCKS_PER_SEC;
}

void testMerge(){
    int *arr = new int[vec.size()];
    for(int i = 0; i < (int)vec.size(); i++) arr[i] = vec[i];
    clock_t begin = clock();
    sort(arr, arr + vec.size());
    clock_t end = clock();
    cout << double(end - begin)/ CLOCKS_PER_SEC;
}

void testQuick(){
    int *arr = new int[vec.size()];
    for(int i = 0; i < (int)vec.size(); i++) arr[i] = vec[i];
    clock_t begin = clock();
    a.heapSort(vec);
    clock_t end = clock();
    cout << double(end - begin)/ CLOCKS_PER_SEC;
}

void testHeap(){
    clock_t begin = clock();
    a.heapSort(vec);
    clock_t end = clock();
    cout << double(end - begin)/ CLOCKS_PER_SEC;
}

void test(){
    testMerge();
    cout << "\t";
    testQuick();
    cout << "\t";
}

int main()
{
    //freopen("output.txt", "w", stdout);
    cout << fixed << setprecision(5);
    int j = 0;
    for(int i = 10000000; j < 1; i *= 2, j++){
        cout << i << endl;
        generateRandom(i);
        test();
    }
    return 0;
}
