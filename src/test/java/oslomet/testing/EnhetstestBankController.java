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
public class EnhetstestBankController {

    @InjectMocks
    // denne skal testes
    private BankController bankController;
    private AdminKontoController kontoController;

    @Mock
    // denne skal Mock'es
    private BankRepository repository;
    private AdminRepository repo2;

    @Mock
    // denne skal Mock'es
    private Sikkerhet sjekk;

    @Test
    public void hentKundeInfo_loggetInn() {

        // arrange
        Kunde enKunde = new Kunde("01010110523",
                "Lene", "Jensen", "Askerveien 22", "3270",
                "Asker", "22224444", "HeiHei");

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentKundeInfo(anyString())).thenReturn(enKunde);

        // act
        Kunde resultat = bankController.hentKundeInfo();

        // assert
        assertEquals(enKunde, resultat);
    }

    @Test
    public void hentKundeInfo_IkkeloggetInn() {

        // arrange
        when(sjekk.loggetInn()).thenReturn(null);

        //act
        Kunde resultat = bankController.hentKundeInfo();

        // assert
        assertNull(resultat);
    }

    @Test
    public void hentKonti_LoggetInn() {
        // arrange
        List<Konto> konti = new ArrayList<>();
        Konto konto1 = new Konto("105010123456", "01010110523",
                720, "Lønnskonto", "NOK", null);
        Konto konto2 = new Konto("105010123456", "12345678901",
                1000, "Lønnskonto", "NOK", null);
        konti.add(konto1);
        konti.add(konto2);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentKonti(anyString())).thenReturn(konti);

        // act
        List<Konto> resultat = bankController.hentKonti();

        // assert
        assertEquals(konti, resultat);
    }

    @Test
    public void hentKonti_IkkeLoggetInn() {
        // arrange

        when(sjekk.loggetInn()).thenReturn(null);

        // act
        List<Konto> resultat = bankController.hentKonti();

        // assert
        assertNull(resultat);
    }

    @Test
    public void hentSaldi_loggetInn() {

        // arrange
        List<Konto> saldi = new ArrayList<>();
        Konto konto3 = new Konto("115111133557", "02020211533",
                800, "Lønnskonto", "NOK", null);
        saldi.add(konto3);

        when(sjekk.loggetInn()).thenReturn("115111133557");

        when(repository.hentSaldi(anyString())).thenReturn(saldi);

        // act
        List<Konto> resultat = bankController.hentSaldi();

        // assert
        assertEquals(saldi, resultat);

    }

    @Test
    public void hentSaldi_IkkeLoggetInn() {
        // arrange

        when(sjekk.loggetInn()).thenReturn(null);

        // act
        List<Konto> resultat = bankController.hentSaldi();

        // assert
        assertNull(resultat);
    }


    @Test
    //Transaksjoner skal ha inn parameterene kontonr, fra og til dato, vet ikke hvordan dette skal testes
    public void hentTransaksjoner_OK() {

        Transaksjon tr1 = new Transaksjon(2, "123456789101", 23.5,
                "2012-03-11", "send", "1", "23456789101");
        Transaksjon tr2 = new Transaksjon(3, "123456789101", 23.5,
                "2021-04-11", "send", "1", "23456789101");

        List<Transaksjon> transaksjons = new ArrayList<>();

        transaksjons.add(tr1);
        transaksjons.add(tr2);

        Konto konto1 = new Konto("115111133557", "02020211533",
                800, "Lønnskonto", "NOK", transaksjons);

        //konto1.setTransaksjoner(transaksjons);

        when(sjekk.loggetInn()).thenReturn("115111133557");
        when(repository.hentTransaksjoner(konto1.getKontonummer(), "2011-01-01", "2013-01-01")).thenReturn(konto1);

        List<Transaksjon> resultat = konto1.getTransaksjoner();

        assertEquals(transaksjons, resultat);

    }

    @Test
    public void hentBetalinger_OK() {

        // arrange
        List<Transaksjon> Transaksjoner = new ArrayList<>();
        Transaksjon Transaksjon1 = new Transaksjon(2, "123456789101", 23.5,
                "2012-03-11", "send", "1", "23456789101");
        Transaksjoner.add(Transaksjon1);

        when(sjekk.loggetInn()).thenReturn("115111133557");
    }

}




