package com.group8.backspace.logic;

import com.group8.backspace.logic.exceptions.CardException;

public class CheckCard {
    private final int CARD_LENGTH = 16;
    private final int DATE_LENGTH = 4;
    private final int SECURITY_LENGTH = 3;


    public String checkFormat(String cardNum, String date, String securityNum) throws CardException {
        if(cardNum.length()==CARD_LENGTH&&date.length()==
                DATE_LENGTH&&securityNum.length()==SECURITY_LENGTH){
            return "Purchase succeed";
        }
        else {
            return "Purchase failed. Please provide a valid credit card.";
        }
    }

}
