import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Класс для демонстрации алгоритма поиска Фибоначчи
 * Сложность: O(log n)
 */
public class Main {
    
    /**
     * Генерация чисел Фибоначчи до числа >= n
     */
    public static List<Integer> generateFibonacciNumbers(int n) {
        List<Integer> fib = new ArrayList<>();
        if (n <= 0) return fib;
        
        fib.add(0);
        fib.add(1);
        
        // Генерируем числа Фибоначчи пока последнее число < n
        while (fib.get(fib.size() - 1) < n) {
            int next = fib.get(fib.size() - 1) + fib.get(fib.size() - 2);
            fib.add(next);
        }
        
        return fib;
    }
    
    /**
     * Поиск Фибоначчи (Fibonacci Search)
     * 
     * @param arr - отсортированный массив для поиска
     * @param target - искомое значение
     * @return индекс найденного элемента или -1 если не найден
     */
    public static int fibonacciSearch(int[] arr, int target) {
        int n = arr.length;
        int step = 1;
        
        System.out.println("Поиск Фибоначчи элемента " + target + ":");
        System.out.println("Отсортированный массив: " + Arrays.toString(arr));
        System.out.println();
        
        // Генерируем числа Фибоначчи
        List<Integer> fib = generateFibonacciNumbers(n);
        int k = fib.size() - 1; // Индекс наибольшего числа Фибоначчи
        
        System.out.print("Числа Фибоначчи: ");
        for (int num : fib) System.out.print(num + " ");
        System.out.println();
        System.out.println("k = " + k + " (F[" + k + "] = " + fib.get(k) + ")");
        System.out.println();
        
        // Инициализация переменных
        int offset = -1; // Смещение от начала массива
        
        // Пока есть элементы для поиска
        while (k > 0) {
            System.out.println("Шаг " + step++ + " (k = " + k + "):");
            
            // Вычисляем индекс для сравнения
            // i = min(offset + F[k-2], n-1)
            int i = Math.min(offset + fib.get(k - 2), n - 1);
            
            System.out.println("  i = min(offset + F[k-2], n-1) = min(" 
                             + offset + " + " + fib.get(k - 2) + ", " + (n - 1) + ") = " + i);
            System.out.println("  arr[" + i + "] = " + arr[i] + ", target = " + target);
            
            // Сравниваем элемент с целевым значением
            if (arr[i] < target) {
                // Перемещаемся в правую часть
                System.out.println("  arr[" + i + "] = " + arr[i] + " < " + target 
                                 + " -> ищем в ПРАВОЙ части");
                offset = i;
                k = k - 1; // Уменьшаем k для следующего шага
            } 
            else if (arr[i] > target) {
                // Перемещаемся в левую часть
                System.out.println("  arr[" + i + "] = " + arr[i] + " > " + target 
                                 + " -> ищем в ЛЕВОЙ части");
                k = k - 2; // Уменьшаем k на 2 для следующего шага
            } 
            else {
                // Элемент найден
                System.out.println("  arr[" + i + "] = " + arr[i] + " == " + target 
                                 + " -> ЭЛЕМЕНТ НАЙДЕН!");
                return i;
            }
            
            System.out.println("  Новые значения: offset = " + offset + ", k = " + k);
            
            // Проверяем последний элемент при k = 1
            if (k == 1 && offset + 1 < n && arr[offset + 1] == target) {
                System.out.println("  Проверка последнего элемента: arr[" + (offset + 1) + "] = " 
                                 + arr[offset + 1] + " == " + target + " -> НАЙДЕН!");
                return offset + 1;
            }
        }
        
        System.out.println("Элемент " + target + " не найден в массиве");
        return -1;
    }
    
    /**
     * Рекурсивная версия поиска Фибоначчи
     */
    public static int fibonacciSearchRecursive(int[] arr, int target, 
                                             List<Integer> fib, int k, int offset, int depth) {
        String indent = "  ".repeat(depth);
        
        System.out.println(indent + "fibonacciSearch(k=" + k + ", offset=" + offset + ")");
        
        if (k <= 0) {
            System.out.println(indent + "  Базовый случай: k <= 0");
            return -1;
        }
        
        // Вычисляем индекс для сравнения
        int i = Math.min(offset + fib.get(k - 2), arr.length - 1);
        
        System.out.println(indent + "  i = min(" + offset + " + " + fib.get(k - 2) + ", " 
                         + (arr.length - 1) + ") = " + i);
        System.out.println(indent + "  arr[" + i + "] = " + arr[i]);
        
        if (arr[i] == target) {
            System.out.println(indent + "  ЭЛЕМЕНТ НАЙДЕН!");
            return i;
        }
        else if (arr[i] < target) {
            System.out.println(indent + "  Ищем в правой части");
            return fibonacciSearchRecursive(arr, target, fib, k - 1, i, depth + 1);
        }
        else {
            System.out.println(indent + "  Ищем в левой части");
            return fibonacciSearchRecursive(arr, target, fib, k - 2, offset, depth + 1);
        }
    }
    
