package pl.devfoundry.testing;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

public class AccountTest {

    @Test
    public void newAccountShouldNotBeActive() {
        //given
        Account account = new Account();

        //then
        assertFalse(account.isActive());
        assertThat(account.isActive(), equalTo(false));
        assertThat(account.isActive(), is(false));
    }

    @Test
    void accountShouldBeActiveAfterActivation() {
        //given
        Account newAccount = new Account();
        //when
        newAccount.activate();
        //then
        assertTrue(newAccount.isActive());
        assertThat(newAccount.isActive(), is(true));
    }

    @Test
    void newlyCreatedAccountShouldNotHaveDeliveryAddressSet() {

        //given
        Account account = new Account();

        //when
        Address address = account.getDefaultDeliveryAddress();

        //then
        assertNull(address);
        assertThat(address, nullValue());
    }

    @Test
    void defaultDeliveryAddressShouldNotBeNullAfterBeingSet() {

        //given
        Address address = new Address("Krakowska", "67c");
        Account account = new Account();
        account.setDefaultDeliveryAddress(address);

        //when
        Address address2 = account.getDefaultDeliveryAddress();

        //then
        assertNotNull(address2);
        assertThat(address2, notNullValue());
    }

    @RepeatedTest(5)
    void newAccountWithNotNullAddressShouldBeActivate() {

        //given
        Address address = new Address("Puławska", "46/6");

        //when
        Account account = new Account(address);

        //then
        assumingThat(address != null, () -> {
            assertTrue(account.isActive());
        });
    }

    @Test
    void invalidEmailShouldThrowExeption() {

        Account account = new Account();

        assertThrows(IllegalArgumentException.class, () -> account.setEmail("wrongEmail"));
    }

    @Test
    void validEmailShouldBeSet() {

        //given
        Account account = new Account();

        //when
        account.setEmail("kontakt@devfoundry.pl");

        //then
        assertThat(account.getEmail(), is("kontakt@devfoundry.pl"));
    }
}
