package ca.ualberta.CMPUT301W15T06.test;

import ca.ualberta.CMPUT301W15T06.ClaimantAddClaimActivity;
import ca.ualberta.CMPUT301W15T06.ClaimantClaimListActivity;
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

public class US01_01_01_Test_2 extends
		ActivityInstrumentationTestCase2<ClaimantAddClaimActivity> {

	public US01_01_01_Test_2() {
		super(ClaimantAddClaimActivity.class);
	}
	
	Button ApproverButton;
	Button ClaimantButton;
	Button FinishButton;
	Instrumentation instrumentation;
	Activity activity;
	EditText textInput;
	Intent intent;
	TextView input_name;
	TextView input_start;
	TextView input_end;
	ListView listView;
	EditText claimant_name;
	EditText claimant_starting_date;
	EditText claimant_ending_date;

	//set up
	protected void setUp() throws Exception {
		super.setUp();
		instrumentation = getInstrumentation();
		activity = getActivity();
		setActivityInitialTouchMode(true);
		input_name = (TextView) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimNameTextView);
		input_start = (TextView) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimStartingDateTextView);
		input_end = (TextView)activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimEndingDateTextView);
		claimant_name = ((EditText) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimNameEditText));
		claimant_starting_date = ((EditText) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimStartingDateEditText));
		claimant_ending_date = ((EditText) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimEndDateEditText));
		FinishButton = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimFinishButton);
		intent = new Intent(getInstrumentation().getTargetContext(), ClaimantAddClaimActivity.class);	
	}
	
	
	/*
	 * Test for US01.01.01 Basic Flow 7
	 */
	
	// test text view: createClaimNameEditText
	public void testClaimantNameTextView_layout() {
	    final View decorView = activity.getWindow().getDecorView();
	    ViewAsserts.assertOnScreen(decorView, input_name);
	    assertTrue(View.GONE == input_name.getVisibility());
	}
	
	// test text view: createClaimStartingDateEditText
	public void testClaimantStartingDateTextView_layout() {
	    final View decorView = activity.getWindow().getDecorView();
	    ViewAsserts.assertOnScreen(decorView, input_start);
	    assertTrue(View.GONE == input_start.getVisibility());
	}
	
	// test text view: createClaimEndDateEditText
	public void testClaimantEndingDateTextView_layout() {
	    final View decorView = activity.getWindow().getDecorView();
	    ViewAsserts.assertOnScreen(decorView, input_end);
	    assertTrue(View.GONE == input_end.getVisibility());
	}
	
	
	/*
	 * test US01.01.01 Basic Flow 8
	 */
	
	// fill blank 
	@SuppressWarnings("unused")
	private void testAddButton(String claimantName, String claimantStartingDate, String itemEndingDate) {
		assertNotNull(activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimFinishButton));
		claimant_name.setText(claimantName);
		claimant_starting_date.setText(claimantStartingDate);
		claimant_ending_date.setText(itemEndingDate);	
	}
	
	
	/*
	 * test US01.01.01 Basic Flow 9
	 * 
	 */
	
	// test Finish button layout
	public void testFinishButtonlayout() {
	    final View decorView = activity.getWindow().getDecorView();

	    ViewAsserts.assertOnScreen(decorView, FinishButton);

	    final ViewGroup.LayoutParams layoutParams =
	           FinishButton.getLayoutParams();
	    assertNotNull(layoutParams);
	    assertEquals(layoutParams.width, WindowManager.LayoutParams.WRAP_CONTENT);
	    assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
	    
	    Button view = (Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimFinishButton);
	    assertEquals("Incorrect label of the button", "Finish", view.getText());
	    
	    ((Button) activity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimFinishButton)).performClick();
	}
	
	
	/*
	 * test US01.01.01 Basic Flow 10
	 * 
	 */
	
	//US01.01.01 test finish button activity
	public void testOpenNextActivity() {
		  // register next activity that need to be monitored.
		  ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ClaimantClaimListActivity.class.getName(), null, false);

		  // open current activity.
		  ClaimantAddClaimActivity myActivity = getActivity();
		  final Button button = (Button) myActivity.findViewById(ca.ualberta.CMPUT301W15T06.R.id.createClaimFinishButton);
		  myActivity.runOnUiThread(new Runnable() {
		    @Override
		    public void run() {
		      // click button and open next activity.
		      button.performClick();
		    }
		  });

		  ClaimantClaimListActivity nextActivity = (ClaimantClaimListActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 1000);
		  // next activity is opened and captured.
		  assertNotNull(nextActivity);
		  nextActivity .finish();
	}
}