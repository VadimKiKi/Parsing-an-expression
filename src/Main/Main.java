package Main;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) throws Exception {
        try {
            Scanner in = new Scanner(System.in);
            System.out.print("Введите выражение:");
            String exp = in.next();
            Analysis str = new Analysis();
            String result ="";
            result = str.Analys(exp);
            System.out.println("Ваше выражение: " + result);
            Calc calc = new Calc(result);
            System.out.println(calc.calculate());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        //Calc calc = new Calc(exp);
        //System.out.println(calc.calculate());
        //System.out.println((2+2)*2)
    }
    public int input() {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите значение для переменой: ");
        int number;
        while (!in.hasNextInt()) {
            System.out.println("Ошибка: неверное значение!");
            System.out.print("Введите корректное значение: ");
            in.next();
        }
        number = in.nextInt();
        return number;
    }
}

