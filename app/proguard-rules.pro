# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
#Specifies not to ignore non-public library classes.
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
-verbose
-dontpreverify
-allowaccessmodification
-mergeinterfacesaggressively
-overloadaggressively
-keepattributes *Annotation*


####################################################################  KEEP ANDROID SUPPORT V7 AND DESIGN

-dontwarn android.support.design.**
-keep class android.support.design.** { *; }
-keep interface android.support.design.** { *; }
-keep public class android.support.design.R$* { *; }

-keep public class android.support.v7.widget.** { *; }
-keep public class android.support.v7.internal.widget.** { *; }
-keep public class android.support.v7.internal.view.menu.** { *; }

-keep public class * extends android.support.v4.view.ActionProvider {
    public <init>(android.content.Context);
}

-keep interface android.support.v4.** { *; }
-keep interface android.support.v7.** { *; }
-keep class android.support.** { *; }

####################################################################  REMOVE WARNINGS


-dontwarn android.support.design.internal.**
-dontwarn com.google.android.gms.**
-dontwarn android.support.v4.**


####################################################################  REMOVE LOGGING

-assumenosideeffects class android.util.Log {
    public static *** e(...);
    public static *** w(...);
    public static *** wtf(...);
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
}

####################################################################  ORG.APACHE.HTTP

-keep class org.apache.http.**
-keep interface org.apache.http.**
-dontwarn org.apache.**

####################################################################  WEVVIEW

-keep public class android.net.http.SslError
-keep public class android.webkit.WebViewClient
-dontwarn android.webkit.WebView
-dontwarn android.net.http.SslError
-dontwarn android.webkit.WebViewClient

####################################################################  GOOGLE PLAY SERVICES LIB - ADS

-keep public class com.google.android.gms.* { public *; }
#-keep class com.google.android.gms.**

# For Google Play Services
-keep public class com.google.android.gms.ads.**{
   public *;
}

####################################################################  GOOGLE PLAY SERVICES LIB

-keep class * extends java.util.ListResourceBundle {
    protected java.lang.Object[][] getContents();
}

# Keep SafeParcelable value, needed for reflection. This is required to support backwards
# compatibility of some classes.
-keep public class com.google.android.gms.common.internal.safeparcel.SafeParcelable {
    public static final *** NULL;
}

# Keep the names of classes/members we need for client functionality.
-keep @interface com.google.android.gms.common.annotation.KeepName
-keepnames @com.google.android.gms.common.annotation.KeepName class *
-keepclassmembernames class * {
    @com.google.android.gms.common.annotation.KeepName *;
}

# Needed for Parcelable/SafeParcelable Creators to not get stripped
-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

# Needed when building against pre-Marshmallow SDK.
-dontwarn android.security.NetworkSecurityPolicy

# Keep metadata about included modules.
-keep public class com.google.android.gms.dynamite.descriptors.** {
  public <fields>;
}

# Keep the implementation of the flags api for google-play-services-flags

-keep public class com.google.android.gms.flags.impl.FlagProviderImpl {
  public <fields>; public <methods>;
}