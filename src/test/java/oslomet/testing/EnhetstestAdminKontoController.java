package oslomet.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.AdminKontoController;
import oslomet.testing.API.BankController;
import oslomet.testing.DAL.AdminRepository;
import oslomet.testing.DAL.BankRepository;
import oslomet.testing.Models.Konto;
import oslomet.testing.Models.Kunde;
import oslomet.testing.Models.Transaksjon;
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
    private AdminKontoController kontoController;

    @Mock
    private AdminRepository repository;

    @Mock
    private Sikkerhet sjekk;

    @Test
    public void test_hentAlleKontiOK() {
        // Arrange
        List<Transaksjon> konto1transaksjoner = new ArrayList<>();
        List<Transaksjon> konto2transaksjoner = new ArrayList<>();
        Konto konto1 = new Konto("05068924604", "41925811793",
                13495.41, "Brukskonto", "NOK", konto1transaksjoner);
        Konto konto2 = new Konto("23129735499", "92081352378",
                116_296.96, "Sparekonto", "NOK", konto2transaksjoner);

        List<Konto> kontoliste = new ArrayList<>();
        kontoliste.add(konto1);
        kontoliste.add(konto2);

        when(sjekk.loggetInn()).thenReturn(konto1.getPersonnummer(), konto2.getPersonnummer());
        when(repository.hentAlleKonti()).thenReturn(kontoliste);

        // Act
        List<Konto> resultat = kontoController.hentAlleKonti();

        // Assert
        assertEquals(kontoliste, resultat);
    }

    @Test
    public void test_hentAlleKontiFeil() {
        // arrange
        when(sjekk.loggetInn()).thenReturn(null);

        // act
        List<Konto> resultat = kontoController.hentAlleKonti();

        // assert
        assertNull(resultat);
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

