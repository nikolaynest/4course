package qa_1.lab1;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Calendar;
import java.util.HashMap;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by nikolay on 2/7/14.
 */
public class FridgeTest {

    private Fridge fridge;
    private int today;

    @Before
    public void init() {
        fridge = new Fridge();
        today = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
    }

    @Test
    public void testIsTurnOnTrue() {
        assertTrue(fridge.isTurnOn());
    }

    @Test public void testTurnOnTrue(){
        fridge.turnOn();
        assertTrue(fridge.isTurnOn());
    }
    @Test
    public void testIsTurnOffFalse() {
        fridge.turnOff();
        assertFalse(fridge.isTurnOn());
    }

    @Test public void testTurnOnAfterTurnOff(){
        fridge.turnOn();
        fridge.turnOff();
        fridge.turnOn();
        assertTrue(fridge.isTurnOn());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(fridge.isEmpty());
    }

    @Test
    public void testIsEmptyAfterPutFoodIn() {
        fridge.putFoodIn(new Food("Meat", Calendar.getInstance().get(Calendar.DAY_OF_YEAR)));
        assertFalse(fridge.isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void testPutFoodInNull() {
        fridge.putFoodIn(null);
    }

    @Test
    public void testHasSpoiledProductsFalse() {
        assertFalse(fridge.hasSpoiledProducts());
    }

    @Test
    public void testHasSpoiledProductsTrue() {
        Food f = new Food("some food", Calendar.getInstance().get(Calendar.DAY_OF_YEAR));
        f.setSpoiled(true);
        fridge.putFoodIn(f);
        assertTrue(fridge.hasSpoiledProducts());
    }

    @Test
    public void testHashMap() {
        HashMap<Food, Long> products = new HashMap<Food, Long>();
        Food food = new Food("Cheese", Calendar.getInstance().get(Calendar.DAY_OF_YEAR));
        products.put(food, System.currentTimeMillis());
        boolean equals = false;
        for (Food f : products.keySet()) {
            if (f.equals(food)) {
                equals = true;
            }
        }
        assertTrue(equals);
    }

    @Test
    public void testTakeFoodOutTwo() {
        Food food = new Food("Fish", Calendar.getInstance().get(Calendar.DAY_OF_YEAR));
        Food food1 = new Food("Tomato", Calendar.getInstance().get(Calendar.DAY_OF_YEAR));
        fridge.putFoodIn(food);
        fridge.putFoodIn(food1);
        fridge.takeFoodOut(food);
        fridge.takeFoodOut(food1);
        assertTrue(fridge.isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void testTakeFoodOutNull() {
        Food food = new Food("Fish", Calendar.getInstance().get(Calendar.DAY_OF_YEAR));
        fridge.putFoodIn(food);
        fridge.takeFoodOut(null);
    }

    @Test
    public void testTakeFoodOutOne() {
        Food food = new Food("Fish", Calendar.getInstance().get(Calendar.DAY_OF_YEAR));
        Food food1 = new Food("Tomato", Calendar.getInstance().get(Calendar.DAY_OF_YEAR));
        fridge.putFoodIn(food);
        fridge.putFoodIn(food1);
        fridge.takeFoodOut(food);
        assertFalse(fridge.isEmpty());
    }

    @Test
            (expected = java.util.ConcurrentModificationException.class)
    public void testTakeFoodOutWithException() {
        Food food = new Food("f1", Calendar.DAY_OF_YEAR);
        Food food1 = new Food("f2", Calendar.DAY_OF_YEAR);
        Food f = new Food("f3", Calendar.DAY_OF_YEAR);

        HashMap<Food, Integer> products = new HashMap<Food, Integer>();
        products.put(food, Calendar.DAY_OF_YEAR);
        products.put(food1, Calendar.DAY_OF_YEAR);
        products.put(f, Calendar.DAY_OF_YEAR);

        for (Food p : products.keySet()) {
            if (p.equals(food)) {
                products.remove(p);
            }
        }
    }

    @Ignore
    @Test
    public void testDifferenceDate() {
        Calendar c1 = Calendar.getInstance();
        c1.get(Calendar.DAY_OF_YEAR);
        Calendar c2 = c1;
    }

    @Test
    public void testGetNumOfSpoiledProductsOne() {
        Food f = new Food("a", Calendar.getInstance().get(Calendar.DAY_OF_YEAR));
        f.setSpoiled(true);
        fridge.putFoodIn(f);
        assertEquals(1, fridge.getNumOfSpoiledProducts());
    }

    @Test
    public void testGetNumOfSpoiledProductsMany() {
        for (int i = 0; i < 100; i++) {
            Food f = new Food(Integer.toString(i) + " food", Calendar.getInstance().get(Calendar.DAY_OF_YEAR));
            f.setSpoiled(true);
            fridge.putFoodIn(f);
        }
        assertEquals(100, fridge.getNumOfSpoiledProducts());
    }

    @Test
    public void testIsEmptyAfterTurnOn() {
        fridge.turnOn();
        assertTrue(fridge.isEmpty());
    }

    @Test
    public void testHasSpoiledProductsAfterPutFoodIn() {
        fridge.turnOn();
        Food f = new Food("f", today - 7);
        fridge.putFoodIn(f);
        assertFalse(fridge.hasSpoiledProducts());
    }

    @Test
    public void testHasSpoiledProdAfterWeek() {
        fridge.turnOn();
        Food f = new Food("f", today - 8);
        fridge.putFoodIn(f);
        assertTrue(fridge.hasSpoiledProducts());
    }

    @Test
    public void testHasSpoiledProdAfterDayWhenTurnOff() {
        fridge.turnOn();
        Food f = new Food("f", today - 1);
        fridge.putFoodIn(f);
        fridge.turnOff();
        assertFalse(fridge.hasSpoiledProducts());
    }

    @Test
    public void testHasSpoiledProdAfter2DaysWhenTurnOff() {
        fridge.turnOn();
        Food f = new Food("f", today - 2);
        fridge.putFoodIn(f);
        fridge.turnOff();
        assertTrue(fridge.hasSpoiledProducts());
    }

    @Test public void testTakeFoodOutDoesEqual(){
        Food f1 = new Food("food", 1);
        Food f2 = new Food("food", 1);
        fridge.putFoodIn(f1);
        assertTrue(fridge.takeFoodOut(f2));
    }
}
