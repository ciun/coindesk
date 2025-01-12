package com.coindesk.demo.repostiory;

import com.coindesk.demo.dto.Coindesk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoindeskRepository extends JpaRepository<Coindesk, String>, JpaSpecificationExecutor<Coindesk> {

    @Query(value = "SELECT cdd.code, cdd.symbol, cdd.rate, cdd.description, cdd.rate_float, cd.ccy_code, cd.name FROM coindesk cd" +
            " JOIN coindesk_detail cdd ON cdd.code = cd.ccy_code" +
            " WHERE cd.ccy_code = :code", nativeQuery = true)
    public List<Coindesk> findByCode(@Param("code") String code);

    @Query(value = "SELECT cdd.code, cdd.symbol, cdd.rate, cdd.description, cdd.rate_float, cd.ccy_code, cd.name FROM coindesk cd" +
            " JOIN coindesk_detail cdd ON cdd.code = cd.ccy_code" , nativeQuery = true)
    public List<Coindesk> findAll();
}
