package com.team.starsort.ui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class ConsoleUITest {

    public static void main(String[] args) {
        testSmokeFullSortFlow();
        testInvalidMenuInputDoesNotCrash();
        System.out.println("All ConsoleUITest passed");
    }

    static void testSmokeFullSortFlow() {
        String input = "1\n1\n5\n1\n1\n1\n0\n";
        String output = runWithInput(input);

        assertContains(output, "Star Sorting App", "menu printed");
        assertContains(output, "Заполнено 5 элементов", "filled 5");
        assertContains(output, "Результат:", "result printed");
        assertContains(output, "До новых встреч!", "exit printed");
    }

    static void testInvalidMenuInputDoesNotCrash() {
        String input = "abc\n0\n";
        String output = runWithInput(input);

        assertContains(output, "Введите целое число", "invalid input handled");
        assertContains(output, "До новых встреч!", "exit still works");
    }

    // ---------- хелперы ----------

    private static String runWithInput(String scriptedInput) {
        InputStream origIn = System.in;
        PrintStream origOut = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        try {
            System.setIn(new ByteArrayInputStream(scriptedInput.getBytes()));
            System.setOut(new PrintStream(buffer));
            new ConsoleUI().run();
        } finally {
            System.setIn(origIn);
            System.setOut(origOut);
        }

        return buffer.toString();
    }

    static void assertContains(String haystack, String needle, String ctx) {
        if (!haystack.contains(needle)) {
            throw new AssertionError(
                    "[" + ctx + "] expected \"" + needle + "\" in output.\nOutput was:\n" + haystack);
        }
    }
}