package com.example.andresteran_i014213.projectofinal_sti.Views.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.andresteran_i014213.projectofinal_sti.Adapters.CarAdapter;
import com.example.andresteran_i014213.projectofinal_sti.Data.DataUser;
import com.example.andresteran_i014213.projectofinal_sti.LoginActivity;
import com.example.andresteran_i014213.projectofinal_sti.Models.Car;
import com.example.andresteran_i014213.projectofinal_sti.Models.Favorites;
import com.example.andresteran_i014213.projectofinal_sti.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    View view;
    List<Car> busesList;
    List<Car> busesFavorites;
    public static DataUser dataCar;
    ListView listView;
    CarAdapter carAdapter;
    Button btnFindBus;
    EditText findBus;
    String find ="";
    CheckBox favoritesBus;
    public static Car carFavorite;


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_search, container, false);
        showTolbar(getResources().getString(R.string.txt_title_toolbar_search),true);
        setHasOptionsMenu(true);
        findBus = (EditText) view.findViewById(R.id.id_fragment_search_bus);
        listView = (ListView) view.findViewById(R.id.id_fragment_list_buses);
        btnFindBus = (Button) view.findViewById(R.id.id_btn_fragment_search_bus);
        favoritesBus = (CheckBox) view.findViewById(R.id.id_item_chb_favorite);

        dataCar = new DataUser(getActivity());
        dataCar.open();

        busesList = dataCar.findAllBuses();

        if (busesList.size()<=0) {
            createData();
        }

        btnFindBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                find= findBus.getText().toString();
                busesList = dataCar.findBuses(find.toUpperCase());
                busesFavorites = dataCar.listFavorites(LoginActivity.userLogin.getId());

                if (busesFavorites.size()<=0) {

                    if (busesList.size() <= 0) {
                        Toast.makeText(getActivity().getApplicationContext(), getString(R.string.txt_search_bus) + findBus.getText().toString(), Toast.LENGTH_SHORT).show();
                    } else {
                        carAdapter = new CarAdapter(getActivity().getApplicationContext(), busesList);
                        listView.setAdapter(carAdapter);
                    }

                }else
                {
                    if (busesList.size() <= 0) {
                        Toast.makeText(getActivity().getApplicationContext(), getString(R.string.txt_search_bus) + findBus.getText().toString(), Toast.LENGTH_SHORT).show();
                    } else {

                        for (int i=0; i< busesList.size();i++){

                            for (int y=0; y< busesFavorites.size();y++){

                                if(busesList.get(i).getId()==busesFavorites.get(y).getId()){
                                    busesList.get(i).setCheck(true);
                                }

                            }

                        }

                        carAdapter = new CarAdapter(getActivity().getApplicationContext(), busesList);
                        listView.setAdapter(carAdapter);

                    }

                }


            }
        });


        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_cargar_datos:
                //onClickButton();
                return (true);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showTolbar(String title, boolean upButton) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    private void createData(){
        Car car = new Car();

        car.setRoute("R1");
        car.setNeighborhood("Lorenzo 7AM -- 8AM \n" +
                "Lorenzo 3PM -- 4AM \n" +
                "Lorenzo 7PM -- 8PM ");
        dataCar.createBus(car);

        car.setRoute("R1");
        car.setNeighborhood("Chapal 7AM -- 8AM \n" +
                "Chapal 3PM -- 4AM \n" +
                "Chapal 7PM -- 8PM ");
        dataCar.createBus(car);

        car.setRoute("R1");
        car.setNeighborhood("Santa Mónica 7AM -- 8AM \n" +
                "Santa Mónica 3PM -- 4AM \n" +
                "Santa Mónica 7PM -- 8PM ");
        dataCar.createBus(car);

        car.setRoute("R2");
        car.setNeighborhood("Briceño 7AM -- 8AM \n" +
                "Briceño 3PM -- 4AM \n" +
                "Briceño 7PM -- 8PM ");
        dataCar.createBus(car);

        car.setRoute("R2");
        car.setNeighborhood("El Popular 7AM -- 8AM \n" +
                "El Popular 3PM -- 4AM \n" +
                "El Popular 7PM -- 8PM ");
        dataCar.createBus(car);

        car.setRoute("R2");
        car.setNeighborhood("Las Américas 7AM -- 8AM \n" +
                "Las Américas 3PM -- 4AM \n" +
                "Las Américas 7PM -- 8PM ");
        dataCar.createBus(car);

        car.setRoute("R3");
        car.setNeighborhood("El Praga 7AM -- 8AM \n" +
                "El Praga 3PM -- 4AM \n" +
                "El Praga 7PM -- 8PM ");
        dataCar.createBus(car);

        car.setRoute("R4");
        car.setNeighborhood("Santiago 7AM -- 8AM \n" +
                "Santiago 3PM -- 4AM \n" +
                "Santiago 7PM -- 8PM ");
        dataCar.createBus(car);




    }

     public static void createDataFavorite(){

        Favorites favorites = new Favorites();
        favorites.setIdUser(LoginActivity.userLogin.getId());
        favorites.setIdBus(SearchFragment.carFavorite.getId());
         dataCar.createFavorites(favorites);
    }

}
