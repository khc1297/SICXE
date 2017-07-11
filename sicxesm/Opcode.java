package sicxesm;

public class Opcode {
	private String OPname;    // opcode name.
	private String OPvalue;   // opcode value.
	private int    OPargSize; // opcode argument size.
	private int    OPformat;  // opcode format.
	private int    OPstat;    // opcode status.

	//Opcode constructor.
	public Opcode(String name, String value, int format, int argSize, int stat) {
		OPname    = name;
		OPvalue   = value;
		OPargSize = argSize;
		OPformat  = format;
		OPstat    = stat;
	}

	public String getOPname() {
		return OPname;
	}

	public void setOPname(String pname) {
		OPname = pname;
	}

	public String getOPvalue() {
		return OPvalue;
	}

	public void setOPvalue(String pvalue) {
		OPvalue = pvalue;
	}

	public int getOPargSize() {
		return OPargSize;
	}

	public void setOPargSize(int pargSize) {
		OPargSize = pargSize;
	}

	public int getOPformat() {
		return OPformat;
	}

	public void setOPformat(int pformat) {
		OPformat = pformat;
	}

	public int getOPstat() {
		return OPstat;
	}

	public void setOPstat(int pstat) {
		OPstat = pstat;
	}
}
