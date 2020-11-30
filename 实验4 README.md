# 实验4_Intent
**自定义WebView验证隐式Intent的使用**
![Intent界面演示](https://note.youdao.com/yws/api/personal/file/9AF78D3DB3624066BABEC4E071853740?method=download&shareKey=8ebc24e9386b5bcb9b830f45a1474186)
## ◼ 第一个应用：获取URL地址并启动隐式Intent的调用。

**Java代码如下：**
```java
public class MainActivity extends AppCompatActivity {
    private Button bt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt1=findViewById(R.id.button);
        bt1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent in1= new Intent(Intent.ACTION_VIEW);
                EditText ed =findViewById(R.id.editText);
                final String net= ed.getText().toString();
                if (isValidUrl(net)){
                    in1.setData(Uri.parse(net));
                }
                else{
                    Toast.makeText(MainActivity.this,"请输入正确网址",Toast.LENGTH_SHORT).show();
                }
                startActivity(in1);
            }
        });

    }
    public static boolean isValidUrl(String urlString){
        URI uri = null;
        try {
            uri = new URI(urlString);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return false;
        }

        if(uri.getHost() == null){
            return false;
        }
        if(uri.getScheme().equalsIgnoreCase("http") || uri.getScheme().equalsIgnoreCase("https")){
            return true;
        }
        return false;
    }
}
```
**xml代码如下：**
```xml
 <EditText
        android:id="@+id/editText"
        android:layout_width="200dp"
        android:layout_height="wrap_content"
        app:layout_constraintBottom_toTopOf="@+id/button"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <Button
        android:id="@+id/button"
        android:layout_width="150dp"
        android:layout_height="wrap_content"
        android:text="@string/webjump"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
```
## ◼ 第二个应用：自定义WebView来加载URL
**Java代码如下：**
```java
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        webView=findViewById(R.id.web1);
        String url=getIntent().getExtras().getString("url");
        webView.loadUrl(url);
    }
```
**xml代码如下：**
```xml
<WebView
    android:id="@+id/web1"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>
```