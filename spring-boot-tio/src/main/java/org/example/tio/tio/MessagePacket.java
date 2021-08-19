package org.example.tio.tio;

import org.tio.core.intf.Packet;

/**
 * @author tanyaowu
 */
public class MessagePacket extends Packet {
    private static final long serialVersionUID = -172060606924066412L;
    public static final String CHARSET = "utf-8";
    public static final int HEADER_LENGTH = 4;//消息头的长度
    private byte[] body;

    /**
     * @return the body
     */
    public byte[] getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(byte[] body) {
        this.body = body;
    }
}
