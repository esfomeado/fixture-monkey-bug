package com.example.bug.evaluation;

public record StringEvaluationValue(String value) implements EvaluationValue {
    @Override
    public EvaluationValueType type() {
        return EvaluationValueType.STRING;
    }
}
