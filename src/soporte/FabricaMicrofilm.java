package soporte;
import java.util.List;
public class FabricaMicrofilm implements FabricaSoporte {
    private record Perfil(String especificacion, double mbPorImagen, int imagenesPorHora) implements PerfilCaptura {}
    private record Esquema(String nombre, List<String> camposObligatorios) implements EsquemaMetadatos {}
    private record Politica(int copias, String especificacion) implements PoliticaAlmacenamiento {}
    public String soporte() { return "MICROFILM"; }
    public PerfilCaptura crearPerfilCaptura() { return new Perfil("600 dpi, 8 bits grises, retroiluminacion, TIFF G4", 12, 400); }
    public EsquemaMetadatos crearEsquemaMetadatos() { return new Esquema("Dublin Core extendido", List.of("titulo", "creador", "fecha", "formato", "cobertura")); }
    public PoliticaAlmacenamiento crearPoliticaAlmacenamiento() { return new Politica(2, "local, nube | SHA-256"); }
}
