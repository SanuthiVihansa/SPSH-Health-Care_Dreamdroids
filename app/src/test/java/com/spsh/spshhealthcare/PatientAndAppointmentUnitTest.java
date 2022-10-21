package com.spsh.spshhealthcare;

import org.junit.Test;

import static org.junit.Assert.*;

import android.widget.EditText;

public class PatientAndAppointmentUnitTest {
    private Patient_Add appointment;

    public void setup() {
        appointment = new Patient_Add();
    }

    @Test
    public void testAllFieldsFilled() {

    }

    @Test
    public void testNameValidation() {
        assertEquals(true, appointment.validateName("Michael"));
        assertEquals(true, appointment.validateName("Michael Jackson"));
        assertEquals(true, appointment.validateName("G.G.T.John"));
        assertEquals(false, appointment.validateName("Mich3al"));
        assertEquals(false, appointment.validateName("G,G,T,John"));
    }

    @Test
    public void testAgeValidation() {
        assertEquals(false, appointment.validateAge("-50"));
        assertEquals(false, appointment.validateAge("-1"));
        assertEquals(false, appointment.validateAge("0"));
        assertEquals(false, appointment.validateAge("126"));
        assertEquals(true, appointment.validateAge("1"));
        assertEquals(true, appointment.validateAge("50"));
        assertEquals(true, appointment.validateAge("124"));
        assertEquals(true, appointment.validateAge("125"));
    }

    @Test
    public void testContactNoValidation() {
        assertEquals(true, appointment.validateContactNo("0771234567"));
        assertEquals(true, appointment.validateContactNo("0111234567"));
        assertEquals(true, appointment.validateContactNo("0711234567"));
        assertEquals(true, appointment.validateContactNo("0761234567"));
        assertEquals(true, appointment.validateContactNo("0721234567"));
        assertEquals(false, appointment.validateContactNo("07712345678"));
        assertEquals(false, appointment.validateContactNo("7712345678"));
        assertEquals(false, appointment.validateContactNo("771234567"));
    }


}
