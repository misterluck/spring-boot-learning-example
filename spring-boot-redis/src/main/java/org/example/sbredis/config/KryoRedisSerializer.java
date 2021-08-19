package org.example.sbredis.config;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.util.Assert;

import java.io.ByteArrayOutputStream;

public class KryoRedisSerializer<T> implements RedisSerializer<Object> {
    private static final Logger logger = LoggerFactory.getLogger(KryoRedisSerializer.class);

    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    private static final ThreadLocal<Kryo> kryos = ThreadLocal.withInitial(Kryo::new);
    private Class<T> type;

    public KryoRedisSerializer(Class<T> type) {
        Assert.notNull(type, "Type must not be null!");
        this.type = type;
    }

    @Override
    public byte[] serialize(Object t) throws SerializationException {
        if (t == null) return EMPTY_BYTE_ARRAY;
        Kryo kryo = kryos.get();
        // kryo.setReferences(false);
        // kryo.register(type);
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Output output = new Output(baos);
            kryo.writeClassAndObject(output, t);
            output.flush();
            output.close();
            return baos.toByteArray();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return EMPTY_BYTE_ARRAY;
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length <= 0)  return null;
        Kryo kryo = kryos.get();
        // kryo.setReferences(false);
        // kryo.register(type);
        try {
            Input input = new Input(bytes);
            Object t = kryo.readClassAndObject(input);
            input.close();
            return t;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
