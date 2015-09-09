package servlet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Game {
	@Id
	@GeneratedValue
	private long id;
	private String gameName;
	private String gamePicture;
	private String gamePath;
	public Game(){
		
	}
	public Game(String gameName, String gamePath, String gamePicture) {
		this.gameName = gameName;
		this.gamePicture = gamePicture;
		this.gamePath = gamePath;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public String getGamePicture() {
		return gamePicture;
	}
	public void setGamePicture(String gamePicture) {
		this.gamePicture = gamePicture;
	}
	public String getGamePath() {
		return gamePath;
	}
	public void setGamePath(String gamePath) {
		this.gamePath = gamePath;
	}
	
	
	
}
