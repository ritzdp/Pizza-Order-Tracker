package com.example.pizzaapp;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
/**
 * Activity for the NYPizza ordering screen.
 * Handles pizza options, including type and size.
 * @author Rishabh Patel, Albert Zou
 */
public class NYPizzaActivity extends SecondaryViewActivity implements AdapterView.OnItemSelectedListener {
    private NYPizza factory;
    private Pizza pizza;
    private StoreOrders storeOrder;
    private Order order;
    private ArrayList<Topping> availableToppings;
    private ArrayList<Topping> selectedToppings;

    private ImageView image;
    private Spinner flavorSpinner;
    private RadioGroup sizeToggles;
    private TextView availabel;
    private RecyclerView available;
    private Button selectBtn;
    private Button deselectBtn;
    private RecyclerView selected;
    private TextView crust;
    private TextView price;
    private Button addBtn;

    private static final int MAX_TOPPINGS = 7;
    private static final int BYO = 0;
    private static final int DELUXE = 1;
    private static final int BBQ = 2;
    private static final int MEATZZA = 3;
    private static final String[] FLAVORS = {"Build Your Own", "Deluxe", "BBQ Chicken", "Meatzza"};
    private static final Topping[] DELUXE_TOP = {
            Topping.SAUSAGE, Topping.PEPPERONI, Topping.GREEN_PEPPER, Topping.ONION, Topping.MUSHROOM};
    private static final Topping[] BBQ_CHICKEN_TOP = {
            Topping.BBQ_CHICKEN, Topping.GREEN_PEPPER, Topping.PROVOLONE, Topping.CHEDDAR};
    private static final Topping[] MEATZZA_TOP = {Topping.SAUSAGE, Topping.PEPPERONI, Topping.BEEF, Topping.HAM};

