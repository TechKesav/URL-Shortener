package com.kesav.Url_Shortener.controller;

import com.kesav.Url_Shortener.model.UrlMapping;
import com.kesav.Url_Shortener.service.UrlService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URI;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/api/url")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/")
    public String index() {
        return "index"; // Thymeleaf template
    }

    @Operation(summary = "Shorten a long URL")
    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestParam String longUrl) {
        // Validate URL
        if (!longUrl.matches("https?://[\\w.-]+(?:\\.[\\w\\.-]+)+[/#?]?.*$") ||
                longUrl.startsWith("http://localhost") || longUrl.startsWith("https://localhost")) {
            throw new IllegalArgumentException("Invalid URL — please enter a real website URL");
        }

        String shortCode = urlService.shortenUrl(longUrl);
        String shortUrl = "http://localhost:8080/api/url/" + shortCode;
        return ResponseEntity.ok(shortUrl);
    }

    @Operation(summary = "Redirect to the original URL and track clicks")
    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {
        // Use service to get original URL and update click count
        UrlMapping mapping = urlService.getAndTrack(shortCode);

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(mapping.getLongUrl()))
                .build();
    }
}
