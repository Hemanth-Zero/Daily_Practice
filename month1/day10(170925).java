import java.util.*;
//2353. Design a Food Rating System
//188ms beats 69%
//RECAP IS BEST
class FoodRatings {
    class Food {
        String name;
        String cuisine;
        int rating;

        Food(String name, String cuisine, int rating) {
            this.name = name;
            this.cuisine = cuisine;
            this.rating = rating;
        }
    }

    Map<String, Food> foodMap;
    Map<String, TreeSet<Food>> cuisineMap;
    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        foodMap = new HashMap<>();
        cuisineMap = new HashMap<>();

        for (int i = 0; i < foods.length; i++) {
            Food f = new Food(foods[i], cuisines[i], ratings[i]);
            foodMap.put(foods[i], f);
            cuisineMap.putIfAbsent(cuisines[i], new TreeSet<>(
                (a, b) -> {
                    if (a.rating != b.rating) return b.rating - a.rating;
                    return a.name.compareTo(b.name); 
                }
            ));
            cuisineMap.get(cuisines[i]).add(f);
        }
    }

    public void changeRating(String foodName, int newRating) {
        Food f = foodMap.get(foodName);
        TreeSet<Food> set = cuisineMap.get(f.cuisine);

        set.remove(f);     
        f.rating = newRating;
        set.add(f);         
    }

    public String highestRated(String cuisine) {
        return cuisineMap.get(cuisine).first().name;
    }
}
