#include <iostream>
#include <vector>
using namespace std;

/**
 * Функция для поддержания свойства кучи (heapify)
 * Восстанавливает свойства max-heap для поддерева с корнем в индексе i
 * 
 * @param arr - массив, представляющий кучу
 * @param n - размер кучи
 * @param i - индекс корневого элемента поддерева
 */
void heapify(vector<int>& arr, int n, int i) {
    int largest = i;        // Инициализируем наибольший элемент как корень
    int left = 2 * i + 1;   // Левый дочерний элемент
    int right = 2 * i + 2;  // Правый дочерний элемент
    
    cout << "  heapify: корень=" << i << " (" << arr[i] << "), "
         << "левый=" << (left < n ? to_string(arr[left]) : "нет") << ", "
         << "правый=" << (right < n ? to_string(arr[right]) : "нет") << endl;
    
    // Если левый дочерний элемент больше корня
    if (left < n && arr[left] > arr[largest]) {
        largest = left;
        cout << "    левый больше -> largest = " << largest << endl;
    }
    
    // Если правый дочерний элемент больше текущего наибольшего
    if (right < n && arr[right] > arr[largest]) {
        largest = right;
        cout << "    правый больше -> largest = " << largest << endl;
    }
    
    // Если наибольший элемент не корень
    if (largest != i) {
        cout << "    меняем " << arr[i] << " и " << arr[largest] << endl;
        swap(arr[i], arr[largest]);
        
        // Рекурсивно heapify затронутое поддерево
        cout << "    рекурсивно heapify для " << largest << endl;
        heapify(arr, n, largest);
    } else {
        cout << "    свойство кучи сохранено" << endl;
    }
}

/**
 * Основная функция пирамидальной сортировки (Heap Sort)
 * 
 * @param arr - массив для сортировки
 */
void heapSort(vector<int>& arr) {
    int n = arr.size();
    
    cout << "ПИРАМИДАЛЬНАЯ СОРТИРОВКА (HEAP SORT)" << endl;
    cout << "Начальный массив: ";
    for (int num : arr) cout << num << " ";
    cout << endl << endl;
    
    // Построение max-heap (перегруппировка массива)
    cout << "ФАЗА 1: Построение max-heap" << endl;
    cout << "============================" << endl;
    // Начинаем с последнего нелистового узла и идем до корня
    for (int i = n / 2 - 1; i >= 0; i--) {
        cout << "Обрабатываем узел " << i << " (" << arr[i] << "):" << endl;
        heapify(arr, n, i);
        
        cout << "  Текущее состояние: ";
        for (int num : arr) cout << num << " ";
        cout << endl;
    }
    
    cout << "\nMax-heap построен: ";
    for (int num : arr) cout << num << " ";
    cout << endl << endl;
    
    // Извлечение элементов из кучи один за другим
    cout << "ФАЗА 2: Извлечение элементов из кучи" << endl;
    cout << "===================================" << endl;
    for (int i = n - 1; i > 0; i--) {
        // Перемещаем текущий корень в конец
        cout << "Шаг " << n - i << ": меняем корень " << arr[0] 
             << " с последним элементом " << arr[i] << endl;
        swap(arr[0], arr[i]);
        
        cout << "  После обмена: ";
        for (int num : arr) cout << num << " ";
        cout << endl;
        
        // Вызываем heapify на уменьшенной куче
        cout << "  Восстанавливаем кучу для корня:" << endl;
        heapify(arr, i, 0);
        
        cout << "  Текущее состояние: ";
        for (int j = 0; j <= i; j++) cout << arr[j] << " ";
        cout << "| ";
        for (int j = i + 1; j < n; j++) cout << arr[j] << " ";
        cout << "(отсортированная часть)" << endl << endl;
    }
}

/**
 * Вспомогательная функция для вставки элемента в кучу
 */
void insertIntoHeap(vector<int>& heap, int value) {
    heap.push_back(value);
    int i = heap.size() - 1;
    
    // Просеивание вверх
    while (i > 0 && heap[(i - 1) / 2] < heap[i]) {
        swap(heap[i], heap[(i - 1) / 2]);
        i = (i - 1) / 2;
    }
}

/**
 * Функция для извлечения максимального элемента из кучи
 */
int extractMax(vector<int>& heap) {
    if (heap.empty()) return -1;
    
    int max = heap[0];
    heap[0] = heap.back();
    heap.pop_back();
    
    if (!heap.empty()) {
        heapify(heap, heap.size(), 0);
    }
    
    return max;
}

/**
 * Визуализация кучи в виде дерева
 */
void printHeapTree(const vector<int>& arr, int n, int i, int level) {
    if (i >= n) return;
    
    // Сначала правый узел, затем корень, затем левый узел
    printHeapTree(arr, n, 2 * i + 2, level + 1);
    
    string indent(level * 4, ' ');
    cout << indent << arr[i] << endl;
    
    printHeapTree(arr, n, 2 * i + 1, level + 1);
}

int main() {
    cout << "ПИРАМИДАЛЬНАЯ СОРТИРОВКА (HEAP SORT) НА C++" << endl;
    cout << "==========================================" << endl;
    
    // Пример 1: Основная демонстрация
    vector<int> arr1 = {12, 11, 13, 5, 6, 7};
    cout << "\nПример 1 - Основная демонстрация:" << endl;
    heapSort(arr1);
    cout << "Финальный отсортированный массив: ";
    for (int num : arr1) cout << num << " ";
    cout << endl;
    
    // Пример 2: Демонстрация работы с кучей
    vector<int> arr2 = {4, 10, 3, 5, 1};
    cout << "\nПример 2 - Визуализация кучи:" << endl;
    cout << "Исходный массив: ";
    for (int num : arr2) cout << num << " ";
    cout << endl;
    
    // Построение кучи
    for (int i = arr2.size() / 2 - 1; i >= 0; i--) {
        heapify(arr2, arr2.size(), i);
    }
    
    cout << "\nMax-heap в виде дерева:" << endl;
    printHeapTree(arr2, arr2.size(), 0, 0);
    
    return 0;
}
