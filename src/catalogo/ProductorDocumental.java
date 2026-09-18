package catalogo;
import java.util.*;
public class ProductorDocumental {
    public final String nombre;
    public final List<String> cargos;
    public ProductorDocumental(String nombre, List<String> cargos) { this.nombre = nombre; this.cargos = new ArrayList<>(cargos); }
    public ProductorDocumental clonar() { return new ProductorDocumental(nombre, cargos); } // copia la lista
}
