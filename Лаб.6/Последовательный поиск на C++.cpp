#include <iostream>
#include <vector>
#include <string>
using namespace std;

/**
 * Функция последовательного (линейного) поиска
 * Проходит по всем элементам массива и сравнивает каждый с искомым значением
 * 
 * @param arr - массив для поиска
 * @param target - искомое значение
 * @return индекс найденного элемента или -1 если не найден
 */
int sequentialSearch(const vector<int>& arr, int target) {
    cout << "Последовательный поиск элемента " << target << ":" << endl;
    
    // Проходим по всем элементам массива
    for (int i = 0; i < arr.size(); i++) {
        cout << "  Проверяем элемент [" << i << "] = " << arr[i];
        
        // Сравниваем текущий элемент с искомым
        if (arr[i] == target) {
            cout << " -> НАЙДЕН!" << endl;
            return i; // Возвращаем индекс найденного элемента
        }
        cout << " -> не совпадает" << endl;
    }
    
    cout << "  Элемент не найден в массиве" << endl;
    return -1; // Элемент не найден
}

/**
 * Последовательный поиск с подсчетом количества сравнений
 */
int sequentialSearchWithCount(const vector<int>& arr, int target, int& comparisons) {
    comparisons = 0;
    
    for (int i = 0; i < arr.size(); i++) {
        comparisons++;
        if (arr[i] == target) {
            return i;
        }
    }
    
    return -1;
}

/**
 * Последовательный поиск для строк
 */
int sequentialSearchString(const vector<string>& arr, const string& target) {
    cout << "Поиск строки \"" << target << "\":" << endl;
    
    for (int i = 0; i < arr.size(); i++) {
        cout << "  Проверяем [" << i << "] = \"" << arr[i] << "\"";
        
        if (arr[i] == target) {
            cout << " -> НАЙДЕН!" << endl;
            return i;
        }
        cout << " -> не совпадает" << endl;
    }
    
    cout << "  Строка не найдена" << endl;
    return -1;
}

/**
 * Функция поиска всех вхождений элемента
 */
vector<int> sequentialSearchAll(const vector<int>& arr, int target) {
    vector<int> indices;
    
    cout << "Поиск всех вхождений элемента " << target << ":" << endl;
    
    for (int i = 0; i < arr.size(); i++) {
        if (arr[i] == target) {
            indices.push_back(i);
            cout << "  Найден на позиции [" << i << "]" << endl;
        }
    }
    
    if (indices.empty()) {
        cout << "  Элемент не найден" << endl;
    } else {
        cout << "  Всего найдено: " << indices.size() << " вхождений" << endl;
    }
    
    return indices;
}

/**
 * Поиск с использованием шаблонов для работы с разными типами данных
 */
template<typename T>
int sequentialSearchTemplate(const vector<T>& arr, const T& target) {
    cout << "Поиск элемента: " << target << endl;
    
    for (int i = 0; i < arr.size(); i++) {
        cout << "  [" << i << "] = " << arr[i];
        
        if (arr[i] == target) {
            cout << " -> НАЙДЕН!" << endl;
            return i;
        }
        cout << " -> нет" << endl;
    }
    
    cout << "  Элемент не найден" << endl;
    return -1;
}

/**
 * Демонстрация работы алгоритма
 */
void demonstrateSequentialSearch() {
    cout << "ПОСЛЕДОВАТЕЛЬНЫЙ ПОИСК (LINEAR SEARCH) НА C++" << endl;
    cout << "============================================" << endl;
    
    // Пример 1: Поиск в массиве целых чисел
    vector<int> numbers = {3, 5, 2, 7, 9, 1, 4, 6, 8};
    int target = 7;
    
    cout << "\nПример 1 - Поиск в массиве целых чисел:" << endl;
    cout << "Массив: ";
    for (int num : numbers) cout << num << " ";
    cout << endl;
    
    int result = sequentialSearch(numbers, target);
    if (result != -1) {
        cout << "✓ Элемент " << target << " найден на позиции: " << result << endl;
    } else {
        cout << "✗ Элемент " << target << " не найден" << endl;
    }
    
    // Пример 2: Поиск всех вхождений
    vector<int> numbersWithDuplicates = {2, 5, 2, 8, 2, 9, 2, 1};
    target = 2;
    
    cout << "\nПример 2 - Поиск всех вхождений:" << endl;
    cout << "Массив: ";
    for (int num : numbersWithDuplicates) cout << num << " ";
    cout << endl;
    
    vector<int> allPositions = sequentialSearchAll(numbersWithDuplicates, target);
    
    // Пример 3: Поиск строк
    vector<string> words = {"apple", "banana", "cherry", "date", "elderberry"};
    string wordTarget = "cherry";
    
    cout << "\nПример 3 - Поиск строк:" << endl;
    cout << "Массив: ";
    for (const string& word : words) cout << word << " ";
    cout << endl;
    
    result = sequentialSearchString(words, wordTarget);
    if (result != -1) {
        cout << "✓ Строка \"" << wordTarget << "\" найдена на позиции: " << result << endl;
    }
    
    // Пример 4: Использование шаблонов
    cout << "\nПример 4 - Универсальный поиск с шаблонами:" << endl;
    vector<double> doubles = {1.1, 2.2, 3.3, 4.4, 5.5};
    double doubleTarget = 3.3;
    
    cout << "Массив double: ";
    for (double d : doubles) cout << d << " ";
    cout << endl;
    
    result = sequentialSearchTemplate(doubles, doubleTarget);
    
    // Пример 5: Анализ производительности
    cout << "\nПример 5 - Анализ производительности:" << endl;
    vector<int> largeArray(1000);
    for (int i = 0; i < largeArray.size(); i++) {
        largeArray[i] = i + 1;
    }
    
    int comparisons;
    
    // Лучший случай - элемент в начале
    sequentialSearchWithCount(largeArray, 1, comparisons);
    cout << "Лучший случай (первый элемент): " << comparisons << " сравнений" << endl;
    
    // Средний случай - элемент в середине
    sequentialSearchWithCount(largeArray, 500, comparisons);
    cout << "Средний случай (середина): " << comparisons << " сравнений" << endl;
    
    // Худший случай - элемент в конце
    sequentialSearchWithCount(largeArray, 1000, comparisons);
    cout << "Худший случай (последний элемент): " << comparisons << " сравнений" << endl;
    
    // Неудачный поиск
    sequentialSearchWithCount(largeArray, 1001, comparisons);
    cout << "Элемент отсутствует: " << comparisons << " сравнений" << endl;
}

int main() {
    demonstrateSequentialSearch();
    return 0;
}
