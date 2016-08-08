package com.example.therdsak.beatbox;

import android.app.Fragment;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;


/**
 * Created by Therdsak on 8/8/2016.
 */
public class BeatBoxFragment extends android.support.v4.app.Fragment {

    private static final String KEY_ = "BeatBoxFragment";
    private BeatBox beatBox;
    private Sound sound;

    public static BeatBoxFragment newInstance() {

        Bundle args = new Bundle();

        BeatBoxFragment fragment = new BeatBoxFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beat_box,container,false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_beat_box_recycle_view);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setAdapter(new SoundAdapter(beatBox.getSounds()));

        return view;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        beatBox.release();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        beatBox = new BeatBox(getActivity());
    }




    private class SoundHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        Button button;
        private Sound sound;

        public SoundHolder(LayoutInflater layoutinflater, ViewGroup viewgroup){
            super(layoutinflater.inflate(R.layout.lsit_item_sound, viewgroup ,false));


            button = (Button) itemView.findViewById(R.id.List_item_sound_button);
            button.setOnClickListener(this);

        }
        @Override
        public void onClick(View view) {
            beatBox.play(sound);
        }

        public void bindSound(Sound sound){
            this.sound = sound;
            button.setText(sound.getName());
        }
    }


    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder>{

        private  List<Sound> soundList;

        SoundAdapter(List<Sound> sounds){
            soundList = sounds;
        }

        @Override
        public SoundHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new SoundHolder(inflater,parent);
        }

        @Override
        public void onBindViewHolder(SoundHolder holder, int position) {
            Sound sound = soundList.get(position);
            holder.bindSound(sound);

        }

        @Override
        public int getItemCount() {
            return soundList.size();
        }
    }
}
