package kishan;

import java.util.InputMismatchException;

public class GlobalExceptionHandler extends Throwable{
    public static void handleException(Exception e) {
    	if (e instanceof ArithmeticException) {
    		System.out.println(e);
    	}else if(e instanceof InputMismatchException) {
    		System.out.println(e);
    	}else if(e instanceof InsufficientBalanceException) {
    		System.out.println(e.getMessage());
    	}else if(e instanceof IncorrecAccNumberException) {
    		System.out.println(e.getMessage());
    	}

    }
}