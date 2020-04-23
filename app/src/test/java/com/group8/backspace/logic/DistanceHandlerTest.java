package com.group8.backspace.logic;


import com.group8.backspace.objects.Location;
import org.junit.Assert;
import org.junit.Test;


public class DistanceHandlerTest {

    @Test
    public void testDuration(){
        System.out.println("\n Start distance test");

        Location a = new Location ("1");
        Location b = new Location ("1");
        a.setDistance("10");
        b.setDistance("20");

        DistanceHandler distHandler = new DistanceHandler(a,b);
        double distance = distHandler.getDistance();
        Assert.assertTrue(Double.compare(10,distance)==0);
        System.out.println("End distance test");
    }

}
