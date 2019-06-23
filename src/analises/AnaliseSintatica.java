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
                    CONST();
                    VAR();
                                        
                    if (token.getStr().toString().equals("begin")){
                        token = mt.geraToken();
                        LISTA_COM();
                        
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
            token = mt.geraToken();
            LISTA_ID();
            //token = mt.geraToken();
            if (token.getStr().toString().equals(";")){
                return;
            }
            else ERRO(";", token.getStr().toString());
        }
        else return;// lambida
            
    }
    
    public void LISTA_ID() {
        //token = mt.geraToken();
        if(token.getTipo() == TiposToken.ID){
            token = mt.geraToken();
            LISTA_ID2();
        }
        else 
            ERRO("identificar", token.getStr().toString());   
    }
    
    public void LISTA_ID2() {
        //token = mt.geraToken();
        
        if (token.getStr().toString().equals(",")){
            token = mt.geraToken();
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
                token = mt.geraToken();
                EXP_ATRIB();
                //token = mt.geraToken();
                if (token.getStr().toString().equals(";")){
                    DEC_CONST2();
                }
                else ERRO(";", token.getStr().toString());
            }
        }
    }
    
    public void DEC_CONST2() {
        DEC_CONST();
        return;
    }
    
    public void VAR() {
        //token = mt.geraToken();
        if (token.getStr().toString().equals("var")){
            token = mt.geraToken();
            DEC_VAR();
        }
        else return;
    }
    
    public void DEC_VAR(){
        //token = mt.geraToken();
        LISTA_ID();
        
        if(token.getStr().toString().equals(":")){
            DEC_VAR2();
            
            if(token.getStr().toString().equals(":")){
                DEC_VAR2();
            }
        }else if (token.getStr().toString().equals("^")){
            LISTA_PONT(); 
            token = mt.geraToken();            
            if(token.getStr().toString().equals(":")){
                DEC_VAR2();
            }else {
                ERRO(":", token.getStr().toString());
            }
        }
    }
    
    public void DEC_VAR2(){
        token = mt.geraToken();
        if(token.getStr().toString().equals("integer") || token.getStr().toString().equals("real") || 
            token.getStr().toString().equals("boolean") || token.getStr().toString().equals("char")|| 
            token.getStr().toString().equals("string") || token.getStr().toString().equals("float")){
            TIPO();
            token = mt.geraToken();
            if(token.getStr().toString().equals(";")){
                token = mt.geraToken();
                DEC_VAR3();
                
            }
            else ERRO(";", token.getStr().toString());
        }
        else
            if(token.getStr().toString().equals("array")){
                token = mt.geraToken();
            
                if(token.getStr().toString().equals("[")){
                    INTERVALO();
                    //token = mt.geraToken();
                    if (token.getStr().toString().equals("]")){
                       token = mt.geraToken();
                       if (token.getStr().toString().equals("of")){
                           token = mt.geraToken();
                           TIPO();
                           token = mt.geraToken();
                           if (token.getStr().toString().equals(";")){
                               token = mt.geraToken();
                               return;
                           }
                           else ERRO(";", token.getStr().toString());
                       }
                       else ERRO("of", token.getStr().toString());
                    }
                    else ERRO("]", token.getStr().toString());
                }
                else ERRO("[", token.getStr().toString());
            }
            else ERRO("array", token.getStr().toString());
    }
    
