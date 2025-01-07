package com.example.bug;

import java.util.List;

public interface ListPrimitive extends Primitive {
    @Override
    List<?> value();
}
