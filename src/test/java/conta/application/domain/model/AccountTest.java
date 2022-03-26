package conta.application.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    Account account;
    Account targetAccount;

    @BeforeEach
    void setup(){
        account = new Account(1, new BigDecimal("10.00"), "Bellamy Blake");
        targetAccount = new Account(2, new BigDecimal("0.00"), "John Marphy");
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
    @DisplayName("it should throw Exception when ammount is zero")
    void test02() {
        var amount = new BigDecimal("0.00");

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
    @DisplayName("it should throw Exception when ammount is zero")
    void test05() {
        var amount = new BigDecimal("0");

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

    @Test
    @DisplayName("it should throw Exception when null value")
    void test08(){
        var exception = assertThrows(IllegalArgumentException.class,
                () -> account.transfer(null, account));
        assertEquals("transfer value is required", exception.getMessage());
        assertEquals(new BigDecimal("10.00"), account.getBalance());
    }

    @Test
    @DisplayName("it should throw Exception when null account")
    void test09(){
        var exception = assertThrows(IllegalArgumentException.class,
                () -> account.transfer(BigDecimal.TEN, null));
        assertEquals("transfer target account is required", exception.getMessage());
        assertEquals(new BigDecimal("10.00"), account.getBalance());
    }

    @Test
    @DisplayName("it should throw Exception when ammount is zero")
    void test10() {
        var amount = new BigDecimal("0.00");

        var exception = assertThrows(IllegalArgumentException.class,
                () -> account.transfer(amount, targetAccount));
        assertEquals("transfer value is required", exception.getMessage());
        assertEquals(new BigDecimal("10.00"), account.getBalance());
        assertEquals(new BigDecimal("0.00"), targetAccount.getBalance());
    }

    @Test
    @DisplayName("it should throw Exception when ammount is less than account balance")
    void test11() {
        var amount = new BigDecimal("100.00");

        var exception = assertThrows(IllegalArgumentException.class,
                () -> account.transfer(amount, targetAccount));
        assertEquals("Insufficient balance", exception.getMessage());
        assertEquals(new BigDecimal("10.00"), account.getBalance());
        assertEquals(new BigDecimal("0.00"), targetAccount.getBalance());
    }

    @Test
    @DisplayName("it should transfer of account when the value is valid ")
    void test12() {
        var amount = new BigDecimal("10.00");

        assertDoesNotThrow(() -> account.transfer(amount, targetAccount));
        assertEquals(new BigDecimal("0.00"), account.getBalance());
        assertEquals(new BigDecimal("10.00"), targetAccount.getBalance());
    }
}