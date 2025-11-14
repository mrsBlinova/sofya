import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Класс для демонстрации алгоритма последовательного поиска
 * Сложность: O(n) в худшем случае
 */
public class Main {
    
    /**
     * Функция последовательного (линейного) поиска
     * Проходит по всем элементам массива и сравнивает каждый с искомым значением
     * 
     * @param arr - массив для поиска
     * @param target - искомое значение
     * @return индекс найденного элемента или -1 если не найден
     */
    public static int sequentialSearch(int[] arr, int target) {
        System.out.println("Последовательный поиск элемента " + target + ":");
        
        // Проходим по всем элементам массива
        for (int i = 0; i < arr.length; i++) {
            System.out.print("  Проверяем элемент [" + i + "] = " + arr[i]);
            
            // Сравниваем текущий элемент с искомым
            if (arr[i] == target) {
                System.out.println(" -> НАЙДЕН!");
                return i; // Возвращаем индекс найденного элемента
            }
            System.out.println(" -> не совпадает");
        }
        
        System.out.println("  Элемент не найден в массиве");
        return -1; // Элемент не найден
    }
    
    /**
     * Последовательный поиск с подсчетом количества сравнений
     */
    public static int sequentialSearchWithCount(int[] arr, int target, int[] comparisons) {
        comparisons[0] = 0;
        
        for (int i = 0; i < arr.length; i++) {
            comparisons[0]++;
            if (arr[i] == target) {
                return i;
            }
        }
        
        return -1;
    }
    
    /**
     * Последовательный поиск для строк
     */
    public static int sequentialSearchString(String[] arr, String target) {
        System.out.println("Поиск строки \"" + target + "\":");
        
        for (int i = 0; i < arr.length; i++) {
            System.out.print("  Проверяем [" + i + "] = \"" + arr[i] + "\"");
            
            if (arr[i].equals(target)) {
                System.out.println(" -> НАЙДЕН!");
                return i;
            }
            System.out.println(" -> не совпадает");
        }
        
        System.out.println("  Строка не найдена");
        return -1;
    }
    
