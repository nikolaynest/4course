package qa_1.lab1;

import java.util.*;

/**
 * Created by nikolay on 2/6/14.
 */
public class Fridge {

    private final static int STORAGE_LIFE_TURN_ON = 7;
    private final static int STORAGE_LIFE_TURN_OFF = 1;

    private boolean isTurnOn;

    public ArrayList<Food> getProducts() {
        return products;
    }

    private ArrayList<Food> products;
    private int storageLife;

    public Fridge() {
        this.isTurnOn = true;
        this.products = new ArrayList<Food>();
        storageLife = STORAGE_LIFE_TURN_ON;
    }

    public Fridge(Food food) {
        isTurnOn = true;
        products = new ArrayList<Food>();
        products.add(food);
        storageLife = STORAGE_LIFE_TURN_ON;
    }

    public boolean isTurnOn() {
        return isTurnOn;
    }

    public boolean isEmpty() {
        return products.size() == 0 ? true : false;
    }

    public void turnOn() {
        isTurnOn = true;
        storageLife = STORAGE_LIFE_TURN_ON;
    }

    public void turnOff() {
        isTurnOn = false;
        storageLife = STORAGE_LIFE_TURN_OFF;
    }

    public void putFoodIn(Food food) {
        if (food == null)
            throw new NullPointerException(" \"null\" is not valid value for add to Fridge instance");
        products.add(food);
    }

    public boolean takeFoodOut(Food food) {
        if (food == null) {
            throw new NullPointerException("Your link points to null");
        }
        for (Iterator<Food> foodIt = products.iterator(); foodIt.hasNext(); ) {
            if (foodIt.next().equals(food)) {
                foodIt.remove();
                return true;
            }
        }
        return false;
    }

    public void deleteFood(Food food){
        if (food == null)
            throw new NullPointerException();
        for(int i = 0; i < products.size(); i++){
            if (products.get(i).equals(food))
                products.remove(i);
        }
    }

    public boolean hasSpoiledProducts() {
        changeFoodStatus();
        for (Food p : products) {
            if (p.isSpoiled()) {
                return true;
            }
        }
        return false;
    }

    public int getNumOfSpoiledProducts() {
        changeFoodStatus();
        int num = 0;
        for (Food f : products) {
            if (f.isSpoiled()) {
                num++;
            }
        }
        return num;
    }

    public void changeFoodStatus() {
        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(Calendar.DAY_OF_YEAR);
        for (Food f : products) {
            if (Math.abs(today - f.dateWhenPutIn) > storageLife) {
                f.setSpoiled(true);
            }
        }
    }

    public int getNumOfProducts() {
        return products.size();
    }
}