package com.group8.backspace.logic;

import static junit.framework.Assert.assertEquals;
import org.junit.Test;


public class CalculatePriceTest {

   @Test
   public void testPrepaidDays(){
       System.out.println("\nStart calculatePrice test");
       CalculatePrices calculatePrices = new CalculatePrices(10,20,30,40);
       calculatePrices.setPrepaidDays(101);
       assertEquals(20,calculatePrices.getPrepaidDays());
       calculatePrices.setPrepaidDays(0);
       assertEquals(0,calculatePrices.getPrepaidDays());
       calculatePrices.setPrepaidDays(50);
       assertEquals(10,calculatePrices.getPrepaidDays());

       assertEquals(30000,calculatePrices.calculateFuelPrice());
       assertEquals(600,calculatePrices.calculateClassPrice());
       assertEquals(400,calculatePrices.calculatePrepaidPrice());
       assertEquals(31000,calculatePrices.calculateTotalPrice());
       System.out.println("End calculatePrice test");
   }
}
