package irrgarten;
import java.util.ArrayList;

public class FuzzyPlayer extends Player {
    public FuzzyPlayer(Player other){
        super(other);
    }
    
    @Override
    public Directions move(Directions direction, ArrayList<Directions> validMoves){
        Directions output;
        boolean contained = validMoves.contains(direction);
        if (contained && validMoves.size()>0){
            output = Dice.nextStep(direction, validMoves, getIntelligence());
        }
        else
        {
            validMoves.remove(direction);
            output = Dice.nextStep(validMoves.get(0), validMoves, getIntelligence());
        }
        return output;
    }
    
    @Override
    public float attack(){
        return  super.attack();
    }
    
    protected float deffensiveEnergy(){
        return super.defensiveEnergy();
    }
    
    @Override
    public String toString(){
        return "Fuzzy" + super.toString();
    }
}
