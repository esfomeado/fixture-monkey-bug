package com.example.bug.evaluation;

import java.math.BigDecimal;

public record NullEvaluationValue() implements EvaluationValue {
    @Override
    public Object value() {
        return null;
    }

    @Override
    public EvaluationValueType type() {
        return EvaluationValueType.NULL;
    }

    @Override
    public BigDecimal numberValue() {
        return null;
    }
}
