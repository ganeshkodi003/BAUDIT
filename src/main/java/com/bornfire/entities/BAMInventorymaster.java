package com.bornfire.entities;


import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="BTM_BAM_INVENTORY_MASTER")
public class BAMInventorymaster {
	
	private BigDecimal ACC_DEPR;
	@Id
    private String ASST_ACCT_NO;
    private String ASST_BRAND;
    private String ASST_CATEGORY;
    private String ASST_CATEGORY_DESC;
    private String ASST_CRNCY;
    private String ASST_DESCRIPTION;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ASST_EXP_DATE;
    
    private String ASST_HEAD;
    private String ASST_NAME;
    private String ASST_RMKS;
    private String ASST_SRL_NO;
    private String ASST_SUB_CATEOGRY;
    private String ASST_TYPE;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date AUTH_TIME;
    
    private String AUTH_USER;
    private BigDecimal CUR_BOOK_VALUE;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date DATE_OF_ACQN;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date DATE_OF_LAST_DEPR;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date DATE_OF_LAST_TFR;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date DATE_OF_PURCHASE;
    
    private String DEL_FLG;
    private BigDecimal DEPR_AMOUNT;
    private String DEPR_FLAG;
    private String DEPR_FREQ;
    private String DEPR_METHOD;
    private BigDecimal DEPR_PERCENT;
    private String DEPR_PL_ACCOUNT;
    private String DEPR_RMKS;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date DEPR_START_DATE;
    
    private String DEPT_DIV_NAME;
    private String EMP_ID;
    private String ENTITY_FLG;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ENTRY_TIME;
    
    private String ENTRY_USER;
    private BigDecimal GTEE_AMT;
    private BigDecimal GTEE_AMT_PERCENT;
    private BigDecimal LIFE_SPAN_MTH;
    private String LOCA_ADDR;
    private String LOC_RMKS;
    private String LOC_TYPE;
    private BigDecimal MKT_VALUE;
    private String MODIFY_FLG;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date MODIFY_TIME;
    
