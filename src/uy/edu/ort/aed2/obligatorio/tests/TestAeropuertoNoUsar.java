package uy.edu.ort.aed2.obligatorio.tests;

import org.junit.jupiter.api.Assertions;

import java.util.Objects;

class TestAeropuertoNoUsar {

    private final String codigo;
    private final String nombre;

    public TestAeropuertoNoUsar(String codigo, String nombre) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestAeropuertoNoUsar that = (TestAeropuertoNoUsar) o;
        return Objects.equals(codigo, that.codigo) && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, nombre);
    }

    @Override
    public String toString() {
        return String.format("%s;%s", codigo, nombre);
    }

    public static TestAeropuertoNoUsar parsear(String st) {
        String[] parts = st.split("[;]");
        Assertions.assertEquals(2, parts.length, "Aeropuerto formateado mal '" + st + "'");
        return new TestAeropuertoNoUsar(parts[0], parts[1]);
    }
}
