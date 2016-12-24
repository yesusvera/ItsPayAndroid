package itspay.br.com.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
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
import com.dexafree.materialList.card.CardProvider;
import com.dexafree.materialList.listeners.RecyclerItemClickListener;
import com.dexafree.materialList.view.MaterialListView;

import java.util.ArrayList;
import java.util.List;

import itspay.br.com.controller.MeusCartoesController;
import itspay.br.com.itspay.R;
import itspay.br.com.model.Credencial;
import jp.wasabeef.recyclerview.animators.SlideInDownAnimator;

public class MeusCartoesActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private MaterialListView mListView;
    private Credencial credenciais[];
    private MeusCartoesController meusCartoesController = new MeusCartoesController(this);
    private SwipeRefreshLayout swipeRefreshLayout;

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
                CartaoActivity.cartaoDetalhe = card;
                CartaoActivity.credencialDetalhe = credenciais[position];
                Intent intent = new Intent(MeusCartoesActivity.this, CartaoActivity.class);
                MeusCartoesActivity.this.startActivity(intent);
            }

            @Override
            public void onItemLongClick(@NonNull Card card, int position) {
                Log.d("LONG_CLICK", "" + card.getTag());
            }
        });

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshCredenciais);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mListView.getAdapter().clearAll();
                meusCartoesController.listarCredenciais();
            }
        });

        swipeRefreshLayout.setRefreshing(true);
        meusCartoesController.listarCredenciais();

    }

    public void configurarCartoes(){

        mListView.setItemAnimator(new SlideInDownAnimator());
        mListView.getItemAnimator().setAddDuration(300);
        mListView.getItemAnimator().setRemoveDuration(300);

//        // Set the dismiss listener
//        mListView.setOnDismissCallback(new OnDismissCallback() {
//            @Override
//            public void onDismiss(@NonNull Card card, int position) {
//                // Show a toast
//                Toast.makeText(MeusCartoesActivity.this, "You have dismissed a " + card.getTag(), Toast.LENGTH_SHORT).show();
//            }
//        });


        adicionarCartoes();

        swipeRefreshLayout.setRefreshing(false);
    }

    private void adicionarCartoes() {
        List<Card> cards = new ArrayList<>();
        for (Credencial cred: credenciais) {
            cards.add(newCard(cred.getCredencialMascarada(),cred.getNomeProduto(), Double.toString(cred.getSaldo())));
        }
        mListView.getAdapter().addAll(cards);
    }


    public Card newCard(String credencialMascarada, String nomeProduto, String saldo) {
        String title = credencialMascarada;
        String description = "Saldo: R$"+ saldo;

        return new Card.Builder(this)
                .setTag("SMALL_IMAGE_CARD")
                .withProvider(new CardProvider())
                .setLayout(R.layout.material_itspay_card)
                .setTitle(nomeProduto)
                .setTitleColor(Color.WHITE)
                .setSubtitle(credencialMascarada)
                .setSubtitleColor(Color.CYAN)
                .setDescription(description)
                .setDescriptionColor(Color.YELLOW)
                .setDrawable(R.drawable.card1)
                .endConfig()
                .build();

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
}
