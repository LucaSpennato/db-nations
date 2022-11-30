package org.generation.italy;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Main {
	
	private static final String URL = "jdbc:mysql://localhost:3306/nations";
	private static final String USER = "root";
	private static final String PWS = "root";

	public static void main(String[] args) {
		
try (Connection con = DriverManager.getConnection(URL, USER, PWS)) {
			
			final String sql = "SELECT * FROM countries WHERE name = ?";
			
			try (PreparedStatement ps = con.prepareStatement(sql)) {
				
				Scanner sc = new Scanner(System.in); 
				
				System.out.println("Ricerca le informazioni di una nazione: ");
				
				String input = sc.next();
				
				ps.setString(1, input);
				
				sc.close();
				
				try (ResultSet rs = ps.executeQuery()) {
					
					while(rs.next()) {
						
						final int id = rs.getInt(1);
						final String name = rs.getString(2);
						final String countryCode = rs.getString(5);
						
						System.out.println(
							id + " - " 
							+ name + " - "
							+ countryCode
						);
					}
				}
			} 
			
		} catch (Exception e) {
			
			System.err.println("ERROR: " + e.getMessage());
		}
	}
	
}
