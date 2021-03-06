package ca.ualberta.CMPUT301W15T06.test;


import ca.ualberta.CMPUT301W15T06.AppSingleton;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListController;
import ca.ualberta.CMPUT301W15T06.ClaimantEditClaimActivity;
import ca.ualberta.CMPUT301W15T06.MainActivity;
import ca.ualberta.CMPUT301W15T06.NetWorkException;
import ca.ualberta.CMPUT301W15T06.User;
import android.annotation.SuppressLint;
import android.app.Activity;
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
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("CutPasteId")
public class US01_06_01_Test extends
		ActivityInstrumentationTestCase2<MainActivity> {

	public US01_06_01_Test() {

		super(MainActivity.class);

	}

	Button ApproverButton;
	Button ClaimantButton;
	Instrumentation instrumentation;
	Activity activity;
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

	User u;
	Button UserButton;
	
	protected void setUp() throws Exception {
		super.setUp();
		AppSingleton.getInstance().setTest(true);
		instrumentation = getInstrumentation();
		activity = getActivity();
		setActivityInitialTouchMode(false);
		ApproverButton = (Button) activity
				.findViewById(ca.ualberta.CMPUT301W15T06.R.id.approverButton);
		ClaimantButton = (Button) activity
				.findViewById(ca.ualberta.CMPUT301W15T06.R.id.claimantButton);
		UserButton = (Button) activity
				.findViewById(ca.ualberta.CMPUT301W15T06.R.id.userButton);
		intent = new Intent(getInstrumentation().getTargetContext(),
				MainActivity.class);
		u = AppSingleton.getInstance().getCurrentUser();

	}
	
	

	public void test010601() {
		/*
		 * Test for US01.06.01 Basic Flow 1
		 */
		// test layout
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
		 * Test for US01.06.01 Basic Flow 2
		 */
		//User click "Change User"

		activity.runOnUiThread(new Runnable(){

			@Override
			public void run() {
				//open the dialog
				UserButton.performClick();
				}	
		});
			

		/*
		 * Test for US01.06.01 Basic Flow 6
		 */
					
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

				
		/*
		 * Test for US01.06.01 Basic Flow 7
		 */
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
		 * Test for US01.06.01 Basic Flow 8
		 */
		// after claimant request to add new claim, a new claim should be added into list
		int count1 = u.getClaimList().size();

		// Click the menu option
		// open third activity by options menu
		ActivityMonitor am = getInstrumentation().addMonitor(
				ClaimantEditClaimActivity.class.getName(), null, false);
		
		// Click the menu option
		// getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_MENU);
		getInstrumentation().invokeMenuActionSync(nextActivity,
				ca.ualberta.CMPUT301W15T06.R.id.add_new_claim, 1);
		Activity a = getInstrumentation().waitForMonitorWithTimeout(am,
				10000);

		input_start = (TextView) a
				.findViewById(ca.ualberta.CMPUT301W15T06.R.id.editClaimStartingDateTextView);
		input_end = (TextView) a
				.findViewById(ca.ualberta.CMPUT301W15T06.R.id.editClaimEndingDateTextView);
		final View decorView11 = a.getWindow().getDecorView();
		// test text view: createClaimStartingDateEditText
		ViewAsserts.assertOnScreen(decorView11, input_start);
		assertNotNull(input_start.getVisibility());
		// test textView: createClaimEndDateEditText
		ViewAsserts.assertOnScreen(decorView11, input_end);
		assertNotNull(input_end.getVisibility());

		int count2 = u.getClaimList().size() - 1;
		assertEquals("count2 = count1", count2, count1);
		
		a.finish();
		nextActivity.finish();
		activity.finish();
		
	}	
}