package irrgarten;
import java.util.ArrayList;

public abstract class Team <T> {
    private final int MAX_CAPACITY = 6;
    private ArrayList<T> team;
    
    public Team(){
        team = new ArrayList<>();
    }
    
    void add(T member){
        if (team.size()<MAX_CAPACITY){
            team.add(member);
        }
    }
    
    public ArrayList<T> getTeam(){
        return team;
    }
}
