package Main;



import java.util.Scanner;
import java.util.Vector;


/**
 * Класс для анализа введенного выражения
 * @author  Вадим Таратонов
 * @version 1.1
 */
public class Analysis {
    /**
     * Вектор для буквенных переменных
     */
    Vector<String> letter;
    /**
     * Вектор для числовых значений заданных переменных
     */
    Vector<Integer> number;
    /**
     * Конструктор без параметров
     */
    public Analysis() {
        letter = new Vector<String>(10);
        number = new Vector<Integer>(10);
    }

    /**
     * Функция анализирования введенного выражения
     * @param text параметр - введенное вырадение
     * @return возвращает выражение без пробелов
     * @throws Exception ошибка неверного ввода
     */
    public String Analys(String text) throws Exception {
        String result = "";
        int pos = 0;
        while (pos < text.length()) {
            char c = text.charAt(pos);
            switch (c) {
                case '(':
                case ')':
                case '+':
                case '-':
                case '*':
                case '/':
                    result += c;
                    result += " ";
                    pos++;
                    continue;
                default:
                    if (c <= '9' && c >= '0') {
                        String sb = "";
                        do {
                            sb += c;
                            pos++;
                            if (pos >= text.length()) {
                                break;
                            }
                            c = text.charAt(pos);
                        } while (c <= '9' && c >= '0');
                        result += sb;
                        result += " ";
                    } else if (c <= 'z' && c >= 'a' || c >= 'A' && c <= 'Z') {
                        String sb = "";
                        do {
                            sb += c;
                            pos++;
                            if (pos >= text.length()) {
                                break;
                            }
                            c = text.charAt(pos);
                        } while (c <= 'z' && c >= 'a' || c >= 'A' && c <= 'Z');
                        if (letter.isEmpty()) {
                            Main main = new Main();
                            int value = main.input();
                            letter.add(sb);
                            number.add(value);
                            sb = String.valueOf(number.elementAt(0));
                        } else {
                            boolean flag = false;
                            for (int i = 0; i < letter.size(); i++) {
                                if (sb.equals(letter.elementAt(i))) {
                                    sb = String.valueOf(number.elementAt(i));
                                    flag = true;
                                }
                            }
                            if (flag == false) {
                                Main main= new Main();
                                int value = main.input();
                                letter.add(sb);
                                number.add(value);
                                sb = String.valueOf(number.lastElement());
                            }
                        }
                        result += sb;
                        result += " ";
                    } else {
                        if (c != ' ') {
                            throw new Exception("Ошибка: неожиданный символ!");
                        }
                        pos++;
                    }

            }

        }
        return result;
    }
}

