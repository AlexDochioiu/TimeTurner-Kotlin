/*
 * Copyright (c) 2019 Alexandru Iustin Dochioiu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.github.alexdochioiu.example

import android.arch.lifecycle.Lifecycle
import android.util.Log
import com.github.alexdochioiu.timeturner.GlobalSurvivor
import com.github.alexdochioiu.timeturner.SurvivorBinding
import java.util.*

/**
 * Created by Alexandru Iustin Dochioiu on 30-Jan-19
 */
class TestPresenter(lifecycle: Lifecycle) {
    private val TAG = "NewPresenter"

    @GlobalSurvivor
    internal var survivingElements: MutableList<String> = ArrayList()

    init {
        Log.e(TAG, "beforeBinding: $survivingElements")

        SurvivorBinding.bind(this, lifecycle)
        Log.e(TAG, "afterBinding: $survivingElements")

        survivingElements.add(UUID.randomUUID().toString())
        Log.e(TAG, "afterNewEntryBinding: $survivingElements")
    }
}