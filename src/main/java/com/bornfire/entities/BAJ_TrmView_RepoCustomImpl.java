package com.bornfire.entities;

import java.util.*;
import java.util.stream.Collectors;

import javax.persistence.*;

import org.springframework.stereotype.Repository;

import com.ibm.icu.math.BigDecimal;
@Repository("bajCustomRepo")
public class BAJ_TrmView_RepoCustomImpl implements BAJ_TrmView_RepoCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BAJ_TrmView_Entity> getOrderByFields(Date trandate, String selectedValue) {
        // ✅ Step 1: Validate allowed columns
        String[] allowed = { "tran_date", "tran_id", "part_tran_id", "part_tran_type","acct_crncy", "tran_amt", "acct_num","acct_name" ,"tran_particluar"};
        Set<String> allowedSet = new HashSet<>(Arrays.asList(allowed));

        List<String> fields = Arrays.asList(selectedValue.split(","));
        for (String field : fields) {
            if (!allowedSet.contains(field.trim())) {
                throw new IllegalArgumentException("Invalid column in selectedValue: " + field);
            }
        }

        // ✅ Step 2: Build query
        String queryStr = "SELECT * FROM TRMWORK_VIEW WHERE tran_date = :trandate";
        if (!fields.isEmpty()) {
            queryStr += " ORDER BY " + String.join(", ", fields);
        }

        // ✅ Step 3: Execute query
        Query query = entityManager.createNativeQuery(queryStr, BAJ_TrmView_Entity.class);
        query.setParameter("trandate", trandate);

        return query.getResultList();
    }
    
    @Override
    public List<BAJ_TrmView_Entity> getOrderByFieldsusingRange(Date trandate, String rangefrom, String rangeto, String selectedValue) {
        // ✅ Step 1: Define allowed columns to avoid SQL injection
        String[] allowed = { "tran_date", "tran_id", "part_tran_id", "part_tran_type", "acct_crncy", "tran_amt", "acct_num","acct_name" ,"tran_particluar" };
        Set<String> allowedSet = new HashSet<>(Arrays.asList(allowed));

        List<String> fields = Arrays.asList(selectedValue.split(","));
        for (String field : fields) {
            if (!allowedSet.contains(field.trim())) {
                throw new IllegalArgumentException("Invalid column in selectedValue: " + field);
            }
        }

        // ✅ Step 2: Build query
        StringBuilder queryStr = new StringBuilder("SELECT * FROM TRMWORK_VIEW WHERE tran_date = :trandate AND tran_amt BETWEEN :rangefrom AND :rangeto");

        if (!fields.isEmpty()) {
            queryStr.append(" ORDER BY ").append(String.join(", ", fields));
        }

        // ✅ Step 3: Execute query
        Query query = entityManager.createNativeQuery(queryStr.toString(), BAJ_TrmView_Entity.class);
        query.setParameter("trandate", trandate);
        query.setParameter("rangefrom", rangefrom);
        query.setParameter("rangeto", rangeto);

        return query.getResultList();
    }
    
    @Override
    public List<BAJ_TrmView_Entity> getOrderByFieldsusingAcctNo(Date trandate, String acctno, String selectedValue) {

        // ✅ Step 1: Define allowed fields for ORDER BY
        String[] allowed = { "tran_date", "tran_id", "part_tran_id", "part_tran_type", "acct_crncy", "tran_amt", "acct_num","acct_name" ,"tran_particluar" };
        Set<String> allowedSet = new HashSet<>(Arrays.asList(allowed));

        // ✅ Step 2: Split, trim, and validate selected fields
        List<String> fields = Arrays.stream(selectedValue.split(","))
                                    .map(String::trim)
                                    .collect(Collectors.toList());

        for (String field : fields) {
            if (!allowedSet.contains(field)) {
                throw new IllegalArgumentException("Invalid column in selectedValue: " + field);
            }
        }

        // ✅ Step 3: Build dynamic query
        StringBuilder queryStr = new StringBuilder("SELECT * FROM TRMWORK_VIEW WHERE tran_date = :trandate AND acct_num = :acctno");

        if (!fields.isEmpty()) {
            queryStr.append(" ORDER BY ").append(String.join(", ", fields));
        }

        // ✅ Step 4: Execute query
        Query query = entityManager.createNativeQuery(queryStr.toString(), BAJ_TrmView_Entity.class);
        query.setParameter("trandate", trandate);
        query.setParameter("acctno", acctno);

        return query.getResultList();
    }

    @Override
    public List<BAJ_TrmView_Entity> getOrderByFieldsusingTranid(Date trandate, String acctno, String tranid, String selectedValue) {
        
        // ✅ Step 1: Define allowed columns for ORDER BY
        String[] allowed = { "tran_date", "tran_id", "part_tran_id", "part_tran_type", "acct_crncy", "tran_amt", "acct_num","acct_name" ,"tran_particluar" };
        Set<String> allowedSet = new HashSet<>(Arrays.asList(allowed));

        // ✅ Step 2: Sanitize and validate input fields
        List<String> fields = Arrays.stream(selectedValue.split(","))
                                    .map(String::trim)
                                    .collect(Collectors.toList());

        for (String field : fields) {
            if (!allowedSet.contains(field)) {
                throw new IllegalArgumentException("Invalid column in selectedValue: " + field);
            }
        }

        // ✅ Step 3: Build dynamic SQL query
        StringBuilder queryStr = new StringBuilder("SELECT * FROM TRMWORK_VIEW WHERE tran_date = :trandate AND acct_num = :acctno AND tran_id = :tranid");

        if (!fields.isEmpty()) {
            queryStr.append(" ORDER BY ").append(String.join(", ", fields));
        }

        // ✅ Step 4: Run the query
        Query query = entityManager.createNativeQuery(queryStr.toString(), BAJ_TrmView_Entity.class);
        query.setParameter("trandate", trandate);
        query.setParameter("acctno", acctno);
        query.setParameter("tranid", tranid);

        return query.getResultList();
    }


    @Override
    public List<BAJ_TrmView_Entity> getOrderByFieldsusingPart_Tranid(Date trandate, String acctno, String tranid,String partTranId,String selectedValue) {
        
        // ✅ Step 1: Define allowed columns for ORDER BY
        String[] allowed = { "tran_date", "tran_id", "part_tran_id", "part_tran_type", "acct_crncy", "tran_amt", "acct_num","acct_name" ,"tran_particluar" };
        Set<String> allowedSet = new HashSet<>(Arrays.asList(allowed));

        // ✅ Step 2: Sanitize and validate input fields
        List<String> fields = Arrays.stream(selectedValue.split(","))
                                    .map(String::trim)
                                    .collect(Collectors.toList());

        for (String field : fields) {
            if (!allowedSet.contains(field)) {
                throw new IllegalArgumentException("Invalid column in selectedValue: " + field);
            }
        }

        // ✅ Step 3: Build dynamic SQL query
        StringBuilder queryStr = new StringBuilder("SELECT * FROM TRMWORK_VIEW WHERE tran_date = :trandate AND acct_num = :acctno AND tran_id = :tranid and part_tran_id =:partTranId");

        if (!fields.isEmpty()) {
            queryStr.append(" ORDER BY ").append(String.join(", ", fields));
        }

        // ✅ Step 4: Run the query
        Query query = entityManager.createNativeQuery(queryStr.toString(), BAJ_TrmView_Entity.class);
        query.setParameter("trandate", trandate);
        query.setParameter("acctno", acctno);
        query.setParameter("tranid", tranid);
        query.setParameter("partTranId", partTranId);

        return query.getResultList();
    }
}
