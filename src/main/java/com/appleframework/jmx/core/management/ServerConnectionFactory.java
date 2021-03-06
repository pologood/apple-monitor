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
package com.appleframework.jmx.core.management;

import com.appleframework.jmx.core.config.ApplicationConfig;

/**
 * Factory class used for creating ServerConnection instance. Every module
 * must implement this interface.
 *
 * date:  Aug 12, 2004
 * @author	Rakesh Kalra
 */
public interface ServerConnectionFactory {

    /**
     * @return  instance of ServerConnection corresponding to the module.
     */
    public ServerConnection getServerConnection(ApplicationConfig config) throws ConnectionFailedException;
}
