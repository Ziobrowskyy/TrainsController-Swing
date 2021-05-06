package com.ziobrowski;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Utils {

    static final Scanner scanner = new Scanner(System.in);
    static final ThreadLocalRandom currentRandomThread = ThreadLocalRandom.current();

    public static int randomInt(int max) {
        return currentRandomThread.nextInt(max);
    }

    public static int randomInt(int min, int max) {
        return currentRandomThread.nextInt(min, max);
    }
    public static boolean randomBool() {
        return currentRandomThread.nextBoolean();
    }
    static int getInt() {
        int value = -1;
        boolean validInput;

        do {
            validInput = true;

            try {
                value = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("Invalid input!");
                validInput = false;
            }

        } while (!validInput);

        return value;
    }

    static int getInt(int min, int max) {
        int value = getInt();

        while (value < min || value > max) {
            System.out.printf(Locale.US, "Invalid range.\nInput integer in range <%d, %d>.\n", 1, max);
            scanner.next();
            value = getInt();
        }

        return value;
    }

    static double getDouble() {
        double value = -1;
        boolean validInput;

        do {
            validInput = true;

            try {
                value = scanner.nextDouble();
            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("Invalid input!");
                validInput = false;
            }

        } while (!validInput);

        return value;
    }

    static double getDouble(double min, double max) {
        double value = getDouble();

        while (value < min || value > max) {
            System.out.printf(Locale.US, "Invalid range.\nInput double in range <%f, %f>.\n", min, max);
            scanner.next();
            value = getDouble();
        }

        return value;
    }
}
