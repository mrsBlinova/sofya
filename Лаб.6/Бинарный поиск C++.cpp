#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath> // Добавляем для функции log2
using namespace std;

/**
 * Итеративная версия бинарного поиска
 * 
 * @param arr - отсортированный массив для поиска
 * @param target - искомое значение
 * @return индекс найденного элемента или -1 если не найден
 */
int binarySearchIterative(const vector<int>& arr, int target) {
    int left = 0;
    int right = arr.size() - 1;
    int step = 1;
    
    cout << "Бинарный поиск (итеративный) элемента " << target << ":" << endl;
    cout << "Отсортированный массив: ";
    for (int num : arr) cout << num << " ";
    cout << endl << endl;
    
    while (left <= right) {
        int mid = left + (right - left) / 2; // Предотвращает переполнение
        
        cout << "Шаг " << step++ << ":" << endl;
        cout << "  left = " << left << " (" << arr[left] << "), "
             << "right = " << right << " (" << arr[right] << "), "
             << "mid = " << mid << " (" << arr[mid] << ")" << endl;
        
        // Проверяем средний элемент
        if (arr[mid] == target) {
            cout << "  arr[" << mid << "] = " << arr[mid] << " == " << target 
                 << " -> ЭЛЕМЕНТ НАЙДЕН!" << endl;
            return mid;
        }
        
        // Если target меньше, ищем в левой половине
        if (arr[mid] > target) {
            cout << "  arr[" << mid << "] = " << arr[mid] << " > " << target 
                 << " -> ищем в ЛЕВОЙ половине" << endl;
            right = mid - 1;
        } 
        // Если target больше, ищем в правой половине
        else {
            cout << "  arr[" << mid << "] = " << arr[mid] << " < " << target 
                 << " -> ищем в ПРАВОЙ половине" << endl;
            left = mid + 1;
        }
        
        cout << "  Новые границы: left = " << left << ", right = " << right << endl;
    }
    
    cout << "  Элемент " << target << " не найден в массиве" << endl;
    return -1;
}

/**
 * Рекурсивная версия бинарного поиска
 */
int binarySearchRecursive(const vector<int>& arr, int target, int left, int right, int depth = 0) {
    string indent(depth * 2, ' ');
    
    cout << indent << "binarySearch(" << left << ", " << right << ")" << endl;
    
    if (left > right) {
        cout << indent << "  Базовый случай: границы пересеклись" << endl;
        return -1;
    }
    
    int mid = left + (right - left) / 2;
    
    cout << indent << "  mid = " << mid << " (" << arr[mid] << ")" << endl;
    
    if (arr[mid] == target) {
        cout << indent << "  arr[" << mid << "] = " << arr[mid] << " == " << target 
             << " -> НАЙДЕН!" << endl;
        return mid;
    }
    
    if (arr[mid] > target) {
        cout << indent << "  arr[" << mid << "] = " << arr[mid] << " > " << target 
             << " -> идем влево" << endl;
        return binarySearchRecursive(arr, target, left, mid - 1, depth + 1);
    } else {
        cout << indent << "  arr[" << mid << "] = " << arr[mid] << " < " << target 
             << " -> идем вправо" << endl;
        return binarySearchRecursive(arr, target, mid + 1, right, depth + 1);
    }
}

/**
 * Бинарный поиск с использованием STL
 */
void binarySearchSTL(const vector<int>& arr, int target) {
    cout << "Бинарный поиск с использованием STL:" << endl;
    
    // binary_search - проверяет наличие элемента
    bool exists = binary_search(arr.begin(), arr.end(), target);
    cout << "  binary_search: элемент " << target 
         << (exists ? " существует" : " не существует") << endl;
    
    // lower_bound - первый элемент не меньший target
    auto lower = lower_bound(arr.begin(), arr.end(), target);
    if (lower != arr.end()) {
        cout << "  lower_bound: первый элемент >= " << target 
             << " находится на позиции " << (lower - arr.begin()) 
             << " (значение: " << *lower << ")" << endl;
    }
    
    // upper_bound - первый элемент больший target
    auto upper = upper_bound(arr.begin(), arr.end(), target);
    if (upper != arr.end()) {
        cout << "  upper_bound: первый элемент > " << target 
             << " находится на позиции " << (upper - arr.begin()) 
             << " (значение: " << *upper << ")" << endl;
    }
    
    // equal_range - диапазон элементов равных target
    auto range = equal_range(arr.begin(), arr.end(), target);
    cout << "  equal_range: элементы равные " << target 
         << " находятся в диапазоне [" << (range.first - arr.begin()) 
         << ", " << (range.second - arr.begin()) << ")" << endl;
}

