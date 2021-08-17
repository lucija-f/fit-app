package hr.tvz.android.fitapp;

import java.io.Serializable;

public class Checkpoint implements Serializable {

    int id;
    String date;
    String result;
    String BMIdescription;

    public Checkpoint(int id, String date, String result, String BMIdescription) {
        this.id = id;
        this.date = date;
        this.result = result;
        this.BMIdescription = BMIdescription;
    }

    public String getDate() {
        return date;
    }

    public String getResult() {
        return result;
    }

    public String getBMIdescription() {
        return BMIdescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setBMIdescription(String BMIdescription) {
        this.BMIdescription = BMIdescription;
    }
}
