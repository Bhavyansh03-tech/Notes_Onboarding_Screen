package com.example.twoiconbuttonos.domain.useCases

import com.example.twoiconbuttonos.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUserManager: LocalUserManager
) {
    operator fun invoke() : Flow<Boolean> {
        return localUserManager.readAppEntry()
    }
}