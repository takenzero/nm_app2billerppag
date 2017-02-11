/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nmlib;

import java.util.HashMap;

/**
 *
 * @author TAKEN
 */
public class GlobalVar {
    HashMap<String, String> gv;
    public GlobalVar(){
        gv = new HashMap<>();
        gv.put("DRIVER_NAME", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        gv.put("JDBC_DRIVER", "jdbc:sqlserver");
        gv.put("HOST_NAME", "DESKTOP-591NU13\\SQLEXPRESS");
        gv.put("INTEGRATED_SECURITY", "false");
        gv.put("USER_JDBC", "mtahir");
        gv.put("PASS_JDBC", "mtahir123");
        gv.put("DB_NAME", "NMDB");
        gv.put("DB_OTOMAX", "otomax");
        gv.put("TIME_JOBHADNLER", "60000");
        gv.put("URL_PPAG", "https://202.152.12.106:7227/PPAG.denom/");
        gv.put("CONTEXT_LOGIN_PPAG", "loginmitrasms");
        gv.put("CONTEXT_TRANSACTION_PPAG", "RequestData");
    }
    
    public String GetValueVar(String NameVar){
        return gv.get(NameVar);
    }
}
