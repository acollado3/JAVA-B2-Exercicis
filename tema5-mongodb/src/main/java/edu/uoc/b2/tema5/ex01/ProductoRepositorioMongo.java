package edu.uoc.b2.tema5.ex01;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.*;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.*;

import static com.mongodb.client.model.Filters.*;

/**
 * ============================================================
 * EXERCICI 01 MongoDB / EJERCICIO 01 MongoDB — Operaciones CRUD / Operacions
 * CRUD ⭐⭐
 * ============================================================
 *
 * [CAT] OBJECTIU: Implementa un repositori MongoDB per a la gestió de Productes
 * (documents BSON).
 * [ES] OBJETIVO: Implementa un repositorio MongoDB para la gestión de Productos
 * (documentos BSON).
 *
 * REGLES / REGLAS:
 * - Omple la classe amb la gestió requerida usant el controlador natiu
 * (MongoCollection).
 * - Llena la clase con la gestión requerida usando el controlador nativo
 * (MongoCollection).
 */
public class ProductoRepositorioMongo {

    private final MongoCollection<Document> col;



    public ProductoRepositorioMongo(MongoDatabase db) {
        // [ES] TODO — Obtén la colección "productos" de la base de datos 'db'.
        // [CAT] TODO — Obtén la col·lecció "productos" de la base de dades 'db'.
        // Además / A més: createIndex para: Indexes.text("nombre") y
        // Indexes.ascending("categoria", "precio")
       String uri = "mongodb://localhost:27017";
        MongoClient mongoClient = com.mongodb.client.MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("test_bdd");
        this.col = database.getCollection("productos");
        col.createIndex(new Document("nombre", "text"));
        col.createIndex(new Document("categoria", 1).append("precio", 1));

        //throw new UnsupportedOperationException("TODO");
    }

    /**
     * [CAT] Insereix un producte al MongoDB i retorna el seu id generat (ObjectId).
     * [ES] Inserta un producto en MongoDB y retorna su id generado (ObjectId).
     */
    public ObjectId insertar(String nombre, String categoria, double precio, int stock) {
        // [ES] TODO — Valida (nombre != vacio, precio > 0, stock >= 0). Si falla ->
        // IllegalArgumentException
        // Crea un nuevo Document() y añádele (append) esos valores + "activo"=true +
        // "creado"=new Date()
        // Inserta el documento en la colección y devuelve el ObjectId generado.
        // [CAT] TODO — Valida (nombre != buit, precio > 0, stock >= 0). Si falla ->
        // IllegalArgumentException
        // Crea un nou Document() i afegeix-li (append) aquests valors + "activo"=true +
        // "creado"=new Date()
        // Insereix el document a la col·lecció i retorna l'ObjectId generat.
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");}
            if(precio<=0){throw new IllegalArgumentException("El precio debe ser mayor que 0");}
            if(stock<0){throw new IllegalArgumentException("El stock no puede ser negativo");}
            Document doc = new Document("nombre", nombre)
                    .append("categoria", categoria)
                    .append("precio", precio)
                    .append("stock", stock)
                    .append("activo", true)
                    .append("creado", new java.util.Date());

            InsertOneResult result = col.insertOne(doc);
            return result.getInsertedId().asObjectId().getValue();
            }

        //throw new UnsupportedOperationException("TODO");


    /**
     * [CAT] Cerca aplicant "text search" (cerca textual pel nom).
     * [ES] Búsqueda aplicando "text search" (búsqueda textual por el nombre).
     */
    public List<Document> buscarPorNombre(String textBusqueda) {
        // [ES] TODO — Usa col.find(Filters.text(...)) para devolver la lista de
        // documentos que coincidan con el texto
        // (usa el índice de tipo text). Y extrae (into) a una nueva
        // ArrayList<Document>.
        // [CAT] TODO — Usa col.find(Filters.text(...)) per retornar la llista de
        // documents que coincideixin amb el text
        // (usa l'índex de tipus text). I extreu (into) a un nou ArrayList<Document>.
        return col.find(Filters.text(textBusqueda))
                .into(new ArrayList<>());




        //throw new UnsupportedOperationException("TODO");
    }

