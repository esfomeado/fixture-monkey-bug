package com.example.bug.evaluation;

import java.util.List;

public record ListEvaluationValue(List<EvaluationValue> value) implements EvaluationValue {
    @Override
    public EvaluationValueType type() {
        return EvaluationValueType.LIST;
    }
}
