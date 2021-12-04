package com.yjn.githubapi.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.yjn.githubapi.model.User;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Delete
    void  deleteStudent(User user);

    @Query("DELETE FROM user")
    void deleteAll();

    @Query("SELECT * FROM user limit 1")
    LiveData<User> getUserByLogin();
}
