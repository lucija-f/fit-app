package hr.tvz.android.fitapp;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = FitappDatabase.class) // database name
public class ExerciseModel extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    int id;
    @Column
    String name;
    @Column
    String image;
    @Column
    String description;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }
}
