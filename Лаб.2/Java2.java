import java.util.ArrayList;
import java.util.List;

public class BinaryHeapWithDelete {
    private List<Integer> heap;
    
    public BinaryHeapWithDelete() {
        heap = new ArrayList<>();
    }
    
    // # Метод для вставки нового элемента в кучу
    public void insert(int value) {
        heap.add(value);
        heapifyUp(heap.size() - 1);
    }
    
    // # Метод для удаления корневого элемента (минимального)
    public int extractMin() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        
        // # 1. Сохраняем минимальный элемент (корень)
        int min = heap.get(0);
        
        // # 2. Заменяем корень последним элементом
        int lastElement = heap.remove(heap.size() - 1);
        
        // # 3. Если куча не пуста после удаления
        if (!heap.isEmpty()) {
            // # Помещаем последний элемент в корень
            heap.set(0, lastElement);
            // # 4. Восстанавливаем свойства кучи сверху вниз
            heapifyDown(0);
        }
        
        return min;
    }
    
    // # Вспомогательный метод для восстановления свойств кучи сверху вниз
    private void heapifyDown(int index) {
        int size = heap.size();
        
        // # Пока у текущего узла есть хотя бы один потомок
        while (index < size) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int smallest = index;
            
            // # 5. Находим наименьший элемент среди текущего узла и его потомков
            if (leftChild < size && heap.get(leftChild) < heap.get(smallest)) {
                smallest = leftChild;
            }
            
            if (rightChild < size && heap.get(rightChild) < heap.get(smallest)) {
                smallest = rightChild;
            }
            
            // # 6. Если текущий узел уже наименьший - выходим
            if (smallest == index) {
                break;
            }
            
            // # 7. Меняем местами с наименьшим потомком
            swap(index, smallest);
            // # 8. Переходим к позиции наименьшего потомка
            index = smallest;
        }
    }
    
    // # Вспомогательный метод для восстановления свойств кучи снизу вверх
    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap.get(index) < heap.get(parentIndex)) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }
    
    // # Вспомогательный метод для обмена элементов
    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
    
    // # Метод для проверки пустоты кучи
    public boolean isEmpty() {
        return heap.isEmpty();
    }
    
    // # Метод для вывода кучи
    public void printHeap() {
        System.out.println("Heap: " + heap);
    }
    
    // # Пример использования
    public static void main(String[] args) {
        BinaryHeapWithDelete heap = new BinaryHeapWithDelete();
        heap.insert(10);
        heap.insert(5);
        heap.insert(15);
        heap.insert(3);
        heap.insert(7);
        
        heap.printHeap(); // # Вывод: Heap: [3, 5, 15, 10, 7]
        
        System.out.println("Extracted min: " + heap.extractMin()); // # Вывод: Extracted min: 3
        heap.
printHeap(); // # Вывод: Heap: [5, 7, 15, 10]
        
        System.out.println("Extracted min: " + heap.extractMin()); // # Вывод: Extracted min: 5
        heap.printHeap(); // # Вывод: Heap: [7, 10, 15]
    }
}
