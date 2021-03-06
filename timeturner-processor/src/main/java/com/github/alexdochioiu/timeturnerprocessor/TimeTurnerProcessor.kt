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

package com.github.alexdochioiu.timeturnerprocessor

import java.util.*
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.TypeElement

/**
 * Created by Alexandru Iustin Dochioiu on 30-Jan-19
 *
 */
class TimeTurnerProcessor : AbstractProcessor() {

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        val supportedAnnotation = LinkedHashSet<String>()
        supportedAnnotation.add("com.github.alexdochioiu.timeturner.GlobalSurvivor")
        supportedAnnotation.add("com.github.alexdochioiu.timeturner.ConfigurationSurvivor")

        return supportedAnnotation
    }

    override fun process(p0: MutableSet<out TypeElement>?, p1: RoundEnvironment?): Boolean {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        return true
    }
}