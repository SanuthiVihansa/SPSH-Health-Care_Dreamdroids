package com.spsh.spshhealthcare;

import org.junit.Test;

import static org.junit.Assert.*;

import java.text.DecimalFormat;

public class PatientAndAppointmentUnitTest {


    @Test
    public void testNameValidation() {
        assertEquals(true, validateName("Michael"));
        assertEquals(true, validateName("Michael Jackson"));
        assertEquals(true, validateName("G.G.T.John"));
        assertEquals(false, validateName("Mich3al"));
        assertEquals(false, validateName("G,G,T,John"));
    }

    @Test
    public void testAgeValidation() {
        assertEquals(true, validateAge("1"));
        assertEquals(true, validateAge("50"));
        assertEquals(true, validateAge("124"));
        assertEquals(true, validateAge("125"));
        assertEquals(false, validateAge("-50"));
        assertEquals(false, validateAge("-1"));
        assertEquals(false, validateAge("0"));
        assertEquals(false, validateAge("126"));
    }

    @Test
    public void testContactNoValidation() {
        assertEquals(true, validateContactNo("0771234567"));
        assertEquals(true, validateContactNo("0111234567"));
        assertEquals(true, validateContactNo("0711234567"));
        assertEquals(true, validateContactNo("0761234567"));
        assertEquals(true, validateContactNo("0721234567"));
        assertEquals(false, validateContactNo("07712345678"));
        assertEquals(false, validateContactNo("7712345678"));
        assertEquals(false, validateContactNo("771234567"));
    }

    @Test
    public void testTimeValidation() {
        assertEquals(true, validateTime("0:00"));
        assertEquals(true, validateTime("5:00"));
        assertEquals(true, validateTime("9:00"));
        assertEquals(true, validateTime("5:59"));
        assertEquals(true, validateTime("00:00"));
        assertEquals(true, validateTime("12:00"));
        assertEquals(true, validateTime("15:00"));
        assertEquals(true, validateTime("23:00"));
        assertEquals(true, validateTime("23:01"));
        assertEquals(true, validateTime("23:59"));
        assertEquals(false, validateTime("5:60"));
        assertEquals(false, validateTime("5:61"));
        assertEquals(false, validateTime("23:60"));
        assertEquals(false, validateTime("23:61"));
        assertEquals(false, validateTime("23;00"));
        assertEquals(false, validateTime("23.00"));
        assertEquals(false, validateTime("23-00"));
        assertEquals(false, validateTime("24:00"));
        assertEquals(false, validateTime("25:00"));
        assertEquals(false, validateTime("05:0"));
        assertEquals(false, validateTime("12:122"));
    }

    @Test
    public void testDateValidation() {
        assertEquals(true, validateDate("01-01-2022"));
        assertEquals(true, validateDate("21-10-2022"));
        assertEquals(true, validateDate("31-12-2022"));
        assertEquals(true, validateDate("31/12/2022"));
        assertEquals(true, validateDate("31-12-2100"));
        assertEquals(false, validateDate("27-10-2021"));
        assertEquals(false, validateDate("32-10-2022"));
        assertEquals(false, validateDate("10-27-2022"));
        assertEquals(false, validateDate("15-13-2022"));
        assertEquals(false, validateDate("00-13-2022"));
        assertEquals(false, validateDate("15-11/2022"));
        assertEquals(false, validateDate("15/11-2022"));
        assertEquals(false, validateDate("15;11;2022"));
        assertEquals(false, validateDate("15.11.2022"));
    }

    @Test
    public void testCost(){
        assertEquals(false, costCalc("555.55", "16:00"));
        assertEquals(true, costCalc("555.55", "21:00"));
        assertEquals(false, costCalc("555.55", "20:59"));
        assertEquals(true, costCalc("555.55", "0:59"));
        assertEquals(true, costCalc("555.55", "0:00"));
        assertEquals(true, costCalc("555.55", "06:59"));
        assertEquals(false, costCalc("555.55", "07:00"));
        assertEquals(true, costCalc("555.55", "00:59"));
        assertEquals(true, costCalc("555.55", "6:59"));
        assertEquals(false, costCalc("555.55", "7:00"));
    }


    //validations
    //validation to check that only letters exist
    public boolean validateName(String string) { //checks for letters
        return string.matches("[a-z. A-Z]+");//NOT WORKING "[a-zA-Z]*" , "[a-zA-Z]+"
    }

    //validation for age
    public boolean validateAge(String string) {
        int isAgeValid = Integer.parseInt(string);
        if (isAgeValid <= 0 || isAgeValid > 125) {
            return false;
        } else {
            return true;
        }
    }

    //validation for contact number
    public boolean validateContactNo(String string) { //checks for letters
        if (string.length() == 10 && string.charAt(0) == '0') {
            return true;
        } else {
            return false;
        }
    }

    //validation for time
    public boolean validateTime(String string) {
        if (string.length() == 4) {
            if (string.charAt(1) == ':' && Integer.parseInt((Character.toString(string.charAt(0)))) > -1 && Integer.parseInt(string.substring(2)) > -1 && Integer.parseInt(string.substring(2)) < 60)
                return true;
            else {
                return false;
            }
        } else if (string.length() == 5) {
            if (string.charAt(2) == ':' && Integer.parseInt(string.substring(0, 2)) > -1 && Integer.parseInt(string.substring(0, 2)) < 24 && Integer.parseInt(string.substring(3)) > -1 && Integer.parseInt(string.substring(3)) < 60)
                return true;
            else {
                return false;
            }
        } else {
            return false;
        }
    }

    //validation for date
    public boolean validateDate(String string) {
        if (string.length() == 10) {
            if ((string.charAt(2) == '-' && string.charAt(5) == '-') || (string.charAt(2) == '/' && string.charAt(5) == '/')) {
                if (Integer.parseInt(string.substring(0, 2)) > 0 && Integer.parseInt(string.substring(0, 2)) < 32 && Integer.parseInt(string.substring(3, 5)) > 0 && Integer.parseInt(string.substring(3, 5)) < 13 && Integer.parseInt(string.substring(6, 10)) > 2021 && Integer.parseInt(string.substring(6, 8)) < 10000) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    //cost calculation validation
    public boolean costCalc(String DocFee, String appointmentTime){
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String finalCost = null;
        double taxRate = 0.1, taxAmount = 0.0, feeDoc = 0.0, lateNightAndEarlyMorningFee = 0, doubleCost = 0.0;
        int time = 0;

        feeDoc = Double.parseDouble(DocFee);
        taxAmount = feeDoc * taxRate;

        if (appointmentTime.length() == 4){
            time = Integer.parseInt(appointmentTime.substring(0,1));
            if (time == 0 || time <= 6) {
                lateNightAndEarlyMorningFee = 0.5 * feeDoc;
                return true;
            }
            else {
                lateNightAndEarlyMorningFee = 0;
                return false;
            }
        }else if(appointmentTime.length() == 5){
            time = Integer.parseInt(appointmentTime.substring(0,2));
            if (time >= 21 || time <= 6) {
                lateNightAndEarlyMorningFee = 0.5 * feeDoc;
                return true;
            }
            else {
                lateNightAndEarlyMorningFee = 0;
                return false;
            }
        }

        doubleCost = taxAmount + lateNightAndEarlyMorningFee + feeDoc;
        finalCost = String.valueOf(decimalFormat.format(doubleCost));

        return true;
    }
}
