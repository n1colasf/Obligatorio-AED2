package uy.edu.ort.aed2.obligatorio;

public class Pasajero {
    private String cedula;
    private String nombre;
    private String telefono;
    private Sistema.Categoria categoria;

    public Pasajero(String cedula, String nombre, String telefono, Sistema.Categoria categoria) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.categoria = categoria;
    }

    public String getCedula() {
        return cedula;
    }
    public String getNombre() {
        return nombre;
    }
    public String getTelefono() {
        return telefono;
    }
    public Sistema.Categoria getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return "NodoPasajero{" +
                "cedula='" + cedula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", categoria=" + categoria.getTexto() +
                '}';
    }

}
