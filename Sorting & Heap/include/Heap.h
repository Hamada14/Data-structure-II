#ifndef HEAP_H
#define HEAP_H

#include <vector>
#include <algorithm>
#include <assert.h>

template <class T>
class Heap
{
    public:
        Heap();
        Heap(std::vector<T> vec);
        T getMax();
        void extractMax();
        void insertValue(T);
        bool isEmpty();
        void heapSort(std::vector<T> &vec);
        ~Heap();

    protected:

    private:
        std::vector<T> _vec;
        void buildHeap(std::vector<T> &vec);
        void heapify(std::vector<T> &vec, int index, int sz);
        void heapifyUp(std::vector<T> &vec, int index);
        int getLeftChild(int);
        int getRightChild(int);
        int getParent(int);
        int _sze;
};

template <class T>
Heap<T>::Heap()
{

}

template <class T>
Heap<T>::Heap(std::vector<T> vec):_vec(vec){
    buildHeap(_vec);
}

template <class T>
void Heap<T>::heapSort(std::vector<T> &vec){
    buildHeap(vec);
    int sz = vec.size();
    for(int i = vec.size() - 1; i >= 0; i--){
        std::swap(vec[i], vec[0]);
        sz--;
        if(i)
            heapify(vec, 0, sz);
    }
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
void Heap<T>::buildHeap(std::vector<T> &vec){
    for(int i = vec.size() / 2; i >= 0; i--)
        heapify(vec, i, vec.size());
}

template <class T>
void Heap<T>::heapify(std::vector<T> &vec, int index, int sz){
    int min_pos = index;
    int left = getLeftChild(index);
    int right = getRightChild(index);
    if(left < sz && vec[left] > vec[min_pos]) min_pos = left;
    if(right < sz && vec[right] > vec[min_pos]) min_pos = right;
    if(min_pos != index){
        std::swap(vec[index], vec[min_pos]);
        heapify(vec, min_pos, sz);
    }
}

template <class T>
void Heap<T>::heapifyUp(std::vector<T> &vec, int index){
    if(index == 0) return;
    int parent = getParent(index);
    if(vec[index] > vec[parent]){
        std::swap(vec[index], vec[parent]);
        heapifyUp(vec, parent);
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
    if(_vec.size()) heapify(_vec, 0, _vec.size());
}

template <class T>
void Heap<T>::insertValue(T a){
    _vec.push_back(a);
    heapifyUp(_vec, _vec.size() - 1);
}

#endif
