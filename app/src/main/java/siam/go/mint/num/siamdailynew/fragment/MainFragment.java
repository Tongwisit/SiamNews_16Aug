package siam.go.mint.num.siamdailynew.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import siam.go.mint.num.siamdailynew.R;
import siam.go.mint.num.siamdailynew.manage.MyAlert;

/**
 * Created by Tong on 15/8/2560.
 */

public class MainFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,container,false);

        return view;
    }   // Create View


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Register Controller
        registerController();

        //Login Controller
        loginController();

    } // OnActivityCreate

    private void loginController() {
        Button button = getView().findViewById(R.id.btnLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Inital View to Variable
                EditText userEditText = getView().findViewById(R.id.edtUser);
                EditText passwordEditText = getView().findViewById(R.id.edtPassword);

                //Change Type Value
                // Get value From Edit text การดึงค่าไปทำงานต่อ
                String userString = userEditText.getText().toString().trim();
                String passwordStrig = passwordEditText.getText().toString().trim();

                //การเช็ค Space
                if (userString.equals("") || passwordStrig.equals("")) {
                    // Have Space
                    Log.d("15Aug", "Have Space");

                    // Call MyAlert
                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.myDialog(getString(R.string.have_space), getString(R.string.message_have_space));

                } else {
                    // No Space
                    Log.d("15Aug", "No Space");

                    //Call MyAlert

                }


            }   //OnClick
        });
    }

    private void registerController() {
        TextView textView = getView().findViewById(R.id.txtNewRegister);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Replace Fragment
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContent, new SignUpFragment())
                        .addToBackStack(null)
                        .commit();

            }   //OnClick
        });
    }
}// Main Class
