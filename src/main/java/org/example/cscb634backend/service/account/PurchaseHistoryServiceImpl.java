package org.example.cscb634backend.service.account;

import org.example.cscb634backend.repository.account.PurchaseHistoryRepository;
import org.springframework.stereotype.Service;

@Service
public class PurchaseHistoryServiceImpl implements PurchaseHistoryService{
	private final PurchaseHistoryRepository purchaseHistoryRepository;
	
	public PurchaseHistoryServiceImpl(PurchaseHistoryRepository purchaseHistoryRepository) {
		this.purchaseHistoryRepository = purchaseHistoryRepository;
	}
}
