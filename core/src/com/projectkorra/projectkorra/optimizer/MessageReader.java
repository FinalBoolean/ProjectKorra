package com.projectkorra.projectkorra.optimizer;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntFunction;

public interface MessageReader {

    int readVarInt();

    String readUtf();

    String readUtf(int limit);

    byte readByte();

    boolean readBool();

    <T> List<T> readList(Decoder<T> decoder);

    <T, C extends Collection<T>> C readCollection(IntFunction<C> factory, Decoder<T> decoder);

    <E extends Enum<E>> E readEnum(Class<E> type);

    @FunctionalInterface
    interface Decoder<T> extends Function<MessageReader, T> {
    }
}