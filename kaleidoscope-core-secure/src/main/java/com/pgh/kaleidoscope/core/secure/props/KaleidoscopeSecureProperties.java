
package com.pgh.kaleidoscope.core.secure.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * secure放行额外配置
 *
 * @author Chill
 */
@Data
@ConfigurationProperties("kaleidoscope.secure")
public class KaleidoscopeSecureProperties {

	private final List<ClientSecure> client = new ArrayList<>();

	private final List<String> skipUrl = new ArrayList<>();

}