    /**
     * Creates pizza and initializes UI elements.
     * Populates text and image fields.
     * @param savedInstanceState default parameter
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ny_pizza);

        factory = new NYPizza();
        availableToppings = new ArrayList<>();
        selectedToppings = new ArrayList<>();
        storeOrder = (StoreOrders) getIntent().getSerializableExtra("STORE");
        order = storeOrder.getCurrOrder();
        pizza = factory.createBuildYourOwn();
        image = (ImageView) findViewById(R.id.pizzaImg);
        flavorSpinner = (Spinner) findViewById(R.id.type);
        sizeToggles = (RadioGroup) findViewById(R.id.size);
        availabel = (TextView) findViewById(R.id.availabel);
        available = (RecyclerView) findViewById(R.id.available);
        selectBtn = (Button) findViewById(R.id.selectBtn);
        deselectBtn = (Button) findViewById(R.id.deselectBtn);
        selected = (RecyclerView) findViewById(R.id.selected);
        crust = (TextView) findViewById(R.id.crust);
        price = (TextView) findViewById(R.id.price);
        addBtn = (Button) findViewById(R.id.addBtn);

        image.setImageResource(R.drawable.ny_byo);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, FLAVORS);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        flavorSpinner.setAdapter(arrayAdapter);
        flavorSpinner.setOnItemSelectedListener(this);

        sizeToggles.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            /**
             * Updates values based on new size selection.
             * @param radioGroup size radioGroup
             * @param i index of selected size
             */
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                String s = ((RadioButton) findViewById(i)).getText().toString().toUpperCase();
                pizza.setSize(Size.valueOf(s));
                price.setText(pizza.price() + "");
            }
        });

        refresh();
        setAdapters(false);

        addBtn.setOnClickListener(new View.OnClickListener() {
            /**
             * Adds pizza to order and alerts the user.
             * @param v default parameter
             */
            @Override
            public void onClick(View v) {
                order.add(pizza);
                makePopUp(getString(R.string.pizza_title), getString(R.string.pizza_title));
            }
        });
    }
    /**
     * Updates fields to match the current selection.
     * Sets size, crust, and price fields.
     * Updates toppings menus
     */
    private void refresh() {
        String s = ((RadioButton) findViewById(sizeToggles.getCheckedRadioButtonId()))
                .getText().toString().toUpperCase();
        pizza.setSize(Size.valueOf(s));

        crust.setText(pizza.crust());

        availableToppings.clear();
        for ( Topping t : Topping.values() ) {
            availableToppings.add(t);
        }

        selectedToppings.clear();

        price.setText(pizza.price() + "");
    }

    /**
     * Initializes the toppings RecyclerAdapters.
     * Sets listeners to select and deselect toppings.
     * @param notBYO
     */
    private void setAdapters(boolean notBYO) {
        RecyclerAdapter availableAdapter = new RecyclerAdapter(availableToppings, notBYO);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        available.setLayoutManager(layoutManager);
        available.setItemAnimator(new DefaultItemAnimator());
        available.setAdapter(availableAdapter);

        RecyclerAdapter selectedAdapter = new RecyclerAdapter(selectedToppings, notBYO);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        selected.setLayoutManager(layoutManager);
        selected.setItemAnimator(new DefaultItemAnimator());
        selected.setAdapter(selectedAdapter);

        selectBtn.setOnClickListener(new View.OnClickListener() {
            /**
             * Adds selected topping to the pizza.
             * Alerts the user if the pizza is at 7 toppings.
             * @param v default parameter
             */
            @Override
            public void onClick(View v) {
                Topping t = availableAdapter.getSelected();
                if ( t != null ) {
                    if ( selectedAdapter.getItemCount() >= MAX_TOPPINGS ) {
                        showToast(getString(R.string.too_many));
                    } else {
                        selectTopping(t);
                        setAdapters(false);
                        price.setText(pizza.price() + "");
                    }
                } else {
                    showToast(getString(R.string.no_selection));
                }
            }
        });

        deselectBtn.setOnClickListener(new View.OnClickListener() {
            /**
             * Removes selected topping from the pizza.
             * @param v default parameter
             */
            @Override
            public void onClick(View v) {
                Topping t = selectedAdapter.getSelected();
                if ( t != null ) {
                    deselectTopping(t);
                    setAdapters(false);
                    price.setText(pizza.price() + "");
                } else {
                    showToast(getString(R.string.no_selection));
                }
            }
        });
    }
    /**
     * Adds chosen topping to selected list and removes it from available list.
     * @param t topping to add.
     */
    private void selectTopping(Topping t) {
        selectedToppings.add(t);
        availableToppings.remove(t);
        pizza.add(t);
    }

    /**
     * Removes the chosen topping.
     * Adds the topping to available and removes it from pizza
     * @param t the topping to remove.
     */
    private void deselectTopping(Topping t) {
        availableToppings.add(t);
        selectedToppings.remove(t);
        pizza.remove(t);
    }

    /**
     * Controls whether the user can select items in the toppings menus.
     * Controls the available and selected fields and the select and deselect buttons.
     * @param notBYO
     */
    private void toggleVisibility(boolean notBYO) {
        int v = ( notBYO ) ? View.GONE : View.VISIBLE;
        availabel.setVisibility(v);
        available.setVisibility(v);
        selectBtn.setVisibility(v);
        deselectBtn.setVisibility(v);
    }

    /**
     * Sets image and toppings settings based on the chosen type of pizza.
     * @param adapterView default parameter
     * @param view default parameter
     * @param i index of the selected item
     * @param l default parameter
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch ( i ) {
            case BYO:
                pizza = factory.createBuildYourOwn();
                refresh();
                image.setImageResource(R.drawable.ny_byo);
                break;
            case DELUXE:
                pizza = factory.createDeluxe();
                refresh();
                image.setImageResource(R.drawable.ny_deluxe);
                for ( Topping t : DELUXE_TOP ) selectTopping(t);
                break;
            case BBQ:
                pizza = factory.createBBQChicken();
                refresh();
                image.setImageResource(R.drawable.ny_bbq);
                for ( Topping t : BBQ_CHICKEN_TOP ) selectTopping(t);
                break;
            case MEATZZA:
                pizza = factory.createMeatzza();
                refresh();
                image.setImageResource(R.drawable.ny_meatzza);
                for ( Topping t : MEATZZA_TOP ) selectTopping(t);
                break;
        }
        setAdapters(( i != BYO ));
        toggleVisibility(( i != BYO ));
    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}

    @Override
    protected StoreOrders getStoreOrder() {
        return storeOrder;
    }
}