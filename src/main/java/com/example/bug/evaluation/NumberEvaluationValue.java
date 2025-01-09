package com.example.bug.evaluation;

import java.math.BigDecimal;

public record NumberEvaluationValue(BigDecimal value) implements EvaluationValue {
    @Override
    public EvaluationValueType type() {
        return EvaluationValueType.NUMBER;
    }

    @Override
    public BigDecimal numberValue() {
        return value;
    }
}
