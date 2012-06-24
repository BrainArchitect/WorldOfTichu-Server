package game;

public class Statistics {

	private String username;
	private int elo;
	private int ttl_games;
	private int ttl_wins;
	private int ttl_leaves;
	private int teamwork;
	private double speed;
	private int ttl_small;
	private int small_successful;
	private int ttl_large;
	private int large_successful;
	private int ttl_bombs;
	
	public Statistics (String username, int elo, int ttl_games, int ttl_wins, int ttl_leaves, int teamwork, double speed, int ttl_small, int small_successful, int ttl_large, int large_successful, int ttl_bombs){
		this.username = username;
		this.elo = elo;
		this.ttl_games = ttl_games;
		this.ttl_wins = ttl_wins;
		this.ttl_leaves = ttl_leaves;
		this.teamwork = teamwork;
		this.speed = speed;
		this.ttl_small = ttl_small;
		this.small_successful = small_successful;
		this.ttl_large = ttl_large;
		this.large_successful = large_successful;
		this.ttl_bombs = ttl_bombs;
	}
	
	public String getUsername() {
		return username;
	}
	public void setId(String username) {
		this.username = username;
	}
	public int getElo() {
		return elo;
	}
	public void setElo(int elo) {
		this.elo = elo;
	}
	public int getTtl_games() {
		return ttl_games;
	}
	public void setTtl_games(int ttl_games) {
		this.ttl_games = ttl_games;
	}
	public int getTtl_wins() {
		return ttl_wins;
	}
	public void setTtl_wins(int ttl_wins) {
		this.ttl_wins = ttl_wins;
	}
	public int getTtl_leaves() {
		return ttl_leaves;
	}
	public void setTtl_leaves(int ttl_leaves) {
		this.ttl_leaves = ttl_leaves;
	}
	public int getTeamwork() {
		return teamwork;
	}
	public void setTeamwork(int teamwork) {
		this.teamwork = teamwork;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public int getTtl_small() {
		return ttl_small;
	}
	public void setTtl_small(int ttl_small) {
		this.ttl_small = ttl_small;
	}
	public int getSmall_successful() {
		return small_successful;
	}
	public void setSmall_successful(int small_successful) {
		this.small_successful = small_successful;
	}
	public int getTtl_large() {
		return ttl_large;
	}
	public void setTtl_large(int ttl_large) {
		this.ttl_large = ttl_large;
	}
	public int getLarge_successful() {
		return large_successful;
	}
	public void setLarge_successful(int large_successful) {
		this.large_successful = large_successful;
	}
	public int getTtl_bombs() {
		return ttl_bombs;
	}
	public void setTtl_bombs(int ttl_bombs) {
		this.ttl_bombs = ttl_bombs;
	}
}
