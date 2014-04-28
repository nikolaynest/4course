package diplom.com.nikolay;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikolay on 3/25/14.
 */
public class Rating {

    public List<Double> getRatings(){
        List<Double> ratings = new ArrayList<>();
        ratings.add(0.1);
        ratings.add(0.2);
        ratings.add(0.3);
        ratings.add(0.4);
        ratings.add(0.5);
        ratings.add(0.6);
        ratings.add(0.7);
        ratings.add(0.8);
        ratings.add(0.9);
        return ratings;
    }
}
