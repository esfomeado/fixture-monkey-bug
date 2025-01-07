package com.example.bug;

public record ExpressionEvaluationValue(Object value) implements EvaluationValue {
    @Override
    public EvaluationValueType type() {
        return EvaluationValueType.EXPRESSION;
    }
}
