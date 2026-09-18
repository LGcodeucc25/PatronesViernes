package fondo;
public class FondoNotarial extends FondoDocumental {
    protected Signatura crearSignatura(ItemDocumental i) {
        int notaria = Integer.parseInt(i.meta.getOrDefault("notaria", "0"));
        return new Signatura(String.format("NOT-%d-N%02d-%05d", i.anio, notaria, consecutivo()));
    }
}
