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


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class ClaimantAddClaimActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_claimant_add_claim);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.claimant_add_claim, menu);
		return true;
	}
	
	public void finishAdd(View v){
		EditText nameView= (EditText) findViewById(R.id.createClaimNameEditText);
		EditText beginView=(EditText) findViewById(R.id.createClaimStartingDateEditText);
		EditText endView=(EditText) findViewById(R.id.createClaimEndDateEditText);
		
		ClaimantAddClaimController cacc=new ClaimantAddClaimController(AppSingleton.getInstance().getClaimList());
		cacc.addClaim(nameView.getText().toString(), beginView.getText().toString(),endView.getText().toString());
		finish();
	}

}
