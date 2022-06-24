package db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Rec  {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "srpski")
    public String srpski;

    @ColumnInfo(name = "nemacki")
    public String nemacki;



}
