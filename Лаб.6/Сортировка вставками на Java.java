import java.util.Arrays;

/**
 * Класс для демонстрации алгоритма сортировки вставками
 * Сложность: O(n²) в худшем случае, O(n) в лучшем случае
 */
public class Main {
    
    /**
     * Метод сортировки вставками (Insertion Sort)
     * Алгоритм последовательно вставляет каждый элемент в отсортированную часть массива
     * 
     * @param arr - массив для сортировки
     */
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        
        System.out.println("Процесс сортировки вставками:");
        System.out.println("Исходный массив: " + Arrays.toString(arr));
        System.out.println();
        
        // Начинаем со второго элемента (i = 1)
        // Первый элемент (i = 0) считается отсортированным
        for (int i = 1; i < n; i++) {
            // Элемент, который нужно вставить
            int key = arr[i];
            // Индекс последнего элемента в отсортированной части
            int j = i - 1;
            
            System.out.println("Шаг " + i + ": Вставляем элемент " + key);
            System.out.println("  Отсортированная часть: " + 
                             Arrays.toString(Arrays.copyOfRange(arr, 0, i)) +
                             " | Неотсортированная: " + 
                             Arrays.toString(Arrays.copyOfRange(arr, i, n)));
            
            // Сдвигаем элементы большие key вправо
            // Пока не найдем правильную позицию для key
            while (j >= 0 && arr[j] > key) {
                System.out.println("  Сдвигаем " + arr[j] + " из позиции " + j + 
                                 " в позицию " + (j + 1));
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            
            // Вставляем key на найденную позицию
            arr[j + 1] = key;
            
            System.out.println("  Вставляем " + key + " в позицию " + (j + 1));
            System.out.println("  Текущее состояние: " + Arrays.toString(arr));
            System.out.println();
        }
    }
    
    /**
     * Оптимизированная версия с бинарным поиском
     */
    public static void insertionSortWithBinarySearch(int[] arr) {
        int n = arr.length;
        
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            
            // Используем бинарный поиск для нахождения позиции вставки
            int pos = binarySearchPosition(arr, 0, i - 1, key);
            
            // Сдвигаем элементы от i-1 до pos вправо
            for (int j = i - 1; j >= pos; j--) {
                arr[j + 1] = arr[j];
            }
            
            // Вставляем key на найденную позицию
            arr[pos] = key;
        }
    }
    
    /**
     * Бинарный поиск позиции для вставки элемента
     */
    private static int binarySearchPosition(int[] arr, int left, int right, int key) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (arr[mid] == key) {
                return mid + 1; // Вставляем после одинакового элемента
            } else if (arr[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
    
    /**
     * Сортировка вставками для строк (демонстрация универсальности)
     */
    public static void insertionSortStrings(String[] arr) {
        int n = arr.length;
        
        for (int i = 1; i < n; i++) {
            String key = arr[i];
            int j = i - 1;
            
            // Сравниваем строки лексикографически
            while (j >= 0 && arr[j].compareTo(key) > 0) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            
            arr[j + 1] = key;
        }
    }
    
    /**
     * Вспомогательный метод для вывода массива
     */
    public static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
    
    public static void printStringArray(String[] arr) {
        System.out.println(Arrays.toString(arr));
    }
    
    /**
     * Главный метод для тестирования алгоритма
     */
    public static void main(String[] args) {
        System.out.println("СОРТИРОВКА ВСТАВКАМИ (INSERTION SORT) НА JAVA");
        System.out.println("=============================================");
        
        // Пример 1: Подробная демонстрация
        int[] arr1 = {12, 11, 13, 5, 6};
        System.out.println("\nПример 1 - Подробная демонстрация:");
        insertionSort(arr1);
        System.out.println("Финальный результат: " + Arrays.toString(arr1));
        
        // Пример 2: Оптимизированная версия
        int[] arr2 = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("\nПример 2 - Оптимизированная версия:");
        System.out.println("Исходный массив: " + Arrays.toString(arr2));
        insertionSortWithBinarySearch(arr2);
        System.out.println("Отсортированный массив: " + Arrays.toString(arr2));
        
        // Пример 3: Уже отсортированный массив
        int[] arr3 = {1, 2, 3, 4, 5};
        System.out.println("\nПример 3 - Отсортированный массив (лучший случай):");
        System.out.println("Исходный массив: " + Arrays.toString(arr3));
        insertionSortWithBinarySearch(arr3);
        System.out.println("Отсортированный массив: " + Arrays.toString(arr3));
        
        // Пример 4: Сортировка строк
        String[] words = {"banana", "apple", "cherry", "date", "blueberry"};
        System.out.println("\nПример 4 - Сортировка строк:");
        System.out.println("Исходный массив: " + Arrays.toString(words));
        insertionSortStrings(words);
        System.out.println("Отсортированный массив: " + Arrays.toString(words));
        
        // Сравнение производительности
        System.out.println("\n--- ХАРАКТЕРИСТИКИ АЛГОРИТМА ---");
        System.out.println("• Лучший случай (отсортированный массив): O(n)");
        System.out.println("• Средний случай: O(n²)");
        System.out.println("• Худший случай (обратный порядок): O(n²)");
        System.out.println("• Пространственная сложность: O(1)");
        System.out.println("• Стабильность: Да");
        System.out.println("• Естественность: Да (адаптируется к частичной отсортированности)");
    }
}
