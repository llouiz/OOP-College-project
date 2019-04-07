package domain;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class Customer {

	private int id;
	private String name;
	private String ss;
	private String email;
	private String phone;

	public Customer(int id, String name, String ss, String email, String phone) {
		this.id = id;
		this.name = name;
		this.ss = ss;
		this.email = email;
		this.phone = phone;
	}

	public Customer() {

	}

	public void systemInfo(String info) {
		System.out.println(info);
	}

	public void systemInfo(String time, String date) {
		SimpleDateFormat simpleDataFormat = new SimpleDateFormat(time);
		SimpleDateFormat sdf = new SimpleDateFormat(date);

		System.out.println("Date and time of the operation: " + sdf.format(Calendar.getInstance().getTime()) + " at "
				+ simpleDataFormat.format(Calendar.getInstance().getTime()));
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSS() {
		return this.ss;
	}

	public void setSS(String ss) {
		this.ss = ss;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
