#ifndef SORTING_H
#define SORTING_H

#include <algorithm>
#include <vector>
#include "Heap.h"

template<typename T>
bool defaultComparator(const T a1, const T a2){
    return a1 < a2;
}

class Sorting
{
    public:
        Sorting();
        ~Sorting();
        template<typename T> void selectionSort(T* startPtr, T* endPtr, bool (*comp)(const T, const T) = defaultComparator);
        template<typename T> void bubbleSort(T* startPtr, T* endPtr, bool (*comp)(const T, const T) = defaultComparator);
        template<typename T> void insertionSort(T* startPtr, T* endPtr, bool (*comp)(const T, const T) = defaultComparator);
        template<typename T> void mergeSort(T* startPtr, T* endPtr, bool (*comp)(const T, const T) = defaultComparator);
        template<typename T> void quickSort(T* startPtr, T* endPtr, bool (*comp)(const T, const T) = defaultComparator);
        template<typename T> void heapSort(std::vector<T> &vec);

    protected:

    private:
        template<typename T> void merge(T* startPtr, T* midPtr, T* endPtr, bool (*comp)(const T, const T));
        template<typename T> int partitionize(T* startPtr, T* endPtr, bool (*comp)(const T, const T));
};


template<typename T>
void Sorting::selectionSort(T* startPtr, T* endPtr, bool (*comp)(const T, const T)){
    int sz = endPtr - startPtr;
    for(int i = 0; i < sz; i++){
        int curPtr = i;
        T curVal = startPtr[i];
        for(int j = i + 1; j < sz; j++){
            if(comp(startPtr[j], curVal)){
                curVal = startPtr[j];
                curPtr = j;
            }
        }
        std::swap(startPtr[i], startPtr[curPtr]);
    }
}

template<typename T>
void Sorting::bubbleSort(T* startPtr, T* endPtr, bool (*comp)(const T, const T)){
    int sz = endPtr - startPtr;
    for(int i = 0; i < sz - 1; i++){
        for(int j = 0; j < sz - 1; j++){
            if(comp(startPtr[j + 1], startPtr[j]))
                std::swap(startPtr[j], startPtr[j + 1]);
        }
    }
}

template<typename T>
void Sorting::insertionSort(T* startPtr, T* endPtr, bool (*comp)(const T, const T)){
    int sz = endPtr - startPtr;
    for(int i = 0; i < sz; i++){
        int j = i;
        while(j && comp(startPtr[j], startPtr[j - 1])){
            std::swap(startPtr[j], startPtr[j - 1]);
            j--;
        }
    }
}

template<typename T>
void Sorting::mergeSort(T* startPtr, T* endPtr, bool (*comp)(const T, const T)){
    int sz = endPtr - startPtr;
    if(sz > 1){
        mergeSort(startPtr, startPtr + (sz / 2), comp);
        mergeSort(startPtr + (sz / 2), endPtr, comp);
        merge(startPtr, startPtr + (sz / 2), endPtr, comp);
    }
}

template<typename T>
void Sorting::merge(T* startPtr, T* midPtr, T* endPtr, bool (*comp)(const T, const T)){
    int i = 0, j = 0;
    T extraArr[endPtr - startPtr + 1];
    int extraSz = 0;
    while(startPtr + i < midPtr && midPtr + j < endPtr)
       if(comp(startPtr[i], midPtr[j])) extraArr[extraSz++] = startPtr[i++];
        else extraArr[extraSz++] = midPtr[j++];
    while(startPtr + i < midPtr) extraArr[extraSz++] = startPtr[i++];
    while(midPtr + j < endPtr) extraArr[extraSz++] = midPtr[j++];
    for(i = 0; i < extraSz; i++) startPtr[i] = extraArr[i];
}

template<typename T>
void Sorting::quickSort(T* startPtr, T* endPtr, bool (*comp)(const T, const T)){
    int sz = endPtr - startPtr;
    if(sz > 1){
        int pivot = partitionize(startPtr, endPtr, comp);
        quickSort(startPtr, startPtr + pivot, comp);
        quickSort(startPtr + pivot + 1, endPtr, comp);
    }
}

template<typename T>
int Sorting::partitionize(T* startPtr, T* endPtr, bool (*comp)(const T, const T)){
    T pivot = *startPtr;
    int i = 0;
    for(int j = 0; startPtr + j < endPtr; j++){
        if(comp(startPtr[j], pivot)){
            std::swap(startPtr[++i], startPtr[j]);
        }
    }
    std::swap(startPtr[i], *startPtr);
    return i;
}

template<typename T>
void Sorting::heapSort(std::vector<T> &vec){
    Heap<T> *a = new Heap<T>();
    a->heapSort(vec);
}
#endif // SORTING_H
