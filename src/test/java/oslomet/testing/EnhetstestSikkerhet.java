package oslomet.testing;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpSession;
import oslomet.testing.DAL.BankRepository;
import oslomet.testing.Models.Kunde;
import oslomet.testing.Sikkerhet.Sikkerhet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EnhetstestSikkerhet {

    @InjectMocks
    private Sikkerhet sikkerhetsController;

    @Mock
    private BankRepository bankrep;

    @Mock
    private MockHttpSession session;

    @Test
    public void test_sjekkLoggInn(){

        Kunde kunde = new Kunde("01010110523", "Lene", "Jensen", "Askerveien 22", "3270", "Oslo", "22224444", "HeiHei");

        when(bankrep.sjekkLoggInn(kunde.getPersonnummer(), kunde.getPassord())).thenReturn("OK");

        String resultat = sikkerhetsController.sjekkLoggInn("01010110523", "HeiHei");

        assertEquals("OK", resultat);
    }
}
