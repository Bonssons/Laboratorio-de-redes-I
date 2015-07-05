/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

/**
 *
 * @author Alysson e Rafael
 */

import java.io.*;
import java.net.*;

public class ServidorTCP {
    
    private void runServer(){
        try{
            ServerSocket servSocket = new ServerSocket(30001);
            System.out.println("Aguardando Conexão . . .");
            Socket socket = servSocket.accept();
            
            byte[] objectAsByte = new byte[socket.getReceiveBufferSize()];
            BufferedInputStream bf = new BufferedInputStream(socket.getInputStream());
            bf.read(objectAsByte);
            
            Arquivo arquivo = (Arquivo) getObjectFromByte(objectAsByte);
            
            String dir = arquivo.getDiretorioDestino().endsWith("/") ? 
                    arquivo.getDiretorioDestino()+ arquivo.getNome() : 
                    arquivo.getDiretorioDestino()+"/"+ arquivo.getNome();
            System.out.println("Escrevendo arquivo em :"+ dir);
            
            FileOutputStream fos = new FileOutputStream(dir);
            fos.write(arquivo.getConteudo());
            fos.close();
            
        }catch(IOException e ){
            e.printStackTrace();
        }
        
       
    }
     private Object getObjectFromByte(byte[] objectAsByte){
        Object obj = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try{
            bis = new ByteArrayInputStream(objectAsByte);
            ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            
            bis.close();
            ois.close();
        }catch(IOException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
         return obj;
    }
    
    
}
