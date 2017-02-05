/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reqservices;

import java.sql.SQLException;
import nmlib.GlobalVar;
import nmlib.SetSession;

/**
 *
 * @author TAKEN
 */
public class ReqToBillerPpag {
    private final GlobalVar gv;
    private final SetSession sess;
    private final Boolean login_sess;
    private final String URL_PPAG                 = "URL_PPAG";
    private final String CONTEXT_LOGIN_PPAG       = "CONTEXT_LOGIN_PPAG";
    private final String CONTEXT_TRANSACTION_PPAG = "CONTEXT_TRANSACTION_PPAG";
    
    private ReqToBillerPpag() throws SQLException{
        this.sess = new SetSession();
        this.gv = new GlobalVar();
        this.login_sess = this.sess.GetSessionValue("LOGIN");
    }
    
    public void doReqToBillerPpag(String message){
        
    }
    
    public void doLoginToBillerPpag(){
        
    }
}
