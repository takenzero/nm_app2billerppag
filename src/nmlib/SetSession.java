/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nmlib;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author TAKEN
 */
public class SetSession {
    JDBCConnection cn = new JDBCConnection();
    private final Connection conn;
    private final GlobalVar gv;
    private final String db;
    private final String DB_NAME = "DB_NAME";
    private final String TB_NAME = "NM_SESSION";
    
    public SetSession() throws SQLDataException{
        gv = new GlobalVar();
        conn = cn.Connect();
        db = gv.GetValueVar(DB_NAME);
    }
    
    public void SetSessionValue(String key, String value) throws SQLException{
        String tbl = this.db+".dbo."+this.TB_NAME;
        String SQL = "UPDATE "+tbl+" SET SESSION_VALUE=? WHERE SESSION_KEY=?";
        PreparedStatement st = conn.prepareStatement(SQL);
        st.setString(1, key);
        st.setString(2, value);
        int rowUpdated = st.executeUpdate();
        if (rowUpdated > 0) {
            System.out.println("Session updated successfully!");
        }else{
            System.out.println("Session failed to update!");
        }
    }
    
    public String GetSessionValue(String key) throws SQLException{
        String tbl = this.db+".dbo."+this.TB_NAME;
        String SQL = "SELECT SESSION_VALUE FROM "+tbl+" WHERE SESSION_KEY='"+key+"'";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);
        rs.next();
        
        return rs.getString("SESSION_VALUE");
    }
}
