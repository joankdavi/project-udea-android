package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class noticias extends Fragment {

    private String title;
    private int page;


    // newInstance constructor for creating fragment with arguments
    public static noticias newInstance(int page, String title) {
        noticias fragmentnoticias = new noticias();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentnoticias.setArguments(args);
        return fragmentnoticias;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
