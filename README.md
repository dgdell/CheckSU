# CheckSU
## A small library to check root permission in Android Device

How to include
Add the repository to your project build.gradle:

```
repositories {
    maven {
        url "https://jitpack.io"
    }
}
```
And add the library to your module build.gradle:
```
dependencies {
	        implementation 'com.github.ZonaRMR:CheckSU:1.0'
	}
  ```
 ## Usage
 Initialize CheckSU
 ```
 boolean returnRootStatus = CheckSU.checkRootAccess(getApplicationContext());
 ```
Now it's time to verify!
###  Example:
 ```
 @Override
        protected void onPostExecute(Boolean isRooted) {
            progressDialog.dismiss();
            if(isRooted){
                Toast.makeText(getApplicationContext(), "Device rooted", Toast.LENGTH_LONG).show();
            } else{
                Toast.makeText(getApplicationContext(), "Device No rooted", Toast.LENGTH_LONG).show();
            }
        }
```
