package soporte;
import java.util.List;
public class FabricaPlacaVidrio implements FabricaSoporte {
    private record Perfil(String especificacion, double mbPorImagen, int imagenesPorHora) implements PerfilCaptura {}
    private record Esquema(String nombre, List<String> camposObligatorios) implements EsquemaMetadatos {}
    private record Politica(int copias, String especificacion) implements PoliticaAlmacenamiento {}
    public String soporte() { return "PLACA_VIDRIO"; }
    public PerfilCaptura crearPerfilCaptura() { return new Perfil("1200 dpi, 48 bits color, mesa de luz calibrada, DNG", 180, 45); }
    public EsquemaMetadatos crearEsquemaMetadatos() { return new Esquema("VRA Core", List.of("titulo", "fecha", "autor", "tecnica", "dimensiones", "material", "ubicacion", "tipoObra", "derechos")); }
    public PoliticaAlmacenamiento crearPoliticaAlmacenamiento() { return new Politica(3, "local, nube, cinta | SHA-512"); }
}
