package be.pxl.ja2.jdbc;

import java.sql.*;

public class SearchBeers {

	public static void main(String[] args) {
		try (Connection connection = DriverManager.getConnection(
				"jdbc:mariadb://localhost:3307/studentdb?useSSL=false", "user",
				"password"); Statement statement = connection.createStatement()) {
			statement.execute("SELECT * FROM Beers b INNER JOIN Categories c ON b.CategoryId = c.Id " +
					"INNER JOIN Brewers br ON b.BrewerId = br.Id " +
					"WHERE Alcohol = 2.0 ORDER BY Alcohol");
			ResultSet results = statement.getResultSet();
			while (results.next()) {
				System.out.printf("%s, %s, %s, %.2f, %.2f%n", results.getString("Brewers.Name"),
						results.getString("Beers.Name"), results.getString("Category"),
						results.getFloat("Price"), results.getFloat("Alcohol"));
			}
		}
		catch (SQLException e)
		{
			System.out.println("Something went wrong: " + e.getMessage());
		}
	}
}
