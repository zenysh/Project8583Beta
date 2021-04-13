package com.GUI.ResponseFields;

public class ISOBreakDown {
	private String ISOResponse;
	private String PrimaryBitmap;
	private int PrimaryBitmapLength = 16;
	private String BitMapExtended;
	private int BitMapExtendedLength = 16;
	private String PAN;
	private int PANLength = 19;
	private String ProcessCode;
	private int ProcessCodeLength = 6;
	private String Amount;
	private int AmountLength = 12;
	private String TranDateAndTime;
	private int TranDateAndTimeLength = 10;
	private String SystemTrace;
	private int SystemTraceLength = 6;
	private String timelocalTran;
	private int timelocalTranLength = 6;
	private String DateLocalTran;
	private int DateLocalTranLength = 4;
	private String Datesettle;
	private int DateSettleLength = 4;
	private String MerchantType;
	private int MerchantTypeLength = 4;
	private String AcquierID;
	private int AcquirerIDLength = 11;
	private String AuthResponsel;
	private int AuthResponse1 = 6;
	private String RetrievalReference;
	private int retrivallength = 12;
	private String ResponseCode;
	private int ResponseCodeLength = 2;
	private String TerminalId;
	private int TerminalIdLength = 8;
	/* Used For Reversal only */
	private String CardAcceptorIdentification;
	private int CardAcceptorIdentificationlength = 15;
	private String CardAcceptorname;
	private int CardAcceptornamelength = 40;
	/*------------------------------------*/
	private String additionaDataNational;
	private int additionalDataNationalLength = 999;
	private String additionaDataPrivate;
	private int additionaDataPrivateLength = 999;
	private String CurrencyCode; // 524
	private int CurrencyCodeLength = 3;
	private String additionalAmounts;
	private int additionalAmountsLength = 120;
	private String Account1;
	private int Account1Length = 28;
	private String Account2;
	private int Account2Length = 28;
	int Skipvalue = 8;

	public ISOBreakDown(String ISOResponse) {
		this.ISOResponse = ISOResponse;
	}

	public void SetFields() {
		// String StringBuilderLength = ISOResponse.substring(1, 3);
		// int Length = Integer.parseInt(StringBuilderLength) + 4;
		StringBuilder sb = new StringBuilder(ISOResponse);

		setPrimaryBitmap(sb.substring(Skipvalue, Skipvalue += PrimaryBitmapLength));
		setBitMapExtended(sb.substring(Skipvalue, Skipvalue += BitMapExtendedLength));
		setPAN(GetLLVARLength(PANLength, sb.substring(Skipvalue, Skipvalue += 2), sb));
		setProcessCode(sb.substring(Skipvalue, Skipvalue += ProcessCodeLength));
		setAmount(sb.substring(Skipvalue, Skipvalue += AmountLength));
		setTranDateAndTime(sb.substring(Skipvalue, Skipvalue += TranDateAndTimeLength));
		setSystemTrace(sb.substring(Skipvalue, Skipvalue += SystemTraceLength));
		setTimelocalTran(sb.substring(Skipvalue, Skipvalue += timelocalTranLength));
		setDateLocalTran(sb.substring(Skipvalue, Skipvalue += DateLocalTranLength));
		setDatesettle(sb.substring(Skipvalue, Skipvalue += DateSettleLength));
		setMerchantType(sb.substring(Skipvalue, Skipvalue += MerchantTypeLength));
		setAcquierID(GetLLVARLength(AcquirerIDLength, sb.substring(Skipvalue, Skipvalue += 2), sb));
		setAuthResponsel(sb.substring(Skipvalue, Skipvalue += AuthResponse1));
		setResponseCode(sb.substring(Skipvalue, Skipvalue += ResponseCodeLength));
		setTerminalId(sb.substring(Skipvalue, Skipvalue += TerminalIdLength));
		setAdditionaDataNational(
				GetLLLVARLength(additionalDataNationalLength, sb.substring(Skipvalue, Skipvalue += 3), sb));
		setAdditionaDataPrivate(
				GetLLLVARLength(additionaDataPrivateLength, sb.substring(Skipvalue, Skipvalue += 3), sb));
		setCurrencyCode(sb.substring(Skipvalue, Skipvalue += CurrencyCodeLength));
		setAdditionalAmounts(GetLLLVARLength(additionalAmountsLength, sb.substring(Skipvalue, Skipvalue += 3), sb));
		setAccount1(GetLLVARLength(Account1Length, sb.substring(Skipvalue, Skipvalue += 2), sb));
		setAccount2(GetLLVARLength(Account2Length, sb.substring(Skipvalue, Skipvalue += 2), sb));
	}

