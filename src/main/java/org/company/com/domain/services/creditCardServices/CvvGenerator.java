package org.company.com.domain.services.creditCardServices;

import java.util.Random;

public class CvvGenerator {

  public int generateCvv() {
    Random random = new Random();
    int cvv = random.nextInt(900) + 100;
    System.out.println(cvv);
    return cvv;
  }

}