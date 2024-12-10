package com.example.bug;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ListValue extends Value {
    public ListValue(List<Value> values) {
        super(values);
    }
}
