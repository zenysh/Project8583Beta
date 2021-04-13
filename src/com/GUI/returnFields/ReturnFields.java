package com.GUI.returnFields;

import java.util.ArrayList;
import java.util.List;

import com.GUI.ConvertHex.HexToBinary;

public class ReturnFields {
	public static void main(String args[]) {
		ReturnFields rf = new ReturnFields("F23A44810EE48000", "0000004006000000");
		System.out.println(rf.retf());
	}

	/*
	 * There are total 128 fields 0 = off(false) 1 = on(true)
	 */
	// private boolean on;
   //  private boolean off;
	private String PrimaryBitmap1;
	private String PrimaryBitmap2;
	private String Binary1;
	private String Binary2;
	private String finalBinary;

	public ReturnFields(String PrimaryBitmap1, String PrimaryBitmap2) {
		this.PrimaryBitmap1 = PrimaryBitmap1;
		this.PrimaryBitmap2 = PrimaryBitmap2;
		ConvertoToBinary();
	}

	public void ConvertoToBinary() {
		HexToBinary htb = new HexToBinary();
		Binary1 = htb.ConverthexToBinary(PrimaryBitmap1); // 1111001000111110010001001000000100101000111001001001000000000000
		Binary2 = htb.ConverthexToBinary(PrimaryBitmap2); // 0000000000000000000000000000000000000110000000000000000000110000
		this.finalBinary = Binary1 + Binary2;
		System.out.println(finalBinary);
	}

	public List<Integer> retf() {
		int len = finalBinary.length();
		List<Integer> k = new ArrayList<Integer>();
		k.add(0); // add 0 for bitmap
		for (int i = 0; i < len; i++) {
			char onoff = finalBinary.charAt(i);
			if (onoff == '1') {
				k.add(i + 1); // add 1 because PDX DataFormat starts from 0
			}
		}
		return k;

	}

	public int[] returnfieldsArray() {
		char[] rf = new char[finalBinary.length()];
		for (int i = 0; i < finalBinary.length(); i++) {
			rf[i] = finalBinary.charAt(i);
		}
		int[] len = new int[rf.length];

		return null;

	}

	public int[] OnFields(char[] rf) {
		int[] onf = new int[rf.length];
		for (char c : rf) {
			if (c == 1) {
				onf[c] = 2;
			}
		}
		return null;

	}

	/*
	 * First get all binary values, then return only on field values according to
	 * position like for 1111 send field 1 2 3 4 then 00 dont send again 1 send
	 * field 7 Send Field no, 1,2,3,4,7,11,12,13,14,15,18,21 etc
	 * 
	 */

}
