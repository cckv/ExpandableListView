package com.example.test_expandablelistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //View
    private ExpandableListView expandableListView;

    //Model：定义的数据
    private String[] groups = {"A组", "B组", "C组"};

    private ArrayList<ItemModel> itemModelArrayList1;
    private ArrayList<ItemModel> itemModelArrayList2;
    private ArrayList<ItemModel> itemModelArrayList3;
//    private String[][] childs = {{"好友A1", "好友A2", "好友A3", "好友A4"}, {"好友B1", "好友B2", "好友B3", "好友B4"}, {"好友C1", "好友C2", "好友C3", "好友C4"}};
    private ArrayList<ArrayList<ItemModel>> childs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        childs = new ArrayList<>();

        itemModelArrayList1 = new ArrayList<>();
        itemModelArrayList1.add(new ItemModel(R.mipmap.iv_lol_icon1, "提莫"));
        itemModelArrayList1.add(new ItemModel(R.mipmap.iv_lol_icon2, "若克"));
        itemModelArrayList1.add(new ItemModel(R.mipmap.iv_lol_icon3, "剑圣"));
        itemModelArrayList1.add(new ItemModel(R.mipmap.iv_lol_icon4, "德莱文"));
        childs.add(itemModelArrayList1);

        itemModelArrayList2 = new ArrayList<>();
        itemModelArrayList2.add(new ItemModel(R.mipmap.iv_lol_icon5, "赵信"));
        itemModelArrayList2.add(new ItemModel(R.mipmap.iv_lol_icon6, "奥拉夫"));
        itemModelArrayList2.add(new ItemModel(R.mipmap.iv_lol_icon7, "安妮"));
        itemModelArrayList2.add(new ItemModel(R.mipmap.iv_lol_icon8, "天使"));
        childs.add(itemModelArrayList2);

        itemModelArrayList3 = new ArrayList<>();
        itemModelArrayList3.add(new ItemModel(R.mipmap.iv_lol_icon9, "泽拉斯"));
        itemModelArrayList3.add(new ItemModel(R.mipmap.iv_lol_icon10, "龙女"));
        itemModelArrayList3.add(new ItemModel(R.mipmap.iv_lol_icon11, "狐狸"));
        itemModelArrayList3.add(new ItemModel(R.mipmap.iv_lol_icon12, "狗熊"));
        childs.add(itemModelArrayList3);

        expandableListView = (ExpandableListView) findViewById(R.id.exlistview);

        expandableListView.setAdapter(new MyExpandableListView());// 设置适配器

        for (int i = 0;i<groups.length;i++){
            expandableListView.expandGroup(i);// 设置展开
        }

        expandableListView.setGroupIndicator(null);// 设置组头箭头为空
    }


    //为ExpandableListView自定义适配器
    class MyExpandableListView extends BaseExpandableListAdapter {

        //返回一级列表的个数
        @Override
        public int getGroupCount() {
            return groups.length;
        }

        //返回每个二级列表的个数
        @Override
        public int getChildrenCount(int groupPosition) { //参数groupPosition表示第几个一级列表
            ArrayList list = childs.get(groupPosition);
            return list.size();
//            return childs[groupPosition].length;
        }

        //返回一级列表的单个item（返回的是对象）
        @Override
        public Object getGroup(int groupPosition) {
            return groups[groupPosition];
        }

        //返回二级列表中的单个item（返回的是对象）
        @Override
        public Object getChild(int groupPosition, int childPosition) {
//            return childs[groupPosition][childPosition];  //不要误写成groups[groupPosition][childPosition]
            ArrayList list = childs.get(groupPosition);
            return list.get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        //每个item的id是否是固定？一般为true
        @Override
        public boolean hasStableIds() {
            return true;
        }

        //【重要】填充一级列表
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.group_item, null);
            } else {

            }
            TextView tv_group = (TextView) convertView.findViewById(R.id.tv_group_name);
            tv_group.setText(groups[groupPosition]);
            return convertView;
        }

        //【重要】填充二级列表
        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.list_item, null);
            }

            ImageView iv_child = (ImageView) convertView.findViewById(R.id.img_icon);
            TextView tv_child = (TextView) convertView.findViewById(R.id.tv_name);

            ArrayList list = childs.get(groupPosition);
            ItemModel model = (ItemModel)list.get(childPosition);
            iv_child.setImageResource(model.getiId());
            tv_child.setText(model.getiName());

//            tv_child.setText(childs[groupPosition][childPosition]);

            return convertView;
        }

        //二级列表中的item是否能够被选中？
        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }
}
