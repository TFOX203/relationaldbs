package relationaldbs.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import relationaldbs.model.Product;
import relationaldbs.util.DBHelper;

public class ProductDaoImpl implements ProductDao {

    

    // ─── Helper: mapea un ResultSet a un objeto Product ───────────────────
    private Product mapResultSet(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getLong("id"));
        product.setName(rs.getString("name"));
        product.setDescription(rs.getString("description"));
        product.setPrice(rs.getDouble("price"));
        product.setStock(rs.getInt("stock"));
        product.setCategory(rs.getString("category"));
        product.setBrand(rs.getString("brand"));
        product.setTag(rs.getString("tag"));
        return product;
    }

    // ─── CREATE TABLE ──────────────────────────────────────────────────────
    public void createTable() {
        String dropSQL   = "DROP TABLE IF EXISTS products";
        String createSQL = "CREATE TABLE IF NOT EXISTS products ("
                + "id          SERIAL PRIMARY KEY,"
                + "name        VARCHAR(255),"
                + "description VARCHAR(500),"
                + "price       FLOAT,"
                + "stock       INTEGER,"
                + "category    VARCHAR(100),"
                + "brand       VARCHAR(100),"
                + "tag         VARCHAR(100)"
                + ")";

        try (Connection conn   = DBHelper.getConnection();
             PreparedStatement drop   = conn.prepareStatement(dropSQL);
             PreparedStatement create = conn.prepareStatement(createSQL)) {

            drop.executeUpdate();
            create.executeUpdate();
            System.out.println("Tabla 'products' creada correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ─── INSERT ────────────────────────────────────────────────────────────
    @Override
    public boolean insert(Product product) {
        String insertSQL = "INSERT INTO products (name, description, price, stock, category, brand, tag) "
                         + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(insertSQL)) {

            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4,    product.getStock());
            ps.setString(5, product.getCategory());
            ps.setString(6, product.getBrand());
            ps.setString(7, product.getTag());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ─── DELETE ────────────────────────────────────────────────────────────
    @Override
    public boolean delete(long id) {
        String deleteSQL = "DELETE FROM products WHERE id = ?";

        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(deleteSQL)) {

            ps.setLong(1, id);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ─── UPDATE ────────────────────────────────────────────────────────────
    @Override
    public void update(Product product) {
        String updateSQL = "UPDATE products SET name = ?, description = ?, price = ?, "
                         + "stock = ?, category = ?, brand = ?, tag = ? WHERE id = ?";

        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(updateSQL)) {

            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4,    product.getStock());
            ps.setString(5, product.getCategory());
            ps.setString(6, product.getBrand());
            ps.setString(7, product.getTag());
            ps.setLong(8,   product.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ─── FIND BY ID ────────────────────────────────────────────────────────
    @Override
    public Product find(long id) {
        String findSQL = "SELECT * FROM products WHERE id = ?";

        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(findSQL)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSet(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ─── FIND BY NAME ──────────────────────────────────────────────────────
    public Product find(String name) {
        String findSQL = "SELECT * FROM products WHERE name ILIKE ?";

        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(findSQL)) {

            ps.setString(1, "%" + name + "%");

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSet(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ─── FIND ALL ──────────────────────────────────────────────────────────
    public List<Product> findAll() {
        String findAllSQL = "SELECT * FROM products";
        List<Product> products = new ArrayList<>();

        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(findAllSQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                products.add(mapResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}