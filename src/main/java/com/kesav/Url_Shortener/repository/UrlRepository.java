package com.kesav.Url_Shortener.repository;

import com.kesav.Url_Shortener.model.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<UrlMapping, Long> {
    Optional<UrlMapping> findByShortCode(String shortCode);
    Optional<UrlMapping> findByLongUrl(String longUrl);
}

