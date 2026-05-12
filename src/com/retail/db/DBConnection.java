package com.retail.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	public static Connection getConnection() {

		Connection con = null;

		try {
			Class.forName("org.postgresql.Driver");

			String url = "jdbc:postgresql://aws-1-ap-south-1.pooler.supabase.com:5432/postgres?sslmode=require";
			String user = "postgres.hhpjtobsdjhwvuetpnhk";
			String password = "rootretailpos";

			con = DriverManager.getConnection(url, user, password);

		} catch (Exception e) {
			System.out.println("Database Connection Failed!\n===If Connection has failed then use mobile hotspot(Wifi) instead of other wifi===");
			e.printStackTrace();
		}

		return con;
	}
}