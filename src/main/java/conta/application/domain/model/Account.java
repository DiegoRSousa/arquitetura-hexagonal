package conta.application.domain.model;

import java.math.BigDecimal;

import static conta.application.domain.model.Error.insufficientBalance;
import static conta.application.domain.model.Error.required;
import static java.util.Objects.isNull;

public class Account {
    private Integer number;
    private BigDecimal balance;
    private String holder;

    public Account(Integer number, BigDecimal balance, String holder) {
        this.number = number;
        this.balance = balance;
        this.holder = holder;
    }

    public void deposit(BigDecimal credit){
        if(isNull(credit))
            required("deposit value");
        if(credit.compareTo(BigDecimal.ZERO) <= 0)
            required("deposit value");
        balance = balance.add(credit);
    }

    public void withdraw(BigDecimal debit) {
        if(isNull(debit))
            required("withdraw value");
        if(debit.compareTo(BigDecimal.ZERO) <= 0)
            required("withdraw value");
        if(debit.compareTo(balance) > 0)
            insufficientBalance();
        balance = balance.subtract(debit);
    }

    public Integer getNumber() {
        return number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getHolder() {
        return holder;
    }
}
