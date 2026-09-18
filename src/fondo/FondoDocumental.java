package fondo;
// Factory Method: registrar() es comun; crearSignatura() lo define cada fondo
public abstract class FondoDocumental {
    private int consecutivo; // independiente por fondo

    public ItemDocumental registrar(ItemDocumental item) {
        if (item == null || item.soporte == null || item.anio <= 0)
            throw new IllegalArgumentException("Item invalido");
        consecutivo++;
        item.signatura = crearSignatura(item);
        return item;
    }
    protected int consecutivo() { return consecutivo; }
    protected abstract Signatura crearSignatura(ItemDocumental item);
}
