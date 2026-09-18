package soporte;
import java.util.List;
public class FabricaPapelFragil implements FabricaSoporte {
    private record Perfil(String especificacion, double mbPorImagen, int imagenesPorHora) implements PerfilCaptura {}
    private record Esquema(String nombre, List<String> camposObligatorios) implements EsquemaMetadatos {}
    private record Politica(int copias, String especificacion) implements PoliticaAlmacenamiento {}
    public String soporte() { return "PAPEL_FRAGIL"; }
    public PerfilCaptura crearPerfilCaptura() { return new Perfil("400 dpi, 24 bits color, luz fria difusa, TIFF sin compresion", 65, 120); }
    public EsquemaMetadatos crearEsquemaMetadatos() { return new Esquema("ISAD(G)", List.of("titulo", "fecha", "nivelDescripcion", "volumen", "productor", "alcance", "condicionesAcceso")); }
    public PoliticaAlmacenamiento crearPoliticaAlmacenamiento() { return new Politica(3, "local, nube, cinta | SHA-256"); }
}
