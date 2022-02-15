package br.com.ifs.edu.leitura;

import br.com.ifs.edu.transfer.Dadosnort;
import br.com.ifs.edu.util.Util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author linuxserver
 */
public class Leitura {

    /*
     *Metodo de leitura e definicao de dados do vetor parametro cmd do tipo String
     */
    public List<Dadosnort> leituraDados() throws Exception {
        //definindo um atributo line
        String line;
        //criacao do atributo utilidade da classe Util
        Util utilidade = new Util();
        //criacao do atributo dadosnort da classe Dadossnort
        Dadosnort dadosnort;
        //criacao do atributo arquivostr para o recebimento dos dados tratados
        List<Dadosnort> arquivostr = new ArrayList<Dadosnort>();
        /*comandos Linux a serem passados para cada uma das posicoes do vetor cmd
        a serem interpretados*/
        String cmd[]            = {"/bin/sh", "-c", "cat /var/log/snort/alert | awk '{print $1,$4,$8,$9,$11}' | sed -r 's/\\.[0-9]{6}//;s/[{}]//g;s/ /,/g;s/:/,/3g; s/-/,/; s/^/'$(date +%Y)'\\//'"};
        
        //criacao do atributo in do tipo BufferedReader
        BufferedReader in = null;
        //criacao do atributo Process para a criacao de um processo no sistema operacional designado
        Process p = null;

        try {
            /*Runtime execute o procedimento informado atraves do parametro cmd*/
            p = Runtime.getRuntime().exec(cmd);
            /*retorno do fluxo de entrada atraves do p.getInputStream()
            atributo in recebe dados*/
            in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            //enquanto existir valores a serem lidos continue
            while ((line = in.readLine()) != null) {
                //criacao do atributo receberdados, para a analise da cadeia de dados por virgula
                String receberdados[] = line.split(",");
                //concatenacao entre a data e horas
                String Data = receberdados[0] + " " + receberdados[1];
                //criacao do atributo aux para posterior analise
                String aux = null;
                //condicao que pergunta se a posicao dos dados nao existirem entao break e encerre
                if (receberdados.length == 0 | null == receberdados[0]) {
                    break;
                } else 
                    /*condicao para o atributo receber dados nao fo vazio e se o atributo "protocolo" 
                    *for igual a icmp crie valores das portas origem e destino com 0000 
                    */
                    if (!receberdados.toString().isEmpty() && receberdados[3].equals("ICMP")) {
                    //construtor recebe dados
                    dadosnort = new Dadosnort(utilidade.utillidadeData(Data), receberdados[2], receberdados[3], receberdados[4], receberdados[5], "0000", "0000");
                    //arquivostr lista recebe dados e armazenar na memoria
                    arquivostr.add(dadosnort);

                } else 
                        //se a condicao for diferente do protocolo ICMP ou nao for vazio
                        if (!receberdados.toString().isEmpty()) {
                    //trocar posicoes entre a porta de origem com ipdestino
                    aux = receberdados[5];
                    receberdados[5] = receberdados[6];
                    receberdados[6] = aux;
                    //construtor recebe dados
                    dadosnort = new Dadosnort(utilidade.utillidadeData(Data), receberdados[2], receberdados[3], receberdados[4], receberdados[5], receberdados[6], receberdados[7]);
                    //grave dados na memoria
                    arquivostr.add(dadosnort);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //encerre valores e mate o processo
                if (in != null && !p.isAlive()) {
                    in.close();
                    p.destroy();
                }
            } catch (Exception ex) {
            }
        }
        //retorne valores da lista na memoria
        return arquivostr;
    } }
