package htw.berlin.prog2.ha1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Retro calculator")
class CalculatorTest {

    @Test
    @DisplayName("should display result after adding two positive multi-digit numbers")
    void testPositiveAddition() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "40";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display result after getting the square root of two")
    void testSquareRoot() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressUnaryOperationKey("√");

        String expected = "1.41421356";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display error when dividing by zero")
    void testDivisionByZero() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(7);
        calc.pressBinaryOperationKey("/");
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display error when drawing the square root of a negative number")
    void testSquareRootOfNegative() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(7);
        calc.pressNegativeKey();
        calc.pressUnaryOperationKey("√");

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should not allow multiple decimal dots")
    void testMultipleDecimalDots() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(1);
        calc.pressDotKey();
        calc.pressDigitKey(7);
        calc.pressDotKey();
        calc.pressDigitKey(8);

        String expected = "1.78";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    //TODO hier weitere Tests erstellen

    @Test
    @DisplayName("should show result after subtracting 2 from 5.")
    void testSubtract() {
         Calculator calc = new Calculator();

         calc.pressDigitKey(5);
         calc.pressBinaryOperationKey("-");
         calc.pressDigitKey(2);
         calc.pressEqualsKey();

         String expected = "3";
         String actual = calc.readScreen();

         assertEquals(expected, actual);
    }

    /* Der Test funktioniert leider...
    @Test
    @DisplayName("should show result after pressing '%'-key")
    void testPressingPercentageKey() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(5);
        calc.pressDigitKey(0);
        calc.pressUnaryOperationKey("%");

        String expected = "0.5";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    } */

    /**
    * Rechner kann keine Prozente addieren. Der Online-Rechner kann das.
    * Also 10 + 10 % müssten 11 ergeben, allerdings ist diese Rechnung nicht ausführbar.
    * -> IllegalArgumentException
    * Normaler Testlauf mit 'expected' und 'actual' daher nicht möglich.
    * 1. Fehler 'IllegalArgumentException'.
    * 2. Fehler Ergebnis könnte auch 10.1 sein, da 10 % = 0.1 -> 10 + 0.1 = 10.1
     * -> Ergebnis muss 11 sein.
    */
    @Test
    @DisplayName("should show result from adding percentage in number")
    void testAddPercentage() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(1);
        calc.pressDigitKey(0);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(1);
        calc.pressDigitKey(0);
        calc.pressUnaryOperationKey("%");
        calc.pressEqualsKey();

        String expected = "11";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    /**
     * Taschenrechner kann nicht Punkt vor Strich rechnen.
     * 1 + (4 / 2) = 3 und nicht 2.
     * Expected: 3
     * Actual: 2 (Was auch immer der rechnet, um auf 2 zu kommen???)
     */

    @Test
    @DisplayName("should calculate point before line")
    void testCalculatePointBeforeLine() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(1);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(4);
        calc.pressBinaryOperationKey("/");
        calc.pressDigitKey(2);
        calc.pressEqualsKey();

        String expected = "3";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }
}

