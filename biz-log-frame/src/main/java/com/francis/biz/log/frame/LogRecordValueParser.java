package com.francis.biz.log.frame;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @author francis
 * @version 2023-08-21
 */
public class LogRecordValueParser {
    public static final String PREFIX = "{{";
    public static final String SUFFIX = "}}";

    public static String parser(String expressionString) {
        EvaluationContext evaluationContext = new StandardEvaluationContext();
        Map<String, Object> variable = LogRecordContext.getVariable();
        variable.forEach(evaluationContext::setVariable);
        SpelExpressionParser parser = new SpelExpressionParser();
        ParserContext context = new TemplateParserContext(PREFIX, SUFFIX);
        Expression expression = parser.parseExpression(expressionString, context);
        return expression.getValue(evaluationContext, String.class);
    }

}
