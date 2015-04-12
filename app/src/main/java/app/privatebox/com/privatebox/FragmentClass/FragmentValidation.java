package app.privatebox.com.privatebox.FragmentClass;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.andreabaccega.widget.FormEditText;

import java.text.Normalizer;

import app.privatebox.com.privatebox.R;

/**
 * Created by Kaede on 11/04/2015.
 */
public class FragmentValidation extends Fragment {

    private FormEditText nameForm;
    private FormEditText phoneForm;
    private FormEditText emailForm;
    private Button btn_valid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.validation_layout, container, false);

        nameForm = (FormEditText)rootView.findViewById(R.id.fullName);
        phoneForm = (FormEditText)rootView.findViewById(R.id.phoneNumber);
        emailForm = (FormEditText)rootView.findViewById(R.id.email);
        btn_valid = (Button)rootView.findViewById(R.id.validate);

        btn_valid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickNext(v);
            }
        });

        return  rootView;
    }

    public void onClickNext(View v) {
        FormEditText[] allFields    = { nameForm, phoneForm, emailForm};

        boolean allValid = true;

        for (FormEditText field: allFields) {
            allValid = field.testValidity() && allValid;
        }

        if (allValid) {
            Toast.makeText(getActivity(), "All Valid", Toast.LENGTH_SHORT).show();
        } else {
            // EditText are going to appear with an exclamation mark and an explicative message.
        }
    }
}
