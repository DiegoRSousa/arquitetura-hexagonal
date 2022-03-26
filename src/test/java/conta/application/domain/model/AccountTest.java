package conta.application.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    Account account;

    @BeforeEach
    void setup(){
        account = new Account(1, new BigDecimal("10.00"), "Bellamy Blake");
    }

    @Test
    @DisplayName("it should throw Exception when null value")
    void test01(){
        var exception = assertThrows(IllegalArgumentException.class,
                () -> account.deposit(null));
        assertEquals("deposit value is required", exception.getMessage());
        assertEquals(new BigDecimal("10.00"), account.getBalance());
    }

    @Test
    @DisplayName("it should throw Exception when ammount is negative")
    void test02() {
        var amount = new BigDecimal("-1");

        var exception = assertThrows(IllegalArgumentException.class,
                () -> account.deposit(amount));
        assertEquals("deposit value is required", exception.getMessage());
        assertEquals(new BigDecimal("10.00"), account.getBalance());
    }

    @Test
    @DisplayName("it should deposit of account when the value is valid ")
    void test03() {
        var amount = new BigDecimal("10.00");;

        assertDoesNotThrow(() -> account.deposit(amount));
        assertEquals(new BigDecimal("20.00"), account.getBalance());
    }

    @Test
    @DisplayName("it should throw Exception when null value")
    void test04(){
        var exception = assertThrows(IllegalArgumentException.class,
                () -> account.withdraw(null));
        assertEquals("withdraw value is required", exception.getMessage());
        assertEquals(new BigDecimal("10.00"), account.getBalance());
    }

    @Test
    @DisplayName("it should throw Exception when ammount is negative")
    void test05() {
        var amount = new BigDecimal("-1");

        var exception = assertThrows(IllegalArgumentException.class,
                () -> account.withdraw(amount));
        assertEquals("withdraw value is required", exception.getMessage());
        assertEquals(new BigDecimal("10.00"), account.getBalance());
    }

    @Test
    @DisplayName("it should throw Exception when ammount is less than account balance")
    void test06() {
        var amount = new BigDecimal("100.00");

        var exception = assertThrows(IllegalArgumentException.class,
                () -> account.withdraw(amount));
        assertEquals("Insufficient balance", exception.getMessage());
        assertEquals(new BigDecimal("10.00"), account.getBalance());
    }

    @Test
    @DisplayName("it should withdraw of account when the value is valid ")
    void test07() {
        var amount = new BigDecimal("10.00");;

        assertDoesNotThrow(() -> account.withdraw(amount));
        assertEquals(new BigDecimal("0.00"), account.getBalance());
    }
}