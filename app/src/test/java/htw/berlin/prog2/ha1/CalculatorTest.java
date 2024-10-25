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
     * Nach langem testen habe ich festgestellt, dass der Code nur den letzten Rechenschritt vollzieht.
     * -> 1 + 2 + 3 + 4 + 5 = 9, da nur 4 + 9 gerechnet wird.
     * Expected :15
     * Actual   :9
     */
    @Test
    @DisplayName("should show result of several additions")
    void testSeveralAdditions() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(1);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(2);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(3);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(4);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(5);
        calc.pressEqualsKey();

        String expected = "15";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    /**
     * Weiterhin habe ich festgestellt, dass der Code die Punkt-vor-Strich-Regel nicht beachtet.
     * Expected :3
     * Actual   :2
     * In diesem Test tritt zwar das oben geschriebene Problem auf, da hier nur 4 / 2 gerechnet wird und nicht 1 + 4 / 2.
     * Punkt-vor-Strich-Rechnung funktionierte trotzdem nicht.
     * Hintergrund ist eine Missinterpretation des Ergebnisses:
     * Ich erwartete, dass der Test fehlschlägt, was er auch tat, weshalb ich auf das Nichtbeachten
     * der Punkt-vor-Strich-Regel schloss.
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

