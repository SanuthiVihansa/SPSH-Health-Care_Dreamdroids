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

}
