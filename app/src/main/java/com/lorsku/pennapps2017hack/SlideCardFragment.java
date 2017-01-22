package com.lorsku.pennapps2017hack;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class SlideCardFragment extends Fragment implements LaunchCardListener.ActionDownInterface {

    private static final Object API = "http://findme.meucomercioeletronico.com/";
    public static MyAppAdapter myAppAdapter;
    public static ViewHolder viewHolder;
    private SlideLaunchAdapterView flingContainer;
    public String token;
    View rootView;
    MainActivity activity;
    private TextView recList;
    public  List<Hackathon> recordSet;

    public  SlideCardFragment(MainActivity _activity){
        activity = _activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            token = getArguments().getString("user_id");
        }

        //create new instance on POJOS Hackathon
        recordSet = new ArrayList<>();

        Hackathon hackathon1 =  new Hackathon();
        hackathon1.setDate("January 27-29, 2017");
        hackathon1.setHackathonName("Fashion Hacks");
        hackathon1.setLocation("Ken State University");
        hackathon1.setPhoto("uploads/userprofile/A20CF1B8FFA5f64as5saopds58asAFE4F78C.jpg");
        recordSet.add(hackathon1);

        Hackathon hackathon2 =  new Hackathon();
        hackathon2.setDate("January 28-29, 2017");
        hackathon2.setHackathonName("HackEd");
        hackathon2.setLocation("Startup Edmonton");
        hackathon2.setPhoto("uploads/userprofile/A20CF1B8FFA5f64as5saopds58asAFE4F78C.jpg");
        recordSet.add(hackathon2);

        Hackathon hackathon3 =  new Hackathon();
        hackathon3.setDate("February 11-12, 2017");
        hackathon3.setHackathonName("Hackpoly");
        hackathon3.setLocation("California State Polytechnic University, Pomona Campus");
        hackathon3.setPhoto("uploads/userprofile/A20CF1B8FFA5f64as5saopds58asAFE4F78C.jpg");
        recordSet.add(hackathon3);

        Hackathon hackathon4 =  new Hackathon();
        hackathon4.setDate("February 11-12, 2017");
        hackathon4.setHackathonName("Pearl Hacks");
        hackathon4.setLocation("University of North Carolina, Chapel Hill Campus");
        hackathon4.setPhoto("uploads/userprofile/A20CF1B8FFA5f64as5saopds58asAFE4F78C.jpg");
        recordSet.add(hackathon4);

        Hackathon hackathon5 =  new Hackathon();
        hackathon5.setDate("February 17-19, 2017");
        hackathon5.setHackathonName("HackYSU");
        hackathon5.setLocation("Youngstown State University");
        hackathon5.setPhoto("uploads/userprofile/A20CF1B8FFA5f64as5saopds58asAFE4F78C.jpg");
        recordSet.add(hackathon5);

        Hackathon hackathon6 =  new Hackathon();
        hackathon6.setDate("February 17-19, 2017");
        hackathon6.setHackathonName("HackCentral");
        hackathon6.setLocation("University of Manitoba");
        hackathon6.setPhoto("uploads/userprofile/A20CF1B8FFA5f64as5saopds58asAFE4F78C.jpg");
        recordSet.add(hackathon6);

        Hackathon hackathon7 =  new Hackathon();
        hackathon7.setDate("February 18-19, 2017");
        hackathon7.setHackathonName("HackHERS");
        hackathon7.setLocation("Rutgers University");
        hackathon7.setPhoto("uploads/userprofile/A20CF1B8FFA5f64as5saopds58asAFE4F78C.jpg");
        recordSet.add(hackathon7);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        if(rootView == null ){
            rootView = inflater.inflate(R.layout.notification_frag, container, false);
        }

        activity = (MainActivity) getActivity();

        init();

        return rootView;
    }


    private void init() {


        flingContainer = (SlideLaunchAdapterView) rootView.findViewById(R.id.frame);
        myAppAdapter = new MyAppAdapter(recordSet, activity);
        flingContainer.setAdapter(myAppAdapter);

        flingContainer.setFlingListener(new SlideLaunchAdapterView.onFlingListener() {

            @Override
            public void removeFirstObjectInAdapter() {
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                recordSet.remove(0);
                myAppAdapter.notifyDataSetChanged();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                recordSet.remove(0);
                myAppAdapter.notifyDataSetChanged();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {

            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.background).setAlpha(0);
                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });


        flingContainer.setOnItemClickListener(new SlideLaunchAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.background).setAlpha(0);
                myAppAdapter.notifyDataSetChanged();
            }
        });

    }

    public  void setData(List<Hackathon> _recordSet){
        if(recordSet == null) {
            recordSet = _recordSet;
        }
    }

    @Override
    public void onActionDownPerform() {
        Log.e("action", "bingo");
    }

    public final class ViewHolder {

        public FrameLayout background;
        public LinearLayout card_3;
        public LinearLayout card_2;
        public TextView DataText;
        public TextView estado;
        public TextView cidade;
        public TextView titulo;
        //public CircularImageView friendPhoto;
        public int tipo;
        public Button btnIgnore;
        public Button btnAccept;

        //controle para esconder as sub-cartas
        public void hideBackground(boolean b, int i) {

            int current_card_num = recordSet.size();

            if(b){

                if(current_card_num >= 3){
                    card_2.setAlpha(1);
                    card_2.setVisibility(View.VISIBLE);
                    card_3.setAlpha(1);
                    card_3.setVisibility(View.VISIBLE);
                } else if(current_card_num == 2){
                    card_3.setAlpha(0);
                    card_3.setVisibility(View.GONE);
                    card_2.setAlpha(1);
                    card_2.setVisibility(View.VISIBLE);
                } else {
                    card_3.setAlpha(0);
                    card_3.setVisibility(View.GONE);
                    card_2.setAlpha(0);
                    card_2.setVisibility(View.GONE);
                }

            } else {
                background.setAlpha(1);
                background.setVisibility(View.VISIBLE);
            }
        }
    }

    public class MyAppAdapter extends BaseAdapter {


        public List<Hackathon> hackathonName;
        public Context context;

        private MyAppAdapter(List<Hackathon> apps, Context context) {
            this.hackathonName = apps;
            this.context = context;
        }

        @Override
        public int getCount() {
            return hackathonName.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View rowView = convertView;

            if (rowView == null) {

                LayoutInflater inflater = activity.getLayoutInflater();
                rowView = inflater.inflate(R.layout.item, parent, false);
                // configure view holder
                viewHolder = new ViewHolder();
                viewHolder.DataText = (TextView) rowView.findViewById(R.id.user_name);
               //viewHolder.friendPhoto = (CircularImageView) rowView.findViewById(R.id.friendPhoto);
                viewHolder.background = (FrameLayout) rowView.findViewById(R.id.background);
                viewHolder.card_3 = (LinearLayout) rowView.findViewById(R.id.card_3);
                viewHolder.card_2 = (LinearLayout) rowView.findViewById(R.id.card_2);
                viewHolder.estado = (TextView) rowView.findViewById(R.id.estado);
                viewHolder.cidade = (TextView) rowView.findViewById(R.id.cidade);
                //viewHolder.titulo = (TextView) rowView.findViewById(R.id.titulo);
                viewHolder.btnIgnore = (Button) rowView.findViewById(R.id.btnIgnore);
                viewHolder.btnAccept = (Button) rowView.findViewById(R.id.btnAccept);
                rowView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.hideBackground(true, 1);

            viewHolder.btnAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("btnAccept", "Aceitar");
                }
            });


            viewHolder.btnIgnore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    flingContainer.getTopCardListener().selectRight();

                }
            });

            viewHolder.tipo = hackathonName.get(position).getType();

            // 1 friendship  /  2 location
            if(viewHolder.tipo == 1){
                viewHolder.titulo.setText("Pedido de Amizade");
                viewHolder.btnAccept.setText("Aceitar");
                viewHolder.btnIgnore.setText("Recusar");
            } else if(viewHolder.tipo == 2){
                viewHolder.titulo.setText("Pedido de Localização");
                viewHolder.btnAccept.setText("Visualizar");
                viewHolder.btnIgnore.setText("Ignorar");
            }

            viewHolder.DataText.setText(hackathonName.get(position).getHackathonName());
            viewHolder.cidade.setText(hackathonName.get(position).getLocation());
            viewHolder.estado.setText(hackathonName.get(position).getDate());

            //user photo
            String photo = hackathonName.get(position).getPhoto();
            String strPhoto = API + "/" + photo;

            if (strPhoto != null) {

              //  Picasso.with(activity)
               //         .load(strPhoto)
                //        .placeholder(R.drawable.avatar_default)
                 //       .fit()
                  //      .centerCrop()
                   //     .into(viewHolder.friendPhoto);

            }


            return rowView;
        }
    }

}
