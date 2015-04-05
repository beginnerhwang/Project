package ca.ualberta.CMPUT301W15T06.test;

import ca.ualberta.CMPUT301W15T06.ClaimantItemListActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListActivity;
import ca.ualberta.CMPUT301W15T06.MainActivity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("CutPasteId")
public class US01_04_01_Test extends

ActivityInstrumentationTestCase2<MainActivity> {

	Button ApproverButton;
	Button UserButton;
	Button ClaimantButton;
	Instrumentation instrumentation;
	Activity activity;
	EditText textInput;
	Intent intent;
	Button AddDestination;
	TextView nameView;
	TextView beginView;
	TextView endView;
	ListView listView;

	public US01_04_01_Test() {
		super(MainActivity.class);
	}

	// set up

	protected void setUp() throws Exception {
		super.setUp();

		instrumentation = getInstrumentation();
		activity = getActivity();
		setActivityInitialTouchMode(true);
		ApproverButton = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton);
		ClaimantButton = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton);
		AddDestination = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.addDestinationButton);
		UserButton = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.userButton);
		intent = new Intent(getInstrumentation().getTargetContext(),MainActivity.class);
	}

	/*
	 * Test for US01.04.01 Basic Flow 1
	 */
	// test button exists
	public void testLayout() {
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton));
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton));
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.userButton));

		// test "Approver" button layout
		final View decorView = activity.getWindow().getDecorView();
		ViewAsserts.assertOnScreen(decorView, ApproverButton);
		final ViewGroup.LayoutParams layoutParams = ApproverButton.getLayoutParams();
		assertNotNull(layoutParams);
		assertEquals(layoutParams.width,WindowManager.LayoutParams.WRAP_CONTENT);
		assertEquals(layoutParams.height,WindowManager.LayoutParams.WRAP_CONTENT);
		Button view = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton);
		assertEquals("Incorrect label of the button", "Approver",view.getText());

		// test "Claimant" button layout
		ViewAsserts.assertOnScreen(decorView, ClaimantButton);
		final ViewGroup.LayoutParams layoutParams1 = ClaimantButton.getLayoutParams();
		assertNotNull(layoutParams1);
		assertEquals(layoutParams1.width,WindowManager.LayoutParams.WRAP_CONTENT);
		assertEquals(layoutParams1.height,WindowManager.LayoutParams.WRAP_CONTENT);
		Button view1 = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton);
		assertEquals("Incorrect label of the button", "Claimant",view1.getText());

		// test "Change User" button layout
		ViewAsserts.assertOnScreen(decorView, ClaimantButton);
		final ViewGroup.LayoutParams layoutParams2 = UserButton.getLayoutParams();
		assertNotNull(layoutParams1);
		assertEquals(layoutParams2.width,WindowManager.LayoutParams.WRAP_CONTENT);
		assertEquals(layoutParams2.height,WindowManager.LayoutParams.WRAP_CONTENT);
		Button view2 = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.userButton);
		assertEquals("Incorrect label of the button", "Change User",view2.getText());

		// User click "Change User"

		// activity.runOnUiThread(new Runnable(){
		//
		// @Override
		// public void run() {
		// //open the dialog
		// UserButton.performClick();
		// }
		// });
		//
		// // test opening a dialog
		// AlertDialog dialog = (AlertDialog) ((MainActivity)
		// activity).getDialog();
		// assertNotNull(dialog);
		//
		// //write the user name
		//
		//
		// //test button on dialog
		// Button PositiveButton =
		// dialog.getButton(DialogInterface.BUTTON_POSITIVE);
		// assertNotNull(PositiveButton);
		// Button NegativeButton =
		// dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
		// assertNotNull(NegativeButton);
		//
		// //click "OK" button
		// PositiveButton.performClick();

		// click "Claimant" button and create next activity
		ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ClaimantClaimListActivity.class.getName(), null, false);
		// open current activity
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
		// check whether the Button object's width and height attributes match
		// the expected values
		final ViewGroup.LayoutParams layoutParams11 = listView.getLayoutParams();
		/* assertNotNull(layoutParams); */
		assertEquals(layoutParams11.width,WindowManager.LayoutParams.MATCH_PARENT);
		assertEquals(layoutParams11.height, WindowManager.LayoutParams.WRAP_CONTENT);	

		// add a new claim
		// TODO Auto-generated method stub

	/*
	 * Test for US01.04.01 Basic Flow 2
	 */
		final ListView claimList = (ListView) nextActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimListView);
		// get next activity
		nextActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				ActivityMonitor am = getInstrumentation().addMonitor(ClaimantItemListActivity.class.getName(), null, false);
				// click the list open next activity.
				claimList.getChildAt(0).performClick();
				ClaimantItemListActivity thirdActivity = (ClaimantItemListActivity) getInstrumentation().waitForMonitorWithTimeout(am, 1000);
				assertNotNull(thirdActivity);
			/*
			 * Test for US01.04.01 Basic Flow 3
			 */	
				ListView ilv = (ListView) thirdActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.itemListView);
				// check if it is on screen
				ViewAsserts.assertOnScreen(decorView1, ilv);
				
			/*
			 * Test for US01.04.01 Basic Flow 4,5,6,7
			 */	
				// Click the menu option;
				getInstrumentation().invokeMenuActionSync(thirdActivity,ca.ualberta.CMPUT301W15T06.R.id.edit, 1);
				//open the forth activity
				Activity forthActivity =  getInstrumentation().waitForMonitorWithTimeout(am, 10000);
				assertNotNull(forthActivity);
				
				EditText claimant_starting_date = ((EditText) forthActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.editClaimStartingDateEditText));
				EditText claimant_ending_date = ((EditText) forthActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.editClaimEndDateEditText));
				TextView input_start = (TextView) forthActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.editClaimStartingDateTextView);
				TextView input_end = (TextView) forthActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.editClaimEndingDateTextView);
		
			/*
			 * Test for US01.04.01 Basic Flow 8
			 */
				 // test text view: createClaimNameEditText
				 final View decorView = forthActivity.getWindow().getDecorView();;
				 // test text view: createClaimStartingDateEditText
				 ViewAsserts.assertOnScreen(decorView, input_start);
				 assertTrue(View.GONE == input_start.getVisibility());
				 // test text view: createClaimEndDateEditText
				 ViewAsserts.assertOnScreen(decorView, input_end);
				 assertTrue(View.GONE == input_end.getVisibility());
				 
			/*
			 * Test for US01.04.01 Basic Flow 8
			 */
				 String claimantStartingDate = "2015-03-04";
				 String itemEndingDate = "2015-03-05";
				 claimant_starting_date.setText(claimantStartingDate);
				 claimant_ending_date.setText(itemEndingDate);
			
			/*
			 * Test for US01.04.01 Basic Flow 9
			 */				 
				 forthActivity.finish();
				 ListView ilv_up = (ListView) thirdActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.itemListView);
				// check if it is on screen
				ViewAsserts.assertOnScreen(decorView1, ilv_up);
				//finish activity
				thirdActivity.finish();
			}
		});
		
		nextActivity.finish();
		activity.finish();		
	}
}