	public void setFieldsReversal() {
		StringBuilder sb = new StringBuilder(ISOResponse);

		setPrimaryBitmap(sb.substring(Skipvalue, Skipvalue += PrimaryBitmapLength));
		setBitMapExtended(sb.substring(Skipvalue, Skipvalue += BitMapExtendedLength));
		setPAN(GetLLVARLength(PANLength, sb.substring(Skipvalue, Skipvalue += 2), sb));
		setProcessCode(sb.substring(Skipvalue, Skipvalue += ProcessCodeLength));
		setAmount(sb.substring(Skipvalue, Skipvalue += AmountLength));
		setTranDateAndTime(sb.substring(Skipvalue, Skipvalue += TranDateAndTimeLength));
		setSystemTrace(sb.substring(Skipvalue, Skipvalue += SystemTraceLength));
		setTimelocalTran(sb.substring(Skipvalue, Skipvalue += timelocalTranLength));
		setDateLocalTran(sb.substring(Skipvalue, Skipvalue += DateLocalTranLength));
		setDatesettle(sb.substring(Skipvalue, Skipvalue += DateSettleLength));
		setMerchantType(sb.substring(Skipvalue, Skipvalue += MerchantTypeLength));
		setAcquierID(GetLLVARLength(AcquirerIDLength, sb.substring(Skipvalue, Skipvalue += 2), sb));
		setRetrievalReference(AnyNULLFormat(sb.substring(Skipvalue, Skipvalue += retrivallength), retrivallength));
		System.out.println(getRetrievalReference());
		setAuthResponsel(sb.substring(Skipvalue, Skipvalue += AuthResponse1));
		setResponseCode(sb.substring(Skipvalue, Skipvalue += ResponseCodeLength));
		setTerminalId(sb.substring(Skipvalue, Skipvalue += TerminalIdLength));
		setCardAcceptorIdentification(
				AnyNULLFormat(sb.substring(Skipvalue, Skipvalue += CardAcceptorIdentificationlength),
						CardAcceptorIdentificationlength));
		setCardAcceptorname(
				AnyNULLFormat(sb.substring(Skipvalue, Skipvalue += CardAcceptornamelength), CardAcceptornamelength));
		setCurrencyCode(sb.substring(Skipvalue, Skipvalue += CurrencyCodeLength));
		System.out.println(getCurrencyCode());
		setAccount1(GetLLVARLength(Account1Length, sb.substring(Skipvalue, Skipvalue += 2), sb));
		setAccount2(GetLLVARLength(Account2Length, sb.substring(Skipvalue, Skipvalue += 2), sb));
	}

	public String GetLLVARLength(int length, String FrontLength, StringBuilder sb) {
		if (FrontLength.contentEquals("00")) {
			return "00";
			// set TextField.PAN = Disabled or blank
		} else {
			int LLVARlength = Integer.parseInt(FrontLength);
			return sb.substring(Skipvalue, Skipvalue += LLVARlength).toString();
		}
	}

