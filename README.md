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
	implementation 'com.github.LowTension:CheckSU:1.1.7'
}
  ```
 ## Usage
###  Example:
 ```java
    @SuppressLint("StaticFieldLeak")
	public class AsyncTaskCheckRoot extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected void onPreExecute() {
	        progressDialog = new ProgressDialog(this);
		   progressDialog.setCancelable(false);
		   progressDialog.setMessage(getString(R.string.analyzing));
		   progressDialog.show();
         }

        @Override
        protected Boolean doInBackground(Void... params) {
		   try {
			Thread.sleep( 2 * 1000 );
		   }
		   catch (InterruptedException e){
			e.printStackTrace();
		   }
		   return CheckSU.checkRootAccess(getApplicationContext());
         }

        @Override
        protected void onPostExecute(Boolean isRooted) {
            progressDialog.dismiss();
		   if(isRooted){
		       Toast.makeText(getApplicationContext(), "Device has root access", Toast.LENGTH_LONG).show();
		   } else{
	               // Do something
		   }
         }
    }
```

Executing a shell operation:

```java
String cmd = "su -c am start -a android.intent.action.ACTION_REQUEST_SHUTDOWN";


if(isRooted){
    CheckSU.executeAsRoot(context, cmd);
} else{
    // Do something
}
```


