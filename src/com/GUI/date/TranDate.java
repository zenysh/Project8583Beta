package com.GUI.date;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class TranDate {
	public String getTranDateTime() {
		SimpleDateFormat DateFor = new SimpleDateFormat("ddMMyyhhmm");
	//	System.out.println(DateFor.format(new Date()));
		String dated = DateFor.format(new Date());
		// String modified = dated.substring(0, 2) + dated.substring(3, 5) +
		// dated.substring(8, 10)
		// + dated.substring(11, 13) + dated.substring(14, 16) + dated.substring(17,
		// 19);
		return dated;
	}

	public String getLocalSettle() {
		SimpleDateFormat DateFor = new SimpleDateFormat("MMdd");
		String dated = DateFor.format(new Date());
		return dated;
	}

	public String getexpDate() {
		SimpleDateFormat DateFor = new SimpleDateFormat("yyMM");
		String dated = DateFor.format(new Date());
		return dated;
	}

	public String getTimeLocal() {
		SimpleDateFormat DateFor = new SimpleDateFormat("hhmmss");
		return DateFor.format(new Date());
	}
	public  long generateRandom(int length) {
	    Random random = new Random();
	    char[] digits = new char[length];
	    digits[0] = (char) (random.nextInt(9) + '1');
	    for (int i = 1; i < length; i++) {
	        digits[i] = (char) (random.nextInt(10) + '0');
	    }
	    return Long.parseLong(new String(digits));
	}
}