package itspay.br.com.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.listeners.RecyclerItemClickListener;
import com.dexafree.materialList.view.MaterialListView;
import com.example.tutoriallibrary.showcaseview.ShowcaseView;
import com.example.tutoriallibrary.showcaseview.targets.ViewTarget;

import java.util.ArrayList;
import java.util.List;

import itspay.br.com.controller.MeusCartoesController;
import itspay.br.com.itspay.R;
import itspay.br.com.model.Credencial;
import itspay.br.com.util.Utils;
import itspay.br.com.util.notification.CustomNotification;
import itspay.br.com.util.usersharepreferences.SharedPreferenceUtil;
import jp.wasabeef.recyclerview.animators.FlipInBottomXAnimator;

public class MeusCartoesActivity extends AppCompatActivity
        implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private ShowcaseView sv;
    FloatingActionButton fab;
    private MaterialListView mListView;
    private Credencial credenciais[];
    private MeusCartoesController meusCartoesController = new MeusCartoesController(this);
    private SwipeRefreshLayout swipeRefreshLayout;
    public static boolean FORCE_LOGOUT = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_cartoes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirMarketPlace();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        mListView = (MaterialListView) findViewById(R.id.material_listview);

        // Add the ItemTouchListener
        mListView.addOnItemTouchListener(new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull Card card, int position) {
                Log.d("CARD_TYPE", "" + card.getTag());
                CartaoActivity.credencialDetalhe = credenciais[position];
                Intent intent = new Intent(MeusCartoesActivity.this, CartaoActivity.class);
                MeusCartoesActivity.this.startActivity(intent);
            }

            @Override
            public void onItemLongClick(@NonNull Card card, int position) {
//                Log.d("LONG_CLICK", "" + card.getTag());
            }
        });

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshCredenciais);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recreate();
            }
        });

        meusCartoesController.listarCredenciais();
    }


    private void isAlertMarketPlace(){

        if(!SharedPreferenceUtil.getBooleanPreference(getBaseContext(),"isGoMarktplace",false) && SharedPreferenceUtil.getBooleanPreference(getBaseContext(),"isTutorialMarktPlace",false)) {
            CustomNotification.getInstance().notificationBuilder(getBaseContext(), R.drawable.cart, R.color.red_itspay_bkp, getString(R.string.app_name), "Conheça nossa Loja e aproveite as ofertas.");
        }

        if(!SharedPreferenceUtil.getBooleanPreference(getBaseContext(),"isTutorialMarktPlace",false)) {

            RelativeLayout.LayoutParams lps = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lps.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            lps.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            int margin = ((Number) (getResources().getDisplayMetrics().density * 12)).intValue();
            lps.setMargins(margin, margin, margin, margin);

            ViewTarget target = new ViewTarget(R.id.fab, this);
            sv = new ShowcaseView.Builder(this)
                    .withMaterialShowcase()
                    .setTarget(target)
                    .setContentTitle(R.string.app_name)
                    .setContentText(R.string.decription_marketplace)
                    .setStyle(R.style.CustomShowcaseTheme2)
                    .replaceEndButton(R.layout.view_custom_button)
                    .build();
            sv.setButtonPosition(lps);

            sv.show();
            SharedPreferenceUtil.setBooleanPreference(getBaseContext(),"isTutorialMarktPlace", true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(FORCE_LOGOUT){
            meusCartoesController.forceLogout();
        }
        isAlertMarketPlace();
    }

    public void abrirMarketPlace(){
        Intent intent = new Intent(this, MarketPlaceActivity.class);
        startActivity(intent);
    }

    public void configurarCartoes(){

        mListView.setItemAnimator(new FlipInBottomXAnimator());
        mListView.getItemAnimator().setAddDuration(500);
        mListView.getItemAnimator().setRemoveDuration(300);

        mListView.getAdapter().clearAll();
        List<Card> cards = new ArrayList<>();
        Utils utils = new Utils();
        for(int i=0; i<credenciais.length; i++){
            cards.add(utils.novoCartaoCredencial(credenciais[i], this));
        }
        mListView.getAdapter().addAll(cards);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
//            super.onBackPressed();
            meusCartoesController.logout();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.meus_cartoes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings_trocar_email_portador) {
            meusCartoesController.abrirTrocarEmail();
            return true;
        }
        if (id == R.id.action_settings_trocar_senha_login) {
            meusCartoesController.abrirTrocarSenha();
            return true;
        }
        if (id == R.id.action_settings_logout) {
            meusCartoesController.logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    @TargetApi(Build.VERSION_CODES.M)
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_trocar_email_portador) {
            meusCartoesController.abrirTrocarEmail();
        } else if (id == R.id.nav_trocar_senha) {
            meusCartoesController.abrirTrocarSenha();
        } else if (id == R.id.nav_termos_de_uso) {
            Intent intent = new Intent(MeusCartoesActivity.this, TermosDeUsoActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_marketplace) {
            abrirMarketPlace();
        } else if(id == R.id.nav_call_sac){
            meusCartoesController.ligar("3232294950");
        } else if(id == R.id.nav_call_ouvidoria){
            meusCartoesController.ligar("3208002859632");
        } else if(id == R.id.nav_email_fale_conosco){
            meusCartoesController.enviarEmail(getString(R.string.info_email), "", "","");
        } else if(id == R.id.nav_logout){
            meusCartoesController.logout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public Credencial[] getCredenciais() {
        return credenciais;
    }

    public void setCredenciais(Credencial[] credenciais) {
        this.credenciais = credenciais;
    }

    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return swipeRefreshLayout;
    }

    public void setSwipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout) {
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    public MaterialListView getmListView() {
        return mListView;
    }

    public void setmListView(MaterialListView mListView) {
        this.mListView = mListView;
    }

    @Override
    public void onClick(View v) {

        if (sv.isShown()) {
            sv.setStyle(R.style.CustomShowcaseTheme);
        } else {
            sv.show();
        }


    }
}
