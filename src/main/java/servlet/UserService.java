package servlet;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class UserService {
	@Inject
    private EntityManager em;
	@Inject
	public DatabaseService databaseService;
	public UserService() {
	}
	public void registerUser(User user){
		List<User> users = databaseService.loadUsers();
		for(User u:users){
			if(user.getName()!=u.getName()){
				em.persist(user);
			}
		}
	}
	public void loginUser(User user){
		
	}
	
}
