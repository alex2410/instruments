package ru.trushkin.instruments.calculator.filter;

import org.springframework.beans.factory.annotation.Value;
import ru.trushkin.instruments.entity.Instrument;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFilter implements Filter<Instrument> {

    @Value("${instruments.reader.dateFormat}")
    private String dateFormat;
    private String startDateStr;
    private String endDateStr;
    private Date startDate;
    private Date endDate;

    public DateFilter(String startDateStr, String endDateStr) {
        this.startDateStr = startDateStr;
        this.endDateStr = endDateStr;
    }

    @PostConstruct
    public void init() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.US);
            startDate = sdf.parse(startDateStr);
            endDate = sdf.parse(endDateStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public boolean isAcceptable(Instrument data) {
        return data.getDate().equals(startDate) || data.getDate().equals(endDate) || (
                data.getDate().before(endDate) && data.getDate().after(startDate)
        );
    }
}