    /**
     * Сравнение производительности с бинарным поиском
     */
    public static void comparePerformance() {
        System.out.println("\n=== СРАВНЕНИЕ ПРОИЗВОДИТЕЛЬНОСТИ ===");
        
        // Создаем большой массив
        int size = 1000000;
        int[] largeArray = new int[size];
        for (int i = 0; i < size; i++) {
            largeArray[i] = i * 2; // Четные числа
        }
        
        int target = 750000;
        
        System.out.println("Размер массива: " + size);
        System.out.println("Ищем элемент: " + target);
        
        // Поиск Фибоначчи
        long startTime = System.nanoTime();
        int result1 = fibonacciSearch(largeArray, target);
        long endTime = System.nanoTime();
        System.out.println("Поиск Фибоначчи: " + (endTime - startTime) + " наносекунд");
        
        // Бинарный поиск
        startTime = System.nanoTime();
        int result2 = Arrays.binarySearch(largeArray, target);
        endTime = System.nanoTime();
        System.out.println("Бинарный поиск: " + (endTime - startTime) + " наносекунд");
    }
    
    /**
     * Демонстрация связи с золотым сечением
     */
    public static void demonstrateGoldenRatio() {
        System.out.println("\n=== СВЯЗЬ С ЗОЛОТЫМ СЕЧЕНИЕМ ===");
        
        List<Integer> fib = generateFibonacciNumbers(1000);
        System.out.println("Числа Фибоначчи и отношение F(n)/F(n-1):");
        
        for (int i = 2; i < fib.size(); i++) {
            double ratio = fib.get(i) / (double)fib.get(i - 1);
            System.out.printf("F[%d] = %d, F[%d]/F[%d] = %.6f%n", 
                            i, fib.get(i), i, i-1, ratio);
        }
        
        double goldenRatio = (1 + Math.sqrt(5)) / 2;
        System.out.printf("Золотое сечение: %.6f%n", goldenRatio);
        System.out.println("Поиск Фибоначчи делит массив в отношении, близком к золотому сечению");
    }
    
    /**
     * Универсальная версия для Comparable объектов
     */
    public static <T extends Comparable<T>> int fibonacciSearchGeneric(T[] arr, T target) {
        int n = arr.length;
        
        // Генерируем числа Фибоначчи
        List<Integer> fib = generateFibonacciNumbers(n);
        int k = fib.size() - 1;
        int offset = -1;
        
        while (k > 0) {
            int i = Math.min(offset + fib.get(k - 2), n - 1);
            
            int comparison = arr[i].compareTo(target);
            
            if (comparison < 0) {
                offset = i;
                k = k - 1;
            } 
            else if (comparison > 0) {
                k = k - 2;
            } 
            else {
                return i;
            }
        }
        
        if (k == 1 && offset + 1 < n && arr[offset + 1].compareTo(target) == 0) {
            return offset + 1;
        }
        
        return -1;
    }
    
    /**
     * Главный метод для тестирования
     */
    public static void main(String[] args) {
        System.out.println("ПОИСК ФИБОНАЧЧИ (FIBONACCI SEARCH) НА JAVA");
        System.out.println("==========================================");
        
        // Пример 1: Основной пример
        int[] arr1 = {10, 22, 35, 40, 45, 50, 80, 82, 85, 90, 100};
        int target1 = 85;
        
        System.out.println("\nПример 1 - Основной пример:");
        fibonacciSearch(arr1, target1);
        
        // Пример 2: Рекурсивная версия
        System.out.println("\nПример 2 - Рекурсивная версия:");
        List<Integer> fib = generateFibonacciNumbers(arr1.length);
        int k = fib.size() - 1;
        System.out.println("Поиск элемента 40:");
        fibonacciSearchRecursive(arr1, 40, fib, k, -1, 0);
        
        // Пример 3: Поиск несуществующего элемента
        System.out.println("\nПример 3 - Поиск несуществующего элемента:");
        fibonacciSearch(arr1, 75);
        
        // Пример 4: Сравнение производительности
        comparePerformance();
        
        // Пример 5: Связь с золотым сечением
        demonstrateGoldenRatio();
        
        // Пример 6: Универсальный поиск
        System.out.println("\nПример 6 - Универсальный поиск:");
        String[] strArray = {"apple", "banana", "cherry", "date", "elderberry"};
        int result = fibonacciSearchGeneric(strArray, "cherry");
        System.out.println("Результат универсального поиска: " + result);
        
        // Характеристики алгоритма
        System.out.println("\n--- ХАРАКТЕРИСТИКИ АЛГОРИТМА ---");
        System.out.println("• Временная сложность: O(log n)");
        System.out.println("• Пространственная сложность: O(1)");
        System.out.println("• Преимущества:");
        System.out.println("  - Использует только сложение и вычитание");
        System.out.println("  - Не требует деления");
        System.out.println("  - Хорош для больших массивов");
        System.out.println("• Недостатки:");
        System.out.println("  - Сложнее в реализации");
        System.out.println("  - Требует предварительной генерации чисел");
        System.out.println("• Особенности:");
        System.out.println("  - Основан на числах Фибоначчи");
        System.out.println("  - Связан с золотым сечением");
        System.out.println("  - Делит массив в оптимальных пропорциях");
    }
}
