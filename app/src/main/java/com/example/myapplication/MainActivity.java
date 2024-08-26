package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.EditText;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private EditText primaryField;
    private Spinner fromCurrencySpinner, toCurrencySpinner;
    private Button convertButton;
    private TextView resultTextView;

    // Perform the conversion when the button is clicked
    private void performConversion() {
        String amountString = primaryField.getText().toString();
        if (amountString.isEmpty()) {
            resultTextView.setText(getString(R.string.enter_an_amount));
            return;
        }

        double amount = Double.parseDouble(amountString);
        String fromCurrency = fromCurrencySpinner.getSelectedItem().toString();
        String toCurrency = toCurrencySpinner.getSelectedItem().toString();

        double conversionRate = getConversionRate(fromCurrency, toCurrency);
        double result = amount * conversionRate;

        resultTextView.setText(String.format("%.2f %s", result, toCurrency));
    }

    // Get the conversion rate based on selected currencies
    private double getConversionRate(String fromCurrency, String toCurrency) {
        // Define some static conversion rates (for demonstration purposes)
        // In a real app, you would fetch these rates from an API

        if (fromCurrency.equals("USD") && toCurrency.equals("EUR")) {
            return 0.89; // Example: 1 USD = 0.85 EUR
        } else if (fromCurrency.equals("EUR") && toCurrency.equals("USD")) {
            return 1.12; // Example: 1 EUR = 1.18 USD
        } else if (fromCurrency.equals("GBP") && toCurrency.equals("USD")) {
            return 1.32; // Example: 1 GBP = 1.30 USD
        } else if (fromCurrency.equals("USD") && toCurrency.equals("INR")) {
            return 83.85; // Example: 1 USD = 74 INR
        } else if (fromCurrency.equals("USD") && toCurrency.equals("BDT")) {
            return 118.0; // Example: 1 USD = 84 BDT
        }

        // Add more conversion rates as needed
        // For unsupported conversions, return 1 (no conversion)
        return 1.0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar toolbar = findViewById(R.id.toolbar);

        // Set the Toolbar as the action bar
        setSupportActionBar(toolbar);

        // Set the title of the action bar
        getSupportActionBar().setTitle("Currency Converter");

        // Initialize the views
        primaryField = findViewById(R.id.primary_field);
        fromCurrencySpinner = findViewById(R.id.fromCurrencySpinner);
        toCurrencySpinner = findViewById(R.id.toCurrencySpinner);
        convertButton = findViewById(R.id.convert_button);
        resultTextView = findViewById(R.id.resultTextView);

        // Set up the ArrayAdapter for the spinners
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.CurrencyStringArray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fromCurrencySpinner.setAdapter(adapter);
        toCurrencySpinner.setAdapter(adapter);
        // Set click listener for the convert button
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performConversion();
            }
        });

    }
}