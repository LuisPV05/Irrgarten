package irrgarten;

public class MonsterTrainer extends Player {
    private MonsterTeam monsterTeam;
    
    public MonsterTrainer(Player other){
        super(other);
        monsterTeam = new MonsterTeam();
        this.configMonsterTeam();
    }
    
    public void configMonsterTeam(){
        for (int i=1; i<=6; i++){
            Monster monstruo1 = new Monster("monstruo " +i , Dice.randomIntelligence(), Dice.randomStrength());
            monsterTeam.add(monstruo1);
        }
    }
    
    public String getMonsterTeam(){
        return monsterTeam.toString();
    }
}
