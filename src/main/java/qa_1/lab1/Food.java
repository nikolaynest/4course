package qa_1.lab1;

/**
 * Created by nikolay on 2/6/14.
 */
public class Food {

    private String food;
    private boolean isSpoiled;
    int dateWhenPutIn;

    public Food(String f, int date) {
        this.food = f;
        isSpoiled = false;
        dateWhenPutIn = date;
    }

    public String getFoodName() {
        return food;
    }

    public void setFoodName(String food) {
        this.food = food;
    }

    public boolean isSpoiled() {
        return isSpoiled;
    }

    public void setSpoiled(boolean isSpoiled) {
        this.isSpoiled = isSpoiled;
    }

    public int getDateWhenPutIn() {
        return dateWhenPutIn;
    }
}