public void DEC_VAR3(){  
        if(token.getTipo()== TiposToken.ID){
            //token = mt.geraToken();
            DEC_VAR();
            
        }
        else return;
    }

    
    public void TIPO(){
        if(token.getStr().toString().equals("integer") || token.getStr().toString().equals("real") || 
            token.getStr().toString().equals("boolean") || token.getStr().toString().equals("char")|| 
            token.getStr().toString().equals("string")|| token.getStr().toString().equals("float")){
            return;
        }
        else ERRO("tipo", token.getStr().toString());
    }
    
    public void INTERVALO(){
        token = mt.geraToken();
        
       // JOptionPane.showMessageDialog(null, "1 Token: "+token.getStr()+" Tipo: "+token.getTipo());
        if (token.getTipo()== TiposToken.CTE){
           // JOptionPane.showMessageDialog(null, "2 Token: "+token.getStr()+" Tipo: "+token.getTipo());
            token = mt.geraToken();
            if (token.getStr().toString().equals(".")){
               // token = mt.geraToken();
               // if (token.getStr().toString().equals(".")){
                    token = mt.geraToken();
                    if (token.getTipo()== TiposToken.CTE){
                        INTERVALO2();
                    }
              //  }
            }
        }
    }
    
    public void INTERVALO2(){
        token = mt.geraToken();
        if (token.getStr().toString().equals(",")){
            
            token = mt.geraToken();
            
            if (token.getTipo()== TiposToken.CTE){
                
                token = mt.geraToken();
                if (token.getStr().toString().equals(".")){
                    token = mt.geraToken();
                    if (token.getStr().toString().equals(".")){
                        token = mt.geraToken();
                        if (token.getTipo()== TiposToken.CTE){
                            return;
                        }
                        else ERRO("cte", token.getStr().toString());
                    }
                    else ERRO(".", token.getStr().toString());
                }
                else ERRO(".", token.getStr().toString());
            }
            else ERRO("cte", token.getStr().toString());
        }
        else return;
    }
    
   public void LISTA_PONT(){
               
        if (token.getTipo()== TiposToken.ID){
            LISTA_PONT2();
        }else{
            
        }
        
    }
    
    public void LISTA_PONT2(){
        token = mt.geraToken();
        if (token.getStr().toString().equals(",")){
            LISTA_PONT3();
        }
        else return;
    }
    
    public void LISTA_PONT3(){
        token = mt.geraToken();
        if (token.getStr().toString().equals("^")){
            LISTA_PONT();
        }
        else if (token.getTipo()== TiposToken.ID){
            LISTA_ID();
        }
    }
    
    public void LISTA_COM(){        
        COMANDO();
        token = mt.geraToken();
        LISTA_COM();
        return;
    }
    
    public void COMANDO(){
        //token = mt.geraToken();///verificar aqui talvez seja necessario adicioanr ele nas chamadas e não aqui 
        if (token.getTipo()== TiposToken.ID){
            ATRIB();
            token = mt.geraToken();
            if (token.getStr().toString().equals(";")){
                return;
            }
            else ERRO(";", token.getStr().toString());
        }
        else if ((token.getStr().toString().equals("read")) || (token.getStr().toString().equals("readln"))){
            ENTRADA();
            token = mt.geraToken();
            if (token.getStr().toString().equals(";")){
                return;
            }
            else ERRO(";", token.getStr().toString());
        }
        else if ((token.getStr().toString().equals("write")) || (token.getStr().toString().equals("writeln"))){
            SAIDA();
            token = mt.geraToken();
            if (token.getStr().toString().equals(";")){
                return;
            }
            else ERRO(";", token.getStr().toString());
        }
        else 
            if (token.getStr().toString().equals("for")){
            FOR();
        }
        else 
            if (token.getStr().toString().equals("while")){
            WHILE();
        }
        else 
            if (token.getStr().toString().equals("repeat")){
            REPEAT();
            token = mt.geraToken();
            if (token.getStr().toString().equals(";")){
                return;
            }
            else ERRO(";", token.getStr().toString());
        }
        else 
            if (token.getStr().toString().equals("if")){
            IF();
            token = mt.geraToken();
            if (token.getStr().toString().equals(";")){
                return;
            }
            else ERRO(";", token.getStr().toString());
        }
    }
    
    public void ESCOPO(){
        //token = mt.geraToken();
        if (token.getStr().toString().equals("begin")){
            token = mt.geraToken();
            LISTA_COM();
            token = mt.geraToken();
            if (token.getStr().toString().equals("end")){
                token = mt.geraToken();
                if (token.getStr().toString().equals(";")){
                    return;
                }
                else ERRO(";", token.getStr().toString());
            }
            else ERRO("end", token.getStr().toString());
        }
        else ERRO("begin", token.getStr().toString());
    }
    
    public void CORPO(){
        token = mt.geraToken();
        if(token.getTipo()== TiposToken.ID ||
        token.getStr().toString().equals("read") ||
        token.getStr().toString().equals("write") ||
        token.getStr().toString().equals("for") ||
        token.getStr().toString().equals("while") ||
        token.getStr().toString().equals("repeat") ||
        token.getStr().toString().equals("if")){
            token = mt.geraToken();
            COMANDO();
        }
        else
            if(token.getStr().toString().equals("begin")){
                ESCOPO();
            }
    }
    
    public void COMANDO2(){
        //token = mt.geraToken();
        if (token.getTipo()== TiposToken.ID){
            ATRIB();
        }
        else 
            if (token.getStr().toString().equals("read")){
            ENTRADA();
        }
        else 
            if (token.getStr().toString().equals("write")){
            SAIDA();
        }
        else if (token.getStr().toString().equals("readln")){
            ENTRADA();
        }
        else if (token.getStr().toString().equals("writeln")){
            SAIDA();
        }
        else 
            if (token.getStr().toString().equals("for")){
            FOR2();
        }
        else 
            if (token.getStr().toString().equals("while")){
            WHILE2();
        }
        else 
            if (token.getStr().toString().equals("repeat")){
            REPEAT();
        }
        else 
            if (token.getStr().toString().equals("if")){
            IF();
        }
    }
    
    public void ESCOPO2(){
        token = mt.geraToken();
        if (token.getStr().toString().equals("begin")){
            token = mt.geraToken();
            LISTA_COM();
            token = mt.geraToken();
            if (token.getStr().toString().equals("end")){
                return;
            }
            else ERRO("end", token.getStr().toString());
        }
        else ERRO("begin", token.getStr().toString());
    }
    
    public void CORPO2(){
        token = mt.geraToken();
        if(token.getTipo()== TiposToken.ID ||
        token.getStr().toString().equals("read") ||
        token.getStr().toString().equals("write") ||
        token.getStr().toString().equals("readln") ||
        token.getStr().toString().equals("writeln") ||
        token.getStr().toString().equals("for") ||
        token.getStr().toString().equals("while") ||
        token.getStr().toString().equals("repeat") ||
        token.getStr().toString().equals("if")){
            COMANDO2();
        }
        else
            if(token.getStr().toString().equals("begin")){
                ESCOPO2();
            }
    }
    
    public void TERMO(){
        token = mt.geraToken();
        if(token.getTipo()== TiposToken.ID || token.getTipo()== TiposToken.CTE){
            return;
        }
        else ERRO("identificador/cte", token.getStr().toString());
    }
    
    public void LISTA_TERMO(){
        token = mt.geraToken();
        if(token.getStr().toString().equals(",")){
            TERMO();
            LISTA_TERMO();
        }
        else return;
    }

    
    public void FOR() {
        //token = mt.geraToken();
        if (token.getStr().toString().equals("for")){
            token = mt.geraToken();
            ATRIB();
           // token = mt.geraToken();
            if (token.getStr().toString().equals("to")){
                token = mt.geraToken();
                EXP_ATRIB();
                
                if (token.getStr().toString().equals("do")){
                    CORPO();
                    token = mt.geraToken();
                }
                else ERRO("do", token.getStr().toString());
            }
            else ERRO("to", token.getStr().toString());
        }
        else ERRO("for", token.getStr().toString());
    }
    
    public void FOR2() {
        //token = mt.geraToken();
        if (token.getStr().toString().equals("for")){
            token = mt.geraToken();
            ATRIB();
            token = mt.geraToken();
            if (token.getStr().toString().equals("to")){
                EXP_ATRIB();
                token = mt.geraToken();
                if (token.getStr().toString().equals("do")){
                    CORPO2();
                }
                else ERRO("do", token.getStr().toString());
            }
            else ERRO("to", token.getStr().toString());
        }
        else ERRO("for", token.getStr().toString());
    }
    
    public void ATRIB(){
        //token = mt.geraToken();
        if (token.getTipo()== TiposToken.ID){
            token = mt.geraToken();
            if(token.getStr().toString().equals(":=")){
                token = mt.geraToken();
                EXP_ATRIB();
            }
            else ERRO(":=", token.getStr().toString());
        }
        else ERRO("identificador", token.getStr().toString());
    }
    
    public void EXP_ATRIB(){
        MUL();        
    }    
    
    public void MUL(){
       DIV();
       MUL2();
    }
    
    public void DIV(){
        SOM();
        DIV2();
    }
    
    public void MUL2(){
        //token = mt.geraToken();
        if(token.getStr().toString().equals("*")){
            token = mt.geraToken();
            MUL(); 
            
        } 
        else
            return;
    }
    
    public void SOM(){
        SUB();
        SOM2();
    }
    
    public void DIV2(){
        //token = mt.geraToken();
        if(token.getStr().toString().equals("/")){
           token = mt.geraToken();
            DIV(); 
        } 
        else
            return;
    }
    
    public void SUB(){
        OPERANDO_ATRIB();
        SUB2();
    }
    
    public void SOM2(){
       //token = mt.geraToken();
       if(token.getStr().toString().equals("+")){
          token = mt.geraToken();
           SOM(); 
       } 
        else
            return; 
    }
    
    public void SUB2(){
       //token = mt.geraToken(); 
       if(token.getStr().toString().equals("-")){
          
           token = mt.geraToken();
           SUB(); 
       } 
        else
            return; 
    }
    
    public void OPERANDO_ATRIB(){
       //token = mt.geraToken();
       if(token.getTipo() == TiposToken.ID){
          return;
       }else if(token.getTipo() == TiposToken.CTE){
           token = mt.geraToken();
           return;
       }else if(token.getStr().toString().equals("(")){
           token = mt.geraToken();
           MUL();
           //token = mt.geraToken();
           if (token.getStr().toString().equals(")")){
               return;
           }
       }       
    }
    
    public void EXP_BOOL(){
      // token = mt.geraToken();
       AND();
    } 
    
    public void AND(){
       token = mt.geraToken();
       OR();
       AND2();
    }
    
    public void OR(){
        //token = mt.geraToken();
        OPERANDO_BOOL();
        OR2();
    }
    
    public void AND2(){
        //token = mt.geraToken();        
        if (token.getStr().toString().equals("and")) {
            AND();
        } else return;
    }
    
    public void OR2(){
        token = mt.geraToken();
        if(token.getStr().toString().equals("or")){
          OR();
        }else{
            return;
        }        
    }
    
    public void OPERANDO_BOOL(){
         token = mt.geraToken();
         if ((token.getTipo() == TiposToken.ID) || (token.getTipo()== TiposToken.CTE) || (token.getStr().toString().equals("("))){
           EXP_REL();  
         }else if(token.getStr().toString().equals("not")){
             OPERANDO_BOOL();            
         } else ERRO("identificador, constante, ( ou not", token.getStr().toString());   
    }
    
    public void EXP_REL(){
      //token = mt.geraToken();
      EXP_ATRIB();
      OP_REL();
      EXP_ATRIB();
    }
    
    public void OP_REL(){
        token = mt.geraToken();
        if(token.getTipo()== TiposToken.PONTUACAO ){
            token = mt.geraToken();
            return;
        }
        /*if((token.getStr().toString().equals("=")) ) {
            token = mt.geraToken();
            return;
        }else if (token.getStr().toString().equals("<>")){
            token = mt.geraToken();
            return;
        }else if (token.getStr().toString().equals(">")){
            token = mt.geraToken();
            return;
        }else if (token.getStr().toString().equals("<")){
            token = mt.geraToken();
            return;
        }else if (token.getStr().toString().equals(">=")){
            token = mt.geraToken();
            return;               
        }else if (token.getStr().toString().equals("<=")){
            token = mt.geraToken();
            return;               
        }    */
    }
    
    public void IF(){
        //oken = mt.geraToken();
        if (token.getStr().toString().equals("if")){
            
           EXP_BOOL();
           token = mt.geraToken();
           if (token.getStr().toString().equals("then")){
               CORPO2();
               IF2();
           }
           else ERRO("then", token.getStr().toString());
        }
        else ERRO("if", token.getStr().toString());
    }
    
    public void IF2(){
        token = mt.geraToken();
        if (token.getStr().toString().equals("else")){
            CORPO2();
        }else {
            return;
        }     
    }
    
    public void WHILE(){
        token = mt.geraToken();
        if (token.getStr().toString().equals("while")){
           EXP_BOOL();
           token = mt.geraToken();
           if (token.getStr().toString().equals("do")){
               CORPO();
           }
           else ERRO("do", token.getStr().toString());
        }
        else ERRO("while", token.getStr().toString());
    }
    
    public void WHILE2(){
        token = mt.geraToken();
        if (token.getStr().toString().equals("while")){
           EXP_BOOL();
           token = mt.geraToken();
           if (token.getStr().toString().equals("do")){
               CORPO2();
           }
           else ERRO("do", token.getStr().toString());
        }
        else ERRO("while", token.getStr().toString());
    }
    
    public void REPEAT(){
        token = mt.geraToken();
        if (token.getStr().toString().equals("repeat")){
            token = mt.geraToken();
           LISTA_COM();
           token = mt.geraToken();
           if (token.getStr().toString().equals("until")){
               EXP_BOOL();
           }
           else ERRO("do", token.getStr().toString());
        }
        else ERRO("while", token.getStr().toString());
    }
    
    public void SAIDA(){
        //token = mt.geraToken();
        if(token.getStr().toString().equals("write")){
           token = mt.geraToken();
           if(token.getStr().toString().equals("(")){
                TERMO();
                LISTA_TERMO();
                //token = mt.geraToken();
                if (token.getStr().toString().equals(")")){
                    return;
                }
                else ERRO(")", token.getStr().toString());
            }
           else ERRO("(", token.getStr().toString());
        }
        else
            if(token.getStr().toString().equals("writeln")){
                token = mt.geraToken();
                WRITELN();
            }
            else ERRO("writeln", token.getStr().toString());
    }
    
    public void WRITELN(){
        //token = mt.geraToken();
        if(token.getStr().toString().equals("(")){
            TERMO();
            LISTA_TERMO();
            //token = mt.geraToken();
            if (token.getStr().toString().equals(")")){
                return;
            }
            else ERRO(")", token.getStr().toString());
        }
        else return;
    }
    
    public void ENTRADA(){
        //token = mt.geraToken();
        if(token.getStr().toString().equals("read")){
           token = mt.geraToken();
           if(token.getStr().toString().equals("(")){
               token = mt.geraToken();
                LISTA_ID();
                token = mt.geraToken();
                if (token.getStr().toString().equals(")")){
                    return;
                }
                else ERRO(")", token.getStr().toString());
            }
           else ERRO("(", token.getStr().toString());
        }
        else
            if(token.getStr().toString().equals("readln")){
                READLN();
            }
            else ERRO("writeln", token.getStr().toString());
    }
    
    public void READLN(){
        token = mt.geraToken();
        if(token.getStr().toString().equals("(")){
            token = mt.geraToken();
            LISTA_ID();
            //token = mt.geraToken();
            if (token.getStr().toString().equals(")")){
                return;
            }
            else ERRO(")", token.getStr().toString());
        }
        else return;
    }
    
    public void ERRO(String esperado, String obtido) {
        JOptionPane.showMessageDialog(null, "Ocorreu um erro na análise! Era esperado " + esperado + " e foi obtido " + obtido);
        
       
        return;
    }
    
   
}
