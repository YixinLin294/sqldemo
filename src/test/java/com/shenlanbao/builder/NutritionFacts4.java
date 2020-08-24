package com.shenlanbao.builder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NutritionFacts4 {
    private int servingSize;
    private int servings;
    private int calories;
    private int fat;
    private int sodium;
    private int carbohydrate;


    private NutritionFacts4() {
    }

    public static void main(String[] args) throws Exception {
        NutritionFacts4 nutritionFacts4 = new EntityCreator<>(NutritionFacts4.class)
                .setValue("servingSize", 240)
                .setValue("servings", 8)
                .setValue("calories", 100)
                .setValue("sodium", 35)
                .setValue("carbohydrate", 27)
                .build();

        System.out.println(nutritionFacts4.carbohydrate);
    }
}
