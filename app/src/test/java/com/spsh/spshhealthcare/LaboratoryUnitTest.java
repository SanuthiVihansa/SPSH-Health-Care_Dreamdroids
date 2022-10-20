package com.spsh.spshhealthcare;

import org.junit.Test;

import static org.junit.Assert.*;

public class LaboratoryUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void name_check(){
        String input1 = "Amaraweera";
        String input2 = "Senanayake1";
        String input3 = "L@santha";

        assertEquals(true, nameIsCorrect(input1));
        assertEquals(false, nameIsCorrect(input2));
        assertEquals(false, nameIsCorrect(input3));
    }

    @Test
    public void time_check(){
        String input1 = "08:30";
        String input2 = "9:20";
        String input3 = "24:03";
        String input4 = "4:80";
        String input5 = "04:93";

        assertEquals(true, timeIsCorrect(input1));
        assertEquals(true, timeIsCorrect(input2));
        assertEquals(false, timeIsCorrect(input3));
        assertEquals(false, timeIsCorrect(input4));
        assertEquals(false, timeIsCorrect(input5));
    }

    public boolean nameIsCorrect(String name) {
        if(name.contains("0") || name.contains("1") || name.contains("2") || name.contains("3") || name.contains("4") || name.contains("5") || name.contains("6") || name.contains("7") || name.contains("8") || name.contains("9") || name.contains("`") || name.contains("~") || name.contains("!") || name.contains("@") || name.contains("#") || name.contains("$") || name.contains("%") || name.contains("^") || name.contains("&") || name.contains("*") || name.contains("(") || name.contains(")") || name.contains("-") || name.contains("_") || name.contains("=") || name.contains("+") || name.contains("{") || name.contains("[") || name.contains("]") || name.contains("}") || name.contains("\\") || name.contains("|") || name.contains(":") || name.contains(";") || name.contains("\"") || name.contains("'") || name.contains(",") || name.contains("<") || name.contains(">") || name.contains("/") || name.contains("?") || name.contains("™") || name.contains("℉") || name.contains("⟬") || name.contains("²") || name.contains("№") || name.contains("⟧") || name.contains("‱") || name.contains("é") || name.contains("℃") || name.contains("⟦") || name.contains("‰") || name.contains("©") || name.contains("€") || name.contains("¥"))
            return false;
        else
            return true;
    }

    public boolean timeIsCorrect(String time){
        if(time.length() == 4){
            if(time.charAt(1) == ':' && Integer.parseInt((Character.toString(time.charAt(0)))) > -1 && Integer.parseInt(time.substring(2)) > -1 && Integer.parseInt(time.substring(2)) < 60)
                return true;
            else
                return false;
        }
        else if(time.length() == 5){
            if(time.charAt(2) == ':' && Integer.parseInt(time.substring(0, 2)) > -1 && Integer.parseInt(time.substring(0, 2)) < 24 && Integer.parseInt(time.substring(3)) > -1 && Integer.parseInt(time.substring(3)) < 60)
                return true;
            else
                return false;
        }
        else
            return false;
    }
}
