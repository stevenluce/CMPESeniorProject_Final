package my.caliappdisplay;

public class Fires {
    private String Name;
    private String Location;
    private String AdminUnit;

    public Fires(){ } //empty constructor

    public Fires(String Name, String Location, String AdminUnit) {
        this.Name = Name;
        this.Location = Location;
        this.AdminUnit = AdminUnit;
    }

    public String getName() { return Name; }
    public String getLocation() { return Location; }
    public String getAdminUnit() { return AdminUnit; }
}
