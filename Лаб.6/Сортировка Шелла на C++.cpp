#include <iostream>
#include <vector>
using namespace std;

/**
 * Сортировка Шелла (Shell Sort)
 * Улучшенная версия сортировки вставками, которая сортирует элементы,
 * находящиеся на определенном расстоянии (gap) друг от друга
 * 
 * @param arr - вектор для сортировки
 */
void shellSort(vector<int>& arr) {
    int n = arr.size();
    
    cout << "Начальный массив: ";
    for (int num : arr) cout << num << " ";
    cout << endl << endl;
    
    // Начинаем с большого промежутка и уменьшаем его
    // Используем последовательность Кнута: gap = gap * 3 + 1
    int gap = 1;
    while (gap < n / 3) {
        gap = gap * 3 + 1;
    }
    
    cout << "Используемая последовательность промежутков: ";
    vector<int> gaps;
    while (gap > 0) {
        gaps.push_back(gap);
        gap = (gap - 1) / 3;
    }
    
    for (int i = gaps.size() - 1; i >= 0; i--) {
        cout << gaps[i] << " ";
    }
    cout << endl << endl;
    
    // Проходим по всем промежуткам
    for (int g = gaps.size() - 1; g >= 0; g--) {
        gap = gaps[g];
        
        cout << "Промежуток (gap) = " << gap << ":" << endl;
        
        // Применяем сортировку вставками для элементов на расстоянии gap
        for (int i = gap; i < n; i++) {
            // Сохраняем текущий элемент
            int temp = arr[i];
            int j;
            
            cout << "  Обрабатываем элемент arr[" << i << "] = " << temp << endl;
            
            // Сдвигаем элементы, которые больше temp, на gap позиций вправо
            for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                cout << "    Сдвигаем arr[" << j - gap << "] = " << arr[j - gap] 
                     << " -> arr[" << j << "]" << endl;
                arr[j] = arr[j - gap];
            }
            
            // Вставляем temp на правильную позицию
            if (j != i) {
                cout << "    Вставляем " << temp << " -> arr[" << j << "]" << endl;
            } else {
                cout << "    Элемент уже на правильной позиции" << endl;
            }
            arr[j] = temp;
            
            cout << "    Текущее состояние: ";
            for (int num : arr) cout << num << " ";
            cout << endl;
        }
        cout << endl;
    }
}

/**
 * Упрощенная версия с последовательностью n/2, n/4, ..., 1
 */
void shellSortSimple(vector<int>& arr) {
    int n = arr.size();
    
    cout << "Упрощенная версия (последовательность n/2, n/4, ...):" << endl;
    
    // Начинаем с gap = n/2 и уменьшаем вдвое на каждом шаге
    for (int gap = n / 2; gap > 0; gap /= 2) {
        cout << "Промежуток (gap) = " << gap << ":" << endl;
        
        // Применяем сортировку вставками для этого gap
        for (int i = gap; i < n; i++) {
            int temp = arr[i];
            int j;
            
            // Сдвигаем элементы, которые больше temp
            for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                arr[j] = arr[j - gap];
            }
            
            arr[j] = temp;
        }
        
        // Выводим состояние после обработки этого gap
        cout << "  Состояние: ";
        for (int num : arr) cout << num << " ";
        cout << endl;
    }
}

/**
 * Функция для вывода массива
 */
void printArray(const vector<int>& arr) {
    for (int num : arr) {
        cout << num << " ";
    }
    cout << endl;
}

int main() {
    cout << "СОРТИРОВКА ШЕЛЛА (SHELL SORT) НА C++" << endl;
    cout << "===================================" << endl;
    
    // Пример 1: Подробная демонстрация
    vector<int> arr1 = {12, 34, 54, 2, 3, 8, 15, 22, 10};
    cout << "\nПример 1 - Подробная демонстрация:" << endl;
    shellSort(arr1);
    cout << "Финальный результат: ";
    printArray(arr1);
    
    // Пример 2: Упрощенная версия
    vector<int> arr2 = {64, 34, 25, 12, 22, 11, 90, 5};
    cout << "\nПример 2 - Упрощенная версия:" << endl;
    cout << "Начальный массив: ";
    printArray(arr2);
    shellSortSimple(arr2);
    cout << "Финальный результат: ";
    printArray(arr2);
    
    return 0;
}
