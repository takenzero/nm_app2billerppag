/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inserttable;

import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLDataException;
import java.sql.SQLException;
import nmlib.GlobalVar;
import nmlib.JDBCConnection;

/**
 *
 * @author TAKEN
 */
public class InsertToHandlerBiller extends Thread{
    private final String dbuse = "NM";
    JDBCConnection cn = new JDBCConnection(dbuse);
    private final Connection conn;
    private final GlobalVar gv;
    private final String db;
    private final String DB_NAME = "DB_NAME";
    private final String TB_NAME = "NM_HANDLER2BILLER";
    private final String host;
    private final String msge;
    private final int stts;
    private final String prod;
    private final String dest;
    private final String dscp;
    
    public InsertToHandlerBiller(String host, String msge, String prod, String dest, int stts, String dscp) throws SQLDataException {
        this.conn = cn.Connect();
        this.gv = new GlobalVar();
        this.db = gv.GetValueVar(DB_NAME);
        this.host = host;
        this.msge = msge;
        this.prod = prod;
        this.dest = dest;
        this.stts = stts;
        this.dscp = dscp;
    }
    
    @Override
    public void run(){
        try{
            Date dt = new Date();
            java.sql.Timestamp sysdate = new java.sql.Timestamp(dt.getTime());
            String tbl = this.db+".dbo."+this.TB_NAME;
            
            String SQL = "INSERT INTO "+tbl+" (HOST_SENDER, MESSASGE, PRODUCT_CODE, DESTINATION_ID, STATUS, SYS_CREATION_DATE, SYS_DATE_STATUS, DESCRIPTION) VALUES(?,?,?,?,?,?,?,?)";
            
            PreparedStatement st = conn.prepareStatement(SQL);
            st.setString(1, this.host);
            st.setString(2, this.msge);
            st.setString(3, this.prod);
            st.setString(4, this.dest);
            st.setInt(5, this.stts);
            st.setTimestamp(6, sysdate);
            st.setTimestamp(7, sysdate);
            st.setString(8, this.dscp);
            int rowsInserted = st.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Data inserted successfully!");
            }else{
                System.out.println("Data inserted failed!");
            }
        }catch(SQLException e){
            System.out.println(e.getErrorCode()+": "+e.getMessage());
        }
    }
}