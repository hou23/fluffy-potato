package com.marshall.Spring.pattern.proxy;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yaojie.hou on 2017/9/11.
 *
 * 静态代理实现缓存功能
 */
public class CachedFontProvider implements FontProvider {

	private FontProvider fontProvider;
	private static Map<String, Font> cached = new HashMap<>();

	public CachedFontProvider(FontProvider fontProvider) {
		this.fontProvider = fontProvider;
	}

	@Override
	public Font getFont(String name) {
		Font font = cached.get(name);
		if (font == null) {
			font = fontProvider.getFont(name);
			cached.put(name, font);
		}
		return font;
	}
}
