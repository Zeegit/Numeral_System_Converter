package converter;

import java.util.Scanner;

class Converter {

}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int sourceRadix = Integer.parseInt(scanner.nextLine());
        String sourceNumber = scanner.nextLine();
        int targetRadix = Integer.parseInt(scanner.nextLine());

        long x;
        if (sourceRadix > 1) {
            x = Long.parseLong(sourceNumber, sourceRadix);
        } else {
            x = sourceNumber.length();
        }

        String target;
        if (targetRadix > 1) {
            target = Long.toString(x, targetRadix);
        } else {

            StringBuilder outputBuffer = new StringBuilder((int)x);
            for (int i = 0; i < x; i++){
                outputBuffer.append("1");
            }
            target = outputBuffer.toString();
        }
        System.out.println(target);

        /*String res;
        if (base == 16) {
            res = "0x"  +Long.toString(n, base);
        } else if (base == 2) {
            res = "0b"  +Long.toString(n, base);
        } else {res = "0"  +Long.toString(n, base);}
        System.out.println(res);*/
    }
}
