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
        tabela.put(16, "read");   
        tabela.put(17, "readln");
        tabela.put(18, "write");
        tabela.put(19, "writeln");
        tabela.put(20, "array");
        tabela.put(21, "of");
        tabela.put(22, "not");
        tabela.put(23, "or");
<<<<<<< HEAD
        tabela.put(24, "and");
        tabela.put(25, "integer");
        tabela.put(26, "real");
        tabela.put(27, "boolean");
        tabela.put(28, "char");
        tabela.put(29, "string");
        
=======
        tabela.put(24, "and");        
        tabela.put(25, "string");
        tabela.put(26, "integer");
        tabela.put(27, "real");
        tabela.put(28, "boolean");
        tabela.put(29, "char");
        tabela.put(30, "float");
>>>>>>> 68fdf7ad6325be4cb2e770aa7f341f65df8fa88e
    }
    
    public Boolean estaContido(String valor) {
        return tabela.containsValue(valor);
    }
    
}
