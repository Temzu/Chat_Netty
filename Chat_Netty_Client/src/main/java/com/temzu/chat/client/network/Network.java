package com.temzu.chat.client.network;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Network {
    private static final Logger LOG = LoggerFactory.getLogger(Network.class);

    private SocketChannel channel;

    public Network(int port, String address) {
        new Thread(() -> {
            EventLoopGroup workerGroup = new NioEventLoopGroup();
            try {
                Bootstrap b = new Bootstrap();
                b.group(workerGroup)
                        .channel(NioSocketChannel.class)
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel socketChannel) throws Exception {
                                channel = socketChannel;
                                channel.pipeline().addLast(
                                        new ObjectDecoder(ClassResolvers.cacheDisabled(getClass().getClassLoader())),
                                        new ObjectEncoder());
                            }
                        });
                ChannelFuture future = b.connect(address, port).sync();
                LOG.debug("Connection success! PORT: " + port + " ADDRESS: " + address);
                future.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                LOG.debug("e = " + e);
            } finally {
                workerGroup.shutdownGracefully();
            }
        }).start();
    }

    public void sendMessage(String msg) {
        channel.writeAndFlush(msg);
    }
}
