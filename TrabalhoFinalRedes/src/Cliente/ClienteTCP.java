/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;



/**
 *
 * @author Alysson e Rafael
 */
public class ClienteTCP {

    private Arquivo arquivo;

    public ClienteTCP(Arquivo arquivo) {
        this.arquivo = arquivo;
        enviarArquivoServidor();
    }

    private void enviarArquivoServidor() {
        if (validarArquivo()) {
            try {
                Socket socket = new Socket(arquivo.getIpDestino(), 30001);
                BufferedOutputStream bf = new BufferedOutputStream(socket.getOutputStream());
                byte[] bytea = serializarArquivo();
                bf.write(bytea);
                bf.flush();
                bf.close();
                socket.close();
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
            ObjectOutputStream ous = null;
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
