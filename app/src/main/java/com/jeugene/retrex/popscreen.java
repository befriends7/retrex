package com.jeugene.retrex;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import static com.squareup.picasso.Picasso.get;

public class popscreen extends AppCompatActivity {

    @BindView(R.id.imagpop)
    ImageView imagepop;
    @BindView(R.id.head)
    TextView head;
    @BindView(R.id.link)
    TextView link;
    String heads,links;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popscreen);

        ButterKnife.bind(this);

        Picasso.get().load(R.drawable.news).fit().into(imagepop);

        intent = getIntent();
        heads = intent.getStringExtra("head");
        links = intent.getStringExtra("link");

        head.setText(heads);
        link.setText(links);

    }
}
