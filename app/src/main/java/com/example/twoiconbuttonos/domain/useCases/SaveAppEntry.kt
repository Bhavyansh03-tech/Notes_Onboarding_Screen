package com.example.twoiconbuttonos.domain.useCases

import com.example.twoiconbuttonos.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}