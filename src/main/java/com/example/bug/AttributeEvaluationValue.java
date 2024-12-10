package com.example.bug;

import java.util.UUID;

public record AttributeEvaluationValue(UUID attributeId, EvaluationValue value) implements EvaluationValue {
}
