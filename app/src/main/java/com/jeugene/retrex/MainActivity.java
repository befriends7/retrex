package com.jeugene.retrex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Toast;


import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rec1)
    RecyclerView rec1;
    @BindView(R.id.prog)
    AVLoadingIndicatorView prog;
    Animation animation;
    ArrayList<fin> finallist;
    View view;
    String head,web;
    private static final String TAG = "tag";
    Intent intent;
    RecyclerView.LayoutManager lm;
    RecAdapter adapter;


    Doc maindoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        finallist = new ArrayList<>();


        prog.show();

        getObservable().subscribeWith(getObserver());
        getObservable();
        getObserver();
        loaddata();

    }

    protected void loaddata() {

        lm = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        rec1.setLayoutManager(lm);
        rec1.setHasFixedSize(true);

    }


    public Observable<Example> getObservable() {
        return APIcreate.getClient().create(Api.class)
                .getnewlist()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public DisposableObserver<Example> getObserver() {

        return new DisposableObserver<Example>() {
            @Override
            public void onNext(Example example) {


                    Response responseList = example.getResponse();
                    List<Doc> doclist = responseList.getDocs();

                    for(int i=0;i<doclist.size();i++)
                    {
                        maindoc = doclist.get(i);
                        Headline heads = maindoc.getHeadline();
                        head = heads.getMain();
                        web = maindoc.getWebUrl();
                        fin fins = new fin(head,web);
                        finallist.add(fins);


                    }

               adapter = new RecAdapter(finallist);
                rec1.setAdapter(adapter);


                adapter.setOnItemClickListener(new onItemClickListener() {
                    @Override
                    public void OnItemClick(fin data) {


                        Intent intent = new Intent(MainActivity.this,popscreen.class);
                        intent.putExtra("head",data.getHead());
                        intent.putExtra("link",data.getWeburl());
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onError(Throwable e) {

                Toast.makeText(MainActivity.this,"Server Error",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onComplete() {

                prog.hide();

            }
        };

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.menu_main,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {

            case R.id.item1:
            {

                Intent intent = new Intent(MainActivity.this,logs.class);
                startActivity(intent);
                break;
            }

        }

        return super.onOptionsItemSelected(item);
    }
}