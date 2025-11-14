import java.util.Arrays;

/**
 * Класс для демонстрации алгоритма сортировки пузырьком
 * Сложность: O(n²) в худшем случае, O(n) в лучшем случае (отсортированный массив)
 */
public class Main {
    
    /**
     * Метод сортировки пузырьком (Bubble Sort)
     * Алгоритм последовательно сравнивает и обменивает соседние элементы,
     * пока массив не будет полностью отсортирован
     * 
     * @param arr - массив для сортировки
     */
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        
        // Внешний цикл: количество проходов по массиву
        for (int i = 0; i < n - 1; i++) {
            // Флаг для оптимизации - отслеживаем, были ли обмены
            boolean swapped = false;
            
            // Внутренний цикл: проходим по неотсортированной части
            // После i проходов i наибольших элементов уже в конце массива
            for (int j = 0; j < n - i - 1; j++) {
                // Сравниваем текущий элемент со следующим
                if (arr[j] > arr[j + 1]) {
                    // Обмен элементов местами
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true; // Был произведен обмен
                }
            }
            
            // Вывод промежуточного состояния
            System.out.println("После прохода " + (i + 1) + ": " + 
                             Arrays.toString(arr) + 
                             (swapped ? "" : " (без обменов)"));
            
            // Если не было обменов - массив отсортирован, выходим
            if (!swapped) {
                System.out.println("✓ Массив отсортирован досрочно!");
                break;
            }
        }
    }
    
    /**
     * Альтернативная версия с подробным выводом сравнений
     */
    public static void bubbleSortDetailed(int[] arr) {
        int n = arr.length;
        int comparisons = 0;
        int swaps = 0;
        
        System.out.println("\n--- Подробный процесс сортировки ---");
        
        for (int i = 0; i < n - 1; i++) {
            System.out.println("\nПроход " + (i + 1) + ":");
            boolean swapped = false;
            
            for (int j = 0; j < n - i - 1; j++) {
                comparisons++;
                System.out.print("  Сравниваем " + arr[j] + " и " + arr[j + 1]);
                
                if (arr[j] > arr[j + 1]) {
                    // Обмен элементов
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                    swaps++;
                    System.out.println(" -> МЕНЯЕМ местами");
                } else {
                    System.out.println(" -> порядок верный");
                }
            }
            
            System.out.println("  Состояние: " + Arrays.toString(arr));
            
            if (!swapped) break;
        }
        
        System.out.println("\nИтог: сравнений = " + comparisons + ", обменов = " + swaps);
    }
    
    /**
     * Вспомогательный метод для вывода массива
     */
    public static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
    
    /**
     * Главный метод для тестирования алгоритма
     */
    public static void main(String[] args) {
        System.out.println("СОРТИРОВКА ПУЗЫРЬКОМ (BUBBLE SORT)");
        System.out.println("=================================");
        
        // Пример 1: Обычная сортировка
        int[] arr1 = {5, 2, 8, 1, 9, 3};
        System.out.println("\nПример 1 - Обычная сортировка:");
        System.out.println("Исходный массив: " + Arrays.toString(arr1));
        
        bubbleSort(arr1);
        System.out.println("Результат: " + Arrays.toString(arr1));
        
        // Пример 2: Подробная демонстрация
        int[] arr2 = {4, 2, 1, 5, 3};
        System.out.println("\nПример 2 - Подробная демонстрация:");
        System.out.println("Исходный массив: " + Arrays.toString(arr2));
        
        bubbleSortDetailed(arr2);
        System.out.println("Результат: " + Arrays.toString(arr2));
        
        // Пример 3: Уже отсортированный массив (лучший случай)
        int[] arr3 = {1, 2, 3, 4, 5};
        System.out.println("\nПример 3 - Отсортированный массив (лучший случай):");
        System.out.println("Исходный массив: " + Arrays.toString(arr3));
        
        bubbleSort(arr3);
        System.out.println("Результат: " + Arrays.toString(arr3));
        
        // Пример 4: Массив в обратном порядке (худший случай)
        int[] arr4 = {5, 4, 3, 2, 1};
        System.out.println("\nПример 4 - Обратный порядок (худший случай):");
        System.out.println("Исходный массив: " + Arrays.toString(arr4));
        
        bubbleSort(arr4);
        System.out.println("Результат: " + Arrays.toString(arr4));
    }
}
