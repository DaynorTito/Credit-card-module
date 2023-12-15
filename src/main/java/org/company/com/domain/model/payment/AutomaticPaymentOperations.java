package org.company.com.domain.model.payment;

import java.time.LocalDate;
import java.util.UUID;
import org.company.com.domain.services.creditCardServices.CreditAccount;

public class AutomaticPaymentOperations {

  private static AutomaticPaymentOperations instance = null;

  public AutomaticPaymentOperations() {
  }

  public static AutomaticPaymentOperations getInstance() {
    if (instance == null) {
      instance = new AutomaticPaymentOperations();
    }
    return instance;
  }

  /**
   * Checks the date to repeat the payment.
   *
   * @param payment The object of the automatic payment.
   */
  public void cancelPayment(AutomaticPayment payment) {
    payment.setStatus(PaymentStatus.CANCELLED);
  }

  /**
   * Checks the date to repeat the payment.
   * @param newDate The new date.
   * @param payment The object of the automatic payment.
   * @return True if changed correctly, false if not.
   */
  public boolean changeDate(LocalDate newDate, AutomaticPayment payment) {
    if (newDate.getDayOfMonth() > 30) {
      return false;
    }
    payment.setRepeatMonthlyAt(newDate);
    return true;
  }

  /**
   * Checks the date to repeat the payment.
   * @param newAmount The new amount.
   * @param payment The object of the automatic payment.
   * @return True if changed correctly, false if not.
   */
  public boolean changeAmount(double newAmount, AutomaticPayment payment) {
    if (newAmount < 10.00) {
      return false;
    }
    //Rounding newAmount to 2 decimals.
    int auxRound = (int) (newAmount * 100);
    newAmount = auxRound / 100.00;

    payment.setAmount(newAmount);
    return true;
  }

  /**
   * Checks the account balance and cancels the automatic payment.
   * @param account Mock of the account.
   * @param payment The object of the automatic payment.
   * @return True if payment is canceled, false otherwise.
   */
  public boolean checkBalance(CreditAccount account, AutomaticPayment payment) {
    boolean isCanceled = false;
    if (account.getAvailableBalance() < payment.getAmount()) {
      isCanceled = true;
      payment.setStatus(PaymentStatus.CANCELLED);
    }
    return isCanceled;
  }

  /**
   * Sets the account to the automatic payment using the ID of the account.
   * @param payment The object of the payment.
   * @param account The ID of the account.
   * @return True if the account is replaced, false if not.
   */
  public boolean linkAccountToPayment(AutomaticPayment payment, UUID account) {
    boolean isReplaced = payment.getAccount() != null;
    payment.setAccount(account);
    return isReplaced;
  }
}
