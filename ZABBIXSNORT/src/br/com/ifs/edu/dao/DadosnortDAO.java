package br.com.ifs.edu.dao;

import br.com.ifs.edu.conexao.Conexao;
import br.com.ifs.edu.transfer.Dadosnort;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author linuxserver
 */
public class DadosnortDAO extends Conexao {

    /*criacao do atributo conectar, passando parametros para conexao ao banco.
     */
    private static final Conexao conectar = new Conexao("127.0.0.1", "Teste", "root", "root", "3306");

    //criacao do metodo de insercao cadastoDadoSnort passando o como parameto objeto Dadosnort
    public boolean cadastroDadoSnort(Dadosnort dados) throws Exception {
        //criacado do insert para a tabela designada, com a utilizacao do stringbiulder
        sqlB().append("insert into znort(datatempoznort, mensagemznort, protocoloznort, iporigemznort,ipdestinoznort,portaorigemznort,portadestinoznort)values (?,?,?,?,?,?,?)");

        try {
            //prepareStatement responsavel por executar comandos SQL
            ps = (conectar.conectar().prepareStatement((getSql().toString())));
            //criacao do atributo tam, sem a necessidade de indicar o local das colunas
            int tam = 1;
            //prepareStatement receba informacoes necessarias para insercao
            ps.setObject(tam++, dados.getDatatempoznort());
            ps.setObject(tam++, dados.getMensagemznort());
            ps.setObject(tam++, dados.getProtocoloznort());
            ps.setObject(tam++, dados.getIporigemznort());
            ps.setObject(tam++, dados.getIpdestinoznort());
            ps.setObject(tam++, dados.getPortaorigemznort());
            ps.setObject(tam++, dados.getPortadestinoznort());

            //execute valores e insira no banco
            return ps.execute();

        } catch (Exception e) {
            throw e;
        } finally {

            try {
                //encerra prepareStatement
                ps.close();
            } catch (Exception e) {
                e.getMessage();
            }
            try {
                //encerra conexao
                desconectar();
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }

    //criacao do metodo itemPortScan nao intencao de obter o total de regras ou ataques PortScan
    public int itemPortScan() throws Exception {

        //comando SQL simples na obtecao da mensagemznort = PORTSCAN
        sqlB().append("SELECT          (mensagemznort)\n"
                + "    FROM     	znort\n"
                + "    WHERE 		mensagemznort='portscan'");

        //criacao do atributo retornarqtdPortScan
        int retornarqtdPortScan = 0;

        try {
            //abri conexao e passa valores atraves decomandos SQL para o prepareStatement
            ps = (conectar.conectar().prepareStatement((getSql().toString())));

            //guarda resultados da projecao no atributo rs do tipo Resultset
            rs = ps.executeQuery();

            while (rs.next()) {
                //indentifica coluna
                rs.getString("mensagemznort");
                //conte a quantidade de vezes que aparece o alerta
                retornarqtdPortScan++;
            }

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.getMessage();
            }
            try {
                //encerre prepareStatement
                ps.close();
            } catch (Exception e) {
                e.getMessage();
            }
            try {
                //desconectar conexao
                desconectar();
            } catch (Exception e) {
                e.getMessage();
            }
        }
        //retorne quantidade de valores
        return retornarqtdPortScan;
    }

    //criacao do metodo itemDoS na obtencao de ter o total das regras ou alertas DoS
    public int itemDoS() throws Exception {
        //comando SQL simples na obtecao da mensagemznort = DoS
        sqlB().append("SELECT 	(mensagemznort)\n"
                + "FROM     	znort\n"
                + "WHERE		mensagemznort='dos'");
        //criacao do atributo retornarqtdDoS do tipo int
        int retornarqtdDoS = 0;
        try {
            //abri conexao e passa valores atraves decomandos SQL para o prepareStatement
            ps = (conectar.conectar().prepareStatement((getSql().toString())));

            //guarda resultados da projecao no atributo rs do tipo Resultset
            rs = ps.executeQuery();

            while (rs.next()) {
                //indentifica coluna
                rs.getString("mensagemznort");
                //conte a quantidade de vezes que aparece o alerta
                retornarqtdDoS++;
            }

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                //encerre Resultset
                rs.close();
            } catch (SQLException e) {
                e.getMessage();
            }
            try {
                //encerre prepareStatement
                ps.close();
            } catch (Exception e) {
                e.getMessage();
            }
            try {
                //encerra conexao
                desconectar();
            } catch (Exception e) {
                e.getMessage();
            }
        }
        //retorne quantidade de valores
        return retornarqtdDoS;
    }

    public int itemPingFlood() throws Exception {
        //comando SQL simples na obtecao da mensagemznort = pingflood
        sqlB().append("SELECT 	(mensagemznort)\n"
                + "FROM     	znort\n"
                + "WHERE		mensagemznort='pingflood'");
        //criacao do atributo retornarqtdPingFlood do tipo int
        int retornarqtdPingFlood = 0;
        try {

            ps = (conectar.conectar().prepareStatement((getSql().toString())));

            //guarda resultados da projecao no atributo rs do tipo Resultset
            rs = ps.executeQuery();

            while (rs.next()) {
                //indentifica coluna
                rs.getString("mensagemznort");
                //conte a quantidade de vezes que aparece o alerta
                retornarqtdPingFlood++;
            }

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                //encerre Resultset
                rs.close();
            } catch (SQLException e) {
                e.getMessage();
            }
            try {
                //encerre prepareStatement
                ps.close();
            } catch (Exception e) {
                e.getMessage();
            }
            try {
                //encerra conexao
                desconectar();
            } catch (Exception e) {
                e.getMessage();
            }
        }
        //retorne quantidade de valores
        return retornarqtdPingFlood;
    }

}
