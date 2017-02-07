package org.deepamehta.eduzen.identity;

import de.deepamehta.core.osgi.PluginActivator;
import de.deepamehta.core.service.Inject;
import de.deepamehta.workspaces.WorkspacesService;
import java.util.logging.Logger;



public class EduzenIdentityPlugin extends PluginActivator {

    private Logger log = Logger.getLogger(getClass().getName());

    // DeepaMehta 4 Standard URIS
    public static final String USER_ACCOUNT_TYPE_URI           = "dm4.accesscontrol.user_account";
    public static final String MAILBOX_TYPE_URI                = "dm4.contacts.email_address";
    public static final String DEFAULT_WORKSPACE               = "de.workspaces.deepamehta";

    // Eduzen Identity Plugin URIs
    public static final String DISPLAY_NAME_TYPE_URI           = "org.deepamehta.identity.display_name";
    public static final String INFO_TYPE_URI                   = "org.deepamehta.identity.infos";
    public static final String PROFILE_PICTURE_EDGE_TYPE_URI   = "org.deepamehta.identity.profile_picture_edge";

    @Inject private WorkspacesService workspaces; // used in Migration4

    @Override
    public void init() {
        log.info("Eduzen Identity Plugin Initialized, standardWorkspace="
            + workspaces.getWorkspace(WorkspacesService.DEEPAMEHTA_WORKSPACE_URI));
    }

}
