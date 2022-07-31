package uy.edu.ort.aed2.obligatorio;

public class MatrizAsientos {
    private int posCant;

    public int getPosCant() {
        return posCant;
    }
    public void setPosCant(int posCant) {
        this.posCant = posCant;
    }

    StringBuilder stringBuilder;
    StringBuilder stringBuilderAuxiliarAsignar;
    StringBuilder stringBuilderAuxiDispAsientos;
    StringBuilder stringBuilderAuxiDispAsientosFinal;

    //TODO// FUNCIONALIDAD 14 -------------------------------------------------------------------------------------------------
    public String Disponibilidad(int[][] matriz, int cantidad, Sistema.Clase unaClase) {
        String asientosDisponibles = "";
        int inicio;
        int tope;
        if (unaClase.equals(Sistema.Clase.Primera)) {inicio = 0;tope = 3;}
        else if (unaClase.equals(Sistema.Clase.Ejecutiva)) {inicio = 3;tope = 7;}
        else {inicio = 7;tope = 26;}

        asientosDisponibles = muestraDisponibilidad(matriz, cantidad, inicio, tope);
        return asientosDisponibles;
    }

    public String muestraDisponibilidad(int[][] matriz, int cantidad, int inicio, int tope) {
        String string = new String();
        String stringAux = new String();
        int k = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = inicio; j < tope; j++) {
                for (k = i; k <= matriz.length; k++) {
                   stringAux = asientosDispPorCantidad(matriz,cantidad, inicio, tope);}
            }
            string = stringAux;
        }
        return string;
    }

    public String asientosDispPorCantidad(int[][] matriz, int cantidad, int inicio, int tope) {
        stringBuilder = new StringBuilder();
        stringBuilderAuxiDispAsientos = new StringBuilder();
        stringBuilderAuxiDispAsientosFinal = new StringBuilder();
        int contador = 0;
        int contadorAux = 0;
        int k = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = inicio; j < tope; j++) {
                k = 0;
                contadorAux = 0;
                stringBuilderAuxiDispAsientos = new StringBuilder();
                while (k < matriz.length) {
                    if (matriz[k][j] == 1) {
                        stringBuilderAuxiDispAsientos = new StringBuilder();
                        contadorAux = 0;
                    }
                    if (matriz[k][j] == 0) {
                        contadorAux++;
                        stringBuilderAuxiDispAsientos.append(asignaAsiento(k, j));
                        if(contadorAux < cantidad){
                            stringBuilderAuxiDispAsientos.append("-");
                        }
                    }
                    if (contadorAux == cantidad) {
                        contadorAux = 0;
                        contador++;
                        stringBuilder.append(stringBuilderAuxiDispAsientos);
                        stringBuilderAuxiDispAsientos = new StringBuilder();
                        stringBuilder.append("|");
                        if (k !=6) {k = k - (cantidad - 1);
                        } else {k = 0;}
                    }
                    k++;
                }
            }
            i = k;
        }
        stringBuilderAuxiDispAsientosFinal.append(stringBuilder.substring(0, stringBuilder.length() - 1));
        this.setPosCant(contador);
        return stringBuilderAuxiDispAsientosFinal.toString();
    }

    public int cantidadDisponible(int[][] matriz, int cantidad, Sistema.Clase unaClase) {
        int inicio;
        int tope;
        int contador = 0;
        int contadorAux = 0;
        int k = 0;

        if (unaClase.equals(Sistema.Clase.Primera)) {inicio = 0; tope = 3;}
        else if (unaClase.equals(Sistema.Clase.Ejecutiva)) {inicio = 3;tope = 7;}
        else {inicio = 7;tope = 26;}

        for (int i = 0; i < matriz.length; i++) {
            for (int j = inicio; j < tope; j++) {
                k = 0;
                contadorAux = 0;
                while (k < matriz.length) {
                    if (matriz[k][j] == 1) {contadorAux = 0;}
                    if (matriz[k][j] == 0) {
                        contadorAux++;

                    }
                    if (contadorAux == cantidad) {
                        contadorAux = 0;
                        contador++;
                        if (k !=6) {k = k - (cantidad - 1);}
                        else {k = 0;}
                    }
                    k++;
                }
            }
            i = k;
        }
        return contador;
    }

    public String asignaAsiento(int k, int j) {
        stringBuilderAuxiliarAsignar = new StringBuilder();
        if (k == 0) {stringBuilderAuxiliarAsignar.append("A" + (j + 1));}
        else if (k == 1) {stringBuilderAuxiliarAsignar.append("B" + (j + 1));}
        else if (k == 2) {stringBuilderAuxiliarAsignar.append("C" + (j + 1));}
        else if (k == 3) {stringBuilderAuxiliarAsignar.append("D" + (j + 1));}
        else if (k == 4) {stringBuilderAuxiliarAsignar.append("E" + (j + 1));}
        else if (k == 5) {stringBuilderAuxiliarAsignar.append("F" + (j + 1));}
        return stringBuilderAuxiliarAsignar.toString();
    }
}
