package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FactoryConnection {
	private static final String URL = "jdbc:mysql://localhost:3306/supermarket?useTimezone=true&serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASSWORD = "yourpassword";

	public static Connection connect() throws SQLException {
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

		return connection;
	}

	public static void main(String[] args) {
		try {
			Connection connection = FactoryConnection.connect();
			System.out.println("Connection successfully completed!");
			connection.close();
		} catch (SQLException e) {
			System.out.println("Connection was not successfully completed!");
			e.printStackTrace();
		}
	}
}
