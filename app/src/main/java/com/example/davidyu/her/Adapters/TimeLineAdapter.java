package com.example.davidyu.her.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.davidyu.her.Authenticator.CustomRequest;
import com.example.davidyu.her.Model.Tip;
import com.example.davidyu.her.R;
import com.example.davidyu.her.Singleton;
import com.example.davidyu.her.models.ChildEntity;
import com.example.davidyu.her.models.GroupEntity;

import com.daimajia.swipe.SwipeLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by caochao on 17/11/15.
 */
public class TimeLineAdapter extends BaseExpandableListAdapter {
    private LayoutInflater inflater = null;
    private List<GroupEntity> groupList;
    private Context context;
    /**
     * 构造方法
     *
     * @param context
     * @param group_list
     */
    public TimeLineAdapter(Context context,
                           List<GroupEntity> group_list) {
        this.context=context;
        this.groupList = group_list;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setGroupList(List<GroupEntity> input){
        this.groupList = input;
    }

    /**
     * 返回一级Item总数
     */
    @Override
    public int getGroupCount() {
        // TODO Auto-generated method stub
        return groupList.size();
    }

    /**
     * 返回二级Item总数
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        if (groupList.get(groupPosition).getChildEntities() == null) {
            return 0;
        } else {
            return groupList.get(groupPosition).getChildEntities().size();
        }
    }

    /**
     * 获取一级Item内容
     */
    @Override
    public Object getGroup(int groupPosition) {
        // TODO Auto-generated method stub
        return groupList.get(groupPosition);
    }

    /**
     * 获取二级Item内容
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groupList.get(groupPosition).getChildEntities().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        // TODO Auto-generated method stub
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        GroupViewHolder holder = new GroupViewHolder();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.group_status_item, null);
        }
        holder.groupName = (TextView) convertView
                .findViewById(R.id.one_status_name);

        holder.groupName.setText(groupList.get(groupPosition).getGroupName());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final int groupPos = groupPosition;
        final int childPos = childPosition;
        ChildViewHolder viewHolder = null;
        final ChildEntity entity = (ChildEntity) getChild(groupPosition,
                childPosition);
        if (convertView != null) {
            viewHolder = (ChildViewHolder) convertView.getTag();
        } else {
            viewHolder = new ChildViewHolder();
            convertView = inflater.inflate(R.layout.child_status_item, null);
            viewHolder.childTitle = (TextView) convertView
                    .findViewById(R.id.tv_title);
            viewHolder.swipeLayout = (SwipeLayout) convertView.findViewById(R.id.sample);
            viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
            viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, viewHolder.swipeLayout.findViewWithTag("Bottom2"));
            viewHolder.iv_trash= (ImageView) convertView.findViewById(R.id.trash);
            convertView.setTag(viewHolder);
        }
        if (entity!=null) {
            viewHolder.childTitle.setText(entity.getChildTitle());

            viewHolder.iv_trash.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toast("Delete!");
                    LayoutInflater li = LayoutInflater.from(context);
                    View promptsView = li.inflate(R.layout.delete_confirm, null);

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            context);

                    // set prompts.xml to alertdialog builder
                    alertDialogBuilder.setView(promptsView);


                    // set dialog message
                    alertDialogBuilder
                            .setCancelable(false)
                            .setPositiveButton("OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            //delete child
                                            DeleteChild(groupPos, childPos);

                                            deleteTimeLine(entity.getChildTitle());
                                        }
                                    })
                            .setNegativeButton("Cancel",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });

                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();
                    //do something

                }
            });
            convertView.findViewById(R.id.item_surface).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toast(entity.getChildTitle());
                    //do something
                }
            });
        }
        return convertView;
    }

    private void deleteTimeLine(String message){
        RequestQueue queue = Volley.newRequestQueue(context);

        //parameters to be passed into volley POST request

        Map<String,String> params = new HashMap<>();
        params.put("message", message);
        /*params.put("username", username);
        params.put("password", password);*/

        CustomRequest jsonObjectRequest = new CustomRequest(Request.Method.POST,
                "http://i.cs.hku.hk/~sclee/android/" + "deleteTimeLine.php",
                params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


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

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return false;
    }

    private class GroupViewHolder {
        TextView groupName;
    }
    private class ChildViewHolder {
        public SwipeLayout swipeLayout;
        public ImageView iv_star,iv_trash;
        public TextView childTitle;
    }
    private void toast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }
    private void DeleteChild(int groupPosition, int childPosition)
    {
        Log.d("delete", "g " + groupPosition + " c " + childPosition);
        groupList.get(groupPosition).removeChild(childPosition);
        if(groupList.get(groupPosition).getChildEntities().size() == 0)
        {
            groupList.remove(groupPosition);
        }
        this.notifyDataSetChanged();
    }
    public void AddGroup(String name)
    {
        groupList.add(new GroupEntity(name));
    }
    public GroupEntity getGroupEntity(int groupPosition)
    {
        return groupList.get(groupPosition);
    }
}
