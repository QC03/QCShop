package com.github.qc03.Command;

public class CmdList {

	public static String[] getCmds()
	{
		String[] cmds = new String[7];
		
		cmds[0] = " ";
		cmds[1] = ShopCmd.cmdPrefix + "/상점 생성 <display_name>";
		cmds[2] = ShopCmd.cmdPrefix + "/상점 목록 <page>";
		cmds[3] = ShopCmd.cmdPrefix + "/상점 열기 <shop_id>";
		cmds[4] = ShopCmd.cmdPrefix + "/상점 설정 <shop_id>";
		cmds[5] = ShopCmd.cmdPrefix + "/상점 상세설정";
		cmds[6] = " ";
		
		return cmds;
	}
}
