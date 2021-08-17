package hr.tvz.android.fitapp;

import java.io.Serializable;

public class Exercise implements Serializable {

    private int id;
    private String name;

    private String imageURL;
    private String description;

    public Exercise(int id, String name, String image, String description) {
        this.id = id;
        this.name = name;
        this.imageURL = image;
        this.description = description;
    }


    public int getId() { return id; }

    public String getName() {
        return name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getDescription() { return description; }



    @Override
    public String toString() {
        return "ExerciseModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + imageURL + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
