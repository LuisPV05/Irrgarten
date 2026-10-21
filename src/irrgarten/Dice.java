
package irrgarten;
import java.util.Random;
import java.util.ArrayList;

public class Dice {
    
    private static final float PROB_DISCARD = (float) 0.2;
    private static final int MAX_USES = 5;
    private static final float MAX_INTELLIGENCE = (float) 10.0;
    private static final float MAX_STRENGTH = (float) 10.0;
    private static final float RESURRECT_PROB = (float) 0.3;
    private static final int WEAPONS_REWARD = 2;
    private static final int SHIELDS_REWARD = 3;
    private static final int HEALTH_REWARD = 5;
    private static final int MAX_ATTACK = 5;
    private static final int MAX_SHIELD = 5;
    
    private static Random generator = new Random();
    
    
    
    public static int randomPos (int max){
        return generator.nextInt(max);
    }
    
    public static int whoStarts (int nplayers){
        return generator.nextInt(nplayers);
    }
    
    public static float randomIntelligence (){
        return (float) (0.0 + (MAX_INTELLIGENCE - 0.0)*generator.nextFloat());
    }
    
    public static float randomStrength (){
        return (float) (0.0 + (MAX_STRENGTH - 0.0)*generator.nextFloat());
    }
    
    public static boolean resurrectPlayer(){
        return 0.0 + (1.0 - 0.0)*generator.nextFloat() < RESURRECT_PROB;
    }
    
    public static int weaponsReward(){
        return generator.nextInt(WEAPONS_REWARD + 1);
    }
    
    public static int shieldsReward(){
        return generator.nextInt(SHIELDS_REWARD + 1);
    }
    
    public static int healthReward(){
        return generator.nextInt(HEALTH_REWARD + 1);
    }
    
    public static float weaponPower(){
        return (float) (0.0 + (MAX_ATTACK - 0.0)*generator.nextFloat());
    }
    
    public static float shieldPower(){
        return (float) (0.0 + (MAX_SHIELD - 0.0)*generator.nextFloat());

    }
    
    public static int usesLeft(){
        return generator.nextInt(MAX_USES + 1);
    }
    
    public static float intensity(float competence){
        return (float) (0.0 + (competence - 0.0)*generator.nextFloat());
    }
    
    public static boolean discardElement(int usesLeft){
        return 0.0 + (1.0 - 0.0)*generator.nextFloat() > usesLeft*PROB_DISCARD;
    }
    
    public static Directions nextStep(Directions preference, ArrayList<Directions> validMoves, float intelligence){
        boolean direccionPreferida = (generator.nextFloat()*MAX_INTELLIGENCE < intelligence);
        if (direccionPreferida){
            return preference;
        }
        else{
            int movimiento = generator.nextInt(validMoves.size());
            return validMoves.get(movimiento);
        }
    }

}