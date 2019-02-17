package kiddomchallenge.vikramtandonapps.com.activity;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import kiddomchallenge.vikramtandonapps.com.R;
import kiddomchallenge.vikramtandonapps.com.utils.ApiClient;
import kiddomchallenge.vikramtandonapps.com.utils.Utils;
import kiddomchallenge.vikramtandonapps.com.adapter.SearchResultAdapter;
import kiddomchallenge.vikramtandonapps.com.interfaces.ApiInterface;
import kiddomchallenge.vikramtandonapps.com.model.ItemListElementModel;
import kiddomchallenge.vikramtandonapps.com.model.SearchResultsModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;
    private TextView tvNoData;
    private RecyclerView rvSearch;
    private Toolbar toolbar;
    private TextView toolBarHeader;
    EditText etSearch;
    Button btnSearch;
    private Dialog mProgressDialog;
    SearchResultAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        initializeViews();
    }

    private void initializeViews() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolBarHeader = (TextView) findViewById(R.id.tvTbTitle);
        toolBarHeader.setText(R.string.kiddom_challenge);

        tvNoData = (TextView) findViewById(R.id.tvNoData);
        rvSearch = (RecyclerView) findViewById(R.id.rvSearch);
        tvNoData.setText(R.string.no_data_available);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        rvSearch.setLayoutManager(mLayoutManager);
        rvSearch.setItemAnimator(new DefaultItemAnimator());

        etSearch = (EditText) findViewById(R.id.etSearch);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(this);
    }

    /*
Get Search Results

Using retrofit to retrieve data based in the query user has entered
@param: searchQuery

     */
    private void callMoviesApi(String searchQuery) {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<SearchResultsModel> call = apiService.getSearchResults(searchQuery);
        call.enqueue(new Callback<SearchResultsModel>() {
            @Override
            public void onResponse(Call<SearchResultsModel> call, Response<SearchResultsModel> response) {

                Utils.cancelProgressDialog(mProgressDialog);
                if (response.body() != null && response.body().getItemListElement() != null
                        && response.body().getItemListElement().size() > 0) {
                    populateResult(response.body().getItemListElement());
                } else {
                    rvSearch.setVisibility(View.GONE);
                    tvNoData.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<SearchResultsModel> call, Throwable t) {
                Utils.cancelProgressDialog(mProgressDialog);
                Toast.makeText(mContext, getString(R.string.api_failed), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void populateResult(ArrayList<ItemListElementModel> itemListElement) {
        rvSearch.setVisibility(View.VISIBLE);
        tvNoData.setVisibility(View.GONE);
        mAdapter = new SearchResultAdapter(itemListElement, mContext);
        rvSearch.setAdapter(mAdapter);
    }

    private void actionSearch() {

        if (TextUtils.isEmpty(etSearch.getText().toString())) {
            Toast.makeText(mContext, getString(R.string.empty_edit_text), Toast.LENGTH_SHORT).show();
        } else {
            if (Utils.isNetworkAvailable(mContext)) {
                mProgressDialog = Utils.showProgressDialog(mContext);
                callMoviesApi(etSearch.getText().toString());
                View v = this.getCurrentFocus();
                if (v != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            } else {
                Toast.makeText(mContext, getString(R.string.no_connectivity), Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSearch:
                actionSearch();
                break;
        }
    }
}
