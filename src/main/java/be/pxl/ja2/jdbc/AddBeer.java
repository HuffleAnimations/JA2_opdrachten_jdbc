package be.pxl.ja2.jdbc;

import java.sql.*;

public class AddBeer
{
	public static void main(String[] args)
	{
		//BrewerId 0, Name 1, CategoryId 2, Alcohol 3, Price 4, Stock 5
		String sql = "";
		int brewerId = Integer.parseInt(args[0]);
		String name = args[1];
		int categoryId = Integer.parseInt(args[2]);
		float alcohol = Float.parseFloat(args[3]);
		float price = Float.parseFloat(args[4]);
		int stock = Integer.parseInt(args[5]);

		try {
			sql = "INSERT INTO Beers (BrewerId, Name, CategoryId, Alcohol, Price, Stock) " +
					"VALUES ('"+brewerId+"', '"+name+"', '"+categoryId+"', '"+alcohol+"', '"+price+"', '"+stock+"')";
//			sql = String.format("INSERT INTO Beers (BrewerId, Name, CategoryId, Alcohol, Price, Stock) " +
//							"VALUES (%d, %s, %d, %.2f, %.2f, %d)", Integer.parseInt(args[0]), args[1],
//					Integer.parseInt(args[2]), Float.parseFloat(args[3]), Float.parseFloat(args[4]),
//					Integer.parseInt(args[5]));
//			sql = "INSERT INTO Beers (BrewerId, Name, CategoryId, Alcohol, Price, Stock) " +
//							"VALUES (6, 'My Beer', 12, 5.0, 2.3, 50)";
		} catch (Exception e) {
			System.out.println("Something went wrong: " + e.getMessage());
		}

		try (Connection connection = DriverManager.getConnection(
				"jdbc:mariadb://localhost:3307/studentdb?useSSL=false", "user",
				"password"); Statement statement = connection.createStatement()) {
			statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			try (ResultSet rs = statement.getGeneratedKeys()) {
				if (rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Primary key: " + id);
				}
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

	}
}