    /**
     * Функция поиска всех вхождений элемента
     */
    public static List<Integer> sequentialSearchAll(int[] arr, int target) {
        List<Integer> indices = new ArrayList<>();
        
        System.out.println("Поиск всех вхождений элемента " + target + ":");
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                indices.add(i);
                System.out.println("  Найден на позиции [" + i + "]");
            }
        }
        
        if (indices.isEmpty()) {
            System.out.println("  Элемент не найден");
        } else {
            System.out.println("  Всего найдено: " + indices.size() + " вхождений");
        }
        
        return indices;
    }
    
    /**
     * Универсальный последовательный поиск с использованием дженериков
     */
    public static <T> int sequentialSearchGeneric(T[] arr, T target) {
        System.out.println("Универсальный поиск элемента: " + target);
        
        for (int i = 0; i < arr.length; i++) {
            System.out.print("  [" + i + "] = " + arr[i]);
            
            if (arr[i].equals(target)) {
                System.out.println(" -> НАЙДЕН!");
                return i;
            }
            System.out.println(" -> нет");
        }
        
        System.out.println("  Элемент не найден");
        return -1;
    }
    
    /**
     * Поиск в ArrayList
     */
    public static <T> int sequentialSearchList(List<T> list, T target) {
        System.out.println("Поиск в ArrayList: " + target);
        
        for (int i = 0; i < list.size(); i++) {
            System.out.print("  [" + i + "] = " + list.get(i));
            
            if (list.get(i).equals(target)) {
                System.out.println(" -> НАЙДЕН!");
                return i;
            }
            System.out.println(" -> нет");
        }
        
        System.out.println("  Элемент не найден");
        return -1;
    }
    
    /**
     * Демонстрация работы алгоритма
     */
    public static void demonstrateSequentialSearch() {
        System.out.println("ПОСЛЕДОВАТЕЛЬНЫЙ ПОИСК (LINEAR SEARCH) НА JAVA");
        System.out.println("==============================================");
        
        // Пример 1: Поиск в массиве целых чисел
        int[] numbers = {3, 5, 2, 7, 9, 1, 4, 6, 8};
        int target = 7;
        
        System.out.println("\nПример 1 - Поиск в массиве целых чисел:");
        System.out.println("Массив: " + Arrays.toString(numbers));
        
        int result = sequentialSearch(numbers, target);
        if (result != -1) {
            System.out.println("✓ Элемент " + target + " найден на позиции: " + result);
        } else {
            System.out.println("✗ Элемент " + target + " не найден");
        }
        
        // Пример 2: Поиск всех вхождений
        int[] numbersWithDuplicates = {2, 5, 2, 8, 2, 9, 2, 1};
        target = 2;
        
        System.out.println("\nПример 2 - Поиск всех вхождений:");
        System.out.println("Массив: " + Arrays.toString(numbersWithDuplicates));
        
        List<Integer> allPositions = sequentialSearchAll(numbersWithDuplicates, target);
        
        // Пример 3: Поиск строк
        String[] words = {"apple", "banana", "cherry", "date", "elderberry"};
        String wordTarget = "cherry";
        
        System.out.println("\nПример 3 - Поиск строк:");
        System.out.println("Массив: " + Arrays.toString(words));
        
        result = sequentialSearchString(words, wordTarget);
        if (result != -1) {
            System.out.println("✓ Строка \"" + wordTarget + "\" найдена на позиции: " + result);
        }
        
        // Пример 4: Универсальный поиск с дженериками
        System.out.println("\nПример 4 - Универсальный поиск с дженериками:");
        Double[] doubles = {1.1, 2.2, 3.3, 4.4, 5.5};
        Double doubleTarget = 3.3;
        
        System.out.println("Массив Double: " + Arrays.toString(doubles));
        result = sequentialSearchGeneric(doubles, doubleTarget);
        
        // Пример 5: Поиск в ArrayList
        System.out.println("\nПример 5 - Поиск в ArrayList:");
        List<String> fruitList = Arrays.asList("apple", "banana", "cherry", "date");
        result = sequentialSearchList(fruitList, "banana");
        
        // Пример 6: Анализ производительности
        System.out.println("\nПример 6 - Анализ производительности:");
        int[] largeArray = new int[1000];
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = i + 1;
        }
        
        int[] comparisons = new int[1];
        
        // Лучший случай - элемент в начале
        sequentialSearchWithCount(largeArray, 1, comparisons);
        System.out.println("Лучший случай (первый элемент): " + comparisons[0] + " сравнений");
        
        // Средний случай - элемент в середине
        sequentialSearchWithCount(largeArray, 500, comparisons);
        System.out.println("Средний случай (середина): " + comparisons[0] + " сравнений");
        
        // Худший случай - элемент в конце
        sequentialSearchWithCount(largeArray, 1000, comparisons);
        System.out.println("Худший случай (последний элемент): " + comparisons[0] + " сравнений");
        
        // Неудачный поиск
        sequentialSearchWithCount(largeArray, 1001, comparisons);
        System.out.println("Элемент отсутствует: " + comparisons[0] + " сравнений");
    }
    
    /**
     * Сравнение с бинарным поиском (для демонстрации)
     */
    public static void compareWithBinarySearch() {
        System.out.println("\n=== СРАВНЕНИЕ С БИНАРНЫМ ПОИСКОМ ===");
        
        int[] sortedArray = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        int target = 13;
        
        System.out.println("Отсортированный массив: " + Arrays.toString(sortedArray));
        System.out.println("Ищем элемент: " + target);
        
        // Последовательный поиск
        System.out.println("\nПоследовательный поиск:");
        long startTime = System.nanoTime();
        int result = sequentialSearch(sortedArray, target);
        long endTime = System.nanoTime();
        System.out.println("Время выполнения: " + (endTime - startTime) + " наносекунд");
        
        // Бинарный поиск
        System.out.println("\nБинарный поиск:");
        startTime = System.nanoTime();
        result = Arrays.binarySearch(sortedArray, target);
        endTime = System.nanoTime();
        System.out.println("Время выполнения: " + (endTime - startTime) + " наносекунд");
    }
    
    /**
     * Главный метод для тестирования
     */
    public static void main(String[] args) {
        demonstrateSequentialSearch();
        compareWithBinarySearch();
        
        // Характеристики алгоритма
        System.out.println("\n--- ХАРАКТЕРИСТИКИ АЛГОРИТМА ---");
        System.out.println("• Временная сложность: O(n)");
        System.out.println("• Пространственная сложность: O(1)");
        System.out.println("• Преимущества:");
        System.out.println("  - Прост в реализации");
        System.out.println("  - Работает с несортированными массивами");
        System.out.println("  - Не требует дополнительной памяти");
        System.out.println("• Недостатки:");
        System.out.println("  - Медленный для больших массивов");
        System.out.println("  - Неэффективен для частых поисков");
        System.out.println("• Применение:");
        System.out.println("  - Небольшие массивы");
        System.out.println("  - Несортированные данные");
        System.out.println("  - Одноразовый поиск");
    }
}
