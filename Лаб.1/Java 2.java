// 2 Задача
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        char[] original = {'a', '1', 'b', '2', 'c', '3'}; // Исходный массив символов
        List<Character> letters = new ArrayList<>(); // Динамический массив для букв
        List<Character> numbers = new ArrayList<>(); // Динамический массив для чисел(цифр)

        // Разделение символов на буквы и цифры
        for (char c : original) { // Перебо всех эл-тов исходного массива
            if (Character.isLetter(c)) { // Отбираем буквы
                letters.add(c); // Добавляем в конец массива
            } else if (Character.isDigit(c)) { // Отбираем цифры
                numbers.add(c); // Добавляем в конец массива
            }
        }

        // Вывод букв
        for (char c : letters) {
            System.out.print(c);// Выводим буквы
        }
        System.out.println();

        original = null; //Удаление массива
        System.gc();

        // Вывод цифр
        for (char c : numbers) {
            System.out.print(c);// Выводим цифры
        }
    }
}
