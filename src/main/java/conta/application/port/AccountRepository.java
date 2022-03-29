package conta.application.port;

import conta.application.domain.model.Account;

public interface AccountRepository {
    Account get(Integer number);
    void update(Account account);
}
