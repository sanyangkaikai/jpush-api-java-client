package cn.jpush.api.push.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.Map;

public class Callback implements PushModel {

    public static final String PARAMS = "params";

    private final Map<String, String> params;

    public Callback(Map<String, String> params) {
        this.params = params;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    public JsonElement toJSON() {
        JsonObject json = new JsonObject();
        JsonObject paramsObject = new JsonObject();
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                String key = entry.getKey();
                if (params.get(key) != null) {
                    paramsObject.add(key, new JsonPrimitive(entry.getValue()));
                } else {
                    paramsObject.add(key, JsonNull.INSTANCE);
                }
            }
            json.add(PARAMS, paramsObject);
        }

        return json;
    }

    public static class Builder {
        private Map<String, String> params;

        public Builder setParams(Map<String, String> params) {
            this.params = params;
            return this;
        }

        public Callback build() {
            return new Callback(params);
        }
    }

}
