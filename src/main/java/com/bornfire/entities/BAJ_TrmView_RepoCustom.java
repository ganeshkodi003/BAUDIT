package com.bornfire.entities;

import java.util.Date;
import java.util.List;

public interface BAJ_TrmView_RepoCustom {
	 List<BAJ_TrmView_Entity> getOrderByFields(Date trandate, String selectedValue);
	 List<BAJ_TrmView_Entity> getOrderByFieldsusingRange(Date trandate, String rangefrom, String rangeto, String selectedValue);
	 List<BAJ_TrmView_Entity> getOrderByFieldsusingAcctNo(Date trandate, String acctno, String selectedValue);
	 List<BAJ_TrmView_Entity> getOrderByFieldsusingTranid(Date trandate, String acctno, String tranid, String selectedValue);
	 List<BAJ_TrmView_Entity> getOrderByFieldsusingPart_Tranid(Date trandate,String acctno,String tranid,String partTranId,String selectedValue);

}
