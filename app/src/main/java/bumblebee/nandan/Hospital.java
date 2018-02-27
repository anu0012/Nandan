package bumblebee.nandan;

import java.io.Serializable;


public class Hospital implements Serializable{
    private String name, category, caretype, systemsOfMedicine, address, state, district,
            subdistrict, pincode, telephone, mobilenumber,
            emergencynum, ambulancenum, tollfree, helpline, hospitalfax, primaryemail, secondaryemail, website,
            specialities, latitide, longitude , facilities, totalnumofbads;

    public Hospital(String s[]){
        name = s[1];
        category = s[2];
        caretype = s[3];
        systemsOfMedicine = s[4];
        address = s[5];
        state = s[6];
        district = s[7];
        subdistrict = s[8];
        pincode = s[9];
        telephone = s[10];
        mobilenumber = s[11];
        emergencynum = s[12];
        ambulancenum = s[13];
        tollfree = s[14];
        helpline = s[15];
        hospitalfax = s[16];
        primaryemail = s[17];
        secondaryemail = s[18];
        website = s[19];
        specialities = s[20];
        latitide = s[21];
        longitude = s[22];
        facilities = s[23];
        totalnumofbads = s[24];

    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getCaretype() {
        return caretype;
    }

    public String getSystemsOfMedicine() {
        return systemsOfMedicine;
    }

    public String getAddress() {
        return address;
    }

    public String getState() {
        return state;
    }

    public String getDistrict() {
        return district;
    }

    public String getSubdistrict() {
        return subdistrict;
    }

    public String getPincode() {
        return pincode;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public String getEmergencynum() {
        return emergencynum;
    }

    public String getAmbulancenum() {
        return ambulancenum;
    }

    public String getTollfree() {
        return tollfree;
    }

    public String getHelpline() {
        return helpline;
    }

    public String getHospitalfax() {
        return hospitalfax;
    }

    public String getPrimaryemail() {
        return primaryemail;
    }

    public String getSecondaryemail() {
        return secondaryemail;
    }

    public String getWebsite() {
        return website;
    }

    public String getSpecialities() {
        return specialities;
    }

    public String getLatitide() {
        return latitide;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getFacilities() {
        return facilities;
    }

    public String getTotalnumofbads() {
        return totalnumofbads;
    }
}
