package bumblebee.nandan;

/**
 * Created by anuragsharma on 07/10/17.
 */

public class MyVaccination {

    private String name, date, location;

    public MyVaccination(String name, String date, String loc){
        this.name = name;
        this.date = date;
        this.location = loc;

    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

}
