import java.util.Arrays;

/**
 * Класс для демонстрации алгоритма сортировки слиянием
 * Сложность: O(n log n) во всех случаях
 */
public class Main {
    
    /**
     * Функция слияния двух отсортированных подмассивов
     * 
     * @param arr - основной массив
     * @param left - левая граница
     * @param mid - середина
     * @param right - правая граница
     */
    public static void merge(int[] arr, int left, int mid, int right) {
        // Размеры двух подмассивов
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        // Создаем временные массивы
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];
        
        // Копируем данные во временные массивы
        for (int i = 0; i < n1; i++)
            leftArr[i] = arr[left + i];
        for (int j = 0; j < n2; j++)
            rightArr[j] = arr[mid + 1 + j];
        
        // Индексы для слияния
        int i = 0, j = 0, k = left;
        
        System.out.println("    Слияние подмассивов:");
        System.out.println("      Левый: " + arrayToString(leftArr, 0, n1 - 1));
        System.out.println("      Правый: " + arrayToString(rightArr, 0, n2 - 1));
        
        // Слияние временных массивов
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
        
        // Копируем оставшиеся элементы
        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }
        
        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
        
        System.out.println("      Результат: " + arrayToString(arr, left, right));
    }
    
    /**
     * Основная функция сортировки слиянием (рекурсивная)
     */
    public static void mergeSort(int[] arr, int left, int right, int depth) {
        String indent = "  ".repeat(depth);
        
        System.out.println(indent + "mergeSort(" + left + ", " + right + ")");
        
        if (left < right) {
            // Находим середину
            int mid = left + (right - left) / 2;
            
            System.out.println(indent + "  Делим на: [" + left + "-" + mid + "] и [" + (mid + 1) + "-" + right + "]");
            
            // Рекурсивно сортируем обе половины
            mergeSort(arr, left, mid, depth + 1);
            mergeSort(arr, mid + 1, right, depth + 1);
            
            // Сливаем отсортированные половины
            System.out.println(indent + "  Сливаем: [" + left + "-" + mid + "] и [" + (mid + 1) + "-" + right + "]");
            merge(arr, left, mid, right);
        } else {
            System.out.println(indent + "  Базовый случай - один элемент: " + arr[left]);
        }
    }
    
    /**
     * Упрощенный метод для вызова сортировки
     */
    public static void mergeSort(int[] arr) {
        System.out.println("Начальный массив: " + arrayToString(arr, 0, arr.length - 1));
        System.out.println("\nПроцесс сортировки:");
        mergeSort(arr, 0, arr.length - 1, 0);
    }
    
    /**
     * Итеративная версия сортировки слиянием
     */
    public static void iterativeMergeSort(int[] arr) {
        int n = arr.length;
        
        System.out.println("\nИтеративная версия:");
        
        // Размер текущего подмассива
        for (int currSize = 1; currSize < n; currSize = 2 * currSize) {
            System.out.println("Размер блока: " + currSize);
            
            for (int leftStart = 0; leftStart < n - 1; leftStart += 2 * currSize) {
                int mid = Math.min(leftStart + currSize - 1, n - 1);
                int rightEnd = Math.min(leftStart + 2 * currSize - 1, n - 1);
                
                System.out.println("  Слияние: [" + leftStart + "-" + mid + "] и [" + (mid + 1) + "-" + rightEnd + "]");
                merge(arr, leftStart, mid, rightEnd);
            }
        }
    }
    
    /**
     * Вспомогательный метод для форматированного вывода подмассива
     */
    public static String arrayToString(int[] arr, int start, int end) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = start; i <= end; i++) {
            sb.append(arr[i]);
            if (i < end) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
    
    /**
     * Сортировка слиянием для строк
     */
    public static void mergeSortStrings(String[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            
            mergeSortStrings(arr, left, mid);
            mergeSortStrings(arr, mid + 1, right);
            
            // Слияние для строк
            mergeStrings(arr, left, mid, right);
        }
    }
    
    private static void mergeStrings(String[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        String[] leftArr = new String[n1];
        String[] rightArr = new String[n2];
        
        for (int i = 0; i < n1; i++)
            leftArr[i] = arr[left + i];
        for (int j = 0; j < n2; j++)
            rightArr[j] = arr[mid + 1 + j];
        
        int i = 0, j = 0, k = left;
        
        while (i < n1 && j < n2) {
            if (leftArr[i].compareTo(rightArr[j]) <= 0) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }
        
        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }
        
        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }
    
    /**
     * Главный метод для тестирования
     */
    public static void main(String[] args) {
        System.out.println("СОРТИРОВКА СЛИЯНИЕМ (MERGE SORT) НА JAVA");
        System.out.println("=======================================");
        
        // Пример 1: Рекурсивная сортировка с подробным выводом
        int[] arr1 = {38, 27, 43, 3, 9, 82, 10};
        System.out.println("\nПример 1 - Рекурсивная версия:");
        mergeSort(arr1);
        System.out.println("\nФинальный результат: " + Arrays.toString(arr1));
        
        // Пример 2: Итеративная версия
        int[] arr2 = {12, 11, 13, 5, 6, 7};
        System.out.println("\nПример 2 - Итеративная версия:");
        System.out.println("Начальный массив: " + Arrays.toString(arr2));
        iterativeMergeSort(arr2);
        System.out.println("Финальный массив: " + Arrays.toString(arr2));
        
        // Пример 3: Сортировка строк
        String[] words = {"banana", "apple", "cherry", "date", "blueberry"};
        System.out.println("\nПример 3 - Сортировка строк:");
        System.out.println("Начальный массив: " + Arrays.toString(words));
        mergeSortStrings(words, 0, words.length - 1);
        System.out.println("Отсортированный массив: " + Arrays.toString(words));
        
        // Характеристики алгоритма
        System.out.println("\n--- ХАРАКТЕРИСТИКИ АЛГОРИТМА ---");
        System.out.println("• Временная сложность: O(n log n)");
        System.out.println("• Пространственная сложность: O(n)");
        System.out.println("• Стабильность: Да");
        System.out.println("• Параллелизуемость: Да");
        System.out.println("• Применимость: Отлично подходит для больших массивов");
    }
