package uy.edu.ort.aed2.obligatorio.tests;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uy.edu.ort.aed2.obligatorio.Retorno;
import uy.edu.ort.aed2.obligatorio.Sistema;
import uy.edu.ort.aed2.obligatorio.SistemaImp;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PasajerosTests {
    private Sistema sistema;
    private Checker<TestPasajeroNoUsar> checker;

    @BeforeEach
    public void initialize() {
        sistema = new SistemaImp();
        sistema.inicializarSistema(200);
        checker = new Checker<>(TestPasajeroNoUsar::parse);
    }


    /**
     * Registra pasajeros validos
     */
    @Test
    public void testRegistrarPasajero_ok() {

        checker.ok(sistema.registrarPasajero("3.222.422-2", "Pepe", "551115-055", Sistema.Categoria.G));
        checker.ok(sistema.registrarPasajero("222.422-2", "Roberto", "5255-055", Sistema.Categoria.G));
        checker.ok(sistema.registrarPasajero("5.232.422-2", "Maria", "5525-032355", Sistema.Categoria.G));
        checker.ok(sistema.registrarPasajero("1.222.422-2", "Giussepe", "55225-055", Sistema.Categoria.F));
        checker.ok(sistema.registrarPasajero("101.422-2", "Paul", "121-055", Sistema.Categoria.F));
    }

    /**
     * Registra algunos pasajeros validos, y otros con cedulas invalidos
     */
    @Test
    public void testRegistrarPasajeroValidacionCedula_error() {
        checker.ok(sistema.registrarPasajero("3.222.422-2", "Pepe", "551115-055", Sistema.Categoria.A));

        checker.error(
                Retorno.Resultado.ERROR_2, "Cedula invalida",
                sistema.registrarPasajero("422-2", "Roberto", "5255-055", Sistema.Categoria.B));

        checker.error(
                Retorno.Resultado.ERROR_2, "Cedula invalida",
                sistema.registrarPasajero("022.422-2", "Roberto", "5255-055", Sistema.Categoria.B));

        checker.error(
                Retorno.Resultado.ERROR_2, "Cedula invalida",
                sistema.registrarPasajero("8.22.422-2", "Roberto", "5255-055", Sistema.Categoria.B));

        checker.error(
                Retorno.Resultado.ERROR_2, "Cedula invalida",
                sistema.registrarPasajero("13434700", "Roberto", "5255-055", Sistema.Categoria.B));
        checker.error(
                Retorno.Resultado.ERROR_2, "Cedula invalida",
                sistema.registrarPasajero("0.322.111-0", "Roberto", "5255-055", Sistema.Categoria.B));
        checker.error(
                Retorno.Resultado.ERROR_2, "Cedula invalida",
                sistema.registrarPasajero("0.blabla.111-0", "Roberto", "5255-055", Sistema.Categoria.B));
        checker.ok(
                sistema.registrarPasajero("2.333.222-3", "Roberto", "5255-055", Sistema.Categoria.B));
    }

    /**
     * Registra algunos pasajeros validos, y otros con campos vacios o nulos
     */
    @Test
    public void testRegistrarPasajeroValidacionCampos_error() {
        checker.ok(sistema.registrarPasajero("3.222.422-2", "Pepe", "551115-055", Sistema.Categoria.A));

        checker.error(
                Retorno.Resultado.ERROR_1, "La cedula es nula",
                sistema.registrarPasajero(null, "Roberto", "5255-055", Sistema.Categoria.B));

        checker.error(
                Retorno.Resultado.ERROR_1, "Telefono vacio",
                sistema.registrarPasajero("4.344.982-1", "Roberto", "", Sistema.Categoria.B));

        checker.error(
                Retorno.Resultado.ERROR_1, "Nombre nulo",
                sistema.registrarPasajero("4.111.111-2", null, "5255-055", Sistema.Categoria.B));

        checker.error(
                Retorno.Resultado.ERROR_1, "categoria nula",
                sistema.registrarPasajero("1.232.222-3", "Roberto", "5255-055", null));
        checker.ok(
                sistema.registrarPasajero("2.333.222-3", "Roberto", "5255-055", Sistema.Categoria.B));
    }

    /**
     * Registra algunos pasajeros validos, otros duplicados y otros invalidos
     */
    @Test
    public void testRegistrarPasajeroDuplicados_error() {
        checker.ok(sistema.registrarPasajero("1.222.422-2", "Pepe", "551115-055", Sistema.Categoria.A));
        checker.ok(sistema.registrarPasajero("2.222.422-2", "Pepe", "551115-055", Sistema.Categoria.A));
        checker.ok(sistema.registrarPasajero("5.211.222-2", "Pepe", "551115-055", Sistema.Categoria.A));
        checker.ok(sistema.registrarPasajero("1.222.322-2", "Pepe", "551115-055", Sistema.Categoria.A));
        checker.ok(sistema.registrarPasajero("2.222.522-2", "Pepe", "551115-055", Sistema.Categoria.A));

        checker.ok(sistema.registrarPasajero("3.222.422-2", "Pepe", "551115-055", Sistema.Categoria.A));

        checker.error(
                Retorno.Resultado.ERROR_2, "Cedula invalida",
                sistema.registrarPasajero("422-2", "Roberto", "5255-055", Sistema.Categoria.B));

        checker.error(Retorno.Resultado.ERROR_3, "Ya fue registrado, tiene que dar ERROR_3",
                sistema.registrarPasajero("3.222.422-2", "Pepe", "551115-055", Sistema.Categoria.A));
        checker.error(Retorno.Resultado.ERROR_3, "Ya fue registrado, tiene que dar ERROR_3",
                sistema.registrarPasajero("5.211.222-2", "Pepe", "551115-055", Sistema.Categoria.A));
        checker.error(
                Retorno.Resultado.ERROR_2, "Si te dio diferente no deberias haberlo registrado antes",
                sistema.registrarPasajero("422-2", "Roberto", "5255-055", Sistema.Categoria.B));
    }

    /**
     * Registra algunos pasajeros validos, otros invalidos y los busca
     */
    @Test
    public void testBuscarPasajeros_ok() {

        checker.ok(sistema.registrarPasajero("3.222.422-2", "Pepe", "551115-055", Sistema.Categoria.A));
        checker.ok(sistema.registrarPasajero("222.422-2", "Pepe", "551115-055", Sistema.Categoria.A));
        checker.ok(sistema.registrarPasajero("1.211.222-2", "Pepe", "551115-055", Sistema.Categoria.A));
        checker.ok(sistema.registrarPasajero("522.322-2", "Maria", "551115-055", Sistema.Categoria.C));
        checker.ok(sistema.registrarPasajero("5.222.522-2", "Gloria", "551115-055", Sistema.Categoria.A));
        checker.ok(sistema.registrarPasajero("6.222.522-2", "Victtoria", "551115-055", Sistema.Categoria.A));
        checker.ok(sistema.registrarPasajero("7.222.522-2", "roberto", "03031452", Sistema.Categoria.D));


        checker.ok(1, TestPasajeroNoUsar.of("3.222.422-2", "Pepe", "551115-055", Sistema.Categoria.A),
                "La cantidad de operaciones tiene que ser 1 porque deberia ser la raiz", "Los datos de la persona no coinciden",
                sistema.buscarPasajero("3.222.422-2"));

        checker.ok(4, TestPasajeroNoUsar.of("522.322-2", "Maria", "551115-055", Sistema.Categoria.C),
                "La cantidad de operaciones no coincide, 3.222.. es la raiz, a la izq esta 222.4222.., a su derecha 1.211, a su izquierda 522... son 4", "Los dattos de la persona no coinciden",
                sistema.buscarPasajero("522.322-2"));

        checker.ok(4, TestPasajeroNoUsar.of("7.222.522-2", "roberto", "03031452", Sistema.Categoria.D),
                "La cantidad de operaciones no coincide, 3.222.. es la raiz, a la der esta 5.222.., a su derecha 6.222, a su der 7.222... son 4", "Los dattos de la persona no coinciden",
                sistema.buscarPasajero("7.222.522-2"));

    }

    /**
     * Tratamos de registrar pasajeros invalidos  y buscarlos, buscamos tambien ppasajeros que no estan registrados
     */
    @Test
    public void testBuscarPasajeros_error() {

        checker.ok(sistema.registrarPasajero("3.222.422-2", "Pepe", "551115-055", Sistema.Categoria.A));
        checker.ok(sistema.registrarPasajero("222.422-2", "Pepe", "551115-055", Sistema.Categoria.A));
        checker.ok(sistema.registrarPasajero("522.322-2", "Maria", "551115-055", Sistema.Categoria.C));
        checker.error(Retorno.Resultado.ERROR_2, "Cedula invalida",
                sistema.registrarPasajero("0.122.322-2", "Maria Eugenia", "12342-222", Sistema.Categoria.C));
        checker.error(Retorno.Resultado.ERROR_1, "Campo vacio",
                sistema.registrarPasajero("4.212.322-2", "Maria Eugenia", null, Sistema.Categoria.C));

        checker.ok(sistema.registrarPasajero("5.222.522-2", "Gloria", "551115-055", Sistema.Categoria.A));
        checker.ok(sistema.registrarPasajero("7.222.522-2", "roberto", "03031452", Sistema.Categoria.D));
        checker.ok(sistema.registrarPasajero("1.211.222-2", "Pepe", "551115-055", Sistema.Categoria.A));
        checker.ok(sistema.registrarPasajero("6.222.522-2", "Victtoria", "551115-055", Sistema.Categoria.A));

        checker.ok(3, TestPasajeroNoUsar.of("522.322-2", "Maria", "551115-055", Sistema.Categoria.C),
                "La cantidad de operaciones no coincide, 3.222.. es la raiz, a la izq esta 222.4222.., a su derecha 522... son 3", "Los dattos de la persona no coinciden",
                sistema.buscarPasajero("522.322-2"));

        checker.error(Retorno.Resultado.ERROR_1, "La cedula es invalida",
                sistema.buscarPasajero("0.122.322-2"));
        checker.error(Retorno.Resultado.ERROR_1, "La cedula es invalida",
                sistema.buscarPasajero("22.322-2"));

        checker.error(Retorno.Resultado.ERROR_2, "No se deberia haber registrado ttiene campo vacio",
                sistema.buscarPasajero("4.212.322-2"));

        checker.error(Retorno.Resultado.ERROR_2, "No existe",
                sistema.buscarPasajero("9.212.322-2"));
    }

    /**
     * Tratamos de registrar pasajeros invalidos  y buscarlos, buscamos tambien ppasajeros que no estan registrados
     */
    @Test
    public void testBuscarPasajerosOrdenados_ok() {
        checker.ok(0, new TestPasajeroNoUsar[]{}, "la cantidad debe ser la cantidad que hay hasta ahora: 0", "Tiene que ser vacio",
                sistema.listarPasajerosAscendente());
        checker.ok(0, new TestPasajeroNoUsar[]{}, "la cantidad debe ser la cantidad que hay hasta ahora: 0", "Tiene que ser vacio",
                sistema.listarPasajerosDescendente());
        //agrego datos
        checker.ok(sistema.registrarPasajero("1.211.222-2", "Pepe", "551115-055", Sistema.Categoria.A));
        checker.ok(sistema.registrarPasajero("222.422-2", "Pepe", "551115-055", Sistema.Categoria.A));
        checker.ok(sistema.registrarPasajero("6.222.522-2", "Victtoria", "551115-055", Sistema.Categoria.A));

        checker.error(Retorno.Resultado.ERROR_2, "Cedula invalida",
                sistema.registrarPasajero("0.122.322-2", "Maria Eugenia", "12342-222", Sistema.Categoria.C));

        checker.ok(sistema.registrarPasajero("522.322-2", "Maria", "551115-055", Sistema.Categoria.C));

        checker.error(Retorno.Resultado.ERROR_1, "Campo vacio",
                sistema.registrarPasajero("4.212.322-2", "Maria Eugenia", null, Sistema.Categoria.C));

        checker.ok(sistema.registrarPasajero("5.222.522-2", "Gloria", "551115-055", Sistema.Categoria.A));
        checker.ok(sistema.registrarPasajero("3.222.422-2", "Pepe", "551115-055", Sistema.Categoria.A));
        checker.ok(sistema.registrarPasajero("7.222.522-2", "roberto", "03031452", Sistema.Categoria.D));

        TestPasajeroNoUsar[] pasajerosOrdAscendente = new TestPasajeroNoUsar[]{
                TestPasajeroNoUsar.of("222.422-2", "Pepe", "551115-055", Sistema.Categoria.A),
                TestPasajeroNoUsar.of("522.322-2", "Maria", "551115-055", Sistema.Categoria.C),
                TestPasajeroNoUsar.of("1.211.222-2", "Pepe", "551115-055", Sistema.Categoria.A),
                TestPasajeroNoUsar.of("3.222.422-2", "Pepe", "551115-055", Sistema.Categoria.A),
                //TestPasajeroNoUsar.of("4.212.322-2", "Maria Eugenia", null, Sistema.Categoria.C),
                TestPasajeroNoUsar.of("5.222.522-2", "Gloria", "551115-055", Sistema.Categoria.A),
                TestPasajeroNoUsar.of("6.222.522-2", "Victtoria", "551115-055", Sistema.Categoria.A),
                TestPasajeroNoUsar.of("7.222.522-2", "roberto", "03031452", Sistema.Categoria.D)
        };

        checker.ok(0, pasajerosOrdAscendente,
                "La cantidad es la cantidad de los que registramos exitosamente", "El orden esta mal, chequear ord lexicografico vs numerico",
                sistema.listarPasajerosAscendente());
        checker.ok(0, reverse(pasajerosOrdAscendente),
                "La cantidad es la cantidad de los que registramos exitosamente", "El orden esta mal, chequear ord lexicografico vs numerico",
                sistema.listarPasajerosDescendente());


    }

    @Test
    public void listarPasajerosPorCategoria_ok() {

        checker.ok(sistema.registrarPasajero("1.211.222-2", "Pepe", "551115-055", Sistema.Categoria.A));
        checker.ok(sistema.registrarPasajero("222.422-2", "Pepe", "551115-055", Sistema.Categoria.A));
        checker.ok(sistema.registrarPasajero("6.222.522-2", "Victtoria", "551115-055", Sistema.Categoria.A));
        checker.ok(sistema.registrarPasajero("234.888-2", "Defensa1", "551115-055", Sistema.Categoria.F));
        checker.ok(sistema.registrarPasajero("9.222.522-2", "Defensa2", "551115-055", Sistema.Categoria.G));

        checker.error(Retorno.Resultado.ERROR_2, "Cedula invalida",
                sistema.registrarPasajero("0.122.322-2", "Maria Eugenia", "12342-222", Sistema.Categoria.C));

        checker.ok(sistema.registrarPasajero("522.322-2", "Maria", "551115-055", Sistema.Categoria.C));

        checker.error(Retorno.Resultado.ERROR_1, "Campo vacio",
                sistema.registrarPasajero("4.212.322-2", "Maria Eugenia", null, Sistema.Categoria.C));

        checker.ok(sistema.registrarPasajero("5.222.522-2", "Gloria", "551115-055", Sistema.Categoria.A));
        checker.ok(sistema.registrarPasajero("3.222.422-2", "Pepe", "551115-055", Sistema.Categoria.A));
        checker.ok(sistema.registrarPasajero("7.222.522-2", "roberto", "03031452", Sistema.Categoria.D));

        checker.ok(0, TestPasajeroNoUsar.of("7.222.522-2", "roberto", "03031452", Sistema.Categoria.D),
                "Solo registramos uno en la D", "Los datos no coinciden",
                sistema.listarPasajerosPorCategoría(Sistema.Categoria.D));
        checker.ok(0, new TestPasajeroNoUsar[]{},
                "No hay pasajeros en la b", "No hay pasajeros en la b",
                sistema.listarPasajerosPorCategoría(Sistema.Categoria.B));
        checker.okNoImportaOrden(0, new TestPasajeroNoUsar[]{
                        TestPasajeroNoUsar.of("1.211.222-2", "Pepe", "551115-055", Sistema.Categoria.A),
                        TestPasajeroNoUsar.of("222.422-2", "Pepe", "551115-055", Sistema.Categoria.A),
                        TestPasajeroNoUsar.of("6.222.522-2", "Victtoria", "551115-055", Sistema.Categoria.A),
                        TestPasajeroNoUsar.of("5.222.522-2", "Gloria", "551115-055", Sistema.Categoria.A),
                        TestPasajeroNoUsar.of("3.222.422-2", "Pepe", "551115-055", Sistema.Categoria.A)
                },
                "Los que registramos correctamente en la A son 5", "Los datos no coinciden",
                sistema.listarPasajerosPorCategoría(Sistema.Categoria.A),
                TestPasajeroNoUsar::parse);

        checker.ok(0, TestPasajeroNoUsar.of("234.888-2", "Defensa1", "551115-055", Sistema.Categoria.F),
                "Solo registramos uno en la F", "Los datos no coinciden",
                sistema.listarPasajerosPorCategoría(Sistema.Categoria.F));
        checker.ok(0, TestPasajeroNoUsar.of("9.222.522-2", "Defensa2", "551115-055", Sistema.Categoria.G),
                "Solo registramos uno en la G", "Los datos no coinciden",
                sistema.listarPasajerosPorCategoría(Sistema.Categoria.G));
    }

    /**
     * Este test busca agregar cosas aleatorias esperando que la implementacion este correcta,
     * idealmente loo van a querer correr cuando este terminado el modulo de pasajeros.
     * Tambien como es aleatorio corranlo varias veces.
     */
    @Test
    public void prubasAleatorias() {
        final int CANTIDAD_A_GENERAR = 4_000;
        final int CEDULA_INICIAL = 100_000_0;
        final int CEDULA_MAX = 9_999_999_9;

        int[] cedulas = new int[CANTIDAD_A_GENERAR];
        TestPasajeroNoUsar[] pasajerosOrdenados = new TestPasajeroNoUsar[CANTIDAD_A_GENERAR];
        Random rand = new Random();
        long semilla = rand.nextLong();
        rand.setSeed(semilla);// ESTO ES POR SI LO QUIEREN REPRODUCIR, poner un print, cambian el seed por el valor de la consola
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Semilla " + semilla);

        int cedulaGeneradaActual = CEDULA_INICIAL;

        //Generamos CANTIDAD_A_GENERAR cedulas aleatorias ordenadas
        for (int i = 0; i < CANTIDAD_A_GENERAR; i++) {
            cedulaGeneradaActual += rand.nextInt((CEDULA_MAX - CEDULA_INICIAL) / CANTIDAD_A_GENERAR) + 1;//Basicamente este bound lo ponemos para noo generar cedulas invalidas
            cedulas[i] = cedulaGeneradaActual;
            pasajerosOrdenados[i] = TestPasajeroNoUsar.of(formatearCedula(cedulaGeneradaActual), "Unnombre", "2232", Sistema.Categoria.C);
        }

        int[] cedulasDesOrdenadas = shuffle(cedulas, rand); //Barajamos las cedulas


        for (int i = 0; i < cedulasDesOrdenadas.length; i++) {
            checker.ok("No pude registrar la cedula '" + formatearCedula(cedulasDesOrdenadas[i]) + "'",
                    sistema.registrarPasajero(formatearCedula(cedulasDesOrdenadas[i]), "Unnombre", "2232", Sistema.Categoria.C));
        }
        double cantidadTotalDeAccesos=0;
        cedulasDesOrdenadas = shuffle(cedulas, rand); //Barajamos las cedulas
        for (int i = 0; i < cedulasDesOrdenadas.length; i++) {
            var resultado=sistema.buscarPasajero(formatearCedula(cedulasDesOrdenadas[i]));
            checker.ok("No encontre la cedula que registre'" + formatearCedula(cedulasDesOrdenadas[i]) + "'",
                    resultado);
            cantidadTotalDeAccesos+=resultado.valorEntero;

        }
        int n=cedulasDesOrdenadas.length;
        double promedioDeAccesos=cantidadTotalDeAccesos/cedulasDesOrdenadas.length;
        Logger.getAnonymousLogger().info(String.format("La cantidad promedio de accesos es %.2f contra el de una lista sería %.2f",
                promedioDeAccesos,
                n*(n+1)/(2f*n))); // formula de Gauss suma de 1 a n es n*(n+1)/2
        Assertions.assertEquals(cantidadEsperadaAccesos(n),promedioDeAccesos,5);
        // 5 es un numero magico pero no se deberia ir mas de eso mientras se mantengan en una cantidad esperada.
        // Si ven que es 10 la diff to-do bien si las cantidades son muy grandes, ahora no se puede ir mucho mas de 100
        // si pasa esto algo hay mal.

        checker.ok(0, pasajerosOrdenados,
                "No es la cantidad esperada", "No estan ordenados",
                sistema.listarPasajerosAscendente());
        checker.ok(0, reverse(pasajerosOrdenados),
                "No es la cantidad esperada", "No estan ordenados",
                sistema.listarPasajerosDescendente());


    }

    //No tienen porque leer este método.
    private double cantidadEsperadaAccesos(int n){
        //REsultado teorico de la profundidad media  de un arbol aleatorio

        double hN=0;//Calculamos el numero harmonico.
        for (int i = 1; i < n; i++) {
            hN+=1d/i;
        }


        //Creanme que esto es de orden O(log n)
        // sino creen es porque Hn~~ 0.57... + log(n) cuando n es grande, log es neperiano
        // entonces queda ((0.5+log(n)*(n+1)-4*n)/n
        //simplificando queda (0.5+log(n))-4 +(0.5+log(n))/n que es cercano a 0.5+log(n)-4 que en O queda O(log(n))
        return (hN*2*(n+1)-4*n)/n;


    }

    private String formatearCedula(int cedula) {
        DecimalFormat df = new DecimalFormat("#,###,###", DecimalFormatSymbols.getInstance(Locale.forLanguageTag("es")));
        return df.format(cedula / 10) + "-" + cedula % 10;
    }

    private int[] shuffle(int[] cedulas, Random rand) {
        int[] copy = Arrays.copyOf(cedulas, cedulas.length);
        for (int i = 0; i < copy.length; i++) {
            int aux = copy[i];
            int baraje = rand.nextInt(copy.length);
            copy[i] = copy[baraje];
            copy[baraje] = aux;
        }
        return copy;
    }

    private TestPasajeroNoUsar[] reverse(TestPasajeroNoUsar[] pasajerosOrdAscendente) {
        TestPasajeroNoUsar[] result = new TestPasajeroNoUsar[pasajerosOrdAscendente.length];
        for (int i = 0; i < pasajerosOrdAscendente.length; i++) {
            result[pasajerosOrdAscendente.length - i - 1] = pasajerosOrdAscendente[i];
        }
        return result;
    }
}
