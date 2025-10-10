#include <iostream>
#include <vector>
using namespace std;

// Функция для обхода графа в глубину (DFS)
void dfs(int v, vector<vector<int>>& graph, vector<bool>& visited) {
    visited[v] = true;
    for (int u : graph[v]) {
        if (!visited[u]) {
            dfs(u, graph, visited);
        }
    }
}

int main() {
    // ЗАДАЧА 1: Построение графа и подсчёт вершин
    int vertices = 5;
    vector<vector<int>> graph(vertices + 1); // Нумерация с 1
    
    // Добавляем рёбра
    graph[1].push_back(2);
    graph[2].push_back(1);
    graph[2].push_back(3);
    graph[3].push_back(2);
    graph[3].push_back(4);
    graph[4].push_back(3);
    graph[4].push_back(5);
    graph[5].push_back(4);
    
    cout << "Задача 1: Вершин в графе = " << vertices << endl;
    
    // ЗАДАЧА 2: Проверка связности
    vector<bool> visited(vertices + 1, false);
    dfs(1, graph, visited); // Запускаем обход из вершины 1
    
    bool connected = true;
    for (int i = 1; i <= vertices; i++) {
        if (!visited[i]) {
            connected = false;
            break;
        }
    }
    
    cout << "Задача 2: Граф " << (connected ? "связный" : "не связный") << endl;
    
    return 0;
}
