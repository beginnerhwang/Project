/*
UA CMPUT 301 Project Group: CMPUT301W15T06

Copyright {2015} {Jingjiao Ni

              Tianqi Xiao

              Jiafeng Wu

              Xinyi Pan 

              Xinyi Wu

              Han Wang}
Licensed under the Apache License, Version 2.0 (the "License");

you may not use this file except in compliance with the License.

You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
Unless required by applicable law or agreed to in writing, software distributed under 
the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF 
ANY KIND, either express or implied. See the License for the specific language 
governing permissions and limitations under the License.

 */

package ca.ualberta.CMPUT301W15T06;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * This <code>ApproverItemListActivity</code> class is an extended class
 * of <code>Activity</code> class. This class controls the User Interface of 
 * <code>Item</code> list for approver. This view displays a list of
 * <code>Item</code> and creates an option menu and return true as 
 * the boolean value. It will be used when the approver asks to access to 
 * the <code>Item</code> list. 
 * 
 * @author CMPUT301W15T06
 * @version 03/16/2015
 * @see android.os.Bundle
 * @see android.app.Activity
 * @see android.view.Menu
 */
public class ApproverItemListActivity extends Activity {
	
	private static final int PHOTO_RECEIPT = 1;
	private static final int ITEM_DETAIL = 0;
	private static final int CHANGE_FLAG = 3;
	private Claim claim;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_approver_item_list);
		
		claim=AppSingleton.getInstance().getCurrentClaim();
		
		
		
		
		ListView listView = (ListView) findViewById(R.id.approverItemListView);
		final ArrayList<Item> list =claim.getItemList();
		final ArrayAdapter<Item> adapter=new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_1,list);
		listView.setAdapter(adapter);
		
		
		
		
		


		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {
				AppSingleton.getInstance().setCurrentItem(list.get(position));
				
				AlertDialog.Builder builder = new AlertDialog.Builder(ApproverItemListActivity.this);
				builder.setTitle(R.string.title_item_dialog);
				itemChoice(builder);
				builder.create();  
				builder.show();
				
			}
			
		});
	}
	
	public void itemChoice(Builder builder){
		builder.setItems(R.array.item_dialog_array, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				if (which==PHOTO_RECEIPT){
//					Intent intent =new Intent(ApproverItemListActivity.this,ClaimantReceiptActivity.class);
//					startActivity(intent);					
				
				}else if (which==ITEM_DETAIL){
//					Intent intent =new Intent(ApproverItemListActivity.this,ClaimantItemDetailActivity.class);
//					startActivity(intent);
				}
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.approver_item_list, menu);
		return true;
	}

}
