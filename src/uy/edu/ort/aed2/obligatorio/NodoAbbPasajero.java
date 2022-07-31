package uy.edu.ort.aed2.obligatorio;

public class NodoAbbPasajero {

    private Pasajero pasajero;
    private NodoAbbPasajero nodoIzq;
    private NodoAbbPasajero nodoDer;

    public NodoAbbPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
        this.nodoIzq = null;
        this.nodoDer = null;
    }

    public NodoAbbPasajero getNodoIzq() {
        return nodoIzq;
    }
    public NodoAbbPasajero getNodoDer() {
        return nodoDer;
    }

    public void setNodoIzq(NodoAbbPasajero nodoIzq) {
        this.nodoIzq = nodoIzq;
    }
    public void setNodoDer(NodoAbbPasajero nodoDer) {
        this.nodoDer = nodoDer;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }
}
