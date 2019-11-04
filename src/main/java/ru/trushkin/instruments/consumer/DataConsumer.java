package ru.trushkin.instruments.consumer;

/**
 * Consumer для сущностей
 *
 * @param <T> - тип сущности
 */
public interface DataConsumer<T> {

    /**
     * Действие выполняющееся для новой сущности
     *
     * @param data - сущность
     */
    void onData(T data);

    /**
     * Окончание работы
     */
    void finish();
}
