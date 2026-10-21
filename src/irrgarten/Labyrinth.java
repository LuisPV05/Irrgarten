package irrgarten;

import java.util.ArrayList;

public class Labyrinth{
    private static final char BLOCK_CHAR = 'X';
    private static final char EMPTY_CHAR = '-';
    private static final char MONSTER_CHAR = 'M';
    private static final char COMBAT_CHAR = 'C';
    private static final char EXIT_CHAR = 'E';
    private static final int ROW = 0;
    private static final int COL = 1;
    private int nRows;
    private int nCols;
    private int exitRow;
    private int exitCol;
    //private Monster[][] MonsterSquare;
    private Monster[][] monsters;
    //private Player[][] PlayerSquare;
    private MonsterTrainer[][] players;
    //private char[][] LabyrinthSquare;
    private char[][] labyrinth;


    
    
    public Labyrinth(int nRows, int nCols, int exitRow, int exitCol){
        this.nRows = nRows;
        this.nCols = nCols;
        this.exitCol = exitCol;
        this.exitRow = exitRow;
        //MonsterSquare = new Monster[filas][columnas];
        //PlayerSquare = new Player[filas][columnas];
        //LabyrinthSquare = new char[filas][columnas];
        labyrinth = new char[nRows][nCols];
        monsters = new Monster[nRows][nCols];
        players = new MonsterTrainer[nRows][nCols];
        
        for (int i=0; i<nRows; i++)
            for (int j=0; j<nCols; j++)
                labyrinth[i][j] = EMPTY_CHAR;
        
        labyrinth[exitRow][exitCol] = EXIT_CHAR;
        

    }
    
    public void spreadPlayers(ArrayList<MonsterTrainer> players){
        for (int i=0; i<players.size(); i++){
            MonsterTrainer p = players.get(i);
            int[] pos = this.randomEmptyPos();
            this.putPlayer2D(-1, -1, pos[ROW], pos[COL], p);
        }
    }
    
    public boolean haveAWinner(){
        boolean ganador = false;
            if (players[exitRow][exitCol] != null)
                ganador = true;
                
        return ganador;
    }
    
    @Override
    public String toString(){
        //return "Numero de filas: " + nRows + ", numero de columnas: " + nCols +
        //       ", fila de salida: " + exitRow + ", columa de salida: " + exitCol + "\n";
        String lab = "";
        for (int i=0; i<nRows; i++){
            for (int j=0; j<nCols; j++){
                lab += labyrinth[i][j];
                lab += " ";
            }
            lab += "\n";
        }
        return lab;
    }
    
    public void addMonster(int row, int col, Monster monster){
        if ((row>=0 && row<nRows) && (col>=0 && col<nCols)){
            if (labyrinth[row][col] == EMPTY_CHAR){
                labyrinth[row][col] = MONSTER_CHAR;
                monsters[row][col] = monster;
                monster.setPos(row, col);
            }
        }
    }
    
    public Monster putPlayer(Directions direction, MonsterTrainer player){
        int oldRow = player.getRow();
        int oldCol = player.getCol();
        int [] newPos = this.dir2Pos(oldRow, oldCol, direction);
        Monster monster = this.putPlayer2D(oldRow, oldCol, newPos[ROW], newPos[COL], player);
        
        return monster;
    }
    
    public void addBlock(Orientation orientation, int startRow, int startCol, int length){
        int incRow;
        int incCol;
        if (orientation == Orientation.VERTICAL){
            incRow = 1;
            incCol = 0;
        }
        else{
            incRow = 0;
            incCol = 1;
        }
        
        int row = startRow;
        int col = startCol;
        while (this.posOK(row, col) && this.emptyPos(row, col) && length > 0){
            labyrinth[row][col] = BLOCK_CHAR;
            length--;
            row += incRow;
            col += incCol;
        }
    }
    
    public ArrayList<Directions> validMoves(int row, int col){
        ArrayList<Directions> salida = new ArrayList<>();
        
        if (this.canStepOn(row+1, col))
            salida.add(Directions.DOWN);
        if (this.canStepOn(row-1, col))
            salida.add(Directions.UP);
        if (this.canStepOn(row, col+1))
            salida.add(Directions.RIGHT);
        if (this.canStepOn(row, col-1))
            salida.add(Directions.LEFT);
        
        return salida;
    }
    
    private boolean posOK(int row, int col){
        return ((row>=0 && row<nRows) && (col>=0 && col<nCols));
    }
    
    private boolean emptyPos(int row, int col){
        return posOK(row,col) && labyrinth[row][col] == EMPTY_CHAR;
    }
    
    private boolean monsterPos(int row, int col){
        return posOK(row,col) && monsters[row][col] != null;
    }
    
    private boolean exitPos(int row, int col){
        return posOK(row,col) && labyrinth[row][col] == EXIT_CHAR;
    }
    
    private boolean combatPos(int row, int col){
        return posOK(row,col) && labyrinth[row][col] == COMBAT_CHAR;
    }
    
    private boolean canStepOn(int row, int col){
        return posOK(row, col) && (emptyPos(row, col) || monsterPos(row, col) || exitPos(row, col)); 
    }
    
    private void updateOldPos(int row, int col){
        if (posOK(row, col)){
            if (combatPos(row, col)){
                labyrinth[row][col] = MONSTER_CHAR;
            }
            else{
                labyrinth[row][col] = EMPTY_CHAR;
            } 
        }
    }
    
    private int[] dir2Pos(int row, int col, Directions direction){
        int fila = row;
        int columna = col;
        
        if (direction == Directions.UP)
            fila -= 1;
        if (direction == Directions.DOWN)
            fila += 1;
        if (direction == Directions.LEFT)
            columna -= 1;
        if (direction == Directions.RIGHT)
            columna += 1;
        
        
        
        return new int[] {fila, columna};
    }
    
    public void cambiaPlayer(MonsterTrainer p){
        updateOldPos(p.getRow(),p.getCol());
        putPlayer2D(p.getRow(),p.getCol(), p.getRow(), p.getCol(), p);
    }
    
    private int[] randomEmptyPos() {
        int fila;
        int col;
        do{
            fila = Dice.randomPos(nRows);
            col = Dice.randomPos(nCols);
            
        }while(!emptyPos(fila, col));
        
        return new int[] {fila, col};
    }
    
    private Monster putPlayer2D(int oldRow, int oldCol, int row, int col, MonsterTrainer player){
        Monster output = null;
        if (this.canStepOn(row, col)){
            if (this.posOK(oldRow, oldCol)){
                MonsterTrainer p = players[oldRow][oldCol];
                if (p == player){
                    this.updateOldPos(oldRow, oldCol);
                    players[oldRow][oldCol] = null;
                }
            }
            boolean monsterPos = this.monsterPos(row, col);
            
            if (monsterPos){
                labyrinth[row][col] = COMBAT_CHAR;
                output = monsters[row][col];
            }
            else{
                char number = player.getNumber();
                labyrinth[row][col] = number;
            }
            players[row][col] = player;
            player.setPos(row, col);
        }
        
        return output;
    }
            
}
