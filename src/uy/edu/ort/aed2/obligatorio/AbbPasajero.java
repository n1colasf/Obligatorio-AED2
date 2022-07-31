package uy.edu.ort.aed2.obligatorio;

public class AbbPasajero {
    private NodoAbbPasajero raiz;

    public AbbPasajero() {
        this.raiz = null;
    }


    //FUNCIONES AUXILIARES -------------------------------------------------------------------------------------------------
    //Verificar campos vacios
    public boolean verificarVacio(String cedula, String nombre, String telefono, Sistema.Categoria categoria) {
        if (cedula == "" || nombre == "" || telefono == "" || cedula == null || nombre == null || telefono == null || categoria == null) {
            return false;
        }
        return true;
    }

    //Verificar estructura de cedula
    public boolean verificarCedula(String cedula) {
        if (cedula.length() == 9) {
            return cedula.matches("^[1-9]{1,1}[\\d]{2,2}\\.[\\d]{3,3}\\-[\\d]{1,1}$");
        } else {
            return cedula.matches("^[1-9]{0,1}\\.?[0-9]{1,1}[\\d]{2,2}\\.[\\d]{3,3}\\-[\\d]{1,1}$");
        }
    }

    //Verificar si existe pasajero
    public boolean existePasajero(String cedula) {
        return this.existePasajeroRecursivo(this.raiz, cedula);
    }

    private boolean existePasajeroRecursivo(NodoAbbPasajero nodoABB, String cedula) {
        boolean pertenece = false;
        if (nodoABB == null) {
            return false;
        } else if (nodoABB.getPasajero().getCedula().equals(cedula)) {
            return true;
        } else {
            if (parsearInt(nodoABB.getPasajero().getCedula()) > parsearInt(cedula)) {
                pertenece = this.existePasajeroRecursivo(nodoABB.getNodoIzq(), cedula);
            } else {
                pertenece = this.existePasajeroRecursivo(nodoABB.getNodoDer(), cedula);
            }
            return pertenece;
        }
    }

    //Parsear cedula de string a numero
    public int parsearInt(String cedula) {
        int retorno;
        String numero = cedula.replace(".", "");
        numero = numero.replace("-", "");
        return retorno = Integer.parseInt(numero);
    }

    //TODO// FUNCIONALIDAD 2 -------------------------------------------------------------------------------------------------
    public int agregarPasajero(String cedula, String nombre, String telefono, Sistema.Categoria categoria) {
        if (!verificarVacio(cedula, nombre, telefono, categoria)) {
            return 1;
        }
        if (!verificarCedula(cedula)) {
            return 2;
        }
        if (existePasajero(cedula)) {
            return 3;
        }
        Pasajero pasajero = new Pasajero(cedula, nombre, telefono, categoria);
        if (this.raiz == null) {
            this.raiz = new NodoAbbPasajero(pasajero);
        } else {
            this.agregarPasajerRec(this.raiz, pasajero);
        }
        SistemaImp.agregarPasajeroAListaCategoria(pasajero);
        return 4;
    }

    private NodoAbbPasajero agregarPasajerRec(NodoAbbPasajero nodoPasajero, Pasajero pasajero) {

        if (parsearInt(nodoPasajero.getPasajero().getCedula()) < parsearInt(pasajero.getCedula())) {
            if (nodoPasajero.getNodoDer() == null) {
                nodoPasajero.setNodoDer(new NodoAbbPasajero(pasajero));
            } else {
                return this.agregarPasajerRec(nodoPasajero.getNodoDer(), pasajero);
            }
        } else if (nodoPasajero.getNodoIzq() == null) {
            nodoPasajero.setNodoIzq(new NodoAbbPasajero(pasajero));
        } else {
            this.agregarPasajerRec(nodoPasajero.getNodoIzq(), pasajero);
        }
        return nodoPasajero;
    }

    //TODO// FUNCIONALIDAD 3 -------------------------------------------------------------------------------------------------
    public int contadorPasajero(String cedula) {
        if (!verificarCedula(cedula) || this.raiz == null) {
            return 0;
        } else {
            int cantidad = 1;
            return this.contadorPasajeroRecursivo(this.raiz, cedula, cantidad);
        }
    }

