# Patrones aplicados

**Abstract Factory** — `soporte/FabricaSoporte` (+ `FabricaPapelFragil`, `FabricaMicrofilm`, `FabricaPlacaVidrio`).
Cada soporte exige perfil de captura, esquema de metadatos y politica de almacenamiento que van juntos. Cada fabrica crea los tres productos de su familia (records privados), y `Lote` recibe solo la fabrica: es imposible mezclar el perfil de placa con la politica del microfilm. `Planificador` usa unicamente las interfaces `PerfilCaptura`, `EsquemaMetadatos`, `PoliticaAlmacenamiento`.

**Factory Method** — `fondo/FondoDocumental` (+ `FondoNotarial`, `FondoCartografico`, `FondoFotografico`).
`registrar()` valida, incrementa el consecutivo y asigna la signatura; el metodo fabrica `crearSignatura()` lo implementa cada fondo con su regla. Cada instancia de fondo tiene su propio consecutivo.

**Prototype** — `catalogo/FichaCatalografica.clonar()` y `ProductorDocumental.clonar()`.
El 90 % de la ficha de una serie se repite; se clona la ficha modelo y solo se ajustan titulo, fecha y folios. El clon es profundo: nueva lista de descriptores y nuevo productor con nueva lista de cargos, por eso modificar un clon no altera el modelo (se evidencia en la salida).
