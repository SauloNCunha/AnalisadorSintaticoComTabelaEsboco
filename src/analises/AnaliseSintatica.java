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
            LISTA_ID();
            if (token.getStr().toString().equals(";")){
                return;
            }
            else ERRO(";", token.getStr().toString());
        }
        else return;// lambida
            
    }
    
    public void LISTA_ID() {
        token = mt.geraToken();
        if(token.getTipo() == TiposToken.ID){
            LISTA_ID2();
        }
        else 
            ERRO("identificar", token.getStr().toString());   
    }
    
    public void LISTA_ID2() {
        token = mt.geraToken();
        
        if (token.getStr().toString().equals(",")){
            LISTA_ID();
        }
        else return;
        
    }
    
    public void CONST() {
        token = mt.geraToken();
        
        if (token.getStr().toString().equals("const")){
            DEC_CONST();
        }
        else return;// lambida
    }
    
    public void DEC_CONST() {
        token = mt.geraToken();
        if(token.getTipo()== TiposToken.ID){
            token = mt.geraToken();
            if (token.getStr().toString().equals("=")){
                //EXP_ATRIB();
                if (token.getStr().toString().equals(";")){
                    DEC_CONST2();
                }
                else ERRO(";", token.getStr().toString());
            }
        }
    }
    
    public void DEC_CONST2() {
        
        return;
    }
    
    public void VAR() {
        token = mt.geraToken();
        if (token.getStr().toString().equals("var")){
            DEC_VAR();
        }
        else return;
    }
    
    public void DEC_VAR(){
        token = mt.geraToken();
        if(token.getTipo()== TiposToken.ID){
            LISTA_ID();
            if(token.getStr().toString().equals(":")){
                
            }
        }
    }
    
    
    public void FOR() {
        token = mt.geraToken();
        if (token.getStr().toString().equals("for")){
            ATRIB();
           
            
        }
     return;
    }
    
    public void ATRIB (){
        token = mt.geraToken();
        if (token.getStr().toString().equals("id")){
            EXPATRIB();
           
            
        }
        return;
    }
    
    public void EXPATRIB(){
        token = mt.geraToken();
        MUL();        
    }    
    
    public void MUL(){
       token = mt.geraToken();
       DIV();
       MUL2();
    }
    
    public void DIV(){
        token = mt.geraToken();
        SOM();
        DIV2();
    }
    
    public void MUL2(){
        token = mt.geraToken();
        if(token.getStr().toString().equals("*")){
           MUL(); 
        } 
        else
            return;
    }
    
    public void SOM(){
        token = mt.geraToken();
        SUB();
        SOM2();
    }
    
    public void DIV2(){
        token = mt.geraToken();
        if(token.getStr().toString().equals("/")){
           DIV(); 
        } 
        else
            return;
    }
    
    public void SUB(){
        token = mt.geraToken();
        OPERANDO_ATRIB();
        SUB2();
    }
    
    public void SOM2(){
       token = mt.geraToken();
       if(token.getStr().toString().equals("+")){
          SOM(); 
       } 
        else
            return; 
    }
    
    public void SUB2(){
       token = mt.geraToken(); 
       if(token.getStr().toString().equals("-")){
          SUB(); 
       } 
        else
            return; 
    }
    
    public void OPERANDO_ATRIB(){
       token = mt.geraToken();
       if(token.getTipo() == TiposToken.ID){
          return;
       }else if(token.getTipo() == TiposToken.CTE){
          return;
       }else if(token.getStr().toString().equals("(")){
           MUL();
           if (token.getStr().toString().equals(")")){
               return;
           }
       }       
    }
    
    public void ERRO(String esperado, String obtido) {
        JOptionPane.showMessageDialog(null, "Ocorreu um erro na análise! Era esperado " + esperado + " e foi obtido " + obtido);
        return;
    }
}
