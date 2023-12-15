package org.company.com.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidationsTest {

    private Validations validations = new Validations();

    @Test
    public void testOnlyLettersValidInputName() {
        assertTrue(validations.onlyLetters("John"));
    }

    @Test
    public void testOnlyLettersInvalidInputName() {
        assertFalse(validations.onlyLetters("#rub3n"));
    }

//  @Test
//  public void testDateOfBirthValidInput() {
//    assertTrue(validations.dateOfBirth("23/10/2000"));
//  }

    @Test
    public void testDateOfBirthInvalidInput() {
        assertFalse(validations.dateOfBirth("23/10/2010"));
    }

    @Test
    public void testValidateDNIValidInput() {
        assertTrue(validations.validateDNI("SAML940512HMCLNS07"));
    }

    @Test
    public void testValidateDNIInvalidInput() {
        assertFalse(validations.validateDNI("123"));
    }

    @Test
    public void testValidateMonthlyIncomeValidInput() {
        assertTrue(validations.validateMonthlyIncome("1000000"));
    }

    @Test
    public void testEmptyStringValidation() {
        assertTrue(validations.isNullOrEmpty(null), "Validación de cadena nula");
        assertTrue(validations.isNullOrEmpty(""), "Validación de cadena vacía");
        assertTrue(validations.isNullOrEmpty("   "), "Validación de cadena con espacios en blanco");
        assertFalse(validations.isNullOrEmpty("Texto no vacío"), "Validación de cadena no vacía");
    }

    @Test
    public void testValidateMonthlyIncomeInvalidInput() {
        assertFalse(validations.validateMonthlyIncome("1000000000"));
    }
}