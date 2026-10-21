
package irrgarten;
import java.util.ArrayList;

public class MonsterTeam extends Team<Monster> {

    @Override
    public String toString(){
        ArrayList<Monster> monsters = getTeam();
        return monsters.toString();
    }
}
