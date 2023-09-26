package exercise;

public class Flat implements Home {

    private int floor;
    private double area;
    private double balconyArea;

    public Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.floor = floor;
        this.balconyArea = balconyArea;
    }

    @Override
    public double getArea() {
        return area + balconyArea;
    }

    @Override
    public int compareTo(Home another) {
        return Double.compare(getArea(), another.getArea());
    }

    public String toString() {
        return String.format("Квартира площадью %s метров на %s этаже", getArea(), floor);
    }

}
