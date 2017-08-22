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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONObject;

import siam.go.mint.num.siamdailynew.R;
import siam.go.mint.num.siamdailynew.manage.GetAllData;
import siam.go.mint.num.siamdailynew.manage.MyAlert;
import siam.go.mint.num.siamdailynew.manage.MyConstant;

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

        // Divition Contorller
        divitionContorller();
    }

    private void divitionContorller() {
        Spinner spinner = getView().findViewById(R.id.spnDivition);
        MyConstant myConstant = new MyConstant();
        String tag = "22AugV1";
//        String[] strings = new String[]{}; การทำฟลิกค่าไปเลย
        //การ ดึง คลาส
        try {

            GetAllData getAllData = new GetAllData(getActivity());
            getAllData.execute(myConstant.getUrlfecdep());
            String strJSoN  = getAllData.get();
            Log.d(tag, "JSON ==>" + strJSoN);

            JSONArray jsonArray = new JSONArray(strJSoN);
            final String[] divitionStrings = new String[jsonArray.length()];
            for (int i = 0; i<jsonArray.length(); i+=1){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                divitionStrings[i] = jsonObject.getString("fd_nameth");
                Log.d(tag, "divition[" + i + "] ==>" + divitionStrings[i]);
            } // for

            ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(
                    getActivity(),
                    android.R.layout.simple_list_item_1,
                    divitionStrings
            );
            spinner.setAdapter(stringArrayAdapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    divitionString = divitionStrings[i];

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                    divitionString = divitionStrings[0];
                }
            });

        } catch (Exception e) {
            Log.d(tag, "e divition ==>" + e.toString());

        }


    }   // DivitionController

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
                } else {
                    uploadNewUserToServer();
                }


            }// onClick
        });
    }

    private void uploadNewUserToServer() {
        // Show Log
        String tag = "22AvgV2";
        Log.d(tag, nameString);
        Log.d(tag, surnameString);
        Log.d(tag, genderString);
        Log.d(tag, emailString);
        Log.d(tag, divitionString);
        Log.d(tag, userString);
        Log.d(tag, passwordString);

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
