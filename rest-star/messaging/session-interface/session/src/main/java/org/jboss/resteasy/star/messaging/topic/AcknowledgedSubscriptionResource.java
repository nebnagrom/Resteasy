package org.jboss.resteasy.star.messaging.topic;

import org.hornetq.api.core.HornetQException;
import org.hornetq.api.core.client.ClientSessionFactory;
import org.jboss.resteasy.star.messaging.queue.AcknowledgedQueueConsumer;
import org.jboss.resteasy.star.messaging.queue.DestinationServiceManager;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public class AcknowledgedSubscriptionResource extends AcknowledgedQueueConsumer implements Subscription
{
   private boolean durable;

   public AcknowledgedSubscriptionResource(ClientSessionFactory factory, String destination, String id, DestinationServiceManager serviceManager)
           throws HornetQException
   {
      super(factory, destination, id, serviceManager);
   }

   public boolean isDurable()
   {
      return durable;
   }

   public void setDurable(boolean durable)
   {
      this.durable = durable;
   }

   @Override
   protected void setAcknowledgeLinks(UriInfo uriInfo, String basePath, Response.ResponseBuilder builder, String index)
   {
      setAcknowledgeNextLink(serviceManager.getLinkStrategy(), builder, uriInfo, basePath, index);
      SubscriptionResource.setSubscriptionLink(serviceManager.getLinkStrategy(), builder, uriInfo, basePath);
   }

   @Override
   protected void setMessageResponseLinks(UriInfo info, String basePath, Response.ResponseBuilder builder, String index)
   {
      setAcknowledgementLink(builder, info, basePath);
      SubscriptionResource.setSubscriptionLink(serviceManager.getLinkStrategy(), builder, info, basePath);
   }

}