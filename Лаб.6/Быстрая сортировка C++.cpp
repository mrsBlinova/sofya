#include <iostream>
#include <vector>
#include <cstdlib>
#include <ctime>
using namespace std;

/**
 * Функция разделения массива для быстрой сортировки
 * Выбирает опорный элемент и перераспределяет элементы так,
 * чтобы все элементы меньше опорного оказались слева,
 * а все элементы больше опорного - справа
 * 
 * @param arr - массив для разделения
 * @param low - начальный индекс
 * @param high - конечный индекс
 * @return индекс опорного элемента после разделения
 */
int partition(vector<int>& arr, int low, int high) {
    // Выбираем последний элемент в качестве опорного
    int pivot = arr[high];
    
    // Индекс элемента, который будет указывать на правильную позицию опорного
    int i = low - 1;
    
    cout << "  Разделение [" << low << "-" << high << "], опорный = " << pivot << endl;
    cout << "  Исходный подмассив: ";
    for (int k = low; k <= high; k++) cout << arr[k] << " ";
    cout << endl;
    
    for (int j = low; j < high; j++) {
        // Если текущий элемент меньше или равен опорному
        if (arr[j] <= pivot) {
            i++; // Увеличиваем индекс для меньших элементов
            swap(arr[i], arr[j]);
            
            if (i != j) {
                cout << "    Меняем " << arr[j] << " и " << arr[i] << " -> ";
                for (int k = low; k <= high; k++) cout << arr[k] << " ";
                cout << endl;
            }
        }
    }
    
    // Помещаем опорный элемент на правильную позицию
    swap(arr[i + 1], arr[high]);
    
    cout << "  Ставим опорный " << pivot << " на позицию " << i + 1 << " -> ";
    for (int k = low; k <= high; k++) cout << arr[k] << " ";
    cout << endl;
    
    return i + 1;
}

/**
 * Функция разделения со случайным выбором опорного элемента
 */
int randomizedPartition(vector<int>& arr, int low, int high) {
    // Выбираем случайный индекс для опорного элемента
    srand(time(0));
    int randomIndex = low + rand() % (high - low + 1);
    
    // Меняем случайный элемент с последним
    swap(arr[randomIndex], arr[high]);
    
    cout << "  Случайный опорный: arr[" << randomIndex << "] = " << arr[high] << endl;
    
    return partition(arr, low, high);
}

/**
 * Основная функция быстрой сортировки
 * 
 * @param arr - массив для сортировки
 * @param low - начальный индекс
 * @param high - конечный индекс
 */
void quickSort(vector<int>& arr, int low, int high, int depth = 0) {
    string indent(depth * 2, ' ');
    
    cout << indent << "quickSort(" << low << ", " << high << ")" << endl;
    
    if (low < high) {
        // Разделяем массив и получаем индекс опорного элемента
        int pi = partition(arr, low, high);
        
        // Рекурсивно сортируем элементы до и после разбиения
        cout << indent << "  Левая часть: [" << low << "-" << pi - 1 << "]" << endl;
        quickSort(arr, low, pi - 1, depth + 1);
        
        cout << indent << "  Правая часть: [" << pi + 1 << "-" << high << "]" << endl;
        quickSort(arr, pi + 1, high, depth + 1);
    } else if (low == high) {
        cout << indent << "  Базовый случай: один элемент " << arr[low] << endl;
    }
}

/**
 * Итеративная версия быстрой сортировки
 */
void iterativeQuickSort(vector<int>& arr) {
    int low = 0;
    int high = arr.size() - 1;
    
    // Создаем стек для хранения границ подмассивов
    vector<int> stack(high - low + 1);
    int top = -1;
    
    // Помещаем начальные границы в стек
    stack[++top] = low;
    stack[++top] = high;
    
    cout << "Итеративная быстрая сортировка:" << endl;
    
    // Пока стек не пуст
    while (top >= 0) {
        // Извлекаем границы
        high = stack[top--];
        low = stack[top--];
        
        cout << "Обрабатываем [" << low << "-" << high << "]" << endl;
        
        // Разделяем массив
        int pi = partition(arr, low, high);
        
        // Если есть элементы слева от опорного, помещаем их в стек
        if (pi - 1 > low) {
            stack[++top] = low;
            stack[++top] = pi - 1;
        }
        
        // Если есть элементы справа от опорного, помещаем их в стек
        if (pi + 1 < high) {
            stack[++top] = pi + 1;
            stack[++top] = high;
        }
    }
}

/**
 * Быстрая сортировка с тремя частями (для массивов с повторяющимися элементами)
 */
void threeWayQuickSort(vector<int>& arr, int low, int high) {
    if (high <= low) return;
    
    int lt = low;      // Указатель на конец элементов меньше опорного
    int gt = high;     // Указатель на начало элементов больше опорного
    int pivot = arr[low]; // Опорный элемент
    int i = low;       // Текущий указатель
    
    cout << "3-way quickSort [" << low << "-" << high << "], pivot = " << pivot << endl;
    
    while (i <= gt) {
        if (arr[i] < pivot) {
            swap(arr[lt++], arr[i++]);
        } else if (arr[i] > pivot) {
            swap(arr[i], arr[gt--]);
        } else {
            i++;
        }
    }
    
    // Рекурсивно сортируем левую и правую части
    threeWayQuickSort(arr, low, lt - 1);
    threeWayQuickSort(arr, gt + 1, high);
}

int main() {
    cout << "БЫСТРАЯ СОРТИРОВКА (QUICK SORT) НА C++" << endl;
    cout << "=====================================" << endl;
    
    // Пример 1: Рекурсивная версия с подробным выводом
    vector<int> arr1 = {10, 7, 8, 9, 1, 5};
    cout << "\nПример 1 - Рекурсивная версия:" << endl;
    cout << "Начальный массив: ";
    for (int num : arr1) cout << num << " ";
    cout << endl << endl;
    
    quickSort(arr1, 0, arr1.size() - 1);
    
    cout << "\nФинальный результат: ";
    for (int num : arr1) cout << num << " ";
    cout << endl;
    
    // Пример 2: Итеративная версия
    vector<int> arr2 = {64, 34, 25, 12, 22, 11, 90};
    cout << "\nПример 2 - Итеративная версия:" << endl;
    cout << "Начальный массив: ";
    for (int num : arr2) cout << num << " ";
    cout << endl;
    
    iterativeQuickSort(arr2);
    cout << "Финальный результат: ";
    for (int num : arr2) cout << num << " ";
    cout << endl;
    
    // Пример 3: 3-way быстрая сортировка
    vector<int> arr3 = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5};
    cout << "\nПример 3 - 3-way быстрая сортировка (для повторяющихся элементов):" << endl;
    cout << "Начальный массив: ";
    for (int num : arr3) cout << num << " ";
    cout << endl;
    
    threeWayQuickSort(arr3, 0, arr3.size() - 1);
    cout << "Финальный результат: ";
    for (int num : arr3) cout << num << " ";
    cout << endl;
    
    return 0;
}
