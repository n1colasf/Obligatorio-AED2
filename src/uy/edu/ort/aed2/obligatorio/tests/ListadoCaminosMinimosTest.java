package uy.edu.ort.aed2.obligatorio.tests;

import org.junit.jupiter.api.Test;
import uy.edu.ort.aed2.obligatorio.Retorno;

public class ListadoCaminosMinimosTest extends GrafoAeropuertosBaseTest {
    private Checker<TestAeropuertoNoUsar> checkerAeropuerto=new Checker<>(TestAeropuertoNoUsar::parsear);
    private static class InfoVuelo{
       final double combustible;
        final double costo;
        final double minutos;

        public InfoVuelo(double combustible, double costo, double minutos) {
            this.combustible = combustible;
            this.costo = costo;
            this.minutos = minutos;
        }
        static InfoVuelo of(double combustible, double costo, double minutos) {
            return new InfoVuelo(combustible,costo,minutos);
        }
    }
    private static final int DISTANCIA_MVD_BS_AS=800;
    private static final int DISTANCIA_BS_AS_SANTIAGO=1000;
    private static final int DISTANCIA_MVD_MADRID=8000;
    private static final int DISTANCIA_MVD_RIO=2000;
    private static final int DISTANCIA_MADRID_SANTIAGO=12000;
    private static final int DISTANCIA_MADRID_PARIS=2000;

    private  static final double COSTO_MIN_MVD_BS_AS=57;
    private  static final double COSTO_MIN_BSAS_SANTIAGO=300;
    private  static final double COSTO_MINIMO_MVD_MADRID=5000;
    private  static final double COSTO_MINIMO_MONTEVIDEO_RIO=500;
    private  static final double COSTO_MINIMO_SANTIAGO_MADRID=1000;
    private  static  final  double COSTO_MINIMO_MADRID_PARIS=400;

    private void inicializarGrafo1(){
        registrarTodosLosAeroppuertosValidos();
        registrarConexionConVuelos(montevideo,buenosAires,
                DISTANCIA_MVD_BS_AS,true,
                InfoVuelo.of(10,205,60),
                InfoVuelo.of(10,COSTO_MIN_MVD_BS_AS,60),
                InfoVuelo.of(10,200,60));
        registrarConexionConVuelos(buenosAires,santiagoDeChile,
                DISTANCIA_BS_AS_SANTIAGO,true,
                InfoVuelo.of(20,COSTO_MIN_BSAS_SANTIAGO,60*2));

        registrarConexionConVuelos(montevideo,madrid,
                DISTANCIA_MVD_MADRID,true,
                InfoVuelo.of(20,COSTO_MINIMO_MVD_MADRID,60*21),
                InfoVuelo.of(20,COSTO_MINIMO_MVD_MADRID+2000,60*21));


        registrarConexionConVuelos(montevideo,rioDeJaneiro,
                DISTANCIA_MVD_RIO,true,
                InfoVuelo.of(200,COSTO_MINIMO_MONTEVIDEO_RIO,60*10));

        registrarConexionConVuelos(santiagoDeChile,madrid,
                DISTANCIA_MADRID_SANTIAGO,true,
                InfoVuelo.of(200,COSTO_MINIMO_SANTIAGO_MADRID,60*14));

        registrarConexionConVuelos(madrid,paris,
                DISTANCIA_MADRID_PARIS,true,
                InfoVuelo.of(200,COSTO_MINIMO_MADRID_PARIS,60*2));

    }

