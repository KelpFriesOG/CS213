public enum Specialty {

    FAMILY(250.00),
    PEDIATRICIAN(300.00),
    ALLERGIST(350.00);

    private final double charge;

    Specialty(double charge) {
        this.charge = charge;
    }

    public double getCharge() {
        return charge;
    }

    @Override
    public String toString() {
        return name() + " ($" + charge + ")";
    }
}
