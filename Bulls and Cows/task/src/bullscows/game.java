package bullscows;

import java.util.*;

public class game {

    public static StringBuilder getCode(int length, int combination) {
        StringBuilder sb = new StringBuilder("0123456789");
        StringBuilder secret = new StringBuilder();
        StringBuilder alpha = new StringBuilder();
        StringBuilder star = new StringBuilder();
        star.append("*".repeat(Math.max(0, length)));

        char c;
        for (c = 'a'; c <= 'z'; ++c) {
            alpha.append(c);
        }
        for (int i = 0; i < combination - 10; i++) {
            sb.append(alpha.charAt(i));
        }
        if (combination > 10) {
            System.out.printf("The secret is prepared: %s (0-9, a-%c).%n", star, sb.charAt(sb.length() - 1));
        } else {
            System.out.printf("The secret is prepared: %s (0-9).%n", star);
        }
        Random random = new Random();
        int randomSelect;
        do {
            randomSelect = random.nextInt(sb.length());
            if (secret.indexOf(String.valueOf(sb.charAt(randomSelect))) == -1) secret.append(sb.charAt(randomSelect));
            if (secret.charAt(0) == '0') secret.deleteCharAt(0);
        } while (secret.length() != length);
//        System.out.println(secret);
        return secret;
    }

    public static int grader(List<String> guessList, ArrayList<String> codeList) {
        int bull = 0;
        int cow = 0;
        int count = codeList.size();
        for (int i = count - 1; i >= 0; i--) {
            if (Objects.equals(guessList.get(i), codeList.get(i))) {
                bull++;
                codeList.remove(i);
                guessList.remove(i);
            }
        }
        for (Object j : codeList) {
            if (guessList.contains(j)) {
                cow++;
            }
        }
        boolean bullAndCow = bull > 0 && cow > 0;
        if (bullAndCow) {
            System.out.printf("Grade: %d bull(s) and %d cow(s).", bull, cow);
            return -1;
        } else if (bull == count) {
            System.out.printf("Grade: %d bull(s).", bull);
            return 1;
        } else if (bull > 0) {
            System.out.printf("Grade: %d bull(s).", bull);
            return -1;
        } else if (cow > 0) {
            System.out.printf("Grade: %d cow(s).", cow);
            return -1;
        } else {
            System.out.println("Grade: None.");
            return -1;
        }
    }
}

