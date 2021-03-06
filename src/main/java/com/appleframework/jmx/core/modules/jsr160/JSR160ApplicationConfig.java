/**
 * Copyright 2004-2005 jManage.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.appleframework.jmx.core.modules.jsr160;

import java.io.Serializable;

import com.appleframework.jmx.core.config.ApplicationConfig;

/**
 *
 * date:  Sep 13, 2004
 * @author	Rakesh Kalra
 */
public class JSR160ApplicationConfig extends ApplicationConfig implements Serializable {
 
	private static final long serialVersionUID = 1L;

	public static String URL_FORMAT = "service:jmx:rmi:///jndi/rmi://{0}:{1}/jmxrmi";
	
	public static String APPLICATION_CONFIG_KEY = "jsr160";

    public String getHost() {
        return super.getHost();
    }

    public Integer getPort() {
        return super.getPort();
    }
}
