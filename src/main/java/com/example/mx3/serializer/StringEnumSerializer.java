package com.example.mx3.serializer;

import com.example.mx3.annotation.EnumMessage;
import com.example.mx3.enums.BaseEnum;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.var;
import org.springframework.util.ReflectionUtils;

import java.io.IOException;

/**
 * 枚举序列化
 *
 * @since 2020/3/7 2:46 下午
 */
public class StringEnumSerializer<T extends String> extends JsonSerializer<T> {

    private static final String MESSAGE = "Message";

    @Override
    public void serialize(String code, JsonGenerator generator, SerializerProvider serializerProvider) throws IOException {
        if (code == null) {
            return;
        }

        generator.writeObject(code);

        var currentValue = generator.getCurrentValue();
        var fieldName = generator.getOutputContext().getCurrentName();

        var field = ReflectionUtils.findField(currentValue.getClass(), fieldName);
        if (field == null) {
            return;
        }

        var enumMessage = field.getAnnotation(EnumMessage.class);
        if (enumMessage == null) {
            return;
        }

        for (BaseEnum<?> e : enumMessage.type().getEnumConstants()) {
            if (code.equals(e.code())) {
                generator.writeStringField(fieldName + MESSAGE, e.message());
                return;
            }
        }

    }
}
