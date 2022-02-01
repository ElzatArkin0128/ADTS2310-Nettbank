package oslomet.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.BankController;
import oslomet.testing.DAL.BankRepository;
import oslomet.testing.Models.Konto;
import oslomet.testing.Models.Kunde;
import oslomet.testing.Sikkerhet.Sikkerhet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EnhetstestAdminKontoController {

    @InjectMocks
    // denne skal testes
    private BankController bankController;

    @Mock
    // denne skal Mock'es
    private BankRepository repository;

    @Mock
    // denne skal Mock'es
    private Sikkerhet sjekk;

    @Test
    public void test_hentAlleKontiOK() {
        // arrange


        // act


        // assert
    }

    @Test
    public void test_hentAlleKontiFeil() {
        // arrange


        // act


        // assert
    }

    @Test
    public void test_registrerKontoOK() {
        // arrange


        // act


        // assert
    }

    @Test
    public void test_registrerKontoFeil() {
        // arrange


        // act


        // assert
    }

    @Test
    public void test_endreKontoOK() {
        // arrange


        // act


        // assert
    }

    @Test
    public void test_endreKontoFeil() {
        // arrange


        // act


        // assert
    }

    @Test
    public void test_slettKontoOK() {
        // arrange


        // act


        // assert
    }

    @Test
    public void test_slettKontoFeil() {
        // arrange


        // act


        // assert
    }
}

