package uy.edu.ort.aed2.obligatorio.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uy.edu.ort.aed2.obligatorio.Retorno;
import uy.edu.ort.aed2.obligatorio.Sistema;
import uy.edu.ort.aed2.obligatorio.SistemaImp;

import java.util.Arrays;
import java.util.stream.Collectors;

import static uy.edu.ort.aed2.obligatorio.Retorno.Resultado.ERROR_1;

/**
 * Clase para testear el metodo {@link uy.edu.ort.aed2.obligatorio.SistemaImp#consultaDisponibilidad}
 */
public class TestDisponibilidad {
    private static final int D = 0; // disponible
    private static final int O = 1; // ocupado
    private static final int N = 2; // no disponible

    private Sistema sistema;
    private Checker<String> checker;

    @BeforeEach
    private void initialize() {
        sistema = new SistemaImp();
        sistema.inicializarSistema(200);
        checker = new Checker<>(this::ordenar);
    }

    // primre clase
    @Test
    void consultaDisponibilidad_primerClaseLibrePeroCantidadCero() {
        /*
         * P primera
         * E ejecutiva
         * T tursistica
         */

        final int[][] asientos = {
                //    1  2  3          4  5  6  7          8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
                //    P  P  P  /* - */ E  E  E  E  /* - */ T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T
                /*A*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*B*/{N, N, N, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*C*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*D*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*E*/{N, N, N, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*F*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D}
        };

        final Retorno respuesta = sistema.consultaDisponibilidad(asientos, 0, Sistema.Clase.Primera);

        checker.error(ERROR_1, "Se esperaba ERROR_1", respuesta);
    }

    @Test
    void consultaDisponibilidad_primerClaseLibrePeroCantidadMenorA0() {
        /*
         * P primera
         * E ejecutiva
         * T tursistica
         */

        final int[][] asientos = {
                //    1  2  3          4  5  6  7          8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
                //    P  P  P  /* - */ E  E  E  E  /* - */ T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T
                /*A*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*B*/{N, N, N, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*C*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*D*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*E*/{N, N, N, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*F*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D}
        };

        final Retorno respuesta = sistema.consultaDisponibilidad(asientos, -1, Sistema.Clase.Primera);

        checker.error(ERROR_1, "Se esperaba ERROR_1", respuesta);
    }


    @Test
    void consultaDisponibilidad_primerClaseLibrePeroCantidadMayorA4() {
        /*
         * P primera
         * E ejecutiva
         * T tursistica
         */

        final int[][] asientos = {
                //    1  2  3          4  5  6  7          8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
                //    P  P  P  /* - */ E  E  E  E  /* - */ T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T
                /*A*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*B*/{N, N, N, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*C*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*D*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*E*/{N, N, N, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*F*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D}
        };

        final Retorno respuesta = sistema.consultaDisponibilidad(asientos, 5, Sistema.Clase.Primera);

        checker.ok(0,
                "",
                "Se esperaba valorEntero 0",
                "Se esperaba valorString \"\"",
                respuesta);
    }

    @Test
    void consultaDisponibilidad_primerClaseLibreYCantidadEs2() {
        /*
         * P primera
         * E ejecutiva
         * T tursistica
         */

        final int[][] asientos = {
                //    1  2  3          4  5  6  7          8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
                //    P  P  P  /* - */ E  E  E  E  /* - */ T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T
                /*A*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*B*/{N, N, N, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*C*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*D*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*E*/{N, N, N, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*F*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D}
        };

        final Retorno respuesta = sistema.consultaDisponibilidad(asientos, 2, Sistema.Clase.Primera);

        String respuestaEsperada = "A1-C1|C1-D1|D1-F1|A2-C2|C2-D2|D2-F2|A3-C3|C3-D3|D3-F3";

        checker.okNoImportaOrden(9,
                respuestaEsperada.split("[|]"),
                "Se esperaba valorEntero 9",
                "Se esperaba valorString " + respuestaEsperada,
                respuesta,
                this::ordenar
        );
    }

    @Test
    void consultaDisponibilidad_primerClaseSoloConUnaCombinacionDisponibleEnSegundaFilaYCantidadEs2() {
        /*
         * P primera
         * E ejecutiva
         * T tursistica
         */

        final int[][] asientos = {
                //    1  2  3          4  5  6  7          8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
                //    P  P  P  /* - */ E  E  E  E  /* - */ T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T
                /*A*/{O, O, O, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*B*/{N, N, N, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*C*/{O, D, O, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*D*/{O, D, O, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*E*/{N, N, N, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*F*/{O, O, O, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D}
        };

        int cantidad = 2;
        final Retorno respuesta = sistema.consultaDisponibilidad(asientos, 2, Sistema.Clase.Primera);

        checker.okNoImportaOrden(1,
                new String[]{"C2-D2"},
                "Se esperaba valorEntero 1",
                "Se esperaba valorString C2-D2",
                respuesta,
                this::ordenar
        );
    }

    @Test
    void consultaDisponibilidad_primerClaseSoloConUnaCombinacionDisponibleEnPrimerFilaYCantidadEs2() {
        /*
         * P primera
         * E ejecutiva
         * T tursistica
         */

        final int[][] asientos = {
                //    1  2  3          4  5  6  7          8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
                //    P  P  P  /* - */ E  E  E  E  /* - */ T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T
                /*A*/{O, O, O, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*B*/{N, N, N, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*C*/{D, O, O, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*D*/{D, O, O, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*E*/{N, N, N, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*F*/{O, D, O, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D}
        };

        final Retorno respuesta = sistema.consultaDisponibilidad(asientos, 2, Sistema.Clase.Primera);

        checker.okNoImportaOrden(1,
                new String[]{"C1-D1"},
                "Se esperaba valorEntero 1",
                "Se esperaba valorString C1-D1",
                respuesta,
                this::ordenar
        );
    }

    @Test
    void consultaDisponibilidad_primerClaseLlenaYCantidadEs1() {
        /*
         * P primera
         * E ejecutiva
         * T tursistica
         */

        final int[][] asientos = {
                //    1  2  3          4  5  6  7          8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
                //    P  P  P  /* - */ E  E  E  E  /* - */ T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T
                /*A*/{O, O, O, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*B*/{N, N, N, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*C*/{O, O, O, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*D*/{O, O, O, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*E*/{N, N, N, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*F*/{O, O, O, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D}
        };

        final Retorno respuesta = sistema.consultaDisponibilidad(asientos, 1, Sistema.Clase.Primera);

        checker.ok(0,
                "",
                "Se esperaba valorEntero 0",
                "Se esperaba valorString \"\"",
                respuesta);
    }

    @Test
    void consultaDisponibilidad_primerClaseParcialementeLlenaYCantidadEs1() {
        /*
         * P primera
         * E ejecutiva
         * T tursistica
         */

        final int[][] asientos = {
                //    1  2  3          4  5  6  7          8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
                //    P  P  P  /* - */ E  E  E  E  /* - */ T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T
                /*A*/{O, D, O, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*B*/{N, N, N, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*C*/{O, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*D*/{D, O, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*E*/{N, N, N, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*F*/{D, D, O, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D}
        };

        final Retorno respuesta = sistema.consultaDisponibilidad(asientos, 1, Sistema.Clase.Primera);

        String respuestaEsperada = "D1|F1|A2|C2|F2|C3|D3";
        checker.okNoImportaOrden(7,
                respuestaEsperada.split("[|]"),
                "Se esperaba valorEntero 7",
                "Se esperaba valorString " + respuestaEsperada,
                respuesta,
                this::ordenar
        );
    }

    // Clase ejecutiva

    @Test
    void consultaDisponibilidad_claseEjecutivaLibrePeroCantidadCero() {
        /*
         * P primera
         * E ejecutiva
         * T tursistica
         */

        final int[][] asientos = {
                //    1  2  3          4  5  6  7          8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
                //    P  P  P  /* - */ E  E  E  E  /* - */ T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T
                /*A*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*B*/{N, N, N, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*C*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*D*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*E*/{N, N, N, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*F*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D}
        };

        final Retorno respuesta = sistema.consultaDisponibilidad(asientos, 0, Sistema.Clase.Ejecutiva);

        checker.error(ERROR_1, "Se esperaba error ERROR_1", respuesta);
    }

    @Test
    void consultaDisponibilidad_claseEjecutivaLibrePeroCantidadMenorA0() {
        /*
         * P primera
         * E ejecutiva
         * T tursistica
         */

        final int[][] asientos = {
                //    1  2  3          4  5  6  7          8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
                //    P  P  P  /* - */ E  E  E  E  /* - */ T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T
                /*A*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*B*/{N, N, N, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*C*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*D*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*E*/{N, N, N, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*F*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D}
        };

        final Retorno respuesta = sistema.consultaDisponibilidad(asientos, -1, Sistema.Clase.Ejecutiva);

        checker.error(ERROR_1, "Se esperaba error ERROR_1", respuesta);
    }


    @Test
    void consultaDisponibilidad_claseEjecutivaLibrePeroCantidadMayorA6() {
        /*
         * P primera
         * E ejecutiva
         * T tursistica
         */

        final int[][] asientos = {
                //    1  2  3          4  5  6  7          8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
                //    P  P  P  /* - */ E  E  E  E  /* - */ T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T
                /*A*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*B*/{N, N, N, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*C*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*D*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*E*/{N, N, N, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*F*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D}
        };

        final Retorno respuesta = sistema.consultaDisponibilidad(asientos, 7, Sistema.Clase.Ejecutiva);

        checker.ok(0,
                "",
                "Se esperaba valorEntero 0",
                "Se esperaba valorString \"\"",
                respuesta);
    }

    @Test
    void consultaDisponibilidad_claseEjecutivaLibreYCantidadEs6() {
        /*
         * P primera
         * E ejecutiva
         * T tursistica
         */

        final int[][] asientos = {
                //    1  2  3          4  5  6  7          8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
                //    P  P  P  /* - */ E  E  E  E  /* - */ T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T
                /*A*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*B*/{N, N, N, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*C*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*D*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*E*/{N, N, N, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*F*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D}
        };

        final Retorno respuesta = sistema.consultaDisponibilidad(asientos, 6, Sistema.Clase.Ejecutiva);

        String respuestaEsperada = "A4-B4-C4-D4-E4-F4|A5-B5-C5-D5-E5-F5|A6-B6-C6-D6-E6-F6|A7-B7-C7-D7-E7-F7";
        checker.okNoImportaOrden(4,
                respuestaEsperada.split("[|]"),
                "Se esperaba valorEntero 4",
                "Se esperaba valorString " + respuestaEsperada,
                respuesta,
                this::ordenar
        );
    }

    @Test
    void consultaDisponibilidad_claseEjecutivaSoloConCuatroCombinacionesYCantidadEs3() {
        /*
         * P primera
         * E ejecutiva
         * T tursistica
         */

        final int[][] asientos = {
                //    1  2  3          4  5  6  7          8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
                //    P  P  P  /* - */ E  E  E  E  /* - */ T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T
                /*A*/{D, D, D, /* - */ O, O, D, O, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*B*/{N, N, N, /* - */ D, O, D, O, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*C*/{D, D, D, /* - */ D, O, D, O, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*D*/{D, D, D, /* - */ D, O, D, O, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*E*/{N, N, N, /* - */ D, O, O, O, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*F*/{D, D, D, /* - */ O, O, O, O, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D}
        };

        final Retorno respuesta = sistema.consultaDisponibilidad(asientos, 3, Sistema.Clase.Ejecutiva);

        String respuestaEsperada = "B4-C4-D4|C4-D4-E4|A6-B6-C6|B6-C6-D6";
        checker.okNoImportaOrden(4,
                respuestaEsperada.split("[|]"),
                "Se esperaba valorEntero 4",
                "Se esperaba valorString " + respuestaEsperada,
                respuesta,
                this::ordenar
        );
    }

    @Test
    void consultaDisponibilidad_claseEjecutivaSoloConUnaCombinacionDisponibleEnFila4YCantidadEs3() {
        /*
         * P primera
         * E ejecutiva
         * T tursistica
         */

        final int[][] asientos = {
                //    1  2  3          4  5  6  7          8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
                //    P  P  P  /* - */ E  E  E  E  /* - */ T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T
                /*A*/{D, D, D, /* - */ O, O, O, O, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*B*/{N, N, N, /* - */ O, O, O, O, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*C*/{D, D, D, /* - */ O, O, O, O, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*D*/{D, D, D, /* - */ D, O, O, O, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*E*/{N, N, N, /* - */ D, O, O, O, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*F*/{D, D, D, /* - */ D, O, O, O, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D}
        };

        final Retorno respuesta = sistema.consultaDisponibilidad(asientos, 3, Sistema.Clase.Ejecutiva);

        checker.okNoImportaOrden(1,
                new String[]{"D4-E4-F4"},
                "Se esperaba valorEntero 1",
                "Se esperaba valorString D4-E4-F4",
                respuesta,
                this::ordenar
        );
    }

    @Test
    void consultaDisponibilidad_claseEjecutivaSoloConUnaCombinacionDisponibleEnFila5YCantidadEs4() {
        /*
         * P primera
         * E ejecutiva
         * T tursistica
         */

        final int[][] asientos = {
                //    1  2  3          4  5  6  7          8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
                //    P  P  P  /* - */ E  E  E  E  /* - */ T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T
                /*A*/{D, D, D, /* - */ O, O, O, O, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*B*/{N, N, N, /* - */ O, O, O, O, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*C*/{D, D, D, /* - */ O, D, O, O, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*D*/{D, D, D, /* - */ O, D, O, O, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*E*/{N, N, N, /* - */ O, D, O, O, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*F*/{D, D, D, /* - */ O, D, O, O, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D}
        };

        final Retorno respuesta = sistema.consultaDisponibilidad(asientos, 4, Sistema.Clase.Ejecutiva);

        checker.okNoImportaOrden(1,
                new String[]{"C5-D5-E5-F5"},
                "Se esperaba valorEntero 1",
                "Se esperaba valorString C5-D5-E5-F5",
                respuesta,
                this::ordenar
        );
    }

    @Test
    void consultaDisponibilidad_claseEjecutivaSoloConUnaCombinacionDisponibleEnFila6YCantidadEs3() {
        /*
         * P primera
         * E ejecutiva
         * T tursistica
         */

        final int[][] asientos = {
                //    1  2  3          4  5  6  7          8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
                //    P  P  P  /* - */ E  E  E  E  /* - */ T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T
                /*A*/{D, D, D, /* - */ O, O, D, O, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*B*/{N, N, N, /* - */ O, O, D, O, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*C*/{D, D, D, /* - */ O, O, D, O, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*D*/{D, D, D, /* - */ O, O, O, O, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*E*/{N, N, N, /* - */ O, O, O, O, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*F*/{D, D, D, /* - */ O, O, O, O, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D}
        };

        final Retorno respuesta = sistema.consultaDisponibilidad(asientos, 3, Sistema.Clase.Ejecutiva);

        checker.okNoImportaOrden(1,
                new String[]{"A6-B6-C6"},
                "Se esperaba valorEntero 1",
                "Se esperaba valorString A6-B6-C6",
                respuesta,
                this::ordenar
        );
    }

    @Test
    void consultaDisponibilidad_claseEjecutivaSoloConUnaCombinacionDisponibleEnFila7YCantidadEs2() {
        /*
         * P primera
         * E ejecutiva
         * T tursistica
         */

        final int[][] asientos = {
                //    1  2  3          4  5  6  7          8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
                //    P  P  P  /* - */ E  E  E  E  /* - */ T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T
                /*A*/{D, D, D, /* - */ O, O, O, O, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*B*/{N, N, N, /* - */ O, O, O, O, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*C*/{D, D, D, /* - */ O, O, O, O, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*D*/{D, D, D, /* - */ O, O, O, O, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*E*/{N, N, N, /* - */ O, O, O, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*F*/{D, D, D, /* - */ O, O, O, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D}
        };

        final Retorno respuesta = sistema.consultaDisponibilidad(asientos, 2, Sistema.Clase.Ejecutiva);

        checker.okNoImportaOrden(1,
                new String[]{"E7-F7"},
                "Se esperaba valorEntero 1",
                "Se esperaba valorString E7-F7",
                respuesta,
                this::ordenar
        );
    }

    @Test
    void consultaDisponibilidad_claseEjecutivaParcialementeLlenaYCantidadEs1() {
        /*
         * P primera
         * E ejecutiva
         * T tursistica
         */

        final int[][] asientos = {
                //    1  2  3          4  5  6  7          8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
                //    P  P  P  /* - */ E  E  E  E  /* - */ T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T
                /*A*/{O, D, O, /* - */ O, O, O, O, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*B*/{N, N, N, /* - */ O, D, O, O, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*C*/{O, D, D, /* - */ O, O, D, O, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*D*/{D, O, D, /* - */ D, O, O, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*E*/{N, N, N, /* - */ O, D, O, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*F*/{D, D, O, /* - */ D, O, D, O, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
        };

        final Retorno respuesta = sistema.consultaDisponibilidad(asientos, 1, Sistema.Clase.Ejecutiva);

        String respuestaEsperada = "D4|F4|B5|E5|C6|F6|D7|E7";
        checker.okNoImportaOrden(8,
                respuestaEsperada.split("[|]"),
                "Se esperaba valorEntero 8",
                "Se esperaba valorString " + respuestaEsperada,
                respuesta,
                this::ordenar
        );
    }

    // Clase turista

    @Test
    void consultaDisponibilidad_claseTuristaLibrePeroCantidadCero() {
        /*
         * P primera
         * E ejecutiva
         * T tursistica
         */

        final int[][] asientos = {
                //    1  2  3          4  5  6  7          8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
                //    P  P  P  /* - */ E  E  E  E  /* - */ T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T
                /*A*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*B*/{N, N, N, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*C*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*D*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*E*/{N, N, N, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*F*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D}
        };

        final Retorno respuesta = sistema.consultaDisponibilidad(asientos, 0, Sistema.Clase.Turista);

        checker.error(ERROR_1, "Se esperaba ERROR_1", respuesta);
    }

    @Test
    void consultaDisponibilidad_claseTuristaLibrePeroCantidadMenorA0() {
        /*
         * P primera
         * E ejecutiva
         * T tursistica
         */

        final int[][] asientos = {
                //    1  2  3          4  5  6  7          8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
                //    P  P  P  /* - */ E  E  E  E  /* - */ T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T
                /*A*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*B*/{N, N, N, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*C*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*D*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*E*/{N, N, N, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*F*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D}
        };

        final Retorno respuesta = sistema.consultaDisponibilidad(asientos, -1, Sistema.Clase.Turista);

        checker.error(ERROR_1, "Se esperaba ERROR_1", respuesta);
    }


    @Test
    void consultaDisponibilidad_claseTuristaLibrePeroCantidadMayorA6() {
        /*
         * P primera
         * E ejecutiva
         * T tursistica
         */

        final int[][] asientos = {
                //    1  2  3          4  5  6  7          8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
                //    P  P  P  /* - */ E  E  E  E  /* - */ T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T
                /*A*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*B*/{N, N, N, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*C*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*D*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*E*/{N, N, N, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*F*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D}
        };

        final Retorno respuesta = sistema.consultaDisponibilidad(asientos, 7, Sistema.Clase.Turista);

        checker.ok(0,
                "",
                "Se esperaba valorEntero 0",
                "Se esperaba valorString \"\"",
                respuesta);
    }

    @Test
    void consultaDisponibilidad_claseTuristaLibreYCantidadEs6() {
        /*
         * P primera
         * E ejecutiva
         * T tursistica
         */

        final int[][] asientos = {
                //    1  2  3          4  5  6  7          8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
                //    P  P  P  /* - */ E  E  E  E  /* - */ T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T
                /*A*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*B*/{N, N, N, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*C*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*D*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*E*/{N, N, N, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*F*/{D, D, D, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D}
        };

        final Retorno respuesta = sistema.consultaDisponibilidad(asientos, 6, Sistema.Clase.Turista);

        String respuestaEsperada = "A8-B8-C8-D8-E8-F8|A9-B9-C9-D9-E9-F9|" +
                "A10-B10-C10-D10-E10-F10|A11-B11-C11-D11-E11-F11|" +
                "A12-B12-C12-D12-E12-F12|A13-B13-C13-D13-E13-F13|" +
                "A14-B14-C14-D14-E14-F14|A15-B15-C15-D15-E15-F15|" +
                "A16-B16-C16-D16-E16-F16|A17-B17-C17-D17-E17-F17|" +
                "A18-B18-C18-D18-E18-F18|A19-B19-C19-D19-E19-F19|" +
                "A20-B20-C20-D20-E20-F20|A21-B21-C21-D21-E21-F21|" +
                "A22-B22-C22-D22-E22-F22|A23-B23-C23-D23-E23-F23|" +
                "A24-B24-C24-D24-E24-F24|A25-B25-C25-D25-E25-F25|" +
                "A26-B26-C26-D26-E26-F26";
        String[] valorStringEsperado = respuestaEsperada.split("[|]");
        checker.okNoImportaOrden(19,
                valorStringEsperado,
                "Se esperaba valorEntero 19",
                "Se esperaba valorString " + respuestaEsperada,
                respuesta,
                this::ordenar
        );
    }

    @Test
    void consultaDisponibilidad_claseTuristaSoloConCuatroCombinacionesYCantidadEs3() {
        /*
         * P primera
         * E ejecutiva
         * T tursistica
         */

        final int[][] asientos = {
                //    1  2  3          4  5  6  7          8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
                //    P  P  P  /* - */ E  E  E  E  /* - */ T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T
                /*A*/{D, D, D, /* - */ O, O, D, O, /* - */ O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O},
                /*B*/{N, N, N, /* - */ D, O, D, O, /* - */ O, D, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O},
                /*C*/{D, D, D, /* - */ D, O, D, O, /* - */ O, D, O, O, O, O, O, O, O, D, O, O, O, O, O, O, O, O, O},
                /*D*/{D, D, D, /* - */ D, O, D, O, /* - */ O, D, O, O, O, O, O, O, O, D, O, O, O, O, O, O, O, O, O},
                /*E*/{N, N, N, /* - */ D, O, O, O, /* - */ O, D, O, O, O, O, O, O, O, D, O, O, O, O, O, O, O, O, O},
                /*F*/{D, D, D, /* - */ O, O, O, O, /* - */ O, O, O, O, O, O, O, O, O, D, O, O, O, O, O, O, O, O, O}
        };

        final Retorno respuesta = sistema.consultaDisponibilidad(asientos, 3, Sistema.Clase.Turista);

        String respuestaEsperada = "B9-C9-D9|C9-D9-E9|C17-D17-E17|D17-E17-F17";
        checker.okNoImportaOrden(4,
                respuestaEsperada.split("[|]"),
                "Se esperaba valorEntero 4",
                "Se esperaba valorString " + respuestaEsperada,
                respuesta,
                this::ordenar
        );
    }

    @Test
    void consultaDisponibilidad_claseTuristaSoloConUnaCombinacionDisponibleEnFila10YCantidadEs3() {
        /*
         * P primera
         * E ejecutiva
         * T tursistica
         */

        final int[][] asientos = {
                //    1  2  3          4  5  6  7          8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
                //    P  P  P  /* - */ E  E  E  E  /* - */ T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T
                /*A*/{D, D, D, /* - */ O, O, O, O, /* - */ O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O},
                /*B*/{N, N, N, /* - */ O, O, O, O, /* - */ O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O},
                /*C*/{D, D, D, /* - */ O, O, O, O, /* - */ O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O},
                /*D*/{D, D, D, /* - */ D, O, O, O, /* - */ O, O, D, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O},
                /*E*/{N, N, N, /* - */ D, O, O, O, /* - */ O, O, D, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O},
                /*F*/{D, D, D, /* - */ D, O, O, O, /* - */ O, O, D, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O}
        };

        final Retorno respuesta = sistema.consultaDisponibilidad(asientos, 3, Sistema.Clase.Turista);

        checker.okNoImportaOrden(1,
                new String[]{"D10-E10-F10"},
                "Se esperaba valorEntero 1",
                "Se esperaba valorString D10-E10-F10",
                respuesta,
                this::ordenar
        );
    }

    @Test
    void consultaDisponibilidad_claseTuristaSoloConUnaCombinacionDisponibleEnFila25YCantidadEs3() {
        /*
         * P primera
         * E ejecutiva
         * T tursistica
         */

        final int[][] asientos = {
                //    1  2  3          4  5  6  7          8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
                //    P  P  P  /* - */ E  E  E  E  /* - */ T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T
                /*A*/{D, D, D, /* - */ O, O, O, O, /* - */ O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O},
                /*B*/{N, N, N, /* - */ O, O, O, O, /* - */ O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, D, O},
                /*C*/{D, D, D, /* - */ O, O, O, O, /* - */ O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, D, O},
                /*D*/{D, D, D, /* - */ D, O, O, O, /* - */ O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, D, O},
                /*E*/{N, N, N, /* - */ D, O, O, O, /* - */ O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O},
                /*F*/{D, D, D, /* - */ D, O, O, O, /* - */ O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O}
        };

        final Retorno respuesta = sistema.consultaDisponibilidad(asientos, 3, Sistema.Clase.Turista);

        checker.okNoImportaOrden(1,
                new String[]{"B25-C25-D25"},
                "Se esperaba valorEntero 1",
                "Se esperaba valorString B25-C25-D25",
                respuesta,
                this::ordenar
        );
    }

    @Test
    void consultaDisponibilidad_claseTuristaSoloConUnaCombinacionDisponibleEnFila26YCantidadEs3() {
        /*
         * P primera
         * E ejecutiva
         * T tursistica
         */

        final int[][] asientos = {
                //    1  2  3          4  5  6  7          8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
                //    P  P  P  /* - */ E  E  E  E  /* - */ T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T
                /*A*/{D, D, D, /* - */ O, O, O, O, /* - */ O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, D},
                /*B*/{N, N, N, /* - */ O, O, O, O, /* - */ O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, D},
                /*C*/{D, D, D, /* - */ O, O, O, O, /* - */ O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, D},
                /*D*/{D, D, D, /* - */ D, O, O, O, /* - */ O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O},
                /*E*/{N, N, N, /* - */ D, O, O, O, /* - */ O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O},
                /*F*/{D, D, D, /* - */ D, O, O, O, /* - */ O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O}
        };

        final Retorno respuesta = sistema.consultaDisponibilidad(asientos, 3, Sistema.Clase.Turista);

        checker.okNoImportaOrden(1,
                new String[]{"A26-B26-C26"},
                "Se esperaba valorEntero 1",
                "Se esperaba valorString A26-B26-C26",
                respuesta,
                this::ordenar
        );

    }

    @Test
    void consultaDisponibilidad_claseTuristaParcialementeLlenaYCantidadEs1() {
        /*
         * P primera
         * E ejecutiva
         * T tursistica
         */

        final int[][] asientos = {
                //    1  2  3          4  5  6  7          8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
                //    P  P  P  /* - */ E  E  E  E  /* - */ T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T
                /*A*/{O, D, O, /* - */ O, O, O, O, /* - */ O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O},
                /*B*/{N, N, N, /* - */ O, D, O, O, /* - */ O, O, D, O, O, O, O, O, D, O, O, O, O, O, O, D, O, O, O},
                /*C*/{O, D, D, /* - */ O, O, D, O, /* - */ O, O, O, O, D, O, O, O, O, D, O, O, O, D, O, O, O, O, O},
                /*D*/{D, O, D, /* - */ D, O, O, D, /* - */ O, O, O, O, O, O, O, D, O, O, O, O, O, O, O, O, O, D, O},
                /*E*/{N, N, N, /* - */ O, D, O, D, /* - */ O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O},
                /*F*/{D, D, O, /* - */ D, O, D, O, /* - */ O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O},
        };

        final Retorno respuesta = sistema.consultaDisponibilidad(asientos, 1, Sistema.Clase.Turista);

        String respuestaEsperada = "B10|C12|D15|B16|C17|C21|B23|D25";
        checker.okNoImportaOrden(8,
                respuestaEsperada.split("[|]"),
                "Se esperaba valorEntero 8",
                "Se esperaba valorString " + respuestaEsperada,
                respuesta,
                this::ordenar
        );
    }

    /**
     * Ordena la entrada alfabeticamente, luego de haberla divido por "-", y devuelve un nuevo string ordenado.
     * Ej: dado "C4-D4-B4", retorna "B4-C4-D4"
     *
     * @param respuesta
     * @return
     */
    private String ordenar(String respuesta) {
        if (respuesta == null) {
            return null;
        }
        return Arrays.stream(respuesta.split("-")).sorted().collect(Collectors.joining("-"));
    }
}
