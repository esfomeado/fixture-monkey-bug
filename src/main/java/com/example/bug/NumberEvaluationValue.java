package com.example.bug;

import java.math.BigDecimal;

public record NumberEvaluationValue(BigDecimal value) implements EvaluationValue {
}
