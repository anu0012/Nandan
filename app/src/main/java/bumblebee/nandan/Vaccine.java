package bumblebee.nandan;

/**
 * Created by anuragsharma on 20/02/18.
 */

public class Vaccine {

    String mName, mDate, mLocation,mEmail;

    public Vaccine(String name, String date, String location, String email){
        this.mName = name;
        this.mDate = date;
        this.mLocation = location;
        this.mEmail = email;
    }

    public String getmEmail() {
        return mEmail;
    }

        public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmLocation() {
        return mLocation;
    }

    public void setmLocation(String mLocation) {
        this.mLocation = mLocation;
    }
}
