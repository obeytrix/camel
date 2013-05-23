/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.component.spring.batch.support;

import org.apache.camel.ConsumerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;

public class CamelItemReader<I> implements ItemReader<I> {

    private static final transient Logger LOG = LoggerFactory.getLogger(CamelItemReader.class);

    private final ConsumerTemplate consumerTemplate;

    private final String endpointUri;

    public CamelItemReader(ConsumerTemplate consumerTemplate, String endpointUri) {
        this.consumerTemplate = consumerTemplate;
        this.endpointUri = endpointUri;
    }

    @Override
    @SuppressWarnings("unchecked")
    public I read() throws Exception {
        LOG.debug("reading new item...");
        I item = (I) consumerTemplate.receiveBody(endpointUri);
        LOG.debug("read item [{}]", item);
        return item;
    }

}
