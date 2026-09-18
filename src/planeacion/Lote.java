package planeacion;
import soporte.FabricaSoporte;
public record Lote(String nombre, FabricaSoporte fabrica, int imagenes, int deterioro, int consultasAnuales, int anioMasAntiguo) {}
