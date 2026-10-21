package irrgarten;


public class ShieldCarDeck extends CarDeck<Shield>{
    
    @Override
    protected void addCards(){
        final int N = 15;
        for (int i=0; i<N; i++){
            Shield escudo = new Shield(Dice.shieldPower(), Dice.usesLeft());
            addCard(escudo);
        }  
    }
}