    private void registrarConexionConVuelos(TestAeropuertoNoUsar origen, TestAeropuertoNoUsar destino,
                                            int km,
                                            boolean bidireccional,
                                            InfoVuelo... vuelos
                                            ) {
        registrarConexion(origen,destino,km);


        if(bidireccional){
            registrarConexion(destino,origen,km);

        }
        for (int i = 0; i < vuelos.length; i++) {
            var infoVuelo=vuelos[i];
            registrarVuelo(origen,destino, origen.getCodigo()+"--"+origen.getNombre()+"--"+i,
                    infoVuelo.combustible,infoVuelo.minutos,infoVuelo.costo);
            if(bidireccional){
                registrarVuelo(origen,destino, origen.getCodigo()+"--"+origen.getNombre()+"--"+i+"--REV",
                        infoVuelo.combustible,infoVuelo.minutos,infoVuelo.costo);
            }
        }
    }

    @Test
    public void testViajeMinimoKm1_ok(){
        inicializarGrafo1();

        checkerAeropuerto.ok(DISTANCIA_MVD_MADRID+DISTANCIA_MADRID_PARIS,
                new TestAeropuertoNoUsar[]{montevideo,madrid,paris},
                "Son 3 las escalas MVD->MADrid->Paris","El orden esta mal",
        sistema.viajeCostoMinimoKilometros(
                montevideo.getCodigo()+"",
                paris.getCodigo()+""));
        checkerAeropuerto.ok(DISTANCIA_MVD_MADRID,
                new TestAeropuertoNoUsar[]{montevideo,madrid},
                "Son 2 las escalas MVD->Madrid","El orden esta mal",
                sistema.viajeCostoMinimoKilometros(
                        montevideo.getCodigo()+"",
                        madrid.getCodigo()+""));
        registrarConexionConVuelos(rioDeJaneiro,paris,200,true,
                InfoVuelo.of(20,20,20));

        checkerAeropuerto.ok(DISTANCIA_MVD_RIO+200,
                new TestAeropuertoNoUsar[]{montevideo,rioDeJaneiro,paris},
                "Son 3 las escalas MVD->Rio->Paris","El orden esta mal",
                sistema.viajeCostoMinimoKilometros(
                        montevideo.getCodigo()+"",
                        paris.getCodigo()+""));
        checkerAeropuerto.ok(DISTANCIA_MVD_RIO+200+DISTANCIA_MADRID_PARIS,
                new TestAeropuertoNoUsar[]{montevideo,rioDeJaneiro,paris,madrid},
                "Son 4 las escalas MVD->Rio->Paris->Madrid","El orden esta mal",
                sistema.viajeCostoMinimoKilometros(
                        montevideo.getCodigo()+"",
                        madrid.getCodigo()+""));

    }

    @Test
    public void testViajeMinimoCosto_ok(){
        inicializarGrafo1();

        checkerAeropuerto.ok((int)(COSTO_MIN_MVD_BS_AS+COSTO_MIN_BSAS_SANTIAGO+COSTO_MINIMO_SANTIAGO_MADRID+COSTO_MINIMO_MADRID_PARIS),
                new TestAeropuertoNoUsar[]{montevideo,buenosAires,santiagoDeChile,madrid,paris},
                "Son 3 las escalas MVD->MADrid->Paris","El orden esta mal",
                sistema.viajeCostoMinimoDolares(
                        montevideo.getCodigo()+"",
                        paris.getCodigo()+""));


        registrarConexionConVuelos(rioDeJaneiro,paris,100000,true,
                InfoVuelo.of(20,1,20));
        checkerAeropuerto.ok((int)(COSTO_MINIMO_MONTEVIDEO_RIO+1),
                new TestAeropuertoNoUsar[]{montevideo,rioDeJaneiro,paris},
                "Son 3 las escalas MVD->MADrid->Paris","El orden esta mal",
                sistema.viajeCostoMinimoDolares(
                        montevideo.getCodigo()+"",
                        paris.getCodigo()+""));


    }

