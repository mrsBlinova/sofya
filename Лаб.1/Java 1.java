// 1 Задача
public class Main {
    public static void main(String[] args) {
        var str = "AaBbCcDd"; // Исходная строчка букв
        System.out.print("str[::2] - ");
        for (int i = 0; i < str.length(); i += 2){  // Перебираем каждый 2-ой эл-т начиная с 0-ого
            System.out.print(str.charAt(i)); // Выводим все подходящие эл-ты
        }
        System.out.print("\nstr[1::2] - ");
        for (int i = 1; i < str.length(); i += 2){  // Перебираем каждый 2-ой эл-т начиная с 1-ого
            System.out.print(str.charAt(i)); // Выводим все подходящие эл-ты
        }
    }
}
