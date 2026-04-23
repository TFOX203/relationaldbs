package relationaldbs.test;

import relationaldbs.dao.ProductDaoImpl;
import relationaldbs.model.Product;

public class ProductDaoTest {
    public static void main(String[] args) {

        ProductDaoImpl productDao = new ProductDaoImpl();

        // ─── CREAR TABLA ──────────────────────────────────────────────────
        productDao.createTable();

        // ─── INSERT ───────────────────────────────────────────────────────
        System.out.println("=== INSERT PRODUCTS ===");
        productDao.insert(new Product(0, "Audi A3",        "Berlina compacta 150CV TFSI",          28999.99,  5, "Berlina",   "Audi", "nuevo"  ));
        productDao.insert(new Product(0, "Audi A4",        "Berlina ejecutiva 200CV quattro",       42999.99,  3, "Berlina",   "Audi", "oferta" ));
        productDao.insert(new Product(0, "Audi A6",        "Sedán de lujo 245CV híbrido",           58999.99,  4, "Berlina",   "Audi", "nuevo"  ));
        productDao.insert(new Product(0, "Audi Q3",        "SUV urbano 150CV S-Tronic",             35999.99,  6, "SUV",       "Audi", "nuevo"  ));
        productDao.insert(new Product(0, "Audi Q5",        "SUV mediano 204CV quattro",             52999.99,  4, "SUV",       "Audi", "oferta" ));
        productDao.insert(new Product(0, "Audi Q7",        "SUV grande 7 plazas 286CV",             74999.99,  2, "SUV",       "Audi", "premium"));
        productDao.insert(new Product(0, "Audi TT",        "Coupé deportivo 230CV S-Tronic",        46999.99,  3, "Deportivo", "Audi", "premium"));
        productDao.insert(new Product(0, "Audi R8",        "Superdeportivo V10 620CV",             172999.99,  1, "Deportivo", "Audi", "premium"));
        productDao.insert(new Product(0, "Audi e-tron",    "SUV eléctrico 408CV 300km autonomía",   79999.99,  3, "Eléctrico", "Audi", "nuevo"  ));
        productDao.insert(new Product(0, "Audi RS6 Avant", "Familiar deportivo 600CV quattro",     125999.99,  2, "Familiar",  "Audi", "premium"));

        // ─── FIND BY ID ───────────────────────────────────────────────────
        System.out.println("=== FIND PRODUCT BY ID ===");
        Product productFound = productDao.find(1L);
        if (productFound != null)
            System.out.println("Producto encontrado: " + productFound.getName() + " - " + productFound.getPrice() + "€");

        // ─── FIND BY NAME ─────────────────────────────────────────────────
        System.out.println("=== FIND PRODUCT BY NAME ===");
        Product productByName = productDao.find("Audi A3");
        if (productByName != null)
            System.out.println("Producto por nombre: " + productByName.getName() + " | stock: " + productByName.getStock());

        // ─── FIND ALL ─────────────────────────────────────────────────────
        System.out.println("=== FIND ALL PRODUCTS ===");
        productDao.findAll().forEach(p ->
            System.out.println("- " + p.getName() + " | " + p.getPrice() + "€ | stock: " + p.getStock() + " | " + p.getCategory()));

        // ─── UPDATE ───────────────────────────────────────────────────────
        System.out.println("=== UPDATE PRODUCT ===");
        productFound.setPrice(25999.99);
        productFound.setStock(8);
        productDao.update(productFound);
        System.out.println("Producto actualizado: " + productDao.find(1L).getPrice() + "€");

        // ─── DELETE ───────────────────────────────────────────────────────
        System.out.println("=== DELETE PRODUCT ===");
        boolean productDeleted = productDao.delete(10L);
        System.out.println("Producto eliminado: " + productDeleted);
    }
}