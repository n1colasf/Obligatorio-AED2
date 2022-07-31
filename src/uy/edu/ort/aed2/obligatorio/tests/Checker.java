package uy.edu.ort.aed2.obligatorio.tests;

import org.junit.jupiter.api.Assertions;
import uy.edu.ort.aed2.obligatorio.Retorno;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Checker<T> {

    private final Function<String, T> parser;

    Checker(Function<String, T> parser) {
        this.parser = parser;
    }

    protected void ok(String msg, Retorno obtenido) {
        assertEquals(Retorno.Resultado.OK, obtenido.resultado, msg);
    }

    protected void ok(Retorno obtenido) {
        assertEquals(Retorno.Resultado.OK, obtenido.resultado, "El retorno se esperaba ok y no es ok");
    }

    protected void ok(int valorEsperado, String msg, Retorno obtenido) {
        ok(obtenido);
        assertEquals(valorEsperado, obtenido.valorEntero, msg != null ? msg : "Los valores enteros no coinciden");
    }

    protected void ok(T valorEsperado, String msg, Retorno obtenido) {
        ok(obtenido);
        assertEquals(valorEsperado.toString(), obtenido.valorString, msg != null ? msg : "Los valores string no coinciden");
    }

    protected void okNoImportaOrden(int valor, T[] resultadoEsperado,
                                    String msgValor, String msgTexto,
                                    Retorno obtenido,
                                    Function<String, T> parse) {
        this.ok(obtenido);
        assertEquals(valor, obtenido.valorEntero, msgValor);
        this.checkNoLeadingPipes(obtenido.valorString);
        String[] textoValoresObtenidos = obtenido.valorString.split("[|]");
        Object[] valoresObtenidos = new Object[textoValoresObtenidos.length];
        for (int i = 0; i < valoresObtenidos.length; i++) {
            valoresObtenidos[i] = parse.apply(textoValoresObtenidos[i]);
        }

        boolean[] existeEnEsperado = new boolean[resultadoEsperado.length];
        for (int i = 0; i < resultadoEsperado.length; i++) {
            T aBuscar = resultadoEsperado[i];
            for (int j = 0; j < valoresObtenidos.length; j++) {
                T elementoObtenido = (T) valoresObtenidos[j];
                if (aBuscar.equals(elementoObtenido)) {
                    existeEnEsperado[i] = true;
                    break;
                }
            }
        }
        int missingValues = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < existeEnEsperado.length; i++) {
            if (!existeEnEsperado[i]) {
                if (sb.length() > 0) {
                    sb.append("|");
                }
                sb.append(resultadoEsperado[i]);
                missingValues++;
            }
        }
        if (missingValues > 0) {
            Assertions.fail(String.format("Me faltan '%s' valores: '%s'", missingValues, sb.toString()));
        }
        assertEquals(resultadoEsperado.length, valoresObtenidos.length, "Los largos no coinciden,estas devolviendo mas de lo que se pide");
        //check same;

    }

    private void checkNoLeadingPipes(String valorStr) {
        Assertions.assertFalse(valorStr.startsWith("|"), "La lista no puede empezar por pipe");
        Assertions.assertFalse(valorStr.endsWith("|"), "La lista no puede terminar por pipe");

    }

    protected void ok(int valor, T resultado, Retorno obtenido) {
        ok(valor, resultado, null, null, obtenido);
    }

    protected void ok(int valorEsperado, T resultadoEsperado, String msgValor, String msgTexto, Retorno obtenido) {
        this.ok(valorEsperado, msgValor, obtenido);
        this.ok(resultadoEsperado, msgTexto, obtenido);

    }

    protected void ok(int valor, T[] resultado, String msgValor, String msgTexto, Retorno obtenido) {
        this.ok(valor, msgValor, obtenido);
        checkNoLeadingPipes(obtenido.valorString);
        StringBuilder sb = new StringBuilder();
        for (var t : resultado) {
            if (sb.length() > 0) {
                sb.append("|");
            }
            sb.append(t.toString());
        }
        assertEquals(sb.toString(), obtenido.valorString, msgTexto != null ? msgTexto : "Los valores string no coinciden");

    }

    public void error(Retorno.Resultado codigo, String descripcion, Retorno obtenido) {
        assertEquals(codigo, obtenido.resultado, "Error no coincide:" + descripcion);
    }


}
