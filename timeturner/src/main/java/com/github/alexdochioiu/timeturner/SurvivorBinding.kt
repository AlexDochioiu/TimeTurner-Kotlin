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

package com.github.alexdochioiu.timeturner

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import java.lang.reflect.Constructor
import java.util.HashMap

/**
 * Created by Alexandru Iustin Dochioiu on 30-Jan-19
 *
 */
object SurvivorBinding {
    private val saverMap = HashMap<String, InstanceSaver<*>>()

    fun <Y : Any> bind(obj: Y, lifecycle: Lifecycle) {
        val className = obj.javaClass.name

        val saver: InstanceSaver<Y>? = saverMap.remove(className) as? InstanceSaver<Y>
        saver?.restore(obj)

        lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroy() {
                try {
                    val kConstructor = Class.forName(className + "_Binding").kotlin.constructors.toMutableList().get(0)

                    val saverInstance = kConstructor.call(obj) as? InstanceSaver<Y>

                    if (saverInstance != null) {
                        saverMap[className] = saverInstance
                    }
                } catch (exception: ClassNotFoundException) {
                    throw IllegalStateException("TimeTurner encountered a problem finding the generated binding class." +
                            " Did you include the TimeTurner-Processor dependency?!")
                } catch (illegalArgException: IllegalArgumentException) {
                    throw IllegalArgumentException("TimeTurner encountered a problem using the generated binding class." +
                            " Did you use the same version for the TimeTurner and TimeTurner-Processor dependencies?")
                }
                finally {
                    lifecycle.removeObserver(this)
                }
            }
        })

    }

    abstract class InstanceSaver<T> {
        abstract fun restore(tClass: T)
    }
}