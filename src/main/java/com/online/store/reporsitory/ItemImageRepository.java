package com.online.store.reporsitory;

import com.online.store.model.ImageGallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemImageRepository extends JpaRepository<ImageGallery, Long> {
}
