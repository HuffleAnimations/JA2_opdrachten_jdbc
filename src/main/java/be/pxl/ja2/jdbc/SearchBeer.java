package be.pxl.ja2.jdbc;

import java.sql.*;

public class SearchBeer
{
	public static void main(String[] args)
	{
		String sql = "SELECT * FROM Beers WHERE Alcohol = ?";

		try (Connection connection = DriverManager.getConnection(
				"jdbc:mariadb://localhost:3307/studentdb?useSSL=false", "user",
				"password"); PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setFloat(1, 3.0F);
			statement.execute();
			ResultSet results = statement.getResultSet();
			while (results.next()) {
				System.out.printf("%s, %.2f, %.2f%n", results.getString("Name"),
						results.getFloat("Price"), results.getFloat("Alcohol"));
			}
		}
		catch (SQLException e)
		{
			System.out.println("Something went wrong: " + e.getMessage());
		}
	}
}
