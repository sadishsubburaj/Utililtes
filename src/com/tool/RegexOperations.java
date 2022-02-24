package com.tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexOperations {
	private String match = null;

	public String regexops(String line, String regexp) {
		Pattern p = Pattern.compile(regexp);
		Matcher m = p.matcher(line);
		if (m.find())
			match = m.group(1);
		return match;
	}

}
