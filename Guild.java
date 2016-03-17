/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonalRPGDev03;

/**
 *
 * @author Connor
 */
public class Guild {
    private int
            guildSize;
    private Entity[]
            guild;
    
    Guild(int guildSize,Entity firstMember){
        this.guildSize=guildSize;
        this.guild=new Entity[guildSize];
        this.add(firstMember);
    }
    Guild(int guildSize, Entity[] guild){
        this.guild=guild;
        this.guildSize=guildSize;
    }
    public void add(Entity member){
        for(int i=0;i<guild.length;i++){
            if(guild[i]==null){
                guild[i]=member;
                member.assignGuildSlot(i);
                return;
            }
        }
        System.out.printf("Could not add %s to guild\n", member.toString());
    }
    public void remove(int member, Party party){
        if(invalidEntry(member))
            return;
        for(Entity pmem:party.getArray()){
            if(pmem==guild[member]){
                party.remove(member);
            }
        }
        guild[member]=null;
        for(int i=0;i<guild.length-1;i++){
            if(guild[i]==null){
                guild[i]=guild[i+1];
            }
        }
                 
    }
    
    public void addToParty(Entity member, Party party){
        party.add(member);
    }
    public void listAllMembers(){
        for(Entity member:guild){
            if(member!=null)
            System.out.printf("-%s\n",member.toString());
        }
    }
    
    public boolean invalidEntry(int entry){
        try{
            guild[entry].damage(0);
            return false;
        }
        catch(ArrayIndexOutOfBoundsException e){
            return true;
        }
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
        Guild guild = new Guild(10,wilson);
        guild.listAllMembers();
        text("----------Test02----------");
        text("Add members to guild");
        guild.add(matilda);
        guild.add(merg);
        guild.add(huntress);
        guild.listAllMembers();
        text("----------Test02----------");
        text("Choose the party from the guild");
        party.listAllMembers();
        guild.addToParty(merg, party);
        party.listAllMembers();
    }
    public static void text(String text){
        System.out.println(text);
    }
}
