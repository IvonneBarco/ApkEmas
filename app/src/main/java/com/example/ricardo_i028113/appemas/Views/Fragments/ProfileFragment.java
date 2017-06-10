package com.example.ricardo_i028113.appemas.Views.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ricardo_i028113.appemas.Data.DataUser;
import com.example.ricardo_i028113.appemas.LoginActivity;
import com.example.ricardo_i028113.appemas.Models.User;
import com.example.ricardo_i028113.appemas.R;;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    View view;
    List<User> userList;
    DataUser dataUser;
    TextView name,email;
    User user;
    Button signOff;

    public ProfileFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_profile, container, false);
        showTolbar(getResources().getString(R.string.txt_title_toolbar_profile),true);
        setHasOptionsMenu(true);

        name = (TextView) view.findViewById(R.id.id_txt_profile_name);
        signOff = (Button) view.findViewById(R.id.id_btn_fragment_profile_Sign_off);
        email = (TextView) view.findViewById(R.id.id_txt_profile_email);

        dataUser = new DataUser(getActivity());
        dataUser.open();
        user = dataUser.checkStatusLogin();

        name.setText(user.getName());
        email.setText(user.getEmail());

        signOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity().getApplicationContext(), getString(R.string.txt_sign_off), Toast.LENGTH_SHORT).show();
                dataUser.statusOff(user.getUsername(),user.getPassword());
                goLogginActivity();
            }
        });

        return view ;
    }

    /*public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }*/

    private void showTolbar(String title, boolean upButton) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
    public void goLogginActivity(){
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }


}
