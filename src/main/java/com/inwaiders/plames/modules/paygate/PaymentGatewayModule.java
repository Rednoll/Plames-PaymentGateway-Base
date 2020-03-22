package com.inwaiders.plames.modules.paygate;

import java.util.List;

import org.jboss.logging.Logger;

import com.inwaiders.plames.domain.module.impl.ModuleBase;
import com.inwaiders.plames.modules.paygate.domain.billing.gateway.PaymentGateway;
import com.inwaiders.plames.modules.paygate.domain.billing.gateway.PaymentGatewayHlRepository;

public class PaymentGatewayModule extends ModuleBase {

	private static PaymentGatewayModule instance = new PaymentGatewayModule();
	
	private PaymentGatewayModule() {
		
	}
	
	@Override
	public void init() {
		
		
	}
	
	@Override
	public void postInit() {
		
		Logger.getLogger(PaymentGatewayModule.class).warn("Default payment gateway not found!");
		
		List<PaymentGatewayHlRepository> reps = PaymentGatewayHlRepository.getRepositories();
		
		if(PaymentGatewayHlRepository.getDefault() == null && !reps.isEmpty()) {
			
			PaymentGatewayHlRepository repository = reps.get(0);
			
			List<PaymentGateway> paygates = repository.getAll();
			
			if(!paygates.isEmpty()) {
				
				PaymentGateway paygate = paygates.get(0);
				
				PaymentGatewayHlRepository.setDefault(paygate);
			
				Logger.getLogger(PaymentGatewayModule.class).info("As default payment gateway was been settted: "+paygate.getName());
			}	
		}
	}

	@Override
	public String getName() {
		
		return "Payment Gateway Module";
	}

	@Override
	public String getVersion() {
		
		return "1V";
	}

	@Override
	public String getDescription() {
		
		return "Позволяет подключать платежные шлюзы к платформе Plames.";
	}

	@Override
	public String getType() {
	
		return "functional";
	}

	@Override
	public String getLicenseKey() {
		
		return null;
	}

	@Override
	public long getSystemVersion() {
		
		return 0;
	}

	@Override
	public long getId() {
	
		return 912835;
	}
	
	public static PaymentGatewayModule getInstance() {
		
		return instance;
	}
}
