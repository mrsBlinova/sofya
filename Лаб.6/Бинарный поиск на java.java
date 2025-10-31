import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Класс для демонстрации алгоритма бинарного поиска
 * Сложность: O(log n)
 */
public class Main {
    
    /**
     * Итеративная версия бинарного поиска
     * 
     * @param arr - отсортированный массив для поиска
     * @param target - искомое значение
     * @return индекс найденного элемента или -1 если не найден
     */
    public static int binarySearchIterative(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int step = 1;
        
        System.out.println("Бинарный поиск (итеративный) элемента " + target + ":");
        System.out.println("Отсортированный массив: " + Arrays.toString(arr));
        System.out.println();
        
        while (left <= right) {
            int mid = left + (right - left) / 2; // Предотвращает переполнение
            
            System.out.println("Шаг " + step++ + ":");
            System.out.println("  left = " + left + " (" + arr[left] + "), " +
                             "right = " + right + " (" + arr[right] + "), " +
                             "mid = " + mid + " (" + arr[mid] + ")");
            
            // Проверяем средний элемент
            if (arr[mid] == target) {
                System.out.println("  arr[" + mid + "] = " + arr[mid] + " == " + target + 
                                 " -> ЭЛЕМЕНТ НАЙДЕН!");
                return mid;
            }
            
            // Если target меньше, ищем в левой половине
            if (arr[mid] > target) {
                System.out.println("  arr[" + mid + "] = " + arr[mid] + " > " + target + 
                                 " -> ищем в ЛЕВОЙ половине");
                right = mid - 1;
            } 
            // Если target больше, ищем в правой половине
            else {
                System.out.println("  arr[" + mid + "] = " + arr[mid] + " < " + target + 
                                 " -> ищем в ПРАВОЙ половине");
                left = mid + 1;
            }
            
            System.out.println("  Новые границы: left = " + left + ", right = " + right);
        }
        
        System.out.println("  Элемент " + target + " не найден в массиве");
        return -1;
    }
    
    /**
     * Рекурсивная версия бинарного поиска
     */
    public static int binarySearchRecursive(int[] arr, int target, int left, int right, int depth) {
        String indent = "  ".repeat(depth);
        
        System.out.println(indent + "binarySearch(" + left + ", " + right + ")");
        
        if (left > right) {
            System.out.println(indent + "  Базовый случай: границы пересеклись");
            return -1;
        }
        
        int mid = left + (right - left) / 2;
        
        System.out.println(indent + "  mid = " + mid + " (" + arr[mid] + ")");
        
        if (arr[mid] == target) {
            System.out.println(indent + "  arr[" + mid + "] = " + arr[mid] + " == " + target + 
                             " -> НАЙДЕН!");
            return mid;
        }
        
        if (arr[mid] > target) {
            System.out.println(indent + "  arr[" + mid + "] = " + arr[mid] + " > " + target + 
                             " -> идем влево");
            return binarySearchRecursive(arr, target, left, mid - 1, depth + 1);
        } else {
            System.out.println(indent + "  arr[" + mid + "] = " + arr[mid] + " < " + target + 
                             " -> идем вправо");
            return binarySearchRecursive(arr, target, mid + 1, right, depth + 1);
        }
    }
    
    /**
     * Бинарный поиск с использованием Arrays.binarySearch()
     */
    public static void demonstrateBuiltInBinarySearch(int[] arr, int target) {
        System.out.println("Бинарный поиск с использованием Arrays.binarySearch():");
        
        int result = Arrays.binarySearch(arr, target);
        
        if (result >= 0) {
            System.out.println("  Элемент " + target + " найден на позиции: " + result);
        } else {
            System.out.println("  Элемент " + target + " не найден");
            // Возвращает (-(insertion point) - 1)
            int insertionPoint = -result - 1;
            System.out.println("  Точка вставки: " + insertionPoint);
        }
    }
    
