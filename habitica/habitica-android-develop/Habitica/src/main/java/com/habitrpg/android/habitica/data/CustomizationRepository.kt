package com.habitrpg.android.habitica.data

import com.habitrpg.android.habitica.models.inventory.Customization

import io.reactivex.rxjava3.core.Flowable
import io.realm.RealmResults

interface CustomizationRepository : BaseRepository {
    fun getCustomizations(type: String, category: String?, onlyAvailable: Boolean): Flowable<RealmResults<Customization>>
}
