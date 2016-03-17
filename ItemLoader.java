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
public class ItemLoader {
    public static final int
            EMPTY=0,
            POTION=1,
            ELIXER=2,
            REJUVI=3,
            BRONZESWORD=10,
            IRONSWORD=11,
            STEELSWORD=12;
    /**
     * 
     * @param itemID
     * @param q quantity
     * @return 
     */
    public static Item loadItem(int itemID, int q){
        switch(itemID){
            case 1: return new HealingItem(itemID,q,5,10,"Potion","Heals for 10hp",HealingItem.HEALTH);
            case 2: return new HealingItem(itemID,q,5,10,"Elixer","Heals for 10mp",HealingItem.MANA);
            case 3: return new HealingItem(itemID,q,5,10,"Rejuvi","Heals for 10hp/10mp",HealingItem.HPANDMP);
            case 10: return new Weapon(itemID,5,4,"Bronze Sword","A simple sword for simple people");
            case 11: return new Weapon(itemID,7,5,"Iron Sword","A simple sword for simple people");
            case 12: return new Weapon(itemID,9,6,"Steel Sword","A simple sword for simple people"); 
        }
        return null;
    }
}
