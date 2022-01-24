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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EnhetstestKundeController {

    @InjectMocks
    // denne skal testes
    private AdminKundeController adminKundeController;

    @Mock
    private AdminRepository repository;


    @Test
    public void test_hentAlleOK() {

        // test logg inn og test logg inn feil

        // arrange
        Kunde kunde1 = new Kunde("01010110523", "Lene", "Jensen", "Askerveien 22", "3270", "Oslo","22224444", "HeiHei");
        Kunde kunde2 = new Kunde("12345678901", "Per", "Hansen", "Osloveien 82", "1234","Oslo", "12345678", "HeiHei");
        List<Kunde> kundeliste = new ArrayList<>();
        kundeliste.add(kunde1);
        kundeliste.add(kunde2);

        Mockito.when(repository.hentAlleKunder()).thenReturn(kundeliste);

        // act
        List<Kunde> resultat = adminKundeController.hentAlle();

        // assert
        assertEquals(kundeliste, resultat);

    }

    @Test
    public void  test_hentAlleFeil() {

        // arrange
        when(repository.hentAlleKunder()).thenReturn(null);

        // act
        List<Kunde> resultat = adminKundeController.hentAlle();

        // assert
        assertNull(resultat);

    }

    @Test
    public void  test_lagreKundeOK() {

    }

    @Test
    public void test_lagreKundeFeil() {

    }

    @Test
    public void test_endreKundeOK() {

    }

    @Test
    public void test_endreKundeFeil() {

    }

    @Test
    public void test_slettKundeOK() {

    }

    @Test
    public void test_slettKundeFeil() {

    }

}
