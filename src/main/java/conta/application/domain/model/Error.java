package conta.application.domain.model;

public class Error {
    public static void required(String name) {
        throw new IllegalArgumentException(name + " is required");
    }

    public static void insufficientBalance() {
        throw new IllegalArgumentException("Insufficient balance");
    }

}