package br.com.ifs.edu.bo;

import br.com.ifs.edu.dao.DadosnortDAO;
import br.com.ifs.edu.transfer.Dadosnort;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author linuxserver
 */
public class DadosnortBO extends DadosnortDAO {

    /*metodo SelecionarDataHorasAtual do tipo boleano. Retorne verdadeiro
     se as horas do sistema for igual ao passado pelo atributo data retornado
     */
    public boolean selecionarDataHorasAtual(Timestamp data) {

        String dataTempoNovaZnort = (new SimpleDateFormat("dd/MM/yyyy HH:mm")).format(data);
        String dataAgoraSistema = (new SimpleDateFormat("dd/MM/yyyy HH:mm")).format(new Date());
        if (dataTempoNovaZnort.equals(dataAgoraSistema)) {
            return true;
        }
        return false;
    }
    /*metodo existeCadastroBO responsavel pelos dados da classe DaO
     */

    public boolean existeCadastroBO(Dadosnort dadosnort) throws Exception {
        /*condicao pergunta se as horas do sistema for igual ao informando entao passe valores
         ao objeto dadosnort da classe DadosnotrDAO e retorne veradeiro senao retorne falso
         */
        if (selecionarDataHorasAtual(dadosnort.getDatatempoznort())) {
            new DadosnortDAO().cadastroDadoSnort(dadosnort);
            return true;
        }
        return false;
    }

    //metodo para o retorno total de alertas do item PortScan
    public int getItemPortScan() throws Exception {
        //criacao do atributo valorPortScan para o recebimento dos dados o metodo itemPortScan
        int valorPortScan = itemPortScan();
        //condicao se for maior do que zero retorne valores
        if (valorPortScan > 0) {
            return valorPortScan;
        }
        //senao retorne so zero
        return 0;
    }

    //metodo para o retorno total de alertas da regra DoS

    public int getItemDoS() throws Exception {
        //criacao do atributo valorPortScan para o recebimento dos dados o metodo itemPortScan
        int valorDoS = itemDoS();
        //condicao se for maior do que zero retorne valores
        if (valorDoS > 0) {
            return valorDoS;
        }
        //senao retorne so zero
        return 0;
    }

    //metodo para o retorno total de alertas da regra PingFlood
    public int getItemPingFlood() throws Exception {
        //criacao do atributo valorPingFlood para o recebimento dos dados o metodo itemPingFlood
        int valorPingFlood = itemPingFlood();
        //condicao para retornar valores maiores do que zero
        if (valorPingFlood > 0) {
            return valorPingFlood;
        }
        //senao retorne so zero
        return 0;
    }
}
