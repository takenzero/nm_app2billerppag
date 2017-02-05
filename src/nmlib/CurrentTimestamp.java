/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nmlib;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author TAKEN
 */
public class CurrentTimestamp {
    public LocalDateTime getCurrentTimestamp(){
        LocalDateTime datetime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd HH:mm:ss");
        String text = datetime.format(formatter);
        LocalDateTime parsedDate = LocalDateTime.parse(text, formatter);
        
        return parsedDate;
    }
}
