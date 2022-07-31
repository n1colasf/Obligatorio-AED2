package uy.edu.ort.aed2.obligatorio;

public class GrafoMundo {
    private int tope;
    private int cantidad;
    private Aeropuerto[] aeropuertos;
    private Conexion[][] matAdy;
    private ListaAeropuerto listaAeropuerto;

    public GrafoMundo(int unTope, boolean esDirigido) {
        this.tope = unTope;
        this.cantidad = 0;
        this.aeropuertos = new Aeropuerto[tope];
        this.matAdy = new Conexion[tope][tope];
        if (esDirigido) {
            for (int i = 0; i < tope; i++) {
                for (int j = 0; j < tope; j++) {
                    this.matAdy[i][j] = new Conexion();
                }
            }
        } else {
            for (int i = 0; i < tope; i++) {
                for (int j = i; j < tope; j++) {
                    this.matAdy[i][j] = new Conexion();
                    this.matAdy[j][i] = this.matAdy[i][j];
                }
            }
        }
    }

    public ListaAeropuerto getListaAeropuerto() {
        return listaAeropuerto;
    }
    public boolean esLleno() {
        return this.cantidad == this.tope;
    }


    //FUNCIONES AUXILIARES -------------------------------------------------------------------------------------------------
    //Obtener la posicion en el array de aeropuertos de la primera posicion libre
    private int obtenerPosLibre() {
        for (int i = 0; i < tope; i++) {
            if (aeropuertos[i] == null) {
                return i;
            }
        }
        return -1;
    }
    //Obtener la posicion en el array del aeropuerto pasado por parametro
    public int obtenerPos(Aeropuerto aeropuerto) {
        for (int i = 0; i < tope; i++) {
            if (aeropuertos[i] != null) {
                if (aeropuertos[i].getCodigo().equals(aeropuerto.getCodigo()) &&
                        aeropuertos[i].getNombre().equals(aeropuerto.getNombre())) {
                    return i;
                }
            }
        }
        return -1;
    }
    //Verificar si existe el aeropuerto pasado por parametro
    public boolean existeAeropuerto(Aeropuerto aeropuerto) {
        return obtenerPos(aeropuerto) > -1;
    }

    //Obtener la posicion del aeropuerto por su codigo
    public int obtenerPosSegunCodigo(String codigoAeropuerto) {
        for (int i = 0; i < tope; i++) {
            if (aeropuertos[i] != null) {
                if (aeropuertos[i].getCodigo().equals(codigoAeropuerto)) {
                    return i;
                }
            }
        }
        return -1;
    }
    //Verificar si existe la conexion entre dos aeropuertos por su codigo
    public boolean existeConexion(String aeropuertoOrigen, String aeropuertoDestino) {
        int posicionOrigen = obtenerPosSegunCodigo(aeropuertoOrigen);
        int posicionDestino = obtenerPosSegunCodigo(aeropuertoDestino);
        return matAdy[posicionOrigen][posicionDestino].isExiste();
    }
    //Verificar si existe un determinado vuelo eb la conexion entre dos aeropuertos por su codigo
    public boolean existeVueloEnConexion(String codigoDeVuelo, String aeropuertoOrigen, String aeropuertoDestino) {
        int posicionOrigen = obtenerPosSegunCodigo(aeropuertoOrigen);
        int posicionDestino = obtenerPosSegunCodigo(aeropuertoDestino);
        if (matAdy[posicionOrigen][posicionDestino].isExiste()) {
            return matAdy[posicionOrigen][posicionDestino].existeVuelo(codigoDeVuelo);
        }
        return false;
    }

    //TODO// FUNCIONALIDAD 7 -------------------------------------------------------------------------------------------------
    public int agregarAeropuerto(Aeropuerto aeropuerto) {
        if (esLleno()) {
            return 1;
        } else if (aeropuerto.getCodigo() == null || aeropuerto.getNombre() == null || aeropuerto.getCodigo().equals("")  || aeropuerto.getNombre().equals("")) {
            return 2;
        } else if (existeAeropuerto(aeropuerto)) {
            return 3;
        } else {
            int posicionLibre = obtenerPosLibre();
            aeropuertos[posicionLibre] = aeropuerto;
            cantidad++;
            return 4;
        }
    }

    //TODO// FUNCIONALIDAD 8 -------------------------------------------------------------------------------------------------
    public int agregarConexion(String aeropuertoOrigen, String aeropuertoDestino, double kilometros) {
        int posicionOrigen = obtenerPosSegunCodigo(aeropuertoOrigen);
        int posicionDestino = obtenerPosSegunCodigo(aeropuertoDestino);
        if (kilometros <= 0) {
            return 1;
        } else if (posicionOrigen == -1) {
            return 2;
        } else if (posicionDestino == -1) {
            return 3;
        } else if (existeConexion(aeropuertoOrigen, aeropuertoDestino)) {
            return 4;
        } else {
            matAdy[posicionOrigen][posicionDestino].setExiste(true);
            matAdy[posicionOrigen][posicionDestino].setKilometros(kilometros);
            return 5;
        }
    }

