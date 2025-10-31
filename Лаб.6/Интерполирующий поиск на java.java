import java.util.Arrays;

/**
 * Класс для демонстрации алгоритма интерполирующего поиска
 * Сложность: O(log log n) в лучшем случае, O(n) в худшем случае
 */
public class Main {
    
    /**
     * Интерполирующий поиск (Interpolation Search)
     * 
     * @param arr - отсортированный массив для поиска
     * @param target - искомое значение
     * @return индекс найденного элемента или -1 если не найден
     */
    public static int interpolationSearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        int step = 1;
        
        System.out.println("Интерполирующий поиск элемента " + target + ":");
        System.out.println("Отсортированный массив: " + Arrays.toString(arr));
        System.out.println();
        
        // Проверяем, что target находится в диапазоне массива
        if (target < arr[low] || target > arr[high]) {
            System.out.println("Элемент " + target + " вне диапазона массива [" 
                             + arr[low] + ", " + arr[high] + "]");
            return -1;
        }
        
        while (low <= high && target >= arr[low] && target <= arr[high]) {
            // Если остался один элемент
            if (low == high) {
                System.out.println("Шаг " + step + ": остался один элемент");
                if (arr[low] == target) {
                    System.out.println("  arr[" + low + "] = " + arr[low] + " == " + target 
                                     + " -> ЭЛЕМЕНТ НАЙДЕН!");
                    return low;
                }
                break;
            }
            
            // Формула интерполяции для предсказания позиции
            // pos = low + ((target - arr[low]) * (high - low)) / (arr[high] - arr[low])
            int pos = low + (int)(((double)(target - arr[low]) * (high - low)) / (arr[high] - arr[low]));
            
            // Обеспечиваем, чтобы pos оставался в границах
            pos = Math.max(low, Math.min(pos, high));
            
            System.out.println("Шаг " + step++ + ":");
            System.out.println("  low = " + low + " (" + arr[low] + "), "
                             + "high = " + high + " (" + arr[high] + ")");
            System.out.println("  Формула: pos = " + low + " + ((" + target + " - " + arr[low] 
                             + ") * (" + high + " - " + low + ")) / (" + arr[high] + " - " + arr[low] + ")");
            System.out.println("  Вычисленная позиция: " + pos + " (arr[" + pos + "] = " + arr[pos] + ")");
            
            // Проверяем элемент на вычисленной позиции
            if (arr[pos] == target) {
                System.out.println("  arr[" + pos + "] = " + arr[pos] + " == " + target 
                                 + " -> ЭЛЕМЕНТ НАЙДЕН!");
                return pos;
            }
            
            // Если target меньше, ищем в левой части
            if (arr[pos] > target) {
                System.out.println("  arr[" + pos + "] = " + arr[pos] + " > " + target 
                                 + " -> ищем в ЛЕВОЙ части");
                high = pos - 1;
            } 
            // Если target больше, ищем в правой части
            else {
                System.out.println("  arr[" + pos + "] = " + arr[pos] + " < " + target 
                                 + " -> ищем в ПРАВОЙ части");
                low = pos + 1;
            }
            
            System.out.println("  Новые границы: low = " + low + ", high = " + high);
        }
        
