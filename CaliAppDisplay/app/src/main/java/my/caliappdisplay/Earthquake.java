package my.caliappdisplay;

public class Earthquake {
    private double mag;
    private String place;

    public Earthquake(){ } //empty constructor

    public Earthquake(double mag, String place) {
        this.mag = mag;
        this.place = place;
    }

    public double getMag() {
        return mag;
    }
    public String getPlace() {
        return place;
    }
}
