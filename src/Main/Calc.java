package Main;
/**
 * Класс для вычисления значения выражения
  * @author  Вадим Таратонов
  * @version 1.1
 */
public class Calc {
    /** Строка символов */
    private String[] symbols;
    /** Позиция элемента */
    private int pos;

    /**
     * Конструктор с параметрами
     * @param symbols исходная стока-выражение
     * @see Calc#Calc()
     */
    public Calc(String symbols) {
        this.symbols = symbols.split(" ");
        this.pos = 0;
    }

    /**
     * Конструктор без параметров
     * @see Calc#Calc(String)
     */
    public Calc(){}

    /**
     * Итоговая функция подсчета выражения
     * @return возвращает конечное значение
     * @throws Exception ошибка неверного ввода
     * @see Calc#AddSub()
     * @see Calc#MulDiv()
     * @see Calc#Brackets()
     */
    public double calculate() throws Exception {
        double result = AddSub();
        if (pos != symbols.length) {
            throw new Exception("Ошибка: неверный ввод!");
        }
        return result;
    }

    /**
     * Функция суммирование или вычитания блоков выражения
     * @return возвращает полученную сумму или разность
     * @throws Exception ошибка неверного ввода
     * @see Calc#MulDiv()
     * @see Calc#Brackets()
     */
    public double AddSub() throws Exception {
        double first = MulDiv();

        while (pos <symbols.length) {
            String operator = symbols[pos];
            if (!operator.equals("+") && !operator.equals("-"))
                break;
            else pos++;
        if (pos < symbols.length) {
            double second = MulDiv();
            if (operator.equals("+"))
                first += second;
            else first -= second;
        }
        else
            throw new Exception("Ошибка: неверный ввод!");
        }
        return first;
    }

    /**
     * Функция умножения или деления блоков выражения
     * @return возвращает полученное произведение или частное
     * @throws Exception ошибка неверного ввода и деления на 0
     * @see Calc#AddSub()
     * @see Calc#Brackets()
     */
    public double MulDiv() throws Exception {
        double first = Brackets();

        while (pos <symbols.length) {
            String operator = symbols[pos];
            if (!operator.equals("*") && !operator.equals("/"))
                break;
            else pos++;
        if (pos < symbols.length) {
            double second = Brackets();
            if (operator.equals("*"))
                first *= second;
            else
                if (second != 0)
                    first /= second;
                else
                    throw new Exception("Ошибка: невозможно делить на 0!");
        } else
            throw new Exception("Ошибка: неверный ввод!");
        }
        return first;
    }

    /**
     * Функция выявления скобок в выражении
     * @return возвращает полученное значение в скобках, рекурсивно заходя в каждую функцию
     * @throws Exception ошибка неверного ввода
     * @see Calc#MulDiv()
     * @see Calc#AddSub()
     */
    public double Brackets() throws Exception {
        String next = symbols[pos];
        double result;
        if(next.equals("-")) {
            pos++;
            String newMinus = symbols[pos];
            if (newMinus.equals("-")) {
                throw new Exception("Ошибка: неверный ввод!");
            }
            result = Brackets();
            return -result;

        }
        if (next.equals("(")) {
            pos++;
            result = AddSub();
            String closingBracket;
            if (pos < symbols.length)
                closingBracket = symbols[pos];
            else throw new Exception("Ошибка: неожиданный конец выражения!");
            if (closingBracket.equals(")")) {
                pos++;
                return result;
            }
            throw new Exception("Ошибка: отсутствует закрывающая скобка!");
        }
        pos++;
        if (next.equals("+") || //next.equals("-") ||
        next.equals("*") || next.equals("/") || next.equals(")"))
            throw new Exception("Ошибка: неверный ввод!");

        return Double.parseDouble(next);
    }
}
