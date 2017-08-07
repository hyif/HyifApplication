package com.hyif.hyifapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hyif.common_library.recyclerview.adapter.wrapper.LoadMoreWrapper;
import com.hyif.common_library.recyclerview.model.DisplayableItem;
import com.hyif.hyifapplication.model.Advertisement;
import com.hyif.hyifapplication.model.Cat;
import com.hyif.hyifapplication.model.Dog;
import com.hyif.hyifapplication.model.Gecko;
import com.hyif.hyifapplication.model.Snake;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  private LoadMoreWrapper mLoadMoreWrapper;
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerView);
    rv.setLayoutManager(new LinearLayoutManager(this));
    MainAdapter adapter = new MainAdapter(this, getAnimals());
    mLoadMoreWrapper = new LoadMoreWrapper(this,adapter);
    rv.setAdapter(mLoadMoreWrapper);
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        mLoadMoreWrapper.showNoMore();
      }
    },3000);
  }

  private List<DisplayableItem> getAnimals() {
    List<DisplayableItem> animals = new ArrayList<>();

    animals.add(new Cat("American Curl"));
    animals.add(new Cat("Baliness"));
    animals.add(new Cat("Bengal"));
    animals.add(new Cat("Corat"));
    animals.add(new Cat("Manx"));
    animals.add(new Cat("Nebelung"));
    animals.add(new Dog("Aidi"));
    animals.add(new Dog("Chinook"));
    animals.add(new Dog("Appenzeller"));
    animals.add(new Dog("Collie"));
    animals.add(new Snake("Mub Adder", "Adder"));
    animals.add(new Snake("Texas Blind Snake", "Blind snake"));
    animals.add(new Snake("Tree Boa", "Boa"));
    animals.add(new Gecko("Fat-tailed", "Hemitheconyx"));
    animals.add(new Gecko("Stenodactylus", "Dune Gecko"));
    animals.add(new Gecko("Leopard Gecko", "Eublepharis"));
    animals.add(new Gecko("Madagascar Gecko", "Phelsuma"));
    animals.add(new Advertisement());
    animals.add(new Advertisement());
    animals.add(new Advertisement());
    animals.add(new Advertisement());
    animals.add(new Advertisement());

    Collections.shuffle(animals);
    return animals;
  }
}
