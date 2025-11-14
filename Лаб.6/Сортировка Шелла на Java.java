import java.util.Arrays;

/**
 * Класс для демонстрации алгоритма сортировки Шелла
 * Сложность: от O(n log²n) до O(n²) в зависимости от последовательности промежутков
 */
public class Main {
    
    /**
     * Сортировка Шелла с последовательностью Кнута
     * 
     * @param arr - массив для сортировки
     */
    public static void shellSort(int[] arr) {
        int n = arr.length;
        
        System.out.println("Начальный массив: " + Arrays.toString(arr));
        System.out.println();
        
        // Генерация последовательности Кнута: 1, 4, 13, 40, 121, ...
        int gap = 1;
        while (gap < n / 3) {
            gap = gap * 3 + 1;
        }
        
        System.out.print("Используемая последовательность промежутков: ");
        // Сохраняем все промежутки для вывода
        java.util.ArrayList<Integer> gaps = new java.util.ArrayList<>();
        while (gap > 0) {
            gaps.add(gap);
            gap = (gap - 1) / 3;
        }
        
        // Выводим промежутки в правильном порядке
        for (int i = gaps.size() - 1; i >= 0; i--) {
            System.out.print(gaps.get(i) + " ");
        }
        System.out.println("\n");
        
        // Проходим по всем промежуткам от большего к меньшему
        for (int g = gaps.size() - 1; g >= 0; g--) {
            gap = gaps.get(g);
            
            System.out.println("Промежуток (gap) = " + gap + ":");
            
            // Применяем модифицированную сортировку вставками
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j;
                
                System.out.println("  Обрабатываем элемент arr[" + i + "] = " + temp);
                
                // Сдвигаем элементы, которые больше temp
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    System.out.println("    Сдвигаем arr[" + (j - gap) + "] = " + 
                                     arr[j - gap] + " -> arr[" + j + "]");
                    arr[j] = arr[j - gap];
                }
                
                // Вставляем temp на найденную позицию
                if (j != i) {
                    System.out.println("    Вставляем " + temp + " -> arr[" + j + "]");
                } else {
                    System.out.println("    Элемент уже на правильной позиции");
                }
                arr[j] = temp;
                
                System.out.println("    Текущее состояние: " + Arrays.toString(arr));
            }
            System.out.println();
        }
    }
    
    /**
     * Упрощенная версия с последовательностью n/2, n/4, ..., 1
     */
    public static void shellSortSimple(int[] arr) {
        int n = arr.length;
        
        System.out.println("Упрощенная версия (последовательность n/2, n/4, ...):");
        
        // Начинаем с большого промежутка и уменьшаем его
        for (int gap = n / 2; gap > 0; gap /= 2) {
            System.out.println("Промежуток (gap) = " + gap + ":");
            
            // Применяем сортировку вставками для этого промежутка
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j;
                
                // Сдвигаем элементы, которые больше temp
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                
                arr[j] = temp;
            }
            
            System.out.println("  Состояние: " + Arrays.toString(arr));
        }
    }
    
    /**
     * Сортировка Шелла с последовательностью Седжвика (оптимальная)
     */
    public static void shellSortSedgewick(int[] arr) {
        int n = arr.length;
        
        System.out.println("Версия с последовательностью Седжвика:");
        
        // Генерация последовательности Седжвика
        java.util.ArrayList<Integer> gaps = new java.util.ArrayList<>();
        int k = 0;
        int gap;
        
        do {
            if (k % 2 == 0) {
                gap = (int)(9 * (Math.pow(2, k) - Math.pow(2, k / 2)) + 1);
            } else {
                gap = (int)(8 * Math.pow(2, k) - 6 * Math.pow(2, (k + 1) / 2) + 1);
            }
            
            if (gap < n) {
                gaps.add(gap);
            }
            k++;
        } while (gap < n);
        
        System.out.print("Последовательность Седжвика: ");
        for (int i = gaps.size() - 1; i >= 0; i--) {
            System.out.print(gaps.get(i) + " ");
        }
        System.out.println();
        
        // Применяем сортировку с полученными промежутками
        for (int g = gaps.size() - 1; g >= 0; g--) {
            gap = gaps.get(g);
            
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j;
                
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                
                arr[j] = temp;
            }
            
            System.out.println("  Gap " + gap + ": " + Arrays.toString(arr));
        }
    }
    
    /**
     * Вспомогательный метод для сравнения производительности
     */
    public static void compareShellSortVersions() {
        int[] arr1 = {23, 12, 45, 2, 67, 34, 89, 5, 78, 15, 43, 21, 8, 56, 33};
        int[] arr2 = arr1.clone();
        int[] arr3 = arr1.clone();
        
        System.out.println("\n=== СРАВНЕНИЕ ВЕРСИЙ СОРТИРОВКИ ШЕЛЛА ===");
        System.out.println("Исходный массив: " + Arrays.toString(arr1));
        
        System.out.println("\n1. Упрощенная версия (n/2):");
        shellSortSimple(arr1);
        
        System.out.println("\n2. Последовательность Кнута:");
        shellSort(arr2);
        
        System.out.println("\n3. Последовательность Седжвика:");
        shellSortSedgewick(arr3);
    }
    
    /**
     * Главный метод для тестирования
     */
    public static void main(String[] args) {
        System.out.println("СОРТИРОВКА ШЕЛЛА (SHELL SORT) НА JAVA");
        System.out.println("====================================");
        
        // Пример 1: Подробная демонстрация
        int[] arr1 = {12, 34, 54, 2, 3, 8, 15, 22, 10};
        System.out.println("\nПример 1 - Подробная демонстрация:");
        shellSort(arr1);
        System.out.println("Финальный результат: " + Arrays.toString(arr1));
        
        // Пример 2: Упрощенная версия
        int[] arr2 = {64, 34, 25, 12, 22, 11, 90, 5};
        System.out.println("\nПример 2 - Упрощенная версия:");
        System.out.println("Начальный массив: " + Arrays.toString(arr2));
        shellSortSimple(arr2);
        System.out.println("Финальный результат: " + Arrays.toString(arr2));
        
        // Сравнение разных версий
        compareShellSortVersions();
        
        // Характеристики алгоритма
        System.out.println("\n--- ХАРАКТЕРИСТИКИ АЛГОРИТМА ---");
        System.out.println("• Сложность: от O(n log²n) до O(n²)");
        System.out.println("• Пространственная сложность: O(1)");
        System.out.println("• Стабильность: Нет");
        System.out.println("• Естественность: Да (улучшает частичную отсортированность)");
        System.out.println("• Преимущества: Быстрее простых квадратичных алгоритмов");
    }
}
