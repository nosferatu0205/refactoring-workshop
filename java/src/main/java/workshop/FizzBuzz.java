package workshop;

public class FizzBuzz {
    public static String checkNumber(int number) { 
        

        if (number % 15 == 0) {
            strReturn = "FizzBuzz";
        } 
        else if (number % 3 == 0) 
            return "Fizz";

        else if (number % 5 == 0) 
            return = "Buzz";
         return String.valueOf(number);
    }
}
