package org.company.com.controllers;

import org.company.com.domain.model.payment.AutomaticPayment;
import org.company.com.domain.services.MinimumPaymentCalculator;
import org.company.com.controllers.exceptions.ControllerException;
import org.company.com.domain.DB.ClientDB;
import org.company.com.domain.repository.CreditCardRepository;
import org.company.com.domain.repository.PaymentsRepository;
import org.company.com.domain.repository.UserRepository;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Optional;


public class AutomaticPaymentController {

    private Connection connection = ClientDB.getInstance().getConnection();

    private AutomaticPayment payment;
    private PaymentsRepository repository = new PaymentsRepository();
    private UserRepository accounts = new UserRepository(connection);
    private Optional<AutomaticPayment> automaticPayment = Optional.empty();
    private String account = "23177e10-eee3-4aa7-ad96-a4c2ec310f73";

    public AutomaticPaymentController() {
    }

    public String[] getAccountData(String accountId) {
        String[] result = new String[]{"", ""};
        try {
            result[0] = "David Lezama";
            result[1] = "9876543210123456";
            return result;
      /*RequestCreditCardAccount account = accounts.findByID(UUID.fromString(accountId));
      result[0] = account.getFirstName() + account.getLastName();
      result[1] = account.getId().toString();
      this.account = account.getId().toString();*/
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String[] getPaymentData(String cardId) {
        String[] result = new String[2];
        Optional<AutomaticPayment> payment = repository.findPaymentByCardId(cardId);
        automaticPayment = payment;
        if (payment.isEmpty()) {
            return result;
        }
        result[0] = String.valueOf(payment.get().getAmount());
        result[1] = payment.get().getRepeatMonthlyAt().toString();
        return result;
    }

    public void addPayment(String amount, LocalDate fecha, String cardId) throws ControllerException {
        validatePayment(fecha, Double.parseDouble(amount), cardId);
        if (automaticPayment.isEmpty()) {
            repository.addPaymentByCardId(amount, fecha, cardId, account);
        } else {
            repository.updatePaymentByCardId(amount, fecha, cardId, account);
        }
    }

    private void validatePayment(LocalDate fecha, double amount, String cardId) throws ControllerException {
        MinimumPaymentCalculator calculator = new MinimumPaymentCalculator();
        CreditCardRepository creditCardRepository = new CreditCardRepository(ClientDB.getInstance().getConnection());
        // TODO: 24/11/2023 calcular limite
        double limit = 3000;//creditCardRepository.findByCardId(cardId).get().get;
        System.out.println(calculator.calculateMinimumPayment(limit) + " " + calculator.calculateTotalPayment(limit));
        if (amount < calculator.calculateMinimumPayment(limit) || amount > calculator.calculateTotalPayment(limit)) {
            throw new ControllerException("Monto es inválido.");
        }

        if (fecha.isBefore(LocalDate.now())) {
            throw new ControllerException("Fecha es inválida.");
        }
    }
}
