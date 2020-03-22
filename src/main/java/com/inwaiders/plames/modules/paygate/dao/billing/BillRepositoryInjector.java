package com.inwaiders.plames.modules.paygate.dao.billing;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inwaiders.plames.modules.paygate.domain.billing.BillBase;

@Service
public class BillRepositoryInjector {

	@Autowired
	private BillRepository repository;

	@PostConstruct
	public void inject() {
		
		BillBase.setRepository(repository);
	}
}
