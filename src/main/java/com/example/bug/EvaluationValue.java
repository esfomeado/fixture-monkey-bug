package com.example.bug;

import java.math.BigDecimal;
import java.util.UUID;

public interface EvaluationValue {
    Object value();

    static EvaluationValue ofNumber(BigDecimal value) {
        return new NumberEvaluationValue(value);
    }

    static EvaluationValue ofString(String value) {
        return new StringEvaluationValue(value);
    }

    static EvaluationValue ofQuestion(BigDecimal value) {
        return new QuestionScoresEvaluationValue(UUID.randomUUID(), value);
    }
}
