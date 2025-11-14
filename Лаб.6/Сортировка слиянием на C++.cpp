#include <iostream>
#include <vector>
using namespace std;

/**
 * Функция слияния двух отсортированных подмассивов
 * 
 * @param arr - основной массив
 * @param left - левая граница
 * @param mid - середина (конец левого подмассива)
 * @param right - правая граница
 */
void merge(vector<int>& arr, int left, int mid, int right) {
    // Размеры двух подмассивов для слияния
    int n1 = mid - left + 1;  // Размер левого подмассива
    int n2 = right - mid;     // Размер правого подмассива
    
    // Создаем временные массивы
    vector<int> leftArr(n1);
    vector<int> rightArr(n2);
    
    // Копируем данные во временные массивы
    for (int i = 0; i < n1; i++)
        leftArr[i] = arr[left + i];
    for (int j = 0; j < n2; j++)
        rightArr[j] = arr[mid + 1 + j];
    
    // Слияние временных массивов обратно в arr[left..right]
    int i = 0;      // Индекс для левого подмассива
    int j = 0;      // Индекс для правого подмассива
    int k = left;   // Индекс для основного массива
    
    cout << "  Слияние: [" << left << "-" << mid << "] и [" << (mid + 1) << "-" << right << "]" << endl;
    cout << "  Левый: ";
    for (int x : leftArr) cout << x << " ";
    cout << "| Правый: ";
    for (int x : rightArr) cout << x << " ";
    cout << endl;
    
    while (i < n1 && j < n2) {
        if (leftArr[i] <= rightArr[j]) {
            arr[k] = leftArr[i];
            i++;
        } else {
            arr[k] = rightArr[j];
            j++;
        }
        k++;
    }
    
    // Копируем оставшиеся элементы левого подмассива
    while (i < n1) {
        arr[k] = leftArr[i];
        i++;
        k++;
    }
    
    // Копируем оставшиеся элементы правого подмассива
    while (j < n2) {
        arr[k] = rightArr[j];
        j++;
        k++;
    }
    
    cout << "  Результат: ";
    for (int x = left; x <= right; x++) cout << arr[x] << " ";
    cout << endl;
}

/**
 * Основная функция сортировки слиянием
 * 
 * @param arr - массив для сортировки
 * @param left - левая граница
 * @param right - правая граница
 */
void mergeSort(vector<int>& arr, int left, int right, int depth = 0) {
    // Отступ для визуализации рекурсии
    string indent(depth * 2, ' ');
    
    cout << indent << "mergeSort(" << left << ", " << right << ")" << endl;
    
    if (left >= right) {
        cout << indent << "  Базовый случай: один элемент" << endl;
        return;
    }
    
    // Находим середину
    int mid = left + (right - left) / 2;
    
    cout << indent << "  Делим на: [" << left << "-" << mid << "] и [" << (mid + 1) << "-" << right << "]" << endl;
    
    // Рекурсивно сортируем две половины
    mergeSort(arr, left, mid, depth + 1);
    mergeSort(arr, mid + 1, right, depth + 1);
    
    // Сливаем отсортированные половины
    cout << indent << "  Вызов merge для [" << left << "-" << right << "]" << endl;
    merge(arr, left, mid, right);
}

/**
 * Вспомогательная функция для удобного вызова
 */
void mergeSort(vector<int>& arr) {
    cout << "Начальный массив: ";
    for (int num : arr) cout << num << " ";
    cout << endl << endl;
    
    mergeSort(arr, 0, arr.size() - 1);
    
    cout << endl << "Финальный массив: ";
    for (int num : arr) cout << num << " ";
    cout << endl;
}

/**
 * Итеративная версия сортировки слиянием
 */
void iterativeMergeSort(vector<int>& arr) {
    int n = arr.size();
    
    // Размер текущего подмассива (1, 2, 4, 8, ...)
    for (int currSize = 1; currSize <= n - 1; currSize = 2 * currSize) {
        cout << "Размер подмассива: " << currSize << endl;
        
        // Выбираем начальную точку разных подмассивов текущего размера
        for (int leftStart = 0; leftStart < n - 1; leftStart += 2 * currSize) {
            // Находим конечную точку левого подмассива
            int mid = min(leftStart + currSize - 1, n - 1);
            // Находим конечную точку правого подмассива
            int rightEnd = min(leftStart + 2 * currSize - 1, n - 1);
            
            cout << "  Слияние: [" << leftStart << "-" << mid << "] и [" << (mid + 1) << "-" << rightEnd << "]" << endl;
            
            // Сливаем подмассивы arr[leftStart...mid] и arr[mid+1...rightEnd]
            merge(arr, leftStart, mid, rightEnd);
        }
    }
}

int main() {
    cout << "СОРТИРОВКА СЛИЯНИЕМ (MERGE SORT) НА C++" << endl;
    cout << "======================================" << endl;
    
    // Пример 1: Рекурсивная версия с подробным выводом
    vector<int> arr1 = {38, 27, 43, 3, 9, 82, 10};
    cout << "\nПример 1 - Рекурсивная версия:" << endl;
    mergeSort(arr1);
    
    // Пример 2: Итеративная версия
    vector<int> arr2 = {12, 11, 13, 5, 6, 7};
    cout << "\n\nПример 2 - Итеративная версия:" << endl;
    cout << "Начальный массив: ";
    for (int num : arr2) cout << num << " ";
    cout << endl;
    
    iterativeMergeSort(arr2);
    
    cout << "Финальный массив: ";
    for (int num : arr2) cout << num << " ";
    cout << endl;
    
    return 0;
}
