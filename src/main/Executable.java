package main;

import java.util.Scanner;

import dao.DAOCustomer;
import dao.DAOProduct;
import domain.Customer;

public class Executable {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Customer c = new Customer();
		DAOCustomer dc = new DAOCustomer();
		DAOProduct dp = new DAOProduct();

		int op = 0;

		do {
			System.out.println("|>>>>>>>>>>>>>>>>>> Registration System <<<<<<<<<<<<<<<<<<<|");
			System.out.println("[1] To Register Product");
			System.out.println("[2] To Register Customer");
			System.out.println("[3] To Exit");
			op = sc.nextInt();
			switch (op) {
			case 1:
				do {
					System.out.println(" |>>>>>>>>>>>>>>>> Product Registration <<<<<<<<<<<<<<<<<<<|");
					System.out.println(" [1] To Insert Product");
					System.out.println(" [2] To Search Product");
					System.out.println(" [3] To Display Product");
					System.out.println(" [4] To Update Product");
					System.out.println(" [5] To Remove Product");
					System.out.println(" [6] To Go back to the main menu");
					op = sc.nextInt();
					switch (op) {
					case 1:
						dp.insert();
						break;
					case 2:
						dp.search();
						break;
					case 3:
						dp.display();
						break;
					case 4:
						dp.update();
						break;
					case 5:
						dp.remove();
						break;
					}
				} while (op < 6);
				break;
			case 2:
				do {
					System.out.println(" |>>>>>>>>>>>>>>>> Customer Registration <<<<<<<<<<<<<<<<<<<|");
					System.out.println(" [1] To Register Customer");
					System.out.println(" [2] To Search Customer");
					System.out.println(" [3] To Display Customer");
					System.out.println(" [4] To Update Customer");
					System.out.println(" [5] To Remove Customer");
					System.out.println(" [6] To Go back to the main menu");
					op = sc.nextInt();
					switch (op) {
					case 1:
						dc.insert();
						break;
					case 2:
						dc.search();
						break;
					case 3:
						dc.display();
						break;
					case 4:
						dc.update();
						break;
					case 5:
						dc.remove();
						break;
					}
				} while (op < 6);
				break;
			case 3:
				System.out.println("Operation Closed...\n");
				break;
			default:
				System.out.println("Enter a valid number between 1 and 3!");

			}
		} while (op <= 0 || op > 3);
		sc.close();

		c.systemInfo("System Information: ");
		c.systemInfo("HH:mm:ss", "MM/dd/yyyy");
		System.out.println("|>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<|");
	}
}
