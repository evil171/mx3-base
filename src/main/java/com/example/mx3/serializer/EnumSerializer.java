package com.example.mx3.serializer;

import com.example.mx3.enums.BaseEnum;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * 枚举序列化
 *
 * @since 2020/3/7 2:46 下午
 */
public class EnumSerializer<T extends BaseEnum<?>> extends JsonSerializer<T> {

    private static final String MESSAGE = "Message";

    @Override
    public void serialize(T baseEnum, JsonGenerator generator, SerializerProvider serializerProvider) throws IOException {

        if (!(baseEnum instanceof Enum)) {
            return;
        }

        generator.writeString(((Enum<?>) baseEnum).name());

        String key = generator.getOutputContext().getCurrentName();
        generator.writeStringField(key + MESSAGE, baseEnum.message());
    }
}
