package eu.tsp.pots.switch1.thread;

import eu.tsp.pots.switch1.config.SwitchConfig;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.*;
import java.util.HashMap;

public class MessageThread extends Thread
{
    private final DatagramSocket socket;
    private boolean running;
    private final byte[] buf = new byte[256];

    @Autowired
    SwitchConfig config;

    public MessageThread(int port) throws SocketException
    {
        socket = new DatagramSocket(port);
    }

    @Override
    public void run()
    {
        running = true;

        while (running)
        {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            try
            {
                socket.receive(packet);
            } catch (IOException e)
            {
                throw new RuntimeException(e);
            }

            var map = config.getMap();
            InetAddress address = packet.getAddress();
            // get source ip address
            String source = address.getHostAddress() + ":" + packet.getPort();
            // if this ip is in the map
            if (map.containsKey(source))
            {
                // get the corresponding ip
                String dest = map.get(source);
                try
                {
                    // ip
                    address = InetAddress.getByName(dest.substring(0, dest.indexOf(":")));
                } catch (UnknownHostException e)
                {
                    throw new RuntimeException(e);
                }
                int port = Integer.parseInt(dest.substring(dest.indexOf(":") + 1));
                packet = new DatagramPacket(buf, buf.length, address, port);
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println(received);
                try
                {
                    //send the packet to the corresponding address
                    socket.send(packet);
                } catch (IOException e)
                {
                    throw new RuntimeException(e);
                }
            }
        }
        socket.close();
    }
}
