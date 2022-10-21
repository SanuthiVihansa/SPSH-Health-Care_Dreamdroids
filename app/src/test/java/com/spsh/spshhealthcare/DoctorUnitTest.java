package com.spsh.spshhealthcare;


import org.junit.Test;

import static org.junit.Assert.*;
public class DoctorUnitTest {
    @Test

    //Test cases for Doc_Name
    public void Doc_name_check(){
        String input1 = "Dr.Sam";
        String input2 = "Dr.86m";
        String input3 = "Edward";
        String input4 = "";

        assertEquals(true, Doc_nameIsCorrect(input1));
        assertEquals(false, Doc_nameIsCorrect(input2));
        assertEquals(false, Doc_nameIsCorrect(input3));
        assertEquals(false, Doc_nameIsCorrect(input4));
    }

    //Test Cases of Assert Statements for Speciality.
    @Test
    public void Doc_speciality_check(){
        String input1 = "Dermatologist";
        String input2 = "7Cardiologist6";
        String input3 = "";

        assertEquals(true, Doc_SpecialityIsCorrect(input1));
        assertEquals(false, Doc_SpecialityIsCorrect(input2));
        assertEquals(false, Doc_SpecialityIsCorrect(input3));
    }

    //Checks Doctor NAME
    public boolean Doc_nameIsCorrect(String docName){
        if(!docName.startsWith("Dr.")||(checkDigit(docName)==false)||(docName.isEmpty())){
            return false;
        }
        else
            return true;
    }

    public boolean checkDigit(String name) {
        //converts the string to an array.
        char[] arr = name.toCharArray();
        //length of the array taken.
        int len = arr.length;
        //iterate through all the characters/elements in the array.
        for(int i =0; i < len; i++){
            //even if a single character is an integer --> returns false.
            if (Character.isDigit(arr[i])== true){
                return false;
            }
        }
        //after iterating through the entire array --> no numerical values at all --> return true.
        return true;
    }

    //Method to check Doc_Speciality.
    public boolean Doc_SpecialityIsCorrect(String speciality){
        if((checkDigit(speciality)==false)||(speciality.isEmpty())){
            return false;
        }
        else
            return true;
    }


}
