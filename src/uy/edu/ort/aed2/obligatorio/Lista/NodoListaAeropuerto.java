package uy.edu.ort.aed2.obligatorio.Lista;

import uy.edu.ort.aed2.obligatorio.Aeropuerto;

public class NodoListaAeropuerto {
    private Aeropuerto aeropuerto;
    private NodoListaAeropuerto sig;
    private NodoListaAeropuerto ant;

    public NodoListaAeropuerto() {}
    public NodoListaAeropuerto(Aeropuerto aeropuerto) {
        this.aeropuerto = aeropuerto;
    }
    public Aeropuerto getAeropuerto() {
        return aeropuerto;
    }
    public NodoListaAeropuerto getSig() {
        return sig;
    }
    public void setSig(NodoListaAeropuerto sig) {
        this.sig = sig;
    }
    public NodoListaAeropuerto getAnt() {return ant; }
    public void setAnt(NodoListaAeropuerto ant) {this.ant = ant; }
}
