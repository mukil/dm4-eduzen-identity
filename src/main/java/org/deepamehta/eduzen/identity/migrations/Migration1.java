package org.deepamehta.eduzen.identity.migrations;

import de.deepamehta.core.AssociationDefinition;
import de.deepamehta.core.TopicType;
import de.deepamehta.core.service.Migration;
import java.util.Collection;
import java.util.logging.Logger;
import static org.deepamehta.eduzen.identity.EduzenIdentityPlugin.MAILBOX_TYPE_URI;
import static org.deepamehta.eduzen.identity.EduzenIdentityPlugin.USER_ACCOUNT_TYPE_URI;

public class Migration1 extends Migration {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public void run() {
        TopicType account = dm4.getTopicType(USER_ACCOUNT_TYPE_URI);
        // 1) If not already done, enrich the "User Account"-Type about a "Mailbox"-Type
        Collection<AssociationDefinition> childTypes = account.getAssocDefs();
        boolean hasPersonAsChild = false;
        for (AssociationDefinition child : childTypes) {
            if (child.getChildTypeUri().equals(MAILBOX_TYPE_URI)) hasPersonAsChild = true;
        }
        if (!hasPersonAsChild) {
            logger.info("Running Identity Migration1 => Enriching \"User Account\"-Type about \"Mailbox\"-Type");
            account.addAssocDef(mf.newAssociationDefinitionModel("dm4.core.aggregation_def", USER_ACCOUNT_TYPE_URI,
                MAILBOX_TYPE_URI, "dm4.core.one", "dm4.core.one"));
        } else {
            logger.info("NOT Running Identity Migration1 => Enriching \"User Account\"-Type about \"Mailbox\"-Type - "
                    + "Already done!");
        }

    }

}