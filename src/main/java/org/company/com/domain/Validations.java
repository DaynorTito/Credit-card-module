package org.company.com.domain;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Validations {


  public Validations() { }
  public boolean onlyLetters(String data) {
    String patron = "^[a-zA-Z]+$";
    Pattern pattern = Pattern.compile(patron);
    Matcher matcher = pattern.matcher(data);
    if (matcher.matches()) {
      return true;
    } else {
      System.out.println("Error en verificacion de cadenas y numeros");
      return false;
    }
  }

  public boolean dateOfBirth(String date) {
    System.out.println(date);
    String[] parth = date.split("-");

    if (parth.length == 3) {
      int year = Integer.parseInt(parth[0]);
      int month = Integer.parseInt(parth[1]);
      int day = Integer.parseInt(parth[2]);

      Calendar dateOfBirth = Calendar.getInstance();
      dateOfBirth.set(year, month - 1, day);
      Calendar currentDate = Calendar.getInstance();

      int age = currentDate.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
      if (currentDate.get(Calendar.DAY_OF_YEAR) < dateOfBirth.get(Calendar.DAY_OF_YEAR)) {
        age--;
      }
      return age >= 18;
    } else {
      System.out.println("fecha invalida");
      return false;
    }
  }
  public boolean isNullOrEmpty(String data){
    return data == null || data.trim().isEmpty();
  }

  public boolean onlyLettersAndNumbers(String data){
    String patron = "^[a-zA-Z0-9]+$";
    Pattern pattern = Pattern.compile(patron);
    Matcher matcher = pattern.matcher(data);
    return matcher.matches();
  }
  public boolean validateDNI(String data) {
    int DNI = data.length();
    if (DNI >= 7 && DNI <= 18) {
      if (onlyLettersAndNumbers(data)) {
        System.out.println("DNI válido.");
        return true;
      } else {
        System.out.println("DNI contiene caracteres inválidos.");
        return false;
      }
    } else {
      System.out.println("Longitud del DNI inválida.");
      return false;
    }
  }

  public boolean validateMonthlyIncome(String number){
    if (number.matches("\\d*0{7,}\\d*")) {
      System.out.println("Cifra invalida");
      return false;
    } else {
      System.out.println("cifra válido.");
      return true;
    }
  }

  public boolean validateEmails(String email) {
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";
    Pattern pattern = Pattern.compile(emailRegex);
    Matcher matcher = pattern.matcher(email);
    if (matcher.matches()){
      return true;
    } else {
      System.out.println("Error en correo electronico");
      return false;
    }
  }

  public boolean isValidDate(String dateStr, String format) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
    try {
      LocalDate.parse(dateStr, formatter);
      return true;
    } catch (DateTimeParseException e) {
      System.out.println("Invalid date. Expected format: " + format);
      return false;
    }
  }

  public boolean isNumner(String numer) {
    return numer.matches("^\\d+$");
  }
}
