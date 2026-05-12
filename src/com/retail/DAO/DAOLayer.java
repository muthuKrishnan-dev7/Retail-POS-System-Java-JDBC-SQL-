package com.retail.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import com.retail.db.DBConnection;
import com.retail.product.Product;
import com.retail.service.BillItem;

public class DAOLayer {

//	Method for Enter User&Password

	public void Authentication() {

		String sql = "SELECT * FROM admin WHERE user_name = ? AND password = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

			Scanner sc = new Scanner(System.in);
			while (true) {

				System.out.print("Enter the UserName ('krishna') : ");
				String user = sc.next();

				System.out.print("Enter the Password ('retailpos') : ");
				String pass = sc.next();

				ps.setString(1, user);
				ps.setString(2, pass);

				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					System.out.println("===== Valid User =====\n");
					break;
				} else {
					System.out.println("===== Invalid User =====\n === Retry ===\n");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method for Print All Product
	public void productShow() throws SQLException {
		String sql = "select * from products";
		try (Connection con = DBConnection.getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);) {

			System.out.println("\n============ PRODUCT LIST ============");
			System.out.printf(" %-5s | %-10s | %-7s | %-5s%n", "ID", "NAME", "PRICE", "STOCK");
			System.out.println("--------------------------------------");

			while (rs.next()) {
				System.out.printf(" %-5d | %-10s | %-7.2f | %-5d%n", rs.getInt("product_id"),
						rs.getString("product_name"), rs.getDouble("price"), rs.getInt("stock"));
			}

			System.out.println("======================================\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		;
	}

//	Method for getting product by Id
	public Product getProductById(int pid) {
		Product p = null;

		String query = "SELECT product_id, product_name, price FROM products WHERE product_id=?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {

			ps.setInt(1, pid);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				p = new Product();
				p.setProductId(rs.getInt("product_id"));
				p.setProductName(rs.getString("product_name"));
				p.setPrice(rs.getDouble("price"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return p;
	}

//	Method for reduce stock after purchase
	public void setStock(int pid, int qty) {

		String query = "UPDATE products SET stock = stock - ? WHERE product_id = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {

			ps.setInt(1, qty); // reduce quantity
			ps.setInt(2, pid); // product id

			int rows = ps.executeUpdate();

//	        System.out.println("Affected Rows : " + rows);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	Method for put Bill in Bill-table
	public int BillTable(double billamt, String mode) throws SQLException {
		String query = "Insert into bills(total_amount, payment_mode) values(?,?) Returning bill_id";
		int billId = 0;
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {

			ps.setDouble(1, billamt);
			ps.setString(2, mode);

			ResultSet rs = ps.executeQuery();
//			System.out.println("Affected rows: " + rows);

			if (rs.next()) {
				billId = rs.getInt("bill_id");
			}

			System.out.println();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return billId;

	}

//	Method for Put bill_items into Bill items table
	public void BillProduct(List<BillItem> billitems, int billId) {

		DAOLayer dao = new DAOLayer();

		for (BillItem product : billitems) {

			String query = "Insert into bill_items (bill_id, product_id, product_name, qty, unit_price, line_total) VALUES (?,?,?,?,?,?)";

			int pid = product.getProductId();
			int qty = product.getProductQty();

			Product p = dao.getProductById(pid);

			double lineTotal = qty * p.getPrice();

			try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {

				ps.setInt(1, billId);
				ps.setInt(2, pid);
				ps.setString(3, p.getProductName());
				ps.setInt(4, qty);
				ps.setDouble(5, p.getPrice());
				ps.setDouble(6, lineTotal);

				int rows = ps.executeUpdate();
//				System.out.println("Affected rows : " + rows);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}
