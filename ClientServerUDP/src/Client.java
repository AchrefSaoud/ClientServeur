import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private DatagramSocket datagramSocket;
    private byte[] buffer;
    private InetAddress inetAddress;

    public Client(DatagramSocket datagramSocket,InetAddress inetAddress){
        this.datagramSocket=datagramSocket;
        this.inetAddress=inetAddress;
    }
    public void sendthenreceive(){
        Scanner sc=new Scanner(System.in);
        while (true) {
            try {
                String msg=sc.nextLine();
                this.buffer=msg.getBytes();
                DatagramPacket datagramPacket=new DatagramPacket(buffer,buffer.length,inetAddress,1234);
                datagramSocket.send(datagramPacket);
                datagramSocket.receive(datagramPacket);
                String msgfromserver=new String(datagramPacket.getData(),0,datagramPacket.getLength());
                System.out.println("the server says you said"+msgfromserver);
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
        sc.close();
    }
    public static void main(String[] args) throws SocketException, UnknownHostException {
        DatagramSocket datagramSocket=new DatagramSocket();
        InetAddress inetAddress=InetAddress.getByName("localhost");
        Client client=new Client(datagramSocket,inetAddress);
        System.out.println("send datagram packet to the server");
        client.sendthenreceive();
    }
}
