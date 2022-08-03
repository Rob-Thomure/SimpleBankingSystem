package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AccountCreator {
    private static final String BIN = "400000";

    public String generateCardNum() {
        Random random = new Random();
        String randomNum1 = String.valueOf(random.nextInt(100000));
        String randomNum2 = String.valueOf(random.nextInt(10000));
        String randomNum = randomNum1 + randomNum2;
        int randomNumsLength = randomNum.length();
        String cardNum = BIN + "0".repeat(9 - randomNumsLength) + randomNum;
        cardNum = cardNum + calculateLastDigit(cardNum);
        return cardNum;
    }

    // calculating last digit of credit card using Luhn formula
    private String calculateLastDigit(String cardNum) {
        List<Integer> digits = new ArrayList<>();
        for (int i = 0; i < cardNum.length(); i++) {
            int digit = Character.digit(cardNum.charAt(i), 10);
            // multiply odd digits by 2
            if (i % 2 == 0) {
                digit *= 2;
                // if odd digit is > 9 then subtract 9
                if (digit > 9) {
                    digit -= 9;
                }
            }
            digits.add(digit);
        }
        int sum = 0;
        for (int digit : digits) {
            sum += digit;
        }
        // last digit is equal to 10 subtract the sum of all digits modulo 10
        int lastDigit;
        if (sum % 10 != 0) {
            lastDigit = 10 - (sum % 10);
        } else {
            lastDigit = 0;
        }
        return String.valueOf(lastDigit);
    }

    public String generatePIN() {
        Random random = new Random();
        String randomNum = String.valueOf(random.nextInt(10000));
        return randomNum + "0".repeat(4 - randomNum.length());
    }
}