/**
 * Бинарный поиск для нахождения первого вхождения (если есть дубликаты)
 */
int findFirstOccurrence(const vector<int>& arr, int target) {
    int left = 0;
    int right = arr.size() - 1;
    int result = -1;
    
    cout << "Поиск первого вхождения элемента " << target << ":" << endl;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        
        cout << "  left=" << left << ", right=" << right << ", mid=" << mid 
             << " (arr[mid]=" << arr[mid] << ")" << endl;
        
        if (arr[mid] == target) {
            result = mid;
            cout << "    Нашли на позиции " << mid << ", продолжаем поиск слева" << endl;
            right = mid - 1; // Продолжаем искать в левой половине
        } else if (arr[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    
    if (result != -1) {
        cout << "  Первое вождение на позиции: " << result << endl;
    } else {
        cout << "  Элемент не найден" << endl;
    }
    
    return result;
}

/**
 * Бинарный поиск для нахождения последнего вхождения
 */
int findLastOccurrence(const vector<int>& arr, int target) {
    int left = 0;
    int right = arr.size() - 1;
    int result = -1;
    
    cout << "Поиск последнего вхождения элемента " << target << ":" << endl;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        
        cout << "  left=" << left << ", right=" << right << ", mid=" << mid 
             << " (arr[mid]=" << arr[mid] << ")" << endl;
        
        if (arr[mid] == target) {
            result = mid;
            cout << "    Нашли на позиции " << mid << ", продолжаем поиск справа" << endl;
            left = mid + 1; // Продолжаем искать в правой половине
        } else if (arr[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    
    if (result != -1) {
        cout << "  Последнее вождение на позиции: " << result << endl;
    } else {
        cout << "  Элемент не найден" << endl;
    }
    
    return result;
}

/**
 * Демонстрация работы алгоритма
 */
void demonstrateBinarySearch() {
    cout << "БИНАРНЫЙ ПОИСК (BINARY SEARCH) НА C++" << endl;
    cout << "====================================" << endl;
    
    // Пример 1: Основной пример
    vector<int> arr1 = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
    int target1 = 13;
    
    cout << "\nПример 1 - Основной пример:" << endl;
    binarySearchIterative(arr1, target1);
    
    // Пример 2: Рекурсивный поиск
    cout << "\nПример 2 - Рекурсивный поиск:" << endl;
    cout << "Поиск элемента 7:" << endl;
    binarySearchRecursive(arr1, 7, 0, arr1.size() - 1);
    
    // Пример 3: Поиск несуществующего элемента
    cout << "\nПример 3 - Поиск несуществующего элемента:" << endl;
    binarySearchIterative(arr1, 8);
    
    // Пример 4: STL функции
    cout << "\nПример 4 - Использование STL:" << endl;
    binarySearchSTL(arr1, 11);
    
    // Пример 5: Поиск в массиве с дубликатами
    vector<int> arrWithDuplicates = {1, 2, 2, 2, 3, 4, 4, 5, 5, 5, 5, 6};
    cout << "\nПример 5 - Массив с дубликатами:" << endl;
    cout << "Массив: ";
    for (int num : arrWithDuplicates) cout << num << " ";
    cout << endl;
    
    findFirstOccurrence(arrWithDuplicates, 2);
    findLastOccurrence(arrWithDuplicates, 5);
    
    // Пример 6: Анализ сложности
    cout << "\nПример 6 - Анализ сложности:" << endl;
    vector<int> largeArray(1000000);
    for (int i = 0; i < largeArray.size(); i++) {
        largeArray[i] = i * 2; // Четные числа
    }
    
    cout << "Размер массива: " << largeArray.size() << " элементов" << endl;
    cout << "Максимальное количество сравнений: " 
         << (int)log2(largeArray.size()) + 1 << endl;
}

int main() {
    demonstrateBinarySearch();
    return 0;
}
