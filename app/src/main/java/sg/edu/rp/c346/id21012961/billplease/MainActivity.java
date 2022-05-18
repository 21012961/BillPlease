package sg.edu.rp.c346.id21012961.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText edtAmount;
    EditText edtPax;
    TextView totalBill;
    TextView eachPay;
    ToggleButton tgSvs;
    ToggleButton tgGst;
    Button btnSplit;
    Button btnReset;
    EditText edtDisc;
    RadioGroup payment;
    RadioButton cash;
    RadioButton PayNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalBill = findViewById(R.id.TotalBill);
        edtAmount = findViewById(R.id.editTextAmount);
        eachPay = findViewById(R.id.EachPay);
        edtPax = findViewById(R.id.editNumPax);
        tgSvs = findViewById(R.id.ToggleSVS);
        tgGst = findViewById(R.id.ToggleGST);
        edtDisc = findViewById(R.id.editDiscount);
        btnSplit = findViewById(R.id.ButtonSplit);
        btnReset = findViewById(R.id.ButtonReset);
        cash = findViewById(R.id.radioButtonCash);
        PayNow = findViewById(R.id.radioButtonPayNow);
        payment = findViewById(R.id.radioGroupMethod);

        btnSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSplit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        double entAmt = Double.parseDouble(edtAmount.getText().toString());
                        double numPax = Double.parseDouble(edtPax.getText().toString());
                        double entDis = Double.parseDouble(edtDisc.getText().toString());
                        double GST = 1.0;
                        if (tgSvs.isChecked()) {
                            GST *= 1.1;
                        } else if (tgGst.isChecked()) {
                            GST *= 1.07;
                        } else if (tgSvs.isChecked() && tgGst.isChecked()) {
                            GST *= 2.17;
                        }

                        double discount = 1 - (entDis / 100);
                        double ttlAmount = (entAmt * GST) * discount;

                        double splitPay = ttlAmount / numPax;
                        totalBill.setText(String.format("Total Bill: $%.2f", ttlAmount));

                        int check_rad = payment.getCheckedRadioButtonId();
                        if (check_rad == R.id.radioButtonCash) {
                            eachPay.setText("Each pays: $" + splitPay + " in cash");
                        } else if (check_rad == R.id.radioButtonPayNow) {
                            eachPay.setText("Each pays: $" + splitPay + " via Paynow to 9068 6335");
                        }
                    }
            });

                btnReset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        edtAmount.getText().clear();
                        edtPax.getText().clear();
                        edtDisc.getText().clear();

                        if ( tgSvs.isChecked() ) {
                            tgSvs.setChecked(false);
                        }
                        if ( tgGst.isChecked() ) {
                            tgGst.setChecked(false);
                        }

                        totalBill.setText("");
                        eachPay.setText("");

                        if ( cash.isChecked() ) {
                            cash.setChecked(false);
                        }
                        if ( PayNow.isChecked() ) {
                            PayNow.setChecked(false);
                        }

                    }

                });
            }


        });



    }
}