    /**
     * [CAT] Llista productes actius (activo=true), ordenats i paginats.
     * [ES] Lista productos activos (activo=true), ordenados y paginados.
     */
    public List<Document> listar(int pagina, int tamañoPagina, String ordenarPorCampo) {
        // [ES] TODO — Devuelve una lista de Document. Filtra por (eq("activo", true)).
        // Ordena ascendiente por el campo indicado. Skip y limit manual (mínimo tamaño
        // 10).
        // [CAT] TODO — Retorna una llista de Document. Filtra per (eq("activo", true)).
        // Ordena ascendent pel camp indicat. Skip i limit manual (mínim mida 10).
        int limit = Math.max(tamañoPagina, 10);
        int skip = (pagina - 1) * limit;

        return col.find(eq("activo", true)) // Filtre booleà
                .sort(Sorts.ascending(ordenarPorCampo))
                .skip(skip)
                .limit(limit)
                .into(new ArrayList<>());
       // throw new UnsupportedOperationException("TODO");
    }

    /**
     * [CAT] Actualitza el preu d'un producte segons ObjectId.
     * [ES] Actualiza el precio de un producto según ObjectId.
     */
    public boolean actualizarPrecio(ObjectId id, double nuevoPrecio) {
        // [ES] TODO — Si nuevoPrecio <= 0 lanza exepción.
        // Aplica un updateOne con SET 'precio' = nuevoPrecio. Retorna true si
        // modifiedCount > 0.
        // [CAT] TODO — Si nuevoPrecio <= 0 llança excepció.
        // Aplica un updateOne amb SET 'precio' = nuevoPrecio. Retorna true si
        // modifiedCount > 0.
        if (nuevoPrecio <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor que 0");
        }

        UpdateResult result = col.updateOne(
                eq("_id", id),
                Updates.set("precio", nuevoPrecio)
        );

        return result.getModifiedCount() > 0;

        //throw new UnsupportedOperationException("TODO");
    }

    /**
     * [CAT] Genera estadístiques agrupant mitjançant Pipeline Aggregation.
     * [ES] Genera estadísticas agrupando mediante Pipeline Aggregation.
     */
    public Map<String, Object> estadisticasPorCategoria() {
        // [ES] TODO — Usa col.aggregate():
        // 1. Match / filtra activo = true
        // 2. Group / agrupa por "$categoria", suma (count) el nº productos y promedio
        // (avg) de "$precio"
        // 3. Retorna un Map donde Key = categoría, y Value = Map interno con los
        // valores.
        // [CAT] TODO — Usa col.aggregate():
        // 1. Match / filtra activo = true
        // 2. Group / agrupa per "$categoria", suma (count) el nº productes i promig
        // (avg) de "$precio"
        // 3. Retorna un Map on Key = categoria, i Value = Map intern amb els valors.
        List<org.bson.conversions.Bson> pipeline = Arrays.asList(
                // 1. Filtrem productes actius
                Aggregates.match(eq("activo", true)),
                // 2. Agrupem per categoria i calculem compte i mitjana
                Aggregates.group("$categoria",
                        Accumulators.sum("cantidad", 1),
                        Accumulators.avg("precioPromedio", "$precio"))
        );

        Map<String, Object> resultadoFinal = new HashMap<>();

        col.aggregate(pipeline).forEach(doc -> {
            String categoria = doc.getString("_id"); // L'ID del grup és la categoria
            Map<String, Object> stats = new HashMap<>();
            stats.put("cantidad", doc.get("cantidad"));
            stats.put("precioPromedio", doc.get("precioPromedio"));

            resultadoFinal.put(categoria != null ? categoria : "Sin Categoria", stats);
        });

        return resultadoFinal;
        //throw new UnsupportedOperationException("TODO");
    }
}
