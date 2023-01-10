package com.example.pizzaapp;

public class ToppingItem {
    private boolean selected;
    private Topping topping;

    public ToppingItem(Topping topping) {
        this.topping = topping;
        selected = false;
    }

    public boolean isSelected() {
        return selected;
    }

    public void select(boolean checked) {
        selected = true;
    }

    public Topping getTopping() {
        return topping;
    }
}
