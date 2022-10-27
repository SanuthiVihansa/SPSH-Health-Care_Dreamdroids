package com.spsh.spshhealthcare;

import org.junit.Test;
import static org.junit.Assert.*;



public class PharmacyUnitTest {
    @Test
    public void  ItemCode_check(){
        String input1 = "P0001";
        String input2 = "M0001";
        String input3 = "S0001";
        String input4 = "C0001";
        String input5 = "I0001";
        String input6 = "S00010";


        assertEquals(true, ItemCodeIsCorrect(input1));
        assertEquals(true, ItemCodeIsCorrect(input2));
        assertEquals(true, ItemCodeIsCorrect(input3));
        assertEquals(true, ItemCodeIsCorrect(input4));
        assertEquals(false, ItemCodeIsCorrect(input5));
        assertEquals(false, ItemCodeIsCorrect(input6));

    }

    @Test
    public void ProducerName_check(){
        String input1 = "50";
        String input2 = "100";
        String input3 = "appleplc";
        String input4 = "james";


        assertEquals(false, ProducerNameIsCorrect(input1));
        assertEquals(false, ProducerNameIsCorrect(input2));
        assertEquals(true, ProducerNameIsCorrect(input3));
        assertEquals(true, ProducerNameIsCorrect(input4));

    }

    public boolean ItemCodeIsCorrect(String itemCode) {

        if ((itemCode.contains("P")||itemCode.contains("S")||itemCode.contains("M")||itemCode.contains("C"))&&itemCode.length()==5)
            return true;
        else
            return false;
    }

    public boolean ProducerNameIsCorrect(String name) {
        if(name.contains("0") || name.contains("1") || name.contains("2") || name.contains("3") || name.contains("4") || name.contains("5") || name.contains("6") || name.contains("7") || name.contains("8") || name.contains("9") || name.contains("`") || name.contains("~") || name.contains("!") || name.contains("@") || name.contains("#") || name.contains("$") || name.contains("%") || name.contains("^") || name.contains("&") || name.contains("*") || name.contains("(") || name.contains(")") || name.contains("-") || name.contains("_") || name.contains("=") || name.contains("+") || name.contains("{") || name.contains("[") || name.contains("]") || name.contains("}") || name.contains("\\") || name.contains("|") || name.contains(":") || name.contains(";") || name.contains("\"") || name.contains("'") || name.contains(",") || name.contains("<") || name.contains(">") || name.contains("/") || name.contains("?") || name.contains("™") || name.contains("℉") || name.contains("⟬") || name.contains("²") || name.contains("№") || name.contains("⟧") || name.contains("‱") || name.contains("é") || name.contains("℃") || name.contains("⟦") || name.contains("‰") || name.contains("©") || name.contains("€") || name.contains("¥"))
            return false;
        else
            return true;
    }


}


