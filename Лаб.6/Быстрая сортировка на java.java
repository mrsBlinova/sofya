import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

/**
 * Класс для демонстрации алгоритма быстрой сортировки
 * Сложность: O(n log n) в среднем случае, O(n²) в худшем случае
 */
public class Main {
    
    /**
     * Функция разделения массива
     * 
     * @param arr - массив для разделения
     * @param low - начальный индекс
     * @param high - конечный индекс
     * @return индекс опорного элемента
     */
    public static int partition(int[] arr, int low, int high, int depth) {
        // Выбираем последний элемент как опорный
        int pivot = arr[high];
        
        // Индекс для элемента, который будет указывать на правильную позицию опорного
        int i = low - 1;
        
        String indent = "  ".repeat(depth);
        System.out.println(indent + "Разделение [" + low + "-" + high + "], опорный = " + pivot);
        System.out.println(indent + "Исходный подмассив: " + 
                          arrayToString(arr, low, high));
        
        for (int j = low; j < high; j++) {
            // Если текущий элемент меньше или равен опорному
            if (arr[j] <= pivot) {
                i++;
                // Меняем местами arr[i] и arr[j]
                swap(arr, i, j);
                
                if (i != j) {
                    System.out.println(indent + "  Меняем " + arr[j] + " и " + arr[i] + 
                                      " -> " + arrayToString(arr, low, high));
                }
            }
        }
        
        // Помещаем опорный элемент на правильную позицию
        swap(arr, i + 1, high);
        
        System.out.println(indent + "Ставим опорный " + pivot + " на позицию " + (i + 1) + 
                          " -> " + arrayToString(arr, low, high));
        
        return i + 1;
    }
    
    /**
     * Функция разделения со случайным выбором опорного элемента
     */
    public static int randomizedPartition(int[] arr, int low, int high, int depth) {
        Random rand = new Random();
        int randomIndex = low + rand.nextInt(high - low + 1);
        
        // Меняем случайный элемент с последним
        swap(arr, randomIndex, high);
        
        String indent = "  ".repeat(depth);
        System.out.println(indent + "Случайный опорный: arr[" + randomIndex + "] = " + arr[high]);
        
        return partition(arr, low, high, depth);
    }
    
    /**
     * Основная функция быстрой сортировки
     */
    public static void quickSort(int[] arr, int low, int high, int depth) {
        String indent = "  ".repeat(depth);
        System.out.println(indent + "quickSort(" + low + ", " + high + ")");
        
        if (low < high) {
            // Получаем индекс опорного элемента
            int pi = partition(arr, low, high, depth);
            
            // Рекурсивно сортируем элементы до и после опорного
            System.out.println(indent + "Левая часть: [" + low + "-" + (pi - 1) + "]");
            quickSort(arr, low, pi - 1, depth + 1);
            
            System.out.println(indent + "Правая часть: [" + (pi + 1) + "-" + high + "]");
            quickSort(arr, pi + 1, high, depth + 1);
        } else if (low == high) {
            System.out.println(indent + "Базовый случай: один элемент " + arr[low]);
        }
    }
    
    /**
     * Упрощенный метод для вызова сортировки
     */
    public static void quickSort(int[] arr) {
        System.out.println("Начальный массив: " + Arrays.toString(arr));
        System.out.println("\nПроцесс сортировки:");
        quickSort(arr, 0, arr.length - 1, 0);
    }
    
