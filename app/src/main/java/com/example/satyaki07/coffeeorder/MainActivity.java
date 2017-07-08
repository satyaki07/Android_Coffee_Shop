package com.example.satyaki07.coffeeorder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;
import java.text.StringCharacterIterator;

/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
 * package com.example.android.justjava;
 */

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();
         String priceMessage = createOrderSummary(price);
        displayMessage(priceMessage);

    }

    /**
     * Calculates the price of the order.
     *
     * @param quantity is the number of cups of coffee ordered
     */
    private int calculatePrice() {
        return quantity*5;
    }

    private String createOrderSummary(int price)
    {
        String priceMessage = "Name: Satyaki";
        priceMessage = priceMessage+"\nQuantity: "+quantity;
        priceMessage = priceMessage+"\nTotal: $"+price;
        priceMessage = priceMessage+"\nThank You.";
        return priceMessage;
    }



    /**
     * this method will work when the plus button is clicked
     */
    public void increment(View view){
        quantity = quantity+1;
        displayQuantity(quantity);

    }
    /**
     * this method will work whenever clicked on the minus button
     */
    public void decrement(View view){
        if(quantity!=0) {
            quantity = quantity - 1;
            displayQuantity(quantity);
        }
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCOffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCOffees);
    }
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}
