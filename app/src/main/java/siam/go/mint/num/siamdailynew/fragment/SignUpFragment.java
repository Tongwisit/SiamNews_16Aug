package siam.go.mint.num.siamdailynew.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

import siam.go.mint.num.siamdailynew.R;
import siam.go.mint.num.siamdailynew.manage.MyAlert;

/**
 * Created by Tong on 15/8/2560.
 */

public class SignUpFragment extends Fragment {

    //Explicit
    private  String nameString, surnameString, emailString, userString, passwordString,
                    genderString, divitionString,repasswordString;
    private boolean aBoolean = true;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        return view;
    } // onCreate View

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Back Controllor
        backControllor();


        //Save conrtollor
        saveConrtollor();

        //Gender Contoller
        genderContoller();
    }

    private void genderContoller() {
        RadioGroup radioGroup = getView().findViewById(R.id.ragGender);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                aBoolean = false;
                switch (i) {
                    case R.id.radMale:
                        genderString = "Male";
                        break;
                    case R.id.radFemale:
                        genderString = "Female";
                        break;
                }
            }
        });
    }


    private void saveConrtollor() {
        ImageView imageView = getView().findViewById(R.id.imvSave);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Initial EditText
                EditText nameEditText = getView().findViewById(R.id.edtName);
                EditText surnameEditText = getView().findViewById(R.id.edtSurname);
                EditText emailEditText = getView().findViewById(R.id.edtEmail);
                EditText userEditText = getView().findViewById(R.id.edtUser);
                EditText passwordEditText = getView().findViewById(R.id.edtPassword);
                EditText repasswordEditText = getView().findViewById(R.id.edtRePassword);

                //Get value to String
                nameString = nameEditText.getText().toString().trim();
                surnameString = surnameEditText.getText().toString().trim();
                emailString = emailEditText.getText().toString().trim();
                userString = userEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();
                repasswordString = repasswordEditText.getText().toString().trim();

                //Check Space
                if (checkSpace()) {
                    //Have Space
                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.myDialog(getResources().getString(R.string.have_space),
                            getResources().getString(R.string.message_have_space));
                } else if (!passwordString.equals(repasswordString)) {
                    //Not Math Password
                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.myDialog(getString(R.string.title_not_Math),
                            getString(R.string.message_not_Math));
                } else if (aBoolean) {
                    //Non Choose Gender
                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.myDialog(getString(R.string.title_non_choose),
                            getString(R.string.message_non_choose));
                }


            }// onClick
        });
    }

    private boolean checkSpace() {
        return nameString.equals("")||
                surnameString.equals("")||
                emailString.equals("")||
                userString.equals("")||
                passwordString.equals("")||
                repasswordString.equals("");
    }

    private void backControllor() {
        ImageView imageView = getView().findViewById(R.id.imvBack);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .popBackStack();
            }
        });
    }
}   //Main Class
