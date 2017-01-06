package itspay.br.com.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
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

import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.listeners.RecyclerItemClickListener;
import com.dexafree.materialList.view.MaterialListView;

import java.util.ArrayList;
import java.util.List;

import itspay.br.com.authentication.IdentityItsPay;
import itspay.br.com.controller.MeusCartoesController;
import itspay.br.com.itspay.R;
import itspay.br.com.model.Credencial;
import itspay.br.com.services.ConnectPortadorService;
import itspay.br.com.util.Utils;
import itspay.br.com.util.UtilsActivity;
import jp.wasabeef.recyclerview.animators.FlipInBottomXAnimator;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MeusCartoesActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private MaterialListView mListView;
    private Credencial credenciais[];
    private MeusCartoesController meusCartoesController = new MeusCartoesController(this);
    private SwipeRefreshLayout swipeRefreshLayout;
    private int countConexaoServicoPlastico;
    public static boolean FORCE_LOGOUT = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_cartoes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Irá abrir o MarketPlace", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
                meusCartoesController.listarCredenciais();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(FORCE_LOGOUT){
            meusCartoesController.forceLogout();
        }

        meusCartoesController.listarCredenciais();
    }

    public void configurarCartoes(){

        mListView.setItemAnimator(new FlipInBottomXAnimator());
        mListView.getItemAnimator().setAddDuration(300);
        mListView.getItemAnimator().setRemoveDuration(300);

        adicionarCartoes();

        countConexaoServicoPlastico = credenciais.length;

        for(final Credencial cred : credenciais){
            Call<ResponseBody> call = ConnectPortadorService
                                            .getService()
                                            .abrirPlastico(
                                                        cred.getIdPlastico(),
                                                        IdentityItsPay.getInstance().getToken());

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    countConexaoServicoPlastico--;

                    if(response.body()!=null && response.body().byteStream()!=null) {
                        cred.setDrawable(new BitmapDrawable(response.body().byteStream()));
                    }

                    if(countConexaoServicoPlastico==0){
                        adicionarCartoes();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    UtilsActivity.alertIfSocketException(t, MeusCartoesActivity.this);
                    countConexaoServicoPlastico--;

                    if(countConexaoServicoPlastico==0){
                        adicionarCartoes();
                    }
                }
            });
        }
    }

    private void adicionarCartoes() {
        mListView.getAdapter().clearAll();
        List<Card> cards = new ArrayList<>();
        for (Credencial cred: credenciais) {
            cards.add(Utils.novoCartaoCredencial(cred, this));
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

        //noinspection SimplifiableIfStatement
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

        } else if(id == R.id.nav_call_sac){
            meusCartoesController.ligar("08009406020");
        } else if(id == R.id.nav_call_ouvidoria){
            meusCartoesController.ligar("35129797");
        } else if(id == R.id.nav_email_fale_conosco){
            meusCartoesController.enviarEmail("sac@financialcartoes.com.br", "", "","");
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
}
