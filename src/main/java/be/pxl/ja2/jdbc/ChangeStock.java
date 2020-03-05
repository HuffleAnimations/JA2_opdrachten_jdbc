package be.pxl.ja2.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ChangeStock
{
	public static void main(String[] args)
	{
		String sql = "UPDATE Beers SET Stock = 50 WHERE Name LIKE '%kriek%'";
		try (Connection connection = DriverManager.getConnection(
				"jdbc:mariadb://localhost:3307/studentdb?useSSL=false", "user",
				"password"); Statement statement = connection.createStatement()) {
			int result = statement.executeUpdate(sql);
			System.out.println("Updated lines: " + result);
		}
		catch (SQLException e)
		{
			System.out.println("Something went wrong: " + e.getMessage());
		}
	}
}
