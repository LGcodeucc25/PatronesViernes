package planeacion;
import java.util.*;
// Solo usa las interfaces de producto de FabricaSoporte
public class Planificador {
    static final double HORAS_JORNADA = 6.5;
    static final int ESTACIONES = 2;

    static int copias(Lote l) { return l.fabrica().crearPoliticaAlmacenamiento().copias(); }
    static double volumenGB(Lote l) { return l.imagenes() * l.fabrica().crearPerfilCaptura().mbPorImagen() * copias(l) / 1024.0; }
    static int jornadas(Lote l) {
        double horas = (double) l.imagenes() / l.fabrica().crearPerfilCaptura().imagenesPorHora();
        return (int) Math.ceil(horas / (HORAS_JORNADA * ESTACIONES));
    }

    public static void imprimir(List<Lote> lotes) {
        System.out.println("\nPLAN DE RECURSOS POR LOTE");
        System.out.printf("%-26s %-13s %6s %6s %12s %8s%n", "LOTE", "SOPORTE", "IMGS", "COPIAS", "VOLUMEN", "JORNADAS");
        double gbTotal = 0; int jTotal = 0;
        for (Lote l : lotes) {
            double gb = volumenGB(l); int j = jornadas(l); gbTotal += gb; jTotal += j;
            System.out.printf("%-26s %-13s %6d %6d %9.2f GB %8d%n", l.nombre(), l.fabrica().soporte(), l.imagenes(), copias(l), gb, j);
        }
        System.out.printf("%-54s %9.3f TB %8d%n", "TOTAL", gbTotal / 1024, jTotal);

        int anio = java.time.Year.now().getValue();
        double maxC = lotes.stream().mapToInt(Lote::consultasAnuales).max().orElse(1);
        double maxA = lotes.stream().mapToInt(l -> anio - l.anioMasAntiguo()).max().orElse(1);
        Map<Lote, Double> indice = new HashMap<>();
        for (Lote l : lotes)
            indice.put(l, Math.round((0.55 * l.deterioro() / 5 + 0.30 * l.consultasAnuales() / maxC
                    + 0.15 * (anio - l.anioMasAntiguo()) / maxA) * 1000) / 1000.0);
        List<Lote> orden = new ArrayList<>(lotes);
        orden.sort(Comparator.comparing((Lote l) -> indice.get(l)).thenComparingInt(Lote::deterioro).reversed());

        System.out.println("\nPRIORIZACION Y CRONOGRAMA");
        System.out.printf("%-2s %-26s %3s %8s %6s %8s %6s %4s%n", "#", "LOTE", "DET", "CONS/ANO", "INDICE", "JORNADAS", "INICIO", "FIN");
        int inicio = 1, n = 1;
        for (Lote l : orden) {
            int j = jornadas(l);
            System.out.printf("%-2d %-26s %3d %8d %6.3f %8d %6d %4d%n", n++, l.nombre(), l.deterioro(), l.consultasAnuales(), indice.get(l), j, inicio, inicio + j - 1);
            inicio += j;
        }
    }
}
