package fondo;
import java.util.*;
import soporte.FabricaSoporte;
public class ItemDocumental {
    public final int anio;
    public final FabricaSoporte soporte;
    public final Map<String, String> meta;
    Signatura signatura; // la asigna FondoDocumental.registrar

    public ItemDocumental(int anio, FabricaSoporte soporte, Map<String, String> meta) {
        this.anio = anio; this.soporte = soporte; this.meta = new HashMap<>(meta);
    }
    public Signatura signatura() { return signatura; }
    public List<String> camposFaltantes() {
        return soporte.crearEsquemaMetadatos().camposObligatorios().stream()
                .filter(c -> meta.get(c) == null || meta.get(c).isBlank()).toList();
    }
}
