package space.devport.wertik.actionbox.condition.operator.impl;

import space.devport.wertik.actionbox.condition.Rule;
import space.devport.wertik.actionbox.condition.context.ContextWrapper;
import space.devport.wertik.actionbox.condition.operator.Operator;
import space.devport.wertik.actionbox.condition.operator.OperatorWrapper;
import space.devport.wertik.actionbox.condition.operator.TriPredicate;

import java.util.HashMap;
import java.util.Map;

public interface ConditionOperator extends TriPredicate<Rule, Rule, ContextWrapper>, Operator {

    Map<String, ConditionOperator> registeredOperators = new HashMap<String, ConditionOperator>() {{
        put("&&", (rule, rule2, wrapper) -> rule.process(wrapper) && rule2.process(wrapper));
        put("//", (r, r1, context) -> r.process(context) || r1.process(context));
    }};

    static ConditionOperator get(String sign) {
        return registeredOperators.get(sign);
    }

    static OperatorWrapper<ConditionOperator, Rule, Rule> fromString(String str) {
        for (Map.Entry<String, ConditionOperator> entry : registeredOperators.entrySet()) {
            if (str.contains(entry.getKey()))
                return new OperatorWrapper<>(entry.getKey(), entry.getValue());
        }
        return null;
    }
}