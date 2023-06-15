package org.fundly.test;

import org.fundly.entity.Loan;
import org.fundly.service.LoanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class LoanTest {
    private LoanService loanService;

    @BeforeEach
    public void setup() {
        loanService = new LoanService();
    }

    @Test
    public void testAddLoan_PaymentDateGreaterThanDueDate_ExceptionThrown() {
        Loan loan = new Loan("L2", "C1", "LEN1", 20000, 5000, new Date(1234567892), 0.01, new Date(1234567891), 0.01);
        try {
            loanService.addLoan(loan);
            fail("Exception should be thrown for payment date greater than due date");
        } catch (Exception e) {
            assertEquals("Payment date can't be greater than the due date for Loan L2", e.getMessage());
        }
    }

    @Test
    public void testGetTotalRemainingAmountByLender() {
        Loan loan1 = new Loan("L3", "C2", "LEN2", 50000, 30000, new Date(1234567893), 0.02, new Date(1234567894), 0.02);
        Loan loan2 = new Loan("L4", "C3", "LEN2", 50000, 20000, new Date(1234567895), 0.02, new Date(1234567896), 0.02);
        try {
            loanService.addLoan(loan1);
            loanService.addLoan(loan2);
        } catch (Exception e) {
            fail("Exception should not be thrown for valid loans");
        }
        double totalRemainingAmount = loanService.getTotalRemainingAmountByLender("LEN2");
        assertEquals(50000 + 20000, totalRemainingAmount);
    }

    @Test
    public void testGetTotalInterestByLender() {
        Loan loan1 = new Loan("L3", "C2", "LEN2", 50000, 30000, new Date(1234567893), 0.02, new Date(1234567894), 0.02);
        Loan loan2 = new Loan("L4", "C3", "LEN2", 50000, 20000, new Date(1234567895), 0.03, new Date(1234567896), 0.02);
        try {
            loanService.addLoan(loan1);
            loanService.addLoan(loan2);
        } catch (Exception e) {
            fail("Exception should not be thrown for valid loans");
        }
        double totalInterest = loanService.getTotalInterestByLender("LEN2");
        assertEquals(50000 * 0.02 + 50000 * 0.03, totalInterest);
    }

    @Test
    public void testGetTotalPenaltyByLender() {
        Loan loan1 = new Loan("L3", "C2", "LEN2", 50000, 30000, new Date(1234567893), 0.02, new Date(1234567894), 0.02);
        Loan loan2 = new Loan("L4", "C3", "LEN2", 50000, 20000, new Date(1234567895), 0.03, new Date(1234567896), 0.02);
        try {
            loanService.addLoan(loan1);
            loanService.addLoan(loan2);
        } catch (Exception e) {
            fail("Exception should not be thrown for valid loans");
        }
        double totalPenalty = loanService.getTotalPenaltyByLender("LEN2");
        assertEquals(0, totalPenalty); // Assuming the current date is before the due date
    }

    @Test
    public void testGetTotalRemainingAmountByInterest() {
        Loan loan1 = new Loan("L3", "C2", "LEN2", 50000, 30000, new Date(1234567893), 0.02, new Date(1234567894), 0.02);
        Loan loan2 = new Loan("L4", "C3", "LEN2", 50000, 20000, new Date(1234567895), 0.02, new Date(1234567896), 0.03);
        try {
            loanService.addLoan(loan1);
            loanService.addLoan(loan2);
        } catch (Exception e) {
            fail("Exception should not be thrown for valid loans");
        }
        double totalRemainingAmount = loanService.getTotalRemainingAmountByInterest(0.02);
        assertEquals(20000 + 30000, totalRemainingAmount);
    }

    @Test
    public void testGetTotalInterestByInterest() {
        Loan loan1 = new Loan("L3", "C2", "LEN2", 50000, 30000, new Date(1234567893), 0.02, new Date(1234567894), 0.02);
        Loan loan2 = new Loan("L4", "C3", "LEN2", 50000, 20000, new Date(1234567895), 0.02, new Date(1234567896), 0.03);
        try {
            loanService.addLoan(loan1);
            loanService.addLoan(loan2);
        } catch (Exception e) {
            fail("Exception should not be thrown for valid loans");
        }
        double totalInterest = loanService.getTotalInterestByInterest(0.02);
        assertEquals(50000 * 0.02 + 50000 * 0.02, totalInterest);
    }

    @Test
    public void testGetTotalPenaltyByInterest() {
        Loan loan1 = new Loan("L3", "C2", "LEN2", 50000, 30000, new Date(1234567893), 0.02, new Date(1234567894), 0.02);
        Loan loan2 = new Loan("L4", "C3", "LEN2", 50000, 20000, new Date(1234567895), 0.02, new Date(1234567896), 0.03);
        try {
            loanService.addLoan(loan1);
            loanService.addLoan(loan2);
        } catch (Exception e) {
            fail("Exception should not be thrown for valid loans");
        }
        double totalPenalty = loanService.getTotalPenaltyByInterest(0.03);
        assertEquals(0, totalPenalty); // Assuming the current date is before the due date
    }

    @Test
    public void testGetTotalRemainingAmountByCustomer() {
        Loan loan1 = new Loan("L3", "C2", "LEN2", 50000, 30000, new Date(1234567893), 0.02, new Date(1234567894), 0.02);
        Loan loan2 = new Loan("L4", "C3", "LEN2", 50000, 20000, new Date(1234567895), 0.02, new Date(1234567896), 0.03);
        try {
            loanService.addLoan(loan1);
            loanService.addLoan(loan2);
        } catch (Exception e) {
            fail("Exception should not be thrown for valid loans");
        }
        double totalRemainingAmount = loanService.getTotalRemainingAmountByCustomer("C2");
        assertEquals(30000, totalRemainingAmount);
    }

    @Test
    public void testGetTotalInterestByCustomer() {
        Loan loan1 = new Loan("L3", "C2", "LEN2", 50000, 30000, new Date(1234567893), 0.02, new Date(1234567894), 0.02);
        Loan loan2 = new Loan("L4", "C3", "LEN2", 50000, 20000, new Date(1234567895), 0.02, new Date(1234567896), 0.03);
        try {
            loanService.addLoan(loan1);
            loanService.addLoan(loan2);
        } catch (Exception e) {
            fail("Exception should not be thrown for valid loans");
        }
        double totalInterest = loanService.getTotalInterestByCustomer("C2");
        assertEquals(50000 * 0.02, totalInterest);
    }

    @Test
    public void testGetTotalPenaltyByCustomer() {
        Loan loan1 = new Loan("L3", "C2", "LEN2", 50000, 30000, new Date(1234567893), 0.02, new Date(1234567894), 0.02);
        Loan loan2 = new Loan("L4", "C3", "LEN2", 50000, 20000, new Date(1234567895), 0.02, new Date(1234567896), 0.03);
        try {
            loanService.addLoan(loan1);
            loanService.addLoan(loan2);
        } catch (Exception e) {
            fail("Exception should not be thrown for valid loans");
        }
        double totalPenalty = loanService.getTotalPenaltyByCustomer("C3");
        assertEquals(2.92635E7, totalPenalty); // Assuming the current date is before the due date
    }
}
