package uy.edu.ort.aed2.obligatorio;

import uy.edu.ort.aed2.obligatorio.Lista.ILista;
import uy.edu.ort.aed2.obligatorio.Lista.NodoListaVuelo;

public class ListaVuelo implements ILista<Vuelo> {

    private NodoListaVuelo inicio;
    protected int largo;

    public ListaVuelo() {
        this.inicio = null;
        this.largo = 0;
    }

    @Override
    public boolean esVacia(){
        return true;
    }

    @Override
    public void agregarAlista(Vuelo vuelo){
        if(inicio == null){
            inicio = new NodoListaVuelo(vuelo);
        } else {
            NodoListaVuelo aux = inicio;
            while (aux.getSig() != null){
                aux = aux.getSig();
            }
            aux.setSig(new NodoListaVuelo(vuelo));
        }
        largo++;
    }

    public boolean existeVuelo(String codigoVuelo){
        NodoListaVuelo vuelo = inicio;
        while (vuelo != null){
            if(vuelo.getVuelo().getCodigoDeVuelo().equals(codigoVuelo)){
                return true;
            }
            vuelo = vuelo.getSig();
        }
        return false;
    }

    public NodoListaVuelo buscarVueloPorCodigo(String codigoVuelo){
        NodoListaVuelo vuelo = inicio;
        while (vuelo != null){
            if(vuelo.getVuelo().getCodigoDeVuelo().equals(codigoVuelo)){
                return vuelo;
            }
            vuelo = vuelo.getSig();
        }
        return null;
    }

    public int buscarVueloMasBarato(){
        NodoListaVuelo vuelo = inicio;
        int costoMasBajo = Integer.MAX_VALUE;
        while (vuelo != null){
            if(vuelo.getVuelo().getCostoEnDolares() < costoMasBajo){
                costoMasBajo = (int)vuelo.getVuelo().getCostoEnDolares();
            }
            vuelo = vuelo.getSig();
        }
        return costoMasBajo;
    }




}
