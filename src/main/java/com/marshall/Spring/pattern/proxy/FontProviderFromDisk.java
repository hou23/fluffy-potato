package com.marshall.Spring.pattern.proxy;

import java.awt.*;

/**
 * Created by yaojie.hou on 2017/9/11.
 */
public class FontProviderFromDisk implements FontProvider {
	@Override
	public Font getFont(String name) {
		return new Font(name, 0, 12);
	}
}
