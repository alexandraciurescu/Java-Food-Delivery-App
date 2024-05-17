package util;


import model.DeliveryDriver;

import java.util.Comparator;

public class DriverComparator implements Comparator<DeliveryDriver>{
    @Override
    public int compare(DeliveryDriver driver1, DeliveryDriver driver2) {
        return driver2.getName().compareTo(driver1.getName());
    }

}

