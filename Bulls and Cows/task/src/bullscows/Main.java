package bullscows;


import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static int turn = 1;

    public static void main(String[] args) {
        play();

    }

    public static void play() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please, enter the secret code's length:");
        int length = 0;
        int combination = 0;
        if (sc.hasNextInt()) {
            length = sc.nextInt();
            if (length < 1) {
                System.out.println("Error: minimum number code length is 1");
                System.exit(0);
            }
        } else {
            System.out.printf("Error: \"%s\" isn't a valid number.", sc.nextLine());
            System.exit(0);
        }
        System.out.println("Input the number of possible symbols in the code:");
        if (sc.hasNextInt()) {
            combination = sc.nextInt();
            if (combination > 36) {
                System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
                System.exit(0);
            }
        } else {
            System.out.printf("Error: \"%s\" isn't a valid number.", sc.nextLine());
            System.exit(0);
        }
        if (length > combination) {
            System.out.printf("Error: it's not possible to generate a code with a length of %d with %d unique symbols.\n", length, combination);
            System.exit(0);
        }
        StringBuilder code = game.getCode(length, combination);
        System.out.println(code);
        ArrayList<String> codeList = new ArrayList<>();
        System.out.println("Okay, let's start a game!");
        int exit = 0;
        while (exit != 1) {
            System.out.printf("Turn %d%n", turn);
            String guessNum = sc.next();
            ArrayList<String> guessList = new ArrayList<>();
            for (int i = 0; i < guessNum.length(); i++) {
//                guessList.add(Integer.parseInt(String.valueOf(guessNum.charAt(i))));
                guessList.add(String.valueOf(guessNum.charAt(i)));

            }
            codeList.clear();
            for (int i = 0; i < code.length(); i++) {
                codeList.add(String.valueOf(code.charAt(i)));
            }
            turn++;
            exit = game.grader(guessList, codeList);
            System.out.println();
        }
        System.out.println("Congratulations! You guessed the secret code.");


    }
}
