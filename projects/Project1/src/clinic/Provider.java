public enum Provider {

    CERAVOLO(Location.EDISON, Specialty.PEDIATRICIAN),
    HARPER(Location.CLARK, Specialty.FAMILY),
    KAUR(Location.PRINCETON, Specialty.ALLERGIST),
    LIM(Location.BRIDGEWATER, Specialty.PEDIATRICIAN),
    PATEL(Location.BRIDGEWATER, Specialty.FAMILY),
    RAMESH(Location.MORRISTOWN, Specialty.ALLERGIST),
    TAYLOR(Location.PISCATAWAY, Specialty.PEDIATRICIAN),
    ZIMNES(Location.CLARK, Specialty.FAMILY);
    
    private final Location location;
    private final Specialty specialty;

    private Provider(Location location, Specialty specialty) {
        this.location = location;
        this.specialty = specialty;
    }

    public Location getLocation() {
        return location;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    @Override
    public String toString() {
        return "[" + name() + ", " + location.toString() + ", " + specialty.toString() + "]";
    }

}
