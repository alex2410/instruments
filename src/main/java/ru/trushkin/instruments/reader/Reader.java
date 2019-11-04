package ru.trushkin.instruments.reader;

import ru.trushkin.instruments.consumer.DataConsumer;

/**
 * Producer для прочитанных данных
 *
 * @param <T>
 */
public interface Reader<T> {

    /**
     * Читает данные и передает их в consumer
     *
     * @param consumer - consumer для данных
     */
    void readData(DataConsumer<T> consumer);

}