    @Test
    public void testViajeMinimoCosto_error_no_hay_camino(){
        registrarTodosLosAeroppuertosValidos();
        checkerAeropuerto.error(Retorno.Resultado.ERROR_2,
                "No hay aristas",
                sistema.viajeCostoMinimoDolares(montevideo.getCodigo(),paris.getCodigo()));

        registrarConexionConVuelos(montevideo,buenosAires,20,false,InfoVuelo.of(1,1,1));
        registrarConexionConVuelos(montevideo,santiagoDeChile,20,false,InfoVuelo. of(2,2,2));
        registrarConexionConVuelos(montevideo,rioDeJaneiro,20,false,InfoVuelo. of(2,2,2));
        registrarConexionConVuelos(rioDeJaneiro,madrid,20,false,InfoVuelo. of(3,3,3));
        checkerAeropuerto.error(Retorno.Resultado.ERROR_2,
                "No hay camino",
                sistema.viajeCostoMinimoDolares(montevideo.getCodigo(),paris.getCodigo()));
        registrarConexionConVuelos(paris,madrid,20,false,InfoVuelo. of(3,3,3));
        //ESTAS AL REVES
        checkerAeropuerto.error(Retorno.Resultado.ERROR_2,
                "No hay camino",
                sistema.viajeCostoMinimoDolares(montevideo.getCodigo(),paris.getCodigo()));
        checkerAeropuerto.error(Retorno.Resultado.ERROR_2,
                "No hay camino",
                sistema.viajeCostoMinimoKilometros(montevideo.getCodigo(),paris.getCodigo()));

        registrarConexionConVuelos(madrid,paris,20,true,InfoVuelo. of(4,4,4));



        checkerAeropuerto.ok((int)(2+3+4),
                new TestAeropuertoNoUsar[]{montevideo,rioDeJaneiro,madrid,paris},
                "El valor no oes el esperado","El orden esta mal",
                sistema.viajeCostoMinimoDolares(
                        montevideo.getCodigo()+"",
                        paris.getCodigo()+""));
        checkerAeropuerto.ok(60,
                new TestAeropuertoNoUsar[]{montevideo,rioDeJaneiro,madrid,paris},
                "El valor no oes el esperado","El orden esta mal",
                sistema.viajeCostoMinimoKilometros(
                        montevideo.getCodigo()+"",
                        paris.getCodigo()+""));
    }

    @Test
    public void testViajeMinimoKm1_error() {
        inicializarGrafo1();
        checkerAeropuerto.error(
                Retorno.Resultado.ERROR_1,
                "Codigo vacio",
                sistema.viajeCostoMinimoDolares("",montevideo.getCodigo())
        );
        checkerAeropuerto.error(
                Retorno.Resultado.ERROR_1,
                "Codigo vacio",
                sistema.viajeCostoMinimoKilometros(montevideo.getCodigo(),null)
        );
    }

    @Test
    public void testViajeMinimoRegistroConexionesNoVuelos_error(){
        registrarTodosLosAeroppuertosValidos();
        registrarConexion(montevideo,rioDeJaneiro,10);
        registrarConexion(rioDeJaneiro,madrid,10);
        registrarConexion(madrid,paris,20);
        checkerAeropuerto.error(Retorno.Resultado.ERROR_2,
                "Las conexiones estan no hay vuelos or lo que no hay costo",
                sistema.viajeCostoMinimoDolares(montevideo.getCodigo(), paris.getCodigo()));
        registrarVuelo(montevideo,rioDeJaneiro,"T1",20,20,20);
        registrarVuelo(rioDeJaneiro,madrid,"T2",20,20,20);
        checkerAeropuerto.error(Retorno.Resultado.ERROR_2,
                "Falta un vuelo",
                sistema.viajeCostoMinimoDolares(montevideo.getCodigo(), paris.getCodigo()));
        registrarVuelo(madrid,paris,"T3",20,20,20);
        //AHORA SI
        checkerAeropuerto.ok(20*3,new TestAeropuertoNoUsar[]{montevideo,rioDeJaneiro,madrid,paris},
                "El valor no coincide","Los aeropuertos estan mal",
                sistema.viajeCostoMinimoDolares(montevideo.getCodigo(), paris.getCodigo()));

    }

}
