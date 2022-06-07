package com.example.soundwave;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM User")
    List<User> getAll();

    @Insert
    void addUser(User user);

    @Query("SELECT * from User WHERE username=(:username) and password=(:password)")
    User login(String username,String password);

    @Update
    void updateUser(User user);
}
