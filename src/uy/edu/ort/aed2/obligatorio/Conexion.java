package uy.edu.ort.aed2.obligatorio;

public class Conexion {
    private boolean existe;
    private String codigoAeropuertoOrigen;
    private String codigoAeropuertoDestino;
    private double kilometros;
    private ListaVuelo listaVuelos = new ListaVuelo();

    public Conexion() {
        this.codigoAeropuertoOrigen = "";
        this.codigoAeropuertoDestino = "";
        this.kilometros = 0;
    }

    public ListaVuelo getListaVuelos() {
        return listaVuelos;
    }

    public double getKilometros() {
        return kilometros;
    }
    public void setKilometros(double kilometros) {
        this.kilometros = kilometros;
    }

    public boolean isExiste() {
        return existe;
    }
    public void setExiste(boolean existe) {
        this.existe = existe;
    }
    public void agregarVuelosAlista(Vuelo vuelo){
        this.listaVuelos.agregarAlista(vuelo);
    }
    public boolean existeVuelo(String codigoVuelo){
        return this.listaVuelos.existeVuelo(codigoVuelo);
    }

    public Vuelo buscarVueloPorCodigo(String codigoVuelo){
        if(!codigoVuelo.equals("")){
            return this.listaVuelos.buscarVueloPorCodigo(codigoVuelo).getVuelo();
        }
        return null;
    }

    public void modificarDatosVuelo(String codigoVuelo,double combustible, double minutos, double costoEnDolares){
        Vuelo vuelo = buscarVueloPorCodigo(codigoVuelo);
        vuelo.setCombustible(combustible);
        vuelo.setMinutos(minutos);
        vuelo.setCostoEnDolares(costoEnDolares);
    }
}
