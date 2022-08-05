package org.example;

import java.util.ArrayList;
import java.util.List;

public class LuhnAlgorithm {

    public static boolean passesLuhnFormula(String cardNum) {
        List<Integer> digits = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            int digit = Character.digit(cardNum.charAt(i), 10);
            if (i % 2 == 0) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            digits.add(digit);
        }
        int sum = 0;
        for (int digit : digits) {
            sum+= digit;
        }
        return sum % 10 == 0;
    }
}
