package com.lance.sharding.mybatis.config.context;

/**
 * datasource
 *
 * @author lance
 * @date 2022/3/9 22:09
 */
public class DbContextHolder {

	private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();

	public static void set(String source) {
		CONTEXT.set(source);
	}

	public static String get() {
		return CONTEXT.get();
	}

	public static void clear() {
		CONTEXT.remove();
	}
}
