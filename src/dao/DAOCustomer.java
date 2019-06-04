package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import domain.Customer;
import domain.Product;
import factory.ConnectionFactory;
import interfaces.Protection;

public class DAOCustomer extends Product implements Protection {
	Scanner sc = new Scanner(System.in);
	ArrayList<Customer> customerList = new ArrayList<Customer>();

	@Override
	public void insert() {
		String str = "";
		do {
			System.out.println("---------------------------------------------------------");

			String sql = "INSERT INTO customer (id, name, cpf, email, phone) VALUES(?, ?, ?, ?, ?)";

			System.out.print("Customer ID: ");
			int id = sc.nextInt();
			sc.nextLine();

			System.out.print("Customer Name: ");
			String name = sc.nextLine();

			System.out.print("Customer Social Security: ");
			String ss = sc.next();
			sc.nextLine();

			System.out.print("Customer Email: ");
			String email = sc.nextLine();

			System.out.print("Customer Phone Number: ");
			String phone = sc.nextLine();

			try {
				Connection connection = ConnectionFactory.connect();
				PreparedStatement ps = connection.prepareStatement(sql);

				ps.setInt(1, id);
				ps.setString(2, name);
				ps.setString(3, ss);
				ps.setString(4, email);
				ps.setString(5, phone);

				ps.executeUpdate();

				System.out.println("Customer registered successfully.");
				ps.close();

				System.out.println("Would you like to register another customer? [Y/N]");
				str = sc.next();

				connection.close();
				ps.close();
			} catch (SQLException e) {
				System.out.println("Customer was not registered successfully.");
				e.printStackTrace();
			}
		} while (str.equals("Y"));
	}

