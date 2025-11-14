import java.util.*;

class TSP {
    public static int tsp(int[][] graph, int mask, int pos, int n, int[][] dp) {
        if (mask == (1 << n) - 1) return graph[pos][0]; // Все города посещены - возвращаемся в стартовый
        if (dp[mask][pos] != -1) return dp[mask][pos]; // Уже вычислено - возвращаем из кеша
        
        int minCost = Integer.MAX_VALUE;
        for (int city = 0; city < n; city++) {
            if ((mask & (1 << city)) == 0) { // Если город не посещен
                int newCost = graph[pos][city] + tsp(graph, mask | (1 << city), city, n, dp);
                minCost = Math.min(minCost, newCost);
            }
        }
        return dp[mask][pos] = minCost; // Сохраняем и возвращаем результат
    }
    
    public static void main(String[] args) {
        int[][] graph = {{0,10,15,20},{10,0,35,25},{15,35,0,30},{20,25,30,0}};
        int n = graph.length;
        int[][] dp = new int[1<<n][n];
        for(int[] row : dp) Arrays.fill(row, -1);
        System.out.println("Минимальная стоимость: " + tsp(graph, 1, 0, n, dp));
    }
}
