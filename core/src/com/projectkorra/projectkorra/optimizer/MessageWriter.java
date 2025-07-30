package com.projectkorra.projectkorra.optimizer;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.function.BiConsumer;

public interface MessageWriter {

    MessageWriter writeVarInt(int value);

    MessageWriter writeUtf(@NotNull String string);

    MessageWriter writeUtf(@NotNull String string, int limit);

    MessageWriter writeByte(byte value);

    MessageWriter writeUnsignedByte(short value);

    MessageWriter writeBool(boolean value);

    <T> MessageWriter writeCollection(@NotNull Collection<T> items, @NotNull Encoder<T> encoder);

    <E extends Enum<E>> MessageWriter writeEnum(@NotNull Enum<E> value);

    @FunctionalInterface
    interface Encoder<T> extends BiConsumer<MessageWriter, T> {
    }
}