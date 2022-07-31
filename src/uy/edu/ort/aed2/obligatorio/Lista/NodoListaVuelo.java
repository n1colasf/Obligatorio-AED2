package uy.edu.ort.aed2.obligatorio.Lista;

import uy.edu.ort.aed2.obligatorio.Pasajero;
import uy.edu.ort.aed2.obligatorio.Vuelo;

public class NodoListaVuelo {
    private Vuelo vuelo;
    private NodoListaVuelo sig;

    public NodoListaVuelo() {}

    public NodoListaVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }
    public Vuelo getVuelo() {
        return vuelo;
    }
    public NodoListaVuelo getSig() {
        return sig;
    }
    public void setSig(NodoListaVuelo sig) {
        this.sig = sig;
    }

}
