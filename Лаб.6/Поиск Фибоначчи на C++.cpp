#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

/**
 * Генерация чисел Фибоначчи до числа >= n
 * 
 * @param n - минимальное число Фибоначчи
 * @return вектор чисел Фибоначчи
 */
vector<int> generateFibonacciNumbers(int n) {
    vector<int> fib;
    if (n <= 0) return fib;
    
    fib.push_back(0);
    fib.push_back(1);
    
    // Генерируем числа Фибоначчи пока последнее число < n
    while (fib.back() < n) {
        int next = fib[fib.size() - 1] + fib[fib.size() - 2];
        fib.push_back(next);
    }
    
    return fib;
}

/**
 * Поиск Фибоначчи (Fibonacci Search)
 * Алгоритм использует числа Фибоначчи для определения позиций сравнения
 * 
 * @param arr - отсортированный массив для поиска
 * @param target - искомое значение
 * @return индекс найденного элемента или -1 если не найден
 */
int fibonacciSearch(const vector<int>& arr, int target) {
    int n = arr.size();
    int step = 1;
    
    cout << "Поиск Фибоначчи элемента " << target << ":" << endl;
    cout << "Отсортированный массив: ";
    for (int num : arr) cout << num << " ";
    cout << endl << endl;
    
    // Генерируем числа Фибоначчи
    vector<int> fib = generateFibonacciNumbers(n);
    int k = fib.size() - 1; // Индекс наибольшего числа Фибоначчи
    
    cout << "Числа Фибоначчи: ";
    for (int num : fib) cout << num << " ";
    cout << endl;
    cout << "k = " << k << " (F[" << k << "] = " << fib[k] << ")" << endl << endl;
    
    // Инициализация переменных
    int offset = -1; // Смещение от начала массива
    
    // Пока есть элементы для поиска
    while (k > 0) {
        cout << "Шаг " << step++ << " (k = " << k << "):" << endl;
        
        // Вычисляем индекс для сравнения
        // i = min(offset + F[k-2], n-1)
        int i = min(offset + fib[k - 2], n - 1);
        
        cout << "  i = min(offset + F[k-2], n-1) = min(" 
             << offset << " + " << fib[k - 2] << ", " << n - 1 << ") = " << i << endl;
        cout << "  arr[" << i << "] = " << arr[i] << ", target = " << target << endl;
        
        // Сравниваем элемент с целевым значением
        if (arr[i] < target) {
            // Перемещаемся в правую часть
            cout << "  arr[" << i << "] = " << arr[i] << " < " << target 
                 << " -> ищем в ПРАВОЙ части" << endl;
            offset = i;
            k = k - 1; // Уменьшаем k для следующего шага
        } 
        else if (arr[i] > target) {
            // Перемещаемся в левую часть
            cout << "  arr[" << i << "] = " << arr[i] << " > " << target 
                 << " -> ищем в ЛЕВОЙ части" << endl;
            k = k - 2; // Уменьшаем k на 2 для следующего шага
        } 
        else {
            // Элемент найден
            cout << "  arr[" << i << "] = " << arr[i] << " == " << target 
                 << " -> ЭЛЕМЕНТ НАЙДЕН!" << endl;
            return i;
        }
        
        cout << "  Новые значения: offset = " << offset << ", k = " << k << endl;
        
        // Проверяем последний элемент при k = 1
        if (k == 1 && offset + 1 < n && arr[offset + 1] == target) {
            cout << "  Проверка последнего элемента: arr[" << offset + 1 << "] = " 
                 << arr[offset + 1] << " == " << target << " -> НАЙДЕН!" << endl;
            return offset + 1;
        }
    }
    
    cout << "Элемент " << target << " не найден в массиве" << endl;
    return -1;
}

/**
 * Рекурсивная версия поиска Фибоначчи
 */
int fibonacciSearchRecursive(const vector<int>& arr, int target, 
                           const vector<int>& fib, int k, int offset, int depth = 0) {
    string indent(depth * 2, ' ');
    
    cout << indent << "fibonacciSearch(k=" << k << ", offset=" << offset << ")" << endl;
    
    if (k <= 0) {
        cout << indent << "  Базовый случай: k <= 0" << endl;
        return -1;
    }
    
    // Вычисляем индекс для сравнения
    int i = min(offset + fib[k - 2], (int)arr.size() - 1);
    
    cout << indent << "  i = min(" << offset << " + " << fib[k - 2] << ", " 
         << arr.size() - 1 << ") = " << i << endl;
    cout << indent << "  arr[" << i << "] = " << arr[i] << endl;
    
    if (arr[i] == target) {
        cout << indent << "  ЭЛЕМЕНТ НАЙДЕН!" << endl;
        return i;
    }
    else if (arr[i] < target) {
        cout << indent << "  Ищем в правой части" << endl;
        return fibonacciSearchRecursive(arr, target, fib, k - 1, i, depth + 1);
    }
    else {
        cout << indent << "  Ищем в левой части" << endl;
        return fibonacciSearchRecursive(arr, target, fib, k - 2, offset, depth + 1);
    }
}

/**
 * Сравнение с бинарным поиском
 */
