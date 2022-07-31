package uy.edu.ort.aed2.obligatorio;

public class Aeropuerto implements Comparable<Aeropuerto>{
        private String codigo;
        private String nombre;

        public Aeropuerto(String codigo, String nombre) {
            this.codigo = codigo;
            this.nombre = nombre;
        }

        public String getCodigo() {
            return codigo;
        }
        public String getNombre() {
            return nombre;
        }

    @Override
    public String toString() {
        return codigo + ';' +
               nombre + '|';
    }

    @Override
    public int compareTo(Aeropuerto aeropuerto){
        if(this.getCodigo().compareTo(aeropuerto.getCodigo())<0){
            return -1;
        }
        else if(aeropuerto.getCodigo().compareTo(this.getCodigo()) < 0){
            return 1;
        }else{
            return 0;
        }
    }
}
