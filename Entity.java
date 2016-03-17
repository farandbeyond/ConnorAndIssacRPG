/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonalRPGDev03;

/**
 * Entity represents a unit that can be found within a battle. This can be an enemy, non-player character, or a player character.
 * It will contain stats representing its strength, equipment equipped by the player, a name, and functions controlling these.
 * @author Connor
 */

public class Entity {
    private Stat
            Strength,
            Dexterity,
            Constitution,
            Intelligence,
            Wisdom,
            Charisma,
            HP,
            HPMax,
            MP,
            MPMax;
    
    private final String
            name;
    
    private boolean 
            isDead;
    private int     
            element,
            exp,
            level,
            guildSlot;
    Weapon  weapon;
    /*
    Equipment   weapon,
                armor1,
                armor2,
                armor3;
    */
    /**
     * Constructor.
     * @param allStats 0=hp, 1=mp, 2=str, 3=dex, 4=con, 5=int, 6=wis, 7=cha
     * @param name 
     */
    Entity(int[] allStats, String name){
        setAllStats(allStats);
        this.name=name;
        isDead=false;
        element=Element.NEUTRAL;
        exp=0;
        level=1;
    }
    Entity(int[] allStats, String name, boolean isDead, int element, int exp, Weapon weapon, int level){
        setAllStats(allStats);
        this.name=name;
        this.isDead=isDead;
        this.element=element;
        this.exp=exp;
        this.weapon=weapon;
        this.level=level;
    }
    //all functions that edit a stat or variable
    private void setAllStats(int[] allStats){
       Strength     =new Stat(allStats[4]);
       Dexterity    =new Stat(allStats[5]);
       Constitution =new Stat(allStats[6]);
       Intelligence =new Stat(allStats[7]);
       Wisdom       =new Stat(allStats[8]);
       Charisma     =new Stat(allStats[9]);
       HP           =new Stat(allStats[0]);
       HPMax        =new Stat(allStats[1]);
       MP           =new Stat(allStats[2]);
       MPMax        =new Stat(allStats[3]);
    }
    public void damage(int damageTaken){
        this.HP.reduce(damageTaken);
        if(HP.get()<=0){
            isDead=true;
            HP.increase(0-HP.get());
        }
    }
    public void heal(int healValue){
        if(!isDead){
            this.HP.increase(healValue);
            if(HP.get()>HPMax.get()){
                HP.reduce(HP.get()-HPMax.get());
            }
        }else{
            System.out.println("!!target is dead, and cannot be healed!!");
        }
    }
    public void mpHeal(int healValue){
        if(!isDead){
            this.MP.increase(healValue);
            if(MP.get()>MPMax.get()){
                MP.reduce(MP.get()-MPMax.get());
            }
        }else{
            System.out.println("!!target is dead, and cannot be healed!!");
        }
    }
    public void raise(int healValue){
        if(isDead){
            this.HP.increase(healValue);
            isDead=false;
        }
        else{
            System.out.println("Target is not dead");
        }
    }
    public boolean canCastSpell(int manaUsed){
        return manaUsed<=MP.get();
    }
    public void spellCast(int manaUsed){
        if(canCastSpell(manaUsed)){
            MP.reduce(manaUsed);
            //System.out.println("Fwoosh! Fireball!!");
        }else{
            System.out.println("Not Enough Mana");
        } 
    }
    public void longRest(){
        isDead=false;
        heal(HPMax.get());
        mpHeal(MPMax.get());
    }
    public int getCastingStat(String schoolOfMagic){
        if(schoolOfMagic.equalsIgnoreCase("Studious"))
            return Intelligence.get();
        else if(schoolOfMagic.equalsIgnoreCase("Natural"))
            return Wisdom.get();
        else if(schoolOfMagic.equalsIgnoreCase("Untamed"))
            return Charisma.get();
        else
            return 0;
    }
    public void gainExp(int xpGained){
        this.exp+=xpGained;
        levelup();
    }
    public void levelup(){
        if(exp>=level*50){
            level+=1;
            System.out.printf("Level Up! Reached Level %d\n", level);
            increaseAllStats();
        }
    }
    public void increaseAllStats(){
        Strength.increase(1);
        Dexterity.increase(1);
        Constitution.increase(1);
        Intelligence.increase(1);
        Wisdom.increase(1);
        Charisma.increase(1);
        HP.increase(5);
        HPMax.increase(5);
        MP.increase(5);
        MPMax.increase(5);
    }
    public void increaseAllStats(int hp, int mp, int str, int dex, int con, int ine, int wis, int cha){
        Strength.increase(str);
        Dexterity.increase(dex);
        Constitution.increase(con);
        Intelligence.increase(ine);
        Wisdom.increase(wis);
        Charisma.increase(cha);
        HP.increase(hp);
        HPMax.increase(hp);
        MP.increase(mp);
        MPMax.increase(mp);
    }
    public void getAllStats(){
        System.out.printf("Str: %d Dex: %d Con: %d Int: %d Wis: %d Cha: %d\n",getStr(),getDex(),getCon(),getInt(),getWis(),getCha());
    }
    public void assignGuildSlot(int guildSlot){
        this.guildSlot=guildSlot;
    }
    
