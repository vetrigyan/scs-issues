package com.gyan.abc;

import com.google.protobuf.AbstractMessage;

import com.google.protobuf.Parser;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.AbstractMessageConverter;
import org.springframework.util.MimeType;

public class StudentProtoMessageConverter<T extends AbstractMessage> extends AbstractMessageConverter
{
    private Parser<T> parser;
    private Class classInstance;

    public StudentProtoMessageConverter(Parser<T> parser, Class classInstance)
    {
        super(new MimeType("application", "protobuf"));
        this.parser = parser;
        this.classInstance = classInstance;
    }

    @Override
    protected boolean supports(Class<?> clazz)
    {
        if (clazz != null)
        {
            return classInstance.isAssignableFrom(clazz);
        }
        return true;
    }

    @Override
    public Object convertFromInternal(Message<?> message, Class<?> targetClass, Object conversionHint)
    {
        if (!(message.getPayload() instanceof byte[]))
        {
            return null;
        }
        try
        {
            return parser.parseFrom((byte[]) message.getPayload());
        }
        catch (Exception e)
        {
            this.logger.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    protected Object convertToInternal(Object payload, MessageHeaders headers, Object conversionHint)
    {
        return ((AbstractMessage) payload).toByteArray();
    }

}
