package fi.helsinki.cs.tmc.core.utilities;

import static org.mockito.Mockito.when;

import fi.helsinki.cs.tmc.core.configuration.TmcSettings;
import fi.helsinki.cs.tmc.core.holders.TmcSettingsHolder;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Locale;
import java.util.concurrent.Callable;

public class ExceptionTrackingCallableTest {

    @Mock TmcSettings settings;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        TmcSettingsHolder.set(settings);
        when(settings.getServerAddress()).thenReturn("testAddress");
        when(settings.clientName()).thenReturn("testClient");
        when(settings.clientVersion()).thenReturn("testVersion");
        when(settings.hostProgramName()).thenReturn("testHostProgram");
        when(settings.hostProgramVersion()).thenReturn("testHostProgramVersion");
        when(settings.getLocale()).thenReturn(new Locale("en"));
    }


    @Test(expected = Exception.class)
    public void testCall() throws Exception {
        new ExceptionTrackingCallable<>(new ExceptionThrowingCallable<Void>()).call();
    }

    class ExceptionThrowingCallable<T> implements Callable<T> {

        @Override
        public T call() throws Exception {
            throw new Exception();
        }
    }
}