    //get functions
    public boolean isDead(){
        return isDead;
    }
    public int getStr(){
        return Strength.get();
    }
    public int getDex(){
        return Dexterity.get();
    }
    public int getCon(){
        return Constitution.get();
    }
    public int getInt(){
        return Intelligence.get();
    }
    public int getWis(){
        return Wisdom.get();
    }
    public int getCha(){
        return Charisma.get();
    }
    public int getHp(){
        return HP.get();
    }
    public int getMaxHp(){
        return HPMax.get();
    }
    public int getMp(){
        return MP.get();
    }
    public int getMaxMp(){
        return MPMax.get();
    }  
    public String getName(){
        return name;
    }
    public void getStatLine(){
        System.out.println(this);
        System.out.printf("%s/%s - %s/%s\n",getHp(),getMaxHp(),getMp(),getMaxMp());
        System.out.printf("Character %s dead\n",!isDead()?"is not":"is");
    }
    public int getGuildSlot(){
        return guildSlot;
    }
    //toString
    public String toString(){
        return name;
    }
    
    //nested class stat
    private class Stat {
        private int     stat;
        Stat(int stat){
            this.stat=stat;
        }
        public int get(){
            return stat;
        }
        public void reduce(int reduction){
            this.stat-=reduction;
        }
        public void increase(int increase){
            this.stat+=increase;
        }
    }

    
    //End of Class. Class tester after
    public static void main(String[] args){
        text("----------Test01----------");
        text("Create and print an instance of Entity, with stats");
        int[] basicStats = {15,15,10,10,5,5,5,5,5,5};
        Entity wilson = new Entity(basicStats, "Wilson");
        text(wilson.toString());
        wilson.getAllStats();
        getWilson(wilson);
        
        text("----------Test02----------");
        text("Cause the entity to take damage, and then heal them after. the heal must not bring them above maxHP");
        text("10 Damage");
        wilson.damage(10);
        getWilson(wilson);
        text("Heal for 50");
        wilson.heal(50);
        getWilson(wilson);
        
        text("----------Test03----------");
        text("Cause the entity to take fatal damage. if the entity has taken fatal damage, they cannot be healed by the heal() function");
        text("20 damage. fatal damage");
        wilson.damage(20);
        getWilson(wilson);
        text("\n30 point heal. should fail due to death.");
        wilson.heal(30);
        getWilson(wilson);
        text("\nRaise wilson with a 5 point heal. should allow full heal after (10 point heal)");
        wilson.raise(5);
        wilson.heal(10);
        getWilson(wilson);
        
        text("----------Test04----------");
        text("Wilson casts a spell. it will consume 10 mana. After one use, wilson cant cast another spell.");
        getWilson(wilson);
        text("Spell #1");
        wilson.spellCast(10);
        getWilson(wilson);
        text("Spell #2");
        wilson.spellCast(10);
        getWilson(wilson);
        
        text("----------Test05----------");
        text("Heal to full. rest at an inn or somesuch.");
        wilson.longRest();
        getWilson(wilson);
        
        text("----------Test06----------");
        text("Gain Experience and level up");
        wilson.gainExp(30);
        wilson.gainExp(30);
        wilson.gainExp(30);
        getWilson(wilson);
        wilson.getAllStats();
        
        text("----------Test07----------");
        text("Raising skill by other means");
        wilson.increaseAllStats(10, 7, 1, 2, 3, 1, 1, 5);
        getWilson(wilson);
        wilson.getAllStats();
        
        

    }
    public static void text(String text){
        System.out.println(text);
    }
    public static void getWilson(Entity wilson){
        System.out.println(wilson);
        System.out.println(wilson.getHp()+"/"+wilson.getMaxHp()+" hp "+wilson.getMp()+"/"+wilson.getMaxMp()+" mp");
        System.out.println("is dead?: "+wilson.isDead());
    }
            
}
