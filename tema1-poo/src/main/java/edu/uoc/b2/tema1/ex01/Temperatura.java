package edu.uoc.b2.tema1.ex01;

/**
 * ============================================================
 * EXERCICIO 01 / EXERCICI 01 — Clase Inmutable: Temperatura  ⭐ (Fácil / Fàcil)
 * ============================================================
 *
 * [CAT] OBJECTIU: Implementa la classe {@code Temperatura} seguint el patró
 * d'immutabilitat. La classe ha de ser immutable: sense setters, tots els
 * camps {@code final}, i cada operació retorna un NOU objecte.
 *
 * [ES]  OBJETIVO: Implementa la clase {@code Temperatura} siguiendo el patrón
 * de inmutabilidad. La clase debe ser inmutable: sin setters, todos los
 * campos {@code final}, y cada operación devuelve un NUEVO objeto.
 *
 * MÉTODOS / MÈTODES:
 *   Constructor: Temperatura(double grados) — valida >= -273.15
 *   getGrados()                : double  — Devuelve/Retorna los grados/graus Celsius
 *   toFahrenheit()             : double  — F = C × 9/5 + 32
 *   toKelvin()                 : double  — K = C + 273.15
 *   sumar(double delta)        : Temperatura — Devuelve/Retorna NUEVA/NOVA instància
 *   restar(double delta)       : Temperatura — Devuelve/Retorna NUEVA/NOVA instància
 *   equals(Object) y hashCode(): basados en grados (tolerancia 0.001) / basats en graus (tolerància 0.001)
 *   toString()                 : "25.00 °C"
 *
 * EJEMPLO / EXEMPLE:
 *   Temperatura t1 = new Temperatura(100.0);
 *   Temperatura t2 = t1.sumar(20.0);
 *   System.out.println(t1); // "100.00 °C" ← t1 NO ha cambiado / NO ha canviat
 *   System.out.println(t2); // "120.00 °C"
 *   System.out.println(t1.toFahrenheit()); // 212.0
 */
public final class Temperatura {
    private final double grados;



    // [ES]  TODO — Declara el campo 'grados' como private final double. Debe ser final para garantizar inmutabilidad.
    // [CAT] TODO — Declara el camp 'grados' com a private final double. Ha de ser final per garantir la immutabilitat.

    /**
     * [ES]  TODO — Implementa el constructor Temperatura(double grados). Valida que no sea inferior al cero absoluto (-273.15 °C).
     *              Si lo es, lanza: throw new IllegalArgumentException("Temperatura inferior al cero absoluto: " + grados);
     * [CAT] TODO — Implementa el constructor Temperatura(double grados). Valida que no sigui inferior al zero absolut (-273.15 °C).
     *              Si ho és, llança: throw new IllegalArgumentException("Temperatura inferior al zero absolut: " + grados);
     */
    public Temperatura(double grados) {

        if(grados< (-273.15)) {
            throw new IllegalArgumentException("Temperatura inferior al zero absolut: " + grados);
        }
        this.grados=grados;

    }

    /**
     * [ES]  Devuelve los grados Celsius de esta temperatura.
     * [CAT] Retorna els graus Celsius d'aquesta temperatura.
     */
    public double getGrados() {return grados;}

    /**
     * [ES]  Convierte la temperatura a grados Fahrenheit. Fórmula: F = C × 9/5 + 32
     * [CAT] Converteix la temperatura a graus Fahrenheit. Fórmula: F = C × 9/5 + 32
     */
    public double toFahrenheit() {
        double gradosF=0.0;
        gradosF= (grados*9/5)+32;
        return gradosF;
    }

    /**
     * [ES]  Convierte la temperatura a Kelvin. Fórmula: K = C + 273.15
     * [CAT] Converteix la temperatura a Kelvin. Fórmula: K = C + 273.15
     */
    public double toKelvin() {
        double gradosK=0.0;
        gradosK=grados + 273.15;
        return gradosK;
    }

    /**
     * [ES]  Devuelve una NUEVA temperatura con {@code delta} grados añadidos. Esta temperatura NO se modifica.
     * [CAT] Retorna una NOVA temperatura amb {@code delta} graus afegits. Aquesta temperatura NO es modifica.
     */
    public Temperatura sumar(double delta)
    {
        Temperatura gradosAfegits = new Temperatura(getGrados() + delta);
        return gradosAfegits;
    }

    /**
     * [ES]  Devuelve una NUEVA temperatura con {@code delta} grados restados. Esta temperatura NO se modifica.
     * [CAT] Retorna una NOVA temperatura amb {@code delta} graus restats. Aquesta temperatura NO es modifica.
     *
     * @throws IllegalArgumentException si el resultado / resultat < -273.15
     */
    public Temperatura restar(double delta) {
        if(getGrados() - delta < (-273.15)) {
            throw new IllegalArgumentException("Temperatura inferior al zero absolut: " + (getGrados() - delta));
        }
        else {
            Temperatura gradosRestats = new Temperatura(getGrados() - delta);
            return gradosRestats;
        }
    }

    /**
     * [ES]  Dos temperaturas son iguales si difieren menos de 0.001 °C.
     * [CAT] Dues temperatures són iguals si difereixen menys de 0.001 °C.
     */
    // @Override
    public boolean equals(Temperatura other)
    {
        Temperatura gradosN = new Temperatura(other.getGrados());
        //if (this.grados == other.grados) return true;
        //if (other == null || getClass() != other.getClass()) return false;

        return Math.abs(this.grados - other.grados) < 0.001;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(Math.round(grados * 100.0) / 100.0);



    }

    /**
     * [ES]  Formato: "25.00 °C"
     * [CAT] Format: "25.00 °C"
     */
    @Override
    public String toString() {
        return String.format("%.2f °C", grados);

    }
    public static void main(String[] args) {
        Temperatura t1 = new Temperatura(100.0);
        Temperatura t2 = t1.sumar(20.0);
        System.out.println(t1); // "100.00 °C" ← t1 NO ha cambiado / NO ha canviat
        System.out.println(t2); // "120.00 °C"
        System.out.println(t1.toFahrenheit()); // 212.0
        System.out.println(t1.toKelvin()); // 373.15
        Temperatura t3 = t1.restar(20.0);
        System.out.println(t3); // "80.00 °C"
        System.err.println("Temperatura t4 = new Temperatura(-300.0);"); // IllegalArgumentException
        Temperatura t4 = new Temperatura(-300.0);
        System.out.println(t4);
        System.out.println("Temperatura t5 = t1.equals(new Temperatura(400.0));"); // IllegalArgumentException
        Temperatura t5 = t1.restar(400.0);
        System.out.println(t5);
    }
}