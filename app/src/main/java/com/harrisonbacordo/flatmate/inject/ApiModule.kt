/*
 * Designed and developed by 2020 FlatMate (Harrison Bacordo)
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
 */
package com.harrisonbacordo.flatmate.inject

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.harrisonbacordo.flatmate.network.AuthApi
import com.harrisonbacordo.flatmate.network.AuthApiImpl
import com.harrisonbacordo.flatmate.network.ChoreApi
import com.harrisonbacordo.flatmate.network.ChoreApiImpl
import com.harrisonbacordo.flatmate.network.GroceryApi
import com.harrisonbacordo.flatmate.network.GroceryApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object ApiModule {

    /**
     * Provides a global instance of [AuthApi]
     */
    @Provides
    fun provideAuthApi(firebaseAuth: FirebaseAuth, firestore: FirebaseFirestore): AuthApi {
        return AuthApiImpl(firebaseAuth, firestore)
    }

    /**
     * Provides a global instance of [ChoreApi]
     */
    @Provides
    fun provideChoreApi(firebaseAuth: FirebaseAuth, firestore: FirebaseFirestore): ChoreApi {
        return ChoreApiImpl(firebaseAuth, firestore)
    }

    /**
     * Provides a global instance of [GroceryApi]
     */
    @Provides
    fun provideGroceryApi(firebaseAuth: FirebaseAuth, firestore: FirebaseFirestore): GroceryApi {
        return GroceryApiImpl(firebaseAuth, firestore)
    }
}
