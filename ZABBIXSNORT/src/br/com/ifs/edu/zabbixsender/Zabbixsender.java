package br.com.ifs.edu.zabbixsender;

import java.io.IOException;

/**
 *
 * @author linuxserver
 */
public class Zabbixsender {

    /*metodo responsavel por fazer a comunicacao com atraves do Zabbix_Sender.
    Informando a chave existente na web do zabbix*/
    public void zabbixSender(String chave, int valorexistente) throws IOException {
        /*comando Zabbix Sender direcionado para o servidor 127.0.0.1, para o host Zabbix server chave e valor a ser obtido*/
        String cmd[] = {"/bin/sh", "-c", "zabbix_sender -c /usr/local/etc/zabbix_agentd.conf -z 127.0.0.1 -p 10051 -s 'Zabbix server' -k " + chave + " -o " + valorexistente};
        //criacao do atributo processar
        Process processar = null;
        //criacao do atributo p da classe ProcessBuilder
        ProcessBuilder p = new ProcessBuilder();
        //atraves do p.command e reconhecido os comando
        p.command(cmd);
        try {
            //processe estes comandos
            processar = p.start();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //feche o processo
                if (!processar.isAlive()) {
                    processar.destroyForcibly().exitValue();
                }
            } catch (Exception ex) {
                throw ex;
            }
        }
    }
}