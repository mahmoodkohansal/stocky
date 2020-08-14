package ir.mahmood.sahame.repository;

import ir.mahmood.sahame.entity.StockEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, Integer> {
    Page<StockEntity> findBySymbolContaining(String search, Pageable pageable);
}
