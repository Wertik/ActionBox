package space.devport.wertik.actionbox.condition.operator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OperatorWrapper<S extends Operator, U, V> {

    @Getter
    private final String sign;
    @Getter
    private final S operator;
}