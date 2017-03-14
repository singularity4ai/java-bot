package com.singularai.watty.utils;

import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneCategory;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneScore;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by vamsi on 13/03/17.
 */
public class Utilities {

    private CloseableHttpClient getDefaultHttpClient() {
        CloseableHttpClient httpclient = HttpClients.createDefault();;
        return httpclient;
    }

    public static String BOT_ID = "267683c2-dd55-42e2-9b89-bdf2d2968f91";
    public String initChatVA() throws WattyException {
        try {
            HttpResponse response = Unirest.post("https://api.ibm.com/virtualagent/run/api/v1/bots/"+BOT_ID+"/dialogs?version=2016-09-16")
                    .header("accept", "application/json")
                    .header("content-type", "application/json")
                    .header("X-IBM-Client-Id","eef75d42-df07-40ce-9828-6e219dc6bfff")
                    .header("X-IBM-Client-Secret","F3kL5sD7lX4yJ4rH6vB0wU8qI7oO4uQ1wQ4kW8mX7dE7pH3gO6")
                    .asString();
            return (String) response.getBody();
        } catch (UnirestException e) {
            throw new WattyException("Error initializing the chatVA",e);
        }
    }

    public JSONObject sendMessage(String dialogId, String message, JSONObject tones) throws WattyException {
        try {
            JSONObject object = new JSONObject();
            object.put("message",message);
            JSONObject context = new JSONObject();
            context.put("tones",tones);
            object.put("context", context);
            HttpResponse response = Unirest.post("https://api.ibm.com/virtualagent/run/api/v1/bots/"+BOT_ID+"/dialogs/"+dialogId+"/messages")
                    .header("accept", "application/json")
                    .header("content-type", "application/json")
                    .header("X-IBM-Client-Id","eef75d42-df07-40ce-9828-6e219dc6bfff")
                    .header("X-IBM-Client-Secret","F3kL5sD7lX4yJ4rH6vB0wU8qI7oO4uQ1wQ4kW8mX7dE7pH3gO6")
                    .body(object)
                    .asString();
            return new JSONObject((String) response.getBody());
        } catch (UnirestException e) {
            throw new WattyException("Error initializing the chatVA",e);
        }
    }

    public JSONObject tradeOffAnalytics() {
        TradeoffAnalytics service = new TradeoffAnalytics();
        service.setUsernameAndPassword("<username>", "<password>");

        Problem problem = new Problem("phone");

        String price = "price";
        String ram = "ram";
        String screen = "screen";

        // Define the objectives
        List<Column> columns = new ArrayList<Column>();
        problem.setColumns(columns);

        columns.add(new NumericColumn().withRange(0, 100).withKey(price).withGoal(Goal.MIN).withObjective(true));
        columns.add(new NumericColumn().withKey(screen).withGoal(Goal.MAX).withObjective(true));
        columns.add(new NumericColumn().withKey(ram).withGoal(Goal.MAX));

        // Define the options to choose
        List<Option> options = new ArrayList<Option>();
        problem.setOptions(options);

        HashMap<String, Object> galaxySpecs = new HashMap<String, Object>();
        galaxySpecs.put(price, 50);
        galaxySpecs.put(ram, 45);
        galaxySpecs.put(screen, 5);
        options.add(new Option("1", "Galaxy S4").withValues(galaxySpecs));

        HashMap<String, Object> iphoneSpecs = new HashMap<String, Object>();
        iphoneSpecs.put(price, 99);
        iphoneSpecs.put(ram, 40);
        iphoneSpecs.put(screen, 4);
        options.add(new Option("2", "iPhone 5").withValues(iphoneSpecs));

        HashMap<String, Object> optimusSpecs = new HashMap<String, Object>();
        optimusSpecs.put(price, 10);
        optimusSpecs.put(ram, 300);
        optimusSpecs.put(screen, 5);
        options.add(new Option("3", "LG Optimus G").withValues(optimusSpecs));

        // Call the service and get the resolution
        Dilemma dilemma = service.dilemmas(problem).execute();
        System.out.println(dilemma);
    }

    public JSONObject analyzeTone(String text) {
        ToneAnalyzer service = new ToneAnalyzer(ToneAnalyzer.VERSION_DATE_2016_05_19);
        service.setUsernameAndPassword("e1814f87-3b52-4f1a-a847-ef78bb891339", "REsXzga3pKz8");


        // Call the service and get the tone
        ToneAnalysis tone = service.getTone(text, null).execute();
        System.out.println(tone);

        //The to string output is JSON so hacking it this way
        return new JSONObject(tone);
    }

    public static void main(String[] args) throws WattyException {
        Utilities utilities = new Utilities();
        //System.out.println(" Chatid  = " + utilities.initChatVA());
        //System.out.println("utilities = " + utilities.sendMessage("baf294c8-94d2-4577-aae5-74a0e53003c5","What is your name?"));
        System.out.println("utilities = " + utilities.analyzeTone("What is your name?"));
    }
}
