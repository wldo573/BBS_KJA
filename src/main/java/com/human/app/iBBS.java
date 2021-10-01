package com.human.app;

import java.util.ArrayList;

public interface iBBS {
	void writebbs(String pTitle,String pContent,String pWriter,String pPasscode);
	void deletebbs(int nBbsId);
	void updatebbs(int nBbsId,String stitle,String scontent);
	ArrayList<BBSrec> getList();
	BBSrec getPost(int bbs_id);
}
