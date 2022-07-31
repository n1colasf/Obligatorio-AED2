package uy.edu.ort.aed2.obligatorio.tests;

import org.junit.jupiter.api.Test;
import uy.edu.ort.aed2.obligatorio.Retorno;

public class ListadoEscalasTest extends GrafoAeropuertosBaseTest {
    private Checker<TestAeropuertoNoUsar> checkerAeropuerto=new Checker<>(TestAeropuertoNoUsar::parsear);

    @Test
    public void test0Escalas_ok(){
        registrarTodosLosAeroppuertosValidos();

        registrarConexion(montevideo,buenosAires,1000);
        registrarConexion(buenosAires,montevideo,1000);
        registrarConexion(buenosAires,madrid,9000);
        registrarConexion(montevideo,montevideo,9000);

        checkerAeropuerto.ok(0,montevideo,
        sistema.listadoAeropuertosCantDeEscalas(montevideo.getCodigo()+"",0));

    }
    @Test
    public void testEscalasNegativas_error(){
        registrarTodosLosAeroppuertosValidos();

        registrarConexion(montevideo,buenosAires,1000);
        registrarConexion(buenosAires,montevideo,1000);
        registrarConexion(buenosAires,madrid,9000);
        registrarConexion(montevideo,montevideo,9000);

        checkerAeropuerto.error(Retorno.Resultado.ERROR_1,
                "Escalas negativas estan mal",
                sistema.listadoAeropuertosCantDeEscalas(montevideo.getCodigo()+"",-1));

    }
    @Test
    public void test0EscalasSinConexiones_ok(){
        registrarTodosLosAeroppuertosValidos();
        registrarConexion(buenosAires,madrid,9000);


        checkerAeropuerto.ok(0,montevideo,
                sistema.listadoAeropuertosCantDeEscalas(montevideo.getCodigo()+"",0));

    }

    @Test
    public void test1EscalaSonLosAdyacentes_ok(){
        registrarTodosLosAeroppuertosValidos();

        registrarConexion(montevideo,buenosAires,1000);
        registrarConexion(buenosAires,montevideo,1000);
        registrarConexion(buenosAires,madrid,9000);
        registrarConexion(montevideo,madrid,9000);
        checkerAeropuerto.ok(0,new TestAeropuertoNoUsar[]{buenosAires,madrid,montevideo},
                "2 son los adyaccentes + 1 qque es mdeo","Los aeropuertos no coinciden",
                sistema.listadoAeropuertosCantDeEscalas(montevideo.getCodigo()+"",1));
    }

    @Test
    public void test2OMasEscalas_ok(){
        registrarTodosLosAeroppuertosValidos();

        registrarConexion(montevideo,buenosAires,1000);
        registrarConexion(buenosAires,montevideo,1000);
        registrarConexion(buenosAires,madrid,9000);
        registrarConexion(montevideo,madrid,9000);
        registrarConexion(madrid,santiagoDeChile,9000);
        registrarConexion(buenosAires,santiagoDeChile,3000);
        registrarConexion(santiagoDeChile,rioDeJaneiro,9000);

        checkerAeropuerto.ok(0,new TestAeropuertoNoUsar[]{buenosAires,madrid,montevideo,santiagoDeChile},
                "Son todos","Los aeropuertos no coinciden",
                sistema.listadoAeropuertosCantDeEscalas(montevideo.getCodigo()+"",2));

        checkerAeropuerto.ok(0,new TestAeropuertoNoUsar[]{buenosAires,madrid,montevideo,rioDeJaneiro,santiagoDeChile},
                "Son todos","Los aeropuertos no coinciden",
                sistema.listadoAeropuertosCantDeEscalas(montevideo.getCodigo()+"",3));
    }


}
