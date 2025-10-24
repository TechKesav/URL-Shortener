package com.kesav.Url_Shortener.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class UrlMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 2048)
    private String longUrl;

    @Column(unique = true)
    private String shortCode;

    private long clickCount = 0;

    private LocalDateTime createdAt;
    private LocalDateTime lastAccessed;

    public UrlMapping(String longUrl, String shortCode) {
    }

    public UrlMapping(Long id, String longUrl, String shortCode, long clickCount, LocalDateTime createdAt, LocalDateTime lastAccessed) {
        this.id = id;
        this.longUrl = longUrl;
        this.shortCode = shortCode;
        this.clickCount = clickCount;
        this.createdAt = createdAt;
        this.lastAccessed = lastAccessed;
    }

    public UrlMapping() {

    }

    public Long getId() { return id; }
    public String getLongUrl() { return longUrl; }
    public void setLongUrl(String longUrl) { this.longUrl = longUrl; }
    public String getShortCode() { return shortCode; }
    public void setShortCode(String shortCode) { this.shortCode = shortCode; }
    public long getClickCount() { return clickCount; }
    public void setClickCount(long clickCount) { this.clickCount = clickCount; }

    public LocalDateTime getLastAccessed() { return lastAccessed; }
    public void setLastAccessed(LocalDateTime lastAccessed) { this.lastAccessed = lastAccessed; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setId(Long id) { this.id = id; }
}

