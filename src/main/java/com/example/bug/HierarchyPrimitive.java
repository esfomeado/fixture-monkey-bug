package com.example.bug;

import java.util.List;
import java.util.UUID;

public record HierarchyPrimitive(UUID hierarchyId, List<UUID> value) implements ListPrimitive {
    public HierarchyPrimitive() {
        this(null, List.of());
    }
}
