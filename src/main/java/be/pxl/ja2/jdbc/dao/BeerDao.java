package be.pxl.ja2.jdbc.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BeerDao {
	private String url;
	private String user;
	private String password;

	public BeerDao() {}

	public BeerDao(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getUser()
	{
		return user;
	}

	public void setUser(String user)
	{
		this.user = user;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public Beer getBeerById(int id) throws BeerException {
		try (Connection connection = getConnection(); PreparedStatement statement = connection
				.prepareStatement("SELECT * FROM Beers WHERE Id = ?")) {
			statement.setInt(1, id);
			try (ResultSet set = statement.executeQuery()) {
				if (set.next()) {
					Beer beer = new Beer();
					beer.setId(id);
					beer.setName(set.getString("Name"));
					beer.setPrice(set.getFloat("Price"));
					beer.setAlcohol(set.getFloat("Alcohol"));
					beer.setStock(set.getInt("Stock"));
					return beer;
				} else {
					return null;
				}
			}
		} catch (SQLException e)
		{
			throw new BeerException(e.getMessage());
		}
	}

	public void updateBeer(Beer beer) throws BeerException {
		try (Connection connection = getConnection(); PreparedStatement statement = connection
				.prepareStatement("UPDATE Beers SET Name = ?, Price = ?, Alcohol = ?, Stock = ? WHERE Id = ?")) {
			statement.setString(1, beer.getName());
			statement.setFloat(2, beer.getPrice());
			statement.setFloat(3, beer.getAlcohol());
			statement.setInt(4, beer.getStock());
			statement.setInt(5, beer.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new BeerException(e.getMessage());
		}
	}

	public List<Beer> getBeersByAlcohol(float alcohol) throws BeerException {
		try (Connection connection = getConnection(); PreparedStatement statement = connection
				.prepareStatement("SELECT * FROM Beers WHERE Alcohol = ?")) {
			statement.setFloat(1, alcohol);
			statement.execute();
			try (ResultSet set = statement.getResultSet()) {
				List<Beer> beers = new ArrayList<Beer>();
				while (set.next()) {
					Beer beer = new Beer();
					beer.setId(set.getInt("Id"));
					beer.setName(set.getString("Name"));
					beer.setPrice(set.getFloat("Price"));
					beer.setAlcohol(alcohol);
					beer.setStock(set.getInt("Stock"));
					beers.add(beer);
				}
				return beers;
			}
		} catch (SQLException e) {
			throw new BeerException(e.getMessage());
		}
	}

	public List<Beer> getBeersByName(String name) throws BeerException {
		try (Connection connection = getConnection(); PreparedStatement statement = connection
				.prepareStatement("SELECT * FROM Beers WHERE Name = ?")) {
			statement.setString(1, name);
			statement.execute();
			try (ResultSet set = statement.getResultSet()) {
				List<Beer> beers = new ArrayList<Beer>();
				while (set.next()) {
					Beer beer = new Beer();
					beer.setId(set.getInt("Id"));
					beer.setName(name);
					beer.setPrice(set.getFloat("Price"));
					beer.setAlcohol(set.getFloat("Alcohol"));
					beer.setStock(set.getInt("Stock"));
					beers.add(beer);
				}
				return beers;
			}
		} catch (SQLException e) {
			throw new BeerException(e.getMessage());
		}
	}

	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
}
