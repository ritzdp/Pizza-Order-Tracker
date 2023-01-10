package com.example.pizzaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Activity for the StoreOrders View.
 * Allows for viewing, cancelling, and exporting
 * orders in the store.
 * @author Rishabh Patel, Albert Zou
 */
public class StoreOrdersActivity extends SecondaryViewActivity implements AdapterView.OnItemSelectedListener {
    private StoreOrders storeOrder;
    private Spinner orderSpinner;
    private ListView pizzas;
    private TextView totalWithTax;
    private Button cancelBtn;

    private static final int EMPTY = 0;
    private static final int FIRST = 0;

    /**
     * Initializes UI elements and populates fields.
     * Receives and stores storeOrder from Main Activity.
     * @param savedInstanceState default parameter
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);

        storeOrder = (StoreOrders) getIntent().getSerializableExtra(getString(R.string.store));
        orderSpinner = (Spinner) findViewById(R.id.spinner);
        pizzas = (ListView) findViewById(R.id.pizzas);
        totalWithTax = (TextView) findViewById(R.id.totalWithTax);
        cancelBtn = (Button) findViewById(R.id.cancelBtn);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, storeOrder.getStringNums());
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderSpinner.setAdapter(arrayAdapter);
        orderSpinner.setSelection(FIRST);
        orderSpinner.setOnItemSelectedListener(this);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            /**
             * Cancels an order
             * Alerts the user.
             * @param view default parameter
             */
            @Override
            public void onClick(View view) {
                if ( orderSpinner.getSelectedItem() != null ) {
                    boolean removed = storeOrder.remove(Integer.parseInt(orderSpinner.getSelectedItem().toString()));
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                            StoreOrdersActivity.this, android.R.layout.simple_spinner_item, storeOrder.getStringNums());
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    orderSpinner.setAdapter(arrayAdapter);
                    makePopUp(getString(R.string.cancelled_title), getString(R.string.cancelled_msg));
                } else {
                    showToast(getString(R.string.no_selection));
                }
            }
        });

        refresh();
    }

    /**
     * Updates values when selections change.
     * Updates orders and total.
     */
    private void refresh() {
        if ( orderSpinner.getAdapter().getCount() > EMPTY ) {
            Order selected = storeOrder
                    .getOrder(Integer.parseInt(orderSpinner.getSelectedItem().toString()));
            ArrayAdapter<Pizza> arrayAdapter = new ArrayAdapter<>(
                    this, R.layout.list_view_item, R.id.listViewItem, selected.getPizzas());
            pizzas.setAdapter(arrayAdapter);
            totalWithTax.setText(String.format("%.2f", selected.total()));
        } else {
            totalWithTax.setText("0.00");
        }
    }

    /**
     * Keeps track of selected item.
     * @param adapterView default parameter
     * @param view default parameter
     * @param i index of selected item
     * @param l default parameter
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        refresh();
        orderSpinner.setSelection(i);
    }

    /**
     * Handles the case when no item is selected.
     * @param adapterView default parameter
     */
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}

    /**
     * Returns the storeOrder so the parent can get this information.
     * @return the storeOrder.
     */
    @Override
    protected StoreOrders getStoreOrder() {
        return storeOrder;
    }
}