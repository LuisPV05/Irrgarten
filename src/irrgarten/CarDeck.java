package irrgarten;
import java.util.ArrayList;
import java.util.Collections;

public abstract class CarDeck <T> {
    private ArrayList<T> carDeck;
    
    public CarDeck(){
        carDeck = new ArrayList<>();
    }
    protected abstract void addCards();
    
    protected void addCard(T card){
        carDeck.add(card);
    }
    
    public T nextCard(){
        if (carDeck.isEmpty()){
            addCards();
            Collections.shuffle(carDeck);
        }
        
        T card = carDeck.get(0);
        carDeck.remove(0);
        return card;
    }
}
