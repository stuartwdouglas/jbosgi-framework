/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2012, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.osgi.framework.internal;

import org.jboss.msc.service.AbstractServiceListener;
import org.jboss.msc.service.Service;
import org.jboss.msc.service.ServiceController;

/**
 * A special type of listener class that can be used with {@link EagerListenerService}.
 *
 * This listener is invoked directly from the services {@link Service#start(org.jboss.msc.service.StartContext)}
 * method by a wrapper. This means that it will be invoked before any {@link org.jboss.msc.service.ServiceListener}
 * listeners.
 *
 * This class must be used if you want to add additional services on service start. Because MSC provides no listener
 * ordering guarentees if services are added via a normal listener the services may actually be added after the AS7
 * container state monitor has decided that the container has stabilised, and released the container state monitor.
 *
 *
 * @see AS7-5365
 * @author Stuart Douglas
 */
public abstract class EagerServiceListener<T> extends AbstractServiceListener<T> {

    protected void addEagerListener(final ServiceController<? extends T> controller) {
        Service<? extends T> service = controller.getService();
        ((EagerListenerService)service).addListener(this);
    }

    protected void removeEagerListener(final ServiceController<? extends T> controller) {
        Service<? extends T> service = controller.getService();
        ((EagerListenerService)service).removeListener(this);
    }

    public abstract void starting(final ServiceController<T> serviceController);

}