	public String GetLLLVARLength(int length, String FrontLength, StringBuilder sb) {
		if (FrontLength.contentEquals("000")) {
			return "000";
			// set TextField.PAN = Disabled or blank
		} else {
			int LLVARlength = Integer.parseInt(FrontLength);
			return sb.substring(Skipvalue, Skipvalue += LLVARlength).toString();
		}
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

	public String getPrimaryBitmap() {
		return PrimaryBitmap;
	}

	public void setPrimaryBitmap(String primaryBitmap) {
		PrimaryBitmap = primaryBitmap;
	}

	public String getBitMapExtended() {
		return BitMapExtended;
	}

	public void setBitMapExtended(String bitMapExtended) {
		BitMapExtended = bitMapExtended;
	}

	public String getPAN() {
		return PAN;
	}

	public void setPAN(String pAN) {
		PAN = pAN;
	}

	public String getProcessCode() {
		return ProcessCode;
	}

	public void setProcessCode(String processCode) {
		ProcessCode = processCode;
	}

	public String getAmount() {
		return Amount;
	}

	public void setAmount(String amount) {
		Amount = amount;
	}

	public String getTranDateAndTime() {
		return TranDateAndTime;
	}

	public void setTranDateAndTime(String tranDateAndTime) {
		TranDateAndTime = tranDateAndTime;
	}

	public String getSystemTrace() {
		return SystemTrace;
	}

	public void setSystemTrace(String systemTrace) {
		SystemTrace = systemTrace;
	}

	public String getTimelocalTran() {
		return timelocalTran;
	}

	public void setTimelocalTran(String timelocalTran) {
		this.timelocalTran = timelocalTran;
	}

	public String getDateLocalTran() {
		return DateLocalTran;
	}

	public void setDateLocalTran(String dateLocalTran) {
		DateLocalTran = dateLocalTran;
	}

	public String getDatesettle() {
		return Datesettle;
	}

	public void setDatesettle(String datesettle) {
		Datesettle = datesettle;
	}

	public String getMerchantType() {
		return MerchantType;
	}

	public void setMerchantType(String merchantType) {
		MerchantType = merchantType;
	}

	public String getAcquierID() {
		return AcquierID;
	}

	public void setAcquierID(String acquierID) {
		AcquierID = acquierID;
	}

	public String getAuthResponsel() {
		return AuthResponsel;
	}

	public void setAuthResponsel(String authResponsel) {
		AuthResponsel = authResponsel;
	}

	public String getRetrievalReference() {
		return RetrievalReference;
	}

	public void setRetrievalReference(String retrievalReference) {
		RetrievalReference = retrievalReference;
	}

	public String getResponseCode() {
		return ResponseCode;
	}

	public void setResponseCode(String responseCode) {
		ResponseCode = responseCode;
	}

	public String getTerminalId() {
		return TerminalId;
	}

	public void setTerminalId(String terminalId) {
		TerminalId = terminalId;
	}

	public String getCardAcceptorIdentification() {
		return CardAcceptorIdentification;
	}

	public void setCardAcceptorIdentification(String cardAcceptorIdentification) {
		CardAcceptorIdentification = cardAcceptorIdentification;
	}

	public String getCardAcceptorname() {
		return CardAcceptorname;
	}

	public void setCardAcceptorname(String cardAcceptorname) {
		CardAcceptorname = cardAcceptorname;
	}

	public String getAdditionaDataNational() {
		return additionaDataNational;
	}

	public void setAdditionaDataNational(String additionaDataNational) {
		this.additionaDataNational = additionaDataNational;
	}

	public String getAdditionaDataPrivate() {
		return additionaDataPrivate;
	}

	public void setAdditionaDataPrivate(String additionaDataPrivate) {
		this.additionaDataPrivate = additionaDataPrivate;
	}

	public String getCurrencyCode() {
		return CurrencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		CurrencyCode = currencyCode;
	}

	public String getAdditionalAmounts() {
		return additionalAmounts;
	}

	public void setAdditionalAmounts(String additionalAmounts) {
		this.additionalAmounts = additionalAmounts;
	}

	public String getAccount1() {
		return Account1;
	}

	public void setAccount1(String account1) {
		Account1 = account1;
	}

	public String getAccount2() {
		return Account2;
	}

	public void setAccount2(String account2) {
		Account2 = account2;
	}

}
