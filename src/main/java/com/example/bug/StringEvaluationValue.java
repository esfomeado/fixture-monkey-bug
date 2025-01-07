package com.example.bug;

public record StringEvaluationValue(String value) implements EvaluationValue {
    @Override
    public EvaluationValueType type() {
        return EvaluationValueType.STRING;
    }
}
