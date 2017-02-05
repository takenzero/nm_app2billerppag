/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digest;

import org.apache.commons.lang3.ArrayUtils;
/**
 *
 * @author TAKEN
 */
public class EncryptPpag {
    public String doEnrypt(String str){
	char [] arrText = {' ','a','A','b','B','c','C','d','D','e','E','f','F','g','G','h','H','i','I','j','J','k','K','l','L','m','M','n','N','o','O','p','P','q','Q','r','R','s','S','t','T','u','U','v','V','w','W','x','X','y','Y','z','Z',
						   '`','1','2','3','4','5','6','7','8','9','0','~','!','@','$','^','*','(',')','-','{','}','|','[',']',':',';','<','>','?',',','.','/','_','='};
	char[] arr = str.toCharArray();
	int len = arrText.length;
	int lenDouble = len*2;
	int lenTriple = len*3;
	char [] arrResult = new char[arr.length];
		
	for (int i = 0; i < arr.length; i++) {
            int xx = ArrayUtils.indexOf(arrText, arr[i]);
            int rnd = xx+i;
            if (rnd<len){
                arrResult[i]= arrText[rnd];
            }else if ((rnd >= len) && (rnd < lenDouble)){
                arrResult[i]= arrText[rnd-len];	
            }else if ((rnd >= lenDouble)&&(rnd < lenTriple)){
                arrResult[i]= arrText[rnd-lenDouble];
            }else if (rnd >= lenTriple){
                arrResult[i]= arrText[rnd-lenTriple];
            }
        }

        String result = "";
        for (int i=0; i<arrResult.length; i++) {
            result = result+arrResult[i];
        }
        return result;
    }
}
