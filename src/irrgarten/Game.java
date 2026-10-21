package irrgarten;

import java.util.ArrayList;

public class Game{
    private static final int MAX_ROUNDS = 10;
    private int currentPlayerIndex;
    private String log;
    private Player currentPlayer;
    private ArrayList<MonsterTrainer> players; 
    private ArrayList<Monster> monsters;
    private Labyrinth labyrinth;

    
    
    public Game(int nPlayers){
        log = null;
        currentPlayerIndex = Dice.whoStarts(nPlayers);
        monsters = new ArrayList<>();
        players = new ArrayList<>();
        for (int i=0; i<nPlayers; i++){
            Player jugador = new Player((char) (i+'0'), Dice.randomIntelligence(), Dice.randomStrength());
            players.add(i,new MonsterTrainer(jugador) );
        }
        currentPlayer = players.get(currentPlayerIndex);
        
        labyrinth = new Labyrinth(10,10, 2, 3);
        configureLabyrinth();
        labyrinth.spreadPlayers(players);

    }
    
    public boolean finished(){
        return labyrinth.haveAWinner();
    }
    
    public boolean nextStep(Directions preferredDirection){
        log = "";
        boolean dead = currentPlayer.dead();
        if (!dead){
            Directions direction = this.actualDirection(preferredDirection);
            
            if (direction != preferredDirection)
                this.logPlayerNoOrders();
            
            Monster monster = labyrinth.putPlayer(direction, (MonsterTrainer) currentPlayer);
            
            if (monster == null)
                this.logNoMonster();
            else{
                GameCharacter winner = this.combat(monster);
                this.manageReward(winner);
            }
        }
        else{
            this.manageResurrection();
        }
        
        boolean endGame = this.finished();
        if (!endGame)
            this.nextPlayer();
        
        return endGame;
    }
    
    public GameState getGameState(){
        String monsterteam = "";
        for (int i=0; i<players.size(); i++){
            monsterteam += players.get(i).getMonsterTeam();
        }
        return new GameState(labyrinth.toString(), players.toString(), monsters.toString(), currentPlayerIndex, labyrinth.haveAWinner(), log, monsterteam);
    }
    
    private void configureLabyrinth(){        
        labyrinth.addBlock(Orientation.VERTICAL, 3, 5, 5);
        labyrinth.addBlock(Orientation.VERTICAL, 1, 2, 7);
        labyrinth.addBlock(Orientation.HORIZONTAL, 5, 3, 1);
        //int newpos[] = labyrinth.randomEmptyPos();
        int newpos1[] = {4,4};
        Monster monstruo1 = new Monster ("monstruo1", Dice.randomIntelligence(), Dice.randomStrength());
        monsters.add(monstruo1);
        labyrinth.addMonster(newpos1[0], newpos1[1], monsters.get(0));
        int newpos2[] = {1,0};
        Monster monstruo2 = new Monster ("monstruo2", Dice.randomIntelligence(), Dice.randomStrength());
        monsters.add(monstruo2);
        labyrinth.addMonster(newpos2[0], newpos2[1], monsters.get(1));

    }
    
    private void nextPlayer(){
        if (currentPlayerIndex == players.size()-1){
            currentPlayerIndex = 0;
        }
        else{
            currentPlayerIndex += 1;
        }
        
        currentPlayer = players.get(currentPlayerIndex);
        
    }
    
    private Directions actualDirection(Directions preferredDirection){
        int currentRow = currentPlayer.getRow();
        int currentCol = currentPlayer.getCol();
        ArrayList<Directions> validMoves = labyrinth.validMoves(currentRow, currentCol);
        
        Directions output = currentPlayer.move(preferredDirection, validMoves);
        
        return output;
    }
    
    private GameCharacter combat(Monster monster){
        float playerAttack = currentPlayer.attack();
        int rounds = 0;
        GameCharacter winner = GameCharacter.PLAYER;
        boolean lose = monster.defend(playerAttack);
        
        while(!lose && rounds<MAX_ROUNDS){
            winner = GameCharacter.MONSTER;
            rounds++;
            float monsterAttack = monster.attack();
            lose = currentPlayer.defend(monsterAttack);
            if (!lose){
                playerAttack = currentPlayer.attack();
                winner = GameCharacter.PLAYER;
                lose = monster.defend(playerAttack);
            }
            
        }
        this.logRounds(rounds, MAX_ROUNDS);
        
        return winner;
    }
    
    private void manageReward(GameCharacter winner){
        if (winner == GameCharacter.PLAYER){
            currentPlayer.receiveReward();
            this.logPlayerWon();
        }
        else{
            this.logMonsterWon();
        }
    }
    
    private void manageResurrection(){
        boolean resurrect = Dice.resurrectPlayer();
        if (resurrect){
            
            currentPlayer.resurrect();
            FuzzyPlayer p = new FuzzyPlayer(currentPlayer);
            currentPlayer = p;
            this.logResurrected();
        }
        else{
            this.logPlayerSkipTurn();
        }
    }
    
    private void logPlayerWon(){
        log = log + "El jugador ha ganado el combate\n";
    }
    
    private void logMonsterWon(){
        log = log + "El monstruo ha ganado la partida\n";
    }
    
    private void logResurrected(){
        log = log + "El jugador ha resucitado\n";
    }
    
    private void logPlayerSkipTurn(){
        log = log + "El jugador ha perdido el turno por estar muerto\n";
    }
    
    private void logPlayerNoOrders(){
        log = log + "El jugador no ha seguido las instrucciones del humano, ya que no es posible\n";
    }
    
    private void logNoMonster(){
        log = log + "El jugador se ha movido a una celda vacia o no se ha podido mover\n";
    }
    
    private void logRounds(int rounds, int max){
        log = log + "Se han producido un total de " + rounds + " de un maximo de " + max + " rondas\n";
    }
}

