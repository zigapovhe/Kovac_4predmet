package si.povhe.symbollayer_kovac;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mapbox.mapboxsdk.annotations.Marker;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MarkerInfo.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MarkerInfo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MarkerInfo extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "marker_name";
    private static final String ARG_PARAM2 = "marker_image";
    private static final String ARG_PARAM3 = "marker_opis";
    private Marker marker;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private int mParam2;
    private String mParam3;
    private Button backBtn;


    private OnFragmentInteractionListener mListener;

    public MarkerInfo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MarkerInfo.
     */
    // TODO: Rename and change types and number of parameters
    public static MarkerInfo newInstance(String param1, String param2, String param3) {
        MarkerInfo fragment = new MarkerInfo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Arguments: " + getArguments());
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
            mParam3 = getArguments().getString(ARG_PARAM3);

        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_marker_info, container, false);

        TextView markerText = view.findViewById(R.id.markerTxt);
        TextView markerOpis = view.findViewById(R.id.opisTxt);

        backBtn = view.findViewById(R.id.backBtn);
        ImageView markerImage = view.findViewById(R.id.markerIMG);
       markerImage.setImageResource(mParam2);

       backBtn.setOnClickListener(this);
        System.out.println("plsWork:"+mParam1);
        markerText.setText(mParam1);
        markerOpis.setText(mParam3);



        return view;
    }

    @Override
    public void onClick(View v) {
        getFragmentManager().popBackStackImmediate();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
