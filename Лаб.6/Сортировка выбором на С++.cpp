#include <iostream>
#include <vector>
using namespace std;

/**
 * Функция сортировки выбором (Selection Sort)
 * Алгоритм: на каждой итерации находим минимальный элемент 
 * в неотсортированной части и меняем его с первым элементом этой части
 * 
 * @param arr - вектор для сортировки (передается по ссылке)
 */
void selectionSort(vector<int>& arr) {
    int n = arr.size();
    
    // Внешний цикл: проходим по всем элементам, кроме последнего
    for (int i = 0; i < n - 1; i++) {
        // Предполагаем, что текущий элемент - минимальный
        int minIndex = i;
        
        // Внутренний цикл: ищем минимальный элемент в неотсортированной части
        for (int j = i + 1; j < n; j++) {
            // Если находим элемент меньше текущего минимального
            if (arr[j] < arr[minIndex]) {
                minIndex = j; // Обновляем индекс минимального элемента
            }
        }
        
        // Меняем местами найденный минимальный элемент с текущим
        // Теперь arr[i] находится на своем окончательном месте
        swap(arr[i], arr[minIndex]);
        
        // Вывод промежуточного состояния (для наглядности)
        cout << "После итерации " << i + 1 << ": ";
        for (int num : arr) {
            cout << num << " ";
        }
        cout << endl;
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
    // Тестовый массив
    vector<int> arr = {64, 25, 12, 22, 11};
    
    cout << "Сортировка выбором (Selection Sort)" << endl;
    cout << "===================================" << endl;
    cout << "Исходный массив: ";
    printArray(arr);
    cout << endl;
    
    // Вызываем сортировку
    selectionSort(arr);
    
    cout << "\nФинальный отсортированный массив: ";
    printArray(arr);
    
    // Дополнительный пример
    cout << "\n--- Дополнительный пример ---" << endl;
    vector<int> arr2 = {5, 2, 8, 1, 9, 3};
    cout << "Исходный массив: ";
    printArray(arr2);
    
    selectionSort(arr2);
    cout << "Отсортированный массив: ";
    printArray(arr2);
    
    return 0;
}
