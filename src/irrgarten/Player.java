package irrgarten;

import java.util.ArrayList;

public class Player extends LabyrinthCharacter{
    private static final int MAX_WEAPONS = 2;
    private static final int MAX_SHIELDS = 3;
    private static final int INITIAL_HEALTH = 10;
    private static final int HITS2LOSE = 3;
    private char number;
    private int consecutiveHits = 0;
    private ArrayList<Shield> shields;
    private ArrayList<Weapon> weapons;
    private ShieldCarDeck shieldCarDeck;
    private WeaponCarDeck weaponCarDeck;
    
    
    public Player(char number, float intelligence, float strength){
        super("Player# " + number, intelligence, strength, INITIAL_HEALTH);
        this.number = number;
        shields = new ArrayList<>();
        weapons = new ArrayList<>();
        shieldCarDeck = new ShieldCarDeck();
        shieldCarDeck.addCards();
        weaponCarDeck = new WeaponCarDeck();
        weaponCarDeck.addCards();
    }
    
    public Player(Player other){
        super(other);
        number = other.number;
        shields = other.shields;
        weapons = other.weapons;
        shieldCarDeck = other.shieldCarDeck;
        weaponCarDeck = other.weaponCarDeck;
    }
    
    public void resurrect(){
        setHealth(INITIAL_HEALTH);
        shields = new ArrayList<>();       
        weapons = new ArrayList<>();       
        consecutiveHits = 0;
    }
        
    public char getNumber(){
        return number;
    }
    
    public Directions move(Directions direction, ArrayList<Directions> validMoves){
        int size = validMoves.size();
        boolean contained = validMoves.contains(direction);
        if ((size>0) && (!contained)){
            Directions firstElement = validMoves.get(0);
            return firstElement;
        }
        else
            return direction;
    }
    
    @Override
    public float attack(){        
        return getStrength() + sumWeapons();
    }
    
    @Override
    public boolean defend(float receivedAttack){
        return manageHit(receivedAttack);
    }
    
    public void receiveReward(){
        
        int wReward = Dice.weaponsReward();
        int sReward = Dice.shieldsReward();
        
        for (int i = 1; i<=wReward; i++){
            Weapon wnew = weaponCarDeck.nextCard();
            this.receiveWeapon(wnew);
        }
        
        for (int j = 1; j<=sReward; j++){
            Shield snew = shieldCarDeck.nextCard();
            this.receiveShield(snew);
        }
        
        int extraHealth = Dice.healthReward();
        setHealth(getHealth()+extraHealth);
        
    }
    
    @Override
    public String toString(){
        return (super.toString() + "\n" + weapons.toString() + "\n" + shields.toString() + "\n");
    }
    
    private void receiveWeapon(Weapon w){
        for (int i=weapons.size()-1; i>=0; i--){
            boolean discard = weapons.get(i).discard();
            if (discard)
                weapons.remove(weapons.get(i));
        }
        
        int size = weapons.size();
        if (size<MAX_WEAPONS)
            weapons.add(w);
    }
    
    private void receiveShield(Shield s){
        for (int i=shields.size()-1; i>=0; i--){
            boolean discard = shields.get(i).discard();
            if (discard){
                shields.remove(shields.get(i));
            }
            
            int size = shields.size();
            if (size<MAX_SHIELDS){
                shields.add(s);
            }
        }
    }
    
    /*
    private Weapon newWeapon(){
        return new Weapon(Dice.weaponPower(), Dice.usesLeft());
    }
    
    private Shield newShield(){
        return new Shield(Dice.shieldPower(), Dice.usesLeft());
    }
    */
    
    protected float sumWeapons(){
        float suma = (float) 0.0;
        for (int i=0; i<weapons.size(); i++){
            suma += weapons.get(i).attack();
        }
        
        return suma;
    }
    
    protected float sumShields(){
        float suma = (float) 0.0;
        for (int i=0; i<shields.size(); i++){
            suma += shields.get(i).protect();
        }
        return suma;
    }
    
    protected float defensiveEnergy(){
        return getIntelligence() + sumShields();
    }
    
    private boolean manageHit(float receivedAttack){
        float defense = this.defensiveEnergy();
        boolean lose;
        if (defense < receivedAttack){
            this.gotWounded();
            this.incConsecutiveHits();
        }
        else 
            this.resetHits();
        
        if ((consecutiveHits == HITS2LOSE) || (this.dead())){
            this.resetHits();
            lose = true;
        }
        else
            lose = false;
        
        return lose;
    }
    
    private void resetHits(){
        consecutiveHits = 0;
    }
    
    private void incConsecutiveHits(){
        consecutiveHits += 1;
    }

}