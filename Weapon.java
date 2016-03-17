/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersonalRPGDev03;

import java.util.Random;

/**
 *
 * @author Connor
 */
public class Weapon extends Item{
    int     baseDamage,
            rollDamage,
            id;
    String  name,
            description;
    Random  rand;
    Weapon(int id, int baseDamage, int rollDamage, String name, String description){
        super(id,1,1,name,description);
        this.baseDamage=baseDamage;
        this.rollDamage=rollDamage;
        rand = new Random();
        /*
        this.id=id;
        this.baseDamage=baseDamage;
        this.name=name;
        this.description=description;
        this.rollDamage=rollDamage;
        rand = new Random();*/
    }
    public void use(Entity Target){
        System.out.println("No Effect");
    }
    public int attack(int Strength){
        int damage = baseDamage+rand.nextInt(rollDamage)+(Strength/2);
        System.out.println(damage+" Damage");
        return damage;
    }

    
    
    public static void main(String[] args){
        text("----------Test01----------");
        text("Ooh a sword! Sharp!");
        int[] basicStats = {15,15,10,10,5,5,5,5,5,5};
        Entity wilson = new Entity(basicStats, "Wilson");
        Weapon sword = new Weapon(101,5,4,"Bronze Sword","A simple sword for simple people");
        text(sword.getName());
        text("----------Test02----------");
        text("Wilson Equips the sword.");
        wilson.weapon=sword;
        text(wilson.weapon.getName());
        text("----------Test03----------");
        text("Wilson attacks... himself. ouch");
        getWilson(wilson);
        wilson.damage(wilson.weapon.attack(wilson.getStr()));
        getWilson(wilson);
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
