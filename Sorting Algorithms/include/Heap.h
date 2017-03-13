#ifndef HEAP_H
#define HEAP_H

#include <vector>


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

        virtual ~Heap();

    protected:

    private:
        std::vector<T> _vec;
        void buildHeap();
        void heapify(int index);
        void heapifyUp(int index);
        int getLeftChild(int);
        int getRightChild(int);
        int getParent(int);
};

#endif
