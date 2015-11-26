package com.example.davidyu.her.Adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.davidyu.her.Activities.MainActivity;
import com.example.davidyu.her.Activities.ProfileActivity;
import com.example.davidyu.her.Authenticator.CustomRequest;
import com.example.davidyu.her.Authenticator.User;
import com.example.davidyu.her.Authenticator.UserLocalStore;
import com.example.davidyu.her.Model.Tip;
import com.example.davidyu.her.Model.UserProfile;
import com.example.davidyu.her.R;
import com.example.davidyu.her.Singleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Johnson on 11/23/15.
 */
public class RelationAdapter extends ArrayAdapter<String> {
    String[] relationsImage;
    TextView relation_button;
    ImageView relation_image;
    Context context;
    RelativeLayout topLayout;

    public RelationAdapter(Context context, String[] relations, String[] relationsImage) {
        super(context, R.layout.adapter_hobby,relations);
        this.relationsImage = relationsImage;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflator = LayoutInflater.from(getContext());
        View customView=inflator.inflate(R.layout.adapter_relation, parent, false);

        String title=getItem(position);
        relation_button = (TextView)customView.findViewById(R.id.relation_button);
        relation_button.setText(title);

        relation_image = (ImageView)customView.findViewById(R.id.relation_image);
        Resources res = context.getResources();

        String mDrawableName = relationsImage[position];
        int resID = res.getIdentifier(mDrawableName, "drawable", context.getPackageName());
        Drawable drawable = res.getDrawable(resID);
        relation_image.setImageDrawable(drawable);

        topLayout = (RelativeLayout) customView.findViewById(R.id.relation_layout);
        topLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialogbox_f4);
                TextView confirm = (TextView) dialog.findViewById(R.id.confirm33);
                TextView b = (TextView) v.findViewById(R.id.relation_button);
                final String r = b.getText().toString();
                // final Intent intent = new Intent(Intent.ACTION_VIEW);
                //  intent.setData(Uri.parse(ONLINE_PAYMENT_URL+userId));

                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO go to next fragment

                        UserProfile u = Singleton.getUserInstance();
                        u.setHerRelation(r);

                        Singleton.setCurrentRelationship(r);

                        //((ProfileActivity) context).goToThirdFragment();

                        //need to change the intent/fragment
                        dialog.dismiss();

                        sendInformationToServer();
                    }
                });
                TextView dismiss = (TextView) dialog.findViewById(R.id.dismiss33);
                dismiss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }


        });

        return customView;
    }

    private void sendInformationToServer(){
        RequestQueue queue = Volley.newRequestQueue(context);

        //parameters to be passed into volley POST request

        UserLocalStore user = new UserLocalStore(context);
        User user1 = user.getLoggedInUser();

        Map<String,String> params = new HashMap<>();

        if (Singleton.getUserInstance().getPersonality()==""){
            params.put("personality", Singleton.getCurrentPersonality());
        }else{
            params.put("personality",Singleton.getUserInstance().getPersonality());
        }

        //params.put("hobbies",)

        if(Singleton.getUserInstance().getRelationship() == ""){
            params.put("relationship",Singleton.getCurrentRelationship());
        }else{
            params.put("relationship",Singleton.getUserInstance().getRelationship());
        }

        params.put("userID", user1.getId());

        StringBuilder stringBuilder = new StringBuilder();

        for(String s: Singleton.getHobbyList()){
            stringBuilder.append(s);
            stringBuilder.append("\t");
        }

        params.put("hobbies", stringBuilder.toString());


        /*params.put("username", username);
        params.put("password", password);*/

        CustomRequest jsonObjectRequest = new CustomRequest(Request.Method.POST,
                "http://i.cs.hku.hk/~sclee/android/" + "setProfile.php",
                params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("volley profile", "success");

                        Log.e("profile:",response.toString());

                        //go to main activity
                        Intent i = new Intent(context, MainActivity.class);
                        context.startActivity(i);

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("volley", "failure");
                        //progressDialog.dismiss();
                    }
                });

        queue.add(jsonObjectRequest);
    }


}
