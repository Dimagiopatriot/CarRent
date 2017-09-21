package model.entity;

public class Car {

    private int id;
    private CarName carName;
    private float rentPricePerHour;

    public enum CarName{
        LADA,
        DAEWOO,
        BMW,
        HYNDAI;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CarName getCarName() {
        return carName;
    }

    public void setCarName(CarName carName) {
        this.carName = carName;
    }

    public float getRentPricePerHour() {
        return rentPricePerHour;
    }

    public void setRentPricePerHour(float rentPricePerHour) {
        this.rentPricePerHour = rentPricePerHour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (id != car.id) return false;
        if (Float.compare(car.rentPricePerHour, rentPricePerHour) != 0) return false;
        return carName == car.carName;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (carName != null ? carName.hashCode() : 0);
        result = 31 * result + (rentPricePerHour != +0.0f ? Float.floatToIntBits(rentPricePerHour) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", carName=" + carName +
                ", rentPricePerHour=" + rentPricePerHour +
                '}';
    }
}
