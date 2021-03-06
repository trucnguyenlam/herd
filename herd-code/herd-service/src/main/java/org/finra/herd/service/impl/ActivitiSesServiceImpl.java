/*
 * Copyright 2015 herd contributors
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
package org.finra.herd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.finra.herd.core.helper.ConfigurationHelper;
import org.finra.herd.model.api.xml.EmailSendRequest;
import org.finra.herd.model.dto.ConfigurationValue;
import org.finra.herd.service.SesService;

/**
 * The Activiti SES service implementation
 */
@Service
public class ActivitiSesServiceImpl implements SesService
{
    @Autowired
    private ConfigurationHelper configurationHelper;

    @Autowired
    private SesService sesService;

    /**
     * Sends email based on the information provided in the email send request
     *
     * @param emailSendRequest email send request with the information to send email
     */
    public void sendEmail(EmailSendRequest emailSendRequest)
    {
        // Get the "from" field for activiti send email.
        emailSendRequest.setSource(configurationHelper.getProperty(ConfigurationValue.ACTIVITI_DEFAULT_MAIL_FROM));

        // Call SES service to send email.
        sesService.sendEmail(emailSendRequest);
    }
}
