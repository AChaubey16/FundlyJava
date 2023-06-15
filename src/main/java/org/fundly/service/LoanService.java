package org.fundly.service;

import org.fundly.entity.Loan;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class LoanService {

    private List<Loan> loans;
    private Logger logger;

    public LoanService() {
        loans = new ArrayList<>();
        logger = Logger.getLogger(LoanService.class.getName());
    }

    public void addLoan(Loan loan) throws Exception {
        if (loan.getPaymentDate().after(loan.getDueDate())) {
            throw new Exception("Payment date can't be greater than the due date for Loan " + loan.getLoanId());
        }
        loans.add(loan);
        if (loan.getDueDate().before(new Date())) {
            logger.warning("Loan " + loan.getLoanId() + " has crossed the due date.");
        }
    }

    public List<Loan> getLoansByLender(String lenderId) {
        List<Loan> result = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan.getLenderId().equals(lenderId)) {
                result.add(loan);
            }
        }
        return result;
    }

    public List<Loan> getLoansByInterest(double interestPerDay) {
        List<Loan> result = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan.getInterestPerDay() == interestPerDay) {
                result.add(loan);
            }
        }
        return result;
    }

    public List<Loan> getLoansByCustomer(String customerId) {
        List<Loan> result = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan.getCustomerId().equals(customerId)) {
                result.add(loan);
            }
        }
        return result;
    }

    public double getTotalRemainingAmountByLender(String lenderId) {
        double totalRemainingAmount = 0;
        for (Loan loan : loans) {
            if (loan.getLenderId().equals(lenderId)) {
                totalRemainingAmount += loan.getRemainingAmount();
            }
        }
        return totalRemainingAmount;
    }

    public double getTotalInterestByLender(String lenderId) {
        double totalInterest = 0;
        for (Loan loan : loans) {
            if (loan.getLenderId().equals(lenderId)) {
                totalInterest += loan.getAmount() * loan.getInterestPerDay();
            }
        }
        return totalInterest;
    }

    public double getTotalPenaltyByLender(String lenderId) {
        double totalPenalty = 0;
        for (Loan loan : loans) {
            if (loan.getLenderId().equals(lenderId)) {
                Date currentDate = new Date();
                if (currentDate.after(loan.getDueDate())) {
                    long daysOverdue = (currentDate.getTime() - loan.getDueDate().getTime()) / (24 * 60 * 60 * 1000);
                    totalPenalty += loan.getAmount() * loan.getPenaltyPerDay() * daysOverdue;
                }
            }
        }
        return totalPenalty;
    }

    public double getTotalRemainingAmountByInterest(double interestPerDay) {
        double totalRemainingAmount = 0;
        for (Loan loan : loans) {
            if (loan.getInterestPerDay() == interestPerDay) {
                totalRemainingAmount += loan.getRemainingAmount();
            }
        }
        return totalRemainingAmount;
    }

    public double getTotalInterestByInterest(double interestPerDay) {
        double totalInterest = 0;
        for (Loan loan : loans) {
            if (loan.getInterestPerDay() == interestPerDay) {
                totalInterest += loan.getAmount() * loan.getInterestPerDay();
            }
        }
        return totalInterest;
    }

    public double getTotalPenaltyByInterest(double interestPerDay) {
        double totalPenalty = 0;
        for (Loan loan : loans) {
            if (loan.getInterestPerDay() == interestPerDay) {
                Date currentDate = new Date();
                if (currentDate.after(loan.getDueDate())) {
                    long daysOverdue = (currentDate.getTime() - loan.getDueDate().getTime()) / (24 * 60 * 60 * 1000);
                    totalPenalty += loan.getAmount() * loan.getPenaltyPerDay() * daysOverdue;
                }
            }
        }
        return totalPenalty;
    }

    public double getTotalRemainingAmountByCustomer(String customerId) {
        double totalRemainingAmount = 0;
        for (Loan loan : loans) {
            if (loan.getCustomerId().equals(customerId)) {
                totalRemainingAmount += loan.getRemainingAmount();
            }
        }
        return totalRemainingAmount;
    }

    public double getTotalInterestByCustomer(String customerId) {
        double totalInterest = 0;
        for (Loan loan : loans) {
            if (loan.getCustomerId().equals(customerId)) {
                totalInterest += loan.getAmount() * loan.getInterestPerDay();
            }
        }
        return totalInterest;
    }

    public double getTotalPenaltyByCustomer(String customerId) {
        double totalPenalty = 0;
        for (Loan loan : loans) {
            if (loan.getCustomerId().equals(customerId)) {
                Date currentDate = new Date();
                if (currentDate.after(loan.getDueDate())) {
                    long daysOverdue = (currentDate.getTime() - loan.getDueDate().getTime()) / (24 * 60 * 60 * 1000);
                    totalPenalty += loan.getAmount() * loan.getPenaltyPerDay() * daysOverdue;
                }
            }
        }
        return totalPenalty;
    }
}
