/*
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
package org.apache.karaf.webconsole.camel.internal.context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.karaf.webconsole.camel.internal.CamelPage;
import org.apache.karaf.webconsole.camel.internal.tracking.TraceContainer;
import org.apache.karaf.webconsole.camel.internal.tracking.Tracer;
import org.apache.karaf.webconsole.core.table.map.MapDataProvider;
import org.apache.karaf.webconsole.core.table.map.MapDataTable;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

/**
 * Page with trace view.
 */
public class DumpPage extends CamelPage {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("serial")
    public DumpPage(TraceContainer container, CamelContext context) {
        Tracer tracer = container.getTracer(context);
        List<Map<String, Serializable>> info;

        if (tracer != null) {
            info = tracer.getInfo();
        } else {
            Session.get().warn("Tracer not found");
            info = new ArrayList<Map<String,Serializable>>();
        }

        add(new ListView<Map<String, Serializable>>("properties", info) {
            @Override
            protected void populateItem(ListItem<Map<String, Serializable>> item) {
                item.add(new MapDataTable<String, Serializable>("propertyMap", new MapDataProvider<String, Serializable>(item.getModelObject()), 20));
            }
        });

    }

}
