package relationaldbs.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import relationaldbs.model.User;

/**
 * @author __Alexander__
 */
public class UserDaoImpl implements UserDao {

    private final static String dbURL = "jdbc:postgresql://localhost:5432/happylearning";
    private final static String username = "postgres";
    private final static String password = "admin";

    // ─── Helper: mapea un ResultSet a un objeto User ───────────────────────
    private User mapResultSet(ResultSet rs) throws SQLException {
        User user = new User(null, null, 0, 0, null, null, null, null, null);
        user.setId(rs.getLong("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));
        user.setBalance(rs.getDouble("balance"));
        user.setEmail(rs.getString("email"));
        user.setPhone(rs.getString("phone"));
        user.setAddress(rs.getString("address"));
        user.setRole(rs.getString("role"));
        user.setRegisterDate(rs.getString("registerDate"));
        return user;
    }

    // ─── INSERT ────────────────────────────────────────────────────────────
    @Override
    public boolean insert(User user) {
        String insertSQL = "INSERT INTO users (name, password, balance, email, phone, address, role, registerDate) "
                         + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(dbURL, username, password);
             PreparedStatement ps = conn.prepareStatement(insertSQL)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setDouble(3, user.getBalance());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPhone());
            ps.setString(6, user.getAddress());
            ps.setString(7, user.getRole());
            ps.setString(8, user.getRegisterDate());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ─── DELETE ────────────────────────────────────────────────────────────
    @Override
    public boolean delete(Long id) {
        String deleteSQL = "DELETE FROM users WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(dbURL, username, password);
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
    public void update(User user) {
        String updateSQL = "UPDATE users SET name = ?, password = ?, balance = ?, email = ?, "
                         + "phone = ?, address = ?, role = ?, registerDate = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(dbURL, username, password);
             PreparedStatement ps = conn.prepareStatement(updateSQL)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setDouble(3, user.getBalance());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPhone());
            ps.setString(6, user.getAddress());
            ps.setString(7, user.getRole());
            ps.setString(8, user.getRegisterDate());
            ps.setLong(9, user.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ─── FIND BY ID ────────────────────────────────────────────────────────
    @Override
    public User find(Long id) {
        String findSQL = "SELECT * FROM users WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(dbURL, username, password);
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

    // ─── FIND BY EMAIL ─────────────────────────────────────────────────────
    @Override
    public User find(String email) {
        String findSQL = "SELECT * FROM users WHERE email = ?";

        try (Connection conn = DriverManager.getConnection(dbURL, username, password);
             PreparedStatement ps = conn.prepareStatement(findSQL)) {

            ps.setString(1, email);

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

    // ─── FIND ALL BY EMAIL (devuelve el primero que coincida) ──────────────
    @Override
    public User findAll(String email) {
        String findSQL = "SELECT * FROM users WHERE email ILIKE ?";
        //                                                  ↑ ILIKE = case insensitive en PostgreSQL

        try (Connection conn = DriverManager.getConnection(dbURL, username, password);
             PreparedStatement ps = conn.prepareStatement(findSQL)) {

            ps.setString(1, "%" + email + "%");

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
    @Override
    public List<User> findAll() {
        String findAllSQL = "SELECT * FROM users";
        List<User> users = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(dbURL, username, password);
             PreparedStatement ps = conn.prepareStatement(findAllSQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                users.add(mapResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
