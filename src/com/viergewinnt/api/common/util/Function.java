package com.viergewinnt.api.common.util;
/**
 * Das Interface Functionn
 */

import java.io.IOException;

public interface Function <P,Q,R> {

	public void execute(P param, Q param2, R param3) throws IOException;
		
}
