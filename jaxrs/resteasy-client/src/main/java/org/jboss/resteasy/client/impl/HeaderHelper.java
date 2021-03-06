package org.jboss.resteasy.client.impl;

import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.jboss.resteasy.spi.StringConverter;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.RuntimeDelegate;
import java.util.Set;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public class HeaderHelper
{
   public static void setAllow(MultivaluedMap headers, String[] methods)
   {
      if (methods == null)
      {
         headers.remove("Allow");
         return;
      }
      StringBuilder builder = new StringBuilder();
      boolean isFirst = true;
      for (String l : methods)
      {
         if (isFirst)
         {
            isFirst = false;
         }
         else
         {
            builder.append(", ");
         }
         builder.append(l);
      }
      headers.putSingle("Allow", builder.toString());
   }

   public static void setAllow(MultivaluedMap headers, Set<String> methods)
   {
      if (methods == null)
      {
         headers.remove("Allow");
         return;
      }
      StringBuilder builder = new StringBuilder();
      boolean isFirst = true;
      for (String l : methods)
      {
         if (isFirst)
         {
            isFirst = false;
         }
         else
         {
            builder.append(", ");
         }
         builder.append(l);
      }
      headers.putSingle("Allow", builder.toString());
   }

   public static String toHeaderString(Object object, ResteasyProviderFactory providerFactory)
   {
      if (object instanceof String) return object.toString();

      StringConverter converter = providerFactory.getStringConverter(object
              .getClass());
      if (converter != null)
         return converter.toString(object);

      RuntimeDelegate.HeaderDelegate delegate = providerFactory
              .createHeaderDelegate(object.getClass());
      if (delegate != null)
         return delegate.toString(object);
      else
         return object.toString();

   }
}
