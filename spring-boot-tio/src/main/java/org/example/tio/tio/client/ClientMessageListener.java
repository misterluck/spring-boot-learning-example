package org.example.tio.tio.client;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.tio.client.intf.ClientAioListener;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.core.intf.Packet;

@Service
public class ClientMessageListener implements ClientAioListener {

    private static final Logger log = LoggerFactory.getLogger(ClientMessageHandler.class);

    /**
     * 建链后触发本方法，注：建链不一定成功，需要关注参数isConnected
     *
     * @param channelContext
     * @param isConnected    是否连接成功,true:表示连接成功，false:表示连接失败
     * @param isReconnect    是否是重连, true: 表示这是重新连接，false: 表示这是第一次连接
     * @throws Exception
     * @author: tanyaowu
     */
    @Override
    public void onAfterConnected(ChannelContext channelContext, boolean isConnected, boolean isReconnect) throws Exception {

        if (isConnected) {
            log.debug("connect ip is {}",channelContext.getClientNode().getIp());
            JSONObject jsonObject = new JSONObject();
            log.debug("send point connect -> {}",jsonObject.toJSONString());
        }
    }

    /**
     * 原方法名：onAfterDecoded
     * 解码成功后触发本方法
     *
     * @param channelContext
     * @param packet
     * @param packetSize
     * @throws Exception
     * @author: tanyaowu
     */
    @Override
    public void onAfterDecoded(ChannelContext channelContext, Packet packet, int packetSize) throws Exception {

    }

    /**
     * 接收到TCP层传过来的数据后
     *
     * @param channelContext
     * @param receivedBytes  本次接收了多少字节
     * @throws Exception
     */
    @Override
    public void onAfterReceivedBytes(ChannelContext channelContext, int receivedBytes) throws Exception {

    }

    /**
     * 消息包发送之后触发本方法
     *
     * @param channelContext
     * @param packet
     * @param isSentSuccess  true:发送成功，false:发送失败
     * @throws Exception
     * @author tanyaowu
     */
    @Override
    public void onAfterSent(ChannelContext channelContext, Packet packet, boolean isSentSuccess) throws Exception {

    }

    /**
     * 处理一个消息包后
     *
     * @param channelContext
     * @param packet
     * @param cost           本次处理消息耗时，单位：毫秒
     * @throws Exception
     */
    @Override
    public void onAfterHandled(ChannelContext channelContext, Packet packet, long cost) throws Exception {

    }

    /**
     * 连接关闭前触发本方法
     *
     * @param channelContext the channelcontext
     * @param throwable      the throwable 有可能为空
     * @param remark         the remark 有可能为空
     * @param isRemove
     * @throws Exception
     * @author tanyaowu
     */
    @Override
    public void onBeforeClose(ChannelContext channelContext, Throwable throwable, String remark, boolean isRemove) throws Exception {
        log.warn("connect is failure ip {}",channelContext.getClientNode().getIp());

        JSONObject jsonObject = new JSONObject();
        log.debug("send point connect -> {}",jsonObject.toJSONString());
    }
}
