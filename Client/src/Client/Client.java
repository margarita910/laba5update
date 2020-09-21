package Client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {
    public static void main(String[] args) throws IOException {
        SocketAddress address = new InetSocketAddress()
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        int chance = 0;
        ByteBuffer receiveDate = ByteBuffer.allocate(10000000);
        channel.bind(new Socket());

    }
}
