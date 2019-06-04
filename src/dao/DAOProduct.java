package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import domain.Product;
import domain.ProductPrice;
import factory.ConnectionFactory;
import interfaces.Protection;

public class DAOProduct extends ProductPrice implements Protection {
	Scanner sc = new Scanner(System.in);
	ArrayList<Product> productList = new ArrayList<Product>();

	@Override
	public void insert() {
		String str = "";

		do {
			System.out.println("---------------------------------------------------------");

			String sql = "INSERT INTO product (id, name, barcode, category, price, unity) "
					+ "VALUES(?, ?, ?, ?, ?, ?)";

			System.out.print("Product ID: ");
			int id = sc.nextInt();
			sc.nextLine();

			System.out.print("Product name: ");
			String name = sc.nextLine();

			System.out.print("Product bar code: ");
			String barcode = sc.next();
			sc.nextLine();

			System.out.print("Product category: ");
			String category = sc.nextLine();

			System.out.print("Price of the product R$: ");
			float price = sc.nextFloat();

			System.out.print("Product unity(ies): ");
			int unity = sc.nextInt();

			try {
				Connection connection = ConnectionFactory.connect();
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setInt(1, id);
				ps.setString(2, name);
				ps.setString(3, barcode);
				ps.setString(4, category);
				ps.setFloat(5, price);
				ps.setInt(6, unity);

				ps.executeUpdate();

				System.out.println("PRODUCT INSERTED SUCCESSFULLY!");
				System.out.println("Would you like to register another product? [Y/N]");
				str = sc.next();
				
				connection.close();
				ps.close();
			} catch (SQLException e) {
				System.out.println("PRODUCT WAS NOT INSERTED SUCCESSFULLY!");
				e.printStackTrace();
			}
		} while (str.equals("Y"));
	}

