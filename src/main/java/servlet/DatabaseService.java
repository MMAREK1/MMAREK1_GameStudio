package servlet;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
@Stateless
public class DatabaseService {
	public DatabaseService() {
	}

	@Inject
	private EntityManager em;

	public List<Game> selectGames() {
		List<Game> games = em.createQuery("SELECT g FROM Game g", Game.class).getResultList();
		return games;
	}
	
	public List<User> loadUsers(){
		List<User> users = em.createQuery("SELECT u FROM User u", User.class).getResultList();
		return users;
	}
}
