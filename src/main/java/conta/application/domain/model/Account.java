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

    public void deposit(BigDecimal amount){
        if(isNull(amount))
            required("deposit value");
        if(amount.compareTo(BigDecimal.ZERO) <= 0)
            required("deposit value");
        balance = balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        if(isNull(amount))
            required("withdraw value");
        if(amount.compareTo(BigDecimal.ZERO) <= 0)
            required("withdraw value");
        if(amount.compareTo(balance) > 0)
            insufficientBalance();
        balance = balance.subtract(amount);
    }

    public void transfer(BigDecimal amount, Account account) {
        if(isNull(amount))
            required("transfer value");
        if(isNull(account))
            required("transfer target account");
        if(amount.compareTo(BigDecimal.ZERO) <= 0)
            required("transfer value");
        if(amount.compareTo(balance) > 0)
            insufficientBalance();

        withdraw(amount);
        account.deposit(amount);

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
