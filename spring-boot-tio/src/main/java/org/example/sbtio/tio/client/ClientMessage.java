package org.example.sbtio.tio.client;

import org.example.sbtio.config.ApplicationProperties;
import org.example.sbtio.tio.util.TioUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tio.client.ClientChannelContext;
import org.tio.client.ClientTioConfig;
import org.tio.client.ReconnConf;
import org.tio.client.TioClient;
import org.tio.client.intf.ClientAioListener;
import org.tio.core.Node;
import org.tio.core.Tio;

@Component
public class ClientMessage {

    private static final Logger log = LoggerFactory.getLogger(ClientMessage.class);

    @Autowired
    private ApplicationProperties properties;

    //handler, 包括编码、解码、消息处理
    @Autowired
    private ClientMessageHandler tioClientHandler;

    //事件监听器，可以为null，但建议自己实现该接口，可以参考showcase了解些接口
    @Autowired
    private ClientAioListener aioListener;

    private  ClientChannelContext clientChannelContext;

    /**
     * 启动程序入口
     */

    public ClientMessage start() {
        try {
            log.info("application property is {}", properties);
            Node serverNode = new Node(properties.getClient().getServerHost(), properties.getClient().getPort());
            //断链后自动连接的，不想自动连接请设为null
            ReconnConf reconnConf = new ReconnConf();
            //一组连接共用的上下文对象
            ClientTioConfig clientTioConfig = new ClientTioConfig(tioClientHandler, aioListener, reconnConf);
            clientTioConfig.setHeartbeatTimeout(properties.getClient().getHeartbeatTimeout());

            TioClient tioClient = new TioClient(clientTioConfig);
            clientChannelContext = tioClient.connect(serverNode);
        }catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
        return this;
    }

    /**
     * 发送消息
     *
     * @param value
     */
    public boolean send(Object value)  {
        return Tio.send(clientChannelContext, TioUtil.toPacket(value));
    }
}
