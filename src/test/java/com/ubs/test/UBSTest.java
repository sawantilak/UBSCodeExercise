package com.ubs.test;

import com.ubs.NegativeNumberException;
import com.ubs.StringCalculator;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UBSTest {

    private StringCalculator sc = null;

    @BeforeEach
    void setup() {
        sc = new StringCalculator();
    }

    @Test
    void testOnlyNumbers1() {
        try {
            assertEquals(sc.add("1,2,3"), 6);
        }catch (NumberFormatException | NegativeNumberException e) {
            e.printStackTrace();            // TODO: Use Logging
        }
    }

    @Test
    void testOnlyNumbers1WithNegatives() {
        try {
            assertThrows(NegativeNumberException.class, () -> sc.add("1,2,-3,4"));
        }catch (NumberFormatException e) {
            e.printStackTrace();            // TODO: Use Logging
        }
    }

    @Test
    void testOnlyNumbers1GT1000() {
        try {
            assertEquals(sc.add("1,1500,2,2500,3"), 6);
        }catch (NumberFormatException | NegativeNumberException e) {
            e.printStackTrace();            // TODO: Use Logging
        }
    }

    @Test
    void testOnlyNumbers2() {
        try {
            assertEquals(sc.add("1,2,3,4,5,6"), 21);
        }catch (NumberFormatException | NegativeNumberException e) {
            e.printStackTrace();            // TODO: Use Logging
        }
    }

    @Test
    void testOnlyNumbers3() {
        try {
            assertEquals(sc.add("4\n5\n6"), 15);
        }catch (NumberFormatException | NegativeNumberException e) {
            e.printStackTrace();            // TODO: Use Logging
        }
    }

    @Test
    void testOnlyNumbers3WithNegatives() {
        try {
            assertThrows(NegativeNumberException.class, () -> sc.add("4\n-5\n6\n-9"));
        }catch (NumberFormatException e) {
            e.printStackTrace();            // TODO: Use Logging
        }
    }

    @Test
    void testOnlyNumbers4() {
        try {
            assertEquals(sc.add("1,2,3,6,6,10,20,50"), 98);
        }catch (NumberFormatException | NegativeNumberException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDifferentDelimiters() {
        try {
            assertEquals(sc.add("//;\n1;2;3;10"), 16);
        }catch (NumberFormatException | NegativeNumberException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDifferentDelimitersWithNegatives() {
        try {
            assertThrows(NegativeNumberException.class, () -> sc.add("//;\n1;2;-3;10"));
        }catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }


    @Test
    void testDifferentDelimitersMutiple1() {
        try {
            assertEquals(sc.add("//,|;\n1,2;3,6;6"), 18);
        }catch (NumberFormatException | NegativeNumberException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDifferentDelimitersMutiple1withNegatives() {
        try {
            assertThrows(NegativeNumberException.class, () -> sc.add("//,|;\n1,2;3,-6;-6"));
        }catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDifferentDelimitersMultiple2() {
        try {
            assertEquals(sc.add("//***|%%\n1%%2***3%%6%%20"), 32);
        }catch (NumberFormatException | NegativeNumberException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDifferentDelimitersMultiple2Gt1000() {
        try {
            assertEquals(sc.add("//***|%%\n1%%2***3%%6%%20***1001"), 32);
        }catch (NumberFormatException | NegativeNumberException e) {
            e.printStackTrace();
        }
    }


    @Test
    void testDifferentDelimitersMultiple3Gt1000() {
        try {
            assertEquals(sc.add("//***|%%\n1%%1001***3%%6%%20"), 30);
        }catch (NumberFormatException | NegativeNumberException e) {
            e.printStackTrace();
        }
    }

}
