package org.example.cscb634backend.repository.account;

import org.example.cscb634backend.entity.account.PurchaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory,Long> {

}
