package com.example.ricardo_i028113.appemas.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ricardo_i028113.appemas.Models.Car;
import com.example.ricardo_i028113.appemas.R;
import com.example.ricardo_i028113.appemas.Views.Fragments.SearchFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kenet on 27/05/2017.
 */

public class CarAdapter extends BaseAdapter {

    List<Car> userList = new ArrayList<>();
    LayoutInflater layoutInflater;
    Context context;

    public CarAdapter(Context context, List<Car> userList) {
        this.context = context;
        this.userList = userList;
        layoutInflater = LayoutInflater.from(this.context);
    }

    public int getCount() {
        return userList.size();
    }

    @Override
    public Car getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.item_bus, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

            viewHolder.checkFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v;
                    Car car = (Car) cb.getTag();
                    Toast.makeText(context, context.getString(R.string.txt_route_search)
                            + car.getRoute()+ context.getString(R.string.txt_neighborhood_routes_search) + car.getNeighborhood() +
                                    (cb.isChecked()? context.getString(R.string.txt_check_true) : context.getString(R.string.txt_check_false) ),
                            Toast.LENGTH_SHORT).show();
                    if (cb.isChecked()){
                        cb.setEnabled(false);
                        SearchFragment.carFavorite = car;
                        SearchFragment.createDataFavorite();
                    }
                    car.setCheck(cb.isChecked());
                }
            });

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        Car car = getItem(position);
        viewHolder.route.setText(car.getRoute());
        viewHolder.neighborhood.setText(car.getNeighborhood());
        viewHolder.checkFavorite.setText(car.getRoute());
        viewHolder.checkFavorite.setChecked(car.isCheck());
        if (viewHolder.checkFavorite.isChecked()==true) viewHolder.checkFavorite.setEnabled(false);
        viewHolder.checkFavorite.setTag(car);

        return convertView;
    }

    public class ViewHolder{
        TextView route;
        TextView neighborhood;
        CheckBox checkFavorite;

        public ViewHolder(View item) {
            route = (TextView) item.findViewById(R.id.id_item_bus_route);
            neighborhood = (TextView) item.findViewById(R.id.id_item_bus_neighborhood);
            checkFavorite = (CheckBox) item.findViewById(R.id.id_item_chb_favorite);
        }
    }

}