package db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface RecDao {

    @Query("SELECT * FROM rec")
    List<Rec> getAllReci();

    @Query("SELECT COUNT(*) FROM rec")
    int brReci();

    @Query("SELECT * FROM rec WHERE id = :uid")
    Rec findById(int uid);

    @Insert
    void insertAll(Rec... rec);

    @Delete
    void delete(Rec rec);
}
