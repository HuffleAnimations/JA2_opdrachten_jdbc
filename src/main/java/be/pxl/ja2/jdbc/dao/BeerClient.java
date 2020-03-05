package be.pxl.ja2.jdbc.dao;

import java.util.List;

public class BeerClient {
	public static void main(String[] args) throws BeerException
	{
		BeerDao beerDao = new BeerDao("jdbc:mariadb://localhost:3307/studentdb?useSSL=false", "user",
				"password");

		Beer beer = beerDao.getBeerById(10);
		System.out.println(beer.toString());

		beer.setStock(beer.getStock() - 10);
		beerDao.updateBeer(beer);

		beer = beerDao.getBeerById(10);
		System.out.println(beer.toString());

		List<Beer> alcoholList = beerDao.getBeersByAlcohol(6.0F);
		for(Beer beerAlcohol : alcoholList) {
			System.out.println(beerAlcohol.toString());
		}

		List<Beer> nameList = beerDao.getBeersByName("Derby bruin (=Piedboeuf dubbel - foncee)");
		for (Beer beerName : nameList) {
			System.out.println(beerName.toString());
		}
	}
}
// Written By A Cat:
//                      54BE
//44444444444444444444444444444444444444444444444444444444444errr