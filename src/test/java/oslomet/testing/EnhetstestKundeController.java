package oslomet.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.AdminKundeController;
import oslomet.testing.DAL.AdminRepository;
import oslomet.testing.Models.Kunde;
import oslomet.testing.Sikkerhet.Sikkerhet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EnhetstestKundeController {

    @InjectMocks
    // denne skal testes
    private AdminKundeController adminKundeController;

    @Mock
    private AdminRepository repository;

    @Mock
    private Sikkerhet sjekk;

    @Test
    public void test_hentAlleOK() {

        // arrange
        Kunde kunde1 = new Kunde("01010110523", "Lene", "Jensen",
                "Askerveien 22", "3270", "Oslo", "22224444", "HeiHei");
        Kunde kunde2 = new Kunde("12345678901", "Per", "Hansen",
                "Osloveien 82", "1234", "Oslo", "12345678", "HeiHei");

        List<Kunde> kundeliste = new ArrayList<>();
        kundeliste.add(kunde1);
        kundeliste.add(kunde2);

        // skjekker om innlogget
        when(sjekk.loggetInn()).thenReturn(kunde1.getPersonnummer(), kunde2.getPersonnummer());

        // henter kunder fra repository
        when(repository.hentAlleKunder()).thenReturn(kundeliste);

        // act
        List<Kunde> resultat = adminKundeController.hentAlle();

        // assert
        assertEquals(kundeliste, resultat);

    }

    @Test
    public void test_hentAlleFeil() {

        when(sjekk.loggetInn()).thenReturn(null);

        // act
        List<Kunde> resultat = adminKundeController.hentAlle();

        // assert
        assertNull(resultat);

    }

    @Test
    public void  test_lagreKundeOK() {

        // arrange
        Kunde enKunde = new Kunde("01010110523", "Lene", "Jensen",
                "Askerveien 22", "3270", "Oslo", "22224444", "HeiHei");

        // sjekker om innlogget
        when(sjekk.loggetInn()).thenReturn(enKunde.getPersonnummer());

        // henter kunder fra repository
        when(repository.registrerKunde(any(Kunde.class))).thenReturn("OK");

        // act
        String resultat = adminKundeController.lagreKunde(enKunde);

        // assert
        assertEquals("OK", resultat);

    }

    @Test
    public void test_lagreKundeFeil() {

        // arrange
        Kunde enKunde = new Kunde("01010110523", "Lene", "Jensen",
                "Askerveien 22", "3270", "Oslo", "22224444", "HeiHei");

        // sjekker om innlogget
        when(sjekk.loggetInn()).thenReturn(null);

        // act
        String resultat = adminKundeController.lagreKunde(enKunde);

        // assert
        assertEquals("Ikke logget inn", resultat);

    }

    @Test
    public void test_endreKundeOK() {

        // arrange
        Kunde enKunde = new Kunde("01010110523", "Lene", "Jensen",
                "Askerveien 22", "3270", "Oslo", "22224444", "HeiHei");

        // sjekker om innlogget
        Mockito.when(sjekk.loggetInn()).thenReturn(enKunde.getPersonnummer());

        // henter kunder fra repository
        Mockito.when(repository.endreKundeInfo(any(Kunde.class))).thenReturn("OK");

        // act
        String resultat = adminKundeController.endre(enKunde);

        // assert
        assertEquals("OK", resultat);
    }

    @Test
    public void test_endreKundeFeil() {

        // arrange
        Kunde enKunde = new Kunde("01010110523", "Lene", "Jensen",
                "Askerveien 22", "3270", "Oslo", "22224444", "HeiHei");

        // sjekker om innlogget
        when(sjekk.loggetInn()).thenReturn(null);

        // act
        String resultat = adminKundeController.endre(enKunde);

        // assert
        assertEquals("Ikke logget inn", resultat);
    }

    @Test
    public void test_slettKundeOK() {

        // arrange
        Kunde enKunde = new Kunde("01010110523", "Lene", "Jensen",
                "Askerveien 22", "3270", "Oslo", "22224444", "HeiHei");

        // sjekker om innlogget
        when(sjekk.loggetInn()).thenReturn(enKunde.getPersonnummer());

        // henter kunder fra repository
        when(repository.slettKunde(enKunde.getPersonnummer())).thenReturn("OK");

        // act
        String resultat = adminKundeController.slett(enKunde.getPersonnummer());

        // assert
        assertEquals("OK", resultat);
    }

    @Test
    public void test_slettKundeFeil() {

        // arrange
        Kunde enKunde = new Kunde("01010110523", "Lene", "Jensen",
                "Askerveien 22", "3270", "Oslo", "22224444", "HeiHei");

        // sjekker om innlogget
        when(sjekk.loggetInn()).thenReturn(null);

        // act
        String resultat = adminKundeController.slett(enKunde.getPersonnummer());

        // assert
        assertEquals("Ikke logget inn", resultat);
    }

}
