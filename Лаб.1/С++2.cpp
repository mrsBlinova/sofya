#include <iostream>
#include <vector>
#include <cctype>  // Äëÿ ôóíêöèé isalpha è isdigit
using namespace std;

int main() {
    // Â ýòîì çàäàíèè íå íàïèñàíî èñïîëüçîâàòü ñðåçû,
    // ïî ýòîìó áóäó ïðîâåðÿòü êàêîé ýë-ò ÷èñëî, à êàêîé ñèìâîë
    vector<char> original = { 'a', '1', 'b', '2', 'c', '3' };// Èñõîäíûé ìàññèâ
    vector<char> letters; // Âåêòîðíûé(äèíàìè÷åñêèé) ìàññèâ äëÿ áóêâ
    vector<char> numbers; // Âåêòîðíûé(äèíàìè÷åñêèé) ìàññèâ äëÿ ÷èñåë(öèôð)

    for (char c : original) { // Ïåðåáî âñåõ ýë-òîâ èñõîäíîãî ìàññèâà
        if (isalpha(c)) { // Îòáèðàåì áóêâû
            letters.push_back(c); // Äîáàâëÿåì â êîíåö ìàññèâà
        }
        else if (isdigit(c)) {  // Îòáèðàåì öèôðû
            numbers.push_back(c); // Äîáàâëÿåì â êîíåö ìàññèâà
        }
    }

    original.clear(); // Îò÷èùàåì èñõîäíûé ìàññèâ

    for (char c : letters) { 
        cout << c;// Âûâîäèì áóêâû
    }
    cout << endl;

    for (char c : numbers) {
        cout << c;// Âûâîäèì öèôðû
    }
    cout << endl;

    return 0;
}
