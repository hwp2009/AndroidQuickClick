# AndroidQuickClick
Android开发中，防止View快速点击 通过一行注解搞定
#### 配置
##### 1、在project的 gradle下 添加仓库地址：
```

	dependencies {
        	classpath 'com.hujiang.aspectjx:gradle-android-plugin-aspectjx:2.0.10'
   	 }

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
##### 2、在项目的gradle下添加 依赖
```
一定引入aspectj的插件
plugins {
    id 'com.android.application'
    id 'android-aspectjx'
}
dependencies {
 implementation 'com.github.hwp2009:AndroidQuickClick:1.0.0'
}
```
#### 如何使用
请参考Demo   com.yulai.MainActivity
使用@SingleClick 作用在onClick(View view) 方法上

```
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //测试快速点击

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "btn被点击了", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
           @SingleClick(clickInterval = 600)
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "bt2被点击了", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
```


