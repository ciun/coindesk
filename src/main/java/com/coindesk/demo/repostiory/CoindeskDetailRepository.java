package com.coindesk.demo.repostiory;

import com.coindesk.demo.dto.CoindeskDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CoindeskDetailRepository extends JpaRepository<CoindeskDetail,Long> , JpaSpecificationExecutor<CoindeskDetail> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM coindesk_detail WHERE code =:code", nativeQuery = true)
    public void deleteByCode(@Param("code") String code);
}
