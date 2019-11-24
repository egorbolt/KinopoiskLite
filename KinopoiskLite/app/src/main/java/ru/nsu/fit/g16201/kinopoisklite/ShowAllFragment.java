package ru.nsu.fit.g16201.kinopoisklite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ShowAllFragment extends Fragment {

    public ShowAllFragment()
    {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_all, container, false);

        //View movieCollection = view.findViewById(id);
        Bundle bundle = this.getArguments();

        if(bundle != null){
            System.out.println("fdfdsdfsd");
        }
        return view;
    }

}
