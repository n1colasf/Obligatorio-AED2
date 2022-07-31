package uy.edu.ort.aed2.obligatorio.tests;

import org.junit.jupiter.api.BeforeEach;
import uy.edu.ort.aed2.obligatorio.Retorno;
import uy.edu.ort.aed2.obligatorio.Sistema;
import uy.edu.ort.aed2.obligatorio.SistemaImp;

public abstract class GrafoAeropuertosBaseTest {
    private Checker<TestAeropuertoNoUsar> checker;
    protected Sistema sistema;

    protected final TestAeropuertoNoUsar montevideo = new TestAeropuertoNoUsar("MVD", "Montevideo");
    protected final TestAeropuertoNoUsar buenosAires = new TestAeropuertoNoUsar("BSAS", "Buenos aires");
    protected final TestAeropuertoNoUsar paris = new TestAeropuertoNoUsar("PARIS", "Paris");
    protected final TestAeropuertoNoUsar madrid = new TestAeropuertoNoUsar("MADRID", "Madrid");
    protected final TestAeropuertoNoUsar rioDeJaneiro = new TestAeropuertoNoUsar("RIOJAN", "Rio de Janeiro");
    protected final TestAeropuertoNoUsar santiagoDeChile = new TestAeropuertoNoUsar("SANTIAGOCHILE", "Santiago de chile");

    @BeforeEach
    public void initialize() {
        checker = new Checker<>(TestAeropuertoNoUsar::parsear);
        sistema = new SistemaImp();
        sistema.inicializarSistema(100);
    }

    protected void registrarTodosLosAeroppuertosValidos() {
        checker.ok(registrarAeropuerto(montevideo));
        checker.ok(registrarAeropuerto(buenosAires));
        checker.ok(registrarAeropuerto(paris));
        checker.ok(registrarAeropuerto(madrid));
        checker.ok(registrarAeropuerto(rioDeJaneiro));
        checker.ok(registrarAeropuerto(santiagoDeChile));
    }
    protected Retorno registrarVuelo(TestAeropuertoNoUsar origen, TestAeropuertoNoUsar destino, String codigoVuelo, double combustible, double minutos, double costoEnDolares) {
        return sistema.registrarVuelo(new String(origen.getCodigo()),new String(destino.getCodigo()),new String(codigoVuelo),combustible,minutos,costoEnDolares);
    }

    protected Retorno registrarConexion(TestAeropuertoNoUsar origen, TestAeropuertoNoUsar destino, double km) {
        return sistema.registrarConexion(new String(origen.getCodigo()),new String(destino.getCodigo()),km);
    }


    protected Retorno registrarAeropuerto(TestAeropuertoNoUsar aeropuerto) {
        return sistema.registrarAeropuerto(new String(aeropuerto.getCodigo()), new String(aeropuerto.getNombre()));
    }
    protected  Retorno actualizarVuelo(TestAeropuertoNoUsar origen, TestAeropuertoNoUsar destino, String cod, double combustible, double minutos, int costo) {
        return sistema.actualizarVuelo(origen.getCodigo(),destino.getCodigo(),cod,combustible,minutos,costo);
    }
}
