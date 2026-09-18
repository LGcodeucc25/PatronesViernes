package app;
import java.util.*;
import catalogo.*;
import fondo.*;
import planeacion.*;
import soporte.*;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        System.out.println("=== ARCHIVO HISTORICO DEPARTAMENTAL - PLAN DE DIGITALIZACION ===");
        FabricaSoporte papel = new FabricaPapelFragil(), micro = new FabricaMicrofilm(), placa = new FabricaPlacaVidrio();
        FondoDocumental notarial = new FondoNotarial(), carto = new FondoCartografico(), foto = new FondoFotografico();
        List<ItemDocumental> todos = new ArrayList<>();

        // 1. Prototype + generacion masiva
        FichaCatalografica modelo = new FichaCatalografica("Escrituras publicas - Notaria 3", "NOTARIAL",
                new ProductorDocumental("Notaria Tercera de Pasto", List.of("Notario", "Escribiente")),
                "Compraventas, testamentos y poderes", "Consulta en sala", "Espanol",
                List.of("Compraventa", "Testamentos", "Poderes", "Hipotecas"));
        System.out.println("Ficha modelo: '" + modelo.serie + "' | " + modelo.resumen());
        int[] folios = {8, 12, 5, 20, 9};
        ItemDocumental ultimoNot = null;
        for (int i = 0; i < 5; i++) {
            FichaCatalografica f = modelo.clonar();
            f.titulo = "Escritura " + (112 + i * 6) + " de 1887"; f.fecha = "1887"; f.folios = folios[i];
            String marca = "";
            if (i == 1) {
                f.descriptores.add("Litigio de tierras");
                f.productor.cargos.set(0, "Notario suplente");
                marca = "  <- descriptor agregado y cargo modificado";
            }
            Map<String, String> m = f.metadatos(); m.put("notaria", "3");
            ultimoNot = notarial.registrar(new ItemDocumental(1887, papel, m));
            todos.add(ultimoNot);
            System.out.printf("%s  %-22s folios %-3d descriptores %d%s%n", ultimoNot.signatura(), f.titulo, f.folios, f.descriptores.size(), marca);
        }
        System.out.println("Verificacion ficha modelo -> " + modelo.resumen() + " (intacta)");

        // 2. Factory Method: consecutivos independientes por fondo
        todos.add(carto.registrar(new ItemDocumental(1905, micro, Map.of("titulo", "Plano de Pasto", "creador", "Oficina de Catastro",
                "fecha", "1905", "formato", "Plano", "cobertura", "Pasto", "escala", "25000"))));
        todos.add(carto.registrar(new ItemDocumental(1912, micro, Map.of("titulo", "Plano del Ejido", "creador", "Anonimo",
                "fecha", "1912", "escala", "25000")))); // incompleto
        todos.add(foto.registrar(new ItemDocumental(1927, placa, Map.of("titulo", "Plaza de Narino", "fecha", "1927",
                "autor", "Quintero Diaz Lopez", "tecnica", "Gelatina de plata", "dimensiones", "13x18 cm", "material", "Vidrio",
                "ubicacion", "Pasto", "tipoObra", "Fotografia", "derechos", "Dominio publico"))));
        todos.add(foto.registrar(new ItemDocumental(1931, placa, Map.of("titulo", "Procesion", "fecha", "1931",
                "material", "Vidrio", "ubicacion", "Pasto", "tipoObra", "Fotografia", "derechos", "Dominio publico")))); // incompleto
        System.out.println("\nSIGNATURAS POR FONDO (consecutivos independientes)");
        System.out.println("NOTARIAL     -> " + ultimoNot.signatura());
        for (ItemDocumental it : todos.subList(5, todos.size()))
            System.out.println((it.signatura().codigo().startsWith("CAR") ? "CARTOGRAFICO" : "FOTOGRAFICO ") + " -> " + it.signatura());

        // 3. Plan, priorizacion y cronograma (Abstract Factory)
        Planificador.imprimir(List.of(
                new Lote("Protocolos 1880-1890", papel, 24000, 4, 520, 1880),
                new Lote("Prensa regional 1955-1970", micro, 86000, 2, 310, 1955),
                new Lote("Placas Quintero", placa, 1800, 5, 140, 1915),
                new Lote("Mapas del Cabildo", papel, 3500, 3, 90, 1850)));

        // 4. Validacion de metadatos
        System.out.println("\nMETADATOS INCOMPLETOS");
        for (ItemDocumental it : todos)
            if (!it.camposFaltantes().isEmpty())
                System.out.println(it.signatura() + " -> faltan: " + String.join(", ", it.camposFaltantes()));
    }
}
