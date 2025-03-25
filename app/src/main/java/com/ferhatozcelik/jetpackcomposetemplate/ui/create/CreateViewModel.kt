package com.ferhatozcelik.jetpackcomposetemplate.ui.create

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferhatozcelik.jetpackcomposetemplate.data.model.Category
import com.ferhatozcelik.jetpackcomposetemplate.data.model.Priority
import com.ferhatozcelik.jetpackcomposetemplate.data.model.Routine
import com.ferhatozcelik.jetpackcomposetemplate.data.repository.AppRepository
import com.ferhatozcelik.jetpackcomposetemplate.util.MissingFieldException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.Period
import java.util.UUID
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class CreateViewModel @Inject constructor(
    private val appRepository: AppRepository
) : ViewModel() {

    private val _routineName = mutableStateOf("")
    private val _routineDescription = mutableStateOf("")
    private val _selectedCategory = mutableStateOf<Category?>(null)
    private val _selectedStartDate = mutableStateOf(LocalDateTime.now())
    private val _hasEndDate = mutableStateOf(false)
    private val _selectedEndDate = mutableStateOf(LocalDateTime.now())
    private val _selectedPriority = mutableStateOf<Priority?>(null)
    private val _selectedPeriod = mutableStateOf<Period?>(null)

    val routineName: State<String> = _routineName
    val routineDescription: State<String> = _routineDescription
    val selectedCategory: State<Category?> = _selectedCategory
    val selectedStartDate: State<LocalDateTime> = _selectedStartDate
    val hasEndDate: State<Boolean> = _hasEndDate
    val selectedEndDate: State<LocalDateTime> = _selectedEndDate
    val selectedPriority: State<Priority?> = _selectedPriority
    val selectedPeriod: State<Period?> = _selectedPeriod

    fun createRoutine() {
        viewModelScope.launch {
            val routine = Routine(
                id = UUID.randomUUID(),
                name = if (routineName.value.isBlank()) throw MissingFieldException("nom") else routineName.value,
                description = routineDescription.value,
                category = selectedCategory.value ?: throw MissingFieldException("catégorie"),
                startTime = selectedStartDate.value,
                endTime = if (hasEndDate.value) selectedEndDate.value else null,
                period = selectedPeriod.value,
                priority = selectedPriority.value ?: throw MissingFieldException("importance")
            )

            appRepository.addOrUpdateRoutine(routine)
        }
    }

    fun setName(name: String) {
        _routineName.value = name
    }

    fun setDescription(description: String) {
        _routineDescription.value = description
    }

    fun setCategory(category: Category) {
        _selectedCategory.value = category
    }

    fun setStartDate(date: LocalDateTime) {
        _selectedStartDate.value = date
    }

    fun setHasEndDate(bool: Boolean) {
        _hasEndDate.value = bool
    }

    fun setEndDate(date: LocalDateTime) {
        _selectedEndDate.value = date
    }

    fun setPriority(priority: Priority) {
        _selectedPriority.value = priority
    }

    fun setPeriod(period: Period?) {
        _selectedPeriod.value = period
    }
}
