import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner =new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String input = scanner.nextLine();
        try {
            System.out.print("Ответ: " + calc(input));
        } catch (InvalidInputException e) {
            throw new RuntimeException(e);
        }

    }
    public static String calc(String input) throws InvalidInputException{
        int result;
        int firstOperand;
        int secondOperand;
        input = input.trim();
        Roman roman = new Roman();

        String[] request = input.split(" ");

        if(request.length!=3)
            throw  new InvalidInputException("Формат математической операции не удовлетворяет заданию");

        if(roman.isRoman(request[0]) ^ roman.isRoman(request[2]))
            throw  new InvalidInputException("Числа должны быть в одинаковой системе счисления");

        boolean isRoman = roman.isRoman(request[0]) && roman.isRoman(request[2]);


        if(!isRoman){
            firstOperand = Integer.parseInt(request[0]);
            secondOperand = Integer.parseInt(request[2]);
        }else {
            firstOperand = roman.converArabic(request[0]);
            secondOperand = roman.converArabic(request[2]);
        }
        if((firstOperand<=0||firstOperand>10)||(secondOperand<=0||secondOperand>10)) {
                throw new InvalidInputException("Выход за диапозон от 1 до 10 при вводе числа");
        }
        String operation = request[1];
        result = switch (operation) {
            case "+" -> firstOperand + secondOperand;
            case "-" -> firstOperand - secondOperand;
            case "*" -> firstOperand * secondOperand;
            case "/" -> {
                if (secondOperand == 0)
                    throw new InvalidInputException("Деление на ноль");
                yield firstOperand / secondOperand;
            }
            default -> throw new InvalidInputException("Доступны только операции сложения, вычитания, умножения и деления");
        };

        if(isRoman)
            return roman.converRoman(result);
        return String.valueOf(result);
    }

}

enum RomanNumber{
    M(1000),
    CM(900),
    D(500),
    CD(400),
    C(100),
    XC(90),
    L(50),
    XL(40),
    X(10),
    IX(9),
    V(5),
    IV(4),
    I(1);


    final int numberArabic;
    RomanNumber(int numberArabic){
        this.numberArabic = numberArabic;
    }
}

class Roman{
    public boolean isRoman(String number){
        String romanSimvol = "IVXLCDM";

        for(int i = 0; i < number.length() ; ++i)
            if(!(romanSimvol.indexOf(number.charAt(i))>-1)){
                return false;
            }
        return true;
    }

    public int converArabic(String number){
        int result = 0;
        int currentNumber = 0;
        int previusNumber = 0;


        for(int i = number.length() -1; i>=0;--i){

            currentNumber = switch (number.charAt(i)) {
                case 'I' -> 1;
                case 'V' -> 5;
                case 'X' -> 10;
                case 'L' -> 50;
                case 'C' -> 100;
                case 'D' -> 500;
                case 'M' -> 1000;
                default -> currentNumber;
            };
            if(currentNumber < previusNumber){
                result -= currentNumber;
            }else
                result += currentNumber;

              previusNumber = currentNumber;
        }

        return result;
    }

    public String converRoman(int number) throws InvalidInputException{
        String result = "";
        int numberTimes;

        if(number<0)
            throw new InvalidInputException("В римской системе нет отрицательных чисел");
        else if(number == 0){
            throw new InvalidInputException("В римской системе нет числа ноль");
        }
        for(RomanNumber romanNumber : RomanNumber.values()){
            numberTimes = number/romanNumber.numberArabic;
            number -= numberTimes * romanNumber.numberArabic;
            for(int i = numberTimes; i > 0;--i){
                result = result.concat(romanNumber.name());

            }
        }

        return result;
    }
}

class InvalidInputException extends Exception{
        public InvalidInputException(String message){
            super(message);
        }
        }