package sicxesm;

public class MemUnit {
	public final static int MEM_SIZE = 2097152; // ������ SIC/XE�ӽ� memory����. 
	static StringBuffer Virtual_MEM = new StringBuffer(MEM_SIZE);
	
	public static StringBuffer getSb() {
		return Virtual_MEM;
	}

	public static void setSb(StringBuffer Virtual_MEM) {
		MemUnit.Virtual_MEM = Virtual_MEM;
	}	
	// memory�� '-' ���ڷ� �ʱ�ȭ
	public void initialMem() {
		int i;
		for(i = 0; i < MEM_SIZE; i++)
			Virtual_MEM.insert(i, '-');
	}
}