    //TODO// FUNCIONALIDAD 9 -------------------------------------------------------------------------------------------------
    public int agregarVuelo(String codigoAeropuertoOrigen, String codigoAeropuertoDestino, String codigoDeVuelo, double combustible,
                            double minutos, double costoEnDolares) {
        int posicionOrigen = obtenerPosSegunCodigo(codigoAeropuertoOrigen);
        int posicionDestino = obtenerPosSegunCodigo(codigoAeropuertoDestino);
        Vuelo vuelo = new Vuelo(codigoAeropuertoOrigen, codigoAeropuertoDestino, codigoDeVuelo, combustible, minutos, costoEnDolares);
        if (combustible <= 0 || minutos <= 0 || costoEnDolares <= 0) {
            return 1;
        } else if (codigoAeropuertoOrigen == null || codigoAeropuertoOrigen.equals("") ||
                codigoAeropuertoDestino == null || codigoAeropuertoDestino.equals("") ||
                codigoDeVuelo == null || codigoDeVuelo.equals("")) {
            return 2;
        } else if (posicionOrigen == -1) {
            return 3;
        } else if (posicionDestino == -1) {
            return 4;
        } else if (!existeConexion(codigoAeropuertoOrigen, codigoAeropuertoDestino)) {
            return 5;
        } else if (existeVueloEnConexion(codigoDeVuelo, codigoAeropuertoOrigen, codigoAeropuertoDestino)) {
            return 6;
        } else {
            matAdy[posicionOrigen][posicionDestino].agregarVuelosAlista(vuelo);
            return 7;
        }
    }

    //TODO// FUNCIONALIDAD 10 -------------------------------------------------------------------------------------------------
    public int actualizarVuelo(String codigoAeropuertoOrigen, String codigoAeropuertoDestino, String codigoDeVuelo, double combustible,
                               double minutos, double costoEnDolares) {
        int posicionOrigen = obtenerPosSegunCodigo(codigoAeropuertoOrigen);
        int posicionDestino = obtenerPosSegunCodigo(codigoAeropuertoDestino);
        if (combustible <= 0 || minutos <= 0 || costoEnDolares <= 0) {
            return 1;
        } else if (codigoAeropuertoOrigen == null || codigoAeropuertoOrigen.equals("") ||
                codigoAeropuertoDestino == null || codigoAeropuertoDestino.equals("") ||
                codigoDeVuelo == null || codigoDeVuelo.equals("")) {
            return 2;
        } else if (posicionOrigen == -1) {
            return 3;
        } else if (posicionDestino == -1) {
            return 4;
        } else if (!existeConexion(codigoAeropuertoOrigen, codigoAeropuertoDestino)) {
            return 5;
        } else if (!existeVueloEnConexion(codigoDeVuelo, codigoAeropuertoOrigen, codigoAeropuertoDestino)) {
            return 6;
        } else {
            matAdy[posicionOrigen][posicionDestino].modificarDatosVuelo(codigoDeVuelo, combustible, minutos, costoEnDolares);
            return 7;
        }
    }

    //TODO// FUNCIONALIDAD 11 -------------------------------------------------------------------------------------------------
    public String listadoAeropuertosSegunCantEscalas(String codigoAeropuerto, int cantidad) {
        ListaAeropuerto listaAeropuerto = new ListaAeropuerto();
        int contador = 0;
        boolean[] visitados = new boolean[tope];
        int pos = obtenerPosSegunCodigo(codigoAeropuerto);
        if (cantidad == 0) {
            Aeropuerto aux = aeropuertos[pos];
            return aux.toString();
        }
        return listadoAeropuertosSegunCantEscalasRec(pos, visitados, cantidad, contador, listaAeropuerto);
    }
    public String listadoAeropuertosSegunCantEscalasRec(int posicionAeropuerto, boolean[] visitados, int cantidad, int contador, ListaAeropuerto listaAeropuerto) {
        visitados[posicionAeropuerto] = true;
        listaAeropuerto.agregarAlista(aeropuertos[posicionAeropuerto]);
        for (int j = 0; j < tope; j++) {
            if (this.matAdy[posicionAeropuerto][j].isExiste() && !visitados[j]) {
                if (contador <= cantidad) {
                    contador++;
                    listadoAeropuertosSegunCantEscalasRec(j, visitados, cantidad, contador, listaAeropuerto);
                }
            }
        }
        return listaAeropuerto.toString();
    }

