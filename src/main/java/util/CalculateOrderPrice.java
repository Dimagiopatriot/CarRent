package util;

import model.entity.Order;

import java.sql.Date;

public class CalculateOrderPrice {

    private static class Holder{
        static final CalculateOrderPrice INSTANCE = new CalculateOrderPrice();
    }

    public static CalculateOrderPrice getInstance(){
        return Holder.INSTANCE;
    }

    public float calculateUserCountAfterOrderApproving(Order order, float currentUserCount){
        Date dateFrom = order.getDateStartRent();
        Date dateTo = order.getDateEndRent();
        int dayDifference = getRentPeriod(dateFrom, dateTo);
        float rentPriceForPeriod = getRentPrice(dayDifference, order.getCar().getRentPricePerDay());
        return currentUserCount - rentPriceForPeriod;
    }

    private int getRentPeriod(Date dateFrom, Date dateTo){
        long timeDifference = Math.abs(dateTo.getTime() - dateFrom.getTime());
        return (int) Math.ceil(timeDifference / (1000 * 3600 * 24));
    }

    private float getRentPrice(int dayDifference, float priceForCarPerDay){
        return dayDifference * priceForCarPerDay;
    }
}
