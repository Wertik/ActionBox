package space.devport.wertik.actionbox.condition;

import com.google.common.base.Strings;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;
import space.devport.wertik.actionbox.condition.context.ContextWrapper;
import space.devport.wertik.actionbox.condition.operator.OperatorWrapper;
import space.devport.wertik.actionbox.condition.operator.impl.RuleOperator;

public class Rule {

    private final String leftSide;
    private final String rightSide;

    @NonNull
    private final RuleOperator operator;
    private String sign;

    public Rule(@Nullable String leftSide, @Nullable String rightSide, @Nullable RuleOperator ruleOperator, String... sign) {
        this.leftSide = leftSide;
        this.rightSide = rightSide;

        this.operator = ruleOperator == null ? (o, o2) -> {
            if (o == null && o2 instanceof Boolean)
                return (Boolean) o2;
            else if (o2 == null && o instanceof Boolean)
                return (Boolean) o;
            return true;
        } : ruleOperator;

        this.sign = sign.length > 0 ? sign[0] : " ";
    }

    public static Rule fromString(String str) {
        // ex.: RULE1>RULE2

        if (Strings.isNullOrEmpty(str))
            return new Rule(null, null, null);

        OperatorWrapper<RuleOperator, Object, Object> wrapper = RuleOperator.fromString(str);

        if (wrapper == null) {
            return new Rule(str, null, null);
        }

        String[] arr = str.split(wrapper.getSign());

        String leftSide = arr[0];
        String rightSide = arr.length > 1 ? arr[1] : null;

        return new Rule(leftSide, rightSide, wrapper.getOperator(), wrapper.getSign());
    }

    public boolean process(ContextWrapper wrapper) {

        String leftString = wrapper.parse(leftSide);
        String rightString = wrapper.parse(rightSide);

        Object left = ParserUtil.parseObject(leftString);
        Object right = ParserUtil.parseObject(rightString);

        return operator.test(left, right);
    }

    @Override
    public String toString() {
        return leftSide + sign + rightSide;
    }
}