	public ArrayList<Product> fillOut() {
		String sql = "SELECT id, name, barcode, category, price, unity FROM product ORDER BY name ASC";

		try {
			Connection connection = ConnectionFactory.connect();
			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			Product p = null;

			while (rs.next()) {
				p = new Product();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setBarcode(rs.getString("barcode"));
				p.setCategory(rs.getString("category"));
				p.setPrice(rs.getFloat("price"));
				p.setUnity(rs.getInt("unity"));

				productList.add(p);
			}
			
			connection.close();
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productList;
	}

	public void search() {
		String sql = "SELECT id, name, barcode, category, price, unity FROM product WHERE id = ?";

		System.out.print("ENTER THE ID OF THE PRODUCT YOU WOULD LIKE TO SEARCH: ");
		int id = sc.nextInt();

		try {
			Connection connection = ConnectionFactory.connect();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			Product p = null;

			if (rs.next()) {
				p = new Product();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setBarcode(rs.getString("barcode"));
				p.setCategory(rs.getString("category"));
				p.setPrice(rs.getFloat("price"));
				p.setUnity(rs.getInt("unity"));

				System.out.println("---------------------------------------------------------|");
				System.out.println("PRODUCT INFORMATION " + p.getName().toUpperCase());
				System.out.println("---------------------------------------------------------|");
				System.out.println("ID: " + p.getId());
				System.out.println("Name: " + p.getName());
				System.out.println("Bar code: " + p.getBarcode());
				System.out.println("Category: " + p.getCategory());
				System.out.printf("Price R$: %.2f", p.getPrice());
				System.out.println("\nUnity: " + p.getUnity());
			} else {
				int result = 0;

				do {
					System.out.println("Customer with that ID doesn't exist!");
					System.out.println("Please enter with a valid ID: ");
					id = sc.nextInt();
					sc.nextLine();

					ps.setInt(1, id);

					rs = ps.executeQuery();

					if (rs.next()) {
						result = rs.getInt("id");

						p = new Product();
						p.setId(rs.getInt("id"));
						p.setName(rs.getString("name"));
						p.setBarcode(rs.getString("barcode"));
						p.setCategory(rs.getString("category"));
						p.setPrice(rs.getFloat("price"));
						p.setUnity(rs.getInt("unity"));

						System.out.println("---------------------------------------------------------|");
						System.out.println("PRODUCT INFORMATION " + p.getName().toUpperCase());
						System.out.println("---------------------------------------------------------|");
						System.out.println("ID: " + p.getId());
						System.out.println("Name: " + p.getName());
						System.out.println("Bar code: " + p.getBarcode());
						System.out.println("Category: " + p.getCategory());
						System.out.printf("Price R$: %.2f", p.getPrice());
						System.out.println("\nUnity: " + p.getUnity());
					}

				} while (result != id);
			}
			
			connection.close();
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("SEARCH UNAVAILABLE. NO PRODUCT REGISTERED!");
			e.printStackTrace();
		}
	}

	@Override
	public void display() {
		System.out.println("|       LIST OF PRODUCTS REGISTERED IN THE SYSTEM        |\n");

		String sql = "SELECT id, name, barcode, category, price, unity FROM product ORDER BY name ASC";

		try {
			Connection connection = ConnectionFactory.connect();
			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			Product p = null;

			while (rs.next()) {
				p = new Product();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setBarcode(rs.getString("barcode"));
				p.setCategory(rs.getString("category"));
				p.setPrice(rs.getFloat("price"));
				p.setUnity(rs.getInt("unity"));

				System.out.println("ID: " + p.getId());
				System.out.println("Name: " + p.getName());
				System.out.println("Bar code: " + p.getBarcode());
				System.out.println("Category: " + p.getCategory());
				System.out.printf("Price R$: %.2f", p.getPrice());
				System.out.println("\nUnity: " + p.getUnity() + "\n");
			}

			ArrayList<Product> d = fillOut();

			if (d.isEmpty()) {
				System.out.println("Empty Product Stock.");
			}

			connection.close();
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("COULDN'T DISPLAY PRODUCTS!");
			e.printStackTrace();
		}

		System.out.println("\n|                                                        |");
	}

	@Override
	public void update() {
		ArrayList<Product> d = fillOut();

		if (d.isEmpty()) {
			System.out.println("UPDATE UNAVAILABLE. PRODUCT STOCK IS EMPTY!");
		} else {
			System.out.println(" Enter the ID of the product you would like to update: ");
			System.out.println("---------------------------------------------------------");

			String sql = "UPDATE product SET id = ?, name = ?, barcode = ?, category = ?, price = ?, unity = ? "
					+ "WHERE id = ?";

			display();

			int idu = sc.nextInt();
			System.out.println("---------------------------------------------------------");

			System.out.print("New Product ID: ");
			int id = sc.nextInt();
			sc.nextLine();

			System.out.print("New product name: ");
			String name = sc.nextLine();

			System.out.print("New product bar code: ");
			String barcode = sc.next();
			sc.nextLine();

			System.out.print("New product category: ");
			String category = sc.nextLine();

			System.out.print("New product price R$: ");
			float price = sc.nextFloat();

			System.out.print("New product unity(ies): ");
			int unity = sc.nextInt();

			try {
				Connection connection = ConnectionFactory.connect();
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setInt(1, id);
				ps.setString(2, name);
				ps.setString(3, barcode);
				ps.setString(4, category);
				ps.setFloat(5, price);
				ps.setInt(6, unity);
				ps.setInt(7, idu);

				ps.executeUpdate();
				System.out.println("NEW PRODUCT UPDATED SUCCESSFULLY!");
				
				connection.close();
				ps.close();
			} catch (SQLException e) {
				System.out.println("COULDN'T UPDATE NEW PRODUCT!");
				e.printStackTrace();
			}
		}
	}

	@Override
	public void remove() {
		ArrayList<Product> d = fillOut();

		if (d.isEmpty()) {
			System.out.println("NO PRODUCT CAN'T BE REMOVED. PRODUCT STOCK IS EMPTY!");
		} else {
			System.out.println(" [1] to remove all products");
			System.out.println(" [2] to remove only one product");
			System.out.println("---------------------------------------------------------");
			int op = sc.nextInt();

			try {
				Connection connection = ConnectionFactory.connect();
				PreparedStatement ps = null;

				if (op == 1) {
					String removeAllSql = "TRUNCATE TABLE product";

					ps = connection.prepareStatement(removeAllSql);
					ps.executeUpdate();

					System.out.println("PRODUCTS REMOVED SUCCESSFULLY!");
				} else if (op == 2) {
					System.out.println("Enter the ID of the product you would like to remove: ");
					System.out.println("---------------------------------------------------------");
					display();
					int id = sc.nextInt();

					String removeSql = "DELETE FROM product WHERE id = ?";

					ps = connection.prepareStatement(removeSql);
					ps.setInt(1, id);

					ps.executeUpdate();

					System.out.println("PRODUCT REMOVED SUCCESSFULLY!");
				}
				
				connection.close();
				ps.close();
			} catch (SQLException e) {
				System.out.println("ERROR WHILE TRYING TO REMOVE PRODUCT.");
				e.printStackTrace();
			}
		}
	}
}
