/*
* JBoss, Home of Professional Open Source
* Copyright 2006, JBoss Inc., and individual contributors as indicated
* by the @authors tag. See the copyright.txt in the distribution for a
* full listing of individual contributors.
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
package org.jboss.osgi.framework.metadata.internal;

import org.jboss.logging.Logger;

/**
 * Abstract value creator.
 * Extend this one for safe string usage.
 *
 * @author <a href="mailto:ales.justin@jboss.com">Ales Justin</a>
*/
abstract class AbstractValueCreator<T> implements ValueCreator<T>
{
   protected Logger log = Logger.getLogger(getClass());
   private boolean trim;

   protected AbstractValueCreator()
   {
      this(false);
   }

   protected AbstractValueCreator(boolean trim)
   {
      this.trim = trim;
   }

   public T createValue(String attribute)
   {
      if (attribute == null)
         return null;
      if (trim)
         attribute = attribute.trim();
      return useString(attribute);
   }

   /**
    * Use this method on non-null trimmed string.
    * @param attibute non-null trimmed string
    * @return expected value
    */
   protected abstract T useString(String attibute);
}
