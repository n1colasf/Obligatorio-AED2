package uy.edu.ort.aed2.obligatorio;

public class Retorno {

    public enum Resultado {
        OK, ERROR_1, ERROR_2, ERROR_3, ERROR_4, ERROR_5, ERROR_6, NO_IMPLEMENTADA
    }

    public Resultado resultado;
    public int valorEntero;
    public String valorString;

    public Retorno(Resultado resultado, int valorEntero, String valorString) {
        this.resultado = resultado;
        this.valorEntero = valorEntero;
        this.valorString = valorString;
    }

    public Retorno(Resultado resultado) {
        this.resultado = resultado;
    }

}
