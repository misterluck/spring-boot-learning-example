package org.example.tio.tio.util;

import org.example.tio.tio.MessagePacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.core.TioConfig;
import org.tio.core.exception.AioDecodeException;
import org.tio.core.intf.Packet;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class TioUtil {

    private static final Logger log = LoggerFactory.getLogger(TioUtil.class);

    /**
     * decode
     *
     * @param buffer
     * @param limit
     * @param position
     * @param readableLength
     * @param channelContext
     * @return
     * @throws AioDecodeException
     */
    public static Packet decode(ByteBuffer buffer, int limit, int position, int readableLength, ChannelContext channelContext) throws AioDecodeException {
        //收到的数据组不了业务包，则返回null以告诉框架数据不够
        if (readableLength < MessagePacket.HEADER_LENGTH) {
            return null;
        }

        //读取消息体的长度
        int bodyLength = buffer.getInt();

        //数据不正确，则抛出AioDecodeException异常
        if (bodyLength < 0) {
            throw new AioDecodeException("bodyLength [" + bodyLength + "] is not right, remote:" + channelContext.getClientNode());
        }

        //计算本次需要的数据长度
        int neededLength = MessagePacket.HEADER_LENGTH + bodyLength;
        //收到的数据是否足够组包
        int isDataEnough = readableLength - neededLength;
        // 不够消息体长度(剩下的buffe组不了消息体)
        if (isDataEnough < 0) {
            return null;
        } else //组包成功
        {
            MessagePacket imPacket = new MessagePacket();
            if (bodyLength > 0) {
                byte[] dst = new byte[bodyLength];
                buffer.get(dst);
                imPacket.setBody(dst);
            }
            return imPacket;
        }
    }

    /**
     * 编码
     *
     * @param packet
     * @param tioConfig
     * @param channelContext
     * @return
     * @author: tanyaowu
     */
    public static ByteBuffer encode(Packet packet, TioConfig tioConfig, ChannelContext channelContext) {
//        log.info("encode");
        MessagePacket helloPacket = (MessagePacket) packet;
        byte[] body = helloPacket.getBody();
        int bodyLen = 0;
        if (body != null) {
            bodyLen = body.length;
        }

        //bytebuffer的总长度是 = 消息头的长度 + 消息体的长度
        int allLen = MessagePacket.HEADER_LENGTH + bodyLen;
        //创建一个新的bytebuffer
        ByteBuffer buffer = ByteBuffer.allocate(allLen);
        //设置字节序
        buffer.order(tioConfig.getByteOrder());

        //写入消息头----消息头的内容就是消息体的长度
        buffer.putInt(bodyLen);

        //写入消息体
        if (body != null) {
            buffer.put(body);
        }
        return buffer;
    }

    public static Packet toPacket(Object value) {
        MessagePacket packet = new MessagePacket();
        if (value instanceof byte[]) {
            packet.setBody((byte[]) value);
        } else {
            try {
                packet.setBody(((String) value).getBytes(MessagePacket.CHARSET));
            } catch (UnsupportedEncodingException e) {
                log.error(e.getLocalizedMessage());
            }
        }
        return packet;
    }
}