void compareWithBinarySearch(const vector<int>& arr, int target) {
    cout << "\n=== СРАВНЕНИЕ С БИНАРНЫМ ПОИСКОМ ===" << endl;
    
    // Поиск Фибоначчи
    cout << "Поиск Фибоначчи:" << endl;
    int result1 = fibonacciSearch(arr, target);
    
    // Бинарный поиск (имитация)
    cout << "\nБинарный поиск:" << endl;
    int left = 0;
    int right = arr.size() - 1;
    int steps = 0;
    
    while (left <= right) {
        steps++;
        int mid = left + (right - left) / 2;
        
        cout << "  Шаг " << steps << ": mid = " << mid << " (arr[" << mid << "] = " << arr[mid] << ")" << endl;
        
        if (arr[mid] == target) {
            cout << "  Элемент найден за " << steps << " шагов" << endl;
            break;
        } else if (arr[mid] > target) {
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }
}

/**
 * Демонстрация на разных размерах массивов
 */
void demonstrateDifferentSizes() {
    cout << "\n=== РАЗНЫЕ РАЗМЕРЫ МАССИВОВ ===" << endl;
    
    // Маленький массив
    vector<int> smallArr = {1, 3, 5, 7, 9};
    cout << "Маленький массив (" << smallArr.size() << " элементов): ";
    for (int num : smallArr) cout << num << " ";
    cout << endl;
    fibonacciSearch(smallArr, 5);
    
    // Средний массив
    vector<int> mediumArr = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20};
    cout << "\nСредний массив (" << mediumArr.size() << " элементов): ";
    for (int num : mediumArr) cout << num << " ";
    cout << endl;
    fibonacciSearch(mediumArr, 14);
    
    // Большой массив
    vector<int> largeArr(21);
    for (int i = 0; i < largeArr.size(); i++) {
        largeArr[i] = i * 2; // Четные числа
    }
    cout << "\nБольшой массив (" << largeArr.size() << " элементов): ";
    for (int i = 0; i < min(10, (int)largeArr.size()); i++) {
        cout << largeArr[i] << " ";
    }
    cout << "... ";
    for (int i = max(0, (int)largeArr.size() - 5); i < largeArr.size(); i++) {
        cout << largeArr[i] << " ";
    }
    cout << endl;
    fibonacciSearch(largeArr, 30);
}

/**
 * Анализ чисел Фибоначчи
 */
void analyzeFibonacciNumbers() {
    cout << "\n=== АНАЛИЗ ЧИСЕЛ ФИБОНАЧЧИ ===" << endl;
    
    for (int n = 10; n <= 100; n *= 10) {
        vector<int> fib = generateFibonacciNumbers(n);
        cout << "Для n = " << n << ":" << endl;
        cout << "  Количество чисел Фибоначчи: " << fib.size() << endl;
        cout << "  Числа: ";
        for (int num : fib) cout << num << " ";
        cout << endl;
        cout << "  Золотое сечение приближение: " << fib.back() / (double)fib[fib.size() - 2] << endl;
        cout << endl;
    }
}

int main() {
    cout << "ПОИСК ФИБОНАЧЧИ (FIBONACCI SEARCH) НА C++" << endl;
    cout << "========================================" << endl;
    
    // Пример 1: Основной пример
    vector<int> arr1 = {10, 22, 35, 40, 45, 50, 80, 82, 85, 90, 100};
    int target1 = 85;
    
    cout << "\nПример 1 - Основной пример:" << endl;
    fibonacciSearch(arr1, target1);
    
    // Пример 2: Рекурсивная версия
    cout << "\nПример 2 - Рекурсивная версия:" << endl;
    vector<int> fib = generateFibonacciNumbers(arr1.size());
    int k = fib.size() - 1;
    cout << "Поиск элемента 40:" << endl;
    fibonacciSearchRecursive(arr1, 40, fib, k, -1);
    
    // Пример 3: Поиск несуществующего элемента
    cout << "\nПример 3 - Поиск несуществующего элемента:" << endl;
    fibonacciSearch(arr1, 75);
    
    // Пример 4: Сравнение с бинарным поиском
    compareWithBinarySearch(arr1, 50);
    
    // Пример 5: Разные размеры массивов
    demonstrateDifferentSizes();
    
    // Пример 6: Анализ чисел Фибоначчи
    analyzeFibonacciNumbers();
    
    // Характеристики алгоритма
    cout << "\n--- ХАРАКТЕРИСТИКИ АЛГОРИТМА ---" << endl;
    cout << "• Временная сложность: O(log n)" << endl;
    cout << "• Пространственная сложность: O(1)" << endl;
    cout << "• Преимущества:" << endl;
    cout << "  - Использует только сложение и вычитание" << endl;
    cout << "  - Хорош для больших массивов" << endl;
    cout << "  - Работает без деления" << endl;
    cout << "• Недостатки:" << endl;
    cout << "  - Сложнее в реализации" << endl;
    cout << "  - Требует генерации чисел Фибоначчи" << endl;
    cout << "• Применение:" << endl;
    cout << "  - Системы с ограниченными вычислительными ресурсами" << endl;
    cout << "  - Когда деление дорогая операция" << endl;
    
    return 0;
}
