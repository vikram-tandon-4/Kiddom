package kiddomchallenge.vikramtandonapps.com.interfaces;


import kiddomchallenge.vikramtandonapps.com.utils.AppConstants;
import kiddomchallenge.vikramtandonapps.com.model.SearchResultsModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("api/assessment_search_wrapper")
    Call<SearchResultsModel> getSearchResults(@Query(AppConstants.QUERY) String query);
}