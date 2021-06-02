# Android

### Android What?: android is based on linux kernel.

→ The Android Operating System is free and open-source software, which means that any individual or organization can use it without paying Google. For this reason, Android has become the most dominant and fastest-growing operating system in the world.

<hr>

- Android runs on billions of devices around the world.  
- The Android operating system is actually a stack composed of five different layers—five individual software elements. Each has its own role to play and unique ways that developers can directly or indirectly interact with them.
    1. Apps
    2. Java API framework
    3. Libraries and Android Runtime
    4. Hardware abstraction layer (HAL)
    5. Linux kernel

![image](https://user-images.githubusercontent.com/67774570/120532091-f4225e00-c3fc-11eb-9222-21800f4d71a7.png) |  ##lmao noobn
<hr> 
**app > java > com.example.helloworld > MainActivity**

This is the main activity. It is the entry point for our app. When we build and run our app, the system launches an instance of this Activity and loads its layout.

**app > res**

The **`res`** folder contains application resources, such as drawable files, layout files, and UI strings. It is used to store the values for the resources that are used in many Android projects to include features of color, styles, dimensions, etc. The benefit of having these resources separate from our Java code is so that they can be updated independently.

**app > res > layout > activity_main.xml**

This XML file defines the layout for the activity’s user interface (UI). It currently contains a `TextView` element with the text “Hello World!”

**app > manifests > AndroidManifest.xml**

The manifest file describes the fundamental characteristics of the app and defines each of its components.

- Android Studio creates this file for us when we create our App.
- Every Android app must include a file precisely named `AndroidManifest.XML` at the root of the project source set.
- The manifest file describes essential information about our app to the Android build tools, the Android operating system, and Google Play.

**Gradle Scripts > build.gradle**

There are two files with this name: one for the project and one for the app module. Each module has its own `build.gradle` file, but this project currently has just one module.

When we click the run button in Android Studio, here are some of the things that Gradle does:

- Locates and downloads the correct versions of any third-party libraries we need.
- Calls the correct build tools in the correct sequence to turn all of our source code and resources into a deployable app.
- Installs and runs our app on an Android device.
