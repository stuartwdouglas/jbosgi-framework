/*
 * JBoss, Home of Professional Open Source
 * Copyright 2009, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.osgi.framework;

import static org.jboss.osgi.framework.Constants.JBOSGI_NAME;

import org.jboss.modules.Module;
import org.jboss.modules.ModuleIdentifier;
import org.jboss.modules.ModuleLoadException;
import org.jboss.msc.service.Service;
import org.jboss.msc.service.ServiceName;
import org.jboss.msc.service.ServiceTarget;
import org.jboss.osgi.deployment.deployer.Deployment;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;
import org.osgi.framework.launch.Framework;

/**
 * Integration point for {@link Bundle} management.
 *
 * @author thomas.diesler@jboss.com
 * @since 24-Mar-2011
 */
public interface FrameworkIntegration extends Service<FrameworkIntegration> {

    ServiceName SERVICE_NAME = JBOSGI_NAME.append("frameworkext");

    /**
     * Get the OSGi Framework
     */
    Framework getFramework();

    /**
     * Install a bundle from the given deployment
     */
    ServiceName installBundle(ServiceTarget serviceTarget, Deployment dep) throws BundleException;

    /**
     * Uninstall the given deployment
     */
    void uninstallBundle(Deployment dep);

    /**
     * Install a bundle from the given module
     */
    ServiceName installBundle(ServiceTarget serviceTarget, Module module) throws BundleException;

    /**
     * Uninstall the given module
     */
    void uninstallBundle(Module module);

    /**
     * Install a bundle from the given module identifier
     */
    ServiceName installBundle(ServiceTarget serviceTarget, ModuleIdentifier identifier) throws BundleException, ModuleLoadException;

    /**
     * Uninstall the given module identifier
     */
    void uninstallBundle(ModuleIdentifier identifier);
}