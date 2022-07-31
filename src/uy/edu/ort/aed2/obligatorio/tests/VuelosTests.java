package uy.edu.ort.aed2.obligatorio.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uy.edu.ort.aed2.obligatorio.Retorno;
import uy.edu.ort.aed2.obligatorio.Sistema;
import uy.edu.ort.aed2.obligatorio.SistemaImp;

public class VuelosTests extends GrafoAeropuertosBaseTest {

    private Checker<TestAeropuertoNoUsar> checker=new Checker<>(TestAeropuertoNoUsar::parsear);



    @Test
    public void registrarVuelo_ok(){
        registrarTodosLosAeroppuertosValidos();
        checker.ok(registrarConexion(montevideo,paris,10));
        checker.ok(registrarConexion(paris,montevideo,102));
        checker.ok(registrarConexion(santiagoDeChile,buenosAires,102));


        checker.ok(registrarVuelo(montevideo,paris,"PA-01",12d,32d,51));
        checker.ok(registrarVuelo(montevideo,paris,"PA-11",11d,33d,53));
        checker.ok(registrarVuelo(paris,montevideo,"PA-21",422d,32d,52));

        checker.ok(registrarVuelo(santiagoDeChile,buenosAires,"P1-221",32d,31d,52));
    }

    @Test
    public void registrarVuelo_error_datos_invalidos(){
        registrarTodosLosAeroppuertosValidos();
        checker.ok(registrarConexion(montevideo,paris,10));
        checker.ok(registrarConexion(paris,montevideo,102));
        checker.ok(registrarConexion(santiagoDeChile,buenosAires,102));


        checker.error(Retorno.Resultado.ERROR_1,"Combustible 0",
                registrarVuelo(montevideo,paris,"PA-01",0d,32d,51));
        checker.error(Retorno.Resultado.ERROR_1,"Costo negativo",
                registrarVuelo(montevideo,paris,"PA-01",10d,32d,-23));
        checker.error(Retorno.Resultado.ERROR_1,"Minutos en 0",
                registrarVuelo(montevideo,paris,"PA-01",10d,0d,51));
        checker.error(Retorno.Resultado.ERROR_1,"Minutos negativos",
                registrarVuelo(montevideo,paris,"PA-01",202d,-32d,51));

    }

    @Test
    public void registrarVuelo_error_codigos_nulos(){
        registrarTodosLosAeroppuertosValidos();
        checker.ok(registrarConexion(montevideo,paris,10));
        checker.ok(registrarConexion(paris,montevideo,102));
        checker.ok(registrarConexion(santiagoDeChile,buenosAires,102));


        checker.error(Retorno.Resultado.ERROR_2,"Destino vacio",
                sistema.registrarVuelo(montevideo.getCodigo(),"","PA-01",20d,32d,51));
        checker.error(Retorno.Resultado.ERROR_2,"Destino vacio",
                sistema.registrarVuelo(montevideo.getCodigo(),null,"PA-01",20d,32d,51));
        checker.error(Retorno.Resultado.ERROR_2,"Origen vacio",
                sistema.registrarVuelo(null,montevideo.getCodigo(),"PA-01",20d,32d,51));
        checker.error(Retorno.Resultado.ERROR_2,"Origen vacio",
                sistema.registrarVuelo("",montevideo.getCodigo(),"PA-01",20d,32d,51));

    }

    @Test
    public void registrarVuelo_error_origen_no_encontrado(){
        registrarTodosLosAeroppuertosValidos();
        checker.ok(registrarConexion(montevideo,paris,10));
        checker.ok(registrarConexion(paris,montevideo,102));
        checker.ok(registrarConexion(santiagoDeChile,buenosAires,102));


        checker.error(Retorno.Resultado.ERROR_3,"El aeropuerto de origen no existe",
                sistema.registrarVuelo("PQA",montevideo.getCodigo(),"PA-01",20d,32d,51));

    }
    @Test
    public void registrarVuelo_error_destino_no_encontrado(){
        registrarTodosLosAeroppuertosValidos();
        checker.ok(registrarConexion(montevideo,paris,10));
        checker.ok(registrarConexion(paris,montevideo,102));
        checker.ok(registrarConexion(santiagoDeChile,buenosAires,102));


        checker.error(Retorno.Resultado.ERROR_4,"El aeropuerto de destino no existe",
                sistema.registrarVuelo(montevideo.getCodigo(),"NOE","PA-01",20d,32d,51));
    }
    @Test
    public void registrarVuelo_error_conexion_no_encontrada(){
        registrarTodosLosAeroppuertosValidos();
        checker.ok(registrarConexion(montevideo,paris,10));
        checker.ok(registrarConexion(paris,montevideo,102));
        checker.ok(registrarConexion(santiagoDeChile,buenosAires,102));


        checker.error(Retorno.Resultado.ERROR_5,"La conexion no existe",
                registrarVuelo(santiagoDeChile,montevideo,"PA-01",20d,32d,51));
    }

