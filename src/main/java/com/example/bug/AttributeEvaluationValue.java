package com.example.bug;

import java.math.BigDecimal;
import java.util.UUID;

public record AttributeEvaluationValue(UUID attributeId, EvaluationValue value) implements EvaluationValue {
    @Override
    public EvaluationValueType type() {
        return EvaluationValueType.ATTRIBUTE;
    }

    @Override
    public BigDecimal numberValue() {
        return value.numberValue();
    }
}
