package com.spsh.spshhealthcare;

import org.junit.Test;

import static org.junit.Assert.*;

import android.widget.EditText;

public class PatientAndAppointmentUnitTest {
    private Patient_Add appointment;
    private Patient_Update updateAppointment;

    public void setup() {
        appointment = new Patient_Add();
        updateAppointment = new Patient_Update();
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
        assertEquals(true, appointment.validateAge("1"));
        assertEquals(true, appointment.validateAge("50"));
        assertEquals(true, appointment.validateAge("124"));
        assertEquals(true, appointment.validateAge("125"));
        assertEquals(false, appointment.validateAge("-50"));
        assertEquals(false, appointment.validateAge("-1"));
        assertEquals(false, appointment.validateAge("0"));
        assertEquals(false, appointment.validateAge("126"));
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

    @Test
    public void testTimeValidation() {
        assertEquals(true, appointment.validateTime("0:00"));
        assertEquals(true, appointment.validateTime("5:00"));
        assertEquals(true, appointment.validateTime("9:00"));
        assertEquals(true, appointment.validateTime("5:59"));
        assertEquals(true, appointment.validateTime("00:00"));
        assertEquals(true, appointment.validateTime("12:00"));
        assertEquals(true, appointment.validateTime("15:00"));
        assertEquals(true, appointment.validateTime("23:00"));
        assertEquals(true, appointment.validateTime("23:01"));
        assertEquals(true, appointment.validateTime("23:59"));
        assertEquals(false, appointment.validateTime("5:60"));
        assertEquals(false, appointment.validateTime("5:61"));
        assertEquals(false, appointment.validateTime("23:60"));
        assertEquals(false, appointment.validateTime("23:61"));
        assertEquals(false, appointment.validateTime("23;00"));
        assertEquals(false, appointment.validateTime("23.00"));
        assertEquals(false, appointment.validateTime("23-00"));
        assertEquals(false, appointment.validateTime("24:00"));
        assertEquals(false, appointment.validateTime("25:00"));
        assertEquals(false, appointment.validateTime("05:0"));
        assertEquals(false, appointment.validateTime("12:122"));
    }

    @Test
    public void testDateValidation() {
        assertEquals(true, appointment.validateDate("01-01-2022"));
        assertEquals(true, appointment.validateDate("21-10-2022"));
        assertEquals(true, appointment.validateDate("31-12-2022"));
        assertEquals(true, appointment.validateDate("31/12/2022"));
        assertEquals(true, appointment.validateDate("31-12-2100"));
        assertEquals(false, appointment.validateDate("27-10-2021"));
        assertEquals(false, appointment.validateDate("32-10-2022"));
        assertEquals(false, appointment.validateDate("10-27-2022"));
        assertEquals(false, appointment.validateDate("15-13-2022"));
        assertEquals(false, appointment.validateDate("00-13-2022"));
        assertEquals(false, appointment.validateDate("15-11/2022"));
        assertEquals(false, appointment.validateDate("15/11-2022"));
        assertEquals(false, appointment.validateDate("15;11;2022"));
        assertEquals(false, appointment.validateDate("15.11.2022"));
    }

    @Test
    public void testUpdatedNameValidation() {
        assertEquals(true, updateAppointment.validateName("Michael"));
        assertEquals(true, updateAppointment.validateName("Michael Jackson"));
        assertEquals(true, updateAppointment.validateName("G.G.T.John"));
        assertEquals(false, updateAppointment.validateName("Mich3al"));
        assertEquals(false, updateAppointment.validateName("G,G,T,John"));
    }

    @Test
    public void testUpdatedAgeValidation() {
        assertEquals(true, updateAppointment.validateAge("1"));
        assertEquals(true, updateAppointment.validateAge("50"));
        assertEquals(true, updateAppointment.validateAge("124"));
        assertEquals(true, updateAppointment.validateAge("125"));
        assertEquals(false, updateAppointment.validateAge("-50"));
        assertEquals(false, updateAppointment.validateAge("-1"));
        assertEquals(false, updateAppointment.validateAge("0"));
        assertEquals(false, updateAppointment.validateAge("126"));
    }

    @Test
    public void testUpdatedContactNoValidation() {
        assertEquals(true, updateAppointment.validateContactNo("0771234567"));
        assertEquals(true, updateAppointment.validateContactNo("0111234567"));
        assertEquals(true, updateAppointment.validateContactNo("0711234567"));
        assertEquals(true, updateAppointment.validateContactNo("0761234567"));
        assertEquals(true, updateAppointment.validateContactNo("0721234567"));
        assertEquals(false, updateAppointment.validateContactNo("07712345678"));
        assertEquals(false, updateAppointment.validateContactNo("7712345678"));
        assertEquals(false, updateAppointment.validateContactNo("771234567"));
    }
}
