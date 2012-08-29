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
package org.apache.karaf.webconsole.osgi.core.pkg.list;

import org.apache.wicket.model.LoadableDetachableModel;
import org.osgi.framework.Version;
import org.osgi.service.packageadmin.ExportedPackage;
import org.osgi.service.packageadmin.PackageAdmin;

public class ExportPackageModel extends LoadableDetachableModel<ExportedPackage> {

    private static final long serialVersionUID = 1L;

    private final PackageAdmin admin;
    private String name;
    private String version;

    public ExportPackageModel(PackageAdmin admin, ExportedPackage object) {
        super(object);
        this.admin = admin;
        name = object.getName();
        version = object.getVersion().toString();
    }

    @Override
    protected ExportedPackage load() {
        ExportedPackage[] packages = admin.getExportedPackages(name);
        if (packages != null) {
            Version vers = new Version(version);
            for (ExportedPackage pkg : packages) {
                if (vers.equals(pkg.getVersion())) {
                    return pkg;
                }
            }
        }
        return null;
    }

}
