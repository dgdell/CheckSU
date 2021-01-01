# CheckSU
## A small library to check root permission in Android Device

How to include
Add the repository to your project build.gradle:

```java
repositories {
    maven {
        ...
        url "https://jitpack.io"
    }
}
```
And add the library to your module build.gradle:
```java
dependencies {
	implementation 'com.github.ZonaRMR:CheckSU:1.0'
}
  ```
 ## Usage
 Initialize CheckSU
 ```java
 boolean isRooted = CheckSU.checkRootAccess(getApplicationContext());
 ```
Now it's time to verify!
###  Example:
 ```java
            ...
            if(isRooted){
                // Implement your code here
            } else{
                //
            }
	    ...
```
