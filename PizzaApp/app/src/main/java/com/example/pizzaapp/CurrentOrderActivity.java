package com.example.pizzaapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
/**
 * Activity for the CurrentOrdering screen.
 * Allows for deleting pizzas from and placing the order.
 * @author Rishabh Patel, Albert Zou
 */
public class CurrentOrderActivity extends SecondaryViewActivity {
    private Order order;
    private StoreOrders storeOrder;
    private TextView orderNum;
    private ListView pizzas;
    private ArrayAdapter<Pizza> pizzasAdapter;
    private TextView subtotal;
    private TextView tax;
    private TextView total;
    private Button removeBtn;
    private Button placeBtn;
    private Button clearBtn;
    private int selected;

    private static final int NOT_SELECTED = -1;

    /**
     * Initializes UI elements and populates fields
     * Receives and stores storeOrder from Main Activity.
     * @param savedInstanceState default parameter
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        storeOrder = (StoreOrders) getIntent().getSerializableExtra("STORE");
        order = storeOrder.getCurrOrder();
        selected = NOT_SELECTED;

        orderNum = findViewById(R.id.orderNum);
        pizzas = findViewById(R.id.pizzas);
        subtotal = findViewById(R.id.subtotal);
        tax = findViewById(R.id.tax);
        total = findViewById(R.id.total);
        removeBtn = findViewById(R.id.removeBtn);
        placeBtn = findViewById(R.id.placeBtn);
        clearBtn = findViewById(R.id.clearBtn);

        orderNum.setText(Integer.toString(order.getSerial()));
        setListeners();
        refresh();
    }

    /**
     * Sets listeners for interactive UI elements.
     */
    private void setListeners() {
        pizzas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * Keeps track of the selected pizza.
             * @param adapterView default parameter
             * @param view default parameter
             * @param i index of the selected pizza
             * @param l default parameter
             */
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selected = i;
            }
        });
        removeBtn.setOnClickListener(new View.OnClickListener() {
            /**
             * Removes selected pizza from the order.
             * Alerts the user if no pizza is selected.
             * @param view default parameter
             */
            @Override
            public void onClick(View view) {
                if ( selected != NOT_SELECTED
                        && pizzasAdapter.getItem(selected) != null ) {
                    order.remove(pizzasAdapter.getItem(selected));
                    selected = NOT_SELECTED;
                    refresh();
                    makePopUp("Pizza Removed", "Individual Pizza Removed From Order.");
                } else {
                    showToast("No Selection");
                }
            }
        });
        placeBtn.setOnClickListener(new View.OnClickListener() {
            /**
             * Places the current order.
             * Resets the order displayed.
             * Alerts the user if the order is empty.
             * @param view default parameter
             */
            @Override
            public void onClick(View view) {
                if ( storeOrder.add(order) ) {
                    order = storeOrder.getCurrOrder();
                    refresh();
                    makePopUp("Order Placed", "Order placed successfully!");
                } else {
                    showToast("The order is empty.");
                }
            }
        });
        clearBtn.setOnClickListener(new View.OnClickListener() {
            /**
             * Clears the current order.
             * Removes all pizzas from the order and refreshes.
             * @param view default parameter
             */
            @Override
            public void onClick(View view) {
                order.clearCart();
                refresh();
                makePopUp("Cart Cleared", "The entire order has been 100% completely cleared!");
            }
        });
    }


    /**
     * Updates values when selections change.
     * Updates the pizzas in the order
     * Updates the subtotal, salesTax, and total.
     */
    private void refresh () {
        orderNum.setText(Integer.toString(order.getSerial()));
        pizzasAdapter = new ArrayAdapter<>(this, R.layout.list_view_item, R.id.listViewItem, order.getPizzas());
        pizzas.setAdapter(pizzasAdapter);
        subtotal.setText(String.format("%.2f", order.subtotal()));
        tax.setText(String.format("%.2f", order.tax()));
        total.setText(String.format("%.2f", order.total()));
    }

    /**
     * Returns the storeOrder so the parent can get this information.
     * @return the storeOrder.
     */
    @Override
    protected StoreOrders getStoreOrder() {
        return storeOrder;
    }
}