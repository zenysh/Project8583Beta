package com.GUI.FieldsModifiers;

public class FieldModifier {
	private String zero = "0";

	public String LLVARString(String values) {

		String llvarstring = null;
		if (values.isEmpty()) {
			llvarstring = "00";
		} else if (values.length() <= 9) {
			llvarstring = "0" + values.length() + values;
		} else if (values.length() > 9) {
			llvarstring = values.length() + values;
		}

		return llvarstring;
	}

	public String LLLVARString(String values) {

		String lllvarstring = null;
		if (values.isEmpty()) {
			lllvarstring = "000";
		} else if (values.length() <= 9) {
			lllvarstring = "00" + values.length() + values;
		} else if (values.length() > 9 && values.length() <= 99) {
			lllvarstring = "0" + values.length() + values;
		} else if (values.length() > 99) {
			lllvarstring = values.length() + values;
		}

		return lllvarstring;
	}

	public String AnyNULLFormat(String values, int index) {
		String realvalues = null;
		if (values.length() == 0) {
			realvalues = AddSpace(values, index);
		} else if (values.length() == index) {
			realvalues = values;
		} else if (values.length() < index) {
			realvalues = values + AddSpace(values, index);
		}
		return realvalues;

	}

	public String AddSpace(String values, int actuallength) {
		int j = values.length();
		int addspace = actuallength - j;
		String spaces = "";
		String asterik = "*";
		for (int i = 0; i < addspace; i++) {
			spaces = spaces + asterik;
		}
		return spaces.replace("*", " ");
	}

	public String Amount(String amount) {
		String spaces = "";
		String asterik = "*";
		int requiredlength = 12;
		if (amount.length() == 12) {
			return amount;
		} else {
			int length = requiredlength - amount.length();
			for (int i = 0; i < length; i++) {
				spaces = spaces + asterik;
			}
		}
		return spaces.replace("*", "0") + amount;
	}

	public String appendZeroInfront(String values) {
		String finalvalue;
		if (values.length() == 6) {
			return values;
		} else {
			int zerotoAppen = 6 - values.length();
			switch (zerotoAppen) {
			case 1:
				finalvalue = "0" + values;
				break;
			case 2:
				finalvalue = "00" + values;
				break;
			case 3:
				finalvalue = "000" + values;
				break;
			case 4:
				finalvalue = "0000" + values;
				break;
			case 5:
				finalvalue = "00000" + values;
				break;
			default:
				finalvalue = "000000";
			}
			return finalvalue;
		}

	}

	public String AppendrequiredZero(String message, int requiredlength) {
		int zeroToAdd = requiredlength - message.length();
		if (requiredlength == message.length()) {
			return message;
		} else {
			for (int i = 0; i < zeroToAdd - 1; i++) { /*-1 to ensure required zero is valid*/
				zero += "0";
			}

		}
		return zero + message;
	}

}
