package sicxesm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class OpTable {
	private String line;
	
	Hashtable<String, Opcode> opTableList = new Hashtable<String, Opcode>();

	//line단위로 읽어온 자료를 토큰단위로 끊어 각 속성값에 집어넣는다.
	public void addInstruct() throws NumberFormatException, IOException {
		String opcode;
		String value;
		int    argsize;
		int    format;
		int	   stat;
		File opfile = new File("opfile.txt");
		
		BufferedReader inBuf = new BufferedReader(new FileReader(opfile));
		while((line = inBuf.readLine()) != null) { 
			StringTokenizer stok = new StringTokenizer(line);
			opcode  = stok.nextToken();
			value   = stok.nextToken();
			format  = Integer.parseInt(stok.nextToken());
			argsize = Integer.parseInt(stok.nextToken());
			stat    = Integer.parseInt(stok.nextToken());

			opTableList.put(value, new Opcode(opcode, value, format, argsize, stat));
		}
		inBuf.close();
	}
	
	public int HexCharToDec(String str) {
	    int i, sum = 0;
	    
	    i = 0;
	    
	    for(i = 0; i < str.length(); i++)
	    {
	        if((str.charAt(i) >= '0') && (str.charAt(i) <= '9')) {
	            sum *= 16;
	            sum += str.charAt(i) - '0';
	        }
	        else {
	            sum *= 16;
	            sum += str.charAt(i) - '7';
	        }
	    }
	  
	    return sum;
	}
}
