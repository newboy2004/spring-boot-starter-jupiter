package org.jupiter.config;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.jupiter.rpc.DefaultClient;
import org.jupiter.rpc.DefaultServer;
import org.jupiter.rpc.JClient;
import org.jupiter.rpc.JServer;
import org.jupiter.transport.netty.JNettyTcpAcceptor;
import org.jupiter.transport.netty.JNettyTcpConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author yanglinlin newboy2004@126.com
 * @create 2017-12-15 14:54
 */

@ConfigurationProperties(prefix="jupiter")
public class JupiterConfig {

    private Logger logger = Logger.getLogger(JupiterConfig.class);

    private int acceptorPort;

    private String registerConnctStr;

    public JupiterConfig(){
        configServer();
        configClient();
    }

    /**
     * 配置jupiter server，并启动监听
     */
    private  void configServer() {
        this.defaultServer= new DefaultServer();
        this.defaultServer.withAcceptor(new JNettyTcpAcceptor(acceptorPort));
        this.defaultServer.connectToRegistryServer(registerConnctStr);
        try {
            this.defaultServer.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error("JupiterConfig->JupiterConfig start error",e);
        }
    }


    /**
     * 配置jupiter client,连接到注册中心
     */
    private void  configClient(){
        this.defaultClient = new DefaultClient().withConnector(new JNettyTcpConnector());
        defaultClient.connectToRegistryServer(registerConnctStr);
    }

    private JServer defaultServer;

    private JClient defaultClient;


    public JServer getDefaultServer() {
        return defaultServer;
    }

    public void setDefaultServer(JServer defaultServer) {
        this.defaultServer = defaultServer;
    }

    public JClient getDefaultClient() {
        return defaultClient;
    }

    public void setDefaultClient(JClient defaultClient) {
        this.defaultClient = defaultClient;
    }
}
