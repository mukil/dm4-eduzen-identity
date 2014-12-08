package org.deepamehta.plugins.identity.migrations;

import de.deepamehta.core.Association;
import de.deepamehta.core.AssociationType;
import de.deepamehta.core.Topic;
import de.deepamehta.core.TopicType;
import de.deepamehta.core.model.AssociationModel;
import de.deepamehta.core.model.SimpleValue;
import de.deepamehta.core.model.TopicRoleModel;
import de.deepamehta.core.service.Migration;
import java.util.List;
import java.util.logging.Logger;

public class Migration4 extends Migration {

    private Logger logger = Logger.getLogger(getClass().getName());

    private String DISPLAY_NAME_TYPE_URI = "org.deepamehta.identity.display_name";
    private String INFO_TYPE_URI = "org.deepamehta.identity.infos";
    
    private String PROFILE_PICTURE_EDGE_TYPE_URI = "org.deepamehta.identity.profile_picture_edge";
    
    private String DEFAULT_WORKSPACE = "de.workspaces.deepamehta";

    @Override
    public void run() {

        TopicType displayName = dms.getTopicType(DISPLAY_NAME_TYPE_URI);
        assignToDeepaMehtaWorkspace(displayName);
        TopicType profileInfo = dms.getTopicType(INFO_TYPE_URI);
        assignToDeepaMehtaWorkspace(profileInfo);
        AssociationType assocationType = dms.getAssociationType(PROFILE_PICTURE_EDGE_TYPE_URI);
        assignToDeepaMehtaWorkspace(assocationType);
    }
    
    public void assignToDeepaMehtaWorkspace(Topic topic) {
        if (topic == null) return;
        Topic wikidataWorkspace = dms.getTopic("uri", new SimpleValue(DEFAULT_WORKSPACE));
        if (!associationExists("dm4.core.aggregation", topic, wikidataWorkspace)) {
            dms.createAssociation(new AssociationModel("dm4.core.aggregation",
                new TopicRoleModel(topic.getId(), "dm4.core.parent"),
                new TopicRoleModel(wikidataWorkspace.getId(), "dm4.core.child")
            ));   
        }
    }
    
    private boolean associationExists(String edge_type, Topic item, Topic user) {
        List<Association> results = dms.getAssociations(item.getId(), user.getId(), edge_type);
        return (results.size() > 0) ? true : false;
    }


}