package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.com.seguratec.theintell.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class favoritos extends Fragment {

    private String title;
    private int page;

    // newInstance constructor for creating fragment with arguments
    public static favoritos newInstance(int page, String title) {
        favoritos fragmentfavoritos = new favoritos();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentfavoritos.setArguments(args);
        return fragmentfavoritos;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favoritos, container, false);
    }

}
