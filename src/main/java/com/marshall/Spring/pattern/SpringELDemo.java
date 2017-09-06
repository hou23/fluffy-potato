package com.marshall.Spring.pattern;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * Created by yaojie.hou on 2017/8/29.
 *
 * SpEL是一种由Spring的 {@link org.springframework.expression.ExpressionParser}
 * 实现分析和执行的语言。这些实现使用作为字符串给出的Spel表达式，并将它们转换为
 * {@link org.springframework.expression.Expression}的实例。上下文组件由
 * {@link org.springframework.expression.EvaluationContext}实现表示，
 * 例如：{@link StandardEvaluationContext}。
 */
public class SpringELDemo {

	public static void main(String[] args) {
		Writer writer = new Writer();
		writer.setName("Writer's name");
		StandardEvaluationContext modifierContext = new StandardEvaluationContext();
		modifierContext.setVariable("name", "Overriden writer's name");
		ExpressionParser parser = new SpelExpressionParser();
		parser.parseExpression("name=#name").getValue(modifierContext);
		System.out.println("writer's name is: " + writer.getName());
	}
}