    /**
     * Бинарный поиск для нахождения первого вхождения
     */
    public static int findFirstOccurrence(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int result = -1;
        
        System.out.println("Поиск первого вхождения элемента " + target + ":");
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            System.out.println("  left=" + left + ", right=" + right + ", mid=" + mid + 
                             " (arr[mid]=" + arr[mid] + ")");
            
            if (arr[mid] == target) {
                result = mid;
                System.out.println("    Нашли на позиции " + mid + ", продолжаем поиск слева");
                right = mid - 1;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        if (result != -1) {
            System.out.println("  Первое вождение на позиции: " + result);
        } else {
            System.out.println("  Элемент не найден");
        }
        
        return result;
    }
    
    /**
     * Бинарный поиск для нахождения последнего вхождения
     */
    public static int findLastOccurrence(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int result = -1;
        
        System.out.println("Поиск последнего вхождения элемента " + target + ":");
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            System.out.println("  left=" + left + ", right=" + right + ", mid=" + mid + 
                             " (arr[mid]=" + arr[mid] + ")");
            
            if (arr[mid] == target) {
                result = mid;
                System.out.println("    Нашли на позиции " + mid + ", продолжаем поиск справа");
                left = mid + 1;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        if (result != -1) {
            System.out.println("  Последнее вождение на позиции: " + result);
        } else {
            System.out.println("  Элемент не найден");
        }
        
        return result;
    }
    
    /**
     * Бинарный поиск для строк
     */
    public static int binarySearchString(String[] arr, String target) {
        int left = 0;
        int right = arr.length - 1;
        
        System.out.println("Бинарный поиск строки \"" + target + "\":");
        System.out.println("Массив: " + Arrays.toString(arr));
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = arr[mid].compareTo(target);
            
            System.out.println("  mid=" + mid + " (\"" + arr[mid] + "\"), compareTo=" + comparison);
            
            if (comparison == 0) {
                System.out.println("  Строка найдена на позиции: " + mid);
                return mid;
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        System.out.println("  Строка не найдена");
        return -1;
    }
    
    /**
     * Демонстрация работы алгоритма
     */
    public static void demonstrateBinarySearch() {
        System.out.println("БИНАРНЫЙ ПОИСК (BINARY SEARCH) НА JAVA");
        System.out.println("=====================================");
        
        // Пример 1: Основной пример
        int[] arr1 = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        int target1 = 13;
        
        System.out.println("\nПример 1 - Основной пример:");
        binarySearchIterative(arr1, target1);
        
        // Пример 2: Рекурсивный поиск
        System.out.println("\nПример 2 - Рекурсивный поиск:");
        System.out.println("Поиск элемента 7:");
        binarySearchRecursive(arr1, 7, 0, arr1.length - 1, 0);
        
        // Пример 3: Встроенный метод Arrays.binarySearch
        System.out.println("\nПример 3 - Встроенный метод:");
        demonstrateBuiltInBinarySearch(arr1, 11);
        demonstrateBuiltInBinarySearch(arr1, 8); // Несуществующий элемент
        
        // Пример 4: Поиск в массиве с дубликатами
        int[] arrWithDuplicates = {1, 2, 2, 2, 3, 4, 4, 5, 5, 5, 5, 6};
        System.out.println("\nПример 4 - Массив с дубликатами:");
        System.out.println("Массив: " + Arrays.toString(arrWithDuplicates));
        
        findFirstOccurrence(arrWithDuplicates, 2);
        findLastOccurrence(arrWithDuplicates, 5);
        
        // Пример 5: Поиск строк
        String[] words = {"apple", "banana", "cherry", "date", "elderberry"};
        System.out.println("\nПример 5 - Поиск строк:");
        binarySearchString(words, "cherry");
        
        // Пример 6: Использование Collections.binarySearch
        System.out.println("\nПример 6 - Поиск в List:");
        List<Integer> list = Arrays.asList(1, 3, 5, 7, 9, 11, 13);
        int index = Collections.binarySearch(list, 7);
        System.out.println("Элемент 7 найден на позиции: " + index);
    }
    
    /**
     * Сравнение с последовательным поиском
     */
    public static void compareWithSequentialSearch() {
        System.out.println("\n=== СРАВНЕНИЕ С ПОСЛЕДОВАТЕЛЬНЫМ ПОИСКОМ ===");
        
        int[] largeArray = new int[1000000];
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = i * 2;
        }
        int target = 999998;
        
        System.out.println("Размер массива: " + largeArray.length);
        System.out.println("Ищем элемент: " + target);
        
        // Бинарный поиск
        long startTime = System.nanoTime();
        int result = Arrays.binarySearch(largeArray, target);
        long endTime = System.nanoTime();
        System.out.println("Бинарный поиск: " + (endTime - startTime) + " наносекунд");
        
        // Последовательный поиск
        startTime = System.nanoTime();
        for (int i = 0; i < largeArray.length; i++) {
            if (largeArray[i] == target) {
                result = i;
                break;
            }
        }
        endTime = System.nanoTime();
        System.out.println("Последовательный поиск: " + (endTime - startTime) + " наносекунд");
    }
    
    /**
     * Главный метод для тестирования
     */
    public static void main(String[] args) {
        demonstrateBinarySearch();
        compareWithSequentialSearch();
        
        // Характеристики алгоритма
        System.out.println("\n--- ХАРАКТЕРИСТИКИ АЛГОРИТМА ---");
        System.out.println("• Временная сложность: O(log n)");
        System.out.println("• Пространственная сложность: O(1) - итеративный, O(log n) - рекурсивный");
        System.out.println("• Преимущества:");
        System.out.println("  - Очень быстрый для больших массивов");
        System.out.println("  - Эффективное использование памяти");
        System.out.println("• Недостатки:");
        System.out.println("  - Требует отсортированный массив");
        System.out.println("  - Сложнее в реализации чем последовательный поиск");
        System.out.println("• Применение:");
        System.out.println("  - Большие отсортированные массивы");
        System.out.println("  - Частые поисковые операции");
        System.out.println("  - Базы данных и файловые системы");
    }
}
