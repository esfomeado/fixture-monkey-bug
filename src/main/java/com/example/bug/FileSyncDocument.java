package com.example.bug;

import java.time.LocalDate;
import java.util.UUID;

public record FileSyncDocument(UUID documentId, UUID ownerId, String name, LocalDate lastUpdated) {
}
