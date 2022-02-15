package br.com.ifs.edu.conexao;

import java.sql.*;

/**
 *
 * @author linuxserver
 */
public class Conexao {
    /*criacao dos atributos a serem relacionado com
     a conexao*/

    public String ip;
    public String banco;
    public String user;
    public String senha;
    public String porta;
    public StringBuilder sql;

    /*atributos de conexao*/
    public PreparedStatement ps;
    public ResultSet rs;
    private Connection con;

    //contrutor vazio
    public Conexao() {

    }

    //contrutor passando parametros
    public Conexao(String ip, String banco, String user, String senha, String porta) {
        this.ip = ip;
        this.banco = banco;
        this.user = user;
        this.senha = senha;
        this.porta = porta;
    }

    //retorno da variavel sql do StringBuilder
    public StringBuilder sqlB() {
        return sql = new StringBuilder();
    }

    /*criacao dos metodos setters e getters*/
    public StringBuilder getSql() {
        return sql;
    }

    public void setSql(StringBuilder sql) {
        this.sql = sql;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }

    public PreparedStatement getPs() {
        return ps;
    }

    public void setPs(PreparedStatement ps) {
        this.ps = ps;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    /*
     *criacao do metodo responsavel pela conexao com o Banco de dados Teste que e
     de responsabilidade do MySQL
     */
    public Connection conectar() {

        try {
            //seta registro caso tenha problemas de conexao
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            //reconhecer biblioteca a ser utilizada
            Class.forName("com.mysql.jdbc.Driver");

            //retorno da conexao passando parametro
            con = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + porta + "/" + banco, user, senha);

        } catch (ClassNotFoundException e) {
            e.getMessage();
        } catch (SQLException e) {
            e.getMessage();
        }
        //retorne conexao casao exista
        return con;
    }

    //metodo para encerrar conexao
    public void desconectar() {
        try {
            con.close();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
}
