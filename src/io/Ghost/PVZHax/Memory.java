package io.Ghost.PVZHax;

public class Memory {
        public static int DprocessId(String str) {
        	byte[] processName = str.getBytes();
    		PriClass p = new PriClass();
    		int jckz = WinAPI.Kernel32.CreateToolhelp32Snapshot(2, 0);
    		if (jckz != 0) {
    			int jcjb = WinAPI.Kernel32.Process32First(jckz, p);
    			while (jcjb != 0) {
    				String pname = new String(p.processName, 0, processName.length+8).substring(8);
    				System.out.println(pname);//Debug
			       System.out.println(new String(processName));//Debug 
    				if (pname.toLowerCase().equals(new String(processName).toLowerCase())) {
    					closeHandle(jckz);
    					return p.processId;
    				}
    				jcjb = WinAPI.Kernel32.Process32Next(jckz, p);
    			}
    			closeHandle(jckz);
    		}
    		return 0;
        }
        public static int readCodeAddress(int processId, String address) {
    		String pyarray[] = address.split("\\+");
    		int pycount = pyarray.length;
    		int zxaddress = 0;
    		if (pycount > 1) {
    			int temp = readMemory(processId, Integer.parseInt(pyarray[0], 16));
    			for (int i = 0; i < pycount - 2; i++) {
    				temp = readMemory(processId, temp + Integer.parseInt(pyarray[i + 1], 16));
    			}
    			zxaddress = temp + Integer.parseInt(pyarray[pycount - 1], 16);
    		} else {
    			zxaddress = Integer.parseInt(pyarray[0], 16);
    		}
    		return zxaddress;
    	}
        public static int readMemoryCode(int processId, String address) {
    		int LocalValuesRead = 0;
    		String LocalSegmentation[] = address.split("\\+");
    		for (int i = 0; i < LocalSegmentation.length; i++) {
    			if (i == LocalSegmentation.length - 1) {
    				LocalValuesRead = Integer.parseInt(LocalSegmentation[i], 16) + LocalValuesRead;
    			} else {
    				LocalValuesRead = readMemory(processId, Integer.parseInt(LocalSegmentation[i], 16) + LocalValuesRead);
    			}
    		}
    		return readMemory(processId, LocalValuesRead);
    	}
        public static int readMemory(int processId, int address) {
    		int[] lpBuffer = new int[1];
    		int handle = WinAPI.Kernel32.OpenProcess(2035711, false, processId);
    		WinAPI.Kernel32.ReadProcessMemory(handle, address, lpBuffer, 4, 0);
    		closeHandle(handle);
    		if (lpBuffer[0] == 0) {
    			return -1;
    		} else {
    			return lpBuffer[0];
    		}
    	}
        public static boolean writeMemory(int processId, int baseAddress, int value) {
    		int[] lpBuffer = new int[1];
    		lpBuffer[0] = value;
    		int handle = WinAPI.Kernel32.OpenProcess(2035711, false, processId);
    		int a = WinAPI.Kernel32.WriteProcessMemory(handle, baseAddress, lpBuffer, 4, 0);
    		closeHandle(handle);
    		return a == 1;
    	}
        public static int getDoubleByte(int value) {
    		String str = Integer.toHexString(value);
    		String newStr = str.substring(str.length() - 4);
    		int getbyte = Integer.parseInt(newStr, 16);
    		return getbyte;
    	}
        public static void closeHandle(int handle) {
    		WinAPI.Kernel32.CloseHandle(handle);
    	}
        public static boolean endId(int processId) {
    		return WinAPI.Kernel32.TerminateProcess(WinAPI.Kernel32.OpenProcess(1, false, processId), 0) == 1;
    	}
    	public static int getModuleHandle(int processId, String moduleName) {
    		int hSnapshot = WinAPI.Kernel32.CreateToolhelp32Snapshot(8, processId);
    		if (hSnapshot == 0) {
    			return -1;
    		}
    		MODULEENTRY32 lpme = new MODULEENTRY32();
    		boolean success = WinAPI.Kernel32.Module32First(hSnapshot, lpme);
    		int ret = -1;
    		while (success) {
    			String szModule = new String(lpme.szModule, 0, moduleName.length());
    			if (moduleName.equals(szModule)) {
    				ret = lpme.hModule;
    				break;
    			}
    			success = WinAPI.Kernel32.Module32Next(hSnapshot, lpme);
    		}
    		WinAPI.Kernel32.CloseHandle(hSnapshot);
    		return ret;
    	}
}
