package ru.trushkin.instruments.consumer;

/**
 * Задача для выполнения над сущностью, переданной из consumer
 *
 * @param <T> - тип сущности
 */
public interface ConsumerTask<T> {

    /**
     * Выполняет задачу над сущностью
     *
     * @param data - сущность
     */
    void execute(T data);

}
