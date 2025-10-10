import java.util.*;

public class GraphTasks {
    
    // Функция для обхода графа в глубину (DFS)
    static void dfs(int v, List<List<Integer>> graph, boolean[] visited) {
        visited[v] = true;
        for (int u : graph.get(v)) {
            if (!visited[u]) {
                dfs(u, graph, visited);
            }
        }
    }
    
    public static void main(String[] args) {
        // ЗАДАЧА 1: Построение графа и подсчёт вершин
        int vertices = 5;
        List<List<Integer>> graph = new ArrayList<>();
        
        // Инициализируем списки смежности (исправлено условие цикла)
        for (int i = 1; i <= vertices; i++) {  // изменено i = 1 вместо i = 0
            graph.add(new ArrayList<>());
        }
        
        // Добавляем рёбра (неориентированный граф)
        graph.get(1).add(2);
        graph.get(2).add(1);
        graph.get(2).add(3);
        graph.get(3).add(2);
        graph.get(3).add(4);
        graph.get(4).add(3);
        graph.get(4).add(5);
        graph.get(5).add(4);
        
        System.out.println("Задача 1: Вершин в графе = " + vertices);
        
        // ЗАДАЧА 2: Проверка связности
        boolean[] visited = new boolean[vertices + 1];  // массив на vertices + 1 элементов
        dfs(1, graph, visited); // Запускаем обход из вершины 1
        
        boolean connected = true;
        for (int i = 1; i <= vertices; i++) {
            if (!visited[i]) {
                connected = false;
                break;
            }
        }
        
        System.out.println("Задача 2: Граф " + (connected ? "связный" : "не связный"));
    }
}
