package catalogo;
import java.util.*;
// Prototype: clon profundo (descriptores y productor no se comparten)
public class FichaCatalografica implements Cloneable {
    public final String serie, fondo, alcance, condicionesAcceso, idioma;
    public ProductorDocumental productor;
    public List<String> descriptores;
    public String titulo, fecha;
    public int folios;

    public FichaCatalografica(String serie, String fondo, ProductorDocumental productor, String alcance,
                              String condicionesAcceso, String idioma, List<String> descriptores) {
        this.serie = serie; this.fondo = fondo; this.productor = productor; this.alcance = alcance;
        this.condicionesAcceso = condicionesAcceso; this.idioma = idioma; this.descriptores = new ArrayList<>(descriptores);
    }
    public FichaCatalografica clonar() {
        try {
            FichaCatalografica c = (FichaCatalografica) super.clone();
            c.descriptores = new ArrayList<>(descriptores);
            c.productor = productor.clonar();
            return c;
        } catch (CloneNotSupportedException e) { throw new AssertionError(e); }
    }
    public Map<String, String> metadatos() {
        Map<String, String> m = new HashMap<>();
        m.put("titulo", titulo); m.put("fecha", fecha); m.put("nivelDescripcion", "Unidad documental");
        m.put("volumen", folios + " folios"); m.put("productor", productor.nombre);
        m.put("alcance", alcance); m.put("condicionesAcceso", condicionesAcceso);
        return m;
    }
    public String resumen() {
        return "descriptores: " + descriptores.size() + " " + descriptores + " | cargos productor: " + productor.cargos.size() + " " + productor.cargos;
    }
}
