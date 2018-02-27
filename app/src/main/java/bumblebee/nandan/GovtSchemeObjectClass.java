package bumblebee.nandan;

/**
 * Created by anuragsharma on 07/10/17.
 */

public class GovtSchemeObjectClass {

    private String name, details, mkey;

    public GovtSchemeObjectClass(String n, String d, String key ){
        name = n;
        details = d;
        mkey = key;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public String getKey() {
        return mkey;
    }
}
