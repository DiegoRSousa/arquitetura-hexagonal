package conta.adapter;

import conta.application.domain.model.Account;
import conta.application.port.AccountRepository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.util.Objects.isNull;

public class FakeAdapterAccount implements AccountRepository {

    private Map<Integer, Account> accounts = new HashMap<>();

    public FakeAdapterAccount() {
        accounts.put(1, new Account(1, BigDecimal.TEN, "Octavia Blake"));
        accounts.put(2, new Account(2, BigDecimal.TEN, "Raven Reyes"));
    }

    @Override
    public Account get(Integer number) {
        return accounts.get(number);
    }

    @Override
    public void update(Account account) {
        var acc = accounts.get(account.getNumber());
        if(!isNull(acc))
            accounts.put(acc.getNumber(), acc);
        else
            throw new IllegalArgumentException("Account not exist: " + account.getNumber());
    }
}
