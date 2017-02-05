/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nmjob;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import nmlib.GlobalVar;
import nmlib.JDBCConnection;
import java.sql.Statement;

/**
 *
 * @author TAKEN
 */
public class JobHandler extends Thread{
    JDBCConnection cn = new JDBCConnection();
    private final Connection conn;
    private final GlobalVar gv;
    private final String db;
    private final String DB_NAME = "DB_NAME";
    private final String TB_NAME = "NM_HANDLER2BILLER";
    private final int TIME_JOB;
    
    public JobHandler() throws SQLDataException{
        conn = cn.Connect();
        gv = new GlobalVar();
        db = gv.GetValueVar(DB_NAME);
        TIME_JOB = Integer.parseInt(gv.GetValueVar("TIME_JOBHADNLER"));
    }
    
    @Override
    public void run(){        
        try {
            String SQL = "SELECT * FROM "+this.TB_NAME;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            Thread.sleep(this.TIME_JOB);
        } catch (InterruptedException | SQLException ex) {
            Logger.getLogger(JobHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
