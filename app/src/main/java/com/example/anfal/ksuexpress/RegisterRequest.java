package com.example.anfal.ksuexpress;

/**
 * Created by Anfal on 3/2/2017 AD.
 */

import android.support.v7.app.AlertDialog;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL = "http://ksuexpress.com/register.php";
    private Map<String, String> params;

    public RegisterRequest (String name, String email, String password, String visually_imp, String beacon_alert, String bildings_alert, Response.Listener<String> listener){
        super (Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("password", password);
        params.put("user_name", email);
        params.put("visually_impaired", visually_imp);
        params.put("buildings_alert", bildings_alert);
        params.put("beacon_alert", beacon_alert);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