    /**
     * Итеративная версия быстрой сортировки
     */
    public static void iterativeQuickSort(int[] arr) {
        int low = 0;
        int high = arr.length - 1;
        
        // Используем стек для хранения границ подмассивов
        Stack<Integer> stack = new Stack<>();
        stack.push(low);
        stack.push(high);
        
        System.out.println("\nИтеративная быстрая сортировка:");
        
        while (!stack.isEmpty()) {
            high = stack.pop();
            low = stack.pop();
            
            System.out.println("Обрабатываем [" + low + "-" + high + "]");
            
            if (low < high) {
                int pi = partition(arr, low, high, 1);
                
                // Если есть элементы слева от опорного, добавляем в стек
                if (pi - 1 > low) {
                    stack.push(low);
                    stack.push(pi - 1);
                }
                
                // Если есть элементы справа от опорного, добавляем в стек
                if (pi + 1 < high) {
                    stack.push(pi + 1);
                    stack.push(high);
                }
            }
        }
    }
    
    /**
     * 3-way быстрая сортировка (для массивов с повторяющимися элементами)
     */
    public static void threeWayQuickSort(int[] arr, int low, int high, int depth) {
        if (high <= low) return;
        
        String indent = "  ".repeat(depth);
        System.out.println(indent + "3-way quickSort [" + low + "-" + high + "]");
        
        int lt = low;      // Конец элементов меньше опорного
        int gt = high;     // Начало элементов больше опорного
        int pivot = arr[low]; // Опорный элемент
        int i = low + 1;   // Текущий указатель
        
        System.out.println(indent + "Опорный: " + pivot);
        System.out.println(indent + "Начальное состояние: " + arrayToString(arr, low, high));
        
        while (i <= gt) {
            if (arr[i] < pivot) {
                swap(arr, lt++, i++);
                System.out.println(indent + "Меньше опорного: " + arrayToString(arr, low, high));
            } else if (arr[i] > pivot) {
                swap(arr, i, gt--);
                System.out.println(indent + "Больше опорного: " + arrayToString(arr, low, high));
            } else {
                i++;
                System.out.println(indent + "Равно опорному: " + arrayToString(arr, low, high));
            }
        }
        
        // Рекурсивно сортируем левую и правую части
        threeWayQuickSort(arr, low, lt - 1, depth + 1);
        threeWayQuickSort(arr, gt + 1, high, depth + 1);
    }
    
    /**
     * Вспомогательный метод для обмена элементов
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    /**
     * Вспомогательный метод для форматированного вывода подмассива
     */
    private static String arrayToString(int[] arr, int start, int end) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = start; i <= end; i++) {
            sb.append(arr[i]);
            if (i < end) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
    
    /**
     * Главный метод для тестирования
     */
    public static void main(String[] args) {
        System.out.println("БЫСТРАЯ СОРТИРОВКА (QUICK SORT) НА JAVA");
        System.out.println("======================================");
        
        // Пример 1: Рекурсивная версия
        int[] arr1 = {10, 7, 8, 9, 1, 5};
        System.out.println("\nПример 1 - Рекурсивная версия:");
        quickSort(arr1);
        System.out.println("\nФинальный результат: " + Arrays.toString(arr1));
        
        // Пример 2: Итеративная версия
        int[] arr2 = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("\nПример 2 - Итеративная версия:");
        System.out.println("Начальный массив: " + Arrays.toString(arr2));
        iterativeQuickSort(arr2);
        System.out.println("Финальный результат: " + Arrays.toString(arr2));
        
        // Пример 3: 3-way быстрая сортировка
        int[] arr3 = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5};
        System.out.println("\nПример 3 - 3-way быстрая сортировка:");
        System.out.println("Начальный массив: " + Arrays.toString(arr3));
        threeWayQuickSort(arr3, 0, arr3.length - 1, 0);
        System.out.println("Финальный результат: " + Arrays.toString(arr3));
        
        // Характеристики алгоритма
        System.out.println("\n--- ХАРАКТЕРИСТИКИ АЛГОРИТМА ---");
        System.out.println("• Лучший случай: O(n log n)");
        System.out.println("• Средний случай: O(n log n)");
        System.out.println("• Худший случай: O(n²)");
        System.out.println("• Пространственная сложность: O(log n)");
        System.out.println("• Стабильность: Нет");
        System.out.println("• Естественность: Да");
    }
}
