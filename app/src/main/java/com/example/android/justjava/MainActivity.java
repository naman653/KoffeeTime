/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */

    int numberOfCoffees=2;

    public void submitOrder(View view) {

        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.whippedCream);
        Boolean orderWhippedCream = whippedCreamCheckbox.isChecked();

        CheckBox chocolateCheckbox = (CheckBox) findViewById(R.id.chocolate);
        Boolean orderChocolate = chocolateCheckbox.isChecked();

        EditText nameEditText = (EditText) findViewById(R.id.customerName);
        Editable name = nameEditText.getText();

        int price = calculatePrice(orderWhippedCream, orderChocolate);

        String priceMessage = createOrderSummary(price, orderWhippedCream, orderChocolate, name);

        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_textview);
        orderSummaryTextView.setText(priceMessage);
    }

    private String createOrderSummary(int priceOfOrder, boolean orderWhippedCream, boolean orderChocolate, Editable name){
        String message = "Name: " + name;
        message += "\nAdd Whipped Cream --> " + orderWhippedCream;
        message += "\nAdd Chocolate --> " + orderChocolate;
        message += "\nQuantity: " + numberOfCoffees;
        message += "\nTotal: $" + priceOfOrder;
        message += "\nThank you!";
        return message;
    }

    public void increment(View view){
        if(numberOfCoffees > 99){
            Toast.makeText(this, "You cannot order more than 100 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        numberOfCoffees++;
        display(numberOfCoffees);
    }

    public void decrement(View view){
        if(numberOfCoffees < 2){
            Toast.makeText(this, "You must order minimum of 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        numberOfCoffees--;
        display(numberOfCoffees);
    }

    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate){
        int pricePerUnit = 5;
        if(hasChocolate){
            pricePerUnit += 2;
        }
        if(hasWhippedCream){
            pricePerUnit += 1;
        }
        return numberOfCoffees * pricePerUnit;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
}