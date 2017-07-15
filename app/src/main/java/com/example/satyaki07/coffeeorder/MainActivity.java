package com.example.satyaki07.coffeeorder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

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
        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();
        CheckBox chocolateToppingCheckbox = (CheckBox) findViewById(R.id.chocolate_topping_checkbox);
        boolean hasChocolate = chocolateToppingCheckbox.isChecked();
        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckbox.isChecked();
        int price = calculatePrice(hasChocolate,hasWhippedCream);
         String priceMessage = createOrderSummary(name,price,hasChocolate,hasWhippedCream);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order for "+name);
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
//        displayMessage(priceMessage);

    }

    /**
     * Calculates the price of the order.*/

    private int calculatePrice(boolean hasChocolate,boolean hasWhippedCream) {
        int basePrice = 5;
        if(hasChocolate)
            basePrice += 2;
        if(hasWhippedCream)
            basePrice += 1;

        return quantity*basePrice;
    }
     /**@param price of the order
     * @param addWhippedCream is whether not the user want Whipped cream in the coffee
     */

    private String createOrderSummary(String name,int price,boolean addChocolateTopping,boolean addWhippedCream)
    {
        String priceMessage = getString(R.string.order_summary_name, name);
        priceMessage = priceMessage+"\nQuantity: "+quantity;
        priceMessage += "\nAdd Chocolate Topping? "+addChocolateTopping;
        priceMessage = priceMessage+"\nAdd Whipped Cream? "+addWhippedCream;
        priceMessage = priceMessage+"\nTotal: $"+price;
        priceMessage = priceMessage+"\n" + getString(R.string.thank_you);
        return priceMessage;
    }



    /**
     * this method will work when the plus button is clicked
     */
    public void increment(View view){
        if(quantity!=101){
        quantity = quantity+1;
        }
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
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message);
//    }
}