        System.out.println("Элемент " + target + " не найден в массиве");
        return -1;
    }
    
    /**
     * Рекурсивная версия интерполирующего поиска
     */
    public static int interpolationSearchRecursive(int[] arr, int target, int low, int high, int depth) {
        String indent = "  ".repeat(depth);
        
        System.out.println(indent + "interpolationSearch(" + low + ", " + high + ")");
        
        // Проверка базовых случаев
        if (low > high || target < arr[low] || target > arr[high]) {
            System.out.println(indent + "  Базовый случай: элемент вне границ");
            return -1;
        }
        
        // Формула интерполяции
        int pos = low + (int)(((double)(target - arr[low]) * (high - low)) / (arr[high] - arr[low]));
        pos = Math.max(low, Math.min(pos, high));
        
        System.out.println(indent + "  pos = " + pos + " (arr[" + pos + "] = " + arr[pos] + ")");
        
        if (arr[pos] == target) {
            System.out.println(indent + "  arr[" + pos + "] = " + arr[pos] + " == " + target 
                             + " -> НАЙДЕН!");
            return pos;
        }
        
        if (arr[pos] > target) {
            System.out.println(indent + "  arr[" + pos + "] = " + arr[pos] + " > " + target 
                             + " -> идем влево");
            return interpolationSearchRecursive(arr, target, low, pos - 1, depth + 1);
        } else {
            System.out.println(indent + "  arr[" + pos + "] = " + arr[pos] + " < " + target 
                             + " -> идем вправо");
            return interpolationSearchRecursive(arr, target, pos + 1, high, depth + 1);
        }
    }
    
    /**
     * Сравнение производительности с бинарным поиском
     */
    public static void comparePerformance() {
        System.out.println("\n=== СРАВНЕНИЕ ПРОИЗВОДИТЕЛЬНОСТИ ===");
        
        // Создаем большой массив с равномерным распределением
        int size = 1000000;
        int[] uniformArray = new int[size];
        for (int i = 0; i < size; i++) {
            uniformArray[i] = i * 10; // 0, 10, 20, ..., 9999990
        }
        
        int target = 750000;
        
        System.out.println("Размер массива: " + size);
        System.out.println("Ищем элемент: " + target);
        
        // Интерполирующий поиск
        long startTime = System.nanoTime();
        int result1 = interpolationSearch(uniformArray, target);
        long endTime = System.nanoTime();
        System.out.println("Интерполирующий поиск: " + (endTime - startTime) + " наносекунд");
        
        // Бинарный поиск
        startTime = System.nanoTime();
        int result2 = Arrays.binarySearch(uniformArray, target);
        endTime = System.nanoTime();
        System.out.println("Бинарный поиск: " + (endTime - startTime) + " наносекунд");
    }
    
    /**
     * Демонстрация на разных типах распределений
     */
    public static void demonstrateDifferentDistributions() {
        System.out.println("\n=== РАЗНЫЕ ТИПЫ РАСПРЕДЕЛЕНИЙ ===");
        
        // 1. Идеальное равномерное распределение
        System.out.println("1. Идеальное равномерное распределение:");
        int[] perfectUniform = {0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        interpolationSearch(perfectUniform, 45);
        
        // 2. Неравномерное распределение
        System.out.println("\n2. Неравномерное распределение:");
        int[] nonUniform = {1, 2, 3, 100, 101, 102, 1000, 1001, 10000};
        interpolationSearch(nonUniform, 101);
        
        // 3. Экспоненциальное распределение
        System.out.println("\n3. Экспоненциальное распределение:");
        int[] exponential = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024};
        interpolationSearch(exponential, 64);
    }
    
    /**
     * Поиск в массиве объектов с использованием интерфейса Comparable
     */
    public static <T extends Comparable<T>> int interpolationSearchGeneric(T[] arr, T target) {
        int low = 0;
        int high = arr.length - 1;
        
        System.out.println("Универсальный интерполирующий поиск: " + target);
        
        while (low <= high && target.compareTo(arr[low]) >= 0 && target.compareTo(arr[high]) <= 0) {
            if (low == high) {
                if (arr[low].compareTo(target) == 0) return low;
                return -1;
            }
            
            // Для универсальных типов используем приблизительную интерполяцию
            // на основе порядковых номеров
            double lowValue = low;
            double highValue = high;
            double targetValue = low + (high - low) / 2.0; // Упрощенная формула
            
            int pos = low + (int)(((targetValue - lowValue) * (high - low)) / (highValue - lowValue));
            pos = Math.max(low, Math.min(pos, high));
            
            int comparison = arr[pos].compareTo(target);
            
            if (comparison == 0) {
                return pos;
            } else if (comparison > 0) {
                high = pos - 1;
            } else {
                low = pos + 1;
            }
        }
        
        return -1;
    }
    
    /**
     * Главный метод для тестирования
     */
    public static void main(String[] args) {
        System.out.println("ИНТЕРПОЛИРУЮЩИЙ ПОИСК (INTERPOLATION SEARCH) НА JAVA");
        System.out.println("===================================================");
        
        // Пример 1: Основной пример
        int[] arr1 = {10, 12, 13, 16, 18, 19, 20, 21, 22, 23, 24, 33, 35, 42, 47};
        int target1 = 18;
        
        System.out.println("\nПример 1 - Основной пример:");
        interpolationSearch(arr1, target1);
        
        // Пример 2: Рекурсивная версия
        System.out.println("\nПример 2 - Рекурсивная версия:");
        System.out.println("Поиск элемента 33:");
        interpolationSearchRecursive(arr1, 33, 0, arr1.length - 1, 0);
        
        // Пример 3: Поиск несуществующего элемента
        System.out.println("\nПример 3 - Поиск несуществующего элемента:");
        interpolationSearch(arr1, 30);
        
        // Пример 4: Сравнение производительности
        comparePerformance();
        
        // Пример 5: Разные типы распределений
        demonstrateDifferentDistributions();
        
        // Пример 6: Универсальный поиск
        System.out.println("\nПример 6 - Универсальный поиск:");
        Integer[] intArray = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        int result = interpolationSearchGeneric(intArray, 50);
        System.out.println("Результат универсального поиска: " + result);
        
        // Характеристики алгоритма
        System.out.println("\n--- ХАРАКТЕРИСТИКИ АЛГОРИТМА ---");
        System.out.println("• Лучший случай: O(log log n) - равномерное распределение");
        System.out.println("• Средний случай: O(log log n)");
        System.out.println("• Худший случай: O(n) - неравномерное распределение");
        System.out.println("• Пространственная сложность: O(1)");
        System.out.println("• Преимущества:");
        System.out.println("  - Очень быстрый для равномерно распределенных данных");
        System.out.println("  - Лучше бинарного поиска для равномерных данных");
        System.out.println("• Недостатки:");
        System.out.println("  - Медленный для неравномерных данных");
        System.out.println("  - Требует вычислений с плавающей точкой");
        System.out.println("• Применение:");
        System.out.println("  - Телефонные справочники");
        System.out.println("  - Равномерно распределенные данные");
        System.out.println("  - Когда известно распределение данных");
    }
}
