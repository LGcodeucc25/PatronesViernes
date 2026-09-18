package fondo;
public class FondoCartografico extends FondoDocumental {
    protected Signatura crearSignatura(ItemDocumental i) {
        String escala = i.meta.getOrDefault("escala", "0").replaceAll("[.,:\\s]", "");
        return new Signatura(String.format("CAR-%s-%04d", escala, consecutivo()));
    }
}
