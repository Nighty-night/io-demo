package com.knight.netty.client;

import com.ibyte.iot.test.data.Protocol;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyClient {
    private static Logger LOGGER = LoggerFactory.getLogger(NettyClient.class);
    public static String IP = "127.0.0.1";
    public static int PORT = 8048;

    public static void main(String[] args) {
        EventLoopGroup client = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(client)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new NettyClientHandler());
                        }
                    });

            ChannelFuture f = bootstrap.connect(IP,PORT).sync();

            f.channel().writeAndFlush(Unpooled.copiedBuffer("我想和你在一起!", CharsetUtil.UTF_8));

            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            client.shutdownGracefully();
        }
    }
}
