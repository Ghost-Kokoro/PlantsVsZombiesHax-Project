package io.Ghost.PVZHax;

import com.sun.jna.Native;
import com.sun.jna.win32.StdCallLibrary;

public interface WinAPI extends StdCallLibrary {

	WinAPI Kernel32 = (WinAPI)Native.loadLibrary("Kernel32", WinAPI.class);	
	
	int OpenProcess(int dwDesiredAccess, boolean bInheritHandle, int dwProcessId);
	
	int CloseHandle(int handle);
	
	boolean Module32First(int hSnapshot, MODULEENTRY32 lpme);
	
	boolean Module32Next(int hSnapshot, MODULEENTRY32 lpme);
	
	int TerminateProcess(int processId,int a);
	
	int ReadProcessMemory(int hProcess, int lpBaseAddress, int[] lpBuffer, int nSize, int lpNumberOfBytesRead);
	
	int WriteProcessMemory(int hProcess, int lpBaseAddress, int[] lpBuffer, int nSize, int lpNumberOfBytesRead);
	
	int CreateToolhelp32Snapshot(int falg, int id);
	
	int Process32First(int h,PriClass p);
	
	int Process32Next(int h,PriClass p);
	
}
