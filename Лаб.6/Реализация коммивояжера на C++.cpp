#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
    vector<vector<int>> dist = {{0,10,15,20,25},{10,0,35,25,30},{15,35,0,30,20},{20,25,30,0,15},{25,30,20,15,0}};
    vector<int> path = {1,2,3,4}; // города B,C,D,E (A=0 - старт)
    int min_dist = 1e9;
    vector<int> best_path;
    
    do {
        int current = dist[0][path[0]]; // A -> первый город
        for (int i = 0; i < path.size()-1; i++) current += dist[path[i]][path[i+1]];
        current += dist[path.back()][0]; // последний -> A
        
        if (current < min_dist) { min_dist = current; best_path = path; }
    } while (next_permutation(path.begin(), path.end()));
    
    cout << "Маршрут: A";
    for (int city : best_path) cout << " -> " << char('A' + city);
    cout << " -> A\nДистанция: " << min_dist << " км" << endl;
    return 0;
}
