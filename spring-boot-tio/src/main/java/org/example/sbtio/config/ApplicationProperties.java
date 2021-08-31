package org.example.sbtio.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Configuration
@ConfigurationProperties("application.tio")
public class ApplicationProperties implements Serializable {

    private final Tio server = new Tio();
    private final Tio client = new Tio();

    public Tio getServer() {
        return server;
    }

    public Tio getClient() {
        return client;
    }

    public static class Tio implements Serializable {
        private boolean enabled = false;
        private Integer port;
        private Integer heartbeatTimeout;
        private String serverHost;

        public boolean getEnabled() {
            return this.enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public Integer getHeartbeatTimeout() {
            return heartbeatTimeout;
        }

        public void setHeartbeatTimeout(Integer heartbeatTimeout) {
            this.heartbeatTimeout = heartbeatTimeout;
        }

        public Integer getPort() {
            return port;
        }

        public void setPort(Integer port) {
            this.port = port;
        }

        public String getServerHost() {
            return serverHost;
        }

        public void setServerHost(String serverHost) {
            this.serverHost = serverHost;
        }
    }

}