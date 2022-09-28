package eu.tsp.pots.phone.thread;

import org.springframework.beans.factory.annotation.Autowired;
import eu.tsp.pots.phone.config.PhoneConfig;
import java.io.IOException;
import java.net.*;

public class CallThread extends Thread{
    private final DatagramSocket socket;
    private boolean running;
    private final byte[] buf = new byte[256];

    @Autowired
    PhoneConfig config;

    public  CallThread(int port) throws SocketException
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

            InetAddress address = packet.getAddress();
            String source = address.getHostAddress() + ":" + packet.getPort();
            String dest = config.getSwitch1();
            try
            {
                address = InetAddress.getByName(dest.substring(1, dest.indexOf(":")));
            } catch (UnknownHostException e)
            {
                throw new RuntimeException(e);
            }
            int port = Integer.parseInt(dest.substring(dest.indexOf(":") + 2));
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
        socket.close();
    }
}
