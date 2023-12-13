package ru.ulstu.is.sbapp.district.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SbappApplicationTests {

    @Test
    public void testAddition() {
        int result = add(2, 3);
        assertEquals(5, result, "Ожидается, что 2 + 3 равно 5");
    }

    @Test
    public void testSubtraction() {
        int result = subtract(5, 2);
        assertEquals(3, result, "Ожидается, что 5 - 2 равно 3");
    }

    @Test
    public void testMultiplication() {
        int result = multiply(3, 4);
        assertEquals(12, result, "Ожидается, что 3 * 4 равно 12");
    }

    @Test
    public void testDivision() {
        double result = divide(10, 2);
        assertEquals(5.0, result, "Ожидается, что 10 / 2 равно 5.0");
    }

    private int subtract(int a, int b) {
        return a - b;
    }

    private int multiply(int a, int b) {
        return a * b;
    }

    private double divide(double a, double b) {
        return a / b;
    }

    private int add(int a, int b) {
        return a + b;
    }
}