package uy.edu.ort.aed2.obligatorio.tests;

import org.junit.jupiter.api.Assertions;
import uy.edu.ort.aed2.obligatorio.Sistema;

import java.util.Arrays;
import java.util.Objects;

class TestPasajeroNoUsar {
    private final String cedula;
    private final String nombre;
    private final String telefono;
    private final Sistema.Categoria categoria;

    private TestPasajeroNoUsar(String cedula, String nombre, String telefono, Sistema.Categoria categoria) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.categoria = categoria;
    }

    public static TestPasajeroNoUsar of(String cedula, String nombre, String telefono, Sistema.Categoria categoria) {
        return new TestPasajeroNoUsar(cedula, nombre, telefono, categoria);

    }

    @Override
    public String toString() {
        return String.format("%s;%s;%s;%s", cedula, nombre, telefono, categoria.getTexto());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestPasajeroNoUsar that = (TestPasajeroNoUsar) o;
        return Objects.equals(cedula, that.cedula) && Objects.equals(nombre, that.nombre) && Objects.equals(telefono, that.telefono) && Objects.equals(categoria, that.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cedula, nombre, telefono, categoria);
    }

    public static TestPasajeroNoUsar parse(String s) {
        String[] parts = s.split(";");
        Assertions.assertEquals(4, parts.length, "El pasajero no es valido");
        return of(parts[0], parts[1], parts[2],
                Arrays.stream(Sistema.Categoria.values())
                        .filter(v -> v.getTexto().equals(parts[3]))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("El nombre '" + parts[3] + "' no es el de uan categoria valida")));
    }
}
