#include <iostream>
using namespace std;

int main()
{
    string str = "AaBbCcDd"; // Исходная строчка букв

    cout << "str[::2] ";
    for (int i = 0; i < str.length(); i += 2) { // Перебираем каждый 2-ой эл-т начиная с 0-ого
        cout << str[i]; // Выводим все подходящие эл-ты
    }
    cout << endl;

    cout << "str[1::2] ";
    for (int i = 1; i < str.length(); i += 2) { // Перебираем каждый 2-ой эл-т начиная с 1-ого
        cout << str[i]; // Выводим все подходящие эл-ты
    }

    return 0;
}
