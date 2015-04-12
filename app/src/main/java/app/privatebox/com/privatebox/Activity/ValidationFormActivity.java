package app.privatebox.com.privatebox.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.andreabaccega.widget.FormEditText;

import app.privatebox.com.privatebox.R;

/**
 * Created by Kaede on 11/04/2015.
 */
public class ValidationFormActivity extends ActionBarActivity {

    private FormEditText nameForm;
    private FormEditText phoneForm;
    private FormEditText emailForm;
    private Button btn_valid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.validation_layout);

        nameForm = (FormEditText)findViewById(R.id.fullName);
        phoneForm = (FormEditText)findViewById(R.id.phoneNumber);
        emailForm = (FormEditText)findViewById(R.id.email);
        btn_valid = (Button)findViewById(R.id.validate);

        btn_valid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickNext(v);
            }
        });
    }

    public void onClickNext(View v) {
        FormEditText[] allFields    = { nameForm, phoneForm, emailForm};

        boolean allValid = true;

        for (FormEditText field: allFields) {
            allValid = field.testValidity() && allValid;
        }

        if (allValid) {
            Toast.makeText(this, "All Valid", Toast.LENGTH_SHORT).show();
        } else {
            // EditText are going to appear with an exclamation mark and an explicative message.
        }
    }
}
