package converter;

import java.util.Scanner;

class Converter {
    private static final String digits = "0123456789abcdefghijklmnopqrstuvwxyz";
    private static final int digitsAfter = 5;

    private static String parseIntegerPart(int sourceRadix, String sourceNumber, int targetRadix) {
        int x = (sourceRadix > 1)?Integer.parseInt(sourceNumber, sourceRadix):sourceNumber.length();

        String target;
        if (targetRadix > 1) {
            target = Long.toString(x, targetRadix);
        } else {
            StringBuilder outputBuffer = new StringBuilder(x);
            for (int i = 0; i < x; i++) {
                outputBuffer. append("1");
            }
            target = outputBuffer.toString();
        }
        return target;
    }

    private static double convertToDecimalRadix (int sourceRadix, String sourceNumber) {
        double number10 = 0;
        for(int i = 0; i < Math.min(sourceNumber.length(), digitsAfter); i++) {
            number10 += Double.parseDouble(String.valueOf(digits.indexOf(sourceNumber.charAt(i)))) / Math.pow(sourceRadix, i+1);
        }
        return number10;
    }

    private static String convertFromDecimalToTargetRadix (double sourceNumber, int targetRadix) {
        StringBuilder b = new StringBuilder();
        Double tmp = sourceNumber;
        for(int i = 0; i < digitsAfter; i++) {
            tmp *= targetRadix;
            b.append(digits.charAt(tmp.intValue()));
            tmp -= tmp.intValue();
        }
        return b.toString();
    }

    private static String parsefractionalPart(int sourceRadix, String sourceNumber, int targetRadix) {
        double number10 = convertToDecimalRadix(sourceRadix, sourceNumber);
        return convertFromDecimalToTargetRadix(number10, targetRadix);
    }

    static String parseNumber(int sourceRadix, String sourceNumber, int targetRadix) {
        String[] n = sourceNumber.split("\\.");

        String targetNumber = parseIntegerPart(sourceRadix, n[0], targetRadix);
        if (n.length > 1) {
            String fractionalPart = parsefractionalPart(sourceRadix, n[1], targetRadix);
            targetNumber = targetNumber + "." + fractionalPart;
        }
        return targetNumber;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int sourceRadix = Integer.parseInt(scanner.next());
        String sourceNumber = scanner.next();
        int targetRadix = Integer.parseInt(scanner.next());

        String targetNumber = Converter.parseNumber(sourceRadix, sourceNumber, targetRadix);
        System.out.println(targetNumber);
    }
}
