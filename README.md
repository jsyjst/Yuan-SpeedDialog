# Yuan-SpeedDialog

[![](https://jitpack.io/v/jsyjst/Yutil.svg)](https://jitpack.io/#jsyjst/Yutil)
[![](https://img.shields.io/badge/作者-jsyjst-blue.svg)](https://blog.csdn.net/qq_41979349)

方便快捷的弹窗，目前有选择对话框、消息对话框、输入对话框、等待提示框、底部选择框等样式的Dialog

# 如何添加
### Gradle的引用方式
#### 1.在Project的build.gradle 中添加仓库地址
```
//JitPack仓库
maven { url 'https://jitpack.io' }
```
##### 示例：
```
allprojects {
	repositories {
		...
      		//JitPack仓库
		maven { url 'https://jitpack.io' }
	}
}
```
#### 2.在Module目录下的build.gradle中添加依赖
```
implementation 'com.github.jsyjst:Yutil:v1.0.1'
```
##### 示例：
```
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'androidx.appcompat:appcompat:1.0.0-beta01'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.2'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'androidx.test:runner:1.1.0-alpha4'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.1.0-alpha4'

    implementation 'com.github.jsyjst:Yuan-SpeedDialog:v1.0.0'
}
```
# 使用方法

提示：所有对话框使用方法与安卓系统自带的AlertDialog相似，首先要在使用前构造出一个SpeedDialog,构造需要传入对应的样式，然后根据需要根据设置相应的属性，最后必须调用Dialog的show方法才能显示出来。
### 样式表
DialogType|样式
-|-|
SpeedDialog.SELECT_TYPE|选择对话框（默认）
SpeedDialog.MESSAGE_TYPE|消息对话框
SpeedDialog.INPUT_TYPE|输入对话框
SpeedDialog.PROGRESS_TYPE|等待提示框
SpeedDialog.BOTTOM_SELECT_TYPE|底部选择框

### 选择对话框
```java
        SpeedDialog selectDialog = new SpeedDialog(this);
        selectDialog.setTitle("删除？")
                .setSureText("删除")
                .setMessage("是否删除所有历史记录")
                .setSureClickListener(new OnSelectClickListener() {
                    @Override
                    public void onClick(Dialog dialog) {
                    }
                }).show(); 
```

### 消息对话框
```java
        SpeedDialog messageDialog = new SpeedDialog(this,SpeedDialog.MESSAGE_TYPE);
        messageDialog.setTitle("消息提示框")
                .setMessage("用于提示一些消息")
                .show();
```
### 输入对话框
```java
        SpeedDialog inputDialog = new SpeedDialog(this,SpeedDialog.INPUT_TYPE);
        inputDialog.setTitle("更换昵称")
                .setSureText("确定")
                .setInputDialogSureClickListener(new OnInputDialogButtonClickListener() {
                    @Override
                    public void onClick(Dialog dialog, String inputText) {
                    }
                })
                .show();
```
### 等待提示框
```java
        SpeedDialog progressDialog = new SpeedDialog(this, SpeedDialog.PROGRESS_TYPE);
        progressDialog.setProgressColor(ContextCompat.getColor(this,R.color.colorPrimary))
                .setProgressText("正在加载...")
                .show();
```

### 底部选择框
```java
        List<String> menuList = new ArrayList<>();
        menuList.add("拍照");
        menuList.add("从相册选取");
        SpeedDialog bottomDialog = new SpeedDialog(this, SpeedDialog.BOTTOM_SELECT_TYPE);
        bottomDialog.setMenuNameList(menuList)
                .setMenuItemClickListener(new OnMenuItemClickListener() {
                    @Override
                    public void onClick(Dialog dialog,int position) {
                    }
                })
                .show();
```
