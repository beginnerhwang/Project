package ca.ualberta.CMPUT301W15T06.test;

import ca.ualberta.CMPUT301W15T06.Claim;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListController;
import ca.ualberta.CMPUT301W15T06.ClaimantEditDestinationActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantEditItemActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantItemListActivity;
import ca.ualberta.CMPUT301W15T06.MainActivity;
import ca.ualberta.CMPUT301W15T06.User;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ListView;

@SuppressLint("CutPasteId")
public class US04_01_01_Test<Final> extends
			ActivityInstrumentationTestCase2<MainActivity> {

	Button ApproverButton;
	Button ClaimantButton;
	Button UserButton;
	Instrumentation instrumentation;
	MainActivity activity;
	EditText textInput;
	Intent intent;
	TextView input_name;
	TextView input_start;
	TextView input_end;
	ListView listView;
	Menu menu;
	View View1;
	EditText claimant_name;
	EditText claimant_starting_date;
	EditText claimant_ending_date;
	Button FinishButton;
	ClaimantClaimListController cclc;
	User u;


	public US04_01_01_Test() {
		super(MainActivity.class);
	}

	//set up
	protected void setUp() throws Exception {
		super.setUp();
	instrumentation = getInstrumentation();
	activity = getActivity();
	setActivityInitialTouchMode(false);
	ApproverButton = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton);
	ClaimantButton = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton);
	UserButton = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.userButton);
	intent = new Intent(getInstrumentation().getTargetContext(), MainActivity.class);	
	u = new User("t");
	cclc = new ClaimantClaimListController(u);
	}
	
	public void test() {
		/*
		* Test for US04.01.01 Basic Flow 1
		*/
		// test button layouts
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton));
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton));
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.userButton));
 
		//test "Approver" button layout
		final View decorView = activity.getWindow().getDecorView();

		ViewAsserts.assertOnScreen(decorView, ApproverButton);

		final ViewGroup.LayoutParams layoutParams =
				ApproverButton.getLayoutParams();
		assertNotNull(layoutParams);
		assertEquals(layoutParams.width, WindowManager.LayoutParams.WRAP_CONTENT);
		assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
   
		Button view = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton);
		assertEquals("Incorrect label of the button", "Approver", view.getText());
		
		//test "Claimant" button layout
		ViewAsserts.assertOnScreen(decorView, ClaimantButton);

		final ViewGroup.LayoutParams layoutParams1 =
				ClaimantButton.getLayoutParams();
		assertNotNull(layoutParams1);
		assertEquals(layoutParams1.width, WindowManager.LayoutParams.WRAP_CONTENT);
		assertEquals(layoutParams1.height, WindowManager.LayoutParams.WRAP_CONTENT);
   
		Button view1 = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton);
		assertEquals("Incorrect label of the button", "Claimant", view1.getText());
		
		//test "Change User" button layout
		ViewAsserts.assertOnScreen(decorView, ClaimantButton);

		final ViewGroup.LayoutParams layoutParams2 =
				UserButton.getLayoutParams();
		assertNotNull(layoutParams1);
		assertEquals(layoutParams2.width, WindowManager.LayoutParams.WRAP_CONTENT);
		assertEquals(layoutParams2.height, WindowManager.LayoutParams.WRAP_CONTENT);
   
		Button view2 = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.userButton);
		assertEquals("Incorrect label of the button", "Change User", view2.getText());

		/*
		 * Test for US01.02.01 Basic Flow 2
		 */
		//User click "Change User"

		activity.runOnUiThread(new Runnable(){

			@Override
			public void run() {

				/*
				* Test for US 01.02.01 Basic Flow 2
				*/
				// click button to start another activity
				assertTrue(UserButton.performClick());	
				
				/*
				 * Test for US 01.02.01 Basic Flow 3
				 */
				//test opening a dialog
		    	// access the alert dialog using the getDialog() method created in the activity
				AlertDialog d = (AlertDialog) activity.getDialog();

//				// check layout
//		    	assertTrue(d.isShowing());
//		    	
//		    	Button p = d.getButton(AlertDialog.BUTTON_POSITIVE);
//		    	Button n = d.getButton(AlertDialog.BUTTON_NEGATIVE);
//		    	
//		    	final View decorView = activity.getWindow().getDecorView();
//				ViewAsserts.assertOnScreen(decorView, p);
//				ViewAsserts.assertOnScreen(decorView, n);
//				
//				/*
//				 * Test for US 01.02.01 Basic Flow 4 
//				 */
//				// set text
//				EditText et = activity.getInputField();
//				assertNotNull(et);
//				
//				et.setText("NewUser");
//				
//				assertTrue(p.performClick());

				
				}	
		});


		//click "Claimant" button and create next activity
		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ClaimantClaimListActivity.class.getName(), null, false);
		//open current activity			
		MainActivity myActivity = getActivity();
		final Button button = (Button) myActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton);
		myActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// click button and open next activity.
				button.performClick();
				}
		});

		ClaimantClaimListActivity nextActivity = (ClaimantClaimListActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
		// next activity is opened and captured.
		assertNotNull(nextActivity);
		
		// view which is expected to be present on the screen			
		final View decorView1 = nextActivity.getWindow().getDecorView();
	 
		listView = (ListView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);
		// check if it is on screen
		ViewAsserts.assertOnScreen(decorView1, listView);
		// check whether the Button object's width and height attributes match the expected values
		final ViewGroup.LayoutParams layoutParams11 = listView.getLayoutParams();
		/*assertNotNull(layoutParams);*/
		assertEquals(layoutParams11.width, WindowManager.LayoutParams.MATCH_PARENT);
		assertEquals(layoutParams11.height, WindowManager.LayoutParams.WRAP_CONTENT);	 
		
		/*
		 * Test for US 04.01.01 Basic Flow 2
		 */
		final ListView claimList = (ListView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);
		
		//get next activity
		nextActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// click the list open next activity.
				ActivityMonitor am = getInstrumentation().addMonitor(ClaimantItemListActivity.class.getName(), null, false);
				claimList.getChildAt(0).performClick();
				ClaimantItemListActivity thirdActivity = (ClaimantItemListActivity) getInstrumentation().waitForMonitorWithTimeout(am, 10000);
				assertNotNull(thirdActivity);

				
			/*
			 * Test for US 04.01.01 Basic Flow 3
			 */		
				ListView ilv = (ListView) thirdActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.itemListView);
				// check if it is on screen
				ViewAsserts.assertOnScreen(decorView1, ilv);
				
				
			/*
			 * Test for US 04.01.01 Basic Flow 4
			 */		
				//test "Add an item" button layout
				final Button addItem = (Button) thirdActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.addItemButton);
				assertNotNull(addItem);
				final View decorView = thirdActivity.getWindow().getDecorView();
				ViewAsserts.assertOnScreen(decorView, addItem);
				final ViewGroup.LayoutParams layoutParams =addItem.getLayoutParams();
				assertNotNull(layoutParams);
				assertEquals(layoutParams.width, WindowManager.LayoutParams.WRAP_CONTENT);
				assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
				Button view = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton);
				assertEquals("Incorrect label of the button", "Add a New Item", view.getText());

				Claim claim = new Claim();
				int count_before = claim.getItemList().size();
				//test button activity
				thirdActivity.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						addItem.performClick();	
						ActivityMonitor activityMonitor = getInstrumentation().addMonitor(MainActivity.class.getName(), null, false);
						ClaimantEditItemActivity forthActivity = (ClaimantEditItemActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10000);
						assertNotNull(forthActivity);
					
						
				/*
				 * Test for US 04.01.01 Basic Flow 5
				 */	
						//test TextView
						final View ddvv = forthActivity.getWindow().getDecorView();
						TextView Date = (TextView) forthActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.editItemDateTextView);
						ViewAsserts.assertOnScreen(ddvv, Date);
						assertNotNull(Date.getVisibility());
						
						TextView Cate = (TextView) forthActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.editItemCategoryTextView);
						ViewAsserts.assertOnScreen(ddvv, Cate);
						assertNotNull(Date.getVisibility());
						
						TextView des = (TextView) forthActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.editItemDescriptionTextView);
						ViewAsserts.assertOnScreen(ddvv, des);
						assertNotNull(des.getVisibility());
						
						TextView Amount = (TextView) forthActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.editItemAmountTextView);
						ViewAsserts.assertOnScreen(ddvv, Amount);
						assertNotNull(Amount.getVisibility());
						
						TextView currency = (TextView) forthActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.editItemCurrencyTextView);
						ViewAsserts.assertOnScreen(ddvv, currency);
						assertNotNull(currency.getVisibility());
						
						//test EditView
						EditText da = ((EditText) forthActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.editItemDateEditText));
						assertNotNull(da);
						
						EditText de = ((EditText) forthActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.editItemDescriptionEditText));
						assertNotNull(de);
						
						EditText am = ((EditText) forthActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.editItemAmountEditText));
						assertNotNull(am);
						
					/*
					 * Test for US 04.01.01 Basic Flow 6
					 */	
						//fill blank
						//test spinner
						Spinner spinner = (Spinner) forthActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.editCategorySpinner);
						assertNotNull(spinner);
						spinner.setSelection(0,true);
						
						Spinner spinner1 = (Spinner) forthActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.editCurrencySpinner);
						assertNotNull(spinner1);
						spinner1.setSelection(0,true);

						final String date1 = "2014-01-01";
						final String des1 = "b";
						final int amount1 = 111;
						da.setText(date1);
						de.setText(des1);
						am.setText(amount1);

						forthActivity.finish();	
					}				
				});
				
			/*
			 * Test for US 04.01.01 Basic Flow 7
			 */	
				
				int count_after = claim.getItemList().size();
				assertEquals(count_before,count_after-1);
			
			}
		});

	}
}


