package hr.tvz.android.fitapp;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.text.DateFormat;

@Table(database = FitappDatabase.class) // database name
public class CheckpointModel extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    int id;
    @Column
    String date;
    @Column
    String result;
    @Column
    String BMIdescription;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getBMIdescription() {
        return BMIdescription;
    }

    public void setBMIdescription(String BMIdescription) {
        this.BMIdescription = BMIdescription;
    }

    public void insertData(DateFormat date, int result, String BMIdescription){

    }
}
