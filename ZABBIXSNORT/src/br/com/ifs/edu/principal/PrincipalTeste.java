package br.com.ifs.edu.principal;

import br.com.ifs.edu.bo.DadosnortBO;
import br.com.ifs.edu.leitura.Leitura;
import br.com.ifs.edu.transfer.Dadosnort;
import br.com.ifs.edu.zabbixsender.Zabbixsender;
import java.util.List;

/**
 *
 * @author linuxserver
 */
public class PrincipalTeste {

    //metodo principal de execucao do projeto
    public static void main(String[] ifsargs) throws Exception {
        //criacao do atributo leitura
        Leitura leitura = new Leitura();
        // criacao do atributo ler do tipo objeto Dadossnort
        List<Dadosnort> ler = leitura.leituraDados();
        //criacao do atributo zbSender
        Zabbixsender zbSender = new Zabbixsender();
        //criacao das atributos "chaves" a serem utilizados pelo Zabbix Sender
        String vetorEventos[] = {"portscan", "dos", "pingflood","portscan.total","dos.total","pingflood.total"};
        //criacao do vetorTotal para a contagem de dados de cada uma dos alertas
        int vetorTotal[] = new int[100];
        //criacao do atributo dadosBO ou classe de negocio
        DadosnortBO dadosBO = new DadosnortBO();

        //criacao de atributo boleano
        boolean achou = true;

        //faz o foreach da  leitura dos extraidos
        for (Dadosnort dados : ler) {
            //achou recebe verdade ou falso
            achou = dadosBO.existeCadastroBO(dados);

            //se a condicao for verdadeira faca alguns processos
            if (achou) {
                /*se a mensagemznort for igual a posicao do vetor que 
                 e o PortsCan faca alguns processos*/
                if (dados.getMensagemznort().equalsIgnoreCase(vetorEventos[0])) {
                    //conte a quantidade de alertas do tipo PORTSCAN
                    vetorTotal[0]++;
                    //passe parametro da chave : portscan e valor do alerta obtido no exato horario
                    zbSender.zabbixSender(vetorEventos[0], vetorTotal[0]);
                } else /*se a mensagemznort for igual a posicao do vetor que 
                 e o DoS faca alguns processos*/ 
                    if (dados.getMensagemznort().equalsIgnoreCase(vetorEventos[1])) {
                    //conte a quantidade de alertas do tipo DoS
                    vetorTotal[1]++;
                    //passe parametro da chave : dos e valor do alerta obtido no exato horario
                    zbSender.zabbixSender(vetorEventos[1], vetorTotal[1]);
                } else /*se a mensagemznort for igual a posicao do vetor que 
                 e o DoS faca alguns processos*/ 
                    if (dados.getMensagemznort().equalsIgnoreCase(vetorEventos[2])) {
                    //conte a quantidade de alertas do tipo PingFlood
                    vetorTotal[2]++;
                    //passe parametro da chave : PingFlood e valor do alerta obtido no exato horario
                    zbSender.zabbixSender(vetorEventos[2], vetorTotal[2]);
                }
                /*posicao 3 recebe o total de alertas gerados atraves 
                 da projecao de obtencao de PortScan*/
                vetorTotal[3] = dadosBO.getItemPortScan();
                zbSender.zabbixSender(vetorEventos[3], vetorTotal[3]);
                /*posicao 4 recebe o total de alertas gerados atraves 
                 da projecao de obtencao de DoS*/
                vetorTotal[4] = dadosBO.getItemDoS();
                zbSender.zabbixSender(vetorEventos[4], vetorTotal[4]);
                /*posicao 5 recebe o total de alertas gerados atraves 
                 da projecao de obtencao de PortScan*/
                vetorTotal[5] = dadosBO.getItemPingFlood();
                zbSender.zabbixSender(vetorEventos[5], vetorTotal[5]);
                /*faca a impressao de cada um dos valores:
                 Mensagem Protocolo IpOrigem IpDestino PortaOrigem PortaDestino
                 QTD Tipos de alertas: PORTSCAN,DOS,PINGFLOOD
                 QTD Total de alertas: PORTSCAN,DOS,PINGFLOOD*/
                System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%d\t%d\t%d\t%d\t%d\t%d\t\n",
                        dados.getMensagemznort(),
                        dados.getProtocoloznort(),
                        dados.getIporigemznort(),
                        dados.getIpdestinoznort(),
                        dados.getPortaorigemznort(),
                        dados.getPortadestinoznort(),
                        vetorTotal[0],
                        vetorTotal[1],
                        vetorTotal[2],
                        vetorTotal[3],
                        vetorTotal[4],
                        vetorTotal[5]);

                //sendo falso passe valor 0, para cada um dos eventos: portscan,dos e pingflood
            } else if (!achou) {
                if (dados.getMensagemznort().equalsIgnoreCase(vetorEventos[0])) {
                    zbSender.zabbixSender(vetorEventos[0], 0);
                } else if (dados.getMensagemznort().equalsIgnoreCase(vetorEventos[1])) {
                    zbSender.zabbixSender(vetorEventos[1], 0);
                } else if (dados.getMensagemznort().equalsIgnoreCase(vetorEventos[2])) {
                    zbSender.zabbixSender(vetorEventos[2], 0);
                }
            }
        }
    }
}
