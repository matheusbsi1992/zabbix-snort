package br.com.ifs.edu.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author linuxserver
 */
public class Util {

    /*criacao do metodo utilidadeData para conversacao da data como o padrao Americano
    e retorne ano mes dia horas min seg
    da forma:yyyy-MM-dd HH:mm:ss
     */
    public Timestamp utillidadeData(String data) throws Exception {

        Date d = (new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).parse(data);
        String Data02 = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(d);
        java.sql.Timestamp dataSQL = java.sql.Timestamp.valueOf(Data02);

        return dataSQL;

    } 
}
