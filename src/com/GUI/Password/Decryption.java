package com.GUI.Password;

import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class Decryption {
	private static String secretKey = "7?UcHnHtf\"%)k[9q.2G9hbrnVPN!ZX\"$nv%PD8;ca[LX\\f'fCAW`m!&En2`/.tHe#Zu%qHsCeqAk~cWBG~TPD<p/8^$\"qV\\2&n[^m[ag8G2=aP=:j^3\\_.@rVft&Hx,6W;<gn5r7#X#@2M~$c34,^>k~N<u%)@T/)hZBY`}fYX>RW4C#t(~:9x]<J%V{t[4apN8\\+b!Dp)r<KyHvU&C_BJHq#_,fDKchAG`E!^Md8wAvw\\=$m$*rWg;?Ks:vEmf>2X)4zn![5z/Uq_Ukp78m<~DuJu?smN'P{;s3Qh2m'GCPew,&e34Rm#@G})BR/L{?HdVWzYH*5v~TpV`9mx]KUsG{LhE/yG){abL}`h(S\"tGjCEFmhn/&P2.5x[[G.532F^8(@kM?eP'?GQk/[EdZS->w3xQm4k\"}8L:$ubVHM<AhYhzr@3YS)646m95\"su[ME8G`t;N@[nfn2=z\"c?J]Xjc_k-V=.\\:E9>\\2NU!cQUuEw7xE2%nUSsKNT\\$Q&@CE";
	private static String salt = "m{=-g{rapC8P+t;[dJ<5/)T@kM\\`kDjMmW\":DC<^3Ae*J'f2Lv]e\"8Bch*#;s]KHXmV)G%5Kj$\\2z\"*DF)nN?BEC?kH;M(Lpb@{@)E({j_P]";

	public static String decrypt(String strToDecrypt) {
		try {
			byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			IvParameterSpec ivspec = new IvParameterSpec(iv);

			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		} catch (Exception e) {
			System.out.println("Error while decrypting: " + e.toString());
		}
		return null;
	}
}
