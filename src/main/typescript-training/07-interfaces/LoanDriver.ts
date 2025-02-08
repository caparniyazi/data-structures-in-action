import {Loan} from "./Loan";
import {ConventionalLoan} from "./ConventionalLoan";

function calculateInterestOnlyLoanPayment(loanTerms: Loan): string {
    // Calculates the monthly payment of an interest only loan.

    let interest = loanTerms.interestRate / 1200;
    let payment;

    payment = loanTerms.principle * interest;
    return "The interest only loan payment is " + payment.toFixed(2);
}

let interestOnlyPayment = calculateInterestOnlyLoanPayment({principle: 30000, interestRate: 5});
console.log(interestOnlyPayment);

function calculateConventionalLoanPayment(loanTerms: ConventionalLoan) {
    // Calculates the monthly payment of a conventional loan
    let interest: number = loanTerms.interestRate / 1200;
    let payment: number;
    payment = loanTerms.principle * interest / (1 - (Math.pow(1 / (1 + interest), loanTerms.months)));
    return "The conventional loan payment is " + payment.toFixed(2);
}

let conventionalPayment = calculateConventionalLoanPayment({principle: 30000, interestRate: 5, months: 180});
console.log(conventionalPayment);