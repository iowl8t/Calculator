package tools;


import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        //initialization
        int x, y;
        char operation;
        String rez;
        Scanner in = new Scanner(System.in);
        String s;

        do {
            //Input user data
            s = "Put first value: ";
            x = putNumber(s);

            s = "Put operation: ";
            operation = putOperation(s);

            s = "Put second value: ";
            y = putNumber(s);

            //Result
            rez = makeOperation(x, y, operation);
            System.out.printf("Result: %s\n", rez);

            //Make history
            toHistory("" + x + operation + y + " = " + rez);

            //Display history
            System.out.print("\nTo see the history of operations put 'h': ");
            final String sh = "h";
            if (sh.equals(in.next()))
                displayHistory();

            //Clear history
            System.out.print("\nTo clear the history put 'c': ");
            final String sc = "c";
            if (sc.equals(in.next()))
                clearHistory();

            //Checking for continue
            try {
                System.out.print("\nTo continue put a number: ");
                in.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Incorrect input. Another time you need to put a number");
                System.out.println("Program has ended");
                break;
            }
        } while (true);
    }


    //Addition
    static String addition(int x, int y) {
        return Integer.toString((x + y));
    }

    //Subtraction
    static String subtraction(int x, int y) {
        return Integer.toString((x - y));
    }

    //Multiplication
    static String multiplication(int x, int y) {
        return Integer.toString((x * y));
    }

    //Division
    static String division(int x, int y) {
        return Integer.toString((x / y));
    }

    //Make operation
    static String makeOperation(int x, int y, char operation) {
        switch (operation) {

            case '+':
                return addition(x, y);
            case '-':
                return subtraction(x, y);
            case '*':
                return multiplication(x, y);
            case '/':
                return division(x, y);
        }
        return "Faceless result";
    }

    //Make the history
    static String[] history = new String[10];
    static int endIndex = 0;
    static int startIndex = 0;
    static int count = 0;
    static boolean flag = false;

    static void toHistory(String rez) {
        history[endIndex++] = rez;
        if (endIndex == (history.length)) {
            endIndex = 0;
            if (!flag) flag = true;
        }
        if (count < history.length) count++;

        if (flag) {
            startIndex++;
            if (startIndex == (history.length)) {
                startIndex = 0;
            }
        }
    }

    //Display history
    static void displayHistory() {
        if ((endIndex - startIndex) == 0) {
            System.out.println("History is empty");
        } else {
            System.out.println("History of last " + count + " operations:");
            int startIndexTemp = startIndex;

            for (int i = 0; i < count; i++) {
                System.out.println((i + 1) + " operation: " + history[startIndexTemp]);
                startIndexTemp++;
                if (startIndexTemp == (history.length)) startIndexTemp = 0;
            }
        }
    }

    //Clear history
    static void clearHistory() {
        //for (int i = 0; i < endIndex; i++)
        //    history[i] = "";
        endIndex = 0;
        startIndex = 0;
        flag = false;
        System.out.println("History was cleared");
    }

    //Input number
    static int putNumber(String s) {
        Scanner in = new Scanner(System.in);

        while (true) {
            try {
                System.out.print(s);
                return in.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Incorrect data. Try more");
                toHistory("Incorrect input data");
                return putNumber(s);
            }
        }
    }

    //Input operation
    static char putOperation(String s) {
        Scanner in = new Scanner(System.in);
        char operation;

        while (true) {
            try {
                System.out.print(s);
                operation = in.next().toCharArray()[0];
                if (operation != '+' && operation != '-' && operation != '*' && operation != '/') {
                    throw new Exception("Incorrect operation");
                }
                break;
            } catch (Exception e) {
                System.out.println("Incorrect operation. Try more");
                toHistory("Incorrect operation");
            }
        }
        return operation;
    }
}