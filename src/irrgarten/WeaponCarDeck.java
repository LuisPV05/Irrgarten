package irrgarten;


public class WeaponCarDeck extends CarDeck<Weapon>{
    
    @Override
    protected void addCards(){
        final int N = 15;
        for (int i=0; i<N; i++){
            Weapon arma = new Weapon(Dice.shieldPower(), Dice.usesLeft());
            addCard(arma);
        }
        
    }
}