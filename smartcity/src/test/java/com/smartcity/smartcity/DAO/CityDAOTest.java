package com.smartcity.smartcity.DAO;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.smartcity.smartcity.SmartcityApplicationTests;
import com.smartcity.smartcity.model.City;
import com.smartcity.smartcity.model.User;
import com.smartcity.smartcity.model.enums.Access;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SmartcityApplicationTests.class)
@Transactional
public class CityDAOTest {

	@Autowired
	UserDAO userDao;

	@Autowired
	CityDAO cityDao;

	User user1;

	City city1;

	@Before
	public void createCity() {

		user1 = new User("1LogUsr", "pass123", "David", "John", "ddd@yahoo.fr", Access.ADMIN);
		userDao.save(user1);

		city1 = new City("Paris", "PAR", 6, 6, 110.0F, 1000.0F, "La captiale de France", 6, 4, user1);
		cityDao.save(city1);

	}

	@Test
	public void findByCityId() {
		City villeFoundById = cityDao.findOne(city1.getCityId());
		assertThat(villeFoundById).isEqualTo(city1);
	}

	@Test
	public void findByVilleName() {
		City villeFoundByName = cityDao.getCityByCityName(city1.getCityName());
		assertThat(villeFoundByName).isEqualTo(city1);
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void CityNameUniqueDataIntegrityViolation() {
		City city2 = new City("Paris", "MET", 6, 6, 110.0F, 1000.0F, "La captiale de France", 6, 4, user1);
		cityDao.save(city2);
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void CityAbrevDataIntegrityViolation() {
		City city3 = new City("MET", "PAR", 6, 6, 110.0F, 1000.0F, "La captiale de France", 6, 4, user1);
		cityDao.save(city3);
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void CityDescriptionDataIntegrityViolation() {
		City city4 = new City("MET", "PAR", 6, 6, 110.0F, 1000.0F,
				"est la capitale de la France. Elle se situe au cœur d'un vaste bassin sédimentaire aux sols fertiles et au climat tempéré, le bassin parisien, sur une boucle de la Seine, entre les confluents de celle-ci avec la Marne et l'Oise. Ses habitants s’appellent les Parisiens. Paris est également le chef-lieu de la région Île-de-France et l'unique commune française qui est en même temps un département. Commune centrale de la métropole du Grand Paris, créée en 2016, elle est divisée en arrondissements, comme les villes de Lyon et de Marseille, au nombre de vingt. L’État y dispose de prérogatives particulières exercées par le préfet de police de Paris. La ville a connu de profondes transformations dans les décennies 1850 à 1860 à travers d'importants travaux consistant notamment au percement de larges avenues, places et jardins, dirigés par le Baron Haussmann",
				6, 4, user1);
		cityDao.save(city4);
	}

}
