package ru.trushkin.instruments.calculator.filter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.trushkin.instruments.TestConfig;
import ru.trushkin.instruments.entity.Instrument;

import java.text.SimpleDateFormat;
import java.util.Locale;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class DateFilterTest {

    @Autowired
    private DateFilter filter;

    @Test
    public void isAcceptable() throws Exception {
         assertTrue(filter.isAcceptable(instrument("01-Nov-2014")));
        assertTrue(filter.isAcceptable(instrument("11-Nov-2014")));
        assertTrue(filter.isAcceptable(instrument("30-Nov-2014")));
        assertFalse(filter.isAcceptable(instrument("01-Dec-2014")));
        assertFalse(filter.isAcceptable(instrument("31-Oct-2014")));
    }

    private Instrument instrument(String date) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
        return Instrument.of(null, sdf.parse(date), null);
    }
}