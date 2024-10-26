package htw.berlin.prog2.ha1;

import java.util.ArrayList;
import java.util.List;

/**
 * Eine Klasse, die das Verhalten des Online Taschenrechners imitiert, welcher auf
 * https://www.online-calculator.com/ aufgerufen werden kann (ohne die Memory-Funktionen)
 * und dessen Bildschirm bis zu zehn Ziffern plus einem Dezimaltrennzeichen darstellen kann.
 * Enthält mit Absicht noch diverse Bugs oder unvollständige Funktionen.
 *
 * ArrayList für vollständige Liste von Rechenoperationen.
 * Diese Liste wird so lange erweitert, bis die Methode pressEqualsKey() (also =) aufgerufen wird.
 * Nur so besteht die Möglichkeit, dass die Rechenaufgabe mehrere Operationen hat und diese analysiert werden.
 */
public class Calculator {

    private String screen = "0";

    private List<String> expression = new ArrayList<>();

    /**
     * @return den aktuellen Bildschirminhalt als String
     */
    public String readScreen() {
        return screen;
    }

    /**
     * Empfängt den Wert einer gedrückten Zifferntaste. Da man nur eine Taste auf einmal
     * drücken kann muss der Wert positiv und einstellig sein und zwischen 0 und 9 liegen.
     * Führt in jedem Fall dazu, dass die gerade gedrückte Ziffer auf dem Bildschirm angezeigt
     * oder rechts an die zuvor gedrückte Ziffer angehängt angezeigt wird.
     * @param digit Die Ziffer, deren Taste gedrückt wurde
     */
    public void pressDigitKey(int digit) {
        if(digit > 9 || digit < 0) throw new IllegalArgumentException();

        if(screen.equals("0")) screen = "";

        screen = screen + digit;
    }

    /**
     * Empfängt den Befehl der C- bzw. CE-Taste (Clear bzw. Clear Entry).
     * Einmaliges Drücken der Taste löscht die zuvor eingegebenen Ziffern auf dem Bildschirm
     * so dass "0" angezeigt wird, jedoch ohne zuvor zwischengespeicherte Werte zu löschen.
     * Wird daraufhin noch einmal die Taste gedrückt, dann werden auch zwischengespeicherte
     * Werte sowie der aktuelle Operationsmodus zurückgesetzt, so dass der Rechner wieder
     * im Ursprungszustand ist.
     *
     * Operation und Value raus, weil nicht mehr verwendet, da ArrayList.
     * Neu: expression.clear
     */
    public void pressClearKey() {
        screen = "0";
        expression.clear();
    }

    /**
     * Empfängt den Wert einer gedrückten binären Operationstaste, also eine der vier Operationen
     * Addition, Substraktion, Division, oder Multiplikation, welche zwei Operanden benötigen.
     * Beim ersten Drücken der Taste wird der Bildschirminhalt nicht verändert, sondern nur der
     * Rechner in den passenden Operationsmodus versetzt.
     * Beim zweiten Drücken nach Eingabe einer weiteren Zahl wird direkt des aktuelle Zwischenergebnis
     * auf dem Bildschirm angezeigt. Falls hierbei eine Division durch Null auftritt, wird "Error" angezeigt.
     * @param operation "+" für Addition, "-" für Substraktion, "x" für Multiplikation, "/" für Division
     */

     /**
      * Value und Operation raus.
     * Neu: expression.add(screen) + (operation).
      * Anpassung für ArrayList.
     */
    public void pressBinaryOperationKey(String operation)  {
        expression.add(screen);
        expression.add(operation);
        screen = "0";
    }

    /**
     * Empfängt den Wert einer gedrückten unären Operationstaste, also eine der drei Operationen
     * Quadratwurzel, Prozent, Inversion, welche nur einen Operanden benötigen.
     * Beim Drücken der Taste wird direkt die Operation auf den aktuellen Zahlenwert angewendet und
     * der Bildschirminhalt mit dem Ergebnis aktualisiert.
     * @param operation "√" für Quadratwurzel, "%" für Prozent, "1/x" für Inversion
     */

