import java.util.Arrays;

/**
 * Класс для демонстрации алгоритма пирамидальной сортировки
 * Сложность: O(n log n) во всех случаях
 */
public class Main {
    
    /**
     * Функция для поддержания свойства max-heap
     * 
     * @param arr - массив, представляющий кучу
     * @param n - размер кучи
     * @param i - индекс корневого элемента поддерева
     */
    public static void heapify(int[] arr, int n, int i, int depth) {
        int largest = i;        // Наибольший элемент как корень
        int left = 2 * i + 1;   // Левый дочерний
        int right = 2 * i + 2;  // Правый дочерний
        
        String indent = "  ".repeat(depth);
        System.out.println(indent + "heapify: корень=" + i + " (" + arr[i] + "), " +
                         "левый=" + (left < n ? arr[left] : "нет") + ", " +
                         "правый=" + (right < n ? arr[right] : "нет") + ")");
        
        // Сравниваем с левым дочерним элементом
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
            System.out.println(indent + "  левый больше -> largest = " + largest);
        }
        
        // Сравниваем с правым дочерним элементом
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
            System.out.println(indent + "  правый больше -> largest = " + largest);
        }
        
        // Если наибольший не корень
        if (largest != i) {
            System.out.println(indent + "  меняем " + arr[i] + " и " + arr[largest]);
            swap(arr, i, largest);
            
            // Рекурсивно heapify затронутое поддерево
            System.out.println(indent + "  рекурсивно heapify для " + largest);
            heapify(arr, n, largest, depth + 1);
        } else {
            System.out.println(indent + "  свойство кучи сохранено");
        }
    }
    
    /**
     * Основная функция пирамидальной сортировки
     */
    public static void heapSort(int[] arr) {
        int n = arr.length;
        
        System.out.println("ПИРАМИДАЛЬНАЯ СОРТИРОВКА (HEAP SORT)");
        System.out.println("Начальный массив: " + Arrays.toString(arr));
        System.out.println();
        
        // Фаза 1: Построение max-heap
        System.out.println("ФАЗА 1: Построение max-heap");
        System.out.println("============================");
        // Начинаем с последнего нелистового узла
        for (int i = n / 2 - 1; i >= 0; i--) {
            System.out.println("Обрабатываем узел " + i + " (" + arr[i] + "):");
            heapify(arr, n, i, 1);
            System.out.println("  Текущее состояние: " + Arrays.toString(arr));
        }
        
        System.out.println("\nMax-heap построен: " + Arrays.toString(arr));
        System.out.println();
        
        // Фаза 2: Извлечение элементов из кучи
        System.out.println("ФАЗА 2: Извлечение элементов из кучи");
        System.out.println("===================================");
        for (int i = n - 1; i > 0; i--) {
            // Перемещаем текущий корень в конец
            System.out.println("Шаг " + (n - i) + ": меняем корень " + arr[0] + 
                             " с последним элементом " + arr[i]);
            swap(arr, 0, i);
            
            System.out.println("  После обмена: " + Arrays.toString(arr));
            
            // Восстанавливаем кучу для корня
            System.out.println("  Восстанавливаем кучу для корня:");
            heapify(arr, i, 0, 1);
            
            // Выводим текущее состояние с разделением на отсортированную и неотсортированную части
            System.out.print("  Текущее состояние: [");
            for (int j = 0; j <= i; j++) {
                System.out.print(arr[j]);
                if (j < i) System.out.print(", ");
            }
            System.out.print("] | [");
            for (int j = i + 1; j < n; j++) {
                System.out.print(arr[j]);
                if (j < n - 1) System.out.print(", ");
            }
            System.out.println("] (отсортированная часть)");
            System.out.println();
        }
    }
    
    /**
     * Класс для реализации структуры данных "Куча"
     */
    static class MaxHeap {
        private int[] heap;
        private int size;
        private int capacity;
        
        public MaxHeap(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            this.heap = new int[capacity];
        }
        
        public MaxHeap(int[] arr) {
            this.capacity = arr.length;
            this.size = arr.length;
            this.heap = arr.clone();
            
            // Построение кучи из массива
            for (int i = size / 2 - 1; i >= 0; i--) {
                heapify(i);
            }
        }
        
        private void heapify(int i) {
            int largest = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            
            if (left < size && heap[left] > heap[largest]) {
                largest = left;
            }
            
            if (right < size && heap[right] > heap[largest]) {
                largest = right;
            }
            
            if (largest != i) {
                swap(heap, i, largest);
                heapify(largest);
            }
        }
        
        public void insert(int value) {
            if (size == capacity) {
                System.out.println("Куча переполнена!");
                return;
            }
            
            heap[size] = value;
            int i = size;
            size++;
            
            // Просеивание вверх
            while (i > 0 && heap[(i - 1) / 2] < heap[i]) {
                swap(heap, i, (i - 1) / 2);
                i = (i - 1) / 2;
            }
        }
        
        public int extractMax() {
            if (size == 0) {
                throw new IllegalStateException("Куча пуста!");
            }
            
            int max = heap[0];
            heap[0] = heap[size - 1];
            size--;
            
            if (size > 0) {
                heapify(0);
            }
            
            return max;
        }
        
        public void print() {
            System.out.print("Куча: ");
            for (int i = 0; i < size; i++) {
                System.out.print(heap[i] + " ");
            }
            System.out.println();
        }
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
     * Демонстрация работы структуры данных "Куча"
     */
    public static void demonstrateHeap() {
        System.out.println("\n=== ДЕМОНСТРАЦИЯ СТРУКТУРЫ ДАННЫХ 'КУЧА' ===");
        
        MaxHeap heap = new MaxHeap(10);
        
        System.out.println("Вставляем элементы в кучу:");
        int[] elements = {10, 20, 15, 30, 5};
        for (int elem : elements) {
            System.out.println("Вставляем " + elem);
            heap.insert(elem);
            heap.print();
        }
        
        System.out.println("\nИзвлекаем элементы из кучи:");
        while (true) {
            try {
                int max = heap.extractMax();
                System.out.println("Извлекли: " + max);
                heap.print();
            } catch (IllegalStateException e) {
                break;
            }
        }
    }
    
    /**
     * Главный метод для тестирования
     */
    public static void main(String[] args) {
        System.out.println("ПИРАМИДАЛЬНАЯ СОРТИРОВКА (HEAP SORT) НА JAVA");
        System.out.println("===========================================");
        
        // Пример 1: Основная демонстрация
        int[] arr1 = {12, 11, 13, 5, 6, 7};
        System.out.println("\nПример 1 - Основная демонстрация:");
        heapSort(arr1);
        System.out.println("Финальный результат: " + Arrays.toString(arr1));
        
        // Пример 2: Другой массив
        int[] arr2 = {4, 10, 3, 5, 1, 8, 2};
        System.out.println("\nПример 2 - Другой массив:");
        System.out.println("Начальный массив: " + Arrays.toString(arr2));
        heapSort(arr2);
        System.out.println("Финальный результат: " + Arrays.toString(arr2));
        
        // Демонстрация структуры данных "Куча"
        demonstrateHeap();
        
        // Характеристики алгоритма
        System.out.println("\n--- ХАРАКТЕРИСТИКИ АЛГОРИТМА ---");
        System.out.println("• Временная сложность: O(n log n)");
        System.out.println("• Пространственная сложность: O(1)");
        System.out.println("• Стабильность: Нет");
        System.out.println("• Естественность: Нет");
        System.out.println("• Преимущества: Гарантированная сложность O(n log n)");
    }
}
