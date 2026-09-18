package fondo;
import java.util.Arrays;
import java.util.stream.Collectors;
public class FondoFotografico extends FondoDocumental {
    protected Signatura crearSignatura(ItemDocumental i) {
        String autor = i.meta.get("autor");
        String ini = (autor == null || autor.isBlank()) ? "XXX"
                : Arrays.stream(autor.trim().split("\\s+")).map(w -> w.substring(0, 1).toUpperCase()).collect(Collectors.joining());
        return new Signatura(String.format("FOT-%ds-%s-%04d", i.anio / 10 * 10, ini, consecutivo()));
    }
}
