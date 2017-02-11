/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inserttable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import nmlib.GlobalVar;
import nmlib.JDBCConnection;

/**
 *
 * @author TAKEN
 */
public class InsertToOtoTable extends Thread{
    private final String dbuse = "OTOMAX";
    JDBCConnection cn = new JDBCConnection(dbuse);
    private final Connection conn;
    private final GlobalVar gv;
    private final String db;
    private final String DB_NAME = "DB_OTOMAX";
    private final String mssg;
    private final String act;
    
    public InsertToOtoTable(String mssg, String act) throws SQLDataException{
        this.conn = cn.Connect();
        this.gv = new GlobalVar();
        this.db = gv.GetValueVar(DB_NAME);
        this.mssg = mssg;
        this.act = act;
    }
    
    @Override
    public void run(){
        if (this.act.equals("INBOX_TEMP")){
            try {
                InsertToInboxTemp();
            } catch (SQLException ex) {
                Logger.getLogger(InsertToOtoTable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void InsertToInboxTemp() throws SQLException{
        Date dt = new Date();
        java.sql.Timestamp sysdate = new java.sql.Timestamp(dt.getTime());
        String tbl = this.db+".dbo.inbox_temp";
        
        String SQL = "INSERT INTO "+tbl+" (tgl_entri, pengirim, pesan, status) VALUES (?,?,?,?)";
        PreparedStatement st = conn.prepareStatement(SQL);
        st.setTimestamp(1, sysdate);
        st.setString(2, "APP_BILLER");
        st.setString(3, this.mssg);
        st.setInt(4, 0);
        int rowsInserted = st.executeUpdate();

        if (rowsInserted > 0) {
            System.out.println("Data inserted successfully!");
        }else{
            System.out.println("Data inserted failed!");
        }
    }
}
