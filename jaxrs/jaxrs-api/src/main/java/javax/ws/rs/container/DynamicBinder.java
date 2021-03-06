/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2011-2012 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * http://glassfish.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
package javax.ws.rs.container;

import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.WriterInterceptor;

/**
 * A dynamic ({@link PostMatching post-matching}) filter or interceptor binding
 * provider.
 *
 * Dynamic binding provider is used by JAX-RS runtime to provide a the filter or
 * interceptor that shall be applied to a particular resource class and method and
 * overrides any annotation-based binding definitions defined on the returned
 * resource filter or interceptor instance.
 * <p />
 * Providers implementing this interface MUST be annotated with
 * {@link javax.ws.rs.ext.Provider &#64;Provider} annotation to be discovered
 * by JAX-RS runtime. This type of providers is supported only as part of the
 * Server API.
 *
 * @param <T> Filter or interceptor type provided by the dynamic binder.
 *
 * @author Santiago Pericas-Geertsen
 * @author Bill Burke
 * @author Marek Potociar
 *
 * @since 2.0
 * @see javax.ws.rs.NameBinding
 */
public interface DynamicBinder<T> {

    /**
     * Get the filter or interceptor instance that should be bound to the particular
     * resource method.
     *
     * The returned instance is expected to return an instance implementing one
     * or more of the following interfaces:
     * <ul>
     *     <li>{@link ContainerRequestFilter}</li>
     *     <li>{@link ContainerResponseFilter}</li>
     *     <li>{@link ReaderInterceptor}</li>
     *     <li>{@link WriterInterceptor}</li>
     * </ul>
     * A returned instance that does not implement any of the interfaces above
     * is ignored and a {@link java.util.logging.Level#WARNING warning} message
     * is logged.
     * <p />
     * The method is called during a (sub)resource method discovery phase (typically
     * once per each discovered (sub)resource method) to return a filter instance
     * that should be bound to a particular (sub)resource method identified by the
     * supplied {@link ResourceInfo resource information}.
     *
     * @param resourceInfo resource class and method information.
     * @return a filter or interceptor instance that should be dynamically bound
     *     to the (sub)resource method or {@code null} otherwise.
     */
    public T getBoundInstance(ResourceInfo resourceInfo);
}
