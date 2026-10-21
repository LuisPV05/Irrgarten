
package irrgarten;

public class GameState {
    private String labyrinth;
    private String players;
    private String monster;
    private int currentPlayer;
    private boolean winner;
    private String log;
    private String monsterTeam;
    
    public GameState(String labyrinth, String players, String monster, int currentPlayer, boolean winner, String log, String monsterTeam){
        this.labyrinth = labyrinth;
        this.players = players;
        this.monster = monster;
        this.currentPlayer = currentPlayer;
        this.winner = winner;
        this.log = log;
        this.monsterTeam = monsterTeam;
    }
    
    public String getLabyrinth(){
        return labyrinth;
    }
  
    public String getPlayers(){
        return players;
    }
    
    public String getMonster(){
        return monster;
    }
    
    public int getCurrentPlayer(){
        return currentPlayer;
    }
    
    public boolean getWinner(){
        return winner;
    }
    
    public String getLog(){
        return log;
    }
    
    public String getMonsterTeam(){
        return monsterTeam;
    }
}
