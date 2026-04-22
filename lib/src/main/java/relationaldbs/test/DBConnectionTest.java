package relationaldbs.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnectionTest {

	private final static String dbURL = "jdbc:postgresql://localhost:5432/postgres";
	private final static String username = "postgres";
	private final static String password = "admin";

	public static void main(String[] args) {

		try {
			Connection conn = DriverManager.getConnection(dbURL, username, password);
			System.out.println("Conexión establecida: " + conn);

			createDataBase(conn);

			// ─── DROP TABLE ───────────────────────────────────────────────
			String dropTableSQL = "DROP TABLE IF EXISTS users";
			PreparedStatement ps = conn.prepareStatement(dropTableSQL);
			ps.executeUpdate();
			ps.close();

			// ─── CREATE TABLE ─────────────────────────────────────────────
			// ✅ FIX 1: faltaba coma entre phone y balance
			// ✅ FIX 2: añadidos campos address, role, registerDate que se usan en el INSERT
			String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" + "id SERIAL PRIMARY KEY,"
					+ "name VARCHAR(255)," + "password VARCHAR(255)," + "email VARCHAR(255)," + "phone VARCHAR(255)," // ✅
																														// coma
																														// añadida
																														// aquí
					+ "balance FLOAT," + "address VARCHAR(255)," + "role VARCHAR(100)," + "registerDate VARCHAR(50)"
					+ ")";

			ps = conn.prepareStatement(createTableSQL);
			ps.executeUpdate();
			ps.close();

			// ─── INSERT ───────────────────────────────────────────────────
			// ✅ FIX 3: el INSERT tenía los valores desordenados y mal formateados
			// ✅ FIX 4: PostgreSQL no acepta múltiples filas con columnas distintas
			String insertSQL = "INSERT INTO users (name, password, balance, email, phone, address, role, registerDate) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

			ps = conn.prepareStatement(insertSQL);
			ps.setString(1, "Sasha");
			ps.setString(2, "12343");
			ps.setDouble(3, 3000);
			ps.setString(4, "sasha@email.com");
			ps.setString(5, "644420201");
			ps.setString(6, "Calle Ramadan 23");
			ps.setString(7, "user");
			ps.setString(8, "12/05/2021");
			System.out.println("Filas insertadas: " + ps.executeUpdate());
			ps.close();

			// ─── SELECT ───────────────────────────────────────────────────
			selectByName(conn, "Sasha");
			selectByName(conn, "Lucas");

			// ─── DELETE ───────────────────────────────────────────────────
			deleteByName(conn, "Panbro");
			deleteByName(conn, "Sasha");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ✅ FIX 5: eliminado ps como parámetro — se crea dentro del método
	// ✅ FIX 6: usar executeQuery() en SELECT, no executeUpdate()
	// ✅ FIX 7: usar parámetro ? en lugar de concatenar el nombre (evita SQL
	// Injection)
	// ✅ FIX 8: columna correcta es "name", no "username"
	private static void selectByName(Connection conn, String name) throws SQLException {

		String selectSQL = "SELECT * FROM users WHERE name = ?";

		try (PreparedStatement ps = conn.prepareStatement(selectSQL)) {
			ps.setString(1, name);

			try (ResultSet rs = ps.executeQuery()) { // ✅ executeQuery para SELECT
				if (rs.next()) {
					System.out.println("Nombre: " + rs.getString("name"));
					System.out.println("Password: " + rs.getString("password"));
					System.out.println("Balance: " + rs.getDouble("balance"));
				} else {
					System.out.println("No se encontró el usuario: " + name);
				}
			}
		}
	}

	// ✅ FIX 9: usar parámetro ? en lugar de concatenar (evita SQL Injection)
	// ✅ FIX 10: columna correcta es "name", no "username"
	private static void deleteByName(Connection conn, String name) throws SQLException {

		String deleteSQL = "DELETE FROM users WHERE name = ?";

		try (PreparedStatement ps = conn.prepareStatement(deleteSQL)) {
			ps.setString(1, name);
			System.out.println("Filas eliminadas (" + name + "): " + ps.executeUpdate());
		}
	}

	private static void createDataBase(Connection conn) {
		try {
			String dbCreationSQL = "CREATE DATABASE happylearning";
			PreparedStatement ps = conn.prepareStatement(dbCreationSQL);
			ps.executeUpdate();
			ps.close();
			System.out.println("Base de datos 'happylearning' creada.");
		} catch (Exception e) {
			// Si ya existe lanza excepción, es normal
			System.out.println("La base de datos ya existe o no se pudo crear: " + e.getMessage());
		}
	}
}