package sicxesm;

public class MemUnit {
	public final static int MEM_SIZE = 2097152; // 가상의 SIC/XE머신 memory설정. 
	static StringBuffer Virtual_MEM = new StringBuffer(MEM_SIZE);
	
	public static StringBuffer getSb() {
		return Virtual_MEM;
	}

	public static void setSb(StringBuffer Virtual_MEM) {
		MemUnit.Virtual_MEM = Virtual_MEM;
	}	
	// memory를 '-' 문자로 초기화
	public void initialMem() {
		int i;
		for(i = 0; i < MEM_SIZE; i++)
			Virtual_MEM.insert(i, '-');
	}
}
