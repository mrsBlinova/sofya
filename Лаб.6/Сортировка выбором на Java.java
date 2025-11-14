import java.util.Arrays;

/**
 * Класс для демонстрации алгоритма сортировки выбором
 * Сложность: O(n²) во всех случаях
 */
public class Main {
    
    /**
     * Метод сортировки выбором
     * На каждой итерации находит минимальный элемент в неотсортированной части
     * и меняет его с первым элементом этой части
     * 
     * @param arr - массив для сортировки
     */
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        
        // Внешний цикл: проходим по всем элементам, кроме последнего
        for (int i = 0; i < n - 1; i++) {
            // Предполагаем, что текущий элемент - минимальный
            int minIndex = i;
            
            // Внутренний цикл: ищем минимальный элемент в неотсортированной части
            for (int j = i + 1; j < n; j++) {
                // Если находим элемент меньше текущего минимального
                if (arr[j] < arr[minIndex]) {
                    minIndex = j; // Обновляем индекс минимального элемента
                }
            }
            
            // Меняем местами найденный минимальный элемент с текущим
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
            
            // Вывод промежуточного состояния (для наглядности)
            System.out.println("После итерации " + (i + 1) + ": " + Arrays.toString(arr));
        }
    }
    
    /**
     * Вспомогательный метод для вывода массива
     */
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    
    /**
     * Главный метод для тестирования алгоритма
     */
    public static void main(String[] args) {
        // Тестовый массив
        int[] arr = {64, 25, 12, 22, 11};
        
        System.out.println("Сортировка выбором (Selection Sort)");
        System.out.println("===================================");
        System.out.print("Исходный массив: ");
        printArray(arr);
        System.out.println();
        
        // Вызываем сортировку
        selectionSort(arr);
        
        System.out.print("\nФинальный отсортированный массив: ");
        printArray(arr);
        
        // Дополнительный пример
        System.out.println("\n--- Дополнительный пример ---");
        int[] arr2 = {5, 2, 8, 1, 9, 3};
        System.out.print("Исходный массив: ");
        printArray(arr2);
        
        selectionSort(arr2);
        System.out.print("Отсортированный массив: ");
        printArray(arr2);
    }
}
