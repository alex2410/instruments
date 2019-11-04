package ru.trushkin.instruments.calculator.filter;

import ru.trushkin.instruments.entity.Instrument;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class BusinessDatesFilter implements Filter<Instrument> {

    private static final String SATURDAY = "Sat";
    private static final String SUNDAY = "Sun";

    private ThreadLocal<SimpleDateFormat> sdf = ThreadLocal.withInitial(() -> new SimpleDateFormat("EEE", Locale.US));

    @Override
    public boolean isAcceptable(Instrument data) {
        String dayOfWeek = sdf.get().format(data.getDate());
        return !SUNDAY.equals(dayOfWeek) && !SATURDAY.equals(dayOfWeek);
    }
}
