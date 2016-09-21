package com.mac.training.collectioner.activity.collection;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mac.training.collectioner.FirebaseCollectionsController;
import com.mac.training.collectioner.R;
import com.mac.training.collectioner.model.Collection;

public class EditCollectionActivity extends AppCompatActivity {

    private EditText collectionName, descriptionName;
    private TextInputLayout inputLayoutNameCol, inputLayoutNameDesc;
    private Button btnEditCol;
    private Collection editableCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_collection);

        inputLayoutNameCol = (TextInputLayout) findViewById(R.id.input_layout_name_col);
        inputLayoutNameDesc = (TextInputLayout) findViewById(R.id.input_layout_desc_col);
        collectionName = (EditText) findViewById(R.id.txtNameColl);
        descriptionName = (EditText) findViewById(R.id.txtDescColl);
        btnEditCol = (Button) findViewById(R.id.btn_EditColl);

        editableCollection = getIntent().getParcelableExtra(EditCollectionActivity.class.getName());

        collectionName.setText(editableCollection.getCollection());

        btnEditCol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });
    }

    /**
     * Validating form
     */
    private void submitForm() {

        if (!validateText(collectionName, inputLayoutNameCol, getString(R.string.collection_name))) {
            return;
        }
        if (!validateText(descriptionName, inputLayoutNameDesc, getString(R.string.collection_description))) {
            return;
        }

        FirebaseCollectionsController.updateCollection("Josimar", collectionName.getText().toString(), "-KS7M_VFtvauOeS7m2Jh");

        Toast.makeText(getApplicationContext(), getString(R.string.collection_modified, collectionName.getText().toString()),
                Toast.LENGTH_SHORT).show();

        finish();
    }

    private boolean validateText(EditText editText, TextInputLayout textInputLayout, String componentName) {
        if (editText.getText().toString().trim().isEmpty()) {
            textInputLayout.setError(getString(R.string.txt_validation_message, componentName));
            requestFocus(editText);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

}
