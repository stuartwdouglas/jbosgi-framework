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

import java.util.HashSet;
import java.util.Set;

import org.jboss.msc.service.Service;
import org.jboss.msc.service.StartContext;
import org.jboss.msc.service.StartException;
import org.jboss.msc.service.StopContext;

/**
 * Special service wrapper implementation that is used with {@link EagerServiceListener},
 * this services wrapper invokes the listener methods after the delegates start method has
 * been invoked.
 * 
 * @see EagerServiceListener
 *
 * @author Stuart Douglas
 */
public class EagerListenerService<T> implements Service<T> {

    private final Service<T> delegate;

    public static <T> Service<T> wrap(final Service<T> service) {
        return new EagerListenerService<T>(service);
    }

    private EagerListenerService(final Service<T> delegate) {
        this.delegate = delegate;
    }

    private Set<EagerServiceListener> listeners = new HashSet<EagerServiceListener>();

    public synchronized void addListener(final EagerServiceListener listener) {
        listeners.add(listener);
    }

    public synchronized void removeListener(final EagerServiceListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void start(final StartContext context) throws StartException {
        delegate.start(context);
        for (EagerServiceListener listener : listeners) {
            listener.starting(context.getController());
        }
    }

    @Override
    public void stop(final StopContext context) {
        delegate.stop(context);
    }

    @Override
    public T getValue() throws IllegalStateException, IllegalArgumentException {
        return delegate.getValue();
    }
}
