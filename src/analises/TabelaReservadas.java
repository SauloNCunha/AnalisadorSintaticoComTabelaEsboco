/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analises;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author valter
 */
public class TabelaReservadas {
    Map<Integer, String> tabela = new HashMap<Integer, String>();
    
    public TabelaReservadas() {
        tabela.put(1, "program");
        tabela.put(2, "uses");
        tabela.put(3, "begin");
        tabela.put(4, "end");
        tabela.put(5, "if");
        tabela.put(6, "then");
        tabela.put(7, "else");
        tabela.put(8, "for");
        tabela.put(9, "to");
        tabela.put(10, "do");
        tabela.put(11, "while");
        tabela.put(12, "repeat");
        tabela.put(13, "until");
        tabela.put(14, "const");
        tabela.put(15, "var");
        tabela.put(16, "define");
        tabela.put(17, "use");
        tabela.put(18, "key");
        tabela.put(19, "readkey");   
        
    }
    
    public Boolean estaContido(String valor) {
        return tabela.containsValue(valor);
    }
    
}
