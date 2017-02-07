package org.deepamehta.eduzen.identity.migrations;

import de.deepamehta.core.AssociationType;
import de.deepamehta.core.Topic;
import de.deepamehta.core.TopicType;
import de.deepamehta.core.service.Inject;
import de.deepamehta.core.service.Migration;
import de.deepamehta.workspaces.WorkspacesService;
import java.util.logging.Logger;
import static org.deepamehta.eduzen.identity.EduzenIdentityPlugin.DEFAULT_WORKSPACE;
import static org.deepamehta.eduzen.identity.EduzenIdentityPlugin.DISPLAY_NAME_TYPE_URI;
import static org.deepamehta.eduzen.identity.EduzenIdentityPlugin.INFO_TYPE_URI;
import static org.deepamehta.eduzen.identity.EduzenIdentityPlugin.PROFILE_PICTURE_EDGE_TYPE_URI;

public class Migration4 extends Migration {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Inject private WorkspacesService workspaces;

    @Override
    public void run() {
        TopicType displayName = dm4.getTopicType(DISPLAY_NAME_TYPE_URI);
        assignToDeepaMehtaWorkspace(displayName);
        TopicType profileInfo = dm4.getTopicType(INFO_TYPE_URI);
        assignToDeepaMehtaWorkspace(profileInfo);
        AssociationType assocationType = dm4.getAssociationType(PROFILE_PICTURE_EDGE_TYPE_URI);
        assignToDeepaMehtaWorkspace(assocationType);
    }

    public void assignToDeepaMehtaWorkspace(Topic topic) {
        if (topic == null) return;
        Topic standardWorkspace = dm4.getTopicByUri(DEFAULT_WORKSPACE);
        workspaces.assignToWorkspace(topic, standardWorkspace.getId());
    }

}