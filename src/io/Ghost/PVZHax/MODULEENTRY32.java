package io.Ghost.PVZHax;

import com.sun.jna.Structure;

public class MODULEENTRY32 extends Structure {
	public int dwSize = 1024;
	public int th32ModuleID;
	public int th32ProcessID;
	public int GlblcntUsage;
	public int ProccntUsage;
	public byte[] modBaseAddr = new byte[1];
	public int modBaseSize;
	public int hModule;
	public byte[] szModule = new byte[256];
	public byte[] szExePath = new byte[256];
	public int dwFlags;
}
