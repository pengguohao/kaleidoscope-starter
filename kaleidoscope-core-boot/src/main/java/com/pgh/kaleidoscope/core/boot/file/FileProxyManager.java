package com.pgh.kaleidoscope.core.boot.file;

import java.io.File;

/**
 * 文件管理类
 *
 * @author Chill
 */
public class FileProxyManager {
	private IFileProxy defaultFileProxyFactory = new KaleidoscopeFileProxyFactory();

	private static FileProxyManager me = new FileProxyManager();

	public static FileProxyManager me() {
		return me;
	}

	public IFileProxy getDefaultFileProxyFactory() {
		return defaultFileProxyFactory;
	}

	public void setDefaultFileProxyFactory(IFileProxy defaultFileProxyFactory) {
		this.defaultFileProxyFactory = defaultFileProxyFactory;
	}

	public String[] path(File file, String dir) {
		return defaultFileProxyFactory.path(file, dir);
	}

	public File rename(File file, String path) {
		return defaultFileProxyFactory.rename(file, path);
	}

}
