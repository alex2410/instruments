package ru.trushkin.instruments.reader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.trushkin.instruments.TestConfig;
import ru.trushkin.instruments.consumer.DataConsumer;
import ru.trushkin.instruments.entity.Instrument;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class InstrumentReaderTest {

    @Autowired
    private InstrumentReader instrumentReader;

    @Test
    public void readData() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
        List<Instrument> expected = new ArrayList<>();
        expected.add(Instrument.of("INSTRUMENT1", sdf.parse("01-Jan-1996"), new BigDecimal("1.1")));
        expected.add(Instrument.of("INSTRUMENT1", sdf.parse("02-Jan-1996"), new BigDecimal("2.2")));
        expected.add(Instrument.of("INSTRUMENT1", sdf.parse("03-Jan-1996"), new BigDecimal("3.3")));
        List<Instrument> actual = new ArrayList<>();
        instrumentReader.readData(new DataConsumer<>() {
            @Override
            public void onData(Instrument data) {
                actual.add(data);
            }

            @Override
            public void finish() {
            }
        });
        assertEquals(expected, actual);
    }
}