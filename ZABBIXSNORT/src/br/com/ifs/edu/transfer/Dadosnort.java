package br.com.ifs.edu.transfer;

import java.sql.Timestamp;

/**
 *
 * @author linuxserver
 */
public class Dadosnort {
    /*criacao dos atributos*/

    private int codigoznort;
    private Timestamp datatempoznort;
    private String mensagemznort;
    private String protocoloznort;
    private String iporigemznort;
    private String ipdestinoznort;
    private String portaorigemznort;
    private String portadestinoznort;

    /*criacao do construtor Dadosnort passamdo parametros*/
    public Dadosnort(Timestamp datatempoznort, String mensagemznort, String protocoloznort, String iporigemznort, String ipdestinoznort, String portaorigemznort, String portadestinoznort) {
        this.datatempoznort = datatempoznort;
        this.mensagemznort = mensagemznort;
        this.protocoloznort = protocoloznort;
        this.iporigemznort = iporigemznort;
        this.ipdestinoznort = ipdestinoznort;
        this.portaorigemznort = portaorigemznort;
        this.portadestinoznort = portadestinoznort;
    }

    /*criacao do construtor Dadosnort vazio*/
    public Dadosnort() {

    }

    /*criacao dos metodos getters e setters*/
    public int getCodigoznort() {
        return codigoznort;
    }

    public void setCodigoznort(int codigoznort) {
        this.codigoznort = codigoznort;
    }

    public Timestamp getDatatempoznort() {
        return datatempoznort;
    }

    public void setDatatempoznort(Timestamp datatempoznort) {
        this.datatempoznort = datatempoznort;
    }

    public String getMensagemznort() {
        return mensagemznort;
    }

    public void setMensagemznort(String mensagemznort) {
        this.mensagemznort = mensagemznort;
    }

    public String getProtocoloznort() {
        return protocoloznort;
    }

    public void setProtocoloznort(String protocoloznort) {
        this.protocoloznort = protocoloznort;
    }

    public String getIporigemznort() {
        return iporigemznort;
    }

    public void setIporigemznort(String iporigemznort) {
        this.iporigemznort = iporigemznort;
    }

    public String getIpdestinoznort() {
        return ipdestinoznort;
    }

    public void setIpdestinoznort(String ipdestinoznort) {
        this.ipdestinoznort = ipdestinoznort;
    }

    public String getPortaorigemznort() {
        return portaorigemznort;
    }

    public void setPortaorigemznort(String portaorigemznort) {
        this.portaorigemznort = portaorigemznort;
    }

    public String getPortadestinoznort() {
        return portadestinoznort;
    }

    public void setPortadestinoznort(String portadestinoznort) {
        this.portadestinoznort = portadestinoznort;
    }
}
