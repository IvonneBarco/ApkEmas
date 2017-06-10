package com.example.ricardo_i028113.appemas.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ricardo_i028113.appemas.Helper.HelperUser;
import com.example.ricardo_i028113.appemas.Models.Car;
import com.example.ricardo_i028113.appemas.Models.Favorites;
import com.example.ricardo_i028113.appemas.Models.User;

import java.util.ArrayList;
import java.util.List;


public class DataUser {

    SQLiteOpenHelper dbHelper;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            HelperUser.COLUMN_ID,
            HelperUser.COLUMN_NAME,
            HelperUser.COLUMN_EMAIL,
            HelperUser.COLUMN_USERNAME,
            HelperUser.COLUMN_PASSWORD
    };

    public DataUser(Context context){
        dbHelper = new HelperUser(context);
    }

    public void open(){
        database = dbHelper.getWritableDatabase();

    }

    public void close(){
        dbHelper.close();
    }

    public User create(User user){
        ContentValues values = new ContentValues();
        values.put(HelperUser.COLUMN_NAME, user.getName());
        values.put(HelperUser.COLUMN_EMAIL, user.getEmail());
        values.put(HelperUser.COLUMN_USERNAME, user.getUsername());
        values.put(HelperUser.COLUMN_PASSWORD, user.getPassword());
        values.put(HelperUser.COLUMN_STATUS, user.getStatus());

        long insertId = database.insert(HelperUser.TABLE_USERS, null, values);

        user.setId(insertId);
        return user;
    }

    public List<User> cursorToList(Cursor cursor){
        List<User> users = new ArrayList<>();
        if (cursor.getCount() > 0){
            while (cursor.moveToNext()){
                User user = new User();
                user.setId(cursor.getLong(cursor.getColumnIndex(HelperUser.COLUMN_ID)));
                user.setName(cursor.getString(cursor.getColumnIndex(HelperUser.COLUMN_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(HelperUser.COLUMN_EMAIL)));
                user.setUsername(cursor.getString(cursor.getColumnIndex(HelperUser.COLUMN_USERNAME)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(HelperUser.COLUMN_PASSWORD)));
                user.setStatus(cursor.getString(cursor.getColumnIndex(HelperUser.COLUMN_STATUS)));

                users.add(user);
            }
        }
        return users;
    }

    public List<User> findAll(){
        Cursor cursor = database.rawQuery("select * from users", null);
        List<User> users = cursorToList(cursor);
        return users;
    }

    public String[] findUser (String username, String password){

        String[] findUser = new String[2];
        Cursor cursor = database.rawQuery("select username,password from users where username = '"+username+"' and " +
                "password = '"+password+"'", null);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                findUser[0] = cursor.getString(0);
                findUser[1] = cursor.getString(1);
            } while (cursor.moveToNext());
        }else{
            findUser[0] =" ";
            findUser[1] = " ";
        }
        return findUser;

    }

    public void statusOn (String username, String password){
        database.execSQL("update users set status = 'true' where username = '"+username+"' and " +
                "password = '"+password+"'");
    }

    public void statusOff (String username, String password){
        database.execSQL("update users set status = 'false' where username = '"+username+"' and " +
                "password = '"+password+"'");
    }

    public User checkStatusLogin (){

        User userLogin = new User();

        Cursor cursor = database.rawQuery("select * from users where status = 'true'", null);
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                userLogin.setId(cursor.getLong(cursor.getColumnIndex(HelperUser.COLUMN_ID)));
                userLogin.setName(cursor.getString(cursor.getColumnIndex(HelperUser.COLUMN_NAME)));
                userLogin.setEmail(cursor.getString(cursor.getColumnIndex(HelperUser.COLUMN_EMAIL)));
                userLogin.setUsername(cursor.getString(cursor.getColumnIndex(HelperUser.COLUMN_USERNAME)));
                userLogin.setPassword(cursor.getString(cursor.getColumnIndex(HelperUser.COLUMN_PASSWORD)));
                userLogin.setStatus(cursor.getString(cursor.getColumnIndex(HelperUser.COLUMN_STATUS)));
            } while (cursor.moveToNext());
        } else {
            userLogin = null;
        }
        return userLogin;
    }

