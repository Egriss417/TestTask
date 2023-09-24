import java.util.Scanner;

public class Main {


    public static void main(String[] args)  {
        Scanner scanner =new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String input = scanner.nextLine();
        System.out.print("Ответ: " + calc(input));

    }
    public static String calc(String input){
        int result = 0;
        int firstOperand = 0;
        int secondOperand = 0;
        input = input.trim();
        Roman roman = new Roman();

        String[] request = input.split(" ");

        Boolean isRoman = roman.isRoman(request[0]) && roman.isRoman(request[0]);
        if(!isRoman){
            firstOperand = Integer.parseInt(request[0]);
            secondOperand = Integer.parseInt(request[2]);
        }else {
            firstOperand = roman.converArabic(request[0]);
            secondOperand = roman.converArabic(request[2]);
        }

        String operation = request[1];
        result = switch (operation) {
            case "+" -> firstOperand + secondOperand;
            case "-" -> firstOperand - secondOperand;
            case "*" -> firstOperand * secondOperand;
            case "/" -> firstOperand / secondOperand;
            default -> result;
        };
        if(isRoman)
            return roman.converRoman(result);
        return String.valueOf(result);
    }

}



class Roman{
    public boolean isRoman(String number){
        String romanSimvol = "IVXLCDM";
        if(romanSimvol.indexOf(number)>-1){
            return true;
        }
        return false;
    }

    public int converArabic(String number){
        int result = 0;
        int currentNumber = 0;
        int previusNumber = 0;

        for(int i = number.length()-1; i>=0;--i){

            switch ( number.charAt(i) ){
                case 'I':
                    currentNumber =1;
                    break;
                case 'V':
                    currentNumber = 5;
                    break;
                case 'X':
                    currentNumber = 10;
                    break;
                case 'L':
                    currentNumber = 50;
                    break;
                case 'C':
                    currentNumber = 100;
                    break;
                case 'D':
                    currentNumber = 500;
                    break;
                case 'M':
                    currentNumber = 1000;
                    break;

            }
            if(currentNumber < previusNumber){
                result -= currentNumber;
            }else
                result += currentNumber;

        }

        return result;
    }

    public String converRoman(int number){
        String result = "";

        return result;
    }
}