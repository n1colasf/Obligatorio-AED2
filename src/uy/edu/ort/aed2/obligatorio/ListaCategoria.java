package uy.edu.ort.aed2.obligatorio;

import uy.edu.ort.aed2.obligatorio.Lista.ILista;
import uy.edu.ort.aed2.obligatorio.Lista.NodoListaPasajero;

public class ListaCategoria implements ILista<Pasajero> {

    private NodoListaPasajero inicio;
    protected int largo;

    public ListaCategoria() {
        this.inicio = null;
        this.largo = 0;
    }

    @Override
    public boolean esVacia(){
        return true;
    }
    @Override
    public void agregarAlista(Pasajero pasajero){
        if(inicio == null){
            inicio = new NodoListaPasajero(pasajero);
        } else {
            NodoListaPasajero aux = inicio;
            while (aux.getSig() != null){
                aux = aux.getSig();
            }
            aux.setSig(new NodoListaPasajero(pasajero));
        }
        largo++;
    }

}
