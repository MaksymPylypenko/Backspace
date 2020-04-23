package com.group8.backspace.presentation.book_flight;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.group8.backspace.R;
import com.group8.backspace.logic.CheckCard;
import com.group8.backspace.logic.CheckCoupon;

public class PurchaseTicket extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_purchase);


        //get the flight object selected via the flightNum passed by BookBrowseActivity
        int currFlightNum = getIntent().getIntExtra("FLIGHT_NUM", 0);

        TextView flightNum = (TextView) findViewById(R.id.flight_view);
        String flightTitle = "Flight #"+currFlightNum;
        flightNum.setText(flightTitle);

        //calculate total price
        TextView fuelPrice = (TextView) findViewById(R.id.ticketPrice);
        TextView extraPrice = (TextView) findViewById(R.id.classPrice);
        TextView totalPrice = (TextView) findViewById(R.id.totalPrice);

        int totalP = getIntent().getIntExtra("Total_Price", 0);
        int ticket = getIntent().getIntExtra("Ticket_Price", 0);
        int extra = getIntent().getIntExtra("Extra", 0);

        totalPrice.setText(Integer.toString(totalP));
        fuelPrice.setText(Integer.toString(ticket));
        extraPrice.setText(Integer.toString(extra));
        Button checkCode = (Button) findViewById(R.id.btn_code);
        checkCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText discountCode = (EditText) findViewById(R.id.code);
                TextView newP = (TextView) findViewById(R.id.newPrice);
                int price = totalP;
                String code = discountCode.getText().toString();
                CheckCoupon check = new CheckCoupon(code, price);
                newP.setText(check.getDiscount());
            }
        });


        Button purchaseBtn = (Button) findViewById(R.id.btn_purchase);
        purchaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText cardNum = (EditText) findViewById(R.id.cardNum);
                EditText expiryDate = (EditText) findViewById(R.id.expiry_date);
                EditText securityCode = (EditText) findViewById(R.id.security_code);

                String card = cardNum.getText().toString();
                String date = expiryDate.getText().toString();
                String code = securityCode.getText().toString();
                TextView condition = (TextView) findViewById(R.id.condition);
                CheckCard check = new CheckCard();
                condition.setText(check.checkFormat(card,date,code));
            }
        });
    }

}
