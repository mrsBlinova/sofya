#include <iostream>
#include <vector>
#include <stdexcept>

class BinaryHeapWithDelete {
private:
    std::vector<int> heap;
    
    // # Вспомогательный метод для восстановления свойств кучи сверху вниз
    void heapifyDown(int index) {
        int size = heap.size();
        
        // # Пока у текущего узла есть хотя бы один потомок
        while (index < size) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int smallest = index;
            
            // # Находим наименьший элемент среди текущего узла и его потомков
            if (leftChild < size && heap[leftChild] < heap[smallest]) {
                smallest = leftChild;
            }
            
            if (rightChild < size && heap[rightChild] < heap[smallest]) {
                smallest = rightChild;
            }
            
            // # Если текущий узел уже наименьший - выходим
            if (smallest == index) {
                break;
            }
            
            // # Меняем местами с наименьшим потомком
            std::swap(heap[index], heap[smallest]);
            // # Переходим к позиции наименьшего потомка
            index = smallest;
        }
    }
    
    // # Вспомогательный метод для восстановления свойств кучи снизу вверх
    void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap[index] < heap[parentIndex]) {
                std::swap(heap[index], heap[parentIndex]);
                index = parentIndex;
            } else {
                break;
            }
        }
    }
    
public:
    // # Конструктор по умолчанию
    BinaryHeapWithDelete() = default;
    
    // # Метод для вставки нового элемента в кучу
    void insert(int value) {
        heap.push_back(value);
        heapifyUp(heap.size() - 1);
    }
    
    // # Метод для удаления корневого элемента (минимального)
    int extractMin() {
        if (heap.empty()) {
            throw std::runtime_error("Heap is empty");
        }
        
        // # Сохраняем минимальный элемент (корень)
        int min = heap[0];
        
        // # Заменяем корень последним элементом
        int lastElement = heap.back();
        heap.pop_back();
        
        // # Если куча не пуста после удаления
        if (!heap.empty()) {
            // # Помещаем последний элемент в корень
            heap[0] = lastElement;
            // # Восстанавливаем свойства кучи сверху вниз
            heapifyDown(0);
        }
        
        return min;
    }
    
    // # Метод для проверки пустоты кучи
    bool isEmpty() const {
        return heap.empty();
    }
    
    // # Метод для вывода кучи
    void printHeap() const {
        std::cout << "Heap: ";
        for (int val : heap) {
            std::cout << val << " ";
        }
        std::cout << std::endl;
    }
};

// # Пример использования
int main() {
    BinaryHeapWithDelete heap;
    heap.insert(10);
    heap.insert(5);
    heap.insert(15);
    heap.insert(3);
    heap.insert(7);
    
    heap.printHeap(); // # Вывод: Heap: 3 5 15 10 7
    
    std::cout << "Extracted min: " << heap.extractMin() << std::endl; // # Вывод: Extracted min: 3
    heap.printHeap(); // # Вывод: Heap: 5 7 15 10
    
    std::cout << "Extracted min: " << heap.extractMin() << std::endl; // # Вывод: Extracted min: 5
    heap.printHeap(); // # Вывод: Heap: 7 10 15
    
    return 0;
}
