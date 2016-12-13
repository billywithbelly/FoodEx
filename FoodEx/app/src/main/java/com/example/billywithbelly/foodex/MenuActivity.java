package com.example.billywithbelly.foodex;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MenuActivity extends AppCompatActivity {

    private Context context;
    ListView list;
    String[] web = {
            "早午餐",
            "下午茶",
            "火鍋",
            "燒肉",
            "日式",
            "韓式",
            "吃到飽"
    } ;

    Integer[] imageId = {
            R.drawable.brunch2,
            R.drawable.afternoontea,
            R.drawable.hot_pot,
            R.drawable.bbq2,
            R.drawable.japan,
            R.drawable.korea,
            R.drawable.buffet
    };
    String url = "http://www.fonfood.com/";
    String url1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

          Intent intent = this.getIntent();
          int location = intent.getIntExtra("num",0);
          switch (location)
          {
              //http://www.fonfood.com/%E5%9F%BA%E9%9A%86%E5%B8%82/%E6%97%A9%E5%8D%88%E9%A4%90
              case 0: url+="基隆市/";break;
              case 1: url+="台北市/";break;
              case 2: url+="新北市/";break;
              case 3: url+="桃園市/";break;
              case 4: url+="新竹市/";break;
              case 5: url+="新竹縣/";break;
              case 6: url+="苗栗縣/";break;
              case 7: url+="台中市/";break;
              case 8: url+="南投縣/";break;
              case 9: url+="彰化縣/";break;
              case 10: url+="雲林縣/";break;
              case 11: url+="嘉義市/";break;
              case 12: url+="嘉義縣/";break;
              case 13: url+="台南市/";break;
              case 14: url+="高雄市/";break;
              case 15: url+="屏東縣/";break;
              case 16: url+="宜蘭縣/";break;
              case 17: url+="花蓮縣/";break;
              case 18: url+="台東縣/";break;
              case 19: url+="澎湖縣/";break;
              case 20: url+="金門縣/";break;
              case 21: url+="連江縣/";break;
          }

        menulist adapter = new
                menulist(MenuActivity.this, web, imageId);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent html = new Intent();
                switch (position)
                {
                    case 0:
                        //brunch
                        html.setClass(MenuActivity.this, HtmlActivity.class)
                                .putExtra("link", url+"早午餐")
                                .putExtra("sorts", web[position]);
                        startActivity(html);
                        break;
                    case 1:
                        //afternoon tea
                        html.setClass(MenuActivity.this, HtmlActivity.class)
                                .putExtra("link", url+"下午茶")
                                .putExtra("sorts", web[position]);
                        startActivity(html);
                        break;
                    case 2:
                        //hot pot
                        html.setClass(MenuActivity.this, HtmlActivity.class)
                                .putExtra("link", url+"火鍋")
                                .putExtra("sorts", web[position]);
                        startActivity(html);
                        break;
                    case 3:
                        //barbeque
                        html.setClass(MenuActivity.this, HtmlActivity.class)
                                .putExtra("link", url+"燒肉")
                                .putExtra("sorts", web[position]);
                        startActivity(html);
                        break;
                    case 4:
                        //japanese
                        html.setClass(MenuActivity.this, HtmlActivity.class)
                                .putExtra("link", url+"日式")
                                .putExtra("sorts", web[position]);
                        startActivity(html);
                        break;
                    case 5:
                        //korean
                        html.setClass(MenuActivity.this, HtmlActivity.class)
                                .putExtra("link", url+"韓式")
                                .putExtra("sorts", web[position]);
                        startActivity(html);
                        break;
                    case 6:
                        //buffet
                        html.setClass(MenuActivity.this, HtmlActivity.class)
                                .putExtra("link", url+"吃到飽")
                                .putExtra("sorts", web[position]);
                        startActivity(html);
                        break;
                }
            }
        });
    }
}
