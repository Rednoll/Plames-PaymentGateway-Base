package com.inwaiders.plames.modules.paygate.dao.billing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inwaiders.plames.modules.paygate.domain.billing.BillBase;

@Repository
public interface BillRepository extends JpaRepository<BillBase, Long>{

}