    //TODO// FUNCIONALIDAD 12 -------------------------------------------------------------------------------------------------
    public int MinimoCantidadKilometros(String codigoAeropuertoDeOrigen, String codigoAeropuertoDestino) {
        int posOrigen = obtenerPosSegunCodigo(codigoAeropuertoDeOrigen);
        int posDestino = obtenerPosSegunCodigo(codigoAeropuertoDestino);

        boolean[] visitados = new boolean[this.tope];
        int[] kilometros = new int[this.tope];
        Aeropuerto[] anterior = new Aeropuerto[tope];

        for (int i = 0; i < this.tope; i++) {
            kilometros[i] = Integer.MAX_VALUE;
            anterior[i] = null;
        }

        kilometros[posOrigen] = 0;
        for (int v = 0; v < this.cantidad; v++) {
            int pos = obtenerSiguienteVerticeNoVisitadoDeMenorKilometros(kilometros, visitados);
            if (pos != -1) {
                visitados[pos] = true;
                for (int j = 0; j < tope; j++) {
                    if (matAdy[pos][j].isExiste() && !visitados[j]) {
                        int costoNuevo = kilometros[pos] + (int) matAdy[pos][j].getKilometros();
                        if (costoNuevo < kilometros[j]) {
                            kilometros[j] = costoNuevo;
                            anterior[j] = aeropuertos[pos];
                        }
                    }
                }
            }
        }
        this.listaAeropuerto = new ListaAeropuerto();
        listarAeropuertosSegunAeropuerto(listaAeropuerto, anterior, posDestino);
        return kilometros[posDestino];
    }

    private int obtenerSiguienteVerticeNoVisitadoDeMenorKilometros(int[] kilometros, boolean[] visitados) {
        int posMin = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < tope; i++) {
            if (!visitados[i] && kilometros[i] < min) {
                min = kilometros[i];
                posMin = i;
            }
        }
        return posMin;
    }

    public ListaAeropuerto listarAeropuertosSegunAeropuerto(ListaAeropuerto listaAeropuerto, Aeropuerto[] anterior, int posDestino) {
        Aeropuerto aeropuerto = aeropuertos[posDestino];
        return listarAeropuertosSegunAeropuertoRec(listaAeropuerto, anterior, aeropuerto);
    }

    public ListaAeropuerto listarAeropuertosSegunAeropuertoRec(ListaAeropuerto listaAeropuerto, Aeropuerto[] anterior, Aeropuerto aeropuerto) {
        if (aeropuerto != null) {
            int aeropuertoPosicion = obtenerPosSegunCodigo(aeropuerto.getCodigo());
            Aeropuerto aeropuerto1 = anterior[aeropuertoPosicion];
            listarAeropuertosSegunAeropuertoRec(listaAeropuerto, anterior, aeropuerto1);
            this.listaAeropuerto.agregarAlistaV2(aeropuerto);
        }
        return listaAeropuerto;
    }


    //TODO// FUNCIONALIDAD 13 -------------------------------------------------------------------------------------------------
    public int minimoDolares(String codigoAeropuertoDeOrigen, String codigoAeropuertoDestino) {

        int posOrigen = obtenerPosSegunCodigo(codigoAeropuertoDeOrigen);
        int posDestino = obtenerPosSegunCodigo(codigoAeropuertoDestino);

        boolean[] visitados = new boolean[this.tope];
        int[] costo = new int[this.tope];
        Aeropuerto[] anterior = new Aeropuerto[tope];

        for (int i = 0; i < this.tope; i++) {
            costo[i] = Integer.MAX_VALUE;
            anterior[i] = null;
        }
        costo[posOrigen] = 0;
        for (int v = 0; v < this.cantidad; v++) {
            int pos = obtenerSiguienteVerticeNoVisitadoDeMenorCosto(costo, visitados);
            if (pos != -1) {
                visitados[pos] = true;
                for (int j = 0; j < tope; j++) {
                    if (matAdy[pos][j].isExiste() && !visitados[j]) {
                        if(matAdy[pos][j].getListaVuelos() != null ){

                            int costoNuevo = costo[pos] + (int) matAdy[pos][j].getListaVuelos().buscarVueloMasBarato();
                            if (costoNuevo < costo[j]) {
                                costo[j] = costoNuevo;
                                anterior[j] = aeropuertos[pos];
                            }
                        }
                    }
                }
            }
        }
        this.listaAeropuerto = new ListaAeropuerto();
        listarAeropuertosSegunAeropuerto(listaAeropuerto, anterior, posDestino);
        return costo[posDestino];
    }

    private int obtenerSiguienteVerticeNoVisitadoDeMenorCosto(int[] costo, boolean[] visitados) {
        int posMin = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < tope; i++) {
            if (!visitados[i] && costo[i] < min) {
                min = costo[i];
                posMin = i;
            }
        }
        return posMin;
    }
}
