package io.Ghost.PVZHax;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PlantsVsZombies extends JFrame{
	private static final long serialVersionUID = 1L;
	public PlantsVsZombies() {
		JPanel p = new JPanel();
		JButton b = new JButton("Lets fuck PVZ!");
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Timer().schedule(new TimerTask() {
					@Override
					public void run() {
						isFuckingPVZ();			
					}
				}, 0,200);
			}	
		});
		p.add(b);
		this.add(p);
		this.setTitle("Plants VS Zombies.exe has been fucked up | by Ghost");
		this.setSize(350,150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public static void isFuckingPVZ() {
		int processId = Memory.DprocessId("PlantsVsZombies.exe");
		int baseAddress=Memory.readMemory(processId,0x6a9ec0);
		baseAddress=Memory.readMemory(processId,baseAddress+0x768);
		Memory.writeMemory(processId, baseAddress+0x5560, 999999);
		for (int i = 0; i<8; i ++) {
			int kc = i*0x50+0x70;
			int b2 = Memory.readMemory(processId,baseAddress+0x144);
			Memory.writeMemory(processId, b2+kc, 1);
		}
/* 基质 006a9ec0
 *  // 游戏偏移 768  
 *  // 阳光偏移 5560 
 *  // 无cd 144 70+i*50 
 *  // 00488F41 C7 47 49 01 00 00 00
 */
	}
	public static void main(String[] args) {
		String pass = JOptionPane.showInputDialog(null,"只适用于植物大战僵尸中文版,并不是年度版! \n"+ "输入123继续使用","警告",JOptionPane.WARNING_MESSAGE);
		if(pass.equals("123")) {
			new PlantsVsZombies();
		} else {
			System.exit(0);
		}
	}
}
