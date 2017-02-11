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
import nmlib.SetSession;

/**
 *
 * @author TAKEN
 */
public class JobHandler extends Thread{
    private final String dbuse = "NM";
    JDBCConnection cn = new JDBCConnection(dbuse);
    private final Connection conn;
    private final SetSession sess;
    private final Boolean login_sess;
    private final GlobalVar gv;
    private final String db;
    private final String DB_NAME = "DB_NAME";
    private final String TB_NAME = "NM_HANDLER2BILLER";
    private final int TIME_JOB;
    
    public JobHandler() throws SQLDataException, SQLException{
        conn = cn.Connect();
        sess = new SetSession();
        gv = new GlobalVar();
        db = gv.GetValueVar(DB_NAME);
        TIME_JOB = Integer.parseInt(gv.GetValueVar("TIME_JOBHADNLER"));
        login_sess = Boolean.parseBoolean(this.sess.GetSessionValue("LOGIN"));
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
