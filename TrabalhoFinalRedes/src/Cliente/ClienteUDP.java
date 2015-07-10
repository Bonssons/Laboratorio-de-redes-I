/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Alysson e Rafael
 */
public class ClienteUDP {
    
    private Arquivo arquivo;
    private DatagramSocket socket = null;
    private String hostName = "localHost";

    public ClienteUDP(Arquivo arquivo) {
        this.arquivo = arquivo;
    }
 
    public void enviarArquivoServidor() {
        if (validarArquivo()) {
            try {
                socket = new DatagramSocket();
                InetAddress IPAddress = InetAddress.getByName(hostName);
                byte[] incomingData = new byte[1024];
                byte[] bytea = serializarArquivo();
                
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ObjectOutputStream os = new ObjectOutputStream(outputStream);
                os.writeObject(bytea);
                byte[] data = outputStream.toByteArray();
                DatagramPacket sendPacket = new DatagramPacket(data, data.length, IPAddress, 30001);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private byte[] serializarArquivo() {
        try {
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            ObjectOutputStream ous;
            ous = new ObjectOutputStream(bao);
            ous.writeObject(arquivo);
            return bao.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private boolean validarArquivo() {

        if ((arquivo.getTamanhoKB()) > 5120) {
            return false;
        } else {
            return true;
        }
    }    
}
