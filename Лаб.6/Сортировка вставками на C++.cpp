#include <iostream>
#include <vector>
using namespace std;

/**
 * Функция сортировки вставками (Insertion Sort)
 * Алгоритм: строит отсортированную часть списка, постепенно вставляя каждый 
 * следующий элемент на правильную позицию в отсортированной части
 * 
 * @param arr - вектор для сортировки (передается по ссылке)
 */
void insertionSort(vector<int>& arr) {
    int n = arr.size();
    
    cout << "Процесс сортировки вставками:" << endl;
    cout << "Исходный массив: ";
    for (int num : arr) cout << num << " ";
    cout << endl << endl;
    
    // Начинаем со второго элемента (первый считается отсортированным)
    for (int i = 1; i < n; i++) {
        // Текущий элемент, который нужно вставить в отсортированную часть
        int key = arr[i];
        // Индекс последнего элемента в отсортированной части
        int j = i - 1;
        
        cout << "Шаг " << i << ": Вставляем " << key << endl;
        cout << "  Отсортированная часть: ";
        for (int k = 0; k <= i - 1; k++) cout << arr[k] << " ";
        cout << " | Неотсортированная: ";
        for (int k = i; k < n; k++) cout << arr[k] << " ";
        cout << endl;
        
        // Сдвигаем элементы большие key вправо, чтобы освободить место
        while (j >= 0 && arr[j] > key) {
            cout << "  Сдвигаем " << arr[j] << " -> позиция " << j + 1 << endl;
            arr[j + 1] = arr[j];
            j = j - 1;
        }
        
        // Вставляем key на правильную позицию
        arr[j + 1] = key;
        
        cout << "  Вставляем " << key << " -> позиция " << j + 1 << endl;
        cout << "  Текущее состояние: ";
        for (int num : arr) cout << num << " ";
        cout << endl << endl;
    }
}

/**
 * Оптимизированная версия для больших массивов
 */
void insertionSortOptimized(vector<int>& arr) {
    int n = arr.size();
    
    for (int i = 1; i < n; i++) {
        int key = arr[i];
        int j = i - 1;
        
        // Используем бинарный поиск для нахождения позиции вставки
        int left = 0;
        int right = i - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > key) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        // Сдвигаем элементы
        for (int k = i - 1; k >= left; k--) {
            arr[k + 1] = arr[k];
        }
        
        arr[left] = key;
    }
}

/**
 * Вспомогательная функция для вывода массива
 */
void printArray(const vector<int>& arr) {
    for (int num : arr) {
        cout << num << " ";
    }
    cout << endl;
}

int main() {
    cout << "СОРТИРОВКА ВСТАВКАМИ (INSERTION SORT) НА C++" << endl;
    cout << "===========================================" << endl;
    
    // Пример 1: Подробная демонстрация
    vector<int> arr1 = {12, 11, 13, 5, 6};
    cout << "\nПример 1 - Подробная демонстрация:" << endl;
    insertionSort(arr1);
    cout << "Финальный результат: ";
    printArray(arr1);
    
    // Пример 2: Быстрая сортировка
    vector<int> arr2 = {64, 34, 25, 12, 22, 11, 90};
    cout << "\nПример 2 - Быстрая сортировка:" << endl;
    cout << "Исходный массив: ";
    printArray(arr2);
    
    insertionSortOptimized(arr2);
    cout << "Отсортированный массив: ";
    printArray(arr2);
    
    // Пример 3: Уже отсортированный массив (лучший случай)
    vector<int> arr3 = {1, 2, 3, 4, 5};
    cout << "\nПример 3 - Отсортированный массив:" << endl;
    cout << "Исходный массив: ";
    printArray(arr3);
    
    insertionSortOptimized(arr3);
    cout << "Отсортированный массив: ";
    printArray(arr3);
    
    return 0;
}
