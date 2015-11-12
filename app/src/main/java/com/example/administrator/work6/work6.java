package com.example.administrator.work6;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class work6 extends Activity {
        private EditText et1,et2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work6);
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
    }
    
    public void savecontacts(View v){
        importPhone(et1.getText().toString(),et2.getText().toString());
        Toast.makeText(this,"联系人添加成功",Toast.LENGTH_LONG).show();
    }
    public void showcontacts(View v){
        Intent intent = new Intent(
                Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivity(intent);
    }

    private void importPhone(String name, String phone) {
        Uri phoneURL = ContactsContract.Data.CONTENT_URI;
        ContentValues value = new ContentValues();
        Uri rawContactUri = this.getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI,value);
        long rawContactId = ContentUris.parseId(rawContactUri);
        value.clear();
        value.put(ContactsContract.Contacts.Data.RAW_CONTACT_ID, rawContactId);
        value.put(ContactsContract.Contacts.Data.MIMETYPE,ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        value.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME,name);
        this.getContentResolver().insert(phoneURL,value);

        value.clear();
        value.put(ContactsContract.Contacts.Data.RAW_CONTACT_ID, rawContactId);
        value.put(ContactsContract.Contacts.Data.MIMETYPE,ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        value.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME,name);
        this.getContentResolver().insert(phoneURL,value);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_work6, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
