package space.devport.wertik.actionbox.condition;

import com.google.common.base.Strings;
import org.jetbrains.annotations.Nullable;
import space.devport.wertik.actionbox.condition.context.ContextWrapper;
import space.devport.wertik.actionbox.condition.operator.OperatorWrapper;
import space.devport.wertik.actionbox.condition.operator.impl.ConditionOperator;

public class Condition {

    private final Rule leftRule;
    private final Rule rightRule;

    private final ConditionOperator operator;
    private String sign;

    public Condition(@Nullable Rule leftRule, @Nullable Rule rightRule, @Nullable ConditionOperator operator, String... sign) {
        this.leftRule = leftRule;
        this.rightRule = rightRule;

        this.operator = operator == null ? (rule, rule2, contextWrapper) -> {
            if (rule == null && rule2 != null)
                return rule2.process(contextWrapper);
            else if (rule2 == null && rule != null)
                return rule.process(contextWrapper);
            return false;
        } : operator;

        this.sign = sign.length > 0 ? sign[0] : " ";
    }

    public boolean process(ContextWrapper wrapper) {

        if (rightRule == null) {
            return leftRule.process(wrapper);
        }

        return operator.process(leftRule, rightRule, wrapper);
    }

    public static Condition fromString(String str) {
        // ex.: RULE1&&RULE2

        if (Strings.isNullOrEmpty(str))
            return new Condition(null, null, null);

        OperatorWrapper<ConditionOperator, Rule, Rule> wrapper = ConditionOperator.fromString(str);

        if (wrapper == null) {
            Rule rule = Rule.fromString(str);
            return new Condition(rule, null, null);
        }

        String[] arr = str.split(wrapper.getSign());

        Rule leftRule = Rule.fromString(arr[0]);
        Rule rightRule = arr.length > 1 ? Rule.fromString(arr[1]) : null;

        return new Condition(leftRule, rightRule, wrapper.getOperator(), wrapper.getSign());
    }

    @Override
    public String toString() {
        return leftRule.toString() + sign + rightRule.toString();
    }
}