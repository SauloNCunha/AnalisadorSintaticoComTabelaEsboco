package analises;

import java.util.HashMap;
import java.util.Map;

public class TabelaIdenticadores {
    private Map<Integer, String> tabela;
    private int key;
     
    public TabelaIdenticadores() {
         this.tabela = new HashMap<Integer, String>();
         this.key = 1;
    }
     
    public void adiciona(String valor) {
        if (!tabela.containsValue(valor)) {
            tabela.put(key, valor);
            key++;
        }
    } 
    
    public Boolean estaContido(String valor) {
        return tabela.containsValue(valor);
    }
}