     /**
      * screen + 2xif raus.
     * Neu: if screen
      * Anpassung für ArrayList
     */
    public void pressUnaryOperationKey(String operation) {
        double value = Double.parseDouble(screen);
        double result  = switch(operation) {
            case "√" -> Math.sqrt(Double.parseDouble(screen));
            case "%" -> Double.parseDouble(screen) / 100;
            case "1/x" -> 1 / Double.parseDouble(screen);
            default -> throw new IllegalArgumentException();
        };
        screen = result == Double.NaN ? "Error" : Double.toString(result);
        if (screen.endsWith(".0")) screen = screen.substring(0, screen.length() - 2);

    }

    /**
     * Empfängt den Befehl der gedrückten Dezimaltrennzeichentaste, im Englischen üblicherweise "."
     * Fügt beim ersten Mal Drücken dem aktuellen Bildschirminhalt das Trennzeichen auf der rechten
     * Seite hinzu und aktualisiert den Bildschirm. Daraufhin eingegebene Zahlen werden rechts vom
     * Trennzeichen angegeben und daher als Dezimalziffern interpretiert.
     * Beim zweimaligem Drücken, oder wenn bereits ein Trennzeichen angezeigt wird, passiert nichts.
     */
    public void pressDotKey() {
        if(!screen.contains(".")) screen = screen + ".";
    }

    /**
     * Empfängt den Befehl der gedrückten Vorzeichenumkehrstaste ("+/-").
     * Zeigt der Bildschirm einen positiven Wert an, so wird ein "-" links angehängt, der Bildschirm
     * aktualisiert und die Inhalt fortan als negativ interpretiert.
     * Zeigt der Bildschirm bereits einen negativen Wert mit führendem Minus an, dann wird dieses
     * entfernt und der Inhalt fortan als positiv interpretiert.
     */
    public void pressNegativeKey() {
        screen = screen.startsWith("-") ? screen.substring(1) : "-" + screen;
    }

    /**
     * Empfängt den Befehl der gedrückten "="-Taste.
     * Wurde zuvor keine Operationstaste gedrückt, passiert nichts.
     * Wurde zuvor eine binäre Operationstaste gedrückt und zwei Operanden eingegeben, wird das
     * Ergebnis der Operation angezeigt. Falls hierbei eine Division durch Null auftritt, wird "Error" angezeigt.
     * Wird die Taste weitere Male gedrückt (ohne andere Tasten dazwischen), so wird die letzte
     * Operation (ggf. inklusive letztem Operand) erneut auf den aktuellen Bildschirminhalt angewandt
     * und das Ergebnis direkt angezeigt.
     */

     /**
     * Die Switch-Cases wurden entfernt, da sie die flexibilität für komplexere Rechenaufgaben behindert.
     * Die Funktion der Operationen wird in der Methode evaluateExpression ausgeführt.
      * Diese Methode ruft die Methode evaluateExpression auf.
     */
    public void pressEqualsKey() {
        expression.add(screen);
        double result = evaluateExpression(expression);
        screen = Double.toString(result);
        if (screen.endsWith(".0")) screen = screen.substring(0, screen.length() - 2);
        expression.clear();
    }

    /**
     * Diese Methode implementiert die Punkt-vor-Strich-Regel.
     * Hier wird die Reihenfolge festgelegt, dass erst die Operatoren 'x' und '/' aus der ArrayList gerechnet wird
     * und dann erst '+' und '-'.
     *
     * @param expression ist die ArrayList mit allen Zahlen und Operatoren.
     * @return Ergebnis
     */
    private double evaluateExpression(List<String> expression) {
        List<String> processedExpression = new ArrayList<>(expression);

        //Erst Punkt
        for (int i = 1; i < processedExpression.size() - 1; i += 2) {
            String operator = processedExpression.get(i);
            if (operator.equals("x") || operator.equals("/")) {
                double left = Double.parseDouble(processedExpression.get(i - 1));
                double right = Double.parseDouble(processedExpression.get(i + 1));
                double partialResult = operator.equals("x") ? left * right : left / right;

                processedExpression.set(i - 1, Double.toString(partialResult));
                processedExpression.remove(i);
                processedExpression.remove(i);
                i -= 2;
            }
        }

        //Dann Strich
        double result = Double.parseDouble(processedExpression.get(0));
        for (int i = 1; i < processedExpression.size(); i += 2) {
            String operator = processedExpression.get(i);
            double nextValue = Double.parseDouble(processedExpression.get(i + 1));
            result = operator.equals("+") ? result + nextValue : result - nextValue;
        }
        return result;
    }

}