    @Test
    public void registrarVuelo_error_duplicado(){
        registrarTodosLosAeroppuertosValidos();
        checker.ok(registrarConexion(montevideo,paris,10));
        checker.ok(registrarConexion(paris,montevideo,102));
        checker.ok(registrarConexion(santiagoDeChile,buenosAires,102));


        checker.ok(registrarVuelo(montevideo,paris,"PA-01",20d,32d,51));
        checker.error(Retorno.Resultado.ERROR_6,"El vuelo esta duplicado",
                registrarVuelo(montevideo,paris,"PA-01",20d,32d,51));
    }
    @Test
    public void actualizarVuelo_ok(){
        registrarTodosLosAeroppuertosValidos();
        checker.ok(registrarConexion(montevideo,paris,10));
        checker.ok(registrarConexion(paris,montevideo,102));
        checker.ok(registrarConexion(santiagoDeChile,buenosAires,102));


        checker.ok(registrarVuelo(montevideo,paris,"PA-01",12d,32d,51));
        checker.ok(registrarVuelo(montevideo,paris,"PA-11",11d,33d,53));
        checker.ok(registrarVuelo(paris,montevideo,"PA-21",422d,32d,52));

        checker.ok(actualizarVuelo(montevideo,paris,"PA-01",50d,31d,52));
    }



    @Test
    public void actualizarVuelo_error_datos_invalidos(){
        registrarTodosLosAeroppuertosValidos();
        checker.ok(registrarConexion(montevideo,paris,10));
        checker.ok(registrarConexion(paris,montevideo,102));
        checker.ok(registrarConexion(santiagoDeChile,buenosAires,102));


        checker.error(Retorno.Resultado.ERROR_1,"Combustible 0",
                actualizarVuelo(montevideo,paris,"PA-01",0d,32d,51));
        checker.error(Retorno.Resultado.ERROR_1,"Costo negativo",
                actualizarVuelo(montevideo,paris,"PA-01",10d,32d,-23));
        checker.error(Retorno.Resultado.ERROR_1,"Minutos en 0",
                actualizarVuelo(montevideo,paris,"PA-01",10d,0d,51));
        checker.error(Retorno.Resultado.ERROR_1,"Minutos negativos",
                actualizarVuelo(montevideo,paris,"PA-01",202d,-32d,51));

    }

    @Test
    public void actualizarVuelo_error_codigos_nulos(){
        registrarTodosLosAeroppuertosValidos();
        checker.ok(registrarConexion(montevideo,paris,10));
        checker.ok(registrarConexion(paris,montevideo,102));
        checker.ok(registrarConexion(santiagoDeChile,buenosAires,102));


        checker.error(Retorno.Resultado.ERROR_2,"Destino vacio",
                sistema.actualizarVuelo(montevideo.getCodigo(),"","PA-01",20d,32d,51));
        checker.error(Retorno.Resultado.ERROR_2,"Destino vacio",
                sistema.actualizarVuelo(montevideo.getCodigo(),null,"PA-01",20d,32d,51));
        checker.error(Retorno.Resultado.ERROR_2,"Origen vacio",
                sistema.actualizarVuelo(null,montevideo.getCodigo(),"PA-01",20d,32d,51));
        checker.error(Retorno.Resultado.ERROR_2,"Origen vacio",
                sistema.actualizarVuelo("",montevideo.getCodigo(),"PA-01",20d,32d,51));

    }

    @Test
    public void actualizarVuelo_error_origen_no_encontrado(){
        registrarTodosLosAeroppuertosValidos();
        checker.ok(registrarConexion(montevideo,paris,10));
        checker.ok(registrarConexion(paris,montevideo,102));
        checker.ok(registrarConexion(santiagoDeChile,buenosAires,102));


        checker.error(Retorno.Resultado.ERROR_3,"El aeropuerto de origen no existe",
                sistema.actualizarVuelo("PQA",montevideo.getCodigo(),"PA-01",20d,32d,51));

    }
    @Test
    public void actualizarVuelo_error_destino_no_encontrado(){
        registrarTodosLosAeroppuertosValidos();
        checker.ok(registrarConexion(montevideo,paris,10));
        checker.ok(registrarConexion(paris,montevideo,102));
        checker.ok(registrarConexion(santiagoDeChile,buenosAires,102));


        checker.error(Retorno.Resultado.ERROR_4,"El aeropuerto de destino no existe",
                sistema.actualizarVuelo(montevideo.getCodigo(),"NOE","PA-01",20d,32d,51));
    }
    @Test
    public void actualizarVuelo_error_conexion_no_encontrada(){
        registrarTodosLosAeroppuertosValidos();
        checker.ok(registrarConexion(montevideo,paris,10));
        checker.ok(registrarConexion(paris,montevideo,102));
        checker.ok(registrarConexion(santiagoDeChile,buenosAires,102));


        checker.error(Retorno.Resultado.ERROR_5,"La conexion no existe",
                actualizarVuelo(santiagoDeChile,montevideo,"PA-01",20d,32d,51));
    }

    @Test
    public void actualizarVuelo_error_vuelo_no_encontrado(){
        registrarTodosLosAeroppuertosValidos();
        checker.ok(registrarConexion(montevideo,paris,10));
        checker.ok(registrarConexion(paris,montevideo,102));
        checker.ok(registrarConexion(santiagoDeChile,buenosAires,102));


        checker.ok(registrarVuelo(montevideo,paris,"PA-01",20d,32d,51));
        checker.ok(registrarVuelo(montevideo,paris,"PA-12",20d,32d,51));
        checker.ok(registrarVuelo(montevideo,paris,"PA-25",20d,32d,51));
        checker.ok(registrarVuelo(montevideo,paris,"PA-32",20d,32d,51));

        checker.ok(actualizarVuelo(montevideo,paris,"PA-25",212,23,21));

        checker.error(Retorno.Resultado.ERROR_6,"El vuelo no deberia estar",
                actualizarVuelo(montevideo,paris,"PA-26",20d,32d,51));
    }

}
