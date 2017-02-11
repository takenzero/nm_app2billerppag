/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nmlib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLDataException;
import java.sql.SQLException;

/**
 *
 * @author TAKEN
 */
public class JDBCConnection {
    GlobalVar gv;
    private Connection CONN;
    private String URL     = "";
    private final String DRIVER_NAME         = "DRIVER_NAME";
    private final String JDBC_DRIVER         = "JDBC_DRIVER";
    private final String HOST_NAME           = "HOST_NAME";
    private final String INTEGRATED_SECURITY = "INTEGRATED_SECURITY";
    private final String USER_JDBC           = "USER_JDBC";
    private final String PASS_JDBC           = "PASS_JDBC";
    private final String DB_NAME             = "DB_NAME";
    private final String DB_OTOMAX           = "DB_OTOMAX";
    
    private final String dn;
    private final String jd;
    private final String hn;
    private final String is;
    private final String uj;
    private final String pj;
    private final String db;
    
    public JDBCConnection(String dbuse){
        this.gv = new GlobalVar();
        
        this.dn = gv.GetValueVar(DRIVER_NAME);
        this.jd = gv.GetValueVar(JDBC_DRIVER);
        this.hn = gv.GetValueVar(HOST_NAME);
        this.is = gv.GetValueVar(INTEGRATED_SECURITY);
        this.uj = gv.GetValueVar(USER_JDBC);
        this.pj = gv.GetValueVar(PASS_JDBC);
        if (dbuse.equals("NM")){
            this.db = gv.GetValueVar(DB_NAME);
        }else{
            this.db = gv.GetValueVar(DB_OTOMAX);
        }
    }
    
    public Connection Connect() throws SQLDataException{
        this.URL = this.jd + "://" + this.hn + ";initial catalog=" + this.db + ";integrated security=" + is;
        
        if (CONN == null){
            try{
                Class.forName(dn);
                System.out.println("Class driver found: "+dn);
                try{
                    CONN = DriverManager.getConnection(this.URL, this.uj, this.pj);
                    System.out.println("JDBC Connection success !");
                }catch (SQLException e){
                    System.out.println("JDBC Connectiuon failed : "+e);
                    System.exit(0);
                }
            }catch (ClassNotFoundException e){
                System.out.println("Class not found: "+e);
                System.exit(0);
            }
        }
        
        return CONN;
    }
}