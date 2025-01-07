package com.example.bug;

import java.util.List;
import java.util.UUID;

public record WorkflowRecord(UUID id, String name, List<Object> list) {
}
