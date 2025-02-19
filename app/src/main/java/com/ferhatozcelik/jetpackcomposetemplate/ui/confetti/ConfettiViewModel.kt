package com.ferhatozcelik.jetpackcomposetemplate.ui.create

import androidx.lifecycle.ViewModel
import com.ferhatozcelik.jetpackcomposetemplate.data.repository.ExampleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CreateViewModel @Inject constructor(private val exampleRepository: ExampleRepository) :
    ViewModel() {
    private val TAG = CreateViewModel::class.java.simpleName


}
