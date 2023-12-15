package org.company.com.domain.services.creditCardServicesTest;
import org.company.com.domain.services.creditCardServices.CvvGenerator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CvvGeneratorTest {

  @Test
  public void testGenerateCvv() {
    CvvGenerator cvvGenerator = new CvvGenerator();
    int cvv = cvvGenerator.generateCvv();

    assertTrue(cvv >= 100 && cvv <= 999);
  }

}