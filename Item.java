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
public abstract class Item {
    private
    int     quantity;
    private
    final
    int     maxQuantity,
            id;
    private
    String  name,
            description;
    Item(int id, int quantity, int maxQuantity, String name,String description){
        this.id=id;
        this.name=name;
        this.quantity=quantity;
        this.maxQuantity=maxQuantity;
        this.description=description;
    }
    Item(){
        this.name="-";
        this.quantity=0;
        this.maxQuantity=0;
        this.id=0;
    }

    public void lowerQuantity(int itemsUsed){
        quantity-=itemsUsed;
    }
    public void restock(int increase){
        this.quantity+=increase;
        if(quantity>maxQuantity){
            System.out.printf("Too many %s, dropping %d\n",toString(),quantity-maxQuantity);
            lowerQuantity(quantity-maxQuantity);
        }
    }
    
    public abstract void use(Entity target);
    
    public int getQuantity(){
        return quantity;
    }
    public String getDescription(){
        return description;
    }
    public int getMaxQuantity(){
        return maxQuantity;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    
    public String toString(){
        return name;
    }
}
