package uy.edu.ort.aed2.obligatorio;

public class SistemaImp implements Sistema {
    AbbPasajero pasajeros;
    GrafoMundo grafoMundo;
    MatrizAsientos matrizAsientos;
    static ListaCategoria listaCatA;
    static ListaCategoria listaCatB;
    static ListaCategoria listaCatC;
    static ListaCategoria listaCatD;
    static ListaCategoria listaCatE;
    static ListaCategoria listaCatF;
    static ListaCategoria listaCatG;


    //FUNCIONES AUXILIARES -------------------------------------------------------------------------------------------------
    public static void agregarPasajeroAListaCategoria(Pasajero pasajero) {
        if (pasajero.getCategoria() == Sistema.Categoria.A) {
            listaCatA.agregarAlista(pasajero);
        } else if (pasajero.getCategoria() == Sistema.Categoria.B) {
            listaCatB.agregarAlista(pasajero);
        } else if (pasajero.getCategoria() == Sistema.Categoria.C) {
            listaCatC.agregarAlista(pasajero);
        } else if (pasajero.getCategoria() == Sistema.Categoria.D) {
            listaCatD.agregarAlista(pasajero);
        } else if (pasajero.getCategoria() == Sistema.Categoria.E) {
            listaCatE.agregarAlista(pasajero);
        } else if (pasajero.getCategoria() == Sistema.Categoria.F) {
            listaCatF.agregarAlista(pasajero);
        }else if (pasajero.getCategoria() == Sistema.Categoria.G) {
            listaCatG.agregarAlista(pasajero);}
    }

    public int cantidadPorCategoria(Categoria categoria) {
        int cantidad = 0;
        if (categoria.getValor() == 0) {
            cantidad = listaCatA.largo;
        } else if (categoria.getValor() == 1) {
            cantidad = listaCatB.largo;
        } else if (categoria.getValor() == 2) {
            cantidad = listaCatC.largo;
        } else if (categoria.getValor() == 3) {
            cantidad = listaCatD.largo;
        } else if (categoria.getValor() == 4) {
            cantidad = listaCatE.largo;
        } else if (categoria.getValor() == 5) {
            cantidad = listaCatF.largo;
        } else if (categoria.getValor() == 6) {
            cantidad = listaCatG.largo;
        }
        return cantidad;
    }


