package com.kesav.Url_Shortener.service;

import com.kesav.Url_Shortener.model.UrlMapping;
import com.kesav.Url_Shortener.repository.UrlRepository;
import com.kesav.Url_Shortener.util.Base62Encoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    // Shorten URL
    @Transactional
    public String shortenUrl(String longUrl) {
        if (longUrl == null || longUrl.isBlank()) {
            throw new IllegalArgumentException("URL cannot be empty");
        }

        // Optional: prevent localhost URLs
        if (longUrl.startsWith("http://localhost") || longUrl.startsWith("https://localhost")) {
            throw new IllegalArgumentException("Invalid URL — please enter a real website URL");
        }

        UrlMapping url = new UrlMapping();
        url.setLongUrl(longUrl); // ✅ You forgot this line
        urlRepository.save(url); // ✅ Save first so ID is generated

        String shortCode = Base62Encoder.encode(url.getId());
        url.setShortCode(shortCode);

        urlRepository.save(url); // ✅ Save again with short code
        return shortCode;
    }


    // Get original URL for redirect
    @Transactional(readOnly = true)
    public String getOriginalUrl(String shortCode) {
        return urlRepository.findByShortCode(shortCode)
                .map(UrlMapping::getLongUrl)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "URL not found"));
    }

    // Get URL and track click
    @Transactional
    public UrlMapping getAndTrack(String shortCode) {
        UrlMapping mapping = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "URL not found"));

        mapping.setClickCount(mapping.getClickCount() + 1);
        mapping.setLastAccessed(LocalDateTime.now());
        urlRepository.save(mapping);

        return mapping;
    }
}
