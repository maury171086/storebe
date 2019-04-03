package com.online.store.reporsitory;

import com.online.store.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Page<Item> findAllByBrandName(Pageable pageable, String brand);

    Page<Item> findAllByCategoryName(Pageable pageable, String category);

    Page<Item> findAllByPrice(Pageable pageable, Double price);

    Page<Item> findAllByNameContainsIgnoreCase(Pageable pageable, String price);

    Page<Item> findAllByCapacityContainsIgnoreCase(Pageable pageable, String price);
}
