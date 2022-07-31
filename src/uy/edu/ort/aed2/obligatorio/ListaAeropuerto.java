package uy.edu.ort.aed2.obligatorio;

import uy.edu.ort.aed2.obligatorio.Lista.ILista;
import uy.edu.ort.aed2.obligatorio.Lista.NodoListaAeropuerto;

public class ListaAeropuerto implements ILista<Aeropuerto> {

    private NodoListaAeropuerto inicio;
    private NodoListaAeropuerto ultimo;
    protected int largo;

    public ListaAeropuerto() {
        this.inicio = null;
        this.ultimo = null;
        this.largo = 0;
    }

    public int getLargo() {return largo;}
    public void setLargo(int largo) {this.largo = largo;}

    @Override
    public boolean esVacia() {
        return true;
    }

    @Override
    public void agregarAlista(Aeropuerto aeropuerto) {
        if (inicio == null) {
            inicio = new NodoListaAeropuerto(aeropuerto);
        } else {
            inicio = agregarAlistaRec(inicio, aeropuerto);
        }
    }
    private NodoListaAeropuerto agregarAlistaRec(NodoListaAeropuerto nodo, Aeropuerto dato) {
        if (nodo == null || (nodo.getAeropuerto().getCodigo().compareTo(dato.getCodigo()) > 0)) {
            NodoListaAeropuerto nuevo = new NodoListaAeropuerto(dato);
            if (nodo != null) {
                nodo.setAnt(nuevo);
            }
            nuevo.setSig(nodo);
            this.agregarFinal(nuevo.getAeropuerto());
            return nuevo;
        }
        NodoListaAeropuerto llamada = agregarAlistaRec(nodo.getSig(), dato);
        llamada.setAnt(nodo);
        nodo.setSig(llamada);

        return nodo;
    }

    public void agregarAlistaV2(Aeropuerto aeropuerto){
        if(inicio == null){
            inicio = new NodoListaAeropuerto(aeropuerto);
        } else {
            NodoListaAeropuerto aux = inicio;
            if(aux.getAeropuerto().getCodigo() != aeropuerto.getCodigo()){
                while (aux.getSig() != null){
                    aux = aux.getSig();
                }
                aux.setSig(new NodoListaAeropuerto(aeropuerto));
            }

        }
        largo++;
    }

    public void agregarFinal(Aeropuerto objeto) {
        NodoListaAeropuerto nuevo = new NodoListaAeropuerto(objeto);
        if (this.esVacia()) {
            inicio = nuevo;
            inicio = ultimo;
        } else {
            ultimo.setSig(nuevo);
            nuevo.setAnt(ultimo);
            ultimo = nuevo;
        }
        largo++;
    }
    @Override
    public String toString() {
        NodoListaAeropuerto aux = inicio;
        String string = "";
        while (aux != null) {
            string += aux.getAeropuerto().getCodigo() + ";" +
                    aux.getAeropuerto().getNombre() + "|";
            aux = aux.getSig();
        }
        return string;
    }

}
