package analises;

import javax.swing.JOptionPane;

public class AnaliseSintatica {
    private Token token;
    private AnaliseLexica mt;
    
    public AnaliseSintatica(String codigo) {
        this.token = new Token();
        this.mt = new AnaliseLexica(codigo);
        PROGRAM();
    }
    
    public void PROGRAM() {
        token = mt.geraToken();
        if (token.getStr().toString().equals("program")){
            token = mt.geraToken();
            if(token.getTipo()== TiposToken.ID){
                mt.getTabelaIdenticadores().adiciona(token.getStr().toString());//Adiciona o Token
                token = mt.geraToken();
                if (token.getStr().toString().equals(";")){
                    LIB();
                    VAR();
                    token = mt.geraToken();
                    
                    if (token.getStr().toString().equals("begin")){
                        token = mt.geraToken();
                        
                        if (token.getStr().toString().equals("end")){
                           token = mt.geraToken();
                           
                            if (token.getStr().toString().equals(".")){
                                JOptionPane.showMessageDialog(null,"Arquivo Compilado com sucesso");
                                return;
                            }
                            else
                                ERRO(".",token.getStr().toString());
                        }
                        else 
                            ERRO("end",token.getStr().toString());                        
                    }
                    else 
                        ERRO("begin",token.getStr().toString());
                }
                else 
                    ERRO(";",token.getStr().toString());
            }
            else
                ERRO("indentificar",token.getStr().toString());
        }
        else
            ERRO("program",token.getStr().toString());
        
    }
    
    public void LIB() {
        token = mt.geraToken();
        
        if (token.getStr().toString().equals("uses")){
            LISTA_LIB();
            if (token.getStr().toString().equals(";")){
                return;
            }
            else ERRO(";", token.getStr().toString());
        }
        else return;// lambida
            
    }
    
    public void LISTA_LIB() {
        token = mt.geraToken();
        if(token.getTipo() == TiposToken.ID){
            LISTA_LIB2();
        }
        else 
            ERRO("identificar", token.getStr().toString());   
    }
    
    public void LISTA_LIB2() {
        token = mt.geraToken();
        
        if (token.getStr().toString().equals(",")){
            LISTA_LIB();
        }
        else return;
        
    }
    
    public void VAR() {
     return;
    }
    
    public void ERRO(String esperado, String obtido) {
        JOptionPane.showMessageDialog(null, "Ocorreu um erro na análise! Era esperado " + esperado + " e foi obtido " + obtido);
        return;
    }
}
