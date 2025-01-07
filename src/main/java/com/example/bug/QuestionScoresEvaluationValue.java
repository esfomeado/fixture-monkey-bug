package com.example.bug;

import java.math.BigDecimal;
import java.util.UUID;

public record QuestionScoresEvaluationValue(UUID questionId, BigDecimal value) implements EvaluationValue {
    @Override
    public EvaluationValueType type() {
        return EvaluationValueType.QUESTION_SCORES;
    }

    @Override
    public BigDecimal numberValue() {
        return value;
    }
}
