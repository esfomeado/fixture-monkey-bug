package com.example.bug;

import java.util.List;

public record ListEvaluationValue(List<EvaluationValue> value) implements EvaluationValue {
}
