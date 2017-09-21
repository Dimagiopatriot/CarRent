package model.entity;

import java.sql.Date;

public class Order {
    private int id;
    private int carId;
    private Date dateStartRent;
    private Date dateEndRent;
    private Status status;
    private String comment;
    private int userId;

    public enum Status {
        ACCEPTED,
        DENIED,
        CLOSED;

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

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
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
        if (carId != order.carId) return false;
        if (userId != order.userId) return false;
        if (!dateStartRent.equals(order.dateStartRent)) return false;
        if (!dateEndRent.equals(order.dateEndRent)) return false;
        if (status != order.status) return false;
        return comment != null ? comment.equals(order.comment) : order.comment == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + carId;
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
                ", carId=" + carId +
                ", dateStartRent=" + dateStartRent +
                ", dateEndRent=" + dateEndRent +
                ", status=" + status +
                ", comment='" + comment + '\'' +
                ", userId=" + userId +
                '}';
    }
}
