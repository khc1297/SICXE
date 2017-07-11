package sicxesm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Loader extends MemUnit {
	
	private File objfile;     // opened object file.
	private String progName;  // program name.
	private String startADDR; // start address.
	private String LOP;		  // length of program.
	private String firstIR;   // first execute instruction.
	private int sTextRec;     // start text record address.
	private int TextRecLen;   // text record length.
	
	public Loader(File inFile) {
		objfile = inFile;
	}

	public void recordDecoder() throws IOException {
		String line;
		
		BufferedReader inBuf = new BufferedReader(new FileReader(objfile));
		// Header record read. 
		line = inBuf.readLine();
		setProgName(line.substring(1, 7));
		setStartADDR(line.substring(7, 13));
		setLOP(line.substring(13,19));
		
		// Text record read.
		while((line = inBuf.readLine()).charAt(0) == 'T') {
			// set Start Text record byte location. 1~7 is location field.
			setSTextRec(HexCharToDec(line.substring(1, 7)) * 2 + HexCharToDec(getStartADDR()));
			// set Text record length byte. 7~9 is length field.
			setTextRecLen(HexCharToDec(line.substring(7,9)) * 2);
			// Load object code in virtual memory. 9~textRecLen is object code field.
			Virtual_MEM.replace(getSTextRec(), getSTextRec() + getTextRecLen(), line.substring(9, 9 + getTextRecLen()));
			
		}
		// End record read.
		setFirstIR(line.substring(1,7));
		
		inBuf.close();
	}

	// Hex character -> integer.
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

	public String getStartADDR() {
		return startADDR;
	}

	public void setStartADDR(String startADDR) {
		this.startADDR = startADDR;
	}

	public String getProgName() {
		return progName;
	}

	public void setProgName(String progName) {
		this.progName = progName;
	}

	public String getLOP() {
		return LOP;
	}

	public void setLOP(String lop) {
		LOP = lop;
	}

	public String getFirstIR() {
		return firstIR;
	}

	public void setFirstIR(String firstIR) {
		this.firstIR = firstIR;
	}

	public int getSTextRec() {
		return sTextRec;
	}

	public void setSTextRec(int textRec) {
		sTextRec = textRec;
	}

	public int getTextRecLen() {
		return TextRecLen;
	}

	public void setTextRecLen(int textRecLen) {
		TextRecLen = textRecLen;
	}
}
	
