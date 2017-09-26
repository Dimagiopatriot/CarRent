package model.entity;

import java.sql.Date;

public class Order {
    private int id;
    private Car car;
    private Date dateStartRent;
    private Date dateEndRent;
    private Status status;
    private String comment;
    private int userId;

    public enum Status {
        GET_FOR_CONFIRMATION,
        ACCEPTED,
        DENIED,
        CLOSED;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    public enum Car {
        LADA(13.5f),
        BMW(20f),
        MERCEDES(21f),
        HYNDAI(16f);

        private float rentPricePerHour;

        Car(float rentPricePerHour) {
            this.rentPricePerHour = rentPricePerHour;
        }

        public float getRentPricePerHour() {
            return rentPricePerHour;
        }

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public static class Builder{
        private int id;
        private Car car;
        private Date dateStartRent;
        private Date dateEndRent;
        private Status status;
        private String comment;
        private int userId;

        public Builder addId(int id){
            this.id = id;
            return this;
        }

        public Builder addCar(Car car){
            this.car = car;
            return this;
        }

        public Builder addDateStartRent(Date dateStartRent){
            this.dateStartRent = dateStartRent;
            return this;
        }

        public Builder addDateEndRent(Date dateEndRent){
            this.dateEndRent = dateEndRent;
            return this;
        }

        public Builder addStatus(Status status){
            this.status = status;
            return this;
        }

        public Builder addComment(String comment){
            this.comment = comment;
            return this;
        }

        public Builder addUserId(int userId){
            this.userId = userId;
            return this;
        }

        public Order createOrder(){
            Order order = new Order();
            order.setId(id);
            order.setCar(car);
            order.setDateStartRent(dateStartRent);
            order.setDateEndRent(dateEndRent);
            order.setStatus(status);
            order.setComment(comment);
            order.setUserId(userId);
            return order;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateStartRent() {
        return dateStartRent;
    }

    public void setDateStartRent(Date dateStartRent) {
        this.dateStartRent = dateStartRent;
    }

    public Date getDateEndRent() {
        return dateEndRent;
    }

    public void setDateEndRent(Date dateEndRent) {
        this.dateEndRent = dateEndRent;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (userId != order.userId) return false;
        if (car != order.car) return false;
        if (!dateStartRent.equals(order.dateStartRent)) return false;
        if (!dateEndRent.equals(order.dateEndRent)) return false;
        if (status != order.status) return false;
        return comment != null ? comment.equals(order.comment) : order.comment == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + car.hashCode();
        result = 31 * result + dateStartRent.hashCode();
        result = 31 * result + dateEndRent.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + userId;
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", car=" + car +
                ", dateStartRent=" + dateStartRent +
                ", dateEndRent=" + dateEndRent +
                ", status=" + status +
                ", comment='" + comment + '\'' +
                ", userId=" + userId +
                '}';
    }
}
