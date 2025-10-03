#include <iostream>
#include <vector>
#include <stdexcept>

class BinaryHeap {
private:
    std::vector<int> heap;
    
    // # Вспомогательный метод для восстановления свойств кучи снизу вверх
    void heapifyUp(int index) {
        // # Пока не дошли до корня
        while (index > 0) {
            // # Находим индекс родительского элемента
            int parentIndex = (index - 1) / 2;
            
            // # Если текущий элемент меньше родительского (min-heap)
            if (heap[index] < heap[parentIndex]) {
                // # Меняем местами с родителем
                std::swap(heap[index], heap[parentIndex]);
                // # Переходим к родительской позиции
                index = parentIndex;
            } else {
                // # Свойства кучи восстановлены, выходим
                break;
            }
        }
    }
    
public:
    // # Конструктор по умолчанию
    BinaryHeap() = default;
    
    // # Метод для вставки нового элемента в кучу
    void insert(int value) {
        // # 1. Добавляем новый элемент в конец вектора
        heap.push_back(value);
        
        // # 2. Восстанавливаем свойства кучи, поднимая новый элемент на нужную позицию
        heapifyUp(heap.size() - 1);
    }
    
    // # Метод для получения корня (минимального элемента)
    int getMin() const {
        if (heap.empty()) {
            throw std::runtime_error("Heap is empty");
        }
        return heap[0];
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
    BinaryHeap heap;
    heap.insert(10);
    heap.insert(5);
    heap.insert(15);
    heap.insert(3);
    heap.insert(7);
    
    heap.printHeap(); // # Вывод: Heap: 3 5 15 10 7
    std::cout << "Min element: " << heap.getMin() << std::endl; // # Вывод: Min element: 3
    
    return 0;
}
