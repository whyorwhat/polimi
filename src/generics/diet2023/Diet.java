package generics.diet2023;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Diet <T>{
    // T is the food type

    Map<String, List<T>> weekProgram;

    public Diet(){
        weekProgram = new HashMap<>();
    }

    public void addFood(T food, String day){
        List<T> foodList = weekProgram.get(day);
        if(foodList == null){
            foodList = new ArrayList<>();
            weekProgram.put(day, foodList);
        }
        foodList.add(food);
    }

    public void removeFood(T food, String day){
        List<T> foodList = weekProgram.get(day);
        if(foodList != null){
            foodList.remove(food);
        }
    }

    public List<T> getDailyFood(String day){
        List<T> foodList = weekProgram.get(day);
        if (foodList == null) {
            return new ArrayList<>();
        }
        return foodList;
    }

    public Map<String, List<T>> getWeekProgram(){
        return weekProgram;
    }

    public static void main(String[] args) {
        Diet<String> dietOne = new Diet<>();
        Diet<Food> dietTwo = new Diet<>();

        dietOne.addFood("Fish", "Monday");
        dietOne.addFood("Meat", "Tuesday");

        dietTwo.addFood(new Food("Cookies", 5000), "Wednesday");
        dietTwo.addFood(new Food("Cake", 10000), "Thursday");
    }
}
