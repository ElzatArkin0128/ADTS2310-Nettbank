package oslomet.testing;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.mock.web.MockHttpSession;
import oslomet.testing.DAL.BankRepository;
import oslomet.testing.Models.Kunde;
import oslomet.testing.Sikkerhet.Sikkerhet;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doAnswer;


@RunWith(MockitoJUnitRunner.class)
public class EnhetstestSikkerhet {

    @InjectMocks
    private Sikkerhet sikkerhetsController;

    @Mock
    private BankRepository bankrep;

    @Mock
    private MockHttpSession session;

    // tester for sjekkLoggInn : 2 tester (OK & feil)
    @Test
    public void test_sjekkLoggInn(){

        // arrange
        Kunde kunde = new Kunde("01010110523", "Lene", "Jensen", "Askerveien 22", "3270", "Oslo", "22224444", "HeiHei");

        Mockito.when(bankrep.sjekkLoggInn(kunde.getPersonnummer(), kunde.getPassord())).thenReturn("OK");

        // act
        String resultat = sikkerhetsController.sjekkLoggInn("01010110523", "HeiHei");

        //assert
        assertEquals("OK", resultat);
    }

    @Test
    public void test_sjekkLoggInnFeil(){

        //Todo: putte inn session her????

        // arrange
        Kunde kunde = new Kunde("01010110523", "Lene", "Jensen", "Askerveien 22", "3270", "Oslo", "22224444", "HeiHei");

        Mockito.when(bankrep.sjekkLoggInn(kunde.getPersonnummer(), kunde.getPassord())).thenReturn("Feil i personnummer eller passord");

        // act
        String resultat = sikkerhetsController.sjekkLoggInn("01010110523", "HeiHei");

        // assert
        assertEquals("Feil i personnummer eller passord", resultat);
    }


    // Tester for loggetInn : 2 tester (OK & feil)
    @Test
    public void test_loggetInn(){

        // arrange
        Map<String,Object> attributes = new HashMap<String,Object>();

        doAnswer(new Answer<Object>(){
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                String key = (String) invocation.getArguments()[0];
                return attributes.get(key);
            }
        }).when(session).getAttribute(anyString());

        doAnswer(new Answer<Object>(){
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                String key = (String) invocation.getArguments()[0];
                Object value = invocation.getArguments()[1];
                attributes.put(key, value);
                return null;
            }
        }).when(session).setAttribute(anyString(), any());

        session.setAttribute("Innlogget", "01010110523");

        // act
        String resultat = sikkerhetsController.loggetInn();

        // assert
        assertEquals("01010110523", resultat);
    }

    @Test
    public void test_loggetInnFeil(){

        // arrange
        session.setAttribute("Innlogget", null);

        // act
        String resultat = sikkerhetsController.loggetInn();

        // assert
        assertNull(resultat);

    }


    // Test for loggUt : 1 test
    @Test
    public void test_loggUt(){

   // arrange
        session.setAttribute("Innlogget", null);

        // act
        String resultat = sikkerhetsController.loggetInn();

        // assert
        assertNull(resultat);

    }

    // Tester for loggInnAdmin : 2 tester (OK & feil)
    @Test
    public void test_loggInnAdmin(){

        // arrange
        session.setAttribute("Innlogget", "Admin");

        // act
        String resultat = sikkerhetsController.loggInnAdmin("Admin", "Admin");

        // assert
        assertEquals("Logget inn", resultat);

    }

    @Test
    public void test_loggInnAdminFeil(){

        // arrange
        session.setAttribute("Innlogget", null);

        // act
        String resultat = sikkerhetsController.loggInnAdmin(anyString(), anyString());

        // assert
        assertEquals("Ikke logget inn", resultat);
    }
}
