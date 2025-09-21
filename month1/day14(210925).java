import java.util.*;
//Leetcode 1912. Design Movie Rental System
// to hard n DSA n mid exams :/ solved 30% 
class MovieRentingSystem {
    class Entity implements Comparable<Entity> {
        int shop, movie, price;
        Entity(int shop, int movie, int price){
            this.shop = shop;
            this.movie = movie;
            this.price = price;
        }
        public int compareTo(Entity e){
            if(price != e.price) return price - e.price;
            if(shop != e.shop) return shop - e.shop;
            return movie - e.movie;
        }
    }

    Map<Integer, TreeSet<Entity>> available = new HashMap<>();
    TreeSet<Entity> rented = new TreeSet<>();
    Map<String, Integer> priceMap = new HashMap<>();

    public MovieRentingSystem(int n, int[][] entries) {
        for(int[] e : entries){
            int shop = e[0], movie = e[1], price = e[2];
            priceMap.put(shop+"#"+movie, price);
            available.putIfAbsent(movie, new TreeSet<>());
            available.get(movie).add(new Entity(shop, movie, price));
        }
    }

    public List<Integer> search(int movie) {
        List<Integer> res = new ArrayList<>();
        if(!available.containsKey(movie)) return res;
        Iterator<Entity> it = available.get(movie).iterator();
        for(int i = 0; i < 5 && it.hasNext(); i++) res.add(it.next().shop);
        return res;
    }

    public void rent(int shop, int movie) {
        int price = priceMap.get(shop+"#"+movie);
        Entity e = new Entity(shop, movie, price);
        available.get(movie).remove(e);
        rented.add(e);
    }

    public void drop(int shop, int movie) {
        int price = priceMap.get(shop+"#"+movie);
        Entity e = new Entity(shop, movie, price);
        rented.remove(e);
        available.get(movie).add(e);
    }

    public List<List<Integer>> report() {
        List<List<Integer>> res = new ArrayList<>();
        Iterator<Entity> it = rented.iterator();
        for(int i = 0; i < 5 && it.hasNext(); i++){
            Entity e = it.next();
            res.add(Arrays.asList(e.shop, e.movie));
        }
        return res;
    }
}