    //TODO// FUNCIONALIDAD 1 -------------------------------------------------------------------------------------------------
    @Override
    public Retorno inicializarSistema(int maxAeropuertos) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        if (maxAeropuertos > 2) {
            pasajeros = new AbbPasajero();
            grafoMundo = new GrafoMundo(maxAeropuertos, true);
            matrizAsientos = new MatrizAsientos();
            listaCatA = new ListaCategoria();
            listaCatB = new ListaCategoria();
            listaCatC = new ListaCategoria();
            listaCatD = new ListaCategoria();
            listaCatE = new ListaCategoria();
            listaCatF = new ListaCategoria();
            listaCatG = new ListaCategoria();

        } else {
            return ret = new Retorno(Retorno.Resultado.ERROR_1);
        }
        return ret;
    }

    //TODO// FUNCIONALIDAD 2 -------------------------------------------------------------------------------------------------
    @Override
    public Retorno registrarPasajero(String cedula, String nombre, String telefono, Categoria categoria) {
        Retorno ret;
        int valor = pasajeros.agregarPasajero(cedula, nombre, telefono, categoria);
        if (valor == 1) return ret = new Retorno(Retorno.Resultado.ERROR_1);
        if (valor == 2) return ret = new Retorno(Retorno.Resultado.ERROR_2);
        if (valor == 3) return ret = new Retorno(Retorno.Resultado.ERROR_3);
        return ret = new Retorno(Retorno.Resultado.OK);
    }

    //TODO// FUNCIONALIDAD 3 -------------------------------------------------------------------------------------------------
    @Override
    public Retorno buscarPasajero(String cedula) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        NodoAbbPasajero pasajero = pasajeros.DatosPasajero(cedula);
        int cantidad = pasajeros.contadorPasajero(cedula);
        if (!pasajeros.verificarCedula(cedula)) {
            return new Retorno(Retorno.Resultado.ERROR_1);
        }
        if (pasajero == null) {
            return new Retorno(Retorno.Resultado.ERROR_2);
        }
        ret.valorEntero = cantidad;
        ret.valorString = pasajero.getPasajero().getCedula() + ';' + pasajero.getPasajero().getNombre() + ';' + pasajero.getPasajero().getTelefono() + ';' + pasajero.getPasajero().getCategoria();
        return ret;
    }

    //TODO// FUNCIONALIDAD 4 -------------------------------------------------------------------------------------------------
    @Override
    public Retorno listarPasajerosAscendente() {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        String auxiliar = pasajeros.listarPasajerosAscendenteAbb();
        ret.valorString = "";
        if (auxiliar.length() > 0) {
            ret.valorString = auxiliar.substring(0, auxiliar.length() - 1);
        }
        return ret;
    }

    //TODO// FUNCIONALIDAD 5 -------------------------------------------------------------------------------------------------
    @Override
    public Retorno listarPasajerosDescendente() {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        String auxiliar = pasajeros.listarPasajerosDescendeteAbb();
        ret.valorString = "";
        if (auxiliar.length() > 0) {
            ret.valorString = auxiliar.substring(0, auxiliar.length() - 1);
        }
        return ret;
    }

    //TODO// FUNCIONALIDAD 6 -------------------------------------------------------------------------------------------------
    @Override
    public Retorno listarPasajerosPorCategoría(Categoria categoria) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        String auxiliar = pasajeros.listarPasajerosPorCategoria(categoria);
        ret.valorString = "";
        if (auxiliar.length() > 0) {
            ret.valorString = auxiliar.substring(0, auxiliar.length() - 1);
            ret.valorEntero = 0;
        }
        return ret;
    }

    //TODO// FUNCIONALIDAD 7 -------------------------------------------------------------------------------------------------
    @Override
    public Retorno registrarAeropuerto(String codigo, String nombre) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        Aeropuerto aeropuerto = new Aeropuerto(codigo, nombre);
        int valor = grafoMundo.agregarAeropuerto(aeropuerto);
        if (valor == 1) return ret = new Retorno(Retorno.Resultado.ERROR_1);
        if (valor == 2) return ret = new Retorno(Retorno.Resultado.ERROR_2);
        if (valor == 3) return ret = new Retorno(Retorno.Resultado.ERROR_3);
        return ret;
    }

    //TODO// FUNCIONALIDAD 8 -------------------------------------------------------------------------------------------------
    @Override
    public Retorno registrarConexion(String codigoAeropuertoOrigen, String codigoAeropuertoDestino, double kilometros) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        //Aeropuerto aeropuertoOrigen = grafoMundo.obtenerAeropuertoSegunCodigo(codigoAeropuertoOrigen);
        //Aeropuerto aeropuertoDestino = grafoMundo.obtenerAeropuertoSegunCodigo(codigoAeropuertoDestino);
        //Conexion conexion = new Conexion(codigoAeropuertoOrigen,codigoAeropuertoDestino,kilometros);
        int valor = grafoMundo.agregarConexion(codigoAeropuertoOrigen, codigoAeropuertoDestino, kilometros);
        if (valor == 1) return ret = new Retorno(Retorno.Resultado.ERROR_1);
        if (valor == 2) return ret = new Retorno(Retorno.Resultado.ERROR_2);
        if (valor == 3) return ret = new Retorno(Retorno.Resultado.ERROR_3);
        if (valor == 4) return ret = new Retorno(Retorno.Resultado.ERROR_4);
        return ret;
    }

    //TODO// FUNCIONALIDAD 9 -------------------------------------------------------------------------------------------------
    @Override
    public Retorno registrarVuelo(String codigoAeropuertoOrigen, String codigoAeropuertoDestino, String codigoDeVuelo, double combustible,
                                  double minutos, double costoEnDolares) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        int valor = grafoMundo.agregarVuelo(codigoAeropuertoOrigen, codigoAeropuertoDestino, codigoDeVuelo, combustible, minutos, costoEnDolares);
        if (valor == 1) return ret = new Retorno(Retorno.Resultado.ERROR_1);
        if (valor == 2) return ret = new Retorno(Retorno.Resultado.ERROR_2);
        if (valor == 3) return ret = new Retorno(Retorno.Resultado.ERROR_3);
        if (valor == 4) return ret = new Retorno(Retorno.Resultado.ERROR_4);
        if (valor == 5) return ret = new Retorno(Retorno.Resultado.ERROR_5);
        if (valor == 6) return ret = new Retorno(Retorno.Resultado.ERROR_6);
        return ret;
    }

    //TODO// FUNCIONALIDAD 10 -------------------------------------------------------------------------------------------------
    @Override
    public Retorno actualizarVuelo(String codigoAeropuertoOrigen, String codigoAeropuertoDestino,
                                   String codigoDeVuelo, double combustible, double minutos, double costoEnDolares) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        int valor = grafoMundo.actualizarVuelo(codigoAeropuertoOrigen, codigoAeropuertoDestino, codigoDeVuelo, combustible, minutos, costoEnDolares);
        if (valor == 1) return ret = new Retorno(Retorno.Resultado.ERROR_1);
        if (valor == 2) return ret = new Retorno(Retorno.Resultado.ERROR_2);
        if (valor == 3) return ret = new Retorno(Retorno.Resultado.ERROR_3);
        if (valor == 4) return ret = new Retorno(Retorno.Resultado.ERROR_4);
        if (valor == 5) return ret = new Retorno(Retorno.Resultado.ERROR_5);
        if (valor == 6) return ret = new Retorno(Retorno.Resultado.ERROR_6);
        return ret;
    }

    //TODO// FUNCIONALIDAD 11 -------------------------------------------------------------------------------------------------
    @Override
    public Retorno listadoAeropuertosCantDeEscalas(String codigoAeropuertoDeOrigen, int cantidad) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        String auxiliar = grafoMundo.listadoAeropuertosSegunCantEscalas(codigoAeropuertoDeOrigen, cantidad);
        ret.valorString = "";
        if (cantidad < 0) {
            return ret = new Retorno(Retorno.Resultado.ERROR_1);
        } else if (auxiliar.length() > 0) {
            ret.valorString = auxiliar.substring(0, auxiliar.length() - 1);
        } else if (grafoMundo.obtenerPosSegunCodigo(codigoAeropuertoDeOrigen) == -1) {
            return ret = new Retorno(Retorno.Resultado.ERROR_2);
        }
        return ret;
    }

    //TODO// FUNCIONALIDAD 12 -------------------------------------------------------------------------------------------------
    @Override
    public Retorno viajeCostoMinimoKilometros(String codigoAeropuertoOrigen, String codigoAeropuertoDestino) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);

        if (codigoAeropuertoOrigen == null || codigoAeropuertoOrigen == "" || codigoAeropuertoDestino == null || codigoAeropuertoDestino == ""
                || grafoMundo.obtenerPosSegunCodigo(codigoAeropuertoOrigen) == -1 || grafoMundo.obtenerPosSegunCodigo(codigoAeropuertoDestino) == -1) {
            return ret = new Retorno(Retorno.Resultado.ERROR_1);
        } else {
            ret.valorEntero = grafoMundo.MinimoCantidadKilometros(codigoAeropuertoOrigen, codigoAeropuertoDestino);
            String auxiliar = grafoMundo.getListaAeropuerto().toString();
            ret.valorString = auxiliar.substring(0, auxiliar.length() - 1);
            if (ret.valorEntero == Integer.MAX_VALUE || ret.valorEntero < 0) {
                return ret = new Retorno(Retorno.Resultado.ERROR_2);
            } else {
                return ret;
            }
        }
    }

    //TODO// FUNCIONALIDAD 13 -------------------------------------------------------------------------------------------------
    @Override
    public Retorno viajeCostoMinimoDolares(String codigoAeropuertoOrigen, String codigoAeropuertoDestino) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);

        if (codigoAeropuertoOrigen == null || codigoAeropuertoOrigen == "" || codigoAeropuertoDestino == null || codigoAeropuertoDestino == ""
                || grafoMundo.obtenerPosSegunCodigo(codigoAeropuertoOrigen) == -1 || grafoMundo.obtenerPosSegunCodigo(codigoAeropuertoDestino) == -1) {
            return ret = new Retorno(Retorno.Resultado.ERROR_1);
        } else {
            ret.valorEntero = grafoMundo.minimoDolares(codigoAeropuertoOrigen, codigoAeropuertoDestino);
            String auxiliar = grafoMundo.getListaAeropuerto().toString();
            ret.valorString = auxiliar.substring(0, auxiliar.length() - 1);

            if (ret.valorEntero == Integer.MAX_VALUE || ret.valorEntero < 0) {
                return ret = new Retorno(Retorno.Resultado.ERROR_2);
            } else {
                return ret;
            }
        }
    }

    //TODO// FUNCIONALIDAD 14 -------------------------------------------------------------------------------------------------
    @Override
    public Retorno consultaDisponibilidad(int[][] matriz, int cantidad, Clase unaClase) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);

        if (cantidad <= 0) {
            return ret = new Retorno(Retorno.Resultado.ERROR_1);
        } else {
            int dispColumna = matrizAsientos.cantidadDisponible(matriz, cantidad, unaClase);
            if(dispColumna == 0){
                ret.valorString = "";
                ret.valorEntero = 0;
            }else{
                ret.valorString = matrizAsientos.Disponibilidad(matriz, cantidad, unaClase);
                ret.valorEntero = matrizAsientos.getPosCant();
            }
        }
        return ret;
    }
}



