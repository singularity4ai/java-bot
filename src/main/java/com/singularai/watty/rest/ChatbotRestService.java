package com.singularai.watty.rest;

import com.singularai.watty.utils.RestUtilities;
import com.singularai.watty.utils.Utilities;
import com.singularai.watty.utils.WattyException;
import org.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

/**
 * Created by vamsi on 13/03/17.
 */
@Path("/chats")
@Produces(MediaType.APPLICATION_JSON)
public class ChatbotRestService {

    private static final Logger log = Logger.getLogger(ChatbotRestService.class.getName());
    Utilities utilities = new Utilities();



    @Context
    private ContainerRequestContext requestContext;

    @POST
    @Path("/hello")
    public String sayHello(String data) {
        return RestUtilities.buildSuccessResponse(data,"data");
    }

    @POST
    @Path("/message")
    public String sendMessage(String data) {
        JSONObject params = new JSONObject(data);
        try {
            JSONObject object;
            object = utilities.sendMessage(params.getString("dialog_id"),params.getString("message"),params.getJSONObject("tones"));
            object.put("tones",utilities.analyzeTone(params.getString("message")));
            return RestUtilities.buildSuccessResponse(object,"data");
        } catch (WattyException e) {
            e.printStackTrace();
            return RestUtilities.buildFailureResponse(e.getMessage(),"data");
        }
    }

    @POST
    @Path("/messages")
    public String readAllMessages(String data) {
        return RestUtilities.buildSuccessResponse(data,"data");
    }

    @POST
    @Path("/initialize")
    public String initialize()  {
        String id = null;
        try {
            id = utilities.initChatVA();
        } catch (WattyException e) {
            e.printStackTrace();
        }
        return id;
    }
}
