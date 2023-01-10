package com.example.pizzaapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


/**
 * An abstract class to allow for
 * shared functionality across the activities.
 * Allows for Toast and AlertDialogs
 * @author Rishabh Patel, Albert Zou
 */
public abstract class SecondaryViewActivity extends AppCompatActivity {
    protected static final int CODE = 1;

    /**
     * Displays a toast to the user containing a message.
     * @param msg to display to the user.
     */
    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * Creates a popup AlertDialog
     * Indicates a successfully operation.
     * Pops out of the current activity.
     * Passes storeOrder back to the MainActivity.
     * @param title of the dialog
     * @param msg of the dialog
     */
    protected void makePopUp(String title, String msg) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage(msg).setTitle(title);
        alert.setCancelable(true);
        alert.setPositiveButton(
                getString(R.string.ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent();
                        intent.putExtra(getString(R.string.store), getStoreOrder());
                        setResult(CODE, intent);
                        finish();
                        dialog.cancel();
                    }
                });
        alert.create().show();
    }

    /**
     * Gets children classes' storeOrder properties
     * @return storeOrder property to MainActivity
     */
    protected abstract StoreOrders getStoreOrder();
}
