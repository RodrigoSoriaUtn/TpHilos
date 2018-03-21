/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;


/**
 *
 * @author rodrigo
 */
public class BeerHouse {
    
    private int beersCuantity;
    private final int beerLimit = 100;
    
    public BeerHouse(){
        beersCuantity = beerLimit; //inicializa llena.
    }
    
    public void charge(int beersToCharge){
        synchronized(this){
            while(isFull(beersToCharge)){
                try {
                    System.out.println("BeerHouse llena.");
                    wait();
                } catch (InterruptedException ex) {
                    System.out.println("Error en la espera cuando la casa esta llena.");
                }
            }
            System.out.println("Cerveza cargada. Stock: " + beersCuantity);
            beersCuantity+= beersToCharge;
            notifyAll();
        }
    }
    
    public void sell(){
        synchronized(this){
            if(!isEmpty()){
                System.out.println("Cerveza vendida. Stock: "+ beersCuantity);
                beersCuantity--;
                notifyAll();
            }else{
                System.out.println("Consumidor Quiso comprar y no había más :(");
            }
        }
    }
    
    public boolean isFull(){
        synchronized(this){
            return beersCuantity >= beerLimit;
        }
    }
    
    public boolean isFull(int additionalBeers){
        synchronized(this){
            return beersCuantity+additionalBeers >= beerLimit;
        }
    }
    
    public boolean isEmpty(){
        synchronized(this){
            return beersCuantity <= 0;
        }
    }
    
}