///  buses
    public Car createBus(Car car){
        ContentValues values = new ContentValues();
        values.put(HelperUser.COLUMN_ROUTE, car.getRoute());
        values.put(HelperUser.COLUMN_NEIGHBORHOD, car.getNeighborhood());

        long insertId = database.insert(HelperUser.TABLE_BUSES, null, values);

        car.setId(insertId);
        return car;
    }

    public List<Car> cursorToListBus(Cursor cursor){
        List<Car> buses = new ArrayList<>();
        if (cursor.getCount() > 0){
            while (cursor.moveToNext()){
                Car car = new Car();
                car.setId(cursor.getLong(cursor.getColumnIndex(HelperUser.COLUMN_ID)));
                car.setRoute(cursor.getString(cursor.getColumnIndex(HelperUser.COLUMN_ROUTE)));
                car.setNeighborhood(cursor.getString(cursor.getColumnIndex(HelperUser.COLUMN_NEIGHBORHOD)));

                buses.add(car);
            }
        }
        return buses;
    }

    public List<Car> findAllBuses(){
        Cursor cursor = database.rawQuery("select * from buses", null);
        List<Car> buses = cursorToListBus(cursor);
        return buses;
    }

    public List<Car> findBuses(String findBus){
        Cursor cursor = database.rawQuery("select * from buses where route ='"+findBus+"' or neighborhood = '"+findBus+"'", null);
        List<Car> buses = cursorToListBus(cursor);
        return buses;
    }


    // favoritos

    public Favorites createFavorites(Favorites favorites){

        ContentValues values = new ContentValues();
        values.put(HelperUser.COLUMN_ID_USER,favorites.getIdUser());
        values.put(HelperUser.COLUMN_ID_BUS,favorites.getIdBus());

        long insertId = database.insert(HelperUser.TABLE_FAVORITES_BUSES_USERS, null, values);

        favorites.setId(insertId);
        return favorites;
    }

    public List<Favorites> cursorToListFavorites(Cursor cursor){
        List<Favorites> favorites = new ArrayList<>();
        if (cursor.getCount() > 0){
            while (cursor.moveToNext()){
                Favorites favorite = new Favorites();
                favorite.setId(cursor.getLong(cursor.getColumnIndex(HelperUser.COLUMN_ID)));
                favorite.setIdUser(cursor.getLong(cursor.getColumnIndex(HelperUser.COLUMN_ID_USER)));
                favorite.setIdBus(cursor.getLong(cursor.getColumnIndex(HelperUser.COLUMN_ID_BUS)));
                favorites.add(favorite);
            }
        }
        return favorites;
    }

    public List<Favorites> findAllFavorites(){
        Cursor cursor = database.rawQuery("select * from favoritesBusesUsers", null);
        List<Favorites> favorites = cursorToListFavorites(cursor);
        return favorites;
    }

    public List<Car> listFavorites(long idUser){
        Cursor cursor = database.rawQuery("select t1.id,t1.route,t1.neighborhood from buses as t1 join favoritesBusesUsers as t2 on t1.id = t2.idBus where t2.idUser = "+idUser, null);
        List<Car> favorites = cursorToListBus(cursor);
        return favorites;
    }

    public List<Car> listRouesBus(String route){
        Cursor cursor = database.rawQuery("select * from buses where  route= '"+route+"'", null);
        List<Car> favorites = cursorToListBus(cursor);
        return favorites;
    }

    public void deleteFavorites (Long idUser , Long idBus){
        database.execSQL("delete from favoritesBusesUsers where  idUser = "+idUser+" and " +
                " idBus = "+idBus+"");
    }

}