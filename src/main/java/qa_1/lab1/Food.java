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

    public boolean isSpoiled() {
        return isSpoiled;
    }

    public void setSpoiled(boolean isSpoiled) {
        this.isSpoiled = isSpoiled;
    }

    @Override
    public boolean equals(Object obj) {
        if (this != obj)
            return false;
        if (!(obj instanceof Food)){
            return false;
        }
        Food f = (Food)obj;
        return (food.equals(f.food) && isSpoiled == f.isSpoiled());
    }

    @Override
    public int hashCode() {
        int result = food != null ? food.hashCode() : 0;
        result = 31 * result + (isSpoiled ? 1 : 0);
        result = 31 * result + dateWhenPutIn;
        return result;
    }
}
