package com.example.bug.evaluation;

public record BooleanEvaluationValue(Boolean value) implements EvaluationValue {
    @Override
    public EvaluationValueType type() {
        return EvaluationValueType.BOOLEAN;
    }
}
