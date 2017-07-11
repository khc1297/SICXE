package sicxesm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Executer extends MemUnit {

	/************************************************/
	/*               Register Generate              */
	/************************************************/
	private int PC = 0;
	private int A  = 0;
	private int X  = 0;
	private int L  = 0;
	private int B  = 0;
	private int S  = 0;
	private int T  = 0;
	private int F  = 0;
	private int SW = 0;  // (< , 1) (= , 2) (> , 3) 
	private int TA = 0;
	/************************************************/
	// input & output file generate.
	private File inputfile = null;
	private File outputfile = null; 
	private FileInputStream inputStream = null;
	private FileOutputStream outputStream = null;
	/************************************************/
	
	// object code token divide list.
	Vector<String> codeLocList = new Vector();
	Vector<String> objCodeList = new Vector();
	
	// optable code table generate.
	OpTable opCodeTab = new OpTable();
	
	// opcode table initialization
	public void opCodeSet() throws NumberFormatException, IOException {
		opCodeTab.addInstruct();
	}
	
	/************************************************/
	/*      Memory의 object코드를 Vector에 저장                  */
	/************************************************/
	public void decoder() {
		String objcode;
		String opcode;
		String xbpe;
		int op_int;
		int ni_bit;
		
		while(!Virtual_MEM.substring(getPC(), getPC() + 1).equals("-")) {
			codeLocList.add(DecToHexChar6(getPC() / 2));  // location을 vector에 저장. 실행 중인 명령어 index에 사용.
			opcode = Virtual_MEM.substring(getPC(), getPC() + 2);  // memory에서 opcode를 읽는다.
			objcode = opcode;  // objcode에 먼저 opcode부분만 저장.
			setPC(getPC()+2); // read opcode byte 만큼 pc 증가
			op_int = HexCharToDec(opcode);  // Hex opcode -> Integer opcode.
			ni_bit = op_int % 4;            // n, i bit divide.
			op_int = op_int - ni_bit;       // op_int is instruction value.
			opcode = DecToHexChar2(op_int); // Integer opcode -> Hex opcode.
		
			/******************************************************/
			/*                 read opcode decode                 */
			/******************************************************/
		
			if(opCodeTab.opTableList.containsKey(opcode)) {
				// 1 format instruction.
				if(opCodeTab.opTableList.get(opcode).getOPformat() == 1) {
				
				}
				// 2 format instruction.
				if(opCodeTab.opTableList.get(opcode).getOPformat() == 2) {
					objcode = objcode.concat(Virtual_MEM.substring(getPC(), getPC() + 2));
					setPC(getPC() + 2);
					objCodeList.add(objcode);
				}
				// 3/4 format instruction.
				if(opCodeTab.opTableList.get(opcode).getOPformat() == 3) {
					xbpe = Virtual_MEM.substring(getPC(), getPC() + 1);
					objcode = objcode.concat(xbpe);
					setPC(getPC() + 1);
					if((HexCharToDec(xbpe) % 2) != 1) {
						// 3 format
						objcode = objcode.concat(Virtual_MEM.substring(getPC(), getPC() + 3));
						setPC(getPC() + 3);
					}
					else {
						// 4 format
						objcode	= objcode.concat(Virtual_MEM.substring(getPC(), getPC() + 5));
						setPC(getPC() + 5);
					}
					objCodeList.add(objcode);
				}
			}
		}
	}
	
	/************************************************/
	/*      Vector에 저장된 명령어를 하나씩 실행                      */
	/************************************************/
	
	public void exeFunc(int index) throws IOException {
		String objcode;
		String opcode;
		int xbpe, loc, char_byte;
		int op_int, ni_bit, x_flag, b_flag, p_flag, e_flag, r1, r2;
		byte _read[] = new byte[1];
		
		x_flag = 0; b_flag = 0; p_flag = 0; e_flag = 0; loc = 0; r1 = 0; r2 = 0; char_byte = 0;
		
		setTA(0);

		// PC = start address.
		System.out.println("PC = "+getPC());
		
		objcode = objCodeList.get(index);  // 실행될 명령어를 가져옴
		opcode = objcode.substring(loc, loc + 2);  // object code에서 opcode부분만 따로 저장.
		setPC(getPC()+1); loc = loc + 2;    // PC의 증가와 location 설정.
			op_int = HexCharToDec(opcode);  // Hex opcode -> Integer opcode.
			ni_bit = op_int % 4;            // n, i bit divide.
			op_int = op_int - ni_bit;       // op_int is instruction value.
			opcode = DecToHexChar2(op_int); // Integer opcode -> Hex opcode.

			/************************************************/
			/*               명령어를 판단하여 실행                           */
			/************************************************/
			switch(opCodeTab.opTableList.get(opcode).getOPformat()) {
				case 1 : // 1 format instruction.
					break;
				case 2 : // 2 format instruction.
					setPC(getPC() + 1); // PC 증가.
					switch(op_int) {
						case 160 :  //COMPR
							r1 = getReg(HexCharToDec(objcode.substring(loc, loc + 1)));
							r2 = getReg(HexCharToDec(objcode.substring(loc + 1, loc + 2)));
							if(r1 > r2) setSW(3);
							if(r1 == r2) setSW(2);
							if(r1 < r2) setSW(1);
							break;
						case 180 :  //CLEAR
							setReg(HexCharToDec(objcode.substring(loc, loc + 1)), 0);
							break;
						case 184 :  //TIXR
							setX(getX() + 1);
							if(getX() < getReg(HexCharToDec(objcode.substring(loc, loc+1)))) setSW(1);
							if(getX() == getReg(HexCharToDec(objcode.substring(loc, loc+1)))) setSW(2);
							if(getX() > getReg(HexCharToDec(objcode.substring(loc, loc+1)))) setSW(3);
							break;
					}
					break;
				case 3 : // 3/4 format instruction. 
					setPC(getPC() + 2);
					xbpe = HexCharToDec(objcode.substring(loc, loc + 1)); 
					loc = loc + 1;
					
					// x, b, p, e flag를 설정.
					if(xbpe > 0) {
						e_flag = xbpe % 2;
						xbpe = xbpe / 2;
					}
					if(xbpe > 0) {
						p_flag = xbpe % 2;
						xbpe = xbpe / 2;
					}
					if(xbpe > 0) {
						b_flag = xbpe % 2;
						xbpe = xbpe / 2;
					}
					if(xbpe > 0) {
						x_flag = xbpe % 2;
						xbpe = xbpe / 2;
					}

					// x, b, p, e flag의 값에 의해 target address 설정.
					if(x_flag == 1)
						setTA(getTA() + getX());
					if(b_flag == 1)
						setTA(getTA() + getB());
					if(p_flag == 1) {
						setTA(getTA() + getPC());
					}
					if(e_flag == 1) {
						setPC(getPC() + 1);
						setTA(getTA() + getPC());
					}
				
					switch(op_int) {
						case  0 :  //LDA
							if(e_flag == 1) setTA(getTA() + HexCharToDec(objcode.substring(loc, loc + 5)));
							else setTA(getTA() + HexCharToDec(objcode.substring(loc, loc + 3)));
							switch(ni_bit) {
							case 1 : if(e_flag == 1) setA(HexCharToDec(objcode.substring(loc, loc + 5)));
							 else setA(HexCharToDec(objcode.substring(loc, loc + 3))); 
							 setTA(getTA()); break;
							case 2 :
								break;
							case 3 :
								setA(HexCharToDec(Virtual_MEM.substring(getTA() * 2, getTA() * 2 + 6)));
								break;
							}
							break;
						case 12 :  //STA
							if(e_flag == 1) setTA(getTA() + HexCharToDec(objcode.substring(loc, loc + 5)));
							else setTA(getTA() + HexCharToDec(objcode.substring(loc, loc + 3)));
							Virtual_MEM.replace(getTA() * 2, getTA() * 2 + 6, DecToHexChar6(getA()));
							break;
						case 16 :  //STX
							if(e_flag == 1) setTA(getTA() + HexCharToDec(objcode.substring(loc, loc + 5)));
							else setTA(getTA() + HexCharToDec(objcode.substring(loc, loc + 3)));
							Virtual_MEM.replace(getTA() * 2, getTA() * 2 + 6, DecToHexChar6(getX()));
							break;
						case 20 :  //STL
							if(e_flag == 1) setTA(getTA() + HexCharToDec(objcode.substring(loc, loc + 5)));
							else setTA(getTA() + HexCharToDec(objcode.substring(loc, loc + 3)));
							Virtual_MEM.replace(getTA() * 2, getTA() * 2 + 6, DecToHexChar6(getL()));
							break;
						case 40 :  //COMP
							if(e_flag == 1) setTA(getTA() + HexCharToDec(objcode.substring(loc, loc + 5)));
							else setTA(getTA() + HexCharToDec(objcode.substring(loc, loc + 3)));
							if(ni_bit == 1) {
								if(getA() < HexCharToDec(objcode.substring(loc, loc + 3))) setSW(1);
								if(getA() == HexCharToDec(objcode.substring(loc, loc + 3))) setSW(2);
								if(getA() > HexCharToDec(objcode.substring(loc, loc + 3))) setSW(3);
							}
							else if (ni_bit == 2)
								;
							else if (ni_bit == 3) {
								if(getA() < HexCharToDec(Virtual_MEM.substring(getTA() * 2, getTA() * 2 + 6))) setSW(1);
								if(getA() == HexCharToDec(Virtual_MEM.substring(getTA() * 2, getTA() * 2 + 6))) setSW(2);
								if(getA() > HexCharToDec(Virtual_MEM.substring(getTA() * 2, getTA() * 2 + 6))) setSW(3);
							}
							break;
						case 48 :  //JEQ
							if(e_flag == 1) setTA(getTA() + HexCharToDec(objcode.substring(loc, loc + 5)));
							else { setTA(getTA() + HexCharToDec(objcode.substring(loc, loc + 3)));
								if(getTA() > 4095) setTA(getTA() - 4096); }
							if(getSW() == 2) setPC(getTA());
							break;
						case 56 :  //JLT
							if(e_flag == 1) setTA(getTA() + HexCharToDec(objcode.substring(loc, loc + 5)));
							else { setTA(getTA() + HexCharToDec(objcode.substring(loc, loc + 3)));
								if(getTA() > 4095) setTA(getTA() - 4096); }
							if(getSW() == 1)
								setPC(getTA());
							break;
						case 60 :  //J
							if(e_flag == 1) setTA(getTA() + HexCharToDec(objcode.substring(loc, loc + 5)));
							else { setTA(getTA() + HexCharToDec(objcode.substring(loc, loc + 3)));
								if(getTA() > 4095) setTA(getTA() - 4096); }
							switch(ni_bit) {
							case 1 : setPC(getTA() - getPC());	break;
							case 2 : setPC(HexCharToDec(Virtual_MEM.substring(getTA() * 2, getTA() * 2 + 6))); break;
							case 3 : setPC(getTA()); break;
							}
							break;
						case 72 :  //JSUB
							if(e_flag == 1) setTA(getTA() + HexCharToDec(objcode.substring(loc, loc + 5)));
							else setTA(getTA() + HexCharToDec(objcode.substring(loc, loc + 3)));
							setL(getPC());
							setPC(getTA());
							break;
						case 76 :  //RSUB
							if(e_flag == 1) setTA(getTA() + HexCharToDec(objcode.substring(loc, loc + 5)));
							else setTA(getTA() + HexCharToDec(objcode.substring(loc, loc + 3)));
							setPC(getL());
							break;
						case 80 :  //LDCH
							if(e_flag == 1) setTA(getTA() + HexCharToDec(objcode.substring(loc, loc + 5)));
							else setTA(getTA() + HexCharToDec(objcode.substring(loc, loc + 3)));
							setA(HexCharToDec(Virtual_MEM.substring(getTA() * 2, getTA() * 2 + 2)));
							break;
						case 84 :  //STCH
							if(e_flag == 1) setTA(getTA() + HexCharToDec(objcode.substring(loc, loc + 5)));
							else setTA(getTA() + HexCharToDec(objcode.substring(loc, loc + 3)));
							Virtual_MEM.replace(getTA() * 2, getTA() * 2 + 2, DecToHexChar2(getA()));
							break;
						case 116 :  //LDT
							if(e_flag == 1) setTA(getTA() + HexCharToDec(objcode.substring(loc, loc + 5)));
							else setTA(getTA() + HexCharToDec(objcode.substring(loc, loc + 3)));
							switch(ni_bit) {
							case 1 : if(e_flag == 1) setT(HexCharToDec(objcode.substring(loc, loc + 5)));
									 else setT(HexCharToDec(objcode.substring(loc, loc + 3))); 
									 setTA(getTA()); break;
							case 2 : break;
							case 3 :
								setT(HexCharToDec(Virtual_MEM.substring(getTA() * 2, getTA() * 2 + 6)));
								break;
							}
							break;
						case 216 :  //RD
							if(e_flag == 1) setTA(getTA() + HexCharToDec(objcode.substring(loc, loc + 5)));
							else setTA(getTA() + HexCharToDec(objcode.substring(loc, loc + 3)));
							int temp = getInputStream().read();
							Integer it1 = new Integer(temp);
							if(temp != -1) setA(temp);
							else setA(0);
							break;
						case 220 :  //WD
							if(e_flag == 1) setTA(getTA() + HexCharToDec(objcode.substring(loc, loc + 5)));
							else setTA(getTA() + HexCharToDec(objcode.substring(loc, loc + 3)));
							getOutputStream().write(getA());
							break;
						case 224 :  //TD
							if(e_flag == 1) setTA(getTA() + HexCharToDec(objcode.substring(loc, loc + 5)));
							else setTA(getTA() + HexCharToDec(objcode.substring(loc, loc + 3)));
							setSW(1);
							break;
					}
			}
	}

/**************************************/
/*         Register get method        */
/**************************************/
	public int getA() {	return A; }
	public int getX() { return X; }
	public int getL() {	return L; }
	public int getB() { return B; }
	public int getS() {	return S; }
	public int getT() {	return T; }
	public int getF() {	return F; }
	public int getPC() { return PC;	}
	public int getSW() { return SW; }

/**************************************/
/*        Register set method         */
/**************************************/
	public void setA(int a) { A = a; }
	public void setX(int x) { X = x; }
	public void setL(int l) { L = l; }
	public void setB(int b) { B = b; }
	public void setS(int s) { S = s; }
	public void setT(int t) { T = t; }
	public void setF(int f) { F = f; }
	public void setPC(int pc) {	PC = pc; }
	public void setSW(int sw) {	SW = sw; }
/**************************************/

	// Target Address process.
	public int getTA() {
		return TA;
	}
	public void setTA(int ta) {
		TA = ta;
	}
	
	// 값에 의해 레지스터를 지정해주는 method.
	public int getReg(int sel) {
		int result = 0;
		switch (sel) {
			case 0 : result = getA(); break;
			case 1 : result = getX(); break;
			case 2 : result = getL(); break;
			case 3 : result = getB(); break;
			case 4 : result = getS(); break;
			case 5 : result = getT(); break;
			case 6 : result = getF(); break;
			case 8 : result = getPC(); break;
			case 9 : result = getSW(); break;
		}
		
		return result;
	}
	// 레지스터에 값을 저장해주는  method.
	public void setReg(int reg, int value) {
		switch (reg) {
			case 0 : setA(value); break;
			case 1 : setX(value); break;
			case 2 : setL(value); break;
			case 3 : setB(value); break;
			case 4 : setS(value); break;
			case 5 : setT(value); break;
			case 6 : setF(value); break;
			case 8 : setPC(value); break;
			case 9 : setSW(value); break;
		}
	}
	
	public File getInputfile() {
		return inputfile;
	}

	public void setInputfile(File inputfile) {
		this.inputfile = inputfile;
		try {
			setInputStream(new FileInputStream(this.inputfile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}


	public FileInputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(FileInputStream inputStream) {
		this.inputStream = inputStream;
	}


	public File getOutputfile() {
		return outputfile;
	}


	public void setOutputfile(File outputfile) {
		this.outputfile = outputfile;
		try {
			setOutputStream(new FileOutputStream(this.outputfile ));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}


	public FileOutputStream getOutputStream() {
		return outputStream;
	}

	public void setOutputStream(FileOutputStream outputStream) {
		this.outputStream = outputStream;
	}

	// integer type opcode2 -> 2 hex string type opcode 
	public String DecToHexChar2(int decimal) {
		String zero = "0";
		String result;
		Integer t1 = new Integer(decimal);
		result = t1.toHexString(decimal);
		
		if(decimal < 16) {
			result = zero.concat(result);
		}
		return result.toUpperCase();
	}
	// integer type -> 6 hex string type
	public String DecToHexChar6(int decimal) {
		String zero = "0";
		String result;
		int i;
		Integer t1 = new Integer(decimal);
		result = t1.toHexString(decimal);
		if(result.length() < 6) {
			for(i = 0; i < 5 - result.length(); i++) {
				zero = zero.concat("0");
			}
			result = zero.concat(result);
		}
		
		return result.toUpperCase();
	}

	// Hex character -> integer
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
