package com.ferhatozcelik.jetpackcomposetemplate.ui.create

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferhatozcelik.jetpackcomposetemplate.data.model.Category
import com.ferhatozcelik.jetpackcomposetemplate.data.model.Priority
import com.ferhatozcelik.jetpackcomposetemplate.data.model.Routine
import com.ferhatozcelik.jetpackcomposetemplate.data.model.Zone
import com.ferhatozcelik.jetpackcomposetemplate.data.repository.AppRepository
import com.ferhatozcelik.jetpackcomposetemplate.util.MissingFieldException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.Period
import java.util.UUID
import javax.inject.Inject

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
    private val _selectedZone = mutableStateOf<Zone?>(null)

    val routineName: State<String> = _routineName
    val routineDescription: State<String> = _routineDescription
    val selectedCategory: State<Category?> = _selectedCategory
    val selectedStartDate: State<LocalDateTime> = _selectedStartDate
    val hasEndDate: State<Boolean> = _hasEndDate
    val selectedEndDate: State<LocalDateTime> = _selectedEndDate
    val selectedPriority: State<Priority?> = _selectedPriority
    val selectedPeriod: State<Period?> = _selectedPeriod
    val selectedZone: State<Zone?> = _selectedZone

    fun createRoutine(onSuccess: () -> Unit, onError: (Throwable) -> Unit) {
        viewModelScope.launch {
            try {
                val routine = Routine(
                    id = UUID.randomUUID(),
                    name = if (_routineName.value.isBlank()) throw MissingFieldException("nom") else _routineName.value,
                    description = _routineDescription.value,
                    category = _selectedCategory.value ?: throw MissingFieldException("catégorie"),
                    startTime = _selectedStartDate.value,
                    endTime = if (_hasEndDate.value) _selectedEndDate.value else null,
                    period = _selectedPeriod.value,
                    priority = _selectedPriority.value ?: throw MissingFieldException("importance"),
                    zone = _selectedZone.value
                )

                appRepository.addOrUpdateRoutine(routine)
                onSuccess()
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    fun setName(name: String) = run { _routineName.value = name }
    fun setDescription(desc: String) = run { _routineDescription.value = desc }
    fun setCategory(cat: Category) = run { _selectedCategory.value = cat }
    fun setStartDate(date: LocalDateTime) = run { _selectedStartDate.value = date }
    fun setHasEndDate(enabled: Boolean) = run { _hasEndDate.value = enabled }
    fun setEndDate(date: LocalDateTime) = run { _selectedEndDate.value = date }
    fun setPriority(priority: Priority) = run { _selectedPriority.value = priority }
    fun setPeriod(period: Period?) = run { _selectedPeriod.value = period }
    fun setDefaultZone() = run { _selectedZone.value = Zone(48.418263, -71.053342, 1000.0) } // UQAC
    fun setLatitude(lat: Double) = run { _selectedZone.value?.let { it.latitude = lat } }
    fun setLongitude(lon: Double) = run { _selectedZone.value?.let { it.longitude = lon } }
    fun setRadius(rad: Double) = run { _selectedZone.value?.let { it.radius = rad } }
}
