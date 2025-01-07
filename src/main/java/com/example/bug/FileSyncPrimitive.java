package com.example.bug;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record FileSyncPrimitive(List<FileSyncDocument> value, UUID connectorId, LocalDate startDate,
                                LocalDate endDate) implements ListPrimitive {
}
