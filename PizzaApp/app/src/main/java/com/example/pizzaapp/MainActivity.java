package com.example.pizzaapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Activity for the Main View.
 * Allows for opening of different Menus and stores information centrally.
 * @author Rishabh Patel, Albert Zou.
 */
public class MainActivity extends AppCompatActivity {
    private StoreOrders storeOrder;
    private Button chicagoBtn;
    private Button NYBtn;
    private Button currentBtn;
    private Button storeBtn;

    private static final int CODE = 1;

    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                /**
                 * Stores the incoming storeOrder from other activities.
                 * @param result default parameter
                 */
                @Override
                public void onActivityResult(ActivityResult result) {
                    if ( result.getResultCode() == CODE ) {
                        storeOrder = (StoreOrders) result.getData().getSerializableExtra("STORE");
                    }
                }
            }
    );


    /**
     * Initializes GUI elements and populates fields.
     * Initializes storeOrders object
     * @param savedInstanceState default parameter
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storeOrder = new StoreOrders();

        chicagoBtn = findViewById(R.id.chicagoBtn);
        chicagoBtn.setOnClickListener(new View.OnClickListener() {
            /**
             * Opens the ChicagoOrderingActivity.
             * Calls openChicago().
             * @param view default parameter
             */
            @Override
            public void onClick(View view) {
                openChicago();
            }
        });

        NYBtn = findViewById(R.id.NYBtn);
        NYBtn.setOnClickListener(new View.OnClickListener() {
            /**
             * Opens the NYOrderingActivity.
             * Calls openNY().
             * @param view default parameter
             */
            @Override
            public void onClick(View view) {
                openNY();
            }
        });

        currentBtn = findViewById(R.id.currentBtn);
        currentBtn.setOnClickListener(new View.OnClickListener() {
            /**
             * Opens the CurrentOrderActivity.
             * Calls openCurrent().
             * @param view default parameter
             */
            @Override
            public void onClick(View view) {
                openCurrent();
            }
        });

        storeBtn = findViewById(R.id.storeBtn);
        storeBtn.setOnClickListener(new View.OnClickListener() {
            /**
             * Opens the StoreOrderActivity.
             * Calls openStore().
             * @param view default parameter
             */
            @Override
            public void onClick(View view) {
                openStore();
            }
        });
    }

    /**
     * Opens Chicago activity.
     * passes storeOrder.
     */
    public void openChicago() {
        Intent intent = new Intent(this, ChicagoPizzaActivity.class);
        intent.putExtra("STORE", storeOrder);
        activityResultLauncher.launch(intent);
    }

    /**
     * Opens NY activity.
     * passes storeOrder.
     */
    public void openNY() {
        Intent intent = new Intent(this, NYPizzaActivity.class);
        intent.putExtra("STORE", storeOrder);
        activityResultLauncher.launch(intent);
    }

    /**
     * Opens Current activity.
     * passes storeOrder.
     */
    public void openCurrent() {
        Intent intent = new Intent(this, CurrentOrderActivity.class);
        intent.putExtra("STORE", storeOrder);
        activityResultLauncher.launch(intent);
    }

    /**
     * Opens store activity.
     * passes storeOrder.
     */
    public void openStore() {
        Intent intent = new Intent(this, StoreOrdersActivity.class);
        intent.putExtra("STORE", storeOrder);
        activityResultLauncher.launch(intent);
    }
}