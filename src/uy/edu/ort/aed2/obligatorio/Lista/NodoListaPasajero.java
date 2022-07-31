package uy.edu.ort.aed2.obligatorio.Lista;

import uy.edu.ort.aed2.obligatorio.Pasajero;

public class NodoListaPasajero {
    private Pasajero pasajero;
    private NodoListaPasajero sig;

    public NodoListaPasajero() {}
    public NodoListaPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }
    public NodoListaPasajero getSig() {
        return sig;
    }
    public void setSig(NodoListaPasajero sig) {
        this.sig = sig;
    }

}
