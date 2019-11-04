package ru.trushkin.instruments.calculator.filter;

import ru.trushkin.instruments.entity.Instrument;

public class NormalDataFilter implements Filter<Instrument> {

    @Override
    public boolean isAcceptable(Instrument data) {
        return data.getName() != null && data.getValue() != null && data.getDate() != null;
    }
}
