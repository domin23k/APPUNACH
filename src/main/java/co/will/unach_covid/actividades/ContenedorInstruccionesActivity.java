package co.chenao.unach_covid.actividades;

import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.TextView;

import co.chenao.unach_covid.R;
import co.chenao.unach_covid.fragments.InstruccionAjustesFragment;
import co.chenao.unach_covid.fragments.InstruccionAyudaFragment;
import co.chenao.unach_covid.fragments.InstruccionFinalFragment;
import co.chenao.unach_covid.fragments.InstruccionInformacionFragment;
import co.chenao.unach_covid.fragments.InstruccionIniciarFragment;
import co.chenao.unach_covid.fragments.InstruccionNickNameFragment;
import co.chenao.unach_covid.fragments.InstruccionRankingFragment;
import co.chenao.unach_covid.fragments.IntroduccionFragment;

public class ContenedorInstruccionesActivity extends AppCompatActivity implements IntroduccionFragment.OnFragmentInteractionListener,InstruccionInformacionFragment.OnFragmentInteractionListener,
                    InstruccionIniciarFragment.OnFragmentInteractionListener,InstruccionNickNameFragment.OnFragmentInteractionListener,InstruccionRankingFragment.OnFragmentInteractionListener,
                    InstruccionAjustesFragment.OnFragmentInteractionListener,InstruccionAyudaFragment.OnFragmentInteractionListener,InstruccionFinalFragment.OnFragmentInteractionListener{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private LinearLayout linearPuntos;//linearLayout de Puntos

    private TextView[] puntosSlide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenedor_instrucciones);

      //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        //Se instancia el linearLayout de puntos
        linearPuntos=findViewById(R.id.idLinearPuntos);
        agregaIndicadorPuntos(0);

        mViewPager.addOnPageChangeListener(viewListener);
    }

    public void agregaIndicadorPuntos(int pos){
        puntosSlide =new TextView[8];
        linearPuntos.removeAllViews();

        for (int i=0; i< puntosSlide.length; i++){
            puntosSlide[i]=new TextView(this);
            puntosSlide[i].setText(Html.fromHtml("&#8226;"));
            puntosSlide[i].setTextSize(35);
            puntosSlide[i].setTextColor(getResources().getColor(R.color.colorBlancoTransparente));
            linearPuntos.addView(puntosSlide[i]);
        }

        if(puntosSlide.length>0){
            puntosSlide[pos].setTextColor(getResources().getColor(R.color.colorBlanco));
        }

    }

    ViewPager.OnPageChangeListener viewListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            agregaIndicadorPuntos(i);
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contenedor_instrucciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static Fragment newInstance(int sectionNumber) {
            Fragment fragment=null;

            switch (sectionNumber){
                case 1: fragment=new IntroduccionFragment(); break;
                case 2: fragment=new InstruccionIniciarFragment(); break;
                case 3: fragment=new InstruccionAjustesFragment(); break;
                case 4: fragment=new InstruccionRankingFragment(); break;
                case 5: fragment=new InstruccionAyudaFragment(); break;
       
                case 7: fragment=new InstruccionInformacionFragment(); break;
            
            }



            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_contenedor_instrucciones, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 8 total pages.
            return 8;
        }
    }
}
