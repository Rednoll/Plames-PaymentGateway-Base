package com.inwaiders.plames.modules.paygate.domain.billing;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.inwaiders.plames.api.user.User;
import com.inwaiders.plames.domain.user.impl.UserImpl;
import com.inwaiders.plames.modules.paygate.dao.billing.BillHighLevelRepository;
import com.inwaiders.plames.modules.paygate.dao.billing.BillRepository;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BillBase implements Bill {
	
	protected static transient BillRepository repository;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@OneToOne(targetEntity = UserImpl.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	protected User user;
	
	@Column(name = "description")
	protected String description;
	
	@Column(name = "amount")
	protected int amount = 0;
	
	@Column(name = "payment_time")
	protected long paymentTime = -1;
	
	@Column(name = "deleted")
	protected volatile boolean deleted = false;
	
	@Override
	public void onSuccess() {}

	@Override
	public void onError() {}
	
	@Override
	public boolean isPaid() {
		
		return this.paymentTime >= 0;
	}
	
	@Override
	public void setPaymentTime(long time) {
		
		this.paymentTime = time;
	}
	
	@Override
	public long getPaymentTime() {
		
		return this.paymentTime;
	}

	@Override
	public int getAmount() {
		
		return amount;
	}
	
	@Override
	public void setDescription(String desc) {

		this.description = desc;
	}

	@Override
	public String getDescription() {
		
		return this.description;
	}

	@Override
	public User getUser() {
		
		return this.user;
	}

	@Override
	public Long getId() {
		
		return this.id;
	}
	
	@Override
	public void save() {
		
		if(!deleted) {
			
			repository.save(this);
		}
	}
	
	@Override
	public void delete() {
		
		deleted = true;
	}
	
	public static BillBase getById(long id) {
		
		return repository.getOne(id);
	}
	
	public static void setRepository(BillRepository rep) {
		
		repository = rep;
	}
	
	public static class HighLevelRepository<T extends BillBase> extends BillHighLevelRepository<T> {

		@Override
		public T getById(Long id) {
			
			return (T) BillBase.getById(id);
		}
	}
}