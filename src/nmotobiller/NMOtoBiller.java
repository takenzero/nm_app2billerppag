/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nmotobiller;

import handler.StartHandler;
import java.io.IOException;
import java.sql.SQLDataException;
import java.sql.SQLException;

/**
 *
 * @author TAKEN
 */
public class NMOtoBiller {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.sql.SQLDataException
     */
    public static void main(String[] args) throws IOException, SQLDataException, SQLException {
        //Start Handler
        try{
            StartHandler str_hnd = new StartHandler();
            System.out.println("Handler Start ...");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    
}
