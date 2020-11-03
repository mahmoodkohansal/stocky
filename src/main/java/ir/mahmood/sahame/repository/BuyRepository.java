package ir.mahmood.sahame.repository;

import ir.mahmood.sahame.entity.BuyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyRepository extends JpaRepository<BuyEntity, Long> {

}