	public ArrayList<Customer> fillOut() {
		String sql = "SELECT id, name, cpf, email, phone FROM customer ORDER BY name ASC";

		try {
			Connection connection = ConnectionFactory.connect();
			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			Customer c = null;

			while (rs.next()) {
				c = new Customer();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setSS(rs.getString("cpf"));
				c.setEmail(rs.getString("email"));
				c.setPhone(rs.getString("phone"));

				customerList.add(c);
			}

			connection.close();
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customerList;
	}

	public void search() {
		String sql = "SELECT id, name, cpf, email, phone FROM customer WHERE id = ?";

		System.out.print("Enter the ID of the customer you would like to search: ");
		int id = sc.nextInt();

		try {
			Connection connection = ConnectionFactory.connect();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			Customer customer = null;

			if (rs.next()) {
				customer = new Customer();
				customer.setId(rs.getInt("id"));
				customer.setName(rs.getString("name"));
				customer.setSS(rs.getString("cpf"));
				customer.setEmail(rs.getString("email"));
				customer.setPhone(rs.getString("phone"));

				System.out.println("---------------------------------------------------------|");
				System.out.println(customer.getName().toUpperCase() + "'S INFORMATIONS ");
				System.out.println("---------------------------------------------------------|");
				System.out.println("ID: " + customer.getId());
				System.out.println("Name: " + customer.getName());
				System.out.println("Socil Security: " + customer.getSS());
				System.out.println("Email: " + customer.getEmail());
				System.out.println("Phone: " + customer.getPhone());
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

						customer = new Customer();
						customer.setId(rs.getInt("id"));
						customer.setName(rs.getString("name"));
						customer.setSS(rs.getString("cpf"));
						customer.setEmail(rs.getString("email"));
						customer.setPhone(rs.getString("phone"));

						System.out.println("---------------------------------------------------------|");
						System.out.println(customer.getName().toUpperCase() + "'S INFORMATIONS ");
						System.out.println("---------------------------------------------------------|");
						System.out.println("ID: " + customer.getId());
						System.out.println("Name: " + customer.getName());
						System.out.println("Socil Security: " + customer.getSS());
						System.out.println("Email: " + customer.getEmail());
						System.out.println("Phone: " + customer.getPhone());
					}

				} while (result != id);
			}

			connection.close();
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.out.println("Research unavailable. No customer registered!");
			e.printStackTrace();
		}
	}

	@Override
	public void display() {
		System.out.println("|       List of customers registered in the system       |\n");

		String sql = "SELECT id, name, cpf, email, phone FROM customer ORDER BY name ASC";

		try {
			Connection connection = ConnectionFactory.connect();
			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			Customer c = null;

			while (rs.next()) {
				c = new Customer();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setSS(rs.getString("cpf"));
				c.setEmail(rs.getString("email"));
				c.setPhone(rs.getString("phone"));

				System.out.println("ID: " + c.getId());
				System.out.println("Name: " + c.getName());
				System.out.println("Socil Security: " + c.getSS());
				System.out.println("Email: " + c.getEmail());
				System.out.println("Phone: " + c.getPhone() + "\n");
			}

			ArrayList<Customer> d = fillOut();

			if (d.isEmpty()) {
				System.out.println("Empty Product Stock.");
			}

			connection.close();
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("\n|                                                       |");
	}

	@Override
	public void update() {
		ArrayList<Customer> d = fillOut();

		if (d.isEmpty()) {
			System.out.println("UPDATE UNAVAILABLE. PRODUCT STOCK IS EMPTY!");
		} else {
			System.out.println(" Enter the ID of the customer you would like to update: ");
			System.out.println("---------------------------------------------------------");

			String sql = "UPDATE customer SET id = ?, name = ?, cpf = ?, email = ?, phone = ? " + "WHERE id = ?";

			display();

			int userId = sc.nextInt();
			System.out.println("---------------------------------------------------------");

			System.out.print("Customer New ID: ");
			int id = sc.nextInt();
			sc.nextLine();

			System.out.print("New Customer Name: ");
			String name = sc.nextLine();

			System.out.print("New Customer Social Security: ");
			String ss = sc.next();
			sc.nextLine();

			System.out.print("New Customer Mail: ");
			String email = sc.nextLine();

			System.out.print("New Customer Phone Number: ");
			String phone = sc.nextLine();

			try {
				Connection connection = ConnectionFactory.connect();
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setInt(1, id);
				ps.setString(2, name);
				ps.setString(3, ss);
				ps.setString(4, email);
				ps.setString(5, phone);
				ps.setInt(6, userId);

				ps.executeUpdate();
				System.out.println("New customer updated successfully!");

				connection.close();
				ps.close();
			} catch (SQLException e) {
				System.out.println("Couldn't update the new customer!");
				e.printStackTrace();
			}
		}
	}

	@Override
	public void remove() {
		ArrayList<Customer> d = fillOut();

		if (d.isEmpty()) {
			System.out.println("NO PRODUCT CAN'T BE REMOVED. PRODUCT STOCK IS EMPTY!");
		} else {
			System.out.println(" [1] to remove all customers");
			System.out.println(" [2] to remove only one customer");
			System.out.println("---------------------------------------------------------");
			int op = sc.nextInt();

			try {
				Connection connection = ConnectionFactory.connect();
				PreparedStatement ps = null;

				if (op == 1) {
					String removeAllSql = "TRUNCATE TABLE customer";

					ps = connection.prepareStatement(removeAllSql);
					ps.executeUpdate();

					System.out.println("Customers removed successfully!");
				} else if (op == 2) {
					System.out.println("Enter the ID of the customer you would like to remove: ");
					System.out.println("---------------------------------------------------------");
					display();
					int id = sc.nextInt();

					String removeSql = "DELETE FROM customer WHERE id = ?";

					ps = connection.prepareStatement(removeSql);
					ps.setInt(1, id);

					ps.executeUpdate();
					System.out.println("Customer removed successfully!");
				}
				connection.close();
				ps.close();
			} catch (SQLException e) {
				System.out.println("Error while trying to remove customer.");
				e.printStackTrace();
			}
		}
	}
}
