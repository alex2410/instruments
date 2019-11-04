package ru.trushkin.instruments.calculator.filter;

import org.junit.Test;
import ru.trushkin.instruments.entity.Instrument;

import java.text.SimpleDateFormat;
import java.util.Locale;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BusinessDatesFilterTest {

    BusinessDatesFilter filter = new BusinessDatesFilter();

    @Test
    public void isAcceptable() throws Exception {
        assertTrue(filter.isAcceptable(instrument("04-Nov-2019")));
        assertTrue(filter.isAcceptable(instrument("05-Nov-2019")));
        assertTrue(filter.isAcceptable(instrument("06-Nov-2019")));
        assertTrue(filter.isAcceptable(instrument("07-Nov-2019")));
        assertTrue(filter.isAcceptable(instrument("08-Nov-2019")));
        assertFalse(filter.isAcceptable(instrument("09-Nov-2019")));
        assertFalse(filter.isAcceptable(instrument("10-Nov-2019")));
    }

    private Instrument instrument(String date) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
        return Instrument.of(null, sdf.parse(date), null);
    }
}