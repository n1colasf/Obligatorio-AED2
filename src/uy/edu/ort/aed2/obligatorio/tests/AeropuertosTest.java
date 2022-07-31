package uy.edu.ort.aed2.obligatorio.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uy.edu.ort.aed2.obligatorio.Retorno;
import uy.edu.ort.aed2.obligatorio.Sistema;
import uy.edu.ort.aed2.obligatorio.SistemaImp;

public class AeropuertosTest  extends GrafoAeropuertosBaseTest{

    private Checker<TestAeropuertoNoUsar> checker=new Checker<>(TestAeropuertoNoUsar::parsear);
    /**
     * Registramos aeropuertos validos
     */
    @Test
    public void regstrarAeropuertos_ok() {
        registrarTodosLosAeroppuertosValidos();

    }
    @Test
    public void regstrarAeropuertos_error_maximo_alcanzado() {
        sistema.inicializarSistema(5);
        checker.ok(registrarAeropuerto(montevideo));
        checker.ok(registrarAeropuerto(paris));
        checker.ok(registrarAeropuerto(madrid));
        checker.ok(registrarAeropuerto(santiagoDeChile));
        checker.ok(registrarAeropuerto(rioDeJaneiro));
        checker.error(Retorno.Resultado.ERROR_1, "Este se pasa del limite",
                registrarAeropuerto(buenosAires));

        checker.error(Retorno.Resultado.ERROR_1, "Este se pasa del limite",
                registrarAeropuerto(buenosAires));
    }

    @Test
    public void regstrarAeropuertos_error_campos_invalidos() {
        sistema.inicializarSistema(5);
        checker.ok(registrarAeropuerto(montevideo));
        checker.ok(registrarAeropuerto(paris));
        checker.ok(registrarAeropuerto(madrid));
        checker.ok(registrarAeropuerto(santiagoDeChile));

        checker.error(Retorno.Resultado.ERROR_2, "El codigo es vacio",
                registrarAeropuerto(new TestAeropuertoNoUsar("", "Un nombre")));
        checker.error(Retorno.Resultado.ERROR_2, "El nombre es null es vacio",
                sistema.registrarAeropuerto("ESES", null));
        checker.error(Retorno.Resultado.ERROR_2, "El codigo es null es vacio",
                sistema.registrarAeropuerto(null, "esese"));
        checker.error(Retorno.Resultado.ERROR_2, "Todo null",
                sistema.registrarAeropuerto(null, null));
    }

    @Test
    public void regstrarAeropuertos_error_duplicado() {
        checker.ok(registrarAeropuerto(montevideo));
        checker.ok(registrarAeropuerto(paris));
        checker.ok(registrarAeropuerto(madrid));
        checker.ok(registrarAeropuerto(santiagoDeChile));

        checker.error(Retorno.Resultado.ERROR_3, "Lo registramos arriba",
                registrarAeropuerto(montevideo));
        checker.error(Retorno.Resultado.ERROR_3, "Lo registramos arriba",
                registrarAeropuerto(paris));

    }

    @Test
    public void registrarConexion_ok(){
        registrarTodosLosAeroppuertosValidos();
        checker.ok(registrarConexion(montevideo,paris,10));
        checker.ok(registrarConexion(paris,montevideo,102));//Acuerdense que es ponderado
        checker.ok(registrarConexion(montevideo,santiagoDeChile,21));
    }

    @Test
    public void registrarConexion_error_datos_invalidos(){
        registrarTodosLosAeroppuertosValidos();
        checker.error(Retorno.Resultado.ERROR_1,"Kilometros negativos",registrarConexion(santiagoDeChile,montevideo,-10));
        checker.error(Retorno.Resultado.ERROR_1,"Kilometros 0",registrarConexion(santiagoDeChile,montevideo,0));
    }
    @Test
    public void registrarConexion_error_aeropuerto_origen_no_existe(){
        registrarTodosLosAeroppuertosValidos();
        checker.ok(registrarConexion(montevideo,paris,10));
        checker.ok(registrarConexion(paris,montevideo,102));//Acuerdense que es ponderado
        checker.ok(registrarConexion(montevideo,santiagoDeChile,21));

        checker.error(Retorno.Resultado.ERROR_2,"El aeropuerto de origen no existe",
                sistema.registrarConexion("",montevideo.getCodigo(),20));
        checker.error(Retorno.Resultado.ERROR_2,"El aeropuerto de origen no existe",
                sistema.registrarConexion(null,montevideo.getCodigo(),20));
        checker.error(Retorno.Resultado.ERROR_2,"El aeropuerto de origen no existe",
                sistema.registrarConexion("BLA",montevideo.getCodigo(),20));
    }
    @Test
    public void registrarConexion_error_aeropuerto_destino_no_existe(){
        registrarTodosLosAeroppuertosValidos();
        checker.ok(registrarConexion(montevideo,paris,10));
        checker.ok(registrarConexion(paris,montevideo,102));//Acuerdense que es ponderado
        checker.ok(registrarConexion(montevideo,santiagoDeChile,21));

        checker.error(Retorno.Resultado.ERROR_3,"El aeropuerto de origen no existe",
                sistema.registrarConexion(montevideo.getCodigo(),"",20));
        checker.error(Retorno.Resultado.ERROR_3,"El aeropuerto de origen no existe",
                sistema.registrarConexion(montevideo.getCodigo(),null,20));
        checker.error(Retorno.Resultado.ERROR_3,"El aeropuerto de origen no existe",
                sistema.registrarConexion(montevideo.getCodigo(),"BLA",20));
    }

    @Test
    public void registrarConexion_error_duplicado() {

        registrarTodosLosAeroppuertosValidos();
        checker.ok(registrarConexion(montevideo,paris,10));
        checker.ok(registrarConexion(paris,montevideo,102));//Acuerdense que es ponderado
        checker.ok(registrarConexion(montevideo,santiagoDeChile,21));
        checker.error(Retorno.Resultado.ERROR_4,"Queriamos agregar un duplicado",
                registrarConexion(montevideo,paris,20));
    }






}
