package com.example.bug;

public record BooleanEvaluationValue(Boolean value) implements EvaluationValue {
    @Override
    public EvaluationValueType type() {
        return EvaluationValueType.BOOLEAN;
    }
}