    private String MODIFY_USER;
    private BigDecimal NOM_DEPR_AMT;
    private BigDecimal ORG_COST;
    private String SOL_ID;
    private String TRAN_FLG;
    private Integer YEAR_OF_PURCHASE;
	public BigDecimal getACC_DEPR() {
		return ACC_DEPR;
	}
	public void setACC_DEPR(BigDecimal aCC_DEPR) {
		ACC_DEPR = aCC_DEPR;
	}
	public String getASST_ACCT_NO() {
		return ASST_ACCT_NO;
	}
	public void setASST_ACCT_NO(String aSST_ACCT_NO) {
		ASST_ACCT_NO = aSST_ACCT_NO;
	}
	public String getASST_BRAND() {
		return ASST_BRAND;
	}
	public void setASST_BRAND(String aSST_BRAND) {
		ASST_BRAND = aSST_BRAND;
	}
	public String getASST_CATEGORY() {
		return ASST_CATEGORY;
	}
	public void setASST_CATEGORY(String aSST_CATEGORY) {
		ASST_CATEGORY = aSST_CATEGORY;
	}
	public String getASST_CATEGORY_DESC() {
		return ASST_CATEGORY_DESC;
	}
	public void setASST_CATEGORY_DESC(String aSST_CATEGORY_DESC) {
		ASST_CATEGORY_DESC = aSST_CATEGORY_DESC;
	}
	public String getASST_CRNCY() {
		return ASST_CRNCY;
	}
	public void setASST_CRNCY(String aSST_CRNCY) {
		ASST_CRNCY = aSST_CRNCY;
	}
	public String getASST_DESCRIPTION() {
		return ASST_DESCRIPTION;
	}
	public void setASST_DESCRIPTION(String aSST_DESCRIPTION) {
		ASST_DESCRIPTION = aSST_DESCRIPTION;
	}
	public Date getASST_EXP_DATE() {
		return ASST_EXP_DATE;
	}
	public void setASST_EXP_DATE(Date aSST_EXP_DATE) {
		ASST_EXP_DATE = aSST_EXP_DATE;
	}
	public String getASST_HEAD() {
		return ASST_HEAD;
	}
	public void setASST_HEAD(String aSST_HEAD) {
		ASST_HEAD = aSST_HEAD;
	}
	public String getASST_NAME() {
		return ASST_NAME;
	}
	public void setASST_NAME(String aSST_NAME) {
		ASST_NAME = aSST_NAME;
	}
	public String getASST_RMKS() {
		return ASST_RMKS;
	}
	public void setASST_RMKS(String aSST_RMKS) {
		ASST_RMKS = aSST_RMKS;
	}
	public String getASST_SRL_NO() {
		return ASST_SRL_NO;
	}
	public void setASST_SRL_NO(String aSST_SRL_NO) {
		ASST_SRL_NO = aSST_SRL_NO;
	}
	public String getASST_SUB_CATEOGRY() {
		return ASST_SUB_CATEOGRY;
	}
	public void setASST_SUB_CATEOGRY(String aSST_SUB_CATEOGRY) {
		ASST_SUB_CATEOGRY = aSST_SUB_CATEOGRY;
	}
	public String getASST_TYPE() {
		return ASST_TYPE;
	}
	public void setASST_TYPE(String aSST_TYPE) {
		ASST_TYPE = aSST_TYPE;
	}
	public Date getAUTH_TIME() {
		return AUTH_TIME;
	}
	public void setAUTH_TIME(Date aUTH_TIME) {
		AUTH_TIME = aUTH_TIME;
	}
	public String getAUTH_USER() {
		return AUTH_USER;
	}
	public void setAUTH_USER(String aUTH_USER) {
		AUTH_USER = aUTH_USER;
	}
	public BigDecimal getCUR_BOOK_VALUE() {
		return CUR_BOOK_VALUE;
	}
	public void setCUR_BOOK_VALUE(BigDecimal cUR_BOOK_VALUE) {
		CUR_BOOK_VALUE = cUR_BOOK_VALUE;
	}
	public Date getDATE_OF_ACQN() {
		return DATE_OF_ACQN;
	}
	public void setDATE_OF_ACQN(Date dATE_OF_ACQN) {
		DATE_OF_ACQN = dATE_OF_ACQN;
	}
	public Date getDATE_OF_LAST_DEPR() {
		return DATE_OF_LAST_DEPR;
	}
	public void setDATE_OF_LAST_DEPR(Date dATE_OF_LAST_DEPR) {
		DATE_OF_LAST_DEPR = dATE_OF_LAST_DEPR;
	}
	public Date getDATE_OF_LAST_TFR() {
		return DATE_OF_LAST_TFR;
	}
	public void setDATE_OF_LAST_TFR(Date dATE_OF_LAST_TFR) {
		DATE_OF_LAST_TFR = dATE_OF_LAST_TFR;
	}
	public Date getDATE_OF_PURCHASE() {
		return DATE_OF_PURCHASE;
	}
	public void setDATE_OF_PURCHASE(Date dATE_OF_PURCHASE) {
		DATE_OF_PURCHASE = dATE_OF_PURCHASE;
	}
	public String getDEL_FLG() {
		return DEL_FLG;
	}
	public void setDEL_FLG(String dEL_FLG) {
		DEL_FLG = dEL_FLG;
	}
	public BigDecimal getDEPR_AMOUNT() {
		return DEPR_AMOUNT;
	}
	public void setDEPR_AMOUNT(BigDecimal dEPR_AMOUNT) {
		DEPR_AMOUNT = dEPR_AMOUNT;
	}
	public String getDEPR_FLAG() {
		return DEPR_FLAG;
	}
	public void setDEPR_FLAG(String dEPR_FLAG) {
		DEPR_FLAG = dEPR_FLAG;
	}
	public String getDEPR_FREQ() {
		return DEPR_FREQ;
	}
	public void setDEPR_FREQ(String dEPR_FREQ) {
		DEPR_FREQ = dEPR_FREQ;
	}
	public String getDEPR_METHOD() {
		return DEPR_METHOD;
	}
	public void setDEPR_METHOD(String dEPR_METHOD) {
		DEPR_METHOD = dEPR_METHOD;
	}
	public BigDecimal getDEPR_PERCENT() {
		return DEPR_PERCENT;
	}
	public void setDEPR_PERCENT(BigDecimal dEPR_PERCENT) {
		DEPR_PERCENT = dEPR_PERCENT;
	}
	public String getDEPR_PL_ACCOUNT() {
		return DEPR_PL_ACCOUNT;
	}
	public void setDEPR_PL_ACCOUNT(String dEPR_PL_ACCOUNT) {
		DEPR_PL_ACCOUNT = dEPR_PL_ACCOUNT;
	}
	public String getDEPR_RMKS() {
		return DEPR_RMKS;
	}
	public void setDEPR_RMKS(String dEPR_RMKS) {
		DEPR_RMKS = dEPR_RMKS;
	}
	public Date getDEPR_START_DATE() {
		return DEPR_START_DATE;
	}
	public void setDEPR_START_DATE(Date dEPR_START_DATE) {
		DEPR_START_DATE = dEPR_START_DATE;
	}
	public String getDEPT_DIV_NAME() {
		return DEPT_DIV_NAME;
	}
	public void setDEPT_DIV_NAME(String dEPT_DIV_NAME) {
		DEPT_DIV_NAME = dEPT_DIV_NAME;
	}
	public String getEMP_ID() {
		return EMP_ID;
	}
	public void setEMP_ID(String eMP_ID) {
		EMP_ID = eMP_ID;
	}
	public String getENTITY_FLG() {
		return ENTITY_FLG;
	}
	public void setENTITY_FLG(String eNTITY_FLG) {
		ENTITY_FLG = eNTITY_FLG;
	}
	public Date getENTRY_TIME() {
		return ENTRY_TIME;
	}
	public void setENTRY_TIME(Date eNTRY_TIME) {
		ENTRY_TIME = eNTRY_TIME;
	}
	public String getENTRY_USER() {
		return ENTRY_USER;
	}
	public void setENTRY_USER(String eNTRY_USER) {
		ENTRY_USER = eNTRY_USER;
	}
	public BigDecimal getGTEE_AMT() {
		return GTEE_AMT;
	}
	public void setGTEE_AMT(BigDecimal gTEE_AMT) {
		GTEE_AMT = gTEE_AMT;
	}
	public BigDecimal getGTEE_AMT_PERCENT() {
		return GTEE_AMT_PERCENT;
	}
	public void setGTEE_AMT_PERCENT(BigDecimal gTEE_AMT_PERCENT) {
		GTEE_AMT_PERCENT = gTEE_AMT_PERCENT;
	}
	public BigDecimal getLIFE_SPAN_MTH() {
		return LIFE_SPAN_MTH;
	}
	public void setLIFE_SPAN_MTH(BigDecimal lIFE_SPAN_MTH) {
		LIFE_SPAN_MTH = lIFE_SPAN_MTH;
	}
	public String getLOCA_ADDR() {
		return LOCA_ADDR;
	}
	public void setLOCA_ADDR(String lOCA_ADDR) {
		LOCA_ADDR = lOCA_ADDR;
	}
	public String getLOC_RMKS() {
		return LOC_RMKS;
	}
	public void setLOC_RMKS(String lOC_RMKS) {
		LOC_RMKS = lOC_RMKS;
	}
	public String getLOC_TYPE() {
		return LOC_TYPE;
	}
	public void setLOC_TYPE(String lOC_TYPE) {
		LOC_TYPE = lOC_TYPE;
	}
	public BigDecimal getMKT_VALUE() {
		return MKT_VALUE;
	}
	public void setMKT_VALUE(BigDecimal mKT_VALUE) {
		MKT_VALUE = mKT_VALUE;
	}
	public String getMODIFY_FLG() {
		return MODIFY_FLG;
	}
	public void setMODIFY_FLG(String mODIFY_FLG) {
		MODIFY_FLG = mODIFY_FLG;
	}
	public Date getMODIFY_TIME() {
		return MODIFY_TIME;
	}
	public void setMODIFY_TIME(Date mODIFY_TIME) {
		MODIFY_TIME = mODIFY_TIME;
	}
	public String getMODIFY_USER() {
		return MODIFY_USER;
	}
	public void setMODIFY_USER(String mODIFY_USER) {
		MODIFY_USER = mODIFY_USER;
	}
	public BigDecimal getNOM_DEPR_AMT() {
		return NOM_DEPR_AMT;
	}
	public void setNOM_DEPR_AMT(BigDecimal nOM_DEPR_AMT) {
		NOM_DEPR_AMT = nOM_DEPR_AMT;
	}
	public BigDecimal getORG_COST() {
		return ORG_COST;
	}
	public void setORG_COST(BigDecimal oRG_COST) {
		ORG_COST = oRG_COST;
	}
	public String getSOL_ID() {
		return SOL_ID;
	}
	public void setSOL_ID(String sOL_ID) {
		SOL_ID = sOL_ID;
	}
	public String getTRAN_FLG() {
		return TRAN_FLG;
	}
	public void setTRAN_FLG(String tRAN_FLG) {
		TRAN_FLG = tRAN_FLG;
	}
	public Integer getYEAR_OF_PURCHASE() {
		return YEAR_OF_PURCHASE;
	}
	public void setYEAR_OF_PURCHASE(Integer yEAR_OF_PURCHASE) {
		YEAR_OF_PURCHASE = yEAR_OF_PURCHASE;
	}
	public BAMInventorymaster(BigDecimal aCC_DEPR, String aSST_ACCT_NO, String aSST_BRAND, String aSST_CATEGORY,
			String aSST_CATEGORY_DESC, String aSST_CRNCY, String aSST_DESCRIPTION, Date aSST_EXP_DATE, String aSST_HEAD,
			String aSST_NAME, String aSST_RMKS, String aSST_SRL_NO, String aSST_SUB_CATEOGRY, String aSST_TYPE,
			Date aUTH_TIME, String aUTH_USER, BigDecimal cUR_BOOK_VALUE, Date dATE_OF_ACQN, Date dATE_OF_LAST_DEPR,
			Date dATE_OF_LAST_TFR, Date dATE_OF_PURCHASE, String dEL_FLG, BigDecimal dEPR_AMOUNT, String dEPR_FLAG,
			String dEPR_FREQ, String dEPR_METHOD, BigDecimal dEPR_PERCENT, String dEPR_PL_ACCOUNT, String dEPR_RMKS,
			Date dEPR_START_DATE, String dEPT_DIV_NAME, String eMP_ID, String eNTITY_FLG, Date eNTRY_TIME,
			String eNTRY_USER, BigDecimal gTEE_AMT, BigDecimal gTEE_AMT_PERCENT, BigDecimal lIFE_SPAN_MTH,
			String lOCA_ADDR, String lOC_RMKS, String lOC_TYPE, BigDecimal mKT_VALUE, String mODIFY_FLG,
			Date mODIFY_TIME, String mODIFY_USER, BigDecimal nOM_DEPR_AMT, BigDecimal oRG_COST, String sOL_ID,
			String tRAN_FLG, Integer yEAR_OF_PURCHASE) {
		super();
		ACC_DEPR = aCC_DEPR;
		ASST_ACCT_NO = aSST_ACCT_NO;
		ASST_BRAND = aSST_BRAND;
		ASST_CATEGORY = aSST_CATEGORY;
		ASST_CATEGORY_DESC = aSST_CATEGORY_DESC;
		ASST_CRNCY = aSST_CRNCY;
		ASST_DESCRIPTION = aSST_DESCRIPTION;
		ASST_EXP_DATE = aSST_EXP_DATE;
		ASST_HEAD = aSST_HEAD;
		ASST_NAME = aSST_NAME;
		ASST_RMKS = aSST_RMKS;
		ASST_SRL_NO = aSST_SRL_NO;
		ASST_SUB_CATEOGRY = aSST_SUB_CATEOGRY;
		ASST_TYPE = aSST_TYPE;
		AUTH_TIME = aUTH_TIME;
		AUTH_USER = aUTH_USER;
		CUR_BOOK_VALUE = cUR_BOOK_VALUE;
		DATE_OF_ACQN = dATE_OF_ACQN;
		DATE_OF_LAST_DEPR = dATE_OF_LAST_DEPR;
		DATE_OF_LAST_TFR = dATE_OF_LAST_TFR;
		DATE_OF_PURCHASE = dATE_OF_PURCHASE;
		DEL_FLG = dEL_FLG;
		DEPR_AMOUNT = dEPR_AMOUNT;
		DEPR_FLAG = dEPR_FLAG;
		DEPR_FREQ = dEPR_FREQ;
		DEPR_METHOD = dEPR_METHOD;
		DEPR_PERCENT = dEPR_PERCENT;
		DEPR_PL_ACCOUNT = dEPR_PL_ACCOUNT;
		DEPR_RMKS = dEPR_RMKS;
		DEPR_START_DATE = dEPR_START_DATE;
		DEPT_DIV_NAME = dEPT_DIV_NAME;
		EMP_ID = eMP_ID;
		ENTITY_FLG = eNTITY_FLG;
		ENTRY_TIME = eNTRY_TIME;
		ENTRY_USER = eNTRY_USER;
		GTEE_AMT = gTEE_AMT;
		GTEE_AMT_PERCENT = gTEE_AMT_PERCENT;
		LIFE_SPAN_MTH = lIFE_SPAN_MTH;
		LOCA_ADDR = lOCA_ADDR;
		LOC_RMKS = lOC_RMKS;
		LOC_TYPE = lOC_TYPE;
		MKT_VALUE = mKT_VALUE;
		MODIFY_FLG = mODIFY_FLG;
		MODIFY_TIME = mODIFY_TIME;
		MODIFY_USER = mODIFY_USER;
		NOM_DEPR_AMT = nOM_DEPR_AMT;
		ORG_COST = oRG_COST;
		SOL_ID = sOL_ID;
		TRAN_FLG = tRAN_FLG;
		YEAR_OF_PURCHASE = yEAR_OF_PURCHASE;
	}
	public BAMInventorymaster() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}