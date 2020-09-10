package space.devport.wertik.actionbox.condition.operator.impl;

import space.devport.wertik.actionbox.condition.operator.Operator;
import space.devport.wertik.actionbox.condition.operator.OperatorWrapper;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiPredicate;

public interface RuleOperator extends BiPredicate<Object, Object>, Operator {

    Map<String, RuleOperator> registeredOperators = new HashMap<String, RuleOperator>() {{
        put(">", (o, o2) -> {
            if (o instanceof Number && o2 instanceof Number)
                return ((Number) o).floatValue() > ((Number) o2).floatValue();
            return false;
        });

        put("<", (o, o2) -> {
            if (o instanceof Number && o2 instanceof Number)
                return ((Number) o).floatValue() < ((Number) o2).floatValue();
            return false;
        });

        put("=", Object::equals);

        put("!=", (o, o2) -> !o.equals(o2));
    }};

    static RuleOperator get(String sign) {
        return registeredOperators.get(sign);
    }

    static OperatorWrapper<RuleOperator, Object, Object> fromString(String str) {
        for (Map.Entry<String, RuleOperator> entry : registeredOperators.entrySet()) {
            if (str.contains(entry.getKey()))
                return new OperatorWrapper<>(entry.getKey(), entry.getValue());
        }
        return null;
    }
}