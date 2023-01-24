package com.example.firstproject.ioc;

import org.springframework.stereotype.Component;

@Component
public class Chef {

    private IngredientFactory ingredientFactory;

    public Chef(IngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    public String cook(String menu) {
        // 재료 준비
//        Pork pork = new Pork("한돈 등심");
        Ingredient ingredient = ingredientFactory.get(menu);
        // 요리 반환
        return ingredient.getName() + "으로 만든 " + menu;
    }
}
