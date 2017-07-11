package sicxesm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;

public class SimGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JMenuBar jJMenuBar = null;
	private JPanel jContentPane = null;
	private JButton exe_Button = null;
	private JLabel FileName_LB = null;
	private JTextField FileName_TF = null;
	private JLabel Header_LB = null;
	private JLabel ProName_LB = null;
	private JTextField ProgName_TF = null;
	private JLabel StartADDR_LB = null;
	private JTextField StartADDR_TF = null;
	private JLabel LOP_LB = null;
	private JTextField LOP_TF = null;
	private JLabel End_LB = null;
	private JLabel AOFE_LB1 = null;
	private JTextField AOFI_TF = null;
	private JLabel A_LB = null;
	private JLabel B_LB = null;
	private JLabel X_LB = null;
	private JLabel L_LB = null;
	private JLabel S_LB = null;
	private JLabel T_LB = null;
	private JLabel F_LB = null;
	private JLabel PC_LB = null;
	private JLabel SW_LB = null;
	private JLabel TA_LB = null;
	private JTextField A_TF = null;
	private JTextField X_TF = null;
	private JTextField L_TF = null;
	private JTextField B_TF = null;
	private JTextField S_TF = null;
	private JTextField T_TF = null;
	private JTextField F_TF = null;
	private JTextField PC_TF = null;
	private JTextField SW_TF = null;
	private JTextField TA_TF = null;
	private JMenu File_Menu = null;
	private JMenuItem FileOpen_MItem = null;
	private JMenu View_Menu = null;
	private JFileChooser Open_obj = null;
	private JList jList = null;
	private JScrollPane jScrollPane = null;
	
	private int index = 0;
	MemUnit memory = new MemUnit();  //  @jve:decl-index=0:
	Executer exeUnit = null;

	
	
	
	//  @jve:decl-index=0:
	/**
	 * This method initializes jJMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getFile_Menu());
			jJMenuBar.add(getView_Menu());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes jContentPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			TA_LB = new JLabel();
			TA_LB.setBounds(new Rectangle(365, 171, 94, 18));
			TA_LB.setText("Target Address :");
			L_LB = new JLabel();
			L_LB.setBounds(new Rectangle(36, 355, 10, 18));
			L_LB.setText("L");
			L_LB = new JLabel();
			L_LB.setBounds(new Rectangle(36, 353, 10, 18));
			L_LB.setText("L");
			SW_LB = new JLabel();
			SW_LB.setBounds(new Rectangle(187, 395, 20, 18));
			SW_LB.setText("SW");
			PC_LB = new JLabel();
			PC_LB.setBounds(new Rectangle(30, 396, 16, 18));
			PC_LB.setText("PC");
			F_LB = new JLabel();
			F_LB.setBounds(new Rectangle(196, 315, 10, 18));
			F_LB.setText("F");
			T_LB = new JLabel();
			T_LB.setBounds(new Rectangle(36, 315, 10, 18));
			T_LB.setText("T");
			S_LB = new JLabel();
			S_LB.setBounds(new Rectangle(196, 275, 10, 18));
			S_LB.setText("S");
			X_LB = new JLabel();
			X_LB.setBounds(new Rectangle(196, 235, 10, 18));
			X_LB.setText("X");
			B_LB = new JLabel();
			B_LB.setBounds(new Rectangle(36, 275, 10, 18));
			B_LB.setText("B");
			A_LB = new JLabel();
			A_LB.setBounds(new Rectangle(36, 235, 10, 18));
			A_LB.setText("A");
			AOFE_LB1 = new JLabel();
			AOFE_LB1.setBounds(new Rectangle(365, 105, 158, 18));
			AOFE_LB1.setText("Address of first Instruction :");
			End_LB = new JLabel();
			End_LB.setBounds(new Rectangle(365, 72, 80, 18));
			End_LB.setText("E (End record)");
			LOP_LB = new JLabel();
			LOP_LB.setBounds(new Rectangle(30, 174, 114, 18));
			LOP_LB.setText("Length Of Program :");
			StartADDR_LB = new JLabel();
			StartADDR_LB.setBounds(new Rectangle(42, 142, 102, 18));
			StartADDR_LB.setText("Starting Address :");
			ProName_LB = new JLabel();
			ProName_LB.setBounds(new Rectangle(52, 112, 92, 18));
			ProName_LB.setText("Program Name :");
			Header_LB = new JLabel();
			Header_LB.setBounds(new Rectangle(49, 75, 104, 21));
			Header_LB.setText("H (Header record)");
			FileName_LB = new JLabel();
			FileName_LB.setBounds(new Rectangle(8, 34, 58, 18));
			FileName_LB.setText("FileName :");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getExe_Button(), null);
			jContentPane.add(FileName_LB, null);
			jContentPane.add(getFileName_TF(), null);
			jContentPane.add(Header_LB, null);
			jContentPane.add(ProName_LB, null);
			jContentPane.add(getProgName_TF(), null);
			jContentPane.add(StartADDR_LB, null);
			jContentPane.add(getStartADDR_TF(), null);
			jContentPane.add(LOP_LB, null);
			jContentPane.add(getLOP_TF(), null);
			jContentPane.add(End_LB, null);
			jContentPane.add(AOFE_LB1, null);
			jContentPane.add(getAOFI_TF(), null);
			jContentPane.add(A_LB, null);
			jContentPane.add(L_LB, null);
			jContentPane.add(B_LB, null);
			jContentPane.add(X_LB, null);
			jContentPane.add(S_LB, null);
			jContentPane.add(T_LB, null);
			jContentPane.add(F_LB, null);
			jContentPane.add(PC_LB, null);
			jContentPane.add(SW_LB, null);
			jContentPane.add(TA_LB, null);
			jContentPane.add(getA_TF(), null);
			jContentPane.add(getX_TF(), null);
			jContentPane.add(getB_TF(), null);
			jContentPane.add(getS_TF(), null);
			jContentPane.add(getT_TF(), null);
			jContentPane.add(getF_TF(), null);
			jContentPane.add(getPC_TF(), null);
			jContentPane.add(getSW_TF(), null);
			jContentPane.add(getJList(), null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(getL_TF(), null);
			jContentPane.add(getTA_TF(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes exe_Button	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getExe_Button() {
		if (exe_Button == null) {
			exe_Button = new JButton();
			exe_Button.setBounds(new Rectangle(589, 278, 92, 43));
			exe_Button.setText("Execution");
			exe_Button.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int i; 
				
					for(i = 0; i < exeUnit.codeLocList.size(); i++)
						if(exeUnit.codeLocList.get(i).equals(DecToHexChar6(exeUnit.getPC())))
							break;
					getJList().setSelectedIndex(i);

					try {
						exeUnit.exeFunc(i);
					} catch (IOException e1) {
						e1.printStackTrace();
					}

					// register 값을 GUI에 출력.
					getA_TF().setText(DecToHexChar6(exeUnit.getA()));
					getX_TF().setText(DecToHexChar6(exeUnit.getX()));
					getL_TF().setText(DecToHexChar6(exeUnit.getL()));
					getB_TF().setText(DecToHexChar6(exeUnit.getB()));
					getS_TF().setText(DecToHexChar6(exeUnit.getS()));
					getF_TF().setText(DecToHexChar6(exeUnit.getF()));
					getT_TF().setText(DecToHexChar6(exeUnit.getT()));
					getPC_TF().setText(DecToHexChar6(exeUnit.getPC()));
					getSW_TF().setText(DecToHexChar6(exeUnit.getSW()));
					getTA_TF().setText(DecToHexChar6(exeUnit.getTA()));
				}
			});
		}
		return exe_Button;
	}

	/**
	 * This method initializes FileName_TF	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getFileName_TF() {
		if (FileName_TF == null) {
			FileName_TF = new JTextField();
			FileName_TF.setBounds(new Rectangle(71, 34, 140, 22));
		}
		return FileName_TF;
	}

	/**
	 * This method initializes ProgName_TF	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getProgName_TF() {
		if (ProgName_TF == null) {
			ProgName_TF = new JTextField();
			ProgName_TF.setBounds(new Rectangle(161, 112, 100, 22));
			ProgName_TF.setBackground(new Color(238, 238, 238));
		}
		return ProgName_TF;
	}

	/**
	 * This method initializes StartADDR_TF	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getStartADDR_TF() {
		if (StartADDR_TF == null) {
			StartADDR_TF = new JTextField();
			StartADDR_TF.setBounds(new Rectangle(161, 142, 100, 22));
			StartADDR_TF.setBackground(new Color(238, 238, 238));
		}
		return StartADDR_TF;
	}

	/**
	 * This method initializes LOP_TF	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getLOP_TF() {
		if (LOP_TF == null) {
			LOP_TF = new JTextField();
			LOP_TF.setBounds(new Rectangle(161, 173, 100, 22));
			LOP_TF.setBackground(new Color(238, 238, 238));
		}
		return LOP_TF;
	}

	/**
	 * This method initializes AOFI_TF	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getAOFI_TF() {
		if (AOFI_TF == null) {
			AOFI_TF = new JTextField();
			AOFI_TF.setBounds(new Rectangle(533, 104, 91, 22));
			AOFI_TF.setBackground(new Color(238, 238, 238));
		}
		return AOFI_TF;
	}

	/**
	 * This method initializes A_TF	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getA_TF() {
		if (A_TF == null) {
			A_TF = new JTextField();
			A_TF.setBounds(new Rectangle(56, 235, 100, 22));
			A_TF.setBackground(new Color(238, 238, 238));
		}
		return A_TF;
	}

	/**
	 * This method initializes X_TF	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getX_TF() {
		if (X_TF == null) {
			X_TF = new JTextField();
			X_TF.setBounds(new Rectangle(216, 235, 100, 22));
			X_TF.setBackground(new Color(238, 238, 238));
		}
		return X_TF;
	}

	/**
	 * This method initializes B_TF	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getB_TF() {
		if (B_TF == null) {
			B_TF = new JTextField();
			B_TF.setBounds(new Rectangle(56, 275, 100, 22));
			B_TF.setBackground(new Color(238, 238, 238));
		}
		return B_TF;
	}

	/**
	 * This method initializes S_TF	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getS_TF() {
		if (S_TF == null) {
			S_TF = new JTextField();
			S_TF.setBounds(new Rectangle(216, 275, 100, 22));
			S_TF.setBackground(new Color(238, 238, 238));
		}
		return S_TF;
	}

	/**
	 * This method initializes T_TF	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getT_TF() {
		if (T_TF == null) {
			T_TF = new JTextField();
			T_TF.setBounds(new Rectangle(56, 315, 100, 22));
			T_TF.setBackground(new Color(238, 238, 238));
		}
		return T_TF;
	}

	/**
	 * This method initializes F_TF	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getF_TF() {
		if (F_TF == null) {
			F_TF = new JTextField();
			F_TF.setBounds(new Rectangle(216, 315, 100, 22));
			F_TF.setBackground(new Color(238, 238, 238));
		}
		return F_TF;
	}

	/**
	 * This method initializes PC_TF	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getPC_TF() {
		if (PC_TF == null) {
			PC_TF = new JTextField();
			PC_TF.setBounds(new Rectangle(56, 397, 100, 22));
			PC_TF.setBackground(new Color(238, 238, 238));
		}
		return PC_TF;
	}

	/**
	 * This method initializes SW_TF	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getSW_TF() {
		if (SW_TF == null) {
			SW_TF = new JTextField();
			SW_TF.setBounds(new Rectangle(216, 397, 100, 22));
			SW_TF.setBackground(new Color(238, 238, 238));
		}
		return SW_TF;
	}

	/**
	 * This method initializes File_Menu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getFile_Menu() {
		if (File_Menu == null) {
			File_Menu = new JMenu();
			File_Menu.setText("File");
			File_Menu.add(getFileOpen_MItem());
		}
		return File_Menu;
	}

	/**
	 * This method initializes FileOpen_MItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getFileOpen_MItem() {
		if (FileOpen_MItem == null) {
			FileOpen_MItem = new JMenuItem();
			FileOpen_MItem.setText("Open");
			FileOpen_MItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
			
					int returnVal = getOpen_obj().showOpenDialog(getParent());
					
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File obj_file = getOpen_obj().getSelectedFile();
		                Loader loadUnit = new Loader(obj_file);
		                exeUnit = new Executer();
		                setIndex(0);
		                		           
		                /*************************************/
		                /*     Load object code to memory.   */
		                /*************************************/
		                try {
							loadUnit.recordDecoder();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						/*************************************/
						
		                try {
		                	// opcode를 hashtable에 load한다.
							exeUnit.opCodeSet();
						} catch (NumberFormatException e2) {
							e2.printStackTrace();
						} catch (IOException e2) {
							e2.printStackTrace();
						}
		                
						// copy파일을 임의로 설정하여줌.
		                File input = new File("input.txt");
		                File output = new File("output.txt");
		                exeUnit.setInputfile(input);
						exeUnit.setOutputfile(output);
						
						// gui name field에 name 출력.
						getFileName_TF().setText(obj_file.getName());
						// program name 출력.
						getProgName_TF().setText(loadUnit.getProgName());
		                // start address 출력.
						getStartADDR_TF().setText(loadUnit.getStartADDR());
		                // program 길이 출력.
						getLOP_TF().setText(loadUnit.getLOP());
		                // 첫번째 실행될 명령어 주소 출력.
						getAOFI_TF().setText(loadUnit.getFirstIR());
		               
						// PC initialize.
		                exeUnit.setPC(HexCharToDec(loadUnit.getStartADDR()));
		                // memory code decoder.
		                exeUnit.decoder();
		                getJList().setListData(exeUnit.objCodeList);
		                exeUnit.setPC(HexCharToDec(getStartADDR_TF().getText()));
		            }
					
				}
			});
		}
		return FileOpen_MItem;
	}

	/**
	 * This method initializes View_Menu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getView_Menu() {
		if (View_Menu == null) {
			View_Menu = new JMenu();
			View_Menu.setText("View");
		}
		return View_Menu;
	}

	/**
	 * This method initializes Open_obj	
	 * 	
	 * @return javax.swing.JFileChooser	
	 */
	private JFileChooser getOpen_obj() {
		if (Open_obj == null) {
			Open_obj = new JFileChooser();

		}
		
		return Open_obj;
	}

	/**
	 * This method initializes jList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJList() {
		if (jList == null) {
			jList = new JList();
			jList.setBounds(new Rectangle(377, 198, 151, 215));
			
		}
		return jList;
	}
	
	/**
	 * This is the default constructor
	 */
	public SimGUI() {
		super();
		memory.initialMem();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(731, 531);
		this.setContentPane(getJContentPane());
		this.setJMenuBar(getJJMenuBar());
		this.setTitle("SIC/XE Simulator");
	}
	


	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane(getJList());
			jScrollPane.setBounds(new Rectangle(365, 221, 167, 198));
		}
		return jScrollPane;
	}

	/**
	 * This method initializes L_TF	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getL_TF() {
		if (L_TF == null) {
			L_TF = new JTextField();
			L_TF.setBounds(new Rectangle(56, 355, 100, 22));
			L_TF.setBackground(new Color(238, 238, 238));
		}
		return L_TF;
	}

	/**
	 * This method initializes TA_TF	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTA_TF() {
		if (TA_TF == null) {
			TA_TF = new JTextField();
			TA_TF.setBounds(new Rectangle(473, 171, 107, 22));
			TA_TF.setBackground(new Color(238, 238, 238));
		}
		return TA_TF;
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

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}


}

