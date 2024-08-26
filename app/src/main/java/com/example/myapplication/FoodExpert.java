package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class FoodExpert {
    List<String> getMenu (String meal) {
        List<String> menu = new ArrayList<>();
        if (meal.equals("Breakfast")) {
            menu.add("Roti");
            menu.add("Dal");
            menu.add("Egg");
        }
        else if (meal.equals("Lunch")) {
            menu.add("Rice");
            menu.add("Chicken");
            menu.add("Fish");
        }
        else {
            menu.add("Rice");
            menu.add("Chicken");
            menu.add("Vegetables");
        }
        return menu;
    }
}