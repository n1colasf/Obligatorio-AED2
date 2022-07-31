package uy.edu.ort.aed2.obligatorio;

public interface Sistema {

    Retorno inicializarSistema(int maxAeropuertos);

    Retorno registrarPasajero(String cedula, String nombre, String telefono, Categoria categoria);

    Retorno buscarPasajero(String cedula);

    Retorno listarPasajerosAscendente();

    Retorno listarPasajerosDescendente();

    Retorno listarPasajerosPorCategoría(Categoria categoria);

    Retorno registrarAeropuerto(String codigo, String nombre);

    Retorno registrarConexion(String codigoAeropuertoOrigen, String codigoAeropuertoDestino, double kilometros);

    Retorno registrarVuelo(String codigoAeropuertoOrigen, String codigoAeropuertoDestino, String codigoDeVuelo, double combustible, double minutos, double costoEnDolares);

    Retorno actualizarVuelo(String codigoAeropuertoOrigen, String codigoAeropuertoDestino, String codigoDeVuelo, double combustible, double minutos, double costoEnDolares);

    Retorno listadoAeropuertosCantDeEscalas(String codigoAeropuertoDeOrigen, int cantidad);

    Retorno viajeCostoMinimoKilometros(String codigoAeropuertoOrigen, String codigoAeropuertoDestino);

    Retorno viajeCostoMinimoDolares(String codigoAeropuertoOrigen, String codigoAeropuertoDestino);

    Retorno consultaDisponibilidad(int[][] matriz, int cantidad, Clase unaClase);

    public enum Clase {
        Primera, Ejecutiva, Turista
    }

    public enum Categoria {
        A("Platino", 0),
        B("Frecuente", 1),
        C("Estándar", 2),
        D("Esporádico", 3),
        E("Nuevo", 4),
        F("Convenio", 5),
        G("Empleado", 6);

        private final String texto;
        private final int valor;

        private Categoria(String unTexto, int unValor) {
            this.texto = unTexto;
            this.valor = unValor;
        }

        public String getTexto() {
            return texto;
        }

        public int getValor() {
            return valor;
        }

        @Override
        public String toString() {
            return texto;
        }
    }
}
