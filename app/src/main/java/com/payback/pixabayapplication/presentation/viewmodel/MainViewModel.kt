package com.payback.pixabayapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.payback.data.models.HitDataSource
import com.payback.data.usecase.RepositoryUsecase
import com.payback.domain.model.Hits
import com.payback.pixabayapp.presentation.util.AppSharedPreference
import com.payback.pixabayapplication.presentation.view.adapter.SearchAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repositoryUsecase: RepositoryUsecase,
    private val hitDataSource: HitDataSource,
    private val appSharedPreference: AppSharedPreference
) : ViewModel() {

    private var _showProgressBar = MutableLiveData<Boolean>(true)

    var showProgressBar: LiveData<Boolean> = _showProgressBar

    val hitListData = MutableLiveData<List<Hits>>()
    protected val compositeDisposable = CompositeDisposable()

    private var _queryData = MutableLiveData<String>()


    val queryData: LiveData<String> = _queryData


    fun populateRecyclyerViewWithHitList(adapter: SearchAdapter){
        hitDataSource.allHits
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<List<Hits>>(){
                override fun onNext(t: List<Hits>) {
                    adapter.setHitList(t)
                    Log.d("SearchAdapterHit", "Next${t.size}")
                }

                override fun onError(e: Throwable) {
                    Log.d("SearchAdapterHit", "Error$e")
                    e.printStackTrace()
                }

                override fun onComplete() {
                    Log.d("SearchAdapterHit", "Completed")
                }
            }).let {
                compositeDisposable.add(it)
            }
    }



    fun getHitList(query: String) {
        repositoryUsecase.getHits(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<List<Hits>>(){

                override fun onNext(t: List<Hits>) {
                    Log.d("TAGIS", "Next${t.size}")
                    hitListData.postValue(t)
                    viewModelScope.launch(Dispatchers.IO) {
                        hitDataSource.doWithTransaction(
                            hitDataSource.deleteAll(),
                            hitDataSource.insert(t)
                        )
                    }
                }

                override fun onError(e: Throwable) {
                    Log.d("TAGIS", "Error$e")
                    e.printStackTrace()
                }

                override fun onComplete() {
                    Log.d("TAGIS", "Completed")
                    _showProgressBar.postValue(false)
                }
            }).let {
                compositeDisposable.add(it)
            }
    }


    fun getQuery(){
        viewModelScope.launch(Dispatchers.IO) {
            _queryData.postValue(appSharedPreference.getQuery())
        }
    }



    fun changeQuery(query: String){
        viewModelScope.launch(Dispatchers.IO){
            appSharedPreference.saveQuery(query)
            _queryData.postValue(appSharedPreference.getQuery())
        }
    }



    fun processQuery(string: String): String{
        return repositoryUsecase.processQueryInApiFormat(string)
    }


    override fun onCleared() {
        compositeDisposable.clear()
    }
}