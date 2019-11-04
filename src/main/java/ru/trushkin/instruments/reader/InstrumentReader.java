package ru.trushkin.instruments.reader;

import org.springframework.beans.factory.annotation.Value;
import ru.trushkin.instruments.consumer.DataConsumer;
import ru.trushkin.instruments.entity.Instrument;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class InstrumentReader implements Reader<Instrument> {

    @Value("${instruments.reader.dateFormat}")
    private String dateFormat;

    @Value("${instruments.reader.fileName}")
    private String fileName;

    private SimpleDateFormat sdf;

    @PostConstruct
    public void init() {
        sdf = new SimpleDateFormat(dateFormat, Locale.US);
    }

    @Override
    public void readData(DataConsumer<Instrument> consumer) {
        try (InputStream is = getStream();
             Scanner scanner = new Scanner(is).useDelimiter(",|" + System.lineSeparator()).useLocale(Locale.US)) {
            while (scanner.hasNext()) {
                String name = scanner.next();
                Date date = sdf.parse(scanner.next());
                BigDecimal value = scanner.nextBigDecimal();

                IOException lastIoException = scanner.ioException();
                if (lastIoException != null) {
                    throw lastIoException;
                }
                consumer.onData(Instrument.of(name, date, value));
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    protected InputStream getStream() throws FileNotFoundException {
        return new FileInputStream(fileName);
    }
}
