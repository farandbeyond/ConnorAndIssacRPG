/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonalRPGDev03;

import java.util.ArrayList;

/**
 *
 * @author Connor
 */
public class Inventory {
    ArrayList<Item> inventory;
    int             maxSize;
    Inventory(int size){
        inventory=new ArrayList<>();
        this.maxSize=size;
    }
    public void add(Item item){
    //System.out.println(inventory.size());
        for(Item thing:inventory){
            if(thing.getId()==item.getId()){
                //bug checker
                //System.out.println("Item Quantity: "+item.getQuantity());
                //System.out.println("Space Remaining: "+(thing.getMaxQuantity()-thing.getQuantity()));
                int numberRestocked = (thing.getMaxQuantity()-thing.getQuantity())<item.getQuantity()
                        ?
                        thing.getMaxQuantity()-thing.getQuantity():item.getQuantity();
                System.out.printf("Restocked an item, %s x%d\n",item.toString(),numberRestocked);
                
                
                thing.restock(item.getQuantity());
                //int numberRestocked = thing.getMaxQuantity()-thing.getQuantity();
                //int numberRestocked = (thing.getQuantity()+item.getQuantity())-item.getMaxQuantity();
                return;
            }
        }
        if(inventory.size()>=maxSize){
            System.out.printf("Inventory Full, could not add %s x%d\n",item.toString(),item.getQuantity());
            return;
        }
        inventory.add(item);
        System.out.printf("added a new item, %s x%d\n",item.toString(),item.getQuantity());
        
    } 
    public void drop(int itemInList){
        inventory.remove(itemInList);
    }
    public void use(int itemInList, Entity target){
        inventory.get(itemInList).use(target);
        //inventory.get(itemInList).lowerQuantity(1);
        if(inventory.get(itemInList).getQuantity()==0){
            inventory.remove(itemInList);
        }
    }
    public void listItems(){
        System.out.println("[");
        for(Item thing:inventory){
            System.out.println("-"+thing.getName()+" x"+thing.getQuantity());
        }
        System.out.println("]");
    }
    public void increaseInventorySize(){
        maxSize+=5;
    }

    public static void main(String[] args){
        text("----------Test01----------");
        text("Creation of items");
        int[] basicStats = {15,15,10,10,5,5,5,5,5,5};
        Entity wilson = new Entity(basicStats, "Wilson");
        Item singlePotion = new HealingItem(0,1,5,10,"potion","Heals for 10hp",HealingItem.HEALTH);
        Item twoPotion = new HealingItem(0,2,5,10,"potion","Heals for 10hp",HealingItem.HEALTH);
        Inventory inventory = new Inventory(4);
        text("----------Test02----------");
        text("Added a potion to the inventory");
        inventory.add(singlePotion);
        inventory.listItems();
        text("----------Test03----------");
        text("more potions! they should restock the old potion item");
        text(singlePotion.getQuantity()+"");
        text(twoPotion.getQuantity()+"");
        //odd little bug here. not printing correct quantity with single potion
        inventory.add(singlePotion);
        inventory.add(twoPotion);
        inventory.listItems();
        text("----------Test04----------");
        text("so many potions. can they all fit in the inv?");
        inventory.add(twoPotion);
        inventory.listItems();
        text("----------Test05----------");
        text("Now for a new item");
        Item sword = new Weapon(101,5,4,"Bronze Sword","A simple sword for simple people");
        inventory.add(sword);
        inventory.listItems();
        inventory.add(new Weapon(202,6,2,"Iron Sword","A more advanced model"));
        inventory.listItems();
        text("----------Test06----------");
        text("Now for another new item");
        inventory.add(new HealingItem(1,2,5,10,"Elixer","Heals for 10mp",HealingItem.MANA));
        inventory.listItems();
        inventory.add(new HealingItem(1,2,5,10,"Elixer","Heals for 10mp",HealingItem.MANA));
        inventory.listItems();
        text("----------Test07----------");
        text("Full Inventory: Rejects all new items");
        inventory.add(new HealingItem(2,2,5,10,"Elixer","Heals for 10mp",HealingItem.MANA));
        inventory.listItems();
        text("----------Test08----------");
        text("Using items on wilson. hes in rough shape");
        wilson.damage(14);
        getWilson(wilson);
        inventory.listItems();
        inventory.use(0, wilson);
        getWilson(wilson);
        inventory.listItems();
        wilson.spellCast(10);
        getWilson(wilson);
        inventory.use(3, wilson);
        getWilson(wilson);
        inventory.listItems();
        text("----------Test09----------");
        text("An item that cant be used...");
        wilson.damage(14);
        getWilson(wilson);
        inventory.listItems();
        inventory.use(0, wilson);
        inventory.listItems();
        text("----------Test10----------");
        text("Using the ItemLoader class");
        inventory.increaseInventorySize();
        wilson.raise(1);
        getWilson(wilson);
        ItemLoader.loadItem(ItemLoader.POTION,2).use(wilson);
        getWilson(wilson);
        inventory.add(ItemLoader.loadItem(ItemLoader.STEELSWORD, 1));
        inventory.add(ItemLoader.loadItem(ItemLoader.REJUVI, 3));
        inventory.add(ItemLoader.loadItem(ItemLoader.ELIXER, 5));
        inventory.listItems();
        /*text("----------Test10----------");
        text("Unrelated");
        System.out.printf("is this good? %s\n", 1>2 ? "yes":"no");
                */
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

