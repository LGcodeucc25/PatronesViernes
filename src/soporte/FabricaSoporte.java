package soporte;
import java.util.List;
// Abstract Factory: cada fabrica crea una familia tecnica coherente
public interface FabricaSoporte {
    String soporte();
    PerfilCaptura crearPerfilCaptura();
    EsquemaMetadatos crearEsquemaMetadatos();
    PoliticaAlmacenamiento crearPoliticaAlmacenamiento();

    interface PerfilCaptura { String especificacion(); double mbPorImagen(); int imagenesPorHora(); }
    interface EsquemaMetadatos { String nombre(); List<String> camposObligatorios(); }
    interface PoliticaAlmacenamiento { int copias(); String especificacion(); }
}