    private int contadorPasajeroRecursivo(NodoAbbPasajero nodoABB, String cedula, int cantidad) {
        if (nodoABB == null) {
            return cantidad;
        } else if (nodoABB.getPasajero().getCedula() == cedula) {
            return cantidad;
        } else {
            cantidad++;
            if (parsearInt(nodoABB.getPasajero().getCedula()) > parsearInt(cedula)) {
                return contadorPasajeroRecursivo(nodoABB.getNodoIzq(), cedula, cantidad);
            } else {
                return contadorPasajeroRecursivo(nodoABB.getNodoDer(), cedula, cantidad);
            }

        }
    }

    public NodoAbbPasajero DatosPasajero(String cedula) {
        if (!verificarCedula(cedula) /*|| this.raiz == null*/) {
            return null;
        } else {
            return this.DatosPasajeroRec(this.raiz, cedula);
        }
    }

    private NodoAbbPasajero DatosPasajeroRec(NodoAbbPasajero nodoABB, String cedula) {
        if (nodoABB == null) {
            return null;
        } else if (nodoABB.getPasajero().getCedula().equals(cedula)) {
            return nodoABB;
        } else {
            if (parsearInt(nodoABB.getPasajero().getCedula()) > parsearInt(cedula)) {
                return this.DatosPasajeroRec(nodoABB.getNodoIzq(), cedula);
            } else {
                return this.DatosPasajeroRec(nodoABB.getNodoDer(), cedula);
            }
        }
    }

    //TODO// FUNCIONALIDAD 4 -------------------------------------------------------------------------------------------------
    StringBuilder stringBuilder;

    public String listarPasajerosAscendenteAbb() {
        stringBuilder = new StringBuilder();
        return listarPasajerosAscendenteAbb(this.raiz);
    }

    private String listarPasajerosAscendenteAbb(NodoAbbPasajero nodoPasajero) {
        if (nodoPasajero != null) {
            listarPasajerosAscendenteAbb(nodoPasajero.getNodoIzq());
            stringBuilder.append(nodoPasajero.getPasajero().getCedula() + ";" + nodoPasajero.getPasajero().getNombre() +
                    ";" + nodoPasajero.getPasajero().getTelefono() + ";" + nodoPasajero.getPasajero().getCategoria().getTexto() + "|");
            listarPasajerosAscendenteAbb(nodoPasajero.getNodoDer());
        }
        return stringBuilder.toString();
    }

    //TODO// FUNCIONALIDAD 5 -------------------------------------------------------------------------------------------------
    public String listarPasajerosDescendeteAbb() {
        stringBuilder = new StringBuilder();
        return listarPasajerosDescendeteAbb(this.raiz);
    }

    private String listarPasajerosDescendeteAbb(NodoAbbPasajero nodoPasajero) {
        if (nodoPasajero != null) {
            listarPasajerosDescendeteAbb(nodoPasajero.getNodoDer());
            stringBuilder.append(nodoPasajero.getPasajero().getCedula() + ";" + nodoPasajero.getPasajero().getNombre() +
                    ";" + nodoPasajero.getPasajero().getTelefono() + ";" + nodoPasajero.getPasajero().getCategoria().getTexto() + "|");
            listarPasajerosDescendeteAbb(nodoPasajero.getNodoIzq());
        }
        return stringBuilder.toString();
    }

    //TODO// FUNCIONALIDAD 6 -------------------------------------------------------------------------------------------------
    public String listarPasajerosPorCategoria(Sistema.Categoria categoria) {
        stringBuilder = new StringBuilder();
        return listarPasajerosPorCategoria(this.raiz, categoria);
    }

    private String listarPasajerosPorCategoria(NodoAbbPasajero nodoPasajero, Sistema.Categoria categoria) {
        if (nodoPasajero != null) {
            listarPasajerosPorCategoria(nodoPasajero.getNodoDer(), categoria);
            if (nodoPasajero.getPasajero().getCategoria().equals(categoria)) {
                stringBuilder.append(nodoPasajero.getPasajero().getCedula() + ";" + nodoPasajero.getPasajero().getNombre() +
                        ";" + nodoPasajero.getPasajero().getTelefono() + ";" + nodoPasajero.getPasajero().getCategoria().getTexto() + "|");
            }
            listarPasajerosPorCategoria(nodoPasajero.getNodoIzq(), categoria);
        }
        return stringBuilder.toString();
    }
}