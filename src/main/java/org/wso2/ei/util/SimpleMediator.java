package org.wso2.ei.util;

import org.apache.synapse.MessageContext;
import org.apache.synapse.SynapseException;
import org.apache.synapse.SynapseLog;
import org.apache.synapse.mediators.AbstractMediator;

/**
 * A simplified version of AbstractMediator. This can be used to create new mediator classes to run in Class mediator
 * . AbstractExtendedMediator provides an abstract mediate method which provides a SimpleMessageContext object, which
 * can be used to perform data transformation tasks easily
 */
public abstract class SimpleMediator extends AbstractMediator {

    protected SimpleMediator() {

        super();
    }

    @Override
    public final boolean mediate(MessageContext messageContext) {

        try {
            mediate(new SimpleMessageContext(messageContext));
            return true;
        } catch (SynapseException e) {
            getLog(messageContext).error(e);
            throw e;
        } catch (RuntimeException e) {
            getLog(messageContext).error(e);
            return false;
        }
    }

    /**
     * Mediate method with more capabilities than the parent method. No need to return boolean feedback when there is
     * an error. Error tracking is done by an overloaded method.
     * Throw SimpleMessageContextException to stop mediation flow due to an error.
     *
     * @param messageContext SimpleMessageContext instance which provides methods for work with payloads and data
     *                       transformation.
     */
    public abstract void mediate(SimpleMessageContext messageContext);

    /**
     * Get a SynapseLog instance appropriate for the given context.
     *
     * @param simpleMessageContext current SimpleMessageContext
     * @return MediatorLog instance - an implementation of the SynapseLog
     */
    protected SynapseLog getLog(SimpleMessageContext simpleMessageContext) {

        return super.getLog(simpleMessageContext.getMessageContext());
    }

}
