import java.util.Scanner;

public class Main {
    public static String calc(String a) {
        String k = a.replaceAll(" ", "");
        if (k.length() < 3 | (!k.contains("-") & !k.contains("+") & !k.contains("/") & !k.contains("*"))) {
            throw new WrongFormatException("строка не является математической операцией");
        }
        if (k.startsWith("-")) {
            throw new WrongFormatException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        int i = 1;
        if (k.contains("+")) {
            i = k.indexOf("+");
        } else if (k.contains("-")) {
            i = k.indexOf("-");

        } else if (k.contains("*")) {
            i = k.indexOf("*");

        } else if (k.contains("/")) {
            i = k.indexOf("/");

        }
        if (k.substring(i + 1).contains("/") | k.substring(i + 1).contains("-") | k.substring(i + 1).contains("+") | k.substring(i + 1).contains("*")) {
            throw new WrongFormatException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        int[] arabic = new int[100];
        String[] roman = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII",
                "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII",
                "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII",
                "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII",
                "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};
        int numb = 1;
        for (int j = 0; j < 100; j++) {
// Да, я понимаю, что это безумие, но я уже не могу остановиться, пока умею только так
            arabic[j] = numb;
            numb += 1;
        }
        for (int p = 0; p < 10; p++) {
            for (int h = 0; h < 10; h++) {
                if ((k.contains(String.valueOf(arabic[h])) | k.contains("0")) & k.contains(roman[p])) {
                    throw new RuntimeException("используются одновременно разные системы счисления");
                }
            }
        }
        int pervoeChislo = 0;
        int vtoroeChislo = 0;
        String s1 = k.substring(0, i);
        String s2 = k.substring(i + 1);
        for (int j = 0; j < 100; j++) {
            if (s1.equals(roman[j])) {
                pervoeChislo = arabic[j];
            }
            if (s2.equals(roman[j])) {
                vtoroeChislo = arabic[j];
            }
        }
        if (pervoeChislo > 10 | vtoroeChislo > 10) {
            throw new SlishkomBolshieChisla("Оба числа должны быть меньше или равны 10, иначе я отказываюсь работать");
        }
        String res = new String();
        if (k.contains("+") & k.contains("I") | k.contains("+") & k.contains("V") | k.contains("+") & k.contains("X")) {
            int result = pervoeChislo + vtoroeChislo;
            for (int q = 0; q < 100; q++) {
                if (result == arabic[q]) {

                    res = roman[q];

                }
            }
            return res;
        }
        if (k.contains("-") & k.contains("I") | k.contains("-") & k.contains("V") | k.contains("-") & k.contains("X")) {
            int result = pervoeChislo - vtoroeChislo;
            if (pervoeChislo <= vtoroeChislo) {
                throw new RuntimeException("в римской системе нет отрицательных чисел или нуля");
            }
            for (int q = 0; q < 100; q++) {
                if (result == arabic[q]) {

                    res = roman[q];

                }
            }
            return res;
        }
        if (k.contains("*") & k.contains("I") | k.contains("*") & k.contains("V") | k.contains("*") & k.contains("X")) {
            int result = pervoeChislo * vtoroeChislo;

            for (int q = 0; q < 100; q++) {
                if (result == arabic[q]) {

                    res = roman[q];

                }
            }
            return res;
        }
        if (k.contains("/") & k.contains("I") | k.contains("/") & k.contains("V") | k.contains("/") & k.contains("X")) {
            int result = pervoeChislo / vtoroeChislo;
            if (result < 1) {
                throw new RuntimeException("в римской системе нет отрицательных чисел или нуля");
            }
            for (int q = 0; q < 100; q++) {
                if (result == arabic[q]) {

                    res = roman[q];

                }
            }
            return res;
        }
        try {
            Integer int1 = Integer.parseInt(k.substring(0, i));
            Integer int2 = Integer.parseInt(k.substring(i + 1));


            if (int1 > 10 | int2 > 10) {
                throw new SlishkomBolshieChisla("Оба числа должны быть меньше или равны 10, иначе я отказываюсь работать");

            }
            if (k.contains("+")) {
                return String.valueOf(int1 + int2);
            } else if (k.contains("-")) {
                return String.valueOf(int1 - int2);

            } else if (k.contains("*")) {
                return String.valueOf(int1 * int2);

            } else if (k.contains("/")) {
                return String.valueOf(int1 / int2);

            }
            return null;
        } catch (SlishkomBolshieChisla s) {
            throw new SlishkomBolshieChisla("Оба числа должны быть меньше или равны 10, иначе я отказываюсь работать");
        } catch (Throwable t) {
            throw new RuntimeException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
    }

    public static void main(String[] args) {


        System.out.println("Введите римские или арабские числа от 1 до 10, чтобы попробовать лучший ограниченный калькулятор во Вселенной");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println("Результат равен " + Main.calc(str));
        sc.close();
    }
}

class SlishkomBolshieChisla extends RuntimeException {
    SlishkomBolshieChisla(String s) {
        super(s);
    }
}

class WrongFormatException extends RuntimeException {
    WrongFormatException(String s) {
        super(s);
    }
}
