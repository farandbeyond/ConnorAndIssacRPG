/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonalRPGDev03;

//import java.util.ArrayList;

/**
 *
 * @author Connor
 */
public class Party {
    //ArrayList<Entity> party;
    Entity[] party;
    int partySize;
    
    Party(int partySize){
        party=new Entity[partySize];
        this.partySize=partySize;
    }
    Party(Entity[] party){
        this.party=party;
        this.partySize=party.length;
    }
    public void add(Entity member){
        for(int i=0;i<party.length;i++){
            if(party[i]==null){
                party[i]=member;
                return;
            }
        }
        System.out.printf("Could not add %s to party\n", member.toString());
    }
    public void remove(int member){
        if(invalidInput(member)){
            System.out.println("Illegal value");
            return;
        }
        System.out.printf("Removed %s, slot %d, from party\n", party[member],member+1);
        party[member]=null;
        for(int i=0;i<party.length-1;i++){
            if(party[i]==null){
                party[i]=party[i+1];
            }
        }
    }
    public void swap(int memberToSwap, int positionToSwapTo){
        if(invalidInput(memberToSwap)||invalidInput(positionToSwapTo)){
            System.out.println("Illegal value.");
            return;
        }
        Entity temp = party[positionToSwapTo];
        party[positionToSwapTo]=party[memberToSwap];
        party[memberToSwap]=temp;
    }
    public void listAllMembers(){
        if(getCurrentPartySize()==0){
            System.out.println("party is Empty");
        }else{
            for(int i=0;i<getCurrentPartySize();i++){
                party[i].toString();
                party[i].getAllStats();
            }
        }
    }
    public void info(int member){
        if(invalidInput(member)){
            System.out.println("Illegal Value");
            return;
        }
        System.out.printf("%s\n", party[member].toString());
        party[member].getAllStats();
    }
    public void rest(){
        for(Entity member:party){
            member.longRest();
        }
    }
    public void dealDamage(int member, int damage){
        party[member].damage(damage);
    }
    public void getAllMembersStats(){
        for(Entity member:party){
            member.getStatLine();
        }
    }
    
    public Entity[] getArray(){
        return party;
    }
    public int getMaxPartySize(){
        return partySize;
    }
    public int getCurrentPartySize(){
        for(int i=0;i<party.length;i++){
            if(party[i]!=null)
                return i;
        }
        return partySize;
    }
    private boolean invalidInput(int member){
        return member>=partySize&&member>=0;
    }
    
    public static void main(String[] args){
        text("----------Test01----------");
        text("Creation of variables");
        Party party = new Party(4);
        int[] basicStats = {15,15,10,10,5,5,5,5,5,5};
        Entity wilson = new Entity(basicStats, "Wilson");
        Entity matilda = new Entity(basicStats, "Matilda");
        Entity merg = new Entity(basicStats, "Merg");
        Entity huntress = new Entity(basicStats, "Huntress");
        text("----------Test02----------");
        text("Add everyone to the party");
        party.add(wilson);
        party.add(matilda);
        party.add(merg);
        party.add(wilson);
        text("----------Test03----------");
        text("whoops. huntress was never added");
        party.add(huntress);
        text("----------Test04----------");
        text("we will have to remove someone else first.");
        party.remove(3);
        party.add(huntress);
        text("----------Test05----------");
        text("Lets swap some members");
        party.listAllMembers();
        party.swap(3, 2);
        party.swap(0, 2);
        text("");
        party.listAllMembers();
        text("----------Test06----------");
        text("Lets grab some greater detail from these people");
        party.info(0);
        party.info(1);
        party.info(2);
        party.info(3);
        text("----------Test07----------");
        text("Invalid Inputs should be rejected");
        party.remove(4);
        party.info(4);
        party.swap(3, 4);
        party.swap(4, 1);
        text("----------Test08----------");
        text("hit the inn. everyone rests");
        party.rest();
        party.getAllMembersStats();
        text("----------Test09----------");
        text("Damage is dealt to the party. ouch");
        party.dealDamage(0, 5);
        party.dealDamage(1, 2);
        party.dealDamage(2, 15);
        party.dealDamage(3, 9);
        party.listAllMembers();
        party.getAllMembersStats();
        party.rest();
        System.out.print("\n");
        party.getAllMembersStats();
        //System.out.printf("is this good? %s\n", 1>2 ? "yes":"no");
        /*text("----------Test09----------");
        text("Hai Brodie");
        int[] brodieStats = {15,15,13,13,10,5,7,10,1,8};
        Entity brodie = new Entity(brodieStats,"Brodie");
        party.remove(3);
        party.add(brodie);
        party.listAllMembers();
        party.dealDamage(3, 15);
        party.getAllMembersStats();
        party.getArray()[3].raise(3);
        //party.getAllMembersStats();
        party.getArray()[3].getAllStats();
        party.getArray()[3].getStatLine();*/
              
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
