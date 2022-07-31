package uy.edu.ort.aed2.obligatorio;

public class Vuelo {
    private String codigoAeropuertoOrigen;
    private String codigoAeropuertoDestino;
    private String codigoDeVuelo;
    private double combustible;
    private double minutos;
    private double costoEnDolares;

    public Vuelo(String codigoAeropuertoOrigen, String codigoAeropuertoDestino, String codigoDeVuelo, double combustible, double minutos, double costoEnDolares) {
        this.codigoAeropuertoOrigen = codigoAeropuertoOrigen;
        this.codigoAeropuertoDestino = codigoAeropuertoDestino;
        this.codigoDeVuelo = codigoDeVuelo;
        this.combustible = combustible;
        this.minutos = minutos;
        this.costoEnDolares = costoEnDolares;
    }

    public String getCodigoDeVuelo() {
        return codigoDeVuelo;
    }

    public void setCombustible(double combustible) {
        this.combustible = combustible;
    }
    public void setMinutos(double minutos) {
        this.minutos = minutos;
    }

    public double getCostoEnDolares() {
        return costoEnDolares;
    }
    public void setCostoEnDolares(double costoEnDolares) {
        this.costoEnDolares = costoEnDolares;
    }
}
