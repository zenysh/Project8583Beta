package com.GUI.Password;

public class EncryptAndDecryptAndReturn {
	private String frm_password;
	private final String Password = "farewell";
	String EncryptedPassword = Encryption.encrypt(Password);

	public EncryptAndDecryptAndReturn(String password) {
		frm_password = password;
	}

	public boolean passwordisCorrect() {
		if (encryptandDecryptpassword()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean encryptandDecryptpassword() {
		if (Decryption.decrypt(EncryptedPassword).equals(frm_password)) {
			return true;
		} else {
			return false;
		}
	}
}
