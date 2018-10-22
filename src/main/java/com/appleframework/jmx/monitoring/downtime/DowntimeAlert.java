/**
 * Copyright 2004-2006 jManage.org
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
package com.appleframework.jmx.monitoring.downtime;

import java.util.EventObject;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.appleframework.jmx.core.config.ApplicationConfig;
import com.appleframework.jmx.core.config.event.ApplicationEvent;
import com.appleframework.jmx.database.entity.AppConfigEntity;
import com.appleframework.jmx.database.service.AlertGroupContactService;
import com.appleframework.jmx.database.service.AppConfigService;
import com.appleframework.jmx.event.EventListener;
import com.appleframework.jmx.monitoring.downtime.alert.AlertDelivery;
import com.appleframework.jmx.monitoring.downtime.event.ApplicationDownEvent;
import com.appleframework.jmx.monitoring.downtime.event.ApplicationUpEvent;
import com.appleframework.monitor.cache.AlertCache;
import com.appleframework.monitor.cache.AlertQueue;
import com.appleframework.monitor.model.AlertDeliveryBo;

/**
 * Records application downtime in the database.
 * 
 * TODO: This reads all applications from the DB. This should be changed to just
 * read the current applications - rk
 * 
 * 
 * @author Rakesh Kalra
 */
@Service("downtimeAlert")
public class DowntimeAlert implements EventListener {

	@Resource
	private AlertCache alertCache;
	
	@Resource
	private AlertQueue alertQueue;

	@Resource
	private AppConfigService appConfigService;

	@Resource
	private AlertGroupContactService alertGroupContactService;

	@Resource
	private AlertDelivery alertDelivery;

	public void handleEvent(EventObject event) {

		ApplicationEvent appEvent = (ApplicationEvent) event;

		ApplicationConfig applicationConfig = appEvent.getApplicationConfig();

		AppConfigEntity config = appConfigService.get(Integer.parseInt(applicationConfig.getAppId()));

		if (config.getIsAlert()) {
			// 处理UP和DOWN事件
			if (appEvent instanceof ApplicationUpEvent) {
				// 处理恢复后
				// 发送恢复消息
				// plus.doSend
				alertCache.init(applicationConfig);
				
			} else if (event instanceof ApplicationDownEvent) {
				Long count = alertCache.incrementAndGet(applicationConfig);
				if (count > 1) {
					//alertDelivery.deliver(AlertDeliveryBo bo);
					//count = 0L;
					AlertDeliveryBo bo = new AlertDeliveryBo();
					bo.setAlertGroupId(config.getAlertGroupId());
					bo.setHost(applicationConfig.getHost());
					bo.setName(applicationConfig.getName());
					alertQueue.add(bo);
					alertCache.init(applicationConfig);
				}
				else {
					alertCache.increment(applicationConfig);
				}
			}
		}
	}

}