
package com.pgh.kaleidoscope.core.boot.tenant;

import com.pgh.kaleidoscope.core.tool.utils.RandomType;
import com.pgh.kaleidoscope.core.tool.utils.StringUtil;

/**
 * blade租户id生成器
 *
 * @author Chill
 */
public class KaleidoscopeTenantId implements TenantId {
	@Override
	public String generate() {
		return StringUtil.random(6, RandomType.INT);
	}
}
