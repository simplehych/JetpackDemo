# Android Jetpack 使用练习
---

## 参考资料：
https://github.com/googlesamples/android-sunflower
https://mp.weixin.qq.com/s/4UP-pDs0FK66g1QUQvRN6A

## Get Point

1. LiveData
2. ViewModel
3. Room


## 使用步骤

以本 Demo 作为参考说明

实现梳理顺序可如下：

1. Word
2. WordDao
3. LiveData
4. AppRoomDatabase
5. WordRepository
6. WordViewModel
7. WordViewModelFactory
8. viewModel.observe



## Groovy 改成 Kotlin


https://docs.gradle.org/current/userguide/build_init_plugin.html
https://guides.gradle.org/migrating-build-logic-from-groovy-to-kotlin/
https://docs.gradle.org/current/userguide/build_environment.html
https://www.jianshu.com/p/8fdfbcf35f0d

## Q & A

Q: 使用 androidx 代替 support 和 arch 包

A: 需要全部更换，只有部分替换可能出现兼容性问题，包括不限于：appcompat、constraintlayout、recyclerview等，将导包类型全部替换

Q: RoomDatabase_Impl does not exist

    ```
    java.lang.RuntimeException: cannot find implementation for com.*.*.*.*.*. *_Impl does not exist
    ```

A: 使用 Kotlin 情况下 build.config 配置如下

    ```
    apply plugin: 'kotlin-android'
    apply plugin: 'kotlin-android-extensions'
    apply plugin: 'kotlin-kapt'

    ...

    kapt "androidx.room:room-compiler:$rootProject.roomVersion"
    implementation "androidx.room:room-runtime:$rootProject.roomVersion"
    ```

Q: Cleartext HTTP traffic to xxx not permitted解决方法

A: https://developer.android.google.cn/training/articles/security-config
