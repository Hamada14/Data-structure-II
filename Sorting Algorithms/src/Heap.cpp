#include "Heap.h"
#include <assert.h>

template <class T>
Heap<T>::Heap()
{

}

template <class T>
Heap<T>::Heap(std::vector<T> vec):_vec(vec){
    buildHeap();
}

template <class T>
Heap<T>::~Heap()
{
}

template <class T>
int Heap<T>::getLeftChild(int index){
    return (index << 1);
}

template <class T>
int Heap<T>::getRightChild(int index){
    return (index << 1) | 1;
}

template <class T>
int Heap<T>::getParent(int index){
    return (index >> 1);
}


template <class T>
void Heap<T>::buildHeap(){
    for(int i = _vec.size() / 2; i >= 0; i--)
        heapify(i);
}

template <class T>
void Heap<T>::heapify(int index){
    int min_pos = index;
    int left = getLeftChild(index);
    int right = getRightChild(index);
    if(left < (int)_vec.size() && _vec[left] > _vec[min_pos]) min_pos = left;
    if(right < (int)_vec.size() && _vec[right] > _vec[min_pos]) min_pos = right;
    if(min_pos != index){
        std::swap(_vec[index], _vec[min_pos]);
        heapify(min_pos);
    }
}

template <class T>
void Heap<T>::heapifyUp(int index){
    if(index == 0) return;
    int parent = getParent(index);
    if(_vec[index] > _vec[parent]){
        std::swap(_vec[index], _vec[parent]);
        heapifyUp(parent);
    }
}


template <class T>
bool Heap<T>::isEmpty(){
    return _vec.size() == 0;
}


template <class T>
T Heap<T>::getMax(){
    assert(!isEmpty());
    return _vec[0];
}
template <class T>
void Heap<T>::extractMax(){
    assert(!isEmpty());
    _vec[0] = _vec[_vec.size() - 1];
    _vec.pop_back();
    if(_vec.size()) heapify(0);
}

template <class T>
void Heap<T>::insertValue(T a){
    _vec.push_back(a);
    heapifyUp(_vec.size() - 1);
}
