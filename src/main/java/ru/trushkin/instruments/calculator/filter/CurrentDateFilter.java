package ru.trushkin.instruments.calculator.filter;

import org.springframework.beans.factory.annotation.Value;
import ru.trushkin.instruments.entity.Instrument;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CurrentDateFilter implements Filter<Instrument> {

    @Value("${instruments.reader.dateFormat}")
    private String dateFormat;

    @Value("${filter.currentDate}")
    private String currentDateString;

    private Date currentDate;

    @PostConstruct
    public void init() {
        try {
            currentDate = new SimpleDateFormat(dateFormat, Locale.US).parse(currentDateString);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public boolean isAcceptable(Instrument data) {
        return data.getDate().before(currentDate) || data.getDate().equals(currentDate);
    }
}
