/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Alysson e Rafael
 * 
 */
public class Arquivo implements Serializable {
    
    private static final long serialVersionUID = 1L; 
    
    private String nome; 
    private byte[] conteudo; 
    private String diretorioDestino;
    private Date dataHoraUpload;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public byte[] getConteudo() {
        return conteudo;
    }

    public void setConteudo(byte[] conteudo) {
        this.conteudo = conteudo;
    }

    public String getDiretorioDestino() {
        return diretorioDestino;
    }

    public void setDiretorioDestino(String diretorioDestino) {
        this.diretorioDestino = diretorioDestino;
    }

    public Date getDataHoraUpload() {
        return dataHoraUpload;
    }

    public void setDataHoraUpload(Date dataHoraUpload) {
        this.dataHoraUpload = dataHoraUpload;
    }
    
    

}
