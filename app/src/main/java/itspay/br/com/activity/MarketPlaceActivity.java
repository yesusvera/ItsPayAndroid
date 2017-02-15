package itspay.br.com.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import itspay.br.com.fragment.LojaCarrinhoFragment;
import itspay.br.com.fragment.LojaPedidosFragment;
import itspay.br.com.fragment.LojaProdutosFragment;
import itspay.br.com.itspay.R;
import itspay.br.com.singleton.CarrinhoSingleton;
import itspay.br.com.util.usersharepreferences.SharedPreferenceUtil;

public class MarketPlaceActivity extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_place);

        SharedPreferenceUtil.setBooleanPreference(getBaseContext(),"isGoMarktplace",true);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setSelectedTabIndicatorColor(getColor(R.color.indicator_tab));

        tabLayout.getTabAt(0).setCustomView(R.layout.tab_loja_personalizado);
        tabLayout.getTabAt(1).setCustomView(R.layout.tab_loja_personalizado);
        tabLayout.getTabAt(2).setCustomView(R.layout.tab_loja_personalizado);

        configuraTabPersonalizado(tabLayout.getTabAt(0).getCustomView(),
                getDrawable(R.drawable.loja),
                View.GONE,
                getResources().getString(R.string.titulo_loja)
        );
        configuraTabPersonalizado(tabLayout.getTabAt(1).getCustomView(),
                getDrawable(R.drawable.meus_pedidos),
                View.GONE,
                getResources().getString(R.string.titulo_meus_pedidos)
        );
        configuraTabPersonalizado(tabLayout.getTabAt(2).getCustomView(),
                getDrawable(R.drawable.carrinho_market),
                View.VISIBLE,
                getResources().getString(R.string.titulo_carrinho)
        );


    }

    @Override
    protected void onResume() {
        super.onResume();
        configuraBadgedCarrinho();
    }

    public void configuraBadgedCarrinho(){
        View view = tabLayout.getTabAt(2).getCustomView();
        TextView txtBadget = (TextView)view.findViewById(R.id.txt_badged);

        int qtde =  CarrinhoSingleton.getInstance().getListaProdutosCarrinho().size();

        if(qtde == 0){
            txtBadget.setVisibility(View.GONE);
        }else {
            txtBadget.setVisibility(View.VISIBLE);
            txtBadget.setText("" + CarrinhoSingleton.getInstance().getListaProdutosCarrinho().size());
        }
    }

    public void configuraTabPersonalizado(View view, Drawable icone, int visibility, String nomeTab){

        TextView txtIcone = (TextView)view.findViewById(R.id.txt_icone);
        TextView txtBadget = (TextView)view.findViewById(R.id.txt_badged);
        TextView txtNomeTab = (TextView)view.findViewById(R.id.txt_nome_tab);

        txtIcone.setBackground(icone);
        txtBadget.setVisibility(visibility);
        txtNomeTab.setText(nomeTab);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_market_place, menu);
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

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        Drawable myDrawable;
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            Fragment fragment = LojaProdutosFragment.newInstance("","");

            switch(position){
                case 0:
                    fragment =  LojaProdutosFragment.newInstance("","");
                    break;
                case 1:
                    fragment =  LojaPedidosFragment.newInstance("","");
                    break;
                case 2:
                    fragment =  LojaCarrinhoFragment.newInstance("","");
                    break;
                default:
                    break;
            }

            return fragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            String title = "";
            switch (position) {
                case 0:
                    title = getResources().getString(R.string.titulo_loja);
                    break;
                case 1:
                    title = getResources().getString(R.string.titulo_meus_pedidos);
                    break;
                case 2:
                    title = getResources().getString(R.string.titulo_carrinho);
                    break;
                default:
                    break;
            }
            SpannableStringBuilder sb = new SpannableStringBuilder("   " + title); // space added before text for convenience

            return sb;
        }
    }
}
