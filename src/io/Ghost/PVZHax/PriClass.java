package io.Ghost.PVZHax;

import com.sun.jna.Structure;

public class PriClass extends Structure{
	public int dwSize=1024;
	public int cntUsage;
	public int processId;
	public int th32DefaultHeapID;
	public int th32ModuleID;
	public int cntThreads;
	public int th32ParentProcessID;
	public int pcPriClassBase;
	public int dwFlags;
	public byte[] processName = new byte[256];

}
