package org.deepamehta.eduzen.identity.migrations;

import de.deepamehta.core.TopicType;
import de.deepamehta.core.service.Migration;
import java.util.logging.Logger;
import static org.deepamehta.eduzen.identity.EduzenIdentityPlugin.DISPLAY_NAME_TYPE_URI;
import static org.deepamehta.eduzen.identity.EduzenIdentityPlugin.INFO_TYPE_URI;
import static org.deepamehta.eduzen.identity.EduzenIdentityPlugin.USER_ACCOUNT_TYPE_URI;

public class Migration3 extends Migration {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public void run() {

        TopicType account = dm4.getTopicType(USER_ACCOUNT_TYPE_URI);
        logger.info("Running Identity Migration3 => Enriching \"User Account\"-Type about \"Info\" and "
                + "\"Display Name\"-Type");
        account.addAssocDef(mf.newAssociationDefinitionModel("dm4.core.composition_def", USER_ACCOUNT_TYPE_URI,
            DISPLAY_NAME_TYPE_URI, "dm4.core.one", "dm4.core.one"));
        account.addAssocDef(mf.newAssociationDefinitionModel("dm4.core.composition_def", USER_ACCOUNT_TYPE_URI,
            INFO_TYPE_URI, "dm4.core.one", "dm4.core.one"));
        
    